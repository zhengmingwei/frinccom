package com.cfcp.incc.utils.generator.idgenerator;

/**
 * <p>
 *
 * @author zyj
 * @date 2016/4/7 0007
 * @since 0.1
 */
import com.cfcp.incc.utils.generator.UUIDGenerator;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class BasicEntityIdGenerator extends UUIDGenerator implements EntityIdGenerator {
	//   id format  =>
//   timestamp |datacenter | sequence
//   41        |10         |  12
	private final long sequenceBits = 12;
	private final long datacenterIdBits = 10L;
	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

	private final long datacenterIdShift = sequenceBits;
	private final long timestampLeftShift = sequenceBits + datacenterIdBits;

	private final long twepoch = 1288834974657L;
	private final long datacenterId;
	private final long sequenceMax = 4096;

	private volatile long lastTimestamp = -1L;
	private volatile long sequence = 0L;

	private static BasicEntityIdGenerator basicEntityIdGenerator;

	public static BasicEntityIdGenerator getInstance() throws RuntimeException {
		if (basicEntityIdGenerator == null) basicEntityIdGenerator = new BasicEntityIdGenerator();
		return basicEntityIdGenerator;
	}

	public BasicEntityIdGenerator() throws RuntimeException {
		datacenterId = getDatacenterId();
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new RuntimeException("datacenterId > maxDatacenterId");
		}
	}

	@Override
	public synchronized String generateLongId(){
		long timestamp = System.currentTimeMillis();
		if (timestamp < lastTimestamp) {
			try {
				throw new RuntimeException("Clock moved backwards.  Refusing to generate id for " + (
						lastTimestamp - timestamp) + " milliseconds.");
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) % sequenceMax;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}
		lastTimestamp = timestamp;
		Long id = ((timestamp - twepoch) << timestampLeftShift) |
				(datacenterId << datacenterIdShift) |
				sequence;
		return id.toString();
	}

	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = System.currentTimeMillis();
		while (timestamp <= lastTimestamp) {
			timestamp = System.currentTimeMillis();
		}
		return timestamp;
	}

	protected long getDatacenterId() throws RuntimeException {

		try {
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			long id;
			if (network == null || network.getHardwareAddress() == null) {
				id = 1;
			} else {
				byte[] mac = network.getHardwareAddress();
				id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
			}
			return id;
		} catch (SocketException e) {
			throw new RuntimeException(e);
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}


	public Serializable generate(){
		String result = null;
		try {
			result = getInstance().generateLongId();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(getInstance().generateLongId());
	}
}
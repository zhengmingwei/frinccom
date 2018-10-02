package com.cfcp.incc.utils.generator.idgenerator;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *
 * @author zyj
 * @date 2016/4/7 0007
 * @since 0.1
 */
public class BasicEntityIdGeneratorTest {
	public static void main(String[] args) throws GetHardwareIdFailedException, InvalidSystemClockException {
		Set<String> ids = Collections.synchronizedSet(new HashSet<String>());

		ExecutorService executorService = Executors.newFixedThreadPool(500);

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i< 1000; i++ ){
					String result = null;
					try {
						result = BasicEntityIdGenerator.getInstance().generateLongId();
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
					//ids should be unique
					Assert.isTrue(!ids.contains(result), "xxxxxx");
					ids.add(result);
					Assert.isTrue(!result.startsWith("-"), "should not start with - ");
					System.out.println(result);
				}
			}
		});
		executorService.shutdown();
	}
}
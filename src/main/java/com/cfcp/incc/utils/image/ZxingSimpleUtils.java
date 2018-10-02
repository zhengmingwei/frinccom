package com.cfcp.incc.utils.image;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * zxing生成二维条码
 * 
 * @author bolen
 * 
 */
public class ZxingSimpleUtils {

	/**
	 * 二维码编码输出文件
	 * @param text
	 * @param width
	 * @param height
	 * @param format
	 * @param outFile
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void encode(String text, int width, int height, String format, File outFile) throws WriterException, IOException {
		QRCodeWriter writer = new QRCodeWriter();
		//text = new String(text.getBytes("UTF-8"),"iso-8859-1");
		BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
		MatrixToImageWriter.writeToPath(matrix, format, outFile.toPath());
	}
	/**
	 * 二维码编码输出文件
	 * @param text
	 * @param width
	 * @param height
	 * @param format
	 * @param outFile
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void encodeWithMargin(String text, int width, int height, int margin, String format, File outFile) throws WriterException, IOException {
		QRCodeWriter writer = new QRCodeWriter();
		//text = new String(text.getBytes("UTF-8"),"iso-8859-1");
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.MARGIN, margin);
		BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageWriter.writeToPath(matrix, format, outFile.toPath());
	}

	/**
	 * 二维码编码输出文件
	 * @param text
	 * @param width
	 * @param height
	 * @param format
	 * @param outFilePath
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void encode(String text, int width, int height, String format, String outFilePath) throws WriterException, IOException {
		File file = new File(outFilePath);
		encode(text, width, height, format, file);
	}
	public static void encodeWithMargin(String text, int width, int height, int margin, String format, String outFilePath) throws WriterException, IOException {
		File file = new File(outFilePath);
		encodeWithMargin(text, width, height, margin, format, file);
	}

	/**
	 * 二维码解码
	 * @param inFile
	 * @return
	 * @throws Exception
	 */
	public static Result decode(File inFile) throws Exception {
		QRCodeReader reader = new QRCodeReader();
		BufferedImage image = ImageIO.read(inFile);
		LuminanceSource source = new BufferedImageLuminanceSource(image );
		Binarizer binarizer = new HybridBinarizer(source );
		BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer  );
		Result result = reader.decode(imageBinaryBitmap);
		return result;
	}

	/**
	 * 二维码解码
	 * @param inFilePath
	 * @return
	 * @throws Exception
	 */
	public static Result decode(String inFilePath) throws Exception {
		return decode(new File(inFilePath));
	}

	public static void main(String[] args) throws Exception{
		String text = "http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E4%B8%AD%E6%96%87&rsv_pq=d28a177300014d83&rsv_t=389ej7r2Sh65RtUUm3w9Z%2FK4V7TE3VIngcMXJ9YhKYD2%2FdzAgXqeV%2Fjl9oc&rsv_enter=1&rsv_sug3=1&rsv_sug4=377&rsv_sug1=1&rsv_sug2=0&inputT=2605";

		text = "http://192.168.1.14:8081/incc/certification/201809271847176283";
		String outFile = "/Users/zhyj/Documents/商品认证/init/qrlogo.gif";
		outFile = "F:\\data\\incc\\certification\\temp\\2018\\9\\201809261191945747 - 副本.png";
		try {
			encodeWithMargin(text, 800, 800,0, "png", outFile);
//			System.out.println(decode(outFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
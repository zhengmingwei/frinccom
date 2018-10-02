package com.cfcp.incc.utils;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理常用的文件类型；
 *
 * <p>
 *
 * @author zyj
 * @date 12/15/2014
 * @since 0.1
 */
public class FileTypeUtils {
	public static final Map<String, Integer> FILE_TYPE_MAP = new HashMap<String, Integer>();
	//类型:1=图片，2=视频，3=OFFICE文档，40=其他；
	public static final Integer TYPE_PICTURE = Integer.valueOf(1);
	public static final Integer TYPE_VIDEO = Integer.valueOf(2);
	public static final Integer TYPE_OFFICE = Integer.valueOf(3);
	public static final Integer TYPE_RADIO = Integer.valueOf(4);
	public static final Integer TYPE_OTHER = Integer.valueOf(40);

	static {
		//picture
		FILE_TYPE_MAP.put("jpg", TYPE_PICTURE); // JPEG (jpg)
		FILE_TYPE_MAP.put("jpeg", TYPE_PICTURE); // JPEG (jpg)
		FILE_TYPE_MAP.put("png", TYPE_PICTURE); // PNG (png)
		FILE_TYPE_MAP.put("gif", TYPE_PICTURE); // GIF (gif)
		FILE_TYPE_MAP.put("bmp", TYPE_PICTURE); // Windows Bitmap (bmp)

		//video
		FILE_TYPE_MAP.put("ram", TYPE_VIDEO); // Real Audio (ram)
		FILE_TYPE_MAP.put("rm", TYPE_VIDEO); // Real Media (rm)
		FILE_TYPE_MAP.put("mov", TYPE_VIDEO); // Quicktime (mov)
		FILE_TYPE_MAP.put("rmvb", TYPE_VIDEO); // rmvb
		FILE_TYPE_MAP.put("avi", TYPE_VIDEO);
		FILE_TYPE_MAP.put("flv", TYPE_VIDEO);
		FILE_TYPE_MAP.put("mp4", TYPE_VIDEO);
		FILE_TYPE_MAP.put("wmv", TYPE_VIDEO);
		FILE_TYPE_MAP.put("3gp", TYPE_VIDEO);
		FILE_TYPE_MAP.put("mkv", TYPE_VIDEO);

		//radio
		FILE_TYPE_MAP.put("wav", TYPE_RADIO); // Wave (wav)
		FILE_TYPE_MAP.put("mid", TYPE_RADIO); // MIDI (mid)
		FILE_TYPE_MAP.put("mp3", TYPE_RADIO);
		FILE_TYPE_MAP.put("wav", TYPE_RADIO);
		FILE_TYPE_MAP.put("aac", TYPE_RADIO);
		FILE_TYPE_MAP.put("wv", TYPE_RADIO);
		FILE_TYPE_MAP.put("flac", TYPE_RADIO);

		//office
		FILE_TYPE_MAP.put("doc", TYPE_OFFICE); // word
		FILE_TYPE_MAP.put("docx", TYPE_OFFICE); // word
		FILE_TYPE_MAP.put("ppt", TYPE_OFFICE);
		FILE_TYPE_MAP.put("pptx", TYPE_OFFICE);
		FILE_TYPE_MAP.put("xls", TYPE_OFFICE);
		FILE_TYPE_MAP.put("xlsx", TYPE_OFFICE);
		FILE_TYPE_MAP.put("mpp", TYPE_OFFICE);
		FILE_TYPE_MAP.put("mppx", TYPE_OFFICE);
		FILE_TYPE_MAP.put("vsd", TYPE_OFFICE);
		FILE_TYPE_MAP.put("vsdx", TYPE_OFFICE);
	}

	public static Integer getFileType(String filename) {
		String suffix = StringUtils.getFilenameExtension(filename);
		Integer type = FILE_TYPE_MAP.get(suffix);
		if(type == null) {
			type = TYPE_OTHER;
		}
		return type;
	}


//	static {
//		FILE_TYPE_MAP.put("ffd8ffe000104a464946", "jpg"); //JPEG (jpg)
//		FILE_TYPE_MAP.put("89504e470d0a1a0a0000", "png"); //PNG (png)
//		FILE_TYPE_MAP.put("47494638396126026f01", "gif"); //GIF (gif)
//		FILE_TYPE_MAP.put("49492a00227105008037", "tif"); //TIFF (tif)
//		FILE_TYPE_MAP.put("424d228c010000000000", "bmp"); //16色位图(bmp)
//		FILE_TYPE_MAP.put("424d8240090000000000", "bmp"); //24位位图(bmp)
//		FILE_TYPE_MAP.put("424d8e1b030000000000", "bmp"); //256色位图(bmp)
//		FILE_TYPE_MAP.put("41433130313500000000", "dwg"); //CAD (dwg)
//		FILE_TYPE_MAP.put("3c21444f435459504520", "html"); //HTML (html)
//		FILE_TYPE_MAP.put("3c21646f637479706520", "htm"); //HTM (htm)
//		FILE_TYPE_MAP.put("48544d4c207b0d0a0942", "css"); //css
//		FILE_TYPE_MAP.put("696b2e71623d696b2e71", "js"); //js
//		FILE_TYPE_MAP.put("7b5c727466315c616e73", "rtf"); //Rich Text Format (rtf)
//		FILE_TYPE_MAP.put("38425053000100000000", "psd"); //Photoshop (psd)
//		FILE_TYPE_MAP.put("46726f6d3a203d3f6762", "eml"); //Email [Outlook Express 6] (eml)
//		FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc"); //MS Excel 注意：word、msi 和 excel的文件头一样
//		FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "vsd"); //Visio 绘图
//		FILE_TYPE_MAP.put("5374616E64617264204A", "mdb"); //MS Access (mdb)
//		FILE_TYPE_MAP.put("252150532D41646F6265", "ps");
//		FILE_TYPE_MAP.put("255044462d312e350d0a", "pdf"); //AdobeAcrobat (pdf)
//		FILE_TYPE_MAP.put("2e524d46000000120001", "rmvb"); //rmvb/rm相同
//		FILE_TYPE_MAP.put("464c5601050000000900", "flv"); //flv与f4v相同
//		FILE_TYPE_MAP.put("00000020667479706d70", "mp4");
//		FILE_TYPE_MAP.put("49443303000000002176", "mp3");
//		FILE_TYPE_MAP.put("000001ba210001000180", "mpg"); //
//		FILE_TYPE_MAP.put("3026b2758e66cf11a6d9", "wmv"); //wmv与asf相同
//		FILE_TYPE_MAP.put("52494646e27807005741", "wav"); //Wave (wav)
//		FILE_TYPE_MAP.put("52494646d07d60074156", "avi");
//		FILE_TYPE_MAP.put("4d546864000000060001", "mid"); //MIDI (mid)
//		FILE_TYPE_MAP.put("504b0304140000000800", "zip");
//		FILE_TYPE_MAP.put("526172211a0700cf9073", "rar");
//		FILE_TYPE_MAP.put("235468697320636f6e66", "ini");
//		FILE_TYPE_MAP.put("504b03040a0000000000", "jar");
//		FILE_TYPE_MAP.put("4d5a9000030000000400", "exe");//可执行文件
//		FILE_TYPE_MAP.put("3c25402070616765206c", "jsp");//jsp文件
//		FILE_TYPE_MAP.put("4d616e69666573742d56", "mf");//MF文件
//		FILE_TYPE_MAP.put("3c3f786d6c2076657273", "xml");//xml文件
//		FILE_TYPE_MAP.put("494e5345525420494e54", "sql");//xml文件
//		FILE_TYPE_MAP.put("7061636b616765207765", "java");//java文件
//		FILE_TYPE_MAP.put("406563686f206f66660d", "bat");//bat文件
//		FILE_TYPE_MAP.put("1f8b0800000000000000", "gz");//gz文件
//		FILE_TYPE_MAP.put("6c6f67346a2e726f6f74", "properties");//bat文件
//		FILE_TYPE_MAP.put("cafebabe0000002e0041", "class");//bat文件
//		FILE_TYPE_MAP.put("49545346030000006000", "chm");//bat文件
//		FILE_TYPE_MAP.put("04000000010000001300", "mxp");//bat文件
//		FILE_TYPE_MAP.put("504b0304140006000800", "docx");//docx文件
//		FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "wps");//WPS文字wps、表格et、演示dps都是一样的
//		FILE_TYPE_MAP.put("6431303a637265617465", "torrent");
//
//		FILE_TYPE_MAP.put("6D6F6F76", "mov"); //Quicktime (mov)
//		FILE_TYPE_MAP.put("FF575043", "wpd"); //WordPerfect (wpd)
//		FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx"); //Outlook Express (dbx)
//		FILE_TYPE_MAP.put("2142444E", "pst"); //Outlook (pst)
//		FILE_TYPE_MAP.put("AC9EBD8F", "qdf"); //Quicken (qdf)
//		FILE_TYPE_MAP.put("E3828596", "pwl"); //Windows Password (pwl)
//		FILE_TYPE_MAP.put("2E7261FD", "ram"); //Real Audio (ram)
//	}

}

package com.cfcp.incc.utils.image;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 缩略图重命名
 * <p>
 * <p>
 *
 * @author zyj
 * @date 1/12/2015
 * @since 0.1
 */
public class RenameThumbnail extends Rename {
	public final static RenameThumbnail RENAME_HLT_THUMBNAIL = new RenameThumbnail();

	private RenameThumbnail() {
	}

	public final static String FIX_STR = "_";
	public final static String WH_FIX_STR = "x";

	@Override
	public String apply(String name, ThumbnailParameter param) {
		String suffix = generatorSuffix(param.getSize());
		return appendFixName(name, suffix);
	}

	public static String generator(String name, String width, String height) {
		String suffix = generatorSuffix(width, height);
		return appendFixName(name, suffix);
	}

	/**
	 * Appends a suffix to a filename.
	 *
	 * @param fileName UploadedFile name to add a suffix on.
	 * @param suffix   The suffix to add.
	 * @return UploadedFile name with specified suffixed affixed.
	 */
	private static String appendFixName(String fileName, String suffix) {
		String newFileName = "";
		int indexOfDot = fileName.lastIndexOf('.');
		if (indexOfDot != -1) {
			newFileName = fileName.substring(0, indexOfDot);
			newFileName += suffix;
			newFileName += fileName.substring(indexOfDot);
		} else {
			newFileName = fileName + suffix;
		}
		return newFileName;
	}

	/**
	 * 生成后缀名
	 *
	 * @param dimension
	 * @return
	 */
	public static String generatorSuffix(Dimension dimension) {
		String str = "";
		return generatorSuffix(((int) dimension.getWidth()) + str, ((int) dimension.getHeight()) + str);
	}

	/**
	 * 生成后缀名
	 *
	 * @param width
	 * @param height
	 * @return
	 */
	public static String generatorSuffix(Integer width, Integer height) {
		return generatorSuffix(width.toString(), height.toString());
	}

	/**
	 * 生成后缀名
	 *
	 * @param width
	 * @param height
	 * @return
	 */
	public static String generatorSuffix(String width, String height) {
		StringBuilder sb = new StringBuilder();
		sb.append(FIX_STR).append(width).append(WH_FIX_STR).append(height);
		return sb.toString();
	}

	/**
     * 缩略图处理工具类；
     * <p>
     *     包含图片缩放、比例剪裁缩放、水印
     * </p>
     *
     *  @author zyj
     * @date 1/12/2015
     * @since 0.1
     */
    public static class ThumbnailUtils {

        public final static String JPG = "jpg";
        public final static String PNG = "png";
        public final static String BMP = "bmp";

        /**
         * 创建一个缩略图
         * @param inFile 源文件
         * @param outFile 文件
         * @param width
         * @param height
         * @param aspectRatio
         * @param quality
         * @throws IOException
         */
        public static void createThumbnail(File inFile, File outFile, int width, int height, boolean aspectRatio, float quality) throws IOException {
            Thumbnails.of(inFile).size(width, height).outputQuality(quality).keepAspectRatio(aspectRatio).toFile(outFile);
        }

        /**
         * 创建一个缩略图
         * @param inFile 源文件
         * @param destinationDir 输出目录
         * @param width
         * @param height
         * @param aspectRatio
         * @param quality
         * @throws IOException
         */
        public static void createThumbnailAutoSuffix(File inFile, File destinationDir, int width, int height, boolean aspectRatio, float quality) throws IOException {
            if(!destinationDir.exists()) {
                destinationDir.mkdirs();
            }
            Thumbnails.of(inFile).size(width, height).outputQuality(quality).keepAspectRatio(aspectRatio).toFiles(destinationDir, RENAME_HLT_THUMBNAIL);
        }

        /**
         * 请参考{@link #createThumbnailAutoSuffix(String, String, int, int, boolean, float)}
         * 默认quality=0.8
         * @param inFile
         * @param destinationDir
         * @param width
         * @param height
         * @param aspectRatio
         * @throws IOException
         */
        public static void createThumbnailAutoSuffix(File inFile, File destinationDir, int width, int height, boolean aspectRatio) throws IOException {
            createThumbnailAutoSuffix(inFile, destinationDir, width, height, aspectRatio, 0.8f);
        }

        /**
         * 请参考{@link #createThumbnailAutoSuffix(String, String, int, int, boolean, float)}
         * @param inFile
         * @param destinationDir
         * @param width
         * @param height
         * @param aspectRatio
         * @param quality
         * @throws IOException
         */
        public static void createThumbnailAutoSuffix(String inFile, String destinationDir, int width, int height, boolean aspectRatio, float quality) throws IOException {
            createThumbnailAutoSuffix(new File(inFile), new File(destinationDir), width, height, aspectRatio, quality);
        }

        /**
         * 请参考{@link #createThumbnailAutoSuffix(String, String, int, int, boolean, float)};
         * 默认quality=0.8
         * @param inFile
         * @param destinationDir
         * @param width
         * @param height
         * @param aspectRatio
         * @throws IOException
         */
        public static void createThumbnailAutoSuffix(String inFile, String destinationDir, int width, int height, boolean aspectRatio) throws IOException {
            createThumbnailAutoSuffix(inFile, destinationDir, width, height, aspectRatio, 0.8f);
        }

        /**
         * 压缩至指定图片尺寸（例如：横400高300），保持图片不变形，多余部分裁剪掉;默认图片质量0.8f
         * @param inFile
         * @param outFile
         * @param width
         * @param height
         * @param format default jpg
         */
        public static void cropThumbnail(File inFile, File outFile, int width, int height, String format) throws IOException {
            cropThumbnail(inFile, outFile, width, height, format, 0.8f);
        }

        /**
         * 压缩至指定图片尺寸（例如：横400高300），保持图片不变形，多余部分裁剪掉;默认图片质量0.8f
         * @param inFile
         * @param outFile
         * @param width
         * @param height
         * @param quality 图片质量
         * @param format default jpg
         */
        public static void cropThumbnail(File inFile, File outFile, int width, int height, String format, float quality) throws IOException {
            cropThumbnail(inFile, outFile, width, height, format, quality, Boolean.FALSE);
        }

        /**
         * 压缩至指定图片尺寸（例如：横400高300），保持图片不变形，多余部分裁剪掉;默认图片质量0.8f
         * @param inFile
         * @param outFile
         * @param width
         * @param height
         * @param quality 图片质量
         * @param keepAspectRatio 比率
         */
        public static void cropThumbnail(File inFile, File outFile, int width, int height, String format, float quality, boolean keepAspectRatio) throws IOException {
            BufferedImage image = ImageIO.read(inFile);
            Thumbnails.Builder<BufferedImage> builder = null;
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            if(imageWidth < width && imageHeight < height) {
                builder = Thumbnails.of(image).size(imageWidth, imageHeight);
                quality = 1f;
            } else {
                if ((float)height / width != (float)imageWidth / imageHeight) {
                    int calcWidth = imageWidth * height / imageHeight;
                    int calcHeight = imageHeight * width / imageWidth;
                    if (imageWidth > imageHeight) {
                        image = Thumbnails.of(inFile).height(calcHeight).asBufferedImage();
    //				height = calcHeight;
                    } else {
                        image = Thumbnails.of(inFile).width(calcWidth).asBufferedImage();
    //				width = calcWidth;
                    }
    //			if (imageWidth > imageHeight) {
    //				image = Thumbnails.of(inFile).height(height).asBufferedImage();
    //			} else {
    //				image = Thumbnails.of(inFile).width(width).asBufferedImage();
    //			}
                    builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height).size(width, height);
                } else {
                    builder = Thumbnails.of(image).size(width, height);
                }
            }
            builder.keepAspectRatio(keepAspectRatio).outputFormat(format).outputQuality(quality).toFile(outFile);
        }

        /**
         * 指定坐标剪裁图片
         * @param inFile
         * @param outFile
         * @param x
         * @param y
         * @param width
         * @param height
         * @throws IOException
         */
        public static void cropThumbnail(File inFile, File outFile, int x, int y, int width, int height) throws IOException {
            Thumbnails.of(inFile).sourceRegion(x, y, width, height).size(width, height).outputQuality(0.8f).keepAspectRatio(false).toFile(outFile);
        }

        /**
         * 请参考{@link #cropThumbnail(File, File, int, int, String)}
         * @param inFile
         * @param destinationDir
         * @param width
         * @param height
         * @throws IOException
         */
        public static void cropThumbnail(File inFile, File destinationDir, int width, int height) throws IOException {
            if(!destinationDir.exists()) {
                destinationDir.mkdirs();
            }
            String outFilePath = generator(destinationDir.getAbsolutePath() + "/" + inFile.getName(), Integer.toString(width), Integer.toString(height));
            cropThumbnail(inFile, new File(outFilePath), width, height, JPG);
        }

        /**
         * 请参考{@link #cropThumbnail(File, File, int, int, String)}
         * @param inFile
         * @param destinationDir
         * @param width
         * @param height
         * @throws IOException
         */
        public static void cropThumbnail(String inFile, String destinationDir, int width, int height) throws IOException {
            File dir = new File(destinationDir);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            cropThumbnail(new File(inFile), dir, width, height);
        }

        /**
         * 图片打上水印
         * @param inFile 文件地址
         * @param outFile 输出地址
         * @param watermarkFile 水印图片地址
         * @param width 压缩宽度
         * @param height 压缩高度
         * @param position 水印位置 参考 e.g: {@link Positions#BOTTOM_RIGHT}
         * @param quality 图片质量
         * @throws IOException
         */
        public static void watermark(String inFile, String outFile, String watermarkFile, int width, int height, Position position, float quality) throws IOException {
            Thumbnails.of(new File(inFile)).size(width, height).watermark(position, ImageIO.read(new File(watermarkFile)), quality).toFile(new File(outFile));
        }

        /**
         * 图片打上水印
         * @param inFile 文件地址
         * @param outFile 输出地址
         * @param watermarkFile 水印图片地址
         * @param position 水印位置 参考 e.g: {@link Positions#BOTTOM_RIGHT}
         * @param quality 图片质量
         * @throws IOException
         */
        public static void watermark(String inFile, String outFile, String watermarkFile, Position position, float quality) throws IOException {
            BufferedImage image = ImageIO.read(new File(inFile));
            Thumbnails.of(new File(inFile)).size(image.getWidth(), image.getHeight()).watermark(position, ImageIO.read(new File(watermarkFile)), quality).toFile(new File(outFile));
        }

        /**
         * 图片打上水印;默认质量为0.8f
         * @param inFile 文件地址
         * @param outFile 输出地址
         * @param watermarkFile 水印图片地址
         * @param position 水印位置 参考 e.g: {@link Positions#BOTTOM_RIGHT}
         * @throws IOException
         */
        public static void watermark(String inFile, String outFile, String watermarkFile, Position position) throws IOException {
            BufferedImage image = ImageIO.read(new File(inFile));
            Thumbnails.of(new File(inFile)).size(image.getWidth(), image.getHeight()).watermark(position, ImageIO.read(new File(watermarkFile)), 0.8f).toFile(new File(outFile));
        }


        /**
         *
         * 自动处理图片；按照比例压缩
         *
         * @param fromFile
         *            源图片文件
         * @param targetFile
         *            目标图片文件
         * @param targetWidth
         *            目标图片文件宽 高
         * @param targetHeight
         *            目标图片文件 高
         * @param quality
         *            清晰度
         * @throws IOException
         */
        public static void thumbnail(File fromFile, File targetFile, int targetWidth, int targetHeight, float quality) throws IOException {
            BufferedImage srcImage = null;
            int s_height = 0;
            int s_width = 0;
            srcImage = ImageIO.read(fromFile);
            // 获取源文件的宽高
            s_height = srcImage.getHeight();
            s_width = srcImage.getWidth();
            // 判断是否需要压缩
            if (targetWidth != s_width || targetHeight != s_height) {// 需要压缩
                BigDecimal n = new BigDecimal(s_height).divide(new BigDecimal(s_width), 10, BigDecimal.ROUND_UP);// 源图片文件高/宽  的比例
                BigDecimal m = new BigDecimal(targetHeight).divide(new BigDecimal(targetWidth), 10, BigDecimal.ROUND_UP);// 目标图片文件高/宽 的比例
                int flag = n.compareTo(m); //比较源图片文件和目标图片 的高/宽 比
                try {
                    if (flag == 0) {// n == m，高/宽 比一致, 无需裁减, 直接压缩
                        Thumbnails.of(fromFile).forceSize(targetWidth, targetHeight).toFile(targetFile);
                    } else { // 高/宽 比不一致，需要裁减
                        if (flag > 0) {// 高的比例 > 宽的比例，以宽的比例进行缩放， 需要裁减高
                            srcImage = Thumbnails.of(fromFile).width(targetWidth).asBufferedImage();//以宽的比例进行缩放
                        } else if (flag < 0) {// n < m 高的比例 < 宽的比例，以高的比例进行缩放， 需要裁减宽
                            srcImage = Thumbnails.of(fromFile).height(targetHeight).asBufferedImage();//以高的比例进行缩放
                        }
                        // 居中裁减
                        Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(srcImage)
                                .sourceRegion(Positions.CENTER, targetWidth, targetHeight).size(targetWidth, targetHeight);
                        builder.outputQuality(quality).toFile(targetFile);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


        public static void main(String[] args) {
            File inFile = new File("/Users/zyj/Desktop/img/test.jpg");
            File outFile = new File("/Users/zyj/Desktop/img/test_2.jpg");
            File outFile2 = new File("/Users/zyj/Desktop/img/test_22.jpg");
            File outFile3 = new File("/Users/zyj/Desktop/img/test_3.jpg");
            try {
                thumbnail(inFile, outFile, 379, 630, 0.8f);
                thumbnail(inFile, outFile2, 379, 630, 1);
                cropThumbnail(inFile, outFile3, 379, 630, JPG, 0.8f, Boolean.TRUE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.cfcp.incc.utils.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by zhyj on 2017/7/21.
 */
public class PngCompositionUtil {

    public static void compositionFile(File qrCodeFile, File templateFile, File outputFile){
        try {
            BufferedImage buffImg = ImageIO.read(templateFile);

            //得到画笔对象
            Graphics g = buffImg.getGraphics();

            //创建你要附加的图象。
            //小图片的路径
//            ImageIcon imgIcon = new ImageIcon(qrCodeFileName);

            BufferedImage img = ImageIO.read(qrCodeFile);

            //得到Image对象。
//            Image img = imgIcon.getImage();

            //将小图片绘到大图片上。
            //5,300 .表示你的小图片在大图片上的位置。
//            g.drawImage(img,1723,34,null);
            g.drawImage(img,2771,49,null);

            //设置颜色。
//            g.setColor(Color.BLACK);
//
//            float lineWidth = 3f;
//
//            ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
//
//            g.drawRect(1717, 28, 631,631);


            g.dispose();

            ImageIO.write(buffImg, "png", outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception{
        //String path = "http://www.cfcp-incc.org/incc/commodity/";
        String path = "F:/data/incc/qrcode/temp/2018/9/";
        String[] text = {/*
                "201707120748986490",
                "201707120748986491",
                "201707120748986492",
                "201707120748986493",
                "201707120748986494"*/
               /* "201707120748986490",
                "201707120748986491",
                "201707120748986492",
                "201707120748986493",
                "201707120748986494"*/
                "201809260397383203",
                "201809261191945747",
                "201809260219011923"
        };
  /*      String qrFile = "F:/data/incc/qrcode/temp/2018/9";
        String templateFileName = "/Users/zhyj/Documents/商品认证/ICON01.png";
        String outputFileName = "/Users/zhyj/Documents/商品认证/init/";
*/

        String qrFile = "F:/data/incc/qrcode/temp/2018/9/";
        String templateFileName = "F:\\data\\incc\\qrcode\\ICON01.png";
        String outputFileName = "F:/data/incc/certification/";




        try {
            for (int i = 0; i < 3; i++) {

                ZxingSimpleUtils.encodeWithMargin(path + text[i], 900, 910, 0,"png", qrFile+text[i]);
                PngCompositionUtil.compositionFile(new File(qrFile+text[i]), new File(templateFileName), new File(outputFileName+text[i]+".png"));
            }
//			System.out.println(decode(outFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

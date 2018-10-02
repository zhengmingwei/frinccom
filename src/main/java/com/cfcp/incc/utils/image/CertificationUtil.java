package com.cfcp.incc.utils.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;

/**
 * Created by zhyj on 2017/7/21.
 */
public class CertificationUtil {

    public static void compositionFile(File qrCodeFile, File templateFile, File outputFile, String name, String brand, String commpany, String validity, String auditDate){
        try {
            //templateFile = new File("F:/data/incc/certification/template.png");
            BufferedImage buffImg = ImageIO.read(templateFile);

            //得到画笔对象
            Graphics2D g = buffImg.createGraphics();

            //创建你要附加的图象。
            //小图片的路径
//            ImageIcon imgIcon = new ImageIcon(qrCodeFileName);

            BufferedImage img = ImageIO.read(qrCodeFile);

            //得到Image对象。
//            Image img = imgIcon.getImage();

            //将小图片绘到大图片上。
            //5,300 .表示你的小图片在大图片上的位置。
//            g.drawImage(img,1723,34,null);
            g.drawImage(img,554,586,null);

            //设置颜色。
//            g.setColor(Color.BLACK);
//
//            float lineWidth = 3f;
//
//            ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
//
//            g.drawRect(1717, 28, 631,631);

            g.setColor(Color.black);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Font plainFont = new Font("微软雅黑", Font.PLAIN, 16);
            drawString(g, plainFont, name, 180, 378);

            drawString(g, plainFont, commpany, 180, 410);
            drawString(g, plainFont, brand, 180, 442 );
            drawString(g, plainFont, validity, 180, 474);

            Font font =  new Font("微软雅黑", Font.PLAIN, 14);
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString(auditDate, 600, 888);
            g.dispose();





//            drawString(g, plainFont, auditDate, 600, 888);

            ImageIO.write(buffImg, "png", outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void drawString(Graphics2D g,Font plainFont, String name, int x, int y){
        AttributedString as = new AttributedString(name);
        as.addAttribute(TextAttribute.FONT, plainFont);
        as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 0, name.length());
        g.drawString(as.getIterator(), x, y);

    }

    public static void main(String[] args) throws Exception{
        //String path = "http://www.cfcp-incc.org/incc/commodity/";
        String path = "F:/data/incc/qrcode/temp/2018/9/";
        String[] text = {
               /* "201707120748986490",
                "201707120748986491",
                "201707120748986492",
                "201707120748986493",
                "201707120748986494"*/
                "201809260397383203",
                "201809261191945747",
                "201809260219011923"

        };

       /*
        String qrFile = "/Users/zhyj/Documents/商品认证/certification/";
        String templateFileName = "/workspace/certification/template.png";
        String outputFileName = "/Users/zhyj/Documents/商品认证/certification/";
       */

        String qrFile = "F:/data/incc/certification/";
        String templateFileName = "F:/data/incc/certification/template.png";
        String outputFileName = "F:/data/incc/certification/";


       // F:\data\incc\certification

        try {
             for (int i = 0; i <3; i++) {

                ZxingSimpleUtils.encodeWithMargin(path + text[0], 132, 132, 0,"png", qrFile+text[i]);
                CertificationUtil.compositionFile(new File(qrFile+text[i]), new File(templateFileName), new File(outputFileName+text[i]+".png"), "常菁茶"+i,"碧生源"+i,"北京澳特舒尔保健品开发有限公司", "2017-08-30 至 2017-8-30", "2017-08-09");
             }
//			System.out.println(decode(outFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

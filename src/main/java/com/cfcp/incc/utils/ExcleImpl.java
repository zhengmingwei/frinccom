package com.cfcp.incc.utils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import com.cfcp.incc.entity.Role;
import com.cfcp.incc.entity.User;
import com.cfcp.incc.utils.generator.UUIDGenerator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class ExcleImpl {

          //自动设置列宽
           private void getMaxColLength(Map<Integer,Integer> colMaxLength, Integer colIndex, Integer length){
                  Integer oriLength = 0;
                  if(colMaxLength.containsKey(colIndex)){
              oriLength = colMaxLength.get(colIndex);
                      }

                  if(length > oriLength){
               colMaxLength.put(colIndex, length);
                      } else {
               colMaxLength.put(colIndex, oriLength);
                      }
              }

    public void export(String[] titles, ServletOutputStream out, List<User> list,String distributor,String imgPath) throws Exception{
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            //HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            HSSFSheet hssfSheet = workbook.createSheet(distributor);
            //加水印
            //putWaterRemarkToExcel(workbook,hssfSheet,imgPath,0,0,4,7,12,24,1,1);
            hssfSheet.protectSheet(UUIDGenerator.getUuid());//设置Excel只读，或者说是不可编辑

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);

            //获取总行数
           // int rowNum=hssfSheet.getLastRowNum();
            //获取总列数
            //int columnNum=hssfRow.getPhysicalNumberOfCells();


            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            //hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);


            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示

                HSSFCellStyle style = workbook.createCellStyle();
                //关键点 IndexedColors.AQUA.getIndex() 对应颜色
                style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
                hssfCell.setCellStyle(style);
            }

            // 第五步，写入实体数据
         /*   Person person1=new Person("1","张三","123","26");
            Person  person2=new Person("2","李四","123","18");
            Person  person3=new Person("3","王五","123","77");
            Person  person4=new Person("4","徐小筱","123","1");
*/
            //这里我把list当做数据库啦
          /*  ArrayList<Person>  list=new ArrayList<Person>();
            list.add(person1);
            list.add(person2);
            list.add(person3);
            list.add(person4);*/

            for (int i = 0; i < list.size(); i++) {
                row = hssfSheet.createRow(i+1);
                User person = list.get(i);

                // 第六步，创建单元格，并设置值
                String  distributorName = null;
                if(person.getDistributor().getName() != null){
                    distributorName = person.getDistributor().getName();
                }
                row.createCell(0).setCellValue(distributorName);
                String name = "";
                if(person.getName() != null){
                    name = person.getName();
                }
                row.createCell(1).setCellValue(name);
                String mail = "";
                if(person.getPassword() != null){
                    mail = person.getMail();
                }
                row.createCell(2).setCellValue(mail);
                String phone=null;
                if(person.getPhone() !=null){
                    phone = person.getPhone();
                }
                row.createCell(3).setCellValue(phone);
                String roses = "";
                if(person.getRoles()!=null){
                    Set<Role> set = person.getRoles();
                    Iterator<Role> it = set.iterator();
                    while (it.hasNext()) {
                        Role str = it.next();
                        roses+=str.getName()+";";
                    }
                }
                row.createCell(4).setCellValue(roses);
                String createDate = "";
                if(person.getCreateTime()!=null){
                    java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    createDate = format1.format(person.getCreateTime());
                }
                row.createCell(5).setCellValue(createDate);
            }


            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");

        }
    }





      /**
 22      * 为Excel打上水印工具函数
 23      * 请自行确保参数值，以保证水印图片之间不会覆盖。
 24      * 在计算水印的位置的时候，并没有考虑到单元格合并的情况，请注意
 25      * @param wb       Excel Workbook
 26      * @param sheet    需要打水印的Excel
 27      * @param waterRemarkPath  水印地址，classPath，目前只支持png格式的图片，
 28      * 因为非png格式的图片打到Excel上后可能会有图片变红的问题，且不容易做出透明效果。
 29      * 同时请注意传入的地址格式，应该为类似："\\excelTemplate\\test.png"
 30      * @param startXCol  水印起始列
 31      * @param startYRow  水印起始行
 32      * @param betweenXCol 水印横向之间间隔多少列
 33      * @param betweenYRow 水印纵向之间间隔多少行
 34      * @param XCount 横向共有水印多少个
 35      * @param YCount 纵向共有水印多少个
 36      * @param waterRemarkWidth 水印图片宽度为多少列
 37      * @param waterRemarkHeight 水印图片高度为多少行
 38      * @throws IOException
 39      */
              public static void putWaterRemarkToExcel(Workbook wb, Sheet
            sheet, String waterRemarkPath, int startXCol, int startYRow,
  int betweenXCol, int betweenYRow, int XCount, int YCount,
  int waterRemarkWidth, int waterRemarkHeight) throws IOException {

                  //校验传入的水印图片格式
                  if(!waterRemarkPath.endsWith("png") &&
                !waterRemarkPath.endsWith("PNG")){
              throw new RuntimeException("向Excel上面打印水印，目前支持png格式的图片。");
                      }

                  //加载图片
                  ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                  InputStream imageIn =  Thread.currentThread().getContextClassLoader().getResourceAsStream(waterRemarkPath);

                  if(null == imageIn || imageIn.available() < 1){
                      imageIn = new FileInputStream(waterRemarkPath);//转换为有的PNG图片时也会变色
                  }
                  if(null == imageIn || imageIn.available() < 1){
              throw new RuntimeException("向Excel上面打印水印，读取水印图片失败(1)。");
                      }
                  BufferedImage bufferImg = ImageIO.read(imageIn);
                  if(null == bufferImg) {
              throw new RuntimeException("向Excel上面打印水印，读取水印图片失败(2)。");
                      }
                  ImageIO.write(bufferImg,"png",byteArrayOut);

                  //开始打水印
                  Drawing drawing = sheet.createDrawingPatriarch();

                  //按照共需打印多少行水印进行循环
                  for(int yCount=0; yCount<YCount; yCount++){
              //按照每行需要打印多少个水印进行循环
              for(int xCount=0; xCount<XCount; xCount++){
                  //创建水印图片位置
                  int xIndexInteger = startXCol + (xCount * waterRemarkWidth) + (xCount
                        * betweenXCol);
                  int yIndexInteger = startYRow + (yCount * waterRemarkHeight) +
                        (yCount * betweenYRow);

                  /*
                    73  * 参数定义：
                    74  * 第一个参数是（x轴的开始节点）；
                    75  * 第二个参数是（是y轴的开始节点）；
                    76  * 第三个参数是（是x轴的结束节点）；
                    77  * 第四个参数是（是y轴的结束节点）；
                    78  * 第五个参数是（是从Excel的第几列开始插入图片，从0开始计数）；
                    79  * 第六个参数是（是从excel的第几行开始插入图片，从0开始计数）；
                    80  * 第七个参数是（图片宽度，共多少列）；
                    81  * 第8个参数是（图片高度，共多少行）；
                    82 */
                /*  ClientAnchor anchor = drawing.createAnchor(0, 0, Short.MAX_VALUE,
                        Integer.MAX_VALUE, xIndexInteger, yIndexInteger, waterRemarkWidth,
                        waterRemarkHeight);*/
                  ClientAnchor anchor = drawing.createAnchor(0, 0, 1000,
                          254, xIndexInteger, yIndexInteger, waterRemarkWidth,
                          waterRemarkHeight);
                  Picture pic = drawing.createPicture(anchor,
                        wb.addPicture(byteArrayOut.toByteArray(), Workbook.PICTURE_TYPE_PNG));
                   pic.resize();

                  imageIn.close();

              }
                      }
              }

}

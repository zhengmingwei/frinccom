package com.cfcp.incc.utils;

import java.io.File;
import java.util.Calendar;

/**
 * Created by zhyj on 2017/8/4.
 */
public class FileUtils {

    static public String createFile(String rootDir){
        Calendar date = Calendar.getInstance();
        File file = new File(rootDir + File.separator + date.get(Calendar.YEAR)
                + File.separator + (date.get(Calendar.MONTH)+1) + File.separator);
        System.out.println(file.getAbsolutePath());
        if(!file.exists()){//目录不存在则直接创建
            file.mkdirs();
        }
        return file.getAbsolutePath()+File.separator;
    }

    static public String getFile(String rootDir, String fileName){
        String month = fileName.substring(4,6);
        File file = new File(rootDir + File.separator + fileName.substring(0, 4) + File.separator + (month.startsWith("0")?month.substring(1):month));
        return file.getAbsolutePath() + File.separator;
    }

    public static void main(String[] args){
        System.out.println(getFile("/workspace/" , "201707240840301412"));
    }
}

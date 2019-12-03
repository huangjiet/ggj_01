package com.dfbz.utils;
import com.alibaba.excel.util.FileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author hjt
 * @description
 * @date 2019/11/23
 */
public class FileUtil {

    public static void download(String fname,HttpServletResponse response){


        //获取文件
        File file = new File(fname);
        //将文件转成一个byte[]字节流
        byte[] array= null;

        try {
            array = FileUtils.readFileToByteArray(file);


            //设置输出格式    默认不支持中文,new String(fname.getBytes(),"ISO-8859-1"),转义中文编码
            response.addHeader("Content-Disposition","attachment;filename="+new String(fname.getBytes(),"ISO-8859-1"));

            response.getOutputStream().write(array);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

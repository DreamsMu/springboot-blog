package com.mhx.blog.util;

import com.mhx.blog.commons.FileConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {


    public String uploadImg(MultipartFile multipartFile,String filePath){
        String realpath="";
        //获取文件名
        String name="";
        long size = multipartFile.getSize();
        if (size > 5242880) {//文件设置大小，我这里设置5M。
            System.err.println("文件大于5M");
        }
        name = multipartFile.getOriginalFilename();//直接返回文件的名字
        String subffix = name.substring(name.lastIndexOf(".") + 1, name.length());//我这里取得文件后缀
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//文件保存进来，我给他重新命名，数据库保存有原本的名字，所以输出的时候再把他附上原本的名字就行了。
        realpath = filePath + "/" + fileName + "." + subffix;
        try {
            multipartFile.transferTo(new File(realpath));//保存文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realpath;
    }
}

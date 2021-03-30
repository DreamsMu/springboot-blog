package com.mhx.blog.Controller;

import com.mhx.blog.Service.ArticleService;
import com.mhx.blog.Service.RootService;
import com.mhx.blog.commons.FileConfigProperties;
import com.mhx.blog.domain.Root;
import com.mhx.blog.util.FileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminContorller {

    @Autowired
    private FileConfigProperties fileConfigProperties;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RootService rootService;


    @PostMapping("updateAdmin")
    public Map updateAdmin(Root root){
        HashMap<Object, Object> map = new HashMap<>();
        try{
            boolean b = articleService.updateAdmin(root);
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            if (b){
                session.setAttribute("root",root);
                map.put("code","200");
            }else{
                map.put("code","100");
            }

        }catch (Exception e) {
            e.getStackTrace();
            map.put("code","100");
        }
        return map;
    }

    @PostMapping("uploadAdminImg")
    public Map uploadAdminImg(@RequestParam( value="file",required=false) MultipartFile multipartFile){
        FileUtil fileUtil = new FileUtil();
        HashMap<Object, Object> map = new HashMap<>();
        try{
            Session session = SecurityUtils.getSubject().getSession();
            if(multipartFile!=null) {
                String img = fileUtil.uploadImg(multipartFile, fileConfigProperties.getAdminImgPath());
                rootService.updateAdminImg(img);
                Root root = articleService.selectRootById(1);
                session.setAttribute("root",root);
                map.put("realpath",img);
            }
            map.put("code","200");
        }catch (Exception e){
            e.getStackTrace();
            map.put("code","100");
        }
        return map;
    }

    @PostMapping("deleteAdminOldImg")
    public void deleteOldImg(@RequestBody String img){
        String locationPath = fileConfigProperties.getLocationPath();
        try{
            File file = new File(locationPath + "/" + img);
            if (file.delete()){
                System.out.println("Admin旧文件删除成功");
            }else{
                System.err.println("Admin旧文件删除失败");
            }
        }catch (Exception e){
            System.err.println("Admin删除过程异常");
            e.getStackTrace();
        }
    }
}

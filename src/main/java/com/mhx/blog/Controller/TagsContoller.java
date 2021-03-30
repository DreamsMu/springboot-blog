package com.mhx.blog.Controller;

import com.mhx.blog.Service.ArticleService;
import com.mhx.blog.commons.FileConfigProperties;
import com.mhx.blog.domain.Tags;
import com.mhx.blog.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TagsContoller {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private FileConfigProperties fileConfigProperties;


    @GetMapping("getTagsById")
    public Tags getTagsById(Integer id){
        Tags tags = articleService.getTagsById(id);
        return tags;
    }

    @PostMapping("uploadTagsImg")
    public Map uploadFile(@RequestParam( value="file",required=false) MultipartFile multipartFile){
        FileUtil fileUtil = new FileUtil();
        HashMap<Object, Object> map = new HashMap<>();
        System.out.println(multipartFile);
        try {
            if(multipartFile!=null) {
                map.put("realpath", fileUtil.uploadImg(multipartFile, fileConfigProperties.getTagsImgPath()));
            }
            map.put("code","200");
        }catch (Exception e){
            map.put("code","100");
            e.getStackTrace();
        }
        return map;
    }

    @PutMapping("updateTags")
    public Map updateTags(@RequestBody Tags tags){
        HashMap<Object, Object> map = new HashMap<>();
        try {
            boolean b = articleService.updateTagsById(tags);
            if (b){
                map.put("code","200");
            }else{
                map.put("code","100");
            }
        }catch (Exception e){
            map.put("code","100");
            e.getStackTrace();
        }
        return map;
    }

    @PostMapping("updateTagsImg")
    public void updateTagsImg(@RequestBody Tags tags){
        System.out.println(tags.toString());
        try{
            articleService.updateTagsImg(tags);
        }catch (Exception e){
            System.err.println("更改标签图片过程中出现异常");
            e.getStackTrace();
        }
    }

    @PostMapping("deleteTagsOldImg")
    public void deleteOldImg(@RequestBody String img){
        String locationPath = fileConfigProperties.getLocationPath();
        try{
            File file = new File(locationPath + "/" + img);
            if (file.delete()){
                System.out.println("Tags旧文件删除成功");
            }else{
                System.err.println("Tags旧文件删除失败");
            }
        }catch (Exception e){
            System.err.println("Tags删除过程异常");
            e.getStackTrace();
        }
    }
}

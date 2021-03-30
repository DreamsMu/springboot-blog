package com.mhx.blog.Controller;

import com.github.pagehelper.PageInfo;
import com.mhx.blog.Service.ArticleService;
import com.mhx.blog.commons.FileConfigProperties;
import com.mhx.blog.domain.Article;
import com.mhx.blog.domain.Category;
import com.mhx.blog.domain.Tags;
import com.mhx.blog.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private FileConfigProperties fileConfigProperties;

    @GetMapping("adminArticleById")
    public Article goArticleById(Integer id){
        Article articleById = articleService.getArticleByIdAdmin(id);
        return articleById;
    }

    @PostMapping("updateArticleById/{id}")
    public Map updateArticleById( @PathVariable("id") Integer id, @RequestBody Article article){
        HashMap<Object, Object> map = new HashMap<>();
        try {
            articleService.updateArticleById(id,article);
            map.put("code","200");
        }catch (Exception e){
            System.err.println(e);
            map.put("code","100");
        }
        return map;
    }

    @PostMapping("addArticle")
    public Map addArticleById(@RequestBody Article article){
        HashMap<Object, Object> map = new HashMap<>();
        try {
            articleService.addArticle(article);
            map.put("code","200");
        }catch (Exception e){
            System.err.println(e);
            map.put("code","100");
        }
        return map;
    }

    @PostMapping("saveContent")
    public Map updateArticleContentById(@RequestBody Article article){
        HashMap<Object, Object> map = new HashMap<>();
        try {
            articleService.updateArticleContentById(article);
            map.put("code","200");
        }catch (Exception e){
            System.err.println(e);
            map.put("code","100");
        }
        return map;
    }

    @GetMapping("deleteContent")
    public Map deleteArticleById(Integer id){
        HashMap<Object, Object> map = new HashMap<>();
        try {
            articleService.deleteArticleById(id);
            map.put("code","200");
        }catch (Exception e){
            System.err.println(e);
            map.put("code","100");
        }
        return map;
    }

    @PostMapping("uploadArticleImg")
    public Map uploadFile(@RequestParam( value="file",required=false) MultipartFile multipartFile){
        FileUtil fileUtil = new FileUtil();
        HashMap<Object, Object> map = new HashMap<>();
        if(multipartFile!=null) {
            map.put("realpath",fileUtil.uploadImg(multipartFile,fileConfigProperties.getArticleImgPath()));
            map.put("code","200");
        }else {
            map.put("code","100");
        }
        return map;
    }
}

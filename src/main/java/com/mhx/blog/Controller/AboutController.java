package com.mhx.blog.Controller;

import com.mhx.blog.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AboutController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("updateAbout")
    public Map updateAbout(@RequestBody String content){
        HashMap<Object, Object> map = new HashMap<>();
        try{
            boolean b = articleService.updateAbout(content);
            if (b){
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
}

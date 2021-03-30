package com.mhx.blog;


import com.mhx.blog.Service.ArticleService;
import com.mhx.blog.Service.RootService;
import com.mhx.blog.dao.ArticleMapper;
import com.mhx.blog.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private RootService rootService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    void contextLoads(){
        System.out.println(articleMapper.selectRootByName("admin").toString());
    }
}

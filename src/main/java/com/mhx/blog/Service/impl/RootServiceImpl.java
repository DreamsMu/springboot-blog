package com.mhx.blog.Service.impl;

import com.mhx.blog.Service.RootService;
import com.mhx.blog.dao.ArticleMapper;
import com.mhx.blog.domain.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootServiceImpl implements RootService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Root createRoot(){
        return new Root(
                "蛙蛙wawaya","74453595","123456","个人介绍","images/img2.png");
    }

    @Override
    public Root getRoot(){
        return createRoot();
    }

    @Override
    public boolean updateAdminImg(String img) {
        return articleMapper.updateAdminImg(new Root(1,img));
    }

    @Override
    public Root selectRootByName(String username) {
        return articleMapper.selectRootByName(username);
    }
}

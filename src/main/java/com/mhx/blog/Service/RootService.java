package com.mhx.blog.Service;

import com.mhx.blog.domain.Article;
import com.mhx.blog.domain.Root;

public interface RootService {
    /**
     * 创建管理员
     * @return
     */
    public Root createRoot();

    /**
     * 获取管理员信息
     * @return
     */
    public Root getRoot();

    boolean updateAdminImg(String img);

    Root selectRootByName(String username);
}

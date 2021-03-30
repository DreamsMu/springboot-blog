package com.mhx.blog.Service;

import com.github.pagehelper.PageInfo;
import com.mhx.blog.domain.Article;
import com.mhx.blog.domain.Category;
import com.mhx.blog.domain.Root;
import com.mhx.blog.domain.Tags;

import java.util.List;

public interface ArticleService {
    /**
     * 查询全部文章
     * @return
     */
    public List<Article> getArticle();

    public List<Article> getArticleDesc();
    /**
     * 根据标签查询文章
     * @return
     */
    public List<Article> getArticleByTags(Integer id);
    /**
     * 根据分类获取文章
     * @return
     */
    public List<Article> getArticleByCategory(Integer id);
    /**
     * 通过id查询文章
     * @return
     */
    public Article getArticleById(Integer id);
    /**
     * 获取上一页文章
     * @return
     */
    public Article getUpArticle(Integer id);
    /**
     * 获取下一页文章
     * @return
     */
    public Article getDownArticle(Integer id);
    /**
     * 获取分类列表
     * @return
     */
    public List<Category> getCategory();
    /**
     * 获取全部标签
     * @return
     */
    public List<Tags> getTags();
    /**
     * 获取文章总数
     * @return
     */
    public Integer getArticleCount();
    /**
     * 获取标签总数
     * @return
     */
    public Integer getTagsCount();
    /**
     * 获取分类数量
     * @return
     */
    public Integer getCategoryCount();
    /**
     * 更新访问次数
     * @return
     */
    public void setTraffic(Integer id, Integer traffic);
    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    public PageInfo<Article> getArticleByPage(Integer page, Integer rows);
    /**
     * 分类分页查询
     * @param page
     * @param rows
     * @return
     */
    public PageInfo<Article> getArticleByCategoryByPage(Integer id,Integer page, Integer rows);
    /**
     * 标签页文章分页查询
     * @param page
     * @param rows
     * @return
     */
    public PageInfo<Article> getArticleByTagsByPage(Integer id,Integer page, Integer rows);

    public Tags getTagsById(Integer id);
    /**
     * 根据id更新文章
     * @param id
     * @param article
     * @return
     */
    public boolean updateArticleById(Integer id, Article article);

    public boolean updateArticleContentById(Article article);

    boolean deleteArticleById(Integer id);

    List<Tags> getTagsList();

    Article getArticleByIdAdmin(Integer id);
    /**
     * 新建文章
     * @return 返回结果boolean
     * @param article
     */
    boolean addArticle(Article article);

    PageInfo<Article> selectLikeArticleByTitle(Integer page, Integer rows, String title);

    boolean updateTagsById(Tags tags);

    boolean updateAbout(String content);

    String selectAbout();

    Root selectRootById(Integer id);

    boolean updateAdmin(Root root);

    boolean updateTagsImg(Tags tags);

}

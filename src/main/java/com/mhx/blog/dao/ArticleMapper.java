package com.mhx.blog.dao;

import com.mhx.blog.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {
    /**
     * 查询全部文章
     * @return
     */
    List<Article> getArticle();

    List<Article> getArticleDesc();
    /**
     * 根据标签查询文章
     * @return
     */
    List<Article> getArticleByTags(Integer id);
    /**
     * 根据分类获取文章
     * @return
     */
    List<Article> getArticleByCategory(Integer id);

    List<Integer> getArticleByCategoryNew(Integer id);

    /**
     * 通过id查询文章
     * @return
     */
    Article getArticleById(Integer id);
    /**
     * 获取上一页文章
     * @return
     */
    Article getUpArticle(Integer id);
    /**
     * 获取下一页文章
     * @return
     */
    Article getDownArticle(Integer id);
    /**
     * 获取分类列表
     * @return
     */
    List<Category> getCategory();

    /**
     * 获取全部标签
     * @return
     */
    List<Tags> getTags();

    List<Tags> getTagsList();
    /**
     * 获取文章总数
     * @return
     */
    Integer getArticleCount();
    /**
     * 获取标签总数
     * @return
     */
    Integer getTagsCount();
    /**
     * 获取分类数量
     * @return
     */
    Integer getCategoryCount();
    /**
     * 更新访问次数
     * @return
     */
    void setTraffic(Article article);

    Tags getTagsById(Integer id);

    boolean updateArticleById(Article article);

    boolean insertCategory(Category category);

    Integer selectCategoryIdByName(String name);

    boolean insertTags(Tags tags);

    Integer selectTagsIdByName(String name);

    boolean updateArticleTagsMap(ArticleTagsMap articleTagsMap);

    boolean insertArticleTagsMap(ArticleTagsMap articleTagsMap);

    Integer selectArticleTagsMap(Integer aid);

    Category selectCategroyById(Integer id);

    boolean updateArticleContentById(Article article);

    boolean deleteArticleById(Integer id);

    boolean deleteCategoryById(Integer id);

    boolean deleteTagsById(Integer id);

    boolean deleteArticleTagsMapById(Integer aid);

    List<ArticleTagsMap> selectArticleTagsMapAll();

    boolean insertArticle(Article article);

    Article selectArticleByName(String title);

    List<Article> selectLikeArticleByTitle(String title);

    boolean updateTagsById(Tags tags);

    boolean updateAbout(String content);

    String selectAbout();

    Root selectRootById(Integer id);

    boolean updateAdmin(Root root);

    boolean updateAdminImg(Root root);

    boolean updateTagsImg(Tags tags);

    Root selectRootByName(String username);
}

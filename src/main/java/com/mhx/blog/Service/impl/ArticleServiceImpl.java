package com.mhx.blog.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mhx.blog.Service.ArticleService;
import com.mhx.blog.dao.ArticleMapper;
import com.mhx.blog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    public ArticleMapper articleMapper;

    @Override
    public List<Article> getArticle() {
        return articleMapper.getArticle();
    }

    @Override
    public List<Article> getArticleDesc() {
        return articleMapper.getArticleDesc();
    }

    @Override
    public List<Article> getArticleByTags(Integer id) {
        return articleMapper.getArticleByTags(id);
    }

    @Override
    public List<Article> getArticleByCategory(Integer id) {
        return articleMapper.getArticleByCategory(id);
    }

    @Override
    public Article getArticleById(Integer id) {
        Article article = articleMapper.getArticleById(id);
        setTraffic(article.getId(),article.getTraffic()+1);
        return article;
    }

    @Override
    public Article getArticleByIdAdmin(Integer id) {
        Article article = articleMapper.getArticleById(id);
        return article;
    }

    @Override
    public Article getUpArticle(Integer id) {
        Article upArticle = articleMapper.getUpArticle(id);
        if (upArticle == null) {
            return new Article(-1);
        }
        return upArticle;
    }

    @Override
    public Article getDownArticle(Integer id) {
        Article downArticle = articleMapper.getDownArticle(id);
        if (downArticle == null) {
            return new Article(-2);
        }
        return downArticle;
    }

    @Override
    public List<Category> getCategory() {
        return articleMapper.getCategory();
    }

    @Override
    public List<Tags> getTags() {
        return articleMapper.getTags();
    }

    @Override
    public List<Tags> getTagsList() {
        return articleMapper.getTagsList();
    }

    @Override
    public Integer getArticleCount() {
        return articleMapper.getArticleCount();
    }

    @Override
    public Integer getTagsCount() {
        return articleMapper.getTagsCount();
    }

    @Override
    public Integer getCategoryCount() {
        return articleMapper.getCategoryCount();
    }

    @Override
    public void setTraffic(Integer id,Integer traffic) {
        Article article = new Article(id, traffic);
        articleMapper.setTraffic(article);
    }

    @Override
    public PageInfo<Article> getArticleByPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Article> article = getArticle();
        PageInfo<Article> pageInfo = new PageInfo<>(article);
        return pageInfo;
    }

    @Override
    public PageInfo<Article> getArticleByCategoryByPage(Integer id,Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Article> article = getArticleByCategory(id);
        PageInfo<Article> pageInfo = new PageInfo<>(article);
        return pageInfo;
    }

    @Override
    public PageInfo<Article> getArticleByTagsByPage(Integer id,Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Article> article = getArticleByTags(id);
        PageInfo<Article> pageInfo = new PageInfo<>(article);
        return pageInfo;
    }

    @Override
    public Tags getTagsById(Integer id) {
        return articleMapper.getTagsById(id);
    }

    @Override
    public boolean updateArticleById(Integer id,Article article1) {
        article1.setId(id);
        //????????????
        Article article = categoryEdit(article1);
        //????????????
        tagsEdit(id, article);
        //??????????????????
        deleteTagsRedundant();
        Article articleById = articleMapper.getArticleById(id);
        //????????????
        boolean b = articleMapper.updateArticleById(article);
        //????????????cid??????????????????cid?????????
        List<Integer> articleId = articleMapper.getArticleByCategoryNew(articleById.getCid());
        if (articleId.size() == 0) {
            articleMapper.deleteCategoryById(articleById.getCid());
        }
        return b;
    }
    @Override
    public boolean addArticle(Article article) {
        //1???????????????
        Article article1 = categoryEdit(article);
        //2???????????????
        boolean b = articleMapper.insertArticle(article1);
        //3????????????????????????
        Article article2 = articleMapper.selectArticleByName(article1.getTitle());
        //4???????????????
        tagsEdit(article2.getId(),article1);
        return b;
    }
    @Override
    public boolean updateArticleContentById(Article article) {
        return articleMapper.updateArticleContentById(article);
    }
    @Override
    public boolean deleteArticleById(Integer id) {
        Article article = articleMapper.getArticleById(id);
        boolean b = articleMapper.deleteArticleById(id);
        List<Integer> articleId = articleMapper.getArticleByCategoryNew(article.getCid());
        if (articleId.size() == 0) {
            articleMapper.deleteCategoryById(article.getCid());
        }
        articleMapper.deleteArticleTagsMapById(id);

        deleteTagsRedundant();

        return b;
    }
    @Override
    public PageInfo<Article> selectLikeArticleByTitle(Integer page, Integer rows, String title){
        PageHelper.startPage(page, rows);
        List<Article> article = articleMapper.selectLikeArticleByTitle(title);
        PageInfo<Article> pageInfo = new PageInfo<>(article);
        return pageInfo;
    }

    @Override
    public boolean updateTagsById(Tags tags) {
        return articleMapper.updateTagsById(tags);
    }

    @Override
    public boolean updateAbout(String content) {
        return articleMapper.updateAbout(content);
    }

    @Override
    public String selectAbout() {
        return articleMapper.selectAbout();
    }

    @Override
    public Root selectRootById(Integer id) {
        return articleMapper.selectRootById(id);
    }

    @Override
    public boolean updateAdmin(Root root) {
        return articleMapper.updateAdmin(root);
    }

    @Override
    public boolean updateTagsImg(Tags tags) {
        return articleMapper.updateTagsImg(tags);
    }



    /**
     * ???????????????????????????cid????????????????????????????????????
     * @param aid
     * @param tid
     */
    private void isCidAtMaps(Integer aid, Integer tid){
        Integer temp = articleMapper.selectArticleTagsMap(aid);
        if (temp == null) {
            articleMapper.insertArticleTagsMap(new ArticleTagsMap(aid, tid));
        }else {
            articleMapper.updateArticleTagsMap(new ArticleTagsMap(aid,tid));
        }
    }
    /**
     * ????????????
     * @param article
     * @return
     */
    private Article categoryEdit(Article article){
        Integer temp = 0;
        List<Category> category = articleMapper.getCategory();
        for (Category cg : category) {
            if (cg.getName().equals(article.getCategory().getName())) {
                temp = 1;
                article.setCategory(new Category(cg.getId(),article.getCategory().getName()));
                break;
            }
        }
        if (!temp.equals(1)){
            articleMapper.insertCategory(new Category(article.getCategory().getName()));
            Integer idByName = articleMapper.selectCategoryIdByName(article.getCategory().getName());
            article.setCategory(new Category(idByName,article.getCategory().getName()));
        }
        return article;
    }
    /**
     * ????????????
     * @param id
     * @param article
     */
    private void tagsEdit(Integer id, Article article){
        //????????????
        /* ?????????????????????
         * 1?????????????????????
         * 2???????????????????????????????????????????????????????????????????????????
         *   2.1???????????????
         *       3.1.1 ???????????????????????????????????????????????????????????????
         *       3.1.2 ??????????????????????????????????????????????????????????????????
         *   2.2??????????????????????????????????????????
         *       2.2.1 ????????????????????????????????????????????????
         * */
        Integer temp2 = 0;
        List<Tags> tags = articleMapper.getTagsList();
        for (Tags tg : tags) {
            if (tg.getName().equals(article.getTags().getName())){
                temp2 = 1;
                isCidAtMaps(id, tg.getId());
                break;
            }
        }
        if (!temp2.equals(1)) {
            articleMapper.insertTags(new Tags(article.getTags().getName()));
            Integer idByName = articleMapper.selectTagsIdByName(article.getTags().getName());
            isCidAtMaps(id, idByName);
        }
    }
    /**
     * ??????????????????:??????????????????
     */
    private void deleteTagsRedundant(){
        /* ?????????????????????
         * 1????????????????????????id
         * 2??????????????????????????????tid
         * 3???id???tid?????????????????????tid?????????id?????????id??????
         * */
        //?????????????????????
        List<Tags> tags = articleMapper.getTagsList();
        List<ArticleTagsMap> articleTagsMaps = articleMapper.selectArticleTagsMapAll();
        //??????????????????????????????
        for (Tags t : tags){
            Integer i = 0;
            for (ArticleTagsMap atm : articleTagsMaps) {
                if (t.getId() == atm.getTid()){
                    i = 1;
                }
            }
            if (i == 0) {
                articleMapper.deleteTagsById(t.getId());
            }
        }
    }
}

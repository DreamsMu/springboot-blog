package com.mhx.blog.Controller;

import com.github.pagehelper.PageInfo;
import com.mhx.blog.Service.ArticleService;
import com.mhx.blog.Service.RootService;
import com.mhx.blog.domain.Article;
import com.mhx.blog.domain.Category;
import com.mhx.blog.domain.Root;
import com.mhx.blog.domain.Tags;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private RootService rootService;
    @Autowired
    private ArticleService articleService;

    @GetMapping({"/","index"})
    public String goHome(Model model){
        Root root = articleService.selectRootById(1);
        PageInfo<Article> article = articleService.getArticleByPage(1, 4);
        List<Category> category = articleService.getCategory();
        model.addAttribute("category",category);
        model.addAttribute("count",getCount());
        model.addAttribute("root",root);
        model.addAttribute("article",article);
        return "views/home";
    }

    @GetMapping({"home"})
    public String goHomeByPage(Integer pageNum,Model model){
        Root root = articleService.selectRootById(1);
        List<Category> category = articleService.getCategory();
        model.addAttribute("category",category);
        PageInfo<Article> article = articleService.getArticleByPage(pageNum, 4);
        model.addAttribute("count",getCount());
        model.addAttribute("root",root);
        model.addAttribute("article",article);
        return "views/home";
    }

    @GetMapping("article")
    public String goArticle(Integer id,Model model){
        List<Category> category = articleService.getCategory();
        model.addAttribute("category",category);
        Root root = articleService.selectRootById(1);
        Article article = articleService.getArticleById(id);
        Article upArticle = articleService.getUpArticle(id);
        Article downArticle = articleService.getDownArticle(id);
        model.addAttribute("count",getCount());
        model.addAttribute("root",root);
        model.addAttribute("article",article);
        model.addAttribute("upArticle",upArticle);
        model.addAttribute("downArticle",downArticle);
        return "views/article";
    }

    @GetMapping("tags")
    public String goTags(Model model){
        List<Category> category = articleService.getCategory();
        model.addAttribute("category",category);
        Root root = articleService.selectRootById(1);
        List<Tags> tags = articleService.getTags();
        model.addAttribute("root",root);
        model.addAttribute("count",getCount());
        model.addAttribute("tags",tags);
        return "views/tags";
    }

    @GetMapping("tagsdetail")
    public String goTagsdetail(Integer id, Integer page, Model model){
        Root root = articleService.selectRootById(1);
        Tags tags = articleService.getTagsById(id);
        List<Category> category = articleService.getCategory();
        model.addAttribute("category",category);
        PageInfo<Article> article = articleService.getArticleByTagsByPage(id, page, 3);
        model.addAttribute("root",root);
        model.addAttribute("count",getCount());
        model.addAttribute("article",article);
        model.addAttribute("tags",tags);
        return "views/tagsdetail";
    }

    @GetMapping("categoriesPage")
    public String goCategoriesByPage(Integer id,Integer page, Model model){
        Root root = articleService.selectRootById(1);
        List<Category> category = articleService.getCategory();
        PageInfo<Article> article = articleService.getArticleByCategoryByPage(id,page, 3);
        model.addAttribute("root",root);
        model.addAttribute("category",category);
        model.addAttribute("article",article);
        model.addAttribute("count",getCount());
        return "views/categories";
    }

    @GetMapping("timeline")
    public String goTimeline(Model model){
        Root root = articleService.selectRootById(1);
        List<Article> article = articleService.getArticleDesc();
        List<Category> category = articleService.getCategory();
        model.addAttribute("category",category);
        model.addAttribute("root",root);
        model.addAttribute("count",getCount());
        model.addAttribute("article",article);
        return "views/timeline";
    }

    @GetMapping("team")
    public String goTeam(Model model){
        Root root = articleService.selectRootById(1);
        List<Category> category = articleService.getCategory();
        model.addAttribute("category",category);
        model.addAttribute("root",root);
        model.addAttribute("count",getCount());
        return "views/team";
    }

    @GetMapping("about")
    public String goAbout(Model model){
        Root root = articleService.selectRootById(1);
        String about = articleService.selectAbout();
        List<Category> category = articleService.getCategory();
        model.addAttribute("category",category);
        model.addAttribute("root",root);
        model.addAttribute("count",getCount());
        model.addAttribute("about",about);
        return "views/about";
    }

    @GetMapping("adminIndex")
    public String goIndex(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        model.addAttribute("session",session);
        model.addAttribute("articleCount",articleService.getArticleCount());
        model.addAttribute("tagsCount",articleService.getTagsCount());
        model.addAttribute("categoryCount",articleService.getCategoryCount());
        return "admin/index";
    }

    @GetMapping("adminAdmin")
    public String goAdmin(Integer id, Model model){
        Session session = SecurityUtils.getSubject().getSession();
        model.addAttribute("session",session);
        Root root = articleService.selectRootById(id);
        model.addAttribute("root",root);
        return "admin/admin";
    }

    @GetMapping("adminArticle")
    public String goAdminArticle(Integer id, Model model){
        Root root = articleService.selectRootById(id);
        model.addAttribute("root",root);
        PageInfo<Article> article = articleService.getArticleByPage(id, 10);
        model.addAttribute("article",article);
        List<Tags> tags = articleService.getTagsList();
        model.addAttribute("tags",tags);
        List<Category> category = articleService.getCategory();
        model.addAttribute("category",category);
        model.addAttribute("con_href","adminArticle");
        return "admin/article";
    }

    @GetMapping("adminArticleSearch")
    public String goArticleSearch(String title, Integer id, Model model){
        PageInfo<Article> article = articleService.selectLikeArticleByTitle(id,10,title);
        model.addAttribute("article",article);
        List<Tags> tags = articleService.getTagsList();
        model.addAttribute("tags",tags);
        List<Category> category = articleService.getCategory();
        model.addAttribute("category",category);
        model.addAttribute("con_href","adminArticleSearch");
        model.addAttribute("con_title",title);
        return "admin/article";
    }

    @GetMapping("adminArticle_edit")
    public String goArticleEdit(Integer id, Model model){
        Article article = articleService.getArticleById(id);
        model.addAttribute("article",article);
        return "admin/article_edit";
    }

    @GetMapping("adminTags")
    public String goAdminTags(Model model){
        List<Tags> tags = articleService.getTags();
        model.addAttribute("tags",tags);
        return "admin/tags";
    }

    @GetMapping("adminAbout")
    public String goAdminAbout(Model model){
        String about = articleService.selectAbout();
        model.addAttribute("about",about);
        return "admin/about";
    }

    @GetMapping("login")
    public String goLogin(){
        return "admin/login";
    }

    @RequestMapping("logout")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "admin/login";
    }

    public Map getCount(){
        Map map = new HashMap<>();
        Integer articleCount = articleService.getArticleCount();
        Integer categoryCount = articleService.getCategoryCount();
        Integer tagsCount = articleService.getTagsCount();
        map.put("articleCount",articleCount);
        map.put("categoryCount",categoryCount);
        map.put("tagsCount",tagsCount);
        return map;
    }
}

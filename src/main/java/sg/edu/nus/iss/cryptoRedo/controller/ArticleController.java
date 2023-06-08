package sg.edu.nus.iss.cryptoRedo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import sg.edu.nus.iss.cryptoRedo.model.Article;
import sg.edu.nus.iss.cryptoRedo.model.ArticleArray;
import sg.edu.nus.iss.cryptoRedo.service.ArticleService;

@Controller
public class ArticleController {
    @Autowired
    ArticleService aSvc;

    @GetMapping(path={"/"} ,produces = {"text/html"})
    //produces = MediaType.TEXT_HTML_VALUE
    //consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
   
    public String getLandingPage(Model m){
        
        ArticleArray aa= new ArticleArray();
        aa=aSvc.getArticleList();
        
        m.addAttribute("articleList", aa);
       

        return "view1";

        
    }

   @PostMapping(path="/articles")
   public String getSavedArticle(@RequestParam(value="selectedArticle") List<String> idList,Model m){
    
    //get all the articles again
    ArticleArray articleList= new ArticleArray();
    articleList=aSvc.getArticleList();

    List<Article> save= new ArrayList<>();

    Iterator<Article> iterator = (articleList.getArticleList()).iterator();

    while(iterator.hasNext()){
        Article article = iterator.next();
        if(idList.contains(article.getId())){
            save.add(article);
        }
    }
    articleList.setArticleList(save);
    aSvc.saveArticles(articleList);

    m.addAttribute("articleList",articleList);
    

    return "view2";

   }


    
}

package sg.edu.nus.iss.cryptoRedo.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.cryptoRedo.model.Article;
import sg.edu.nus.iss.cryptoRedo.model.ArticleArray;

@Repository
public class ArticleRepository {

    @Autowired
    RedisTemplate<String,Object> template;

    public void saveArticles(ArticleArray a){
        for(Article art:a.getArticleList()){
        template.opsForHash().put("Save Articles",art.getId(),art);
    }
     }

     public Optional<Article> getArticlebyId(String id){
        Article a= (Article)template.opsForHash().get("Save Articles",id);

        if(a==null){
            return Optional.empty();
        }

        return Optional.of(a);
     }

     

    
    
}

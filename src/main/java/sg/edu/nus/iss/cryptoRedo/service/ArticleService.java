package sg.edu.nus.iss.cryptoRedo.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

import sg.edu.nus.iss.cryptoRedo.model.Article;
import sg.edu.nus.iss.cryptoRedo.model.ArticleArray;
import sg.edu.nus.iss.cryptoRedo.repository.ArticleRepository;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository aRepo;

    public ArticleArray getArticleList(){

    String url="https://min-api.cryptocompare.com/data/v2/news/?lang=EN";

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> response = template.getForEntity(url,String.class);

    ArticleArray aa = ArticleArray.create(response.getBody());

    return aa;
    }
    public void saveArticles(ArticleArray a){
        aRepo.saveArticles(a);
    }

    // public List<ObjectError> validateSelection(List<String> idList){
    //     List<ObjectError> errors = new LinkedList<>();
    //     FieldError error;
        
    //     if(idList.isEmpty()){
    //         error= new FieldError("selectedArticle","selectedArticle","Please select at least one article");
    //         errors.add(error);
    //     }
    //     return errors;
    // }

    
}

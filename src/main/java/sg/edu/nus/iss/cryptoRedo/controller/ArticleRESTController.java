package sg.edu.nus.iss.cryptoRedo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.cryptoRedo.model.Article;
import sg.edu.nus.iss.cryptoRedo.repository.ArticleRepository;

@RestController
@RequestMapping(path = "/news",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleRESTController {
@Autowired
ArticleRepository aRepo;

    
    @GetMapping(path="{id}")
    public ResponseEntity<String> getNewsById(@PathVariable String id){
        Optional<Article> a=aRepo.getArticlebyId(id);
        if(a.isEmpty()){
            JsonObject err= Json.createObjectBuilder()
            .add("HttpStatus",HttpStatus.NOT_FOUND.toString())
            .add("error","Cannot find news article %s".formatted(id))
            .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.toString());
        }


        return ResponseEntity.status(HttpStatus.OK).body(a.get().toJson().toString());
        

    }


    
}

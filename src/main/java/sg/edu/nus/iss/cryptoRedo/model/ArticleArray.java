package sg.edu.nus.iss.cryptoRedo.model;

import java.io.Serializable;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class ArticleArray implements Serializable {

    List<Article> articleList = new LinkedList<>();

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public void addToList(Article a){
        articleList.add(a);
    }

    public static JsonObject toJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        return jsonObject;
    }

    public static ArticleArray create(String jsonstr) {
        ArticleArray a = new ArticleArray();
        JsonObject o = toJson(jsonstr);
        List<Article> list = o.getJsonArray("Data")
        .stream()
        .map(v->(JsonObject)v)
        .map(v->Article.createFromJson(v))
        .toList();

        a.setArticleList(list);
    return a;
}
}

    //     for (JsonValue jsonvalue : jArray) {
    //         JsonObject articleObject = jsonvalue.asJsonObject();
    //         Article article = new Article();
           
    //         article.setId(articleObject.getString("id"));
    //         article.setPublished_on(Long.parseLong(articleObject.getJsonNumber("published_on").toString()));
    //         article.setTitle(articleObject.getString("title"));
    //         article.setUrl(articleObject.getString("url"));
    //         article.setImageurl(articleObject.getString("imageurl"));
    //         article.setBody(articleObject.getString("body"));
    //         article.setTags(articleObject.getString("tags"));
    //         article.setCategory(articleObject.getString("categories"));

    //         a.addToList(article);
    //     }

    //     return a;
    // }

    // public ArticleArray createFromJson(String jsonStr){
    //     JsonReader r = Json.createReader(new StringReader(jsonStr));
    //     JsonObject o = r.readObject();
    //     JsonArray jArray =o.getJsonArray("Data");
        
    //     ArticleArray articleList = new ArticleArray();

    //      for(JsonValue jsonValue:jArray){
    //         JsonObject ob= jsonValue.asJsonObject();
    //         Article a = new Article();
    //         a.createFromJson(ob);

    //         articleList.addToList(a);


    //      }

    //      return articleList;


    // }


    
    

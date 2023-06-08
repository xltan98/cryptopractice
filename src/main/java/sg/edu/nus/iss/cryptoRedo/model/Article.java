package sg.edu.nus.iss.cryptoRedo.model;

import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Article implements Serializable {

    private String id;
    private long published_on;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String category;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public long getPublished_on() {
        return published_on;
    }
    public void setPublished_on(long published_on) {
        this.published_on = published_on;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public static Article createFromJson(JsonObject o){
        // JsonReader r = Json.createReader(new StringReader(jsonStr));
        // JsonObject o = r.readObject();

        Article a= new Article();

        a.setBody(o.getString("body"));
        a.setCategory(o.getString("categories"));
        a.setId(o.getString("id"));
        a.setImageurl(o.getString("imageurl"));
        a.setPublished_on(o.getJsonNumber("published_on").longValue());
        a.setTags(o.getString("tags"));
        a.setTitle(o.getString("title"));
        a.setUrl(o.getString("url"));

        return a;

    }

    public  JsonObject toJson(){
        return Json.createObjectBuilder()
        .add("body",this.body)
        .add("categories",this.category)
        .add("id",this.id)
        .add("imageurl",this.imageurl)
        .add("published_on",this.getPublished_on())
        .add("tags",this.tags)
        .add("title",this.title)
        .add("url",this.url)
        .build();
    }

    


    
}

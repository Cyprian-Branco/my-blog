package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {
    private String content;
    private static ArrayList<Post> instances = new ArrayList<>();
    private boolean published;
    private LocalDateTime createdAt;

    public Post(String content){
        this.content = content;
        this.published =false;
        this.createdAt = LocalDateTime.now();
        instances.add(this);
    }
    public String getContent(){
        return content;
    }
    public static ArrayList<Post> getAll(){
        return instances;
    }
    public static void clearAllPosts(){
        instances.clear();
    }
    public boolean getPublished(){
        return this.published;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
}

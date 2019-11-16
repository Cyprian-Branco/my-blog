import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import models.Post;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main (String[] args) {
        staticFileLocation("/public");

        get("/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("content");
            Post newPost = new Post(content);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Post> posts = Post.getAll();
            model.put("posts", posts);
            return new ModelAndView(model, "index.hbs");

        }, new HandlebarsTemplateEngine());
        get("/posts/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<>();
          int idOfPostToFind = Integer.parseInt(request.params(":id"));
          Post foundPost = Post.findById(idOfPostToFind);
          model.put("post", foundPost);
          return new ModelAndView(model, "post-detail.hbs");
        }, new HandlebarsTemplateEngine());
        get("/posts/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToEdit = Integer.parseInt(request.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            model.put("editPost", editPost);
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/posts/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newContent = request.queryParams("content");
            int idOfPostToEdit = Integer.parseInt(request.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            editPost.update(newContent);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/posts/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Post deletePost = Post.findById(idOfPostToDelete); //use it to find post
            deletePost.deletePost();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}

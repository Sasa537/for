package com.example.Blog.Controllers;

import com.example.Blog.models.Post;
import com.example.Blog.repo.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/blog-main")
    public String blogMain (Model model)
    {
        Iterable<Post> posts = blogRepository.findAll();
        model.addAttribute("posts",posts);
        return "blog-main";
    }
    @GetMapping("/admin")
    public String adminAdd (Model model)
    {

        return "admin";
    }

    @PostMapping("/admin")
    public String BlogPostAdd (@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model)
    {
        Post post = new Post (title,anons,full_text);
        blogRepository.save(post);
        return "redirect:/blog-main";
    }
    @GetMapping("/blog/{id}")
    public String blogDetails (@PathVariable(value = "id") long id, Model model)
    {
       Optional<Post> post =  blogRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
      

            return "blog-details";

    }
}

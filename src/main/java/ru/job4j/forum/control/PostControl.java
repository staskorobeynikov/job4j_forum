package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.ForumService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/posts")
public class PostControl {

    private final ForumService fService;

    public PostControl(ForumService fService) {
        this.fService = fService;
    }

    @GetMapping("/post")
    public String post(@RequestParam("id") int id, Model model) {
        model.addAttribute("topic", fService.findById(id));
        return "post/post";
    }

    @GetMapping("/create")
    public String createPost(@RequestParam("id") int id, Model model) {
        model.addAttribute("topic_id", id);
        return "post/create";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post, HttpServletRequest req) {
        String topicId = req.getParameter("topic_id");
        Post addPost = Post.of(
                post.getId(),
                post.getName(),
                post.getDescription()
        );
        addPost.setCreator(
                fService.uFindByUsername(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
        );
        fService.addPost(Integer.parseInt(topicId), addPost);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String getViewUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", fService.postFindById(id));
        return "post/edit";
    }
}

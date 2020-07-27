package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.ForumService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostControl {

    private final ForumService fService;

    public PostControl(ForumService fService) {
        this.fService = fService;
    }

    @GetMapping("/post")
    public String post(@RequestParam("id") int id, Model model) {
        model.addAttribute("topic", fService.findById(id));
        return "post";
    }

    @GetMapping("/create")
    public String createPost(@RequestParam("id") int id, Model model) {
        model.addAttribute("topic_id", id);
        return "post/create";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post, HttpServletRequest req) {
        String topicId = req.getParameter("topic_id");
        fService.addPost(Integer.parseInt(topicId), Post.of(
                post.getId(),
                post.getName(),
                post.getDesc())
        );
        return "redirect:/";
    }

    @GetMapping("/update")
    public String getViewUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", fService.postFindById(id));
        return "post/edit";
    }
}

package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Status;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.service.ForumService;

import java.util.Arrays;

@Controller
@RequestMapping("/topic")
public class TopicControl {

    private final ForumService fService;

    public TopicControl(ForumService fService) {
        this.fService = fService;
    }

    @GetMapping("/create")
    public String getCreateTopic() {
        return "topic/create";
    }

    @GetMapping("/update")
    public String getFormUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute("topic", fService.findById(id));
        model.addAttribute("status", Arrays.asList(Status.values()));
        return "topic/edit";
    }

    @PostMapping("/save")
    public String updateTopic(@ModelAttribute Topic topic) {
        Topic nTopic = Topic.of(topic.getName());
        if (topic.getStatus() != null && topic.getStatus().equals(Status.CLOSED)) {
            nTopic.setStatus(topic.getStatus());
        }
        nTopic.setAuthor(fService.uFindById(1));
        nTopic.setId(topic.getId());
        fService.addTopic(nTopic);
        return "redirect:/";
    }
}

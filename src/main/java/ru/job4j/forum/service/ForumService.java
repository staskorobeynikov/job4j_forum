package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.TopicRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.List;

@Service
public class ForumService {

    private final TopicRepository tRep;

    private final PostRepository pRep;

    private final UserRepository uRep;

    public ForumService(TopicRepository tRep,
                        PostRepository pRep,
                        UserRepository uRep) {
        this.tRep = tRep;
        this.pRep = pRep;
        this.uRep = uRep;
    }

    public List<Topic> getAllTopics() {
        return tRep.findAllTopics();
    }

    public Topic findById(int id) {
        return tRep.findTopic_PostsById(id);
    }

    @Transactional
    public void addPost(int id, Post post) {
        if (post.getId() == 0) {
            post.setTopic(tRep.findById(id).get());
            pRep.save(post);
        } else {
            pRep.updatePost(post.getName(), post.getDescription(), post.getId());
        }
    }

    @Transactional
    public void addTopic(Topic topic) {
        if (topic.getId() == 0) {
            tRep.save(topic);
        } else {
            tRep.updateTopic(topic.getName(), topic.getStatus(), topic.getId());
        }
    }

    public Post postFindById(int id) {
        return pRep.findPost_TopicById(id);
    }

    public User uFindByUsername(String username) {
        return uRep.findByUsername(username);
    }
}

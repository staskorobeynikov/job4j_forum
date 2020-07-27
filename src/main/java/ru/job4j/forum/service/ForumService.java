package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ForumService {

    private final Map<Integer, Post> posts = new HashMap<>();

    private final Map<Integer, Topic> topics = new HashMap<>();

    private final List<User> users = new ArrayList<>();

    private static final AtomicInteger TOPIC_ID = new AtomicInteger(2);

    private static final AtomicInteger POST_ID = new AtomicInteger(3);

    public ForumService() {
        Post one = Post.of(1, "Лада", "Продам машину ладу 01.");
        Post two = Post.of(2, "Тойота", "Продам машину тойоту.");
        posts.put(one.getId(), one);
        posts.put(two.getId(), two);
        User user = User.of("root", "root");
        users.add(user);
        Topic topic = Topic.of("Продажа автомобилей");
        topic.setId(1);
        topic.setPosts(new ArrayList<>(posts.values()));
        topic.setAuthor(user);
        topics.put(topic.getId(), topic);
        one.setTopic(topic);
        two.setTopic(topic);
    }

    public List<Post> getAll() {
        return new ArrayList<>(posts.values());
    }

    public List<Topic> getAllTopics() {
        return new ArrayList<>(topics.values());
    }

    public Topic findById(int id) {
        return topics.get(id);
    }

    public void addPost(int id, Post post) {
        Topic byId = findById(id);
        post.setTopic(byId);
        if (post.getId() == 0) {
            post.setId(POST_ID.getAndIncrement());
        }
        posts.put(post.getId(), post);
    }

    public void addTopic(Topic topic) {
        topic.setAuthor(users.get(0));
        if (topic.getId() == 0) {
            topic.setId(TOPIC_ID.getAndIncrement());
        }
        topics.put(topic.getId(), topic);
    }

    public Post postFindById(int id) {
        return posts.get(id);
    }
}

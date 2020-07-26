package ru.job4j.forum.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Topic {
    private int id;

    private String name;

    private User author;

    private List<Post> posts = new ArrayList<>();

    public Topic of(String name) {
        Topic topic = new Topic();
        topic.name = name;
        return topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

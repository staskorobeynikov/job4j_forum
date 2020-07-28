package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {

    Post findPost_TopicById(int id);

    @Modifying
    @Query("update Post as p set p.name = ?1, p.description = ?2 where p.id = ?3")
    void updatePost(String name, String desc, int id);
}

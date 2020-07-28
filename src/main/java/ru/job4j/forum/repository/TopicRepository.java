package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Status;
import ru.job4j.forum.model.Topic;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

    @Query("select distinct t from Topic t join fetch t.author")
    List<Topic> findAllTopics();

    Topic findTopic_PostsById(int id);

    @Modifying
    @Query("update Topic as t set t.name = ?1, t.status = ?2 where t.id = ?3")
    void updateTopic(String name, Status status, int id);
}

package com.softuni.repository;

import com.softuni.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<CommentEntity, Long> {
    @Query("select  avg (c.score) from CommentEntity c ")
    Double findAvgScore();
}

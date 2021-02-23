package com.softuni.repository;

import com.softuni.model.entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Long> {

    List<HomeworkEntity> findAllByAuthor_Id(Long id);

    Optional<HomeworkEntity> findTop1ByOrderByComments();
    @Query("select  h.author.username from HomeworkEntity h group by h.author order by count(h) ")
    List<String> findAllByTOP();
}

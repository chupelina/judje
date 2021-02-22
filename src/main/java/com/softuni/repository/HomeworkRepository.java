package com.softuni.repository;

import com.softuni.model.entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Long> {

    List<HomeworkEntity> findAllByAuthor_Id(Long id);
}

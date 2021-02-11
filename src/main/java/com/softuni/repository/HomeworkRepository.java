package com.softuni.repository;

import com.softuni.model.entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Long> {
}

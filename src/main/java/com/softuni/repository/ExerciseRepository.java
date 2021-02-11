package com.softuni.repository;

import com.softuni.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    @Query("Select e.name from ExerciseEntity e order by e.name")
    List<String> getAllExercises();

    Optional<ExerciseEntity> findByName(String name);
}

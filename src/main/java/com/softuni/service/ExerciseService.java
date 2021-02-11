package com.softuni.service;

import com.softuni.model.binding.ExerciseAddBindingModel;
import com.softuni.model.entity.ExerciseEntity;

import java.util.List;

public interface ExerciseService {
    void addExercise(ExerciseAddBindingModel exerciseAddBindingModel);
    List<String> getAllExercise();

    boolean checkIsLate(String exercise);

    ExerciseEntity getExerciseByName(String exercise);
}

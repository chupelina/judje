package com.softuni.service.impl;

import com.softuni.model.binding.ExerciseAddBindingModel;
import com.softuni.model.entity.ExerciseEntity;
import com.softuni.repository.ExerciseRepository;
import com.softuni.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ModelMapper modelMapper;
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ModelMapper modelMapper, ExerciseRepository exerciseRepository) {
        this.modelMapper = modelMapper;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public void addExercise(ExerciseAddBindingModel exerciseAddBindingModel) {
        ExerciseEntity exerciseEntity = modelMapper
                .map(exerciseAddBindingModel, ExerciseEntity.class);
        exerciseRepository.save(exerciseEntity);
    }
}

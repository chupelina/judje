package com.softuni.service.impl;

import com.softuni.model.binding.ExerciseAddBindingModel;
import com.softuni.model.entity.ExerciseEntity;
import com.softuni.repository.ExerciseRepository;
import com.softuni.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<String> getAllExercise() {
        return exerciseRepository.getAllExercises();

    }

    @Override
    public boolean checkIsLate(String exercise) {
        ExerciseEntity currentExercise = exerciseRepository.findByName(exercise).orElse(null);
        if(LocalDateTime.now().isAfter(currentExercise.getDueDate())){
            return true;
        }
        return false;
    }

    @Override
    public ExerciseEntity getExerciseByName(String exercise) {

        return exerciseRepository.findByName(exercise).get();
    }
}

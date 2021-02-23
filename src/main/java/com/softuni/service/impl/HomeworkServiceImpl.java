package com.softuni.service.impl;

import com.softuni.model.binding.HomeworkViewModel;
import com.softuni.model.entity.HomeworkEntity;
import com.softuni.repository.HomeworkRepository;
import com.softuni.security.CurrentUser;
import com.softuni.service.CommentService;
import com.softuni.service.ExerciseService;
import com.softuni.service.HomeworkService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final CurrentUser currentUser;
    private final ExerciseService exerciseService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, CurrentUser currentUser, ExerciseService exerciseService, UserService userService, ModelMapper modelMapper) {
        this.homeworkRepository = homeworkRepository;
        this.currentUser = currentUser;
        this.exerciseService = exerciseService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addHomework(String exercise, String gitAddress) {
        HomeworkEntity homework = new HomeworkEntity();
        homework.setAddedOn(LocalDateTime.now()).setGitAddress(gitAddress)
                .setExercise(exerciseService.getExerciseByName(exercise))
                .setAuthor(userService.findById(currentUser.getId()));
        homeworkRepository.save(homework);
    }

    @Override
    public HomeworkViewModel getHomeworkModel() {
       HomeworkEntity entity = homeworkRepository.findTop1ByOrderByComments()
                .orElse(null);
         return modelMapper.map(entity, HomeworkViewModel.class);
    }

    @Override
    public HomeworkEntity getHomeworkById(Long id) {
        return homeworkRepository.findById(id).orElse(null);
    }

    @Override
    public List<String> getTopUsers() {
        return homeworkRepository.findAllByTOP();
    }

}

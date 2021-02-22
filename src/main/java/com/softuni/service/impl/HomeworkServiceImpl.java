package com.softuni.service.impl;

import com.softuni.model.entity.HomeworkEntity;
import com.softuni.repository.HomeworkRepository;
import com.softuni.security.CurrentUser;
import com.softuni.service.ExerciseService;
import com.softuni.service.HomeworkService;
import com.softuni.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final CurrentUser currentUser;
    private final ExerciseService exerciseService;
    private final UserService userService;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, CurrentUser currentUser, ExerciseService exerciseService, UserService userService) {
        this.homeworkRepository = homeworkRepository;
        this.currentUser = currentUser;
        this.exerciseService = exerciseService;
        this.userService = userService;
    }


    @Override
    public void addHomework(String exercise, String gitAddress) {
        HomeworkEntity homework = new HomeworkEntity();
        homework.setAddedOn(LocalDateTime.now()).setGitAddress(gitAddress)
                .setExercise(exerciseService.getExerciseByName(exercise))
                .setAuthor(userService.findById(currentUser.getId()));
        homeworkRepository.save(homework);
    }

}

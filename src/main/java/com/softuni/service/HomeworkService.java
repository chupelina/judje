package com.softuni.service;

import com.softuni.model.binding.HomeworkAddBindingModel;
import com.softuni.model.binding.HomeworkViewModel;
import com.softuni.model.entity.HomeworkEntity;

import java.util.List;

public interface HomeworkService {

    void addHomework(String exercise, String gitAddress);

    HomeworkViewModel getHomeworkModel();

    HomeworkEntity getHomeworkById(Long id);

    List<String> getTopUsers();
}

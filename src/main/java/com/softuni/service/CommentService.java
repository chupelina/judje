package com.softuni.service;

import com.softuni.model.binding.CommentsAddBindingModel;
import com.softuni.model.binding.HomeworkViewModel;

import java.util.Map;

public interface CommentService {

    void addComment(Long id, CommentsAddBindingModel commentsAddBindingModel);

    Double getAvg();

    Map<Integer, Integer> findScoreMap();
}

package com.softuni.service.impl;

import com.softuni.model.binding.CommentsAddBindingModel;
import com.softuni.model.binding.HomeworkViewModel;
import com.softuni.model.entity.CommentEntity;
import com.softuni.repository.CommentsRepository;
import com.softuni.service.CommentService;
import com.softuni.service.HomeworkService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentsRepository commentsRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final HomeworkService homeworkService;

    public CommentServiceImpl(CommentsRepository commentsRepository, ModelMapper modelMapper, UserService userService, HomeworkService homeworkService) {
        this.commentsRepository = commentsRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.homeworkService = homeworkService;
    }

    @Override
    public void addComment(Long id, CommentsAddBindingModel commentsAddBindingModel) {
        CommentEntity entity = modelMapper.map(commentsAddBindingModel, CommentEntity.class);
        entity.setAuthor(userService.findById(id))
             .setHomework(homeworkService.getHomeworkById(commentsAddBindingModel.getHomeworkId()));
        entity.setId(null);
        commentsRepository.save(entity);
    }

    @Override
    public Double getAvg() {
        return commentsRepository.findAvgScore();
    }

    @Override
    public Map<Integer, Integer> findScoreMap() {
        Map<Integer, Integer> scoreMap = initScoreMap();
        commentsRepository.findAll().forEach(c->{
                int score=c.getScore();
             scoreMap.put(score, scoreMap.get(score)+1);
                });
        return scoreMap;
    }

    private Map<Integer, Integer> initScoreMap() {
        Map<Integer, Integer> scoreMap = new HashMap<>();
        for (int i = 2; i < 7; i++) {
            scoreMap.put(i,0);
        }
        return scoreMap;
    }
}

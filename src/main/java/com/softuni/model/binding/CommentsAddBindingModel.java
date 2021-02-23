package com.softuni.model.binding;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;


public class CommentsAddBindingModel {
    private Long homeworkId;
    @NotNull(message = "Enter your score please!")
    private Integer score;
    @Length(min= 3, message = "You must enter at least 3 characters!")
    private String textContent;

    public Long getHomeworkId() {
        return homeworkId;
    }

    public CommentsAddBindingModel setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public CommentsAddBindingModel setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentsAddBindingModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
}

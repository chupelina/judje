package com.softuni.web;

import com.softuni.security.CurrentUser;
import com.softuni.service.CommentService;
import com.softuni.service.ExerciseService;
import com.softuni.service.HomeworkService;
import com.softuni.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;
    private final CommentService commentService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, ExerciseService exerciseService, HomeworkService homeworkService, CommentService commentService, UserService userService) {
        this.currentUser = currentUser;
        this.exerciseService = exerciseService;

        this.homeworkService = homeworkService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.isAnonymous()) {
            return "index";
        }
        model.addAttribute("exercises", exerciseService.getAllExercise());
       model.addAttribute("topUsers", homeworkService.getTopUsers());
       model.addAttribute("avg", commentService.getAvg());
       model.addAttribute("userCount", userService.getAllUsers());
       model.addAttribute("scoreMap", commentService.findScoreMap() );
        return "home";
    }

}

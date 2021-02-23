package com.softuni.web;

import com.softuni.model.binding.CommentsAddBindingModel;
import com.softuni.security.CurrentUser;
import com.softuni.service.CommentService;
import com.softuni.service.HomeworkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/comments")
public class CommentsController {
    private final HomeworkService homeworkService;
    private final CommentService commentService;
    private final CurrentUser currentUser;

    public CommentsController(HomeworkService homeworkService, CommentService commentService, CurrentUser currentUser) {
        this.homeworkService = homeworkService;
        this.commentService = commentService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String addComment(Model model){
        if(!model.containsAttribute("commentsAddBindingModel")){
            model.addAttribute("commentsAddBindingModel", new CommentsAddBindingModel());

        }
        model.addAttribute("homeworkModel", homeworkService.getHomeworkModel());
        return "homework-check";
    }
    @PostMapping("/add")
    public String confirmAdding(@Valid CommentsAddBindingModel commentsAddBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("commentsAddBindingModel",commentsAddBindingModel );
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentsAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }
        commentService.addComment(currentUser.getId(), commentsAddBindingModel);
        System.out.println();
        return "redirect:/";
    }


}

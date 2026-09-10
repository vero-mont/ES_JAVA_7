package it.aulab.progetto_blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.services.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public String index(Model viewModel) {
        viewModel.addAttribute("title", "Comments");
        viewModel.addAttribute("comments", commentService.readAll());
        return "comments";
    }

    @GetMapping("create")
    public String createCommentView(Model viewModel) {
        viewModel.addAttribute("title", "Create Comment");
        viewModel.addAttribute("comment", new Comment());
        return "createComment";
    }

    @PostMapping
    public String createComment(@ModelAttribute("comment") Comment comment) {
        commentService.create(comment);
        return "redirect:/comments";
    }
}
package com.sparta.todolist.controller;

import com.sparta.todolist.dto.comment.CommentSaveRequestDto;
import com.sparta.todolist.dto.comment.CommentSaveResponseDto;
import com.sparta.todolist.dto.comment.CommentUpdateRequestDto;
import com.sparta.todolist.entity.Comment;
import com.sparta.todolist.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentSaveResponseDto> saveComment(@RequestBody CommentSaveRequestDto commentSaveRequestDto) {
        return ResponseEntity.ok(commentService.saveComment(commentSaveRequestDto));
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentSaveResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        return ResponseEntity.ok(commentService.updateComment(commentId, commentUpdateRequestDto));
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}

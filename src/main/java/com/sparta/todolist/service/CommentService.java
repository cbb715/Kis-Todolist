package com.sparta.todolist.service;

import com.sparta.todolist.dto.comment.CommentSaveRequestDto;
import com.sparta.todolist.dto.comment.CommentSaveResponseDto;
import com.sparta.todolist.dto.comment.CommentUpdateRequestDto;
import com.sparta.todolist.entity.Comment;
import com.sparta.todolist.entity.Todo;
import com.sparta.todolist.repository.CommentRepository;
import com.sparta.todolist.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(CommentSaveRequestDto dto) {
        Todo todo = todoRepository.findById(dto.getTodoId())
                .orElseThrow(() -> new NullPointerException("일정을 찾을 수 없습니다."));

        Comment newComment = new Comment(dto.getContent(), dto.getUsername(), todo);
        Comment savedComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(savedComment.getId(), savedComment.getContent(), savedComment.getUsername());
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NullPointerException("댓글을 찾을 수 없습니다."));
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @Transactional
    public CommentSaveResponseDto updateComment(Long commentId, CommentUpdateRequestDto dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NullPointerException("댓글을 찾을 수 없습니다."));

        comment.update(dto.getContent());
        Comment updatedComment = commentRepository.save(comment);

        return new CommentSaveResponseDto(updatedComment.getId(), updatedComment.getContent(), updatedComment.getUsername());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new RuntimeException("댓글을 찾을 수 없습니다.");
        }
        commentRepository.deleteById(commentId);
    }
}

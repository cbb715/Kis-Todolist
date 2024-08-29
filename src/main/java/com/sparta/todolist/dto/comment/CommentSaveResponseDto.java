package com.sparta.todolist.dto.comment;

import lombok.Getter;

@Getter
public class CommentSaveResponseDto {

    private final Long id;
    private final String content;
    private final String username;

    public CommentSaveResponseDto(Long id, String content, String username) {
        this.id = id;
        this.content = content;
        this.username = username;
    }
}

package com.sparta.todolist.dto.comment;

import lombok.Getter;

@Getter
public class CommentSaveRequestDto {

        private Long todoId;
        private String content;
        private String username;
}

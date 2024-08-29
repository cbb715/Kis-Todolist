package com.sparta.todolist.dto.todo;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {

    private String username;
    private String title;
    private String contents;
}

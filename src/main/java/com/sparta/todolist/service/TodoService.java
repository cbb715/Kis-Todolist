package com.sparta.todolist.service;

import com.sparta.todolist.dto.todo.*;
import com.sparta.todolist.entity.Todo;
import com.sparta.todolist.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto) {
        Todo newTodo = new Todo(
                todoSaveRequestDto.getUsername(),
                todoSaveRequestDto.getTitle(),
                todoSaveRequestDto.getContents()
        );
        Todo savedTodo = todoRepository.save(newTodo);

        return new TodoSaveResponseDto(savedTodo.getId(), savedTodo.getTitle(), savedTodo.getContents());
    }

    public List<TodoSimpleResponseDto> getTodos() {
        List<Todo> todoList = todoRepository.findAll();
        List<TodoSimpleResponseDto> dtoList = new ArrayList<>();
        for (Todo todo : todoList) {
            TodoSimpleResponseDto dto = new TodoSimpleResponseDto(todo.getId(), todo.getTitle());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public TodoUpdateResponseDto updateTodo(Long todoId, TodoUpdateRequestDto todoUpdateRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("리스트를 찾을 수 없습니다."));
        todo.update(todoUpdateRequestDto.getTitle(), todoUpdateRequestDto.getContents());

        return new TodoUpdateResponseDto(todo.getId(), todo.getTitle(), todo.getContents());
    }

    @Transactional
    public void deleteTodo(Long todoId) {
        if (!todoRepository.existsById(todoId)) {
            throw new NullPointerException("리스트를 찾을 수 없습니다.");
        }
        todoRepository.deleteById(todoId);
    }
}
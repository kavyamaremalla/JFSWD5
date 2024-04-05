package com.example.todomanagement.service;

import com.example.todomanagement.dto.ToDoDto;

import java.util.List;

public interface ToDoService {

    ToDoDto addToDoTask(ToDoDto toDoDto);

    ToDoDto getToDoTask(Long id);

    List<ToDoDto> getAllToDoTasks();

    ToDoDto updateToDoTask(ToDoDto toDoDto, Long id);

    void deleteToDoTask(Long id);

}

package com.example.todomanagement.controller;

import com.example.todomanagement.dto.ToDoDto;
import com.example.todomanagement.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/todos")
public class ToDoController {

    private ToDoService toDoService;

    @PreAuthorize("hasRole(\"ADMIN\")")
    @PostMapping("/addTask")
    public ResponseEntity<ToDoDto> addTodoTask(@RequestBody ToDoDto toDoDto){
        ToDoDto savedToDoTask = toDoService.addToDoTask(toDoDto);
        return new ResponseEntity<>(savedToDoTask, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyRole(\"ADMIN\", \"USER\")")
    @GetMapping("/getAllTasks")
    public ResponseEntity<List<ToDoDto>> getAllToDos(){
        List<ToDoDto> toDoDtoList = toDoService.getAllToDoTasks();
        return new ResponseEntity<>(toDoDtoList,HttpStatus.OK);
    }
}

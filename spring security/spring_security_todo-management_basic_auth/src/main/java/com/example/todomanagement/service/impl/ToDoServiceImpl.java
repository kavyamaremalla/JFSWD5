package com.example.todomanagement.service.impl;

import com.example.todomanagement.dto.ToDoDto;
import com.example.todomanagement.entity.ToDo;
import com.example.todomanagement.repository.ToDoRepository;
import com.example.todomanagement.service.ToDoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository toDoRepository;

    private ModelMapper modelMapper;

    @Override
    public ToDoDto addToDoTask(ToDoDto toDoDto) {
        ToDo toDoEntity = modelMapper.map(toDoDto, ToDo.class);
        return modelMapper.map(toDoRepository.save(toDoEntity), ToDoDto.class);
    }

    @Override
    public ToDoDto getToDoTask(Long id) {
        ToDo toDoEntity = toDoRepository.findById(id).get();
        return modelMapper.map(toDoEntity, ToDoDto.class);
    }

    @Override
    public List<ToDoDto> getAllToDoTasks() {
        return toDoRepository.findAll().stream()
                .map(toDoEntity -> modelMapper.map(toDoEntity, ToDoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ToDoDto updateToDoTask(ToDoDto toDoDto, Long id) {

        ToDo toDoEntity = toDoRepository.findById(id).get();

        toDoEntity.setTitle(toDoDto.getTitle());
        toDoEntity.setDescription(toDoDto.getDescription());
        toDoEntity.setCompleted(toDoDto.isCompleted());

        return modelMapper.map(toDoRepository.save(toDoEntity), ToDoDto.class);
    }

    @Override
    public void deleteToDoTask(Long id) {
        toDoRepository.deleteById(id);
    }
}

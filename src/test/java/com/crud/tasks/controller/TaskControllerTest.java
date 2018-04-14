package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {

        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1l, "Test1", "Contenet1"));

        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1l, "Test1", "Contenet1"));

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test1")));
    }

    @Test
    public void getTask() throws Exception {

        //Given
        Task task = new Task(1l, "Test1", "Contenet1");
        TaskDto taskDto = new TaskDto(1l, "Test1", "Contenet1");
        Optional<Task> optionalTask = Optional.of((Task) task);
        when(service.getTaskById(task.getId())).thenReturn(optionalTask);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test1")));
    }

    @Test
    public void deleteTask() throws Exception {

        //Given
        Task task = new Task(1l, "Test1", "Contenet1");
        //When & Then
        mockMvc.perform(delete("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTask() throws Exception {

        //Given
        Task updatedTask = new Task(1l, "TestUpdate1", "ContenetUpdate");
        TaskDto updatedTaskDto = new TaskDto(1l, "TestUpdate1", "ContenetUpdate");

        when(service.saveTask(updatedTask)).thenReturn(updatedTask);

        when(taskMapper.mapToTaskDto(updatedTask)).thenReturn(updatedTaskDto);
        when(taskMapper.mapToTask(updatedTaskDto)).thenReturn(updatedTask);

        when(taskMapper.mapToTaskDto(any())).thenReturn(updatedTaskDto);
        when(taskMapper.mapToTask(any())).thenReturn(updatedTask);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedTask);

        //When & Then
        ResultActions action = mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent));

        action.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("TestUpdate1")));
    }

    @Test
    public void createTask() throws Exception {

        //Given
        Task task = new Task(1l, "Test1", "Contenet");
        TaskDto taskDto = new TaskDto(1l, "Test1", "Contenet");

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        ResultActions action = mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent));

        action.andExpect(status().isOk());

    }
}
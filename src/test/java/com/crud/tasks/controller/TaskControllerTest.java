package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        ResultActions action = mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON));

        action.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id",is(1)))
            .andExpect(jsonPath("$[0].title", is("Test1")));

    }

    @Test
    public void getTask() throws Exception {
    }

    @Test
    public void deleteTask() throws Exception {
    }

    @Test
    public void updateTask() throws Exception {
    }

    @Test
    public void createTask() throws Exception {
    }

}
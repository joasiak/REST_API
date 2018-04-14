package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTask() throws Exception {
        //Given
        Task task = new Task(1l, "Test1", "Contenet1");
        TaskDto taskDto = new TaskDto(1l, "Test1", "Contenet1");
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(mappedTask.getId(), task.getId());
        Assert.assertEquals(mappedTask.getTitle(), task.getTitle());
        Assert.assertEquals(mappedTask.getContent(), task.getContent());
    }

    @Test
    public void mapToTaskDto() throws Exception {
        //Given
        Task task = new Task(1l, "Test1", "Contenet1");
        TaskDto taskDto = new TaskDto(1l, "Test1", "Contenet1");
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(mappedTaskDto.getId(), task.getId());
        Assert.assertEquals(mappedTaskDto.getTitle(), task.getTitle());
        Assert.assertEquals(mappedTaskDto.getContent(), task.getContent());
    }

    @Test
    public void mapToTaskDtoList() throws Exception {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1l, "Test1", "Contenet1"));

        List<TaskDto> listTaskDto = new ArrayList<>();
        listTaskDto.add(new TaskDto(1l, "Test1", "Contenet1"));

        //When
        List<TaskDto> mappedTaskList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(mappedTaskList.get(0).getId(), listTaskDto.get(0).getId());
        Assert.assertEquals(mappedTaskList.get(0).getTitle(), listTaskDto.get(0).getTitle());
        Assert.assertEquals(mappedTaskList.get(0).getContent(), listTaskDto.get(0).getContent());

    }

}
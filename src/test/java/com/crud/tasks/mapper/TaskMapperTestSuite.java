package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper mapper;

    @Test
    public void shouldMapTasksToDtos() {
        //given
        Task task1 = new Task(1L, "title1", "desc1");
        Task task2 = new Task(2L, "title2", "desc2");
        List<Task> tasks = Arrays.asList(task1, task2);

        //when
        List<TaskDto> taskDtos = mapper.mapToTaskDtoList(tasks);
        TaskDto taskDto1 = taskDtos.get(0);
        TaskDto taskDto2 = taskDtos.get(1);

        //then
        assertEquals(taskDto1.getId(), task1.getId());
        assertEquals(taskDto1.getContent(), task1.getContent());
        assertEquals(taskDto1.getTitle(), task1.getTitle());
        assertEquals(taskDto2.getId(), task2.getId());
        assertEquals(taskDto2.getContent(), task2.getContent());
        assertEquals(taskDto2.getTitle(), task2.getTitle());
    }

    @Test
    public void shouldMapTaskDtoToTask() {
        //given
        TaskDto taskDto = new TaskDto(1L, "title1", "desc1");

        //when
        Task task = mapper.mapToTask(taskDto);

        //then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getContent(), taskDto.getContent());
        assertEquals(task.getTitle(), taskDto.getTitle());
    }

}

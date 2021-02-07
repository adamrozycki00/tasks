package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.*;
import com.crud.tasks.service.DbService;
import com.google.gson.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TaskController.class)
@RunWith(SpringRunner.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper mapper;

    @Test
    public void shouldReturnTasks() throws Exception {
        //given
        TaskDto task1 = new TaskDto(1L, "title 1", "content 1");
        TaskDto task2 = new TaskDto(2L, "title 2", "content 2");
        List<TaskDto> tasks = Arrays.asList(task1, task2);
        when(mapper.mapToTaskDtoList(anyList())).thenReturn(tasks);

        //when & then
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnTaskWithGivenId() throws Exception {
        //given
        int testId = 8;
        Task task = new Task((long) testId, "title", "content");
        TaskDto taskDto = new TaskDto(task.getId(), task.getTitle(), task.getContent());
        when(service.getTask((long) testId)).thenReturn(Optional.of(task));
        when(mapper.mapToTaskDto(ArgumentMatchers.eq(task))).thenReturn(taskDto);

        //when & then
        mockMvc.perform(get("/v1/tasks/" + testId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testId)));
    }

    @Test
    public void shouldCallMethodDeleteTaskWithGivenIdAsParameter() throws Exception {
        //given
        int testId = 8;

        //when & then
        mockMvc.perform(delete("/v1/tasks/" + testId))
                .andExpect(status().isOk());

        //then
        verify(service, times(1)).deleteTask((long) testId);
    }

    @Test
    public void shouldAcceptAndReturnTaskDto() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(7L, "test title", "test content");
        String jsonContent = new Gson().toJson(taskDto);
        when(mapper.mapToTaskDto(any())).thenReturn(taskDto);

        //when & then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(7)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test content")));
    }

    @Test
    public void shouldCallMethodSaveTaskWithGivenTaskAsParameter() throws Exception {
        //given
        Task task = new Task(1L, "title", "content");
        TaskDto taskDto = new TaskDto(task.getId(), task.getTitle(), task.getContent());
        String jsonContent = new Gson().toJson(taskDto);
        when(mapper.mapToTask(ArgumentMatchers.eq(taskDto))).thenReturn(task);

        //when & then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        //then
        verify(service, times(1)).saveTask(task);
    }

}

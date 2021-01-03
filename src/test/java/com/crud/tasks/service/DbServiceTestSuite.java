package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService service;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldCallFindAll() {
        //when
        service.getAllTasks();

        //then
        verify(repository, times(1)).findAll();
    }

    @Test
    public void shouldCallFindById() {
        //given
        Long id = 9L;

        //when
        service.getTask(id);

        //then
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void shouldCallSave() {
        //given
        Task task = new Task(7L, "title", "content");

        //when
        service.saveTask(task);

        //then
        verify(repository, times(1)).save(task);
    }

    @Test
    public void shouldCallDeleteById() {
        //given
        Long id = 99L;

        //when
        service.deleteTask(id);

        //then
        verify(repository, times(1)).deleteById(id);
    }


}

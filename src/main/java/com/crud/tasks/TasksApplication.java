package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {

		TaskDto taskDto = new TaskDto(Long.valueOf(1), "test title", "test content");
		System.out.println(taskDto);

		SpringApplication.run(TasksApplication.class, args);
	}

}

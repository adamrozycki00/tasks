package com.crud.tasks.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDto)) return false;

        TaskDto taskDto = (TaskDto) o;

        if (!id.equals(taskDto.id)) return false;
        if (!title.equals(taskDto.title)) return false;
        return content.equals(taskDto.content);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

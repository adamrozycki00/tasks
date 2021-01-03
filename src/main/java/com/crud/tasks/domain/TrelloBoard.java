package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class TrelloBoard {

    private String id;
    private String name;
    private List<TrelloList> lists;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrelloBoard)) return false;

        TrelloBoard that = (TrelloBoard) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return Objects.equals(lists, that.lists);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (lists != null ? lists.hashCode() : 0);
        return result;
    }

}

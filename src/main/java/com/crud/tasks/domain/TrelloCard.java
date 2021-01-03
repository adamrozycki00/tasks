package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrelloCard {

    private String name;
    private String description;
    private String pos;
    private String listId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrelloCard)) return false;

        TrelloCard that = (TrelloCard) o;

        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        if (!pos.equals(that.pos)) return false;
        return listId.equals(that.listId);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + pos.hashCode();
        result = 31 * result + listId.hashCode();
        return result;
    }

}

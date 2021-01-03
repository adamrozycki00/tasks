package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrelloList {

    private String id;
    private String name;
    private boolean isClosed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrelloList)) return false;

        TrelloList that = (TrelloList) o;

        if (isClosed != that.isClosed) return false;
        if (!id.equals(that.id)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (isClosed ? 1 : 0);
        return result;
    }

}

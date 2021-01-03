package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrelloTestSuite {

    @Test
    public void shouldCreateEmptyTrello() {
        //when
        Trello trello = new Trello();
        Long board = trello.getBoard();
        Long card = trello.getCard();

        //then
        assertNull(board);
        assertNull(card);
    }

    @Test
    public void shouldCreateNonEmptyTrello() {
        //when
        Trello trello = new Trello(3L, 9L);
        Long board = trello.getBoard();
        Long card = trello.getCard();

        //then
        assertNotNull(board);
        assertNotNull(card);
    }

}

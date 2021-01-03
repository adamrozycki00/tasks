package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator validator;

    @Test
    public void shouldReturnFilteredBoardList() {
        //given
        TrelloBoard board1 = new TrelloBoard("1", "name", new ArrayList<>());
        TrelloBoard board2 = new TrelloBoard("2", "test", new ArrayList<>());
        List<TrelloBoard> trelloBoards = Arrays.asList(board1, board2);

        //when
        List<TrelloBoard> validatedTrelloBoards = validator.validateTrelloBoards(trelloBoards);

        //then
        assertEquals(1, validatedTrelloBoards.size());
    }

}

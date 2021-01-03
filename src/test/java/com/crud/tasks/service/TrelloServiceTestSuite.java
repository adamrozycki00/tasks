package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloCardDTO;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService service;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void shouldCallGetTrelloBoards() {
        //when
        service.fetchTrelloBoards();

        //then
        verify(trelloClient, times(1)).getTrelloBoards();
    }

    @Test
    public void shouldCallCreateNewCard() {
        //given
        TrelloCardDTO cardDTO = new TrelloCardDTO("name", "desc", "pos", "id");

        //when
        service.createTrelloCard(cardDTO);

        //then
        verify(trelloClient, times(1)).createNewCard(cardDTO);
    }

}

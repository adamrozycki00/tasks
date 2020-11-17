package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDTO;
import com.crud.tasks.domain.TrelloCardDTO;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private TrelloConfig trelloConfig;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloAppToken()).thenReturn("test");
        when(trelloConfig.getTrelloAppUsername()).thenReturn("testUser");
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //given
        TrelloBoardDTO[] trelloBoards = new TrelloBoardDTO[1];
        trelloBoards[0] = new TrelloBoardDTO("test_id", "test_board", new ArrayList<>());

        URI url = new URI("http://test.com/members/testUser/boards?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(url, TrelloBoardDTO[].class)).thenReturn(trelloBoards);

        //when
        List<TrelloBoardDTO> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateNewCard() throws URISyntaxException {
        //given
        TrelloCardDTO trelloCardDTO = new TrelloCardDTO(
                "Test task",
                "Test description",
                "top",
                "test_id"
        );

        URI url = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20description&pos=top&idList=test_id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                null,
                "Test task",
                "http://test.com"
        );

        when(restTemplate.postForObject(url, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        //when
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDTO);

        //then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }
}

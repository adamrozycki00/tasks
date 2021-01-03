package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade facade;

    @Mock
    private TrelloService service;

    @Mock
    private TrelloMapper mapper;

    @Mock
    private TrelloValidator validator;

    @Test
    public void shouldFetchEmptyList() {
        //given
        ArrayList<TrelloListDTO> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDTO("1", "test_list", false));

        ArrayList<TrelloBoardDTO> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDTO("1", "test", trelloLists));

        ArrayList<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        ArrayList<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));

        when(service.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(mapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(mapper.mapToBoardDTOs(anyList())).thenReturn(new ArrayList<>());
        when(validator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

        //when
        List<TrelloBoardDTO> trelloBoardDTOs = facade.fetchTrelloBoards();

        //then
        assertNotNull(trelloBoardDTOs);
        assertEquals(0, trelloBoardDTOs.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //given
        ArrayList<TrelloListDTO> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDTO("1", "my_list", false));

        ArrayList<TrelloBoardDTO> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDTO("1", "my_task", trelloLists));

        ArrayList<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "my_list", false));

        ArrayList<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "my_task", mappedTrelloLists));

        when(service.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(mapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(mapper.mapToBoardDTOs(anyList())).thenReturn(trelloBoards);
        when(validator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //when
        List<TrelloBoardDTO> trelloBoardDTOs = facade.fetchTrelloBoards();

        //then
        assertNotNull(trelloBoardDTOs);
        assertEquals(1, trelloBoardDTOs.size());

        trelloBoardDTOs.forEach(trelloBoardDTO -> {
            assertEquals("1", trelloBoardDTO.getId());
            assertEquals("my_task", trelloBoardDTO.getName());

            trelloBoardDTO.getLists().forEach(trelloListDTO -> {
                assertEquals("1", trelloListDTO.getId());
                assertEquals("my_list", trelloListDTO.getName());
                assertEquals(false, trelloListDTO.isClosed());
            });
        });
    }

}

package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper mapper;

    @Test
    public void shouldMapTrelloBoardListToDTOListAndViceVersa() {
        //given
        TrelloList trelloList1 = new TrelloList("id1", "name1", true);
        TrelloList trelloList2 = new TrelloList("id2", "name2", false);
        TrelloBoard trelloBoard1 = new TrelloBoard("id1", "name1", Arrays.asList(trelloList1, trelloList2));
        TrelloBoard trelloBoard2 = new TrelloBoard("id2", "name2", Arrays.asList(trelloList2, trelloList1));
        List<TrelloBoard> trelloBoardsBeforeMapping = Arrays.asList(trelloBoard1, trelloBoard2);

        //when
        List<TrelloBoard> trelloBoardsAfterMapping =
                mapper.mapToBoards(mapper.mapToBoardDTOs(trelloBoardsBeforeMapping));
        TrelloBoard trelloBoard1AfterMapping = trelloBoardsAfterMapping.get(0);
        TrelloBoard trelloBoard2AfterMapping = trelloBoardsAfterMapping.get(1);

        //then
        assertEquals(trelloBoard1, trelloBoard1AfterMapping);
        assertEquals(trelloBoard2, trelloBoard2AfterMapping);
    }

    @Test
    public void shouldMapTrelloCardsToDTOsAndViceVersa() {
        //given
        TrelloCard trelloCard1 = new TrelloCard("name1", "desc1", "pos1", "id1");
        TrelloCard trelloCard2 = new TrelloCard("name2", "desc2", "pos2", "id2");

        //when
        TrelloCard trelloCard1AfterMapping = mapper.mapToCard(mapper.mapToCardDTO(trelloCard1));
        TrelloCard trelloCard2AfterMapping = mapper.mapToCard(mapper.mapToCardDTO(trelloCard2));

        //then
        assertEquals(trelloCard1, trelloCard1AfterMapping);
        assertEquals(trelloCard2, trelloCard2AfterMapping);
    }

}

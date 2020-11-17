package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDTO;
import com.crud.tasks.domain.TrelloCardDTO;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(value = "/getTrelloBoards", method = GET)
    public List<TrelloBoardDTO> getTrelloBoards() {
//        trelloClient.getTrelloBoards()
//                .forEach(board -> {
//                    System.out.println("\n" + board.getName() + " - " + board.getId());
//                    System.out.println("\tThis board contains lists:");
//                    board.getLists().forEach(list ->
//                            System.out.println("\t" + list.getName() + " - " + list.getId() + " - " + list.isClosed()));
//                });
        return trelloClient.getTrelloBoards();
    }

    @RequestMapping(value = "/createTrelloCard", method = POST)
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDTO trelloCardDTO) {
        CreatedTrelloCard card = trelloClient.createNewCard(trelloCardDTO);
//        System.out.println(card.getId() + " - " + card.getName() + " - " + card.getShortUrl() + " - " +
//                card.getBadges().getVotes() + " - " + card.getBadges().getAttachmentsByType().getTrello().getBoard());
        return card;
    }

}

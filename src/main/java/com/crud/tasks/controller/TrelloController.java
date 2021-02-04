package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDTO;
import com.crud.tasks.domain.TrelloBoardDTO;
import com.crud.tasks.domain.TrelloCardDTO;
import com.crud.tasks.trello.facade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
public class TrelloController {

    @Autowired
    private TrelloFacade facade;

    @RequestMapping(value = "/getTrelloBoards", method = GET)
    public List<TrelloBoardDTO> getTrelloBoards() {
        return facade.fetchTrelloBoards();
    }

    @RequestMapping(value = "/createTrelloCard", method = POST)
    public CreatedTrelloCardDTO createTrelloCard(@RequestBody TrelloCardDTO trelloCardDTO) {
        return facade.createTrelloCard(trelloCardDTO);
    }

}

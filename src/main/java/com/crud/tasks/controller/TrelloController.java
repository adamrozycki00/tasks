package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDTO;
import com.crud.tasks.domain.TrelloCardDTO;
import com.crud.tasks.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloService service;

    @RequestMapping(value = "/getTrelloBoards", method = GET)
    public List<TrelloBoardDTO> getTrelloBoards() {
        return service.fetchTrelloBoards();
    }

    @RequestMapping(value = "/createTrelloCard", method = POST)
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDTO trelloCardDTO) {
        return service.createTrelloCard(trelloCardDTO);
    }

}

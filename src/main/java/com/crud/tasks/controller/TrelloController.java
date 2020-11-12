package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDTO;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(value = "/getTrelloBoards", method = GET)
    public void getTrelloBoards() {
        List<TrelloBoardDTO> trelloBoards = trelloClient.getTrelloBoards();
        trelloBoards.forEach(board -> System.out.println(board.getId() + ", " + board.getName()));
    }

}
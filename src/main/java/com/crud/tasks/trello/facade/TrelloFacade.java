package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrelloFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    @Autowired
    private TrelloService service;

    @Autowired
    private TrelloMapper mapper;

    @Autowired
    private TrelloValidator validator;

    public List<TrelloBoardDTO> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = mapper.mapToBoards(service.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = validator.validateTrelloBoards(trelloBoards);
        return mapper.mapToBoardDTOs(filteredBoards);
    }

    public CreatedTrelloCardDTO createTrelloCard(TrelloCardDTO cardDTO) {
        validator.validateCard(mapper.mapToCard(cardDTO));
        return service.createTrelloCard(cardDTO);
    }

}

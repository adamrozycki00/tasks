package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDTO;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDTO;
import com.crud.tasks.domain.TrelloCardDTO;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrelloService {

    private static final String SUBJECT = "Task: New Trello card";

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    @Autowired
    private AdminConfig adminConfig;

    public List<TrelloBoardDTO> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDTO createTrelloCard(TrelloCardDTO trelloCardDTO) {
        CreatedTrelloCardDTO newCard = trelloClient.createNewCard(trelloCardDTO);
        Optional.ofNullable(newCard).ifPresent(card -> {
            Mail mail = new Mail(adminConfig.getAdminMail(), SUBJECT,
                            "New card: " + card.getName() + " has been created on your Trello account.");
            emailService.send(mail, emailService.getText(mail, false));
        });
        return newCard;
    }

}

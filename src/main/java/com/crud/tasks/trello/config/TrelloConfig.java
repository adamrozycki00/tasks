package com.crud.tasks.trello.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TrelloConfig {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloAppToken;

    @Value("${trello.app.username}")
    private String trelloAppUsername;

    public String getTrelloApiEndpoint() {
        return trelloApiEndpoint;
    }

    public String getTrelloAppKey() {
        return trelloAppKey;
    }

    public String getTrelloAppToken() {
        return trelloAppToken;
    }

    public String getTrelloAppUsername() {
        return trelloAppUsername;
    }

}

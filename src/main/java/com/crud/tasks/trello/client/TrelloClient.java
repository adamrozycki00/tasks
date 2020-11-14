package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDTO;
import com.crud.tasks.domain.TrelloCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloAppToken;

    @Value("${trello.app.username}")
    private String trelloAppUsername;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDTO> getTrelloBoards() {
        Optional<TrelloBoardDTO[]> boardsResponse =
                Optional.ofNullable(restTemplate.getForObject(getUrl(), TrelloBoardDTO[].class));
        return boardsResponse.map(Arrays::asList).orElseGet(ArrayList::new);
    }

    private URI getUrl() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloAppUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloAppToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDTO trelloCardDTO) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloAppToken)
                .queryParam("name", trelloCardDTO.getName())
                .queryParam("desc", trelloCardDTO.getDescription())
                .queryParam("pos", trelloCardDTO.getPos())
                .queryParam("idList", trelloCardDTO.getListId())
                .build().encode().toUri();
        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }

}

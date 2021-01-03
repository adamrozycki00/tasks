package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloConfigTestSuite {

    @Autowired
    private TrelloConfig config;

    @Test
    public void shouldGetConfigProperties() {
        //when
        String trelloApiEndpoint = config.getTrelloApiEndpoint();
        String trelloAppKey = config.getTrelloAppKey();
        String trelloAppToken = config.getTrelloAppToken();
        String trelloAppUsername = config.getTrelloAppUsername();

        //then
        assertEquals("https://api.trello.com/1", trelloApiEndpoint);
        assertEquals("30bddd2d7563206266070054806ab40c", trelloAppKey);
        assertEquals("be554dfd59ec0e0876195105dbcbedb977653efd5bcc51fa0f8f9c576a69a551", trelloAppToken);
        assertEquals("adamroycki1", trelloAppUsername);
    }

}

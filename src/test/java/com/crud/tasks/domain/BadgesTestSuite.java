package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BadgesTestSuite {

    @Test
    public void shouldCreateEmptyBadges() {
        //when
        Badges badges = new Badges();
        Long votes = badges.getVotes();
        AttachmentsByType attachmentsByType = badges.getAttachmentsByType();

        //then
        assertNull(votes);
        assertNull(attachmentsByType);
    }

    @Test
    public void shouldCreateNonEmptyBadges() {
        //when
        Badges badges = new Badges(9L, new AttachmentsByType());
        Long votes = badges.getVotes();
        AttachmentsByType attachmentsByType = badges.getAttachmentsByType();

        //then
        assertNotNull(votes);
        assertNotNull(attachmentsByType);
    }

}

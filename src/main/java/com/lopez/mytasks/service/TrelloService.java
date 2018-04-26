package com.lopez.mytasks.service;

import com.lopez.mytasks.config.AdminConfig;
import com.lopez.mytasks.domain.CreatedTrelloCardDto;
import com.lopez.mytasks.domain.Mail;
import com.lopez.mytasks.domain.TrelloBoardDto;
import com.lopez.mytasks.domain.TrelloCardDto;
import com.lopez.mytasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New Trello card";

    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createdTrelloCard(final TrelloCardDto trelloCardDto) {

        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        ofNullable(newCard).ifPresent(card -> simpleEmailService.send(new Mail(adminConfig.getAdminMail(), "", SUBJECT, "New Card: " + trelloCardDto.getName() + " has been created on your Trello account")));
        return newCard;
    }
}

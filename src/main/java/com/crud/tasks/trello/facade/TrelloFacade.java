package com.crud.tasks.trello.facade;

import com.crud.tasks.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrelloFacade {

    @Autowired
    private TrelloService trelloService;
}

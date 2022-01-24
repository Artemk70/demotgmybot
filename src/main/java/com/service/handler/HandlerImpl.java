package com.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
public class HandlerImpl implements Handler {
    private MessageHandlerImpl messageHandler;
    private QueryHandlerImpl queryHandler;

    public HandlerImpl(MessageHandlerImpl messageHandler, QueryHandlerImpl queryHandler) {
        this.messageHandler = messageHandler;
        this.queryHandler = queryHandler;
    }

    @Override
    public BotApiMethod<?> handler(Update update) {
        if (update.hasCallbackQuery()) {
            log.info("query from keyboard");
            return queryHandler.handler(update);
        } else if (update.hasMessage()) {
            log.info("query from message");
            return messageHandler.handler(update);
        }
        log.info("another query");
        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text(update.getMessage().getText())
                .build();
    }
}

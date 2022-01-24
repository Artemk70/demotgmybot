package com;

import com.service.handler.HandlerImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Getter
@Setter
public class Bot extends TelegramWebhookBot {
    private String username;
    private String token;
    private String botPath;

    private HandlerImpl handler;

    public Bot(DefaultBotOptions options) {
        super(options);
    }

    @Autowired
    public void setHandler(HandlerImpl handler) {
        this.handler = handler;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return handler.handler(update);
    }
}

package com.service;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageHandler {
    public BotApiMethod<?> handler(Update update);
}

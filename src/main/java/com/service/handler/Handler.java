package com.service.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Handler {

    public BotApiMethod<?> handler(Update update);
}

package com.service.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public interface MessageHandler {
    BotApiMethod<?> handler(Update update);

    BotApiMethod<?> messageHandler(String chatId, String text);

    BotApiMethod<?> messageHandler(String chatId, List<List<InlineKeyboardButton>> buttons);
}

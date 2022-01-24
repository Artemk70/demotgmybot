package com.service.handler;

import com.service.FindOutTheExchangeRateImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MessageHandlerImpl implements MessageHandler {
    private FindOutTheExchangeRateImpl exchangeRate;
    private final String commandExchangeRate = "/exchange_rate";

    @Autowired
    public void setExchangeRate(FindOutTheExchangeRateImpl exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public SendMessage handler(Update update) {
        SendMessage sendMessage = null;

        Message message = update.getMessage();
        String chatId = String.valueOf(message.getChatId());

        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> optionalCommandExchangeRate = message.getEntities()
                    .stream()
                    .filter(e -> commandExchangeRate.equals(e.getText()))
                    .findFirst();

            if (optionalCommandExchangeRate.isPresent()) {
                String commandFromMessage = message.getText().substring(optionalCommandExchangeRate.get().getOffset(),
                        optionalCommandExchangeRate.get().getLength());

                if (commandExchangeRate.equals(commandFromMessage)) {
                    List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
                    buttons.add(Arrays.asList(
                            InlineKeyboardButton.builder().text("USD").callbackData("usd").build(),
                            InlineKeyboardButton.builder().text("EUR").callbackData("eur").build(),
                            InlineKeyboardButton.builder().text("GBP").callbackData("gbp").build(),
                            InlineKeyboardButton.builder().text("CHF").callbackData("chf").build())
                    );
                    sendMessage = messageHandler(chatId, buttons);
                } else {
                    sendMessage = messageHandler(chatId, message.getText());
                }
            }
        }

        return sendMessage;
    }

    @Override
    public SendMessage messageHandler(String chatId, String text) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
    }

    @Override
    public SendMessage messageHandler(String chatId, List<List<InlineKeyboardButton>> buttons) {
        return SendMessage.builder()
                .chatId(chatId)
                .text("Курс валют ЦБ РФ")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build();
    }
}

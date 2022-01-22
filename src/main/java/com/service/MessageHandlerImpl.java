package com.service;

import com.dao.CourseDAO;
import com.dao.СurrenciesDAO;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
public class MessageHandlerImpl implements MessageHandler {
    private final String commandCurrencyConverter = "/exchange_rate";

    @Override
    public BotApiMethod<?> handler(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = null;
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities()
                    .stream()
                    .filter(e -> commandCurrencyConverter.equals(e.getText()))
                    .findFirst();

            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(),
                        commandEntity.get().getLength());

                if (commandCurrencyConverter.equals(command)) {
                    FindOutTheExchangeRateImpl exchangeRate = new FindOutTheExchangeRateImpl();
                    CourseDAO course = exchangeRate.getCourse();
                    СurrenciesDAO valute = course.getValute();
                    Double usd = valute.getUsd().getValue();
                    Double eur = valute.getEur().getValue();

                    sendMessage = SendMessage.builder()
                            .chatId(message.getChatId().toString())
                            .text("USD: " + usd
                                    + "\n" +
                                    "EUR: " + eur)
                            .build();
                }
            }
        }
        return sendMessage;
    }
}

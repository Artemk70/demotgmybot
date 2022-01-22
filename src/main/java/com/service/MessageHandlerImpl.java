package com.service;

import com.dao.CourseDAO;
import com.dao.СurrenciesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
public class MessageHandlerImpl implements MessageHandler {
    private FindOutTheExchangeRateImpl exchangeRate;
    private final String commandCurrencyConverter = "/exchange_rate";

    @Autowired
    public void setExchangeRate(FindOutTheExchangeRateImpl exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

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
                    CourseDAO course = exchangeRate.getCourse();
                    СurrenciesDAO valute = course.getValute();
                    Double usd = valute.getUsd().getValue();
                    Double eur = valute.getEur().getValue();
                    String data = course.getDate();

                    sendMessage = SendMessage.builder()
                            .chatId(message.getChatId().toString())
                            .text(data +
                                    "\n" +
                                    "USD: " + usd
                                    + "\n" +
                                    "EUR: " + eur)
                            .build();
                }
            }
        }
        return sendMessage;
    }
}

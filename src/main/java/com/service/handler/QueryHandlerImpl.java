package com.service.handler;

import com.dao.CourseDAO;
import com.dao.СurrenciesDAO;
import com.service.FindOutTheExchangeRateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class QueryHandlerImpl implements QueryHandler {
    FindOutTheExchangeRateImpl exchangeRate;

    @Autowired
    public void setExchangeRate(FindOutTheExchangeRateImpl exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public SendMessage handler(Update update) {

        Message message = update.getCallbackQuery().getMessage();
        String chatId = String.valueOf(message.getChatId());
        String currency = update.getCallbackQuery().getData();

        CourseDAO course = exchangeRate.getCourse();
        СurrenciesDAO currencies = course.getValute();
        String date = course.getDate();
        String answer = "";


        switch (currency) {
            case "usd":
                Double usd = currencies.getUsd().getValue();
                answer = date + "\nUSD: " + usd;
                break;
            case "eur":
                Double eur = currencies.getEur().getValue();
                answer = date + "\nEUR: " + eur;
                break;
            case "gbp":
                Double gbp = currencies.getGbp().getValue();
                answer = date + "\nGBP: " + gbp;
                break;
            case "chf":
                Double chf = currencies.getChf().getValue();
                answer = date + "\nCHF: " + chf;
                break;
        }

        return SendMessage.builder()
                .chatId(chatId)
                .text(answer)
                .build();
    }
}

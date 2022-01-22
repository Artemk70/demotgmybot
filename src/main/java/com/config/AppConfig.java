package com.config;

import com.Bot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "telegram")
public class AppConfig {
    private String webHookPath;
    private String userName;
    private String botToken;

    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;

    @Bean
    public Bot bot() {
        DefaultBotOptions defaultBotOptions = new DefaultBotOptions();
        Bot bot = new Bot(defaultBotOptions);
        return bot;
    }
}

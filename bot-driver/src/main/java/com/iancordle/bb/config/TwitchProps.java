package com.iancordle.bb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
@ConfigurationProperties(prefix = "twitch")
public class TwitchProps {
    private String clientId;
    private String clientSecret;
    private String gameId;
    private List<String> titleKeys;
    private List<UUID> streamTags;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<String> getTitleKeys() {
        return titleKeys;
    }

    public void setTitleKeys(List<String> titleKeys) {
        this.titleKeys = titleKeys;
    }

    public List<UUID> getStreamTags() {
        return streamTags;
    }

    public void setStreamTags(List<UUID> streamTags) {
        this.streamTags = streamTags;
    }
}

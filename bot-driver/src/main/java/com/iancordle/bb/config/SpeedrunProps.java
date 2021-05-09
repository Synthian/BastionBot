package com.iancordle.bb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "speedrun")
public class SpeedrunProps {
    private String gameId;
    private List<String> newGameVariables;
    private List<String> newGamePlusVariables;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<String> getNewGameVariables() {
        return newGameVariables;
    }

    public void setNewGameVariables(List<String> newGameVariables) {
        this.newGameVariables = newGameVariables;
    }

    public List<String> getNewGamePlusVariables() {
        return newGamePlusVariables;
    }

    public void setNewGamePlusVariables(List<String> newGamePlusVariables) {
        this.newGamePlusVariables = newGamePlusVariables;
    }
}

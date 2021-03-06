package com.iancordle.bb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "discord")
public class DiscordProps {

    private String token;
    private Status status;
    private Server server;
    private String runIcon;
    private List<String> echoedEmojis;
    private Map<String, String> staticCommands;

    public Status getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public Server getServer() {
        return server;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public String getRunIcon() {
        return runIcon;
    }

    public void setRunIcon(String runIcon) {
        this.runIcon = runIcon;
    }

    public List<String> getEchoedEmojis() {
        return echoedEmojis;
    }

    public void setEchoedEmojis(List<String> echoedEmojis) {
        this.echoedEmojis = echoedEmojis;
    }

    public Map<String, String> getStaticCommands() {
        return staticCommands;
    }

    public void setStaticCommands(Map<String, String> staticCommands) {
        this.staticCommands = staticCommands;
    }

    public static class Status {
        private String game;

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }
    }

    public static class Server {
        private String id;
        private String runsChannel;
        private String streamsChannel;
        private String messageChannel;

        public String getId() {
            return id;
        }

        public String getRunsChannel() {
            return runsChannel;
        }

        public String getStreamsChannel() {
            return streamsChannel;
        }

        public String getMessageChannel() {
            return messageChannel;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setRunsChannel(String runsChannel) {
            this.runsChannel = runsChannel;
        }

        public void setStreamsChannel(String streamsChannel) {
            this.streamsChannel = streamsChannel;
        }

        public void setMessageChannel(String messageChannel) {
            this.messageChannel = messageChannel;
        }
    }

}

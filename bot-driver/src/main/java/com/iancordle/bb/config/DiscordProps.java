package com.iancordle.bb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "discord")
public class DiscordProps {

    private String token;
    private Status status;
    private Server server;
    private String runIcon;

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

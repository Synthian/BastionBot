package com.iancordle.bb.service;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.TwitchHelix;
import com.github.twitch4j.helix.domain.Stream;
import com.github.twitch4j.helix.domain.User;
import com.iancordle.bb.config.TwitchProps;
import com.iancordle.bb.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TwitchService {
    private final TwitchClient twitchClient;
    private final TwitchHelix helix;
    private final TwitchProps twitchProps;

    @Autowired
    public TwitchService(TwitchProps twitchProps) {
        this.twitchProps = twitchProps;
        twitchClient = TwitchClientBuilder.builder()
                .withClientId(twitchProps.getClientId())
                .withClientSecret(twitchProps.getClientSecret())
                .withEnableHelix(true)
                .build();
        helix = twitchClient.getHelix();
    }

    public List<Stream> getStreams() {
        return helix.getStreams(null,
                null,
                null,
                100,
                null,
                Collections.singletonList(twitchProps.getGameId()),
                null,
                null,
                null).execute().getStreams();
    }

    public User getUser(String id) throws UserNotFoundException {
        List<User> users = helix.getUsers(null, Collections.singletonList(id), null).execute().getUsers();
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        } else {
            throw new UserNotFoundException(id);
        }
    }
}

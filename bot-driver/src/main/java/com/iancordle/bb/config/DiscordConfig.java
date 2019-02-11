package com.iancordle.bb.config;

import com.iancordle.bb.exceptions.ConfigurationException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
public class DiscordConfig {

    private final DiscordProps discordProps;

    public DiscordConfig(DiscordProps discordProps) {
        this.discordProps = discordProps;
    }

    @Bean
    public JDA jda() throws LoginException, InterruptedException {
        // TODO: handle null values? Default values for configuration, etc.
        return new JDABuilder(AccountType.BOT)
                .setGame(Game.playing(discordProps.getStatus().getGame()))
                .setToken(discordProps.getToken())
                .build()
                .awaitReady();
    }

    @Bean
    public Guild guild(JDA jda) throws ConfigurationException {
        Guild g = jda.getGuildById(discordProps.getServer().getId());
        if (g == null) {
            throw new ConfigurationException("Could not find connected guild with ID " + discordProps.getServer().getId());
        }
        return g;
    }

    @Bean(name = "RunsTextChannel")
    public TextChannel runsTextChannel(Guild guild) throws ConfigurationException {
        return getFirstTextChannelByName(guild, discordProps.getServer().getRunsChannel());
    }

    @Bean(name = "StreamsTextChannel")
    public TextChannel streamsTextChannel(Guild guild) throws ConfigurationException {
        return getFirstTextChannelByName(guild, discordProps.getServer().getStreamsChannel());
    }

    @Bean(name = "MessageTextChannel")
    public TextChannel messageTextChannel(Guild guild) throws ConfigurationException {
        return getFirstTextChannelByName(guild, discordProps.getServer().getMessageChannel());
    }

    private TextChannel getFirstTextChannelByName(Guild g, String n) throws ConfigurationException {
        List<TextChannel> list = g.getTextChannelsByName(n, true);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new ConfigurationException("Could not find text channel " + n + " in selected guild.");
    }
}
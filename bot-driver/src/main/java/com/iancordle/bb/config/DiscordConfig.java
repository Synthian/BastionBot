package com.iancordle.bb.config;

import com.iancordle.bb.exceptions.ConfigurationException;
import com.iancordle.bb.listeners.ReactionListener;
import com.iancordle.bb.listeners.StaticResponseListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
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
        JDABuilder builder = JDABuilder.createDefault(discordProps.getToken());
        if (discordProps.getStatus() != null && discordProps.getStatus().getGame() != null) {
            builder.setActivity(Activity.playing(discordProps.getStatus().getGame()));
        }
        JDA jda = builder.build().awaitReady();
        jda.addEventListener(new ReactionListener(discordProps));
        jda.addEventListener(new StaticResponseListener(discordProps));
        return jda;
    }

    @Bean
    public Guild guild(JDA jda) throws ConfigurationException {
        Guild guild = jda.getGuildById(discordProps.getServer().getId());
        if (guild == null) {
            throw new ConfigurationException("Could not find connected guild with ID " + discordProps.getServer().getId());
        }
        return guild;
    }

    @Bean
    public TextChannel runsTextChannel(Guild guild) throws ConfigurationException {
        return getFirstTextChannelByName(guild, discordProps.getServer().getRunsChannel());
    }

    @Bean
    public TextChannel streamsTextChannel(Guild guild) throws ConfigurationException {
        return getFirstTextChannelByName(guild, discordProps.getServer().getStreamsChannel());
    }

    @Bean
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

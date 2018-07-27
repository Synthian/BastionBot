package ic.bastionbot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.List;

public class Discord {
    private JDA jda;
    private static final Logger logger = LoggerFactory.getLogger(Discord.class.getName());


    public Discord(String token) {
        try
        {
            jda = new JDABuilder(AccountType.BOT)
                    .setGame(Game.playing("Bastion"))
                    .setToken(token)
                    .buildBlocking();
        }
        catch (LoginException e)
        {
            logger.error("JDA LoginException");
            logger.error(e.getMessage());
        }
        catch (InterruptedException e)
        {
            logger.error("JDA LoginException");
            logger.error(e.getMessage());
        }
    }

    public void addEventListener(ListenerAdapter listener) {
        jda.addEventListener(listener);
    }

    public long getGuildID(String name) {
        List<Guild> guilds = jda.getGuilds();

        for (Guild g : guilds) {
            if (g.getName().equals(name)) {
                return g.getIdLong();
            }
        }

        logger.info("Could not find guild with name" + name);
        return 0L;
    }

    public List<TextChannel> getTextChannelsByName(Guild g, String name) {
        List<TextChannel> channels = g.getTextChannelsByName(name, true);
        return channels;
    }

    public Guild getGuildById (long id) {
        return jda.getGuildById(id);
    }

    public Message sendMessageBlocking(Message m, TextChannel tc) {
        return tc.sendMessage(m).complete();
    }

    public void queueSendMessage(Message m, TextChannel tc) {
        tc.sendMessage(m).queue();
    }

    public void queueEditMessage(Message oldm, Message newm) {
        oldm.editMessage(newm).queue();
    }

    public void queueSendEmbed(MessageEmbed me, TextChannel tc) {
        tc.sendMessage(me).queue();
    }

    public void disconnect() {
        jda.shutdown();
    }

}

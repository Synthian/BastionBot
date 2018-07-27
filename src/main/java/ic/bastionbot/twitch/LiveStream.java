package ic.bastionbot.twitch;

import net.dv8tion.jda.core.entities.Message;

public class LiveStream {

    private Message message;
    private long id;

    public LiveStream(long id, Message message) {
        this.id = id;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public Message getMessage() {
        return message;
    }
}


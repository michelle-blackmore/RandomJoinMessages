package net.fauxfemale.randomjoinmessages;

public enum MessageType {
    JOIN ("join"),
    WELCOME ("welcome");

    public final String string;

    MessageType (String messageType) {
        this.string = messageType;
    }
}

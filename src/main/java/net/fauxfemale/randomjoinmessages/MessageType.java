package net.fauxfemale.randomjoinmessages;

public enum MessageType {
    JOIN("join"),
    WELCOME("welcome"),
    QUIT("leave");

    public final String string;

    MessageType(String messageType) {
        this.string = messageType;
    }
}

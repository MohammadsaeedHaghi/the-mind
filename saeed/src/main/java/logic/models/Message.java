package logic.models;

import logic.models.player.Player;

public class Message {
    private Player sender;
    private Player receiver;
    private String text;


    public Message(Player sender, Player receiver, String text) {
        this.sender = sender;
        this.receiver = receiver;

        if (text.equals(":|") || text.equals(":)") || text.equals(":(")) {
            this.text = text;
        } else {
            this.text = "";
        }

    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", text='" + text + '\'' +
                '}';
    }
}

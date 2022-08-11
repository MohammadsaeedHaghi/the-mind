package logic;

import logic.models.card.Card;
import logic.models.player.Player;

import java.util.List;

public class Game {
    private List<Player> players;
    private List<Card> cards;
    private int level;
    private int hearts;
    private int ninjas;
    private boolean started;

    public void nextRound(){

    }

    public void setCards(){

    }

    public Boolean checkCard(){
        //check that is there smaller card in others players hand

        return null;
    }

    public void useNinja(){

    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}

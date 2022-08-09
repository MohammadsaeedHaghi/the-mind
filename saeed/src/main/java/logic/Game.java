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

    public void nextRound(){

    }
    public void setCards(){

    }

    public Boolean checkCard(int cardNum){
        //check that is there smaller card in others players hand

        for (Player player:
             players) {
            if (player.smallestCardNum().getNumber()<cardNum) {
                return false;
            }
        }
        return true;
    }

    public void useNinja(){

    }

}

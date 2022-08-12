package logic;

import logic.models.card.Card;
import logic.models.player.Player;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private List<Player> players;
    private List<Card> cards;
    private int level;
    private int hearts;
    private int ninjas;
    private boolean started;
    private GameStatus gameStatus;


    public Game() {
        players = new LinkedList<>();
    }

    public void nextRound() {
            if (level == 12) {
                gameStatus = GameStatus.Win;
                //TODO
            }
            if (level == 2 || level ==5 || level == 8) {
                hearts++;
            }
            if (level == 1 || level ==4 || level == 7) {
                ninjas++;
            }
            level++;
            //TODO
    }

    public void setCards(Card card, Player player) {
        boolean checkCard = checkCard(card.getNumber());
        if (!checkCard) {
            loseHeart();
            removeSmallerCards(card);
        } else {
            removeCard(card, player);
            if (cards.size() == 0) {
                nextRound();
            }
        }

    }

    private void removeSmallerCards(Card card) {
        for (Player player :
                players) {
            for (Card playerCard :
                    player.getCards()) {
                if (playerCard.getNumber() <= card.getNumber()) {
                    removeCard(playerCard, player);
                }
            }
        }
    }

    private void removeCard(Card card, Player player) {
        cards.remove(card);
        player.removeCard(card);
    }

    public Boolean checkCard(int cardNum) {//check that is there smaller card in others players hand
        for (Player player :
                players) {
            if (player.smallestCardNum().getNumber() < cardNum) {
                return false;
            }
        }
        return true;
    }

    public void useNinja() {

    }

    public void addPlayer(Player player){
        this.players.add(player);
        if(players.size() == 1){
            player.setHost(true);
        }
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

    public void loseHeart() {
        if (hearts != 0) {
            hearts = hearts - 1;
        }
        if (hearts == 0) {
            gameStatus = GameStatus.Lose;
            //TODO
        }
    } //TODO
}

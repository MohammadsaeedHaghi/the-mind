package logic.models.player;

import logic.Game;
import logic.models.card.Card;

import java.util.List;

public abstract class Player {
    private List<Card> cards;
    private Game game;

    private boolean host;
    private String name;


    public Player(Game game, String name) {
        this.game = game;
        this.name = name;
        host = false;
        if(game.getPlayers().size()==0)
            host = true;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public void useCard(Card card) {
            game.setCards(smallestCardNum(), this);
    }



    public Card smallestCardNum() {
        int min = cards.get(0).getNumber();
        int index = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (min > cards.get(i).getNumber()) {
                min = cards.get(i).getNumber();
                index = i;
            }
        }
        return cards.get(index);
    }


    public void removeCard(Card card) {
        cards.remove(card);
    }

    public List<Card> getCards() {
        return cards;
    }


}

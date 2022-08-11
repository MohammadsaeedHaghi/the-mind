package logic.models.player;

import logic.Game;
import logic.models.card.Card;

import java.util.List;

public abstract class Player {
    private List<Card> cards;
    private Game game;


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

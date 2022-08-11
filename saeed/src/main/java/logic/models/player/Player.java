package logic.models.player;

import logic.Game;
import logic.models.card.Card;

import javax.xml.catalog.Catalog;
import java.util.List;

public abstract class Player{
    private List<Card> cards;





    public void useCard(Card card){
        cards.remove(card);
    }



    public Card smallestCardNum(){
            int min = cards.get(0).getNumber();
            int index = 0;
            for (int i=0; i< cards.size(); i++) {
                if (min > cards.get(i).getNumber()) {
                    min = cards.get(i).getNumber();
                    index = 0;
                }
            }
            return cards.get(index);
    }
}

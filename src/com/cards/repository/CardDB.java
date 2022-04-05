package com.cards.repository;

import java.util.ArrayList;
import java.util.List;
import com.cards.entitty.Card;

public class CardDB {
    private final List<Card> cards = new ArrayList<>();

    public int getMaxNumError() {
        int max = 0;
        if(cards.size()>0) {
            for (Card card : cards) {
                if (card.getError() > max) {
                    max = card.getError();
                }
            }
        }
        return max;
    }

    public void remove(Card card) {
        cards.remove(card);
    }

    public void remove(int i) {
        cards.remove(i);
    }

    public String getTermWithIndex(int i) {
        return cards.get(i).getTerm();
    }

    public String getDefinitionWithIndex(int i) {
        return cards.get(i).getDefinition();
    }

    public void add(Card card) {
        cards.add(card);
    }
    
    public void add(int i, Card card) {
        cards.add(i, card);
    }

    public List<Card> getCards() {
        return cards;
    }
}

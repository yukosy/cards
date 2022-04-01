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

    public List<Card> getCards() {
        return cards;
    }
}

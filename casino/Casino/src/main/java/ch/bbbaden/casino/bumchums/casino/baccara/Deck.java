/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.baccara;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author lucae
 */
public class Deck {

    private ArrayList<Card> cardDeck;

    //Constructor
    public Deck() {
        this.cardDeck = new ArrayList<Card>();
    }

    /**
     * Creates deck with 6x52 cards
     */
    public void generateDeck() {
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 52; i++) {
                Suit suit;

                //Picks a suit
                switch ((int) Math.floor(i / 13)) {
                    case 0:
                        suit = Suit.SPADE;
                        break;
                    case 1:
                        suit = Suit.HEART;
                        break;
                    case 2:
                        suit = Suit.CLUB;
                        break;
                    case 3:
                        suit = Suit.DIAMOND;
                        break;
                    default:
                        suit = Suit.SPADE;
                }

                //Adds a card with suit and Value to the deck
                cardDeck.add(new Card(suit, (i % 13) + 1));
            }
        }
        //Shuffles the deck once it got generated
        Collections.shuffle(cardDeck);
    }

    /**
     * returns the first card and removes it from the list
     * @return card
     */
    public Card drawCard() {
        Card card = cardDeck.get(0);
        cardDeck.remove(0);
        return card;
    }

    //Getter
    public int cardsLeft(){
        return cardDeck.size();
    }
}

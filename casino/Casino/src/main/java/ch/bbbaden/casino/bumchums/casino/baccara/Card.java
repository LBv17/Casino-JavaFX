/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.baccara;

/**
 *
 * @author lucae
 */
public class Card {
	private Suit suit;
	private int faceValue, pointValue;
        
        //Constructor
	public Card(Suit suit, int faceValue) {
		this.suit = suit;
		this.faceValue = faceValue;

		//pointValue = points that each card gives according to the baccarat rules
		if (faceValue > 9)
			this.pointValue = 0;
		else
			this.pointValue = faceValue;
	}
        
        //Getter
	public Suit getSuit() {
		return this.suit;
	}
	public int getFaceValue() {
		return this.faceValue;
	}
	public int getPointValue() {
		return this.pointValue;
	}
}

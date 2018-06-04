/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.baccara;

import javafx.scene.image.Image;

/**
 *
 * @author lucae
 */
public class Game {

    private Bank bank = new Bank();
    private Deck deck = new Deck();
    private String betType;
    private boolean betPlaced, tie, thirdNeeded, dealerThird, playerThird;
    private int playerPoints, dealerPoints, betAmount, profit;

    public void startGame() {
        deck.generateDeck();
    }

    /**
     * places bet
     * @param intBet
     * @param type 
     */
    public void placeBet(int intBet, String type) {
        this.betAmount = intBet;
        bank.placeBet(betAmount);
        betType = type;
        betPlaced = true;
    }

    /**
     * Draws a Card, saves the value points of the card, gets the image from the files and returns it
     * @return image
     */
    public Image drawPlayerCard() {
        Card card = deck.drawCard();
        Image image = new Image(getClass().getResourceAsStream("/images/baccara/" + card.getSuit().toString().toLowerCase() + "s-" + card.getFaceValue() + ".jpg"));
        playerPoints += card.getPointValue();
        return image;
    }

    /**
     * Draws a Card, saves the value points of the card, gets the image from the files and returns it
     * @return Image
     */
    public Image drawDealerCard() {
        Card card = deck.drawCard();
        Image image = new Image(getClass().getResourceAsStream("/images/baccara/" + card.getSuit().toString().toLowerCase() + "s-" + card.getFaceValue() + ".jpg"));
        dealerPoints += card.getPointValue();
        return image;
    }
    
    /**
     * checks if the game is won or lost
     * @param a
     * @param b
     * @return win (boolean)
     */
    public boolean checkWin(int a, int b) {
        //Checks if you won with your bet type
        if (betType.equals("Tie")) {
            tie = (a == b);
            return (a == b);
        } else if (betType.equals("Dealer")) {
            tie = (a == b);
            return (a < b);
        } else if (betType.equals("Player")) {
            tie = (a == b);
            return (a > b);
        } else {
            return false;
        }
    }

    /**
     * calculates and returns profit
     * @param win
     * @return 
     */
    public int getProfit(boolean win) {
        if (win) {
            if (tie) {
                //Tie payouts of 1:9 or 1:8 are normal
                bank.addBalance(betAmount * 9, betAmount * 8);
                profit = betAmount*8;
                return betAmount * 9;
            } else {
                bank.addBalance(betAmount * 2, betAmount);
                profit = betAmount;
                return betAmount * 2;
            }
        } else {
            if (tie) {
                //Returns the set bet
                bank.returnBalance(betAmount);
                profit = 0;
                return 0;
            } else {
                bank.addLoss(betAmount);
                profit = -betAmount;
                return betAmount;
            }
        }
    }
    
    /**
     * resets all the variables
     */
    public void reset() {
        //resets the points so a second round can be played
        betPlaced = false;
        thirdNeeded = true;
        dealerThird = false;
        playerThird = false;
        playerPoints = 0;
        dealerPoints = 0;
    }
    
    public void end(){
        bank.update(profit);
    }

    public void shuffle() {
        deck.generateDeck();
    }

    //Setter
    public void setDealerThird(boolean bool) {
        this.dealerThird = bool;
    }

    public void setPlayerThird(boolean bool) {
        this.playerThird = bool;
    }

    public void setThirdNeeded(boolean bool) {
        this.thirdNeeded = bool;
    }

    //Getter
    public int getCardsLeft() {
        return deck.cardsLeft();
    }

    public boolean getThirdNeeded() {
        return this.thirdNeeded;
    }

    public boolean getPlayerThird() {
        return this.playerThird;
    }

    public boolean getDealerThird() {
        return this.dealerThird;
    }

    public boolean getBetPlaced() {
        return this.betPlaced;
    }

    public boolean getBetValid(int bet) {
        return (bank.getBalance() >= bet);
    }

    public String getBetType() {
        return this.betType;
    }

    public int getBetAmount() {
        return this.betAmount;
    }

    public int getPlayerPoints() {
        return this.playerPoints;
    }

    public int getDealerPoints() {
        return this.dealerPoints;
    }
    
    //Balance is saved locally so the client connects to the database only once per round
    public int getBalance() {
        return bank.getBalance();
    }
    //Balance from the database
    public int getDbBalance(){
        return bank.getDbBalance();
    }

    public boolean getTie() {
        return this.tie;
    }
}

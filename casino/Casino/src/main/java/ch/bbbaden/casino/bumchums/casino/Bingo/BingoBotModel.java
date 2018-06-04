/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Bingo;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Alex
 */
public class BingoBotModel {

    private final int[][] cardNumber = new int[5][5];
    private final int[][] coveredNumbers = new int[5][5];
    private final boolean[] coveredNumberArray = new boolean[25];
    private final ArrayList<Integer> rndNumberArray = new ArrayList<>();
    private boolean win = false;
    private String botName = "";
    private String gameResponse = "";

    public BingoBotModel(String botName) {
        this.botName = botName;
        generateCardNumbers();
    }

    public String getBotName() {
        return botName;
    }

    public void setGameResponse(String gameResponse) {
        this.gameResponse = gameResponse;
    }

    public ArrayList getCardNumber() {
        ArrayList<Integer> cardNumberArray = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 5; y++) {
                cardNumberArray.add(cardNumber[i][y]);
                counter++;
            }
        }
        return cardNumberArray;
    }

    private void generateCardNumbers() {
        for (int i = 1; i < 75; i++) {
            rndNumberArray.add(i);
        }
        Collections.shuffle(rndNumberArray);
        int g = 0;
        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 5; y++) {
                cardNumber[i][y] = rndNumberArray.get(g);
                g++;
            }
        }
    }

    public int[][] getCoveredNumbers() {
        return coveredNumbers;
    }

    public boolean[] getCoveredNumberArray() {
        return coveredNumberArray;
    }

    //get game response
    public String getGameResponse() {
        return gameResponse;
    }

    //get win 
    public boolean isWin() {
        return win;
    }

    public boolean newNumber(int newBingoNum) {
        boolean numberAppeared = false;
        int a = 0;
        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 5; y++) {
                if (cardNumber[i][y] == newBingoNum) {
                    numberAppeared = true;
                    coveredNumbers[i][y] = newBingoNum;
                    coveredNumberArray[a] = true;
                    bingoLegitimacy(i, y);

                }
                a++;
            }
        }
        return numberAppeared;
    }

    private boolean checkAcross(int xNumber) {

        boolean noNumberFound = false;

        for (int y = 0; y < 5; y++) {

            if (coveredNumbers[xNumber][y] == 0) {
                noNumberFound = true;
            }
        }
        if (!noNumberFound) {
            return true;
        } else {
            return false;
        }

    }

    private boolean checkDown(int yNumber) {

        boolean noNumberFound = false;

        for (int i = 0; i < 5; i++) {

            if (coveredNumbers[i][yNumber] == 0) {
                noNumberFound = true;
            }
        }
        if (!noNumberFound) {
            return true;
        } else {
            return false;
        }
    }

    public void bingoLegitimacy(int i, int y) {

        String output = "";
        boolean across = checkAcross(i);
        boolean down = checkDown(y);
        //if across/ down / diagonally is true; bot gets bingo
        if (across || down) {
            win = true;

            if (across == true) {
                output = botName + " has a bingo going across the card!";
                System.out.println(botName + " has a bingo going across the card!");

            }
            if (down == true) {
                output = botName + " has a bingo going down the card!";
                System.out.println(botName + " has a bingo going down the card!");
            }

        } else {

        }
    }

}

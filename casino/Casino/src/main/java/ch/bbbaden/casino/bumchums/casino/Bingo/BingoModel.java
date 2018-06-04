/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Bingo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Alex
 */
public class BingoModel {

    private final Random randomizeCard = new Random();
    String output = "";

    private ArrayList<Integer> cardNumbers = new ArrayList<>();
    private ArrayList<Integer> bingoNumbers = new ArrayList<>();
    private final int[][] clickedNumbers = new int[5][5];

    //Generate a number between 1 and 75 for the random bingo number
    public int createBingoNumber() {
        int bingoNumber = 0;
        boolean duplicateBingoNumber = false;

        //Check size of arrayList
        if (bingoNumbers.size() == 76) {
            //Clear if list is full
            bingoNumbers.clear();
        }

        do {
            duplicateBingoNumber = false;
            bingoNumber = (int) (Math.random() * 75 + 1);
            for (Integer nums : bingoNumbers) {
                if (bingoNumber == nums) {
                    duplicateBingoNumber = true;
                    break;
                }
            }

        } while (duplicateBingoNumber == true);

        //add random bingo number to arrayList
        bingoNumbers.add(bingoNumber);

        return bingoNumber;
    }

    private ArrayList generateCardNumbers() {
        int rndNumber = 0;
        cardNumbers = new ArrayList<>();
        boolean duplicateCardNumber = false; //Initialize duplicateCardNumber

        for (int i = 0; i < 25; i++) {
            do {
                duplicateCardNumber = false;
                rndNumber = (int) (Math.random() * 75 + 1); //Create random number
                for (Integer nums : cardNumbers) {
                    if (rndNumber == nums) { //Check if number was already generated before
                        duplicateCardNumber = true;
                        break;
                    }
                }
            } while (duplicateCardNumber == true); //repeat loop when duplicate is true

            cardNumbers.add(rndNumber); //add random bingo Numbers to arrayList   
        }

        return cardNumbers;
    }

    public ArrayList getCardNumbers() {
        return generateCardNumbers();
    }

    public void fillArray(int index, int fillNumber) {
        int y = 0; //oben nach unten
        int x = 0; //links nach rechts

        for (int q = 0; q < index; q++) {

            //if 5 is reached; do this:
            if ((q + 1) % 5 == 0) {
                y++; //adds to y axis
                x = 0; //resets x axis
            } else {
                x++; //adds to x axis
            }
        }
        clickedNumbers[x][y] = fillNumber;

    }

//    public void returnArray() {
//
//        for (int i = 0; i < 5; i++) {
//            System.out.println("");
//            for (int y = 0; y < 5; y++) {
//                System.out.print(clickedNumbers[y][i]);
//            }
//        }
//    }
    //Checks for bingo across
    private int checkAcross() {

        boolean zeroField = false;

        //For loop in for loop:
        //2-Dimensional array to check every position
        for (int i = 0; i < 5; i++) {
            zeroField = false;

            for (int y = 0; y < 5; y++) {
                //Check if the position is empty
                if (clickedNumbers[y][i] == 0) {
                    zeroField = true;
                }
            } //When there is a number in the whole row "i":
            if (zeroField == false) {
                return i;
            }
        }
        return -1;
    }

    //Checks for bingo downwards
    private int checkDown() {

        boolean zeroField = false;

        //For loop in for loop:
        //2-Dimensional array to check every position
        for (int i = 0; i < 5; i++) {
            zeroField = false;

            for (int y = 0; y < 5; y++) {
                //Check if the position is empty
                if (clickedNumbers[i][y] == 0) {
                    zeroField = true;
                }
            } //When there is a number in the whole row "i":
            if (zeroField == false) {
                return i;
            }
        }
        return -1;
    }

    public boolean bingoLegitimacy() {

        boolean win = true;
        int across = checkAcross();
        int down = checkDown();

        if ((across + down) > -2) {

            win = true;
            if (across > -1) {
                for (int i = 0; i < 5; i++) {
                    //Checks if the clicked number is NOT in the bingonumber array
                    if (!bingoNumbers.contains(clickedNumbers[i][across])) {
//                        System.out.println("no bingo across!" + clickedNumbers[i][across]);
                        System.out.println("no bingo across");
                        win = false;
                    }
                }
                if (win) {
//                    System.out.println("correct bingo across");
                    System.out.println("correct bingo across");
                    return true;
                }
            }
            if (down > -1) {
                win = true;
                for (int i = 0; i < 5; i++) {
                    //Checks if the clicked number is NOT in the bingonumber array
                    if (!bingoNumbers.contains(clickedNumbers[down][i])) {
                        System.out.println("no bingo down!");
                        win = false;
                    }
                }
                if (win) {
                    System.out.println("correct bingo down!");
                    return true;
                }

            }
            output = "numbers are not correct";
            System.out.println("numbers are not correct!");
        } else {
            System.out.println("you need a full row!");
        }
        return false;

    }

    public String getOutput() {
        return output;
    }

}

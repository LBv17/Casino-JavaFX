/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.SicBo;

import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author LB
 */
public class SicBo {
        
    private static SicBo sicBo = null;

    private int won = 0;
    private int loss = 0;
    private int bet = 0;
    private final String game = "SicBo";
    private Player player;
    
    private SicBo() {}
    
    public static SicBo getInstance() {
        if (sicBo == null) {
            sicBo = new SicBo();
        }
        return sicBo;
    }

    /**
     * Returns a int array with 3 random numbers for the dice
     * @return 
     */
    public int[] rollDice() {
        int[] randomNums = new int[3];
        Random r = new Random();

        for (int i = 0; i < 3; i++) {
            randomNums[i] = r.nextInt(6) + 1;
        }

        return randomNums;
    }

    /**
     * Takes all the bets and checks if the player won lost or tied
     * @param paneIds
     * @param amountList
     * @param wins
     * @return 
     */
    public int calculateResult(ArrayList<String> paneIds, ArrayList<Integer> amountList, ArrayList<String> wins) {

        boolean triple = false;
        boolean lost = false;
        int dieAmount = 0;
        int result = 0;
        int totalBet = 0;
        
        for (int i = 0; i < paneIds.size(); i++) {

            String s = paneIds.get(i);
            int amount = amountList.get(i);
            totalBet += amount;
            
            System.out.println(s);
            System.out.println(wins.contains(s));

            if (wins.contains(s)) {

                if (s.equals("triplePane")) {
                    triple = true;
                    result += amount * 30;
                } else if (s.contains("triple")) {
                    triple = true;
                    result += amount * 180;
                } else if (s.contains("double")) {
                    result += amount * 11;
                } else if (s.contains("sumTen") || s.contains("sumEleven")) {
                    result += amount * 6;
                } else if (s.contains("sumNine") || s.contains("sumTwelve")) {
                    result += amount * 6;
                } else if (s.contains("sumEight") || s.contains("sumThirteen")) {
                    result += amount * 8;
                } else if (s.contains("sumSeven") || s.contains("sumFourteen")) {
                    result += amount * 12;
                } else if (s.contains("sumSix") || s.contains("sumFifteen")) {
                    result += amount * 18;
                } else if (s.contains("sumFive") || s.contains("sumSixteen")) {
                    result += amount * 20;
                } else if (s.contains("sumFour") || s.contains("sumSeventeen")) {
                    result += amount * 60;
                } else if (s.contains("small") || s.contains("big")) {
                    if (triple) {
                        // lost
                        lost = true;
                    } else {
                        // 1 wins 1
                        result += amount;
                    }
                } else if (s.equals("onePane") || s.equals("twoPane") || s.equals("threePane") || s.equals("fourPane") || s.equals("fivePane") || s.equals("sixPane")) {
                    dieAmount++;
                    // 1:1, 2:1, 3:1
                } else if (s.contains("Pane")) {
                    result += amount * 6;
                } else {
                    //nope
                    System.out.println("nope");
                }

                if (i == paneIds.size() - 1) {
                    if (dieAmount == 1) {
                        result += amount;
                    } else if (dieAmount == 2) {
                        result += amount * 2;
                    } else if (dieAmount == 3) {
                        result += amount * 3;
                    }
                }
            }
        }
      
        this.bet = totalBet;  
        
        if (!lost) {
            
            if (totalBet > result) {
                // Lost
                this.loss = totalBet-result;
            } else if (totalBet < result) {
                // Won
                this.won = result;
            } 
            updateStats();
            return result-totalBet;
            
        } else {
            return 0-totalBet;
        }
       
    }
        
    /**
     * Inserts statistics into db
     */
    private void updateStats() {
        Date date = new Date();
        DBDefaultUtil db = new DBDefaultUtil();
        db.updateStats(player, game, won, loss, bet, date);
    }

    void setPlayer(Player player) {
        this.player = player;
    }
    
}

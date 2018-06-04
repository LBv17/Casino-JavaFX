/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.baccara;

import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author lucae
 */
public class Bank {
    public static Player player;
    private DBDefaultUtil db = new DBDefaultUtil();
    private int balance, bet, won, loss;
    
    /**
     * @return balance from database 
     */
    public int getDbBalance(){
        balance = db.getAccountBalance(player.getEmail());
        return balance;
    }
    /**
     * @return local balance
     */
    public int getBalance(){
        return balance;
    }
    /**
     * places bet
     * @param bet 
     */
    public void placeBet(int bet){
        balance -= bet;
        this.bet = bet;
    }
    
    /**
     * Communicates a loss with the database
     * @param bet 
     */
    public void addLoss(int bet){
        db.updateAccountBalance(player.getEmail(), -bet);
    }
    /**
     * returns balance in case of tie
     * @param bet 
     */
    public void returnBalance(int bet){
        balance += bet;
    }
    /**
     * Communicates profit with database
     * @param bet
     * @param prof 
     */
    public void addBalance(int bet, int prof){
        balance += bet;
        db.updateAccountBalance(player.getEmail(), prof);
    }
    
    /**
     * updates statistics in the database
     * @param prof 
     */
    public void update(int prof){
        if (prof > 0){
            won = prof;
            loss = 0;
        }else{
            won = 0;
            loss = prof;
        }
        Date date = new Date();
        db.updateStats(player, "baccara", won, loss, bet, date);
    }
}

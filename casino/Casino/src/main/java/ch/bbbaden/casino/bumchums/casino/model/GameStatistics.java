/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.model;

import java.util.Date;

/**
 *
 * @author LB
 */
public class GameStatistics {
    
    private String game;
    private String player;
    private int won;
    private int lost;
    private int bet;
    private Date Date;

    public GameStatistics(String game, String player, int won, int lost, int bet, Date Date) {
        this.game = game;
        this.player = player;
        this.won = won;
        this.lost = lost;
        this.bet = bet;
        this.Date = Date;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
    
}

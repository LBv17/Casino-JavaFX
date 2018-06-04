/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.util;

import ch.bbbaden.casino.bumchums.casino.model.GameStatistics;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.model.PlayerStatistics;
import ch.bbbaden.casino.bumchums.casino.model.User;
import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author LB
 */
public interface Databasable {

    public abstract boolean testConnection();
    
    /**
     * Sign up new user
     * @param email
     * @param firstName
     * @param lastName
     * @param password 
     */
    public abstract void addNewUser(String email, String firstName, String lastName, String password);
    
    /**
     * Authenticate user when he signs in
     * 
     * @param email
     * @param password
     * @return 
     */
    public abstract boolean authenticateUser(String email, String password);
    
    /**
     * Get and create user object from email address
     * 
     * @param email
     * @return 
     */
    public abstract User getUser(String email);
    
    /**
     * Returns a Collection of all the players in the database
     * @return 
     */
    public abstract ObservableList<Player> getAllPlayers();
    
    /**
     * Gets the users Account balance
     * @param email
     * @return 
     */
    public abstract int getAccountBalance(String email);
    
    /**
     * Used to update Account Balance
     * @param email
     * @param newBalance 
     */
    public abstract void updateAccountBalance(String email, int balanceUpdate);
    
    /**
     * Used to update user in database
     * 
     * @param user
     * @return 
     */
    public abstract String updateUser(User user);
    
    /** 
     * Used to update the password of the user
     * 
     * @param user
     * @param pwd
     * @return 
     */
    public abstract String updatePassword(User user, String pwd);
    
    /**
     * Used to insert statistics in database for all games
     * @param user
     * @param game
     * @param won
     * @param loss
     * @param bet
     * @param date 
     */
    public abstract void updateStats(User user, String game, int won, int loss, int bet, Date date);
    
    /**
     * Gets the games statistics for the administrator
     * @param game
     * @param from
     * @param to
     * @return 
     */
    public abstract ObservableList<GameStatistics> getGameStats(String game, Date from, Date to);
    
    /**
     * Gets the player statistics for the administrator
     * @param email
     * @param game
     * @param from
     * @param to
     * @return 
     */
    public abstract ObservableList<PlayerStatistics> getPlayerStats(String email, String game, Date from, Date to);

}

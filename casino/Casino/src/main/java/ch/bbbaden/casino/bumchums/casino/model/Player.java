/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.model;

/**
 *
 * @author LB
 */
public class Player extends User {

    public Player(String fName, String lName, String email, boolean admin, int id) {
        super(fName, lName, email, admin, id);
    }
}

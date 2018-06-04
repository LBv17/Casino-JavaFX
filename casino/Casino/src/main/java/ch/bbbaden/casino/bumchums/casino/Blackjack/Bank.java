/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Blackjack;

/**
 *
 * @author Startklar
 */
public class Bank {

    static double credit = 0;
    static FXMLViewController con;
    //Setter con.
    public void setCon(FXMLViewController con) {
        this.con = con;
    }
    // Getter credit.
    public static double getCredit() {
        
        return Bank.credit;
    }
    //Platzhalter
    public void calculate(double finalBeta) {

    }

}

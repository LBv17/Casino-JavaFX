/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Blackjack;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author Startklar
 */
public class CalculatorGroupier {
    private boolean insurance = false;
    private Label label5;
    int resultBank = 0;
    int count = 0;
    FXMLViewController con;

    //Setter con.

    public void setCon(FXMLViewController con) {
        this.con = con;
    }

    
    //Setter resultBank.
    public void setResultBank(int resultBank) {
        this.resultBank = resultBank;
    }

    public CalculatorGroupier() {
    }
    //Hier wird das Resultat des Groupiers berechnet.
    public ArrayList<Card> calculates(Integer[] intercards, int GroupCount, boolean holdBank, ArrayList<Card> cards, int counter) {
        if (con.getGroupCount() == 0) {
            resultBank = con.getFirstIntercard();
        } else if (GroupCount == 1) {
            resultBank = intercards[0] + intercards[1];
        } else if (GroupCount == 2) {
            resultBank = intercards[0] + intercards[1] + intercards[2];
        } else if (GroupCount == 3) {
            resultBank = intercards[0] + intercards[1] + intercards[2] + intercards[3];
        } else if (GroupCount == 4) {
            resultBank = intercards[0] + intercards[1] + intercards[2] + intercards[3] + intercards[4];
        } else if (GroupCount == 5) {
            resultBank = intercards[0] + intercards[1] + intercards[2] + intercards[3] + intercards[4] + intercards[5];
        } else if (GroupCount == 6) {
            resultBank = intercards[0] + intercards[1] + intercards[2] + intercards[3] + intercards[4] + intercards[5] + intercards[6];
        } else if (GroupCount == 7) {
            resultBank = intercards[0] + intercards[1] + intercards[2] + intercards[3] + intercards[4] + intercards[5] + intercards[6] + intercards[7];
        } else if (GroupCount == 8) {
            resultBank = intercards[0] + intercards[1] + intercards[2] + intercards[3] + intercards[4] + intercards[5] + intercards[6] + intercards[7] + intercards[8];
        } else if (GroupCount == 9) {
            resultBank = intercards[0] + intercards[1] + intercards[2] + intercards[3] + intercards[4] + intercards[5] + intercards[6] + intercards[7] + intercards[8] + intercards[9];
        }
        
        if (resultBank > 21) {
            if (controlAce(intercards[0]) || controlAce(intercards[1]) || controlAce(intercards[2]) || controlAce(intercards[3]) || controlAce(intercards[4]) || controlAce(intercards[5]) || controlAce(intercards[6]) || controlAce(intercards[7]) || controlAce(intercards[8]) || controlAce(intercards[9])) {
                resultBank -= 10;
            }
           
           
        }
        this.con.hold(holdBank, resultBank, count);
        return cards;
    }

    //Hier wird kontrolliert ob die Karte As seinen Wert wechseln soll oder nicht.
    private boolean controlAce(int cards) {
        if (cards == 11) {
            return true;
        }
        return false;
    }
    
    //Getter resultBank.

    public int getResultBank() {
        return resultBank;
    }
    //Getter count.
    public int getCount() {
        return count;
    }
}

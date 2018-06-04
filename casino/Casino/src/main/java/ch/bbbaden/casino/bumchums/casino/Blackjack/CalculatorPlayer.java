/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Blackjack;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author Startklar
 */
public class CalculatorPlayer {

    boolean holdBank;
    private Label label6;
    public static CalculatorPlayer instance;
    static int result;
    boolean stop = false;
    FXMLViewController con;
    boolean hold = false;
    int resultGroup = 0;
    static boolean won = false;
    static boolean lost = false;

    public CalculatorPlayer() {
        instance = this;
    }

    //Setter con.
    public void setCon(FXMLViewController con) {
        this.con = con;
    }

    //Berechnung des Resultates des Spielers.
    public ArrayList<Card> calculate(Integer[] ncards, int count, ArrayList<Card> cards) {
        if (count == 1) {
            result = ncards[0] + ncards[1];

        } else if (count == 2) {
            result = ncards[0] + ncards[1] + ncards[2];

        } else if (count == 3) {
            result = ncards[0] + ncards[1] + ncards[2] + ncards[3];

        } else if (count == 4) {
            result = ncards[0] + ncards[1] + ncards[2] + ncards[3] + ncards[4];

        } else if (count == 5) {
            result = ncards[0] + ncards[1] + ncards[2] + ncards[3] + ncards[4] + ncards[5];

        } else if (count == 6) {
            result = ncards[0] + ncards[1] + ncards[2] + ncards[3] + ncards[4] + ncards[5] + ncards[6];

        } else if (count == 7) {
            result = ncards[0] + ncards[1] + ncards[2] + ncards[3] + ncards[4] + ncards[5] + ncards[6] + ncards[7];

        } else if (count == 8) {
            result = ncards[0] + ncards[1] + ncards[2] + ncards[3] + ncards[4] + ncards[5] + ncards[6] + ncards[7] + ncards[8];

        } else if (count == 9) {
            result = ncards[0] + ncards[1] + ncards[2] + ncards[3] + ncards[4] + ncards[5] + ncards[6] + ncards[7] + ncards[8] + ncards[9];

        }

        if (result > 21) {
            if (controlAce(ncards[0]) || controlAce(ncards[1]) || controlAce(ncards[2]) || controlAce(ncards[3]) || controlAce(ncards[4]) || controlAce(ncards[5]) || controlAce(ncards[6]) || controlAce(ncards[7]) || controlAce(ncards[8]) || controlAce(ncards[9])) {
                result -= 10;
                con.syncCalculator(result);
            }
            if (result > 21 && stop == false) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(400);
//                            JOptionPane.showMessageDialog(null, "You lost!");
                            stop = true;
                            lost = true;
                            con.showLabelLost(lost);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CalculatorPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }

        }

        this.con.hold(holdBank, 0, result);

        return cards;
    }

    //Die Änderung des Wertes der Karte As.
    private boolean controlAce(int card) {
        if (card == 11) {
            return true;
        }
        return false;
    }

    //VHier wird herausgefunden, wer gewonnen hat.
    public void MasterMind(int resultGroup, boolean stop, boolean hold) {
        if (result < resultGroup) {
            if (resultGroup > 21 && stop == false) {
                stop = true;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(400);
//                            JOptionPane.showMessageDialog(null, "You won!");
                            won = true;
                            con.showLabelWon(won);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CalculatorPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            } else if (resultGroup == 21 && stop == false || resultGroup < 21 && stop == false) {
                stop = true;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(400);
//                            JOptionPane.showMessageDialog(null, "You lost!");
                            lost = true;
                            con.showLabelLost(lost);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CalculatorPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }
        } else if (result > resultGroup) {
            if (result > 21 && stop == false) {
                stop = true;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(400);
//                            JOptionPane.showMessageDialog(null, "You lost!");
                            lost = true;
                            con.showLabelLost(lost);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CalculatorPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            } else if (result < 21 && stop == false) {
                stop = true;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(400);
//                            JOptionPane.showMessageDialog(null, "You won!");
                            won = true;
                            con.showLabelWon(won);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CalculatorPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            } else if (result == 21 && stop == false) {
                stop = true;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(400);
                            won = true;
                            con.shout();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CalculatorPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
            }
            
        } else if (result == resultGroup && hold == true) {
            stop = true;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(400);
//                        JOptionPane.showMessageDialog(null, "It's a tie!");
                        con.showLabelTie();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CalculatorPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        }
    }

    //Hier wird eine Variable weiter gegeben.
    public void forwardTo() {
        con.syncHold(5);
    }

    //Hier wird der boolean stop weitergegeben.
    public boolean isStop() {
        return stop;
    }

    //Getter result.
    public static int getResult() {
        return result;
    }

    //Getter resultGroup
    public int getResultGroup() {
        return resultGroup;
    }

    //Her wird der Boolean won weitergegeben.
    public boolean isWon() {
        return won;
    }

    //Her wird der Boolean lost weitergegeben.
    public static boolean isLost() {
        return lost;
    }

}

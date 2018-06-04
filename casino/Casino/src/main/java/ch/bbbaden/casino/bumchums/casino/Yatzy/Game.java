/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Yatzy;

import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author nico
 */
public class Game {
    //variables get declared
    static int numb, numb2, numb3, numb4, numb5;
    static String die1, die2, die3, die4, die5;
    
    //Random numbers get generated 
    public Game(boolean bl[]) {
        if (bl[0] == false) {
            numb = (int) ((Math.random()) * 6 + 1);
            die1 = String.valueOf(numb);
        }
        if (bl[1] == false) {
            numb2 = (int) ((Math.random()) * 6 + 1);
            die2 = String.valueOf(numb2);
        }
        if (bl[2] == false) {
            numb3 = (int) ((Math.random()) * 6 + 1);
            die3 = String.valueOf(numb3);
        }
        if (bl[3] == false) {
            numb4 = (int) ((Math.random()) * 6 + 1);
            die4 = String.valueOf(numb4);
        }
        if (bl[4] == false) {
            numb5 = (int) ((Math.random()) * 6 + 1);
            die5 = String.valueOf(numb5);
        }
    }
    
//getter for the random numbers
    public String getDie1() {
        return die1;
    }

    public String getDie2() {
        return die2;
    }

    public String getDie3() {
        return die3;
    }

    public String getDie4() {
        return die4;
    }

    public String getDie5() {
        return die5;
    }

    //random numbers get filled into toArray
    public int[] toArray() {
        int[] würfel = {numb, numb2, numb3, numb4, numb5};
        return würfel;
    }

    /**
     * Code is partly copied from
     * https://github.com/bratanon/Yatzy/blob/master/src/Rules.java &
     * https://www.codeproject.com/Articles/8657/A-Simple-Yahtzee-Game in work 
     * with Joel Meier, partly copied code starts from here:
     * collects the eyes of a dice
     * */
    public int summe(int value) {
        int[] würfel = toArray();
        int sum = 0;
        for (int i = 0; i < würfel.length; i++) {
            if (würfel[i] == value) {
                sum += würfel[i];
            }
        }
        return sum;
    }

    public int ones() {
        return summe(1);
    }

    public int twos() {
        return summe(2);
    }

    public int threes() {
        return summe(3);
    }

    public int fours() {
        return summe(4);
    }

    public int fives() {
        return summe(5);
    }

    public int sixes() {
        return summe(6);
    }
    //Three of a kind gets calculated
    public int threeOfKind() {
        int[] würfel = toArray();
        Arrays.sort(würfel);
        if ((würfel[0] == würfel[1]) && (würfel[1] == würfel[2]) && (würfel[2] != würfel[3]) && (würfel[3] != würfel[4])) {
            return würfel[0] + würfel[1] + würfel[2] + würfel[3] + würfel[4];
        } else if ((würfel[1] == würfel[2]) && (würfel[2] == würfel[3]) && (würfel[3] != würfel[4]) && (würfel[4] != würfel[0])) {
            return würfel[0] + würfel[1] + würfel[2] + würfel[3] + würfel[4];
        } else if ((würfel[2] == würfel[3]) && (würfel[3] == würfel[4]) && (würfel[4] != würfel[0]) && (würfel[0] != würfel[1])) {
            return würfel[0] + würfel[1] + würfel[2] + würfel[3] + würfel[4];
        } else {
            return 0;
        }

    }   
    //Four of a kind gets calculated
    public int fourOfKind() {
        int[] würfel = toArray();
        Arrays.sort(würfel);
        if (würfel[0] == würfel[1] && würfel[1] == würfel[2] && würfel[2] == würfel[3] && würfel[3] != würfel[4]) {
            return würfel[0] + würfel[1] + würfel[2] + würfel[3] + würfel[4];
        } else if (würfel[1] == würfel[2] && würfel[2] == würfel[3] && würfel[3] == würfel[4] && würfel[4] != würfel[0]) {
            return würfel[0] + würfel[1] + würfel[2] + würfel[3] + würfel[4];
        } else {
            return 0;
        }
    }
    //Small Straight gets calculated
    public int smallStraight() {
        int[] würfel = toArray();
        Arrays.sort(würfel);
        int Sum = 0;
        for (int j = 0; j < 4; j++) {
            int temp = 0;
            if (würfel[j] == würfel[j + 1]) {
                temp = würfel[j];

                for (int k = j; k < 4; k++) {
                    würfel[k] = würfel[k + 1];
                }

                würfel[4] = temp;
            }
        }
        if (((würfel[0] == 1) && (würfel[1] == 2) && (würfel[2] == 3) && (würfel[3] == 4))
                || ((würfel[0] == 2) && (würfel[1] == 3) && (würfel[2] == 4) && (würfel[3] == 5))
                || ((würfel[0] == 3) && (würfel[1] == 4) && (würfel[2] == 5) && (würfel[3] == 6))
                || ((würfel[1] == 1) && (würfel[2] == 2) && (würfel[3] == 3) && (würfel[4] == 4))
                || ((würfel[1] == 2) && (würfel[2] == 3) && (würfel[3] == 4) && (würfel[4] == 5))
                || ((würfel[1] == 3) && (würfel[2] == 4) && (würfel[3] == 5) && (würfel[4] == 6))) {
            Sum = 30;
        }

        return Sum;

    }
    //Large Straight gets calculated
    public int largeStraight() {

        int[] würfel = toArray();
        Arrays.sort(würfel);
        int Sum = 0;

        if (((würfel[0] == 1)
                && (würfel[1] == 2)
                && (würfel[2] == 3)
                && (würfel[3] == 4)
                && (würfel[4] == 5))
                || ((würfel[0] == 2)
                && (würfel[1] == 3)
                && (würfel[2] == 4)
                && (würfel[3] == 5)
                && (würfel[4] == 6))) {
            Sum = 40;
        }

        return Sum;
    }
    //Full House gets calculated
    public int fullHouse() {
        int[] würfel = toArray();
        Arrays.sort(würfel);
        int Sum = 0;
        if ((((würfel[0] == würfel[1]) && (würfel[1] == würfel[2]))
                && (würfel[3] == würfel[4])
                && (würfel[2] != würfel[3]))
                || ((würfel[0] == würfel[1])
                && ((würfel[2] == würfel[3]) && (würfel[3] == würfel[4]))
                && (würfel[1] != würfel[2]))) {
            Sum = 25;
        }

        return Sum;
    }
    //until here is partly copied
    //Chance gets calculated
    public int chance() {
        int chance = numb + numb2 + numb3 + numb4 + numb5;
        return chance;
    }
    //Yatzy gets calculated
    public int yatzy() {
        int yatzy = 0;
        if (numb == numb2 && numb2 == numb3 && numb3 == numb4 && numb4 == numb5) {
            yatzy = 50;
        }
        return yatzy;
    }
}

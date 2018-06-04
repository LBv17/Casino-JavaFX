/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Yatzy;

import java.util.Arrays;

/**
 *
 * @author nico
 */
public class Opponent {
    //Variables get declared
    private int numb1, numb2, numb3, numb4, numb5;
    public String lab;
    private final boolean blocked[] = new boolean[13];
    int index;
    int biggest = 0;

    public String getLab() {
        return lab;
    }

    public int getBiggest() {
        return biggest;
    }
    //random numbers get genrated
    public void wirf() {
        numb1 = (int) (Math.random() * 6 + 1);
        numb2 = (int) (Math.random() * 6 + 1);
        numb3 = (int) (Math.random() * 6 + 1);
        numb4 = (int) (Math.random() * 6 + 1);
        numb5 = (int) (Math.random() * 6 + 1);
    }
    //random numbers get filled into toArray
    public int[] toArray() {
        int[] würfel = {numb1, numb2, numb3, numb4, numb5};
        return würfel;
    }
   //every method gets checked and the highest possible value gets returned.
    public int check() {
        wirf();
        for (int a : toArray()) {
            System.out.print(a + " ");
        }
        

        if (blocked[0] == false) {
            biggest = ones();
            lab = "Ones";

        }
        if (blocked[1] == false) {
            if (biggest < twos()) {
                biggest = twos();
                lab = "Twos";
                index = 1;
            }
        }
        if (blocked[2] == false) {
            if (biggest < threes()) {
                biggest = threes();
                lab = "Threes";
                index = 2;
            }
        }
        if (blocked[3] == false) {
            if (biggest < fours()) {
                biggest = fours();
                lab = "Fours";
                index = 3;
            }
        }
        if (blocked[4] == false) {
            if (biggest < fives()) {
                biggest = fives();
                lab = "Fives";
                index = 4;
            }
        }
        if (blocked[5] == false) {
            if (biggest < sixes()) {
                biggest = sixes();
                lab = "Sixes";
                index = 5;
            }
        }
        if (blocked[6] == false) {
            if (biggest < threeOfKind()) {
                biggest = threeOfKind();
                lab = "ThreeOfKind";
                index = 6;
            }
        }
        if (blocked[7] == false) {
            if (biggest < fourOfKind()) {
                biggest = fourOfKind();
                lab = "FourOfKind";;
                index = 7;
            }
        }
        if (blocked[8] == false) {
            if (biggest < fullHouse()) {
                biggest = fullHouse();
                lab = "FullHouse";
                index = 8;
            }
        }
        if (blocked[9] == false) {
            if (biggest < smallStraight()) {
                biggest = smallStraight();
                lab = "SmallStraight";
                index = 9;
            }
        }
        if (blocked[10] == false) {
            if (biggest < largeStraight()) {
                biggest = largeStraight();
                lab = "LargeStraight";
                index = 10;
            }
        }
        if (blocked[11] == false) {
            if (biggest < yatzy()) {
                biggest = yatzy();
                lab = "Yatzy";
                index = 11;
            }
        }
        if (blocked[12] == false) {
            if (biggest < chance()) {
                biggest = chance();
                lab = "Chance";
                index = 12;
            }
        }
        blocked[index] = true;
        System.out.println(lab + ":" + biggest);
        return biggest;

    }
    //Checks if ones are possible
    public int ones() {
        int ones = 0;
        int[] würfel = toArray();
        if (würfel[0] == 1) {
            ones += 1;
        }
        if (würfel[1] == 1) {
            ones += 1;
        }
        if (würfel[2] == 1) {
            ones += 1;
        }
        if (würfel[3] == 1) {
            ones += 1;
        }
        if (würfel[4] == 1) {
            ones += 1;
        }
        return ones;
    }
    //Checks if twos are possible
    public int twos() {
        int twos = 0;
        int[] würfel = toArray();
        if (würfel[0] == 2) {
            twos += 2;
        }
        if (würfel[1] == 2) {
            twos += 2;
        }
        if (würfel[2] == 2) {
            twos += 2;
        }
        if (würfel[3] == 2) {
            twos += 2;
        }
        if (würfel[4] == 2) {
            twos += 2;
        }
        return twos;
    }
    //Checks if threes are possible
    public int threes() {
        int threes = 0;
        int[] würfel = toArray();
        if (würfel[0] == 3) {
            threes += 3;
        }
        if (würfel[1] == 3) {
            threes += 3;
        }
        if (würfel[2] == 3) {
            threes += 3;
        }
        if (würfel[3] == 3) {
            threes += 3;
        }
        if (würfel[4] == 3) {
            threes += 3;
        }
        return threes;
    }
    //Checks if fours are possible 
    public int fours() {
        int fours = 0;
        int[] würfel = toArray();
        if (würfel[0] == 4) {
            fours += 4;
        }
        if (würfel[1] == 4) {
            fours += 4;
        }
        if (würfel[2] == 4) {
            fours += 4;
        }
        if (würfel[3] == 4) {
            fours += 4;
        }
        if (würfel[4] == 4) {
            fours += 4;
        }
        return fours;
    }
    //Checks if fives are possible
    public int fives() {
        int fives = 0;
        int[] würfel = toArray();
        if (würfel[0] == 5) {
            fives += 5;
        }
        if (würfel[1] == 5) {
            fives += 5;
        }
        if (würfel[2] == 5) {
            fives += 5;
        }
        if (würfel[3] == 5) {
            fives += 5;
        }
        if (würfel[4] == 5) {
            fives += 5;
        }
        return fives;
    }
    //Checks if sixes are possible
    public int sixes() {
        int sixes = 0;
        int[] würfel = toArray();
        if (würfel[0] == 6) {
            sixes += 6;
        }
        if (würfel[1] == 6) {
            sixes += 6;
        }
        if (würfel[2] == 6) {
            sixes += 6;
        }
        if (würfel[3] == 6) {
            sixes += 6;
        }
        if (würfel[4] == 6) {
            sixes += 6;
        }
        return sixes;
    }
    //Checks if three of kind is possible
    public int threeOfKind() {
        int threeOfKind = 0;
        int[] würfel = toArray();
        Arrays.sort(würfel);
        if ((würfel[0] == würfel[1]) && (würfel[1] == würfel[2]) && (würfel[2] != würfel[3]) && (würfel[3] != würfel[4])) {
            threeOfKind = würfel[0] + würfel[1] + würfel[2] + würfel[3] + würfel[4];
            return threeOfKind;
        } else if ((würfel[1] == würfel[2]) && (würfel[2] == würfel[3]) && (würfel[3] != würfel[4]) && (würfel[4] != würfel[0])) {
            threeOfKind = würfel[0] + würfel[1] + würfel[2] + würfel[3] + würfel[4];
            return threeOfKind;
        } else if ((würfel[2] == würfel[3]) && (würfel[3] == würfel[4]) && (würfel[4] != würfel[0]) && (würfel[0] != würfel[1])) {
            threeOfKind = würfel[0] + würfel[1] + würfel[2] + würfel[3] + würfel[4];
            return threeOfKind;
        } else {
            return 0;
        }
    }
    //checks if four of kind is possible
    public int fourOfKind() {
        int fourOfKind = 0;
        int[] würfel = toArray();
        Arrays.sort(würfel);
        if (würfel[0] == würfel[1] && würfel[1] == würfel[2] && würfel[2] == würfel[3] && würfel[3] != würfel[4]) {
            fourOfKind = würfel[0] + würfel[1] + würfel[2] + würfel[3] + würfel[4];
            return fourOfKind;
        } else if (würfel[1] == würfel[2] && würfel[2] == würfel[3] && würfel[3] == würfel[4] && würfel[4] != würfel[0]) {
            fourOfKind = würfel[0] + würfel[1] + würfel[2] + würfel[3] + würfel[4];
            return fourOfKind;
        } else {
            return 0;
        }
    }
    //checks if full hous is possible
    public int fullHouse() {
        int fullHouse = 0;
        int[] würfel = toArray();
        Arrays.sort(würfel);
        if ((((würfel[0] == würfel[1]) && (würfel[1] == würfel[2]))
                && (würfel[3] == würfel[4])
                && (würfel[2] != würfel[3]))
                || ((würfel[0] == würfel[1])
                && ((würfel[2] == würfel[3]) && (würfel[3] == würfel[4]))
                && (würfel[1] != würfel[2]))) {
            fullHouse = 25;
        }
        return fullHouse;
    }
    //checks if small straight is possible
    public int smallStraight() {
        int smallStraight = 0;
        int[] würfel = toArray();
        Arrays.sort(würfel);
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
            smallStraight = 30;
        }
        return smallStraight;
    }
    //checks if large straight is possible
    public int largeStraight() {
        int largeStraight = 0;
        int[] würfel = toArray();
        Arrays.sort(würfel);

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
            largeStraight = 40;
        }
        return largeStraight;
    }
    //check if yatzy is possible
    public int yatzy() {
        int yatzy = 0;
        if (numb1 == numb2 && numb2 == numb3 && numb3 == numb4 && numb4 == numb5) {
            yatzy = 50;
        }
        return yatzy;
    }
    //checks if chance is possible
    public int chance() {
        int chance = 0;
        chance = numb1 + numb2 + numb3 + numb4 + numb5;
        return chance;
    }
}

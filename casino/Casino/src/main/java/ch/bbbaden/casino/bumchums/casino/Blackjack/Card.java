/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Blackjack;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;

/**
 *
 * @author Startklar
 */
public class Card {
    private String name;
    private String type;
    private int value;

    public Card(int value, String name, String type) {
        this.value = value;
        this.name = name;
        this.type = type;
    }


    public Card() {
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

}

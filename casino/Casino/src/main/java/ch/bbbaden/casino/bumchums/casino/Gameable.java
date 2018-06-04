/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino;

import ch.bbbaden.casino.bumchums.casino.model.Player;
import javafx.stage.Stage;

/**
 *
 * @author LB
 */
public interface Gameable {
    
    public abstract void setMainApp(MainApp mainApp);
    public abstract void setPlayer(Player player);
    public abstract void setStage(Stage stage);
    
}

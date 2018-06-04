/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Bingo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alex
 */
public class Bingo extends Stage {
    
    Stage stage;

    public void start(Stage stage) throws Exception {
        stage = this;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Bingo/BingoStartView.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }
    
}

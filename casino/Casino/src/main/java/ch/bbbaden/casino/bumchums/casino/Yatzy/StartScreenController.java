/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Yatzy;

import ch.bbbaden.casino.bumchums.casino.Gameable;
import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nico
 */
public class StartScreenController implements Initializable, Gameable {

    int bet;
    int balance;
    
    @FXML
    private Label lbBalance;
    @FXML
    private TextField txtBet;
    private MainApp mainApp;
    private Player player;
    private DBDefaultUtil db;
    private Stage stage;
    @FXML
    //If an correct input is given it switches screen.
    public void screenChangeButton(ActionEvent event) throws IOException {
        if (txtBet.getText().isEmpty() == true ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("WRONG INPUT");
            alert.setContentText("Something is wrong with your input, or the input is missing.");
            alert.showAndWait();
        } else {
            bet = Integer.parseInt(txtBet.getText());
            mainApp.showYatzyGame(player, bet);
            this.stage.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DBDefaultUtil();
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
        lbBalance.setText(String.valueOf(db.getAccountBalance(player.getEmail())));
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

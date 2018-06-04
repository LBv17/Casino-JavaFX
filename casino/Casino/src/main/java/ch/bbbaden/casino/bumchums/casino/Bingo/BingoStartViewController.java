/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Bingo;

import ch.bbbaden.casino.bumchums.casino.Gameable;
import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class BingoStartViewController implements Initializable, Gameable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnStartGame;
    @FXML
    private Label lblAccount, lblCardPrice, lblReady, lblName;
    @FXML
    private Text txtReminder;
    @FXML
    private Button btnQuitGame;
    @FXML
    private Button btnPurchaseCard;

    private int accountBalance = 0;
    private int cardPrice = 0;
    private MainApp mainApp;
    private Player player;
    private Stage stage;
    private DBDefaultUtil db;
    
//    private BingoMainViewController playerData = new BingoMainViewController(lblAccount, lblName);

    @FXML
    public void purchaseCard(ActionEvent event) {
        btnStartGame.setVisible(true);
        btnPurchaseCard.setDisable(true);

        System.out.println("the player bought 1 card");
        accountBalance = (accountBalance - cardPrice); //Subtract price from account
        lblAccount.setText(accountBalance + " coins");

        txtReminder.setVisible(true);
        lblReady.setVisible(true);
    }

    @FXML
    public void startGame(ActionEvent event) throws IOException {

        //Close Window
        mainApp.showBingoGame(player, cardPrice);

    }

    @FXML
    public void quitGame(ActionEvent event) {

        //Close window
        mainApp.showMenuView(player);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Label with Account Balance
        lblAccount.setText(accountBalance + " coins");
        lblCardPrice.setText(cardPrice + " coins");
        btnStartGame.setVisible(false);
        txtReminder.setVisible(false);
        lblReady.setVisible(false);
        db = new DBDefaultUtil();
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
        lblAccount.setText(Integer.toString(db.getAccountBalance(player.getEmail())));
        accountBalance = db.getAccountBalance(player.getEmail());
        cardPrice = accountBalance / 8;
        lblCardPrice.setText(cardPrice + " coins");
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}

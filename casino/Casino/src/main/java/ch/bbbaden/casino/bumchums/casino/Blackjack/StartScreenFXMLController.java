package ch.bbbaden.casino.bumchums.casino.Blackjack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Startklar
 */
public class StartScreenFXMLController implements Initializable, Gameable {

    private CalculatorPlayer calcPlayer;
    private CalculatorGroupier calcGroupier;
    Bank bank;
    @FXML
    private Label user;
    @FXML
    private Label money;
    private Player player;
    private MainApp mainApp;
    private Stage stage;
	Username username;

    /**
     * Initializes the controller class.
     */
    //HIer wird man zum View des Spieles weitergeleitet.
    @FXML
    private void begin(ActionEvent event) throws IOException {
        mainApp.showBlackJackGame(player);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Hier werden die Userdaten und die Guthabendaten gesetzt.
        try {
            bank.getCredit();
            String zaster = String.valueOf(bank.getCredit());
            money.setText(zaster);
        } catch (Exception e) {
        }

        this.bank = new Bank();
        this.calcPlayer = new CalculatorPlayer();
        this.calcGroupier = new CalculatorGroupier();
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
        DBDefaultUtil db = new DBDefaultUtil();
        user.setText(player.getFirstName() + " " + player.getLastName());
        money.setText(Integer.toString(db.getAccountBalance(player.getEmail())));
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void returnView(MouseEvent event) {
        mainApp.showMenuView(player);
    }

   

  

}

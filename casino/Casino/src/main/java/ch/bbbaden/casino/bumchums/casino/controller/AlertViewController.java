/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.controller;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.AlertType;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LB
 */
public class AlertViewController implements Initializable {

    private Stage stage;
    private AlertType type;
    private Player player;
    private MainApp mainApp;
    
    @FXML
    private ImageView closeView;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXButton okayButton;
    @FXML
    private Label titleLabel;
    @FXML
    private TextFlow contentFlow;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AlertViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void setStage(Stage alertStage) {
        this.stage = alertStage;
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public void setText(String text) {
        Text t = new Text();
        t.setText(text);
        t.setStyle("-fx-fill: #FFFFFF");
        contentFlow.getChildren().add(t);
    }

    public void setType(AlertType type) {
        // 1 Alert
        // 2 Warning
        // 3 Error
        // 69 SicBo specific
        // 200 Internet Alert
        
        this.type = type;
    }
    
    public void setOkButtonText(String s) {
        okayButton.setText(s);
    }
    
    public void setCancelButtonText(String s) {
        cancelButton.setText(s);
    }

    @FXML
    private void onCloseAction(MouseEvent event) {
        backToMenu();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        backToMenu();
    }

    private void backToMenu() {
        switch (this.type) {
            case SIC_BO: // handles close again
                mainApp.getPrimaryStage().close();
                mainApp.showMenuView(player);
                this.stage.close();      
                break;
            case CONNECTION: 
                try {
                    // handles try to connect again
                    mainApp.checkConnection();
                } catch (Exception ex) {
                    Logger.getLogger(AlertViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.stage.close();
                break;
            default:
                this.stage.close();
                //throw new AssertionError();
        }      
    }
    
    @FXML
    private void handleOkAction(ActionEvent event) {
        switch (this.type) {
            case SIC_BO: // handles play again
                mainApp.getPrimaryStage().close();
                mainApp.showSicBo(player);
                this.stage.close();
                break;
            case CONNECTION: 
                try {
                    // handles try to connect again
                    mainApp.checkConnection();
                } catch (Exception ex) {
                    Logger.getLogger(AlertViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.stage.close();
                break;
            default:
                this.stage.close();
                //throw new AssertionError();
        }
    } 
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.controller;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Admin;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.model.User;
import ch.bbbaden.casino.bumchums.casino.util.AlertType;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author LB
 */
public class StartViewController implements Initializable {

    private double X;
    private double Y;
    private MainApp mainApp;
    private DBDefaultUtil db;

    @FXML
    private JFXButton signInButton;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton signUpButton;
    @FXML
    private Pane dragPane;
    @FXML
    private ImageView closeImageView;
    @FXML
    private ImageView minimizeImageView;
    @FXML
    private ImageView logoImageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // @todo set imageviews images

        // Configure close and minimize icon
        Image closeIcon = new Image("/images/close_white.png");
        Image minimizeIcon = new Image("/images/minimize_white.png");
        closeImageView.setImage(closeIcon);
        closeImageView.setFitWidth(100);
        closeImageView.setPreserveRatio(true);
        closeImageView.setSmooth(true);
        minimizeImageView.setImage(minimizeIcon);
        minimizeImageView.setFitWidth(100);
        minimizeImageView.setPreserveRatio(true);
        minimizeImageView.setSmooth(true);

        // Configure logo icon
        Image redLogo = new Image("/images/red.png");
        logoImageView.setImage(redLogo);
        logoImageView.setPreserveRatio(true);
        logoImageView.setSmooth(true);

        db = new DBDefaultUtil();

        passwordField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("ENTER");
                handleSignInAction(new ActionEvent());
            }
        });

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Handle dragging the Stage with recursion in the following 3 functions
     *
     * @param event
     */
    @FXML
    private void handlePaneMouseDragged(MouseEvent event) {
        mainApp.getPrimaryStage().setX(event.getScreenX() + X);
        mainApp.getPrimaryStage().setY(event.getScreenY() + Y);
    }

    @FXML
    private void handlePaneDragReleased(MouseDragEvent event) {
        mainApp.getPrimaryStage().setX(event.getScreenX());
        mainApp.getPrimaryStage().setY(event.getScreenY());
    }

    @FXML
    private void handlePanePressed(MouseEvent event) {
        X = mainApp.getPrimaryStage().getX() - event.getScreenX();
        Y = mainApp.getPrimaryStage().getY() - event.getScreenY();
    }

    @FXML
    private void handleCloseClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleMinimizeClicked(MouseEvent event) {
        mainApp.getPrimaryStage().setIconified(true);
    }

    @FXML
    private void handleSignUpAction(ActionEvent event) {
        mainApp.showSignUpView();
    }

    @FXML
    private void handleSignInAction(ActionEvent event) {

        String email = emailField.getText().trim();
        String pwd = passwordField.getText();

        System.out.println("---->>>>>> " + db.authenticateUser(email, pwd));
        boolean auth = db.authenticateUser(email, pwd);

        if (!auth) {
            mainApp.showAlert("Error!", "Password or Email wrong!.", AlertType.INFO, null, "Okay");

        } else {
            User user = db.getUser(email);

            if (user instanceof Admin) {
                mainApp.showAdminView((Admin) user);
            } else if (user instanceof Player) {
                mainApp.showMenuView((Player) user);
            } else {
                System.out.println("SOMETHING WENT WRONG");
            }
        }

    }

    @FXML
    private void handleForgotAction(MouseEvent event) {
        mainApp.showResetPasswordView();
    }

}

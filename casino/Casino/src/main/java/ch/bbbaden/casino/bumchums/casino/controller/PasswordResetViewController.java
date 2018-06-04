/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.controller;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.User;
import ch.bbbaden.casino.bumchums.casino.util.AlertType;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import ch.bbbaden.casino.bumchums.casino.util.Validator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.*;
import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
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
public class PasswordResetViewController implements Initializable {

    private DBDefaultUtil db;
    
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXPasswordField rPasswordField;
    
    private Stage stage;
    private MainApp mainApp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DBDefaultUtil();
    }    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        // todo check input
        User user = db.getUser(emailField.getText().trim());
        String pw = passwordField.getText();
        String repPw = rPasswordField.getText();
        String alertText = "";
        
        if (user != null) {

            if (pw.equals(repPw)) {
                alertText += "Passwords don't match!\n";
            }
            
            alertText += Validator.validatePassword(pw);

            if (alertText.endsWith("valid")) {
                // change pw in db
                db.updatePassword(user, pw);
                // send mail if succesfully validated
                sendMail(user.getFirstName()+""+user.getLastName(), user.getEmail());
                
                Platform.runLater(() -> {
                    mainApp.showAlert("Successfull Reset", "You can sing in with your new password now!", AlertType.INFO, null, "Ok");
                });
                
                stage.close();
            } else {
                mainApp.showAlert("Error!", alertText, AlertType.ERROR, null, "Okay");
            }
     
        } else {
            // show error
            mainApp.showAlert("Reset Error", "Email not found!", AlertType.ERROR, null, "Okay");
        }
        
    }

    @FXML
    private void handleCloseAction(MouseEvent event) {
        stage.close();
    }
    
    private void sendMail(String userName, String userEmail) {
        
        String ip = "";
        
        try {
        URL whatismyip = new URL("http://checkip.amazonaws.com/");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        ip = in.readLine();
        
        } catch (Exception e) {
            System.err.println(e);
        }
        
        Properties props = new Properties();
        String host = "smtp.gmail.com";
        String from = "casinobumchums";
        String pass = "bbbCasino_420";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, "BumChums"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail, userName));
            msg.setSubject("Password Reset");
            msg.setText("If you didn't change your password sign in and change it!\nThe Public IP of computer that changed your password is: " + ip);
            Transport transport = session.getTransport();transport.connect(host,from,pass );
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

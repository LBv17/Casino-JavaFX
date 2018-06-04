/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.controller;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Admin;
import ch.bbbaden.casino.bumchums.casino.model.GameStatistics;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.model.PlayerStatistics;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LB
 */
public class PlayerStatsViewController implements Initializable {

    @FXML
    private ImageView closeView;
    @FXML
    private JFXComboBox<String> playerBox;
    @FXML
    private JFXComboBox<String> gameBox;
    @FXML
    private JFXDatePicker fromPicker;
    @FXML
    private JFXDatePicker toPicker;
    @FXML
    private TableView<PlayerStatistics> statsTableView;
    @FXML
    private TableColumn<PlayerStatistics, String> playerCol;
    @FXML
    private TableColumn<PlayerStatistics, String> gameCol;
    @FXML
    private TableColumn<PlayerStatistics, Integer> wonCol;
    @FXML
    private TableColumn<PlayerStatistics, Integer> lostCol;
    @FXML
    private TableColumn<PlayerStatistics, Integer> betCol;
    @FXML
    private TableColumn<PlayerStatistics, Date> dateCol;

    private MainApp mainApp;
    private Stage stage;
    private DBDefaultUtil db;
    private Admin admin;
    private double X;
    private double Y;
    private ObservableList<Player> playerList = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gameBox.getItems().addAll("All","SicBo", "Yatzy", "BlackJack", "Baccara", "Bingo");
                
        gameCol.setCellValueFactory(new PropertyValueFactory<PlayerStatistics, String>("game"));
        playerCol.setCellValueFactory(new PropertyValueFactory<PlayerStatistics, String>("player"));
        wonCol.setCellValueFactory(new PropertyValueFactory<PlayerStatistics, Integer>("won"));
        lostCol.setCellValueFactory(new PropertyValueFactory<PlayerStatistics, Integer>("lost"));
        betCol.setCellValueFactory(new PropertyValueFactory<PlayerStatistics, Integer>("bet"));
        dateCol.setCellValueFactory(new PropertyValueFactory<PlayerStatistics, Date>("date"));
    
        db = new DBDefaultUtil();
        
        playerList = db.getAllPlayers();
        ObservableList<String> allPlayerNames = FXCollections.observableArrayList();
        
        playerList.forEach((p) -> {
            allPlayerNames.add(p.getFirstName() + " " + p.getLastName());
        });

        playerBox.getItems().addAll(allPlayerNames);
    }  
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @FXML
    private void handleLoadAction(ActionEvent event) {
        if (statsTableView.getItems() != null) {
            statsTableView.getItems().clear();
        }
    
        Date from = Date.from(fromPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(toPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Player p = playerList.get(playerBox.getSelectionModel().getSelectedIndex());
        ObservableList<PlayerStatistics> playerStats = db.getPlayerStats(p.getEmail(), gameBox.getSelectionModel().getSelectedItem(), from, to);
        statsTableView.setItems(playerStats);
    }
    
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
    private void handleCloseAction(MouseEvent event) {
        mainApp.showAdminView(admin);
        this.stage.close();
    }
    
}

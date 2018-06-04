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
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
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
public class GameStatsViewController implements Initializable {

    @FXML
    private TableView<GameStatistics> statsTableView;
    @FXML
    private JFXDatePicker fromPicker;
    @FXML
    private JFXDatePicker toPicker;
    @FXML
    private JFXComboBox<String> gameBox;
    @FXML
    private TableColumn<GameStatistics, String> gameCol;
    @FXML
    private TableColumn<GameStatistics, String> playerCol;
    @FXML
    private TableColumn<GameStatistics, Integer> wonCol;
    @FXML
    private TableColumn<GameStatistics, Integer> lostCol;
    @FXML
    private TableColumn<GameStatistics, Integer> betCol;
    @FXML
    private TableColumn<GameStatistics, Date> dateCol;
    @FXML
    private ImageView closeView;
    
    private MainApp mainApp;
    private Stage stage;
    private DBDefaultUtil db;
    private Admin admin;
    private double X;
    private double Y;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill comboBox
        gameBox.getItems().addAll("SicBo", "Yatzy", "BlackJack", "Baccara", "Bingo");
        
        gameCol.setCellValueFactory(new PropertyValueFactory<GameStatistics, String>("game"));
        playerCol.setCellValueFactory(new PropertyValueFactory<GameStatistics, String>("player"));
        wonCol.setCellValueFactory(new PropertyValueFactory<GameStatistics, Integer>("won"));
        lostCol.setCellValueFactory(new PropertyValueFactory<GameStatistics, Integer>("lost"));
        betCol.setCellValueFactory(new PropertyValueFactory<GameStatistics, Integer>("bet"));
        dateCol.setCellValueFactory(new PropertyValueFactory<GameStatistics, Date>("date"));
    
        db = new DBDefaultUtil();
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
        
        ObservableList<GameStatistics> gameStats = db.getGameStats(gameBox.getSelectionModel().getSelectedItem(), from, to);
        statsTableView.setItems(gameStats);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.SicBo;

import ch.bbbaden.casino.bumchums.casino.Gameable;
import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.AlertType;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author LB
 */
public class SicBoViewController implements Initializable, Gameable {

    private MainApp mainApp;
    private Player player;
    private Stage stage;
    private double X, Y;
    private SicBo game = SicBo.getInstance();
    private DBDefaultUtil db;
    private int tempBalance;
    
    @FXML private Pane smallPane, bigPane, doubleOnePane, doubleTwoPane, doubleThreePane, doubleFourPane, doubleFivePane, doubleSixPane, triplePane, tripleOnePane, tripleTwoPane, tripleThreePane, tripleFourPane, tripleFivePane, tripleSixPane, sumFourPane, sumFivePane, sumSixPane, sumSevenPane, sumEightPane, sumNinePane, sumTenPane, sumElevenPane, sumTwelvePane, sumThirteenPane, sumFourteenPane, sumFifteenPane, sumSixteenPane, sumSeventeenPane, oneTwoPane, oneThreePane, oneFourPane, oneFivePane, oneSixPane, twoThreePane, twoFourPane, twoFivePane, twoSixPane, threeFourPane, threeFivePane, threeSixPane, fourFivePane, fourSixPane, fiveSixPane, onePane, twoPane, threePane, fourPane, fivePane, sixPane, comboPane, singlePane, sumPane, doublePane, tripleContainerPane;
    @FXML private Pane dragPane;
    @FXML private JFXComboBox<Integer> betBox;
    @FXML private JFXButton rollButton;
    @FXML private ImageView diceOne, diceTwo, diceThree, boardView;
    @FXML private AnchorPane anchorPane;
    @FXML private JFXButton confirmBetButton;
    @FXML private JFXButton resetButton;
    @FXML private Label accountBalanceLabel;
    
    private ObservableList<Node> comboChildren;
    private ObservableList<Node> sumChildren;
    private ObservableList<Node> singleChildren;
    private ObservableList<Node> doubleChildren;
    private ObservableList<Node> tripleChildren;
    
    private ArrayList<ImageView> chipList = new ArrayList();
    private ArrayList<Label> labelList = new ArrayList();
    private ArrayList<String> bettedPaneList = new ArrayList();
    private ArrayList<Integer> bettedAmountList = new ArrayList();
    private ArrayList<String> winningPanes = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        resetView();
        // @todo check balance
        
        boardView.setImage(new Image("/images/SicBo/SicBoBack.png"));
        diceOne.setImage(new Image("/images/SicBo/1.png"));
        diceTwo.setImage(new Image("/images/SicBo/2.png"));
        diceThree.setImage(new Image("/images/SicBo/3.png"));

        comboChildren = comboPane.getChildren();
        sumChildren = sumPane.getChildren();
        singleChildren = singlePane.getChildren();
        doubleChildren = doublePane.getChildren();
        tripleChildren = tripleContainerPane.getChildren();

        ObservableList<Integer> options = FXCollections.observableArrayList(50, 100, 500, 1000);
        
        betBox.setItems(options);
        betBox.getSelectionModel().select(0);

        rollButton.setDisable(true);

        db = new DBDefaultUtil();
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
        game.setPlayer(player);
        
        // show accountbalance
        int accountBalance = db.getAccountBalance(player.getEmail());
        this.tempBalance = accountBalance;
        accountBalanceLabel.setText(String.valueOf(accountBalance));
        
        if (50 > accountBalance) {
            Platform.runLater(() -> {
                mainApp.showAlert("Low Account Balance", "Recharge balance to play you don't hava enough money", AlertType.INFO, player, "Okay");
            });
        } else {
            createChip();
        }
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
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
        
        //@todo ask if sure to exit
        
        int sum = 0;
        for (Integer i : bettedAmountList) {
            sum+=i;
        }
        
        db.updateAccountBalance(player.getEmail(), 0-sum);
        
        mainApp.showMenuView(player);
        this.stage.close();
    }

    
    @FXML
    /**
     * Basically the whole game flow 
     */
    private void handleRollAction(ActionEvent event) {

        diceOne.setImage(new Image("/images/SicBo/diceGif.gif"));
        diceTwo.setImage(new Image("/images/SicBo/diceGif.gif"));
        diceThree.setImage(new Image("/images/SicBo/diceGif.gif"));

        int[] dice = game.rollDice();
        String d1 = Integer.toString(dice[0]);
        String d2 = Integer.toString(dice[1]);
        String d3 = Integer.toString(dice[2]);
        
        Task task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                Thread.sleep(2000);

                diceOne.setImage(new Image("/images/SicBo/" + d1 + ".png"));
                diceTwo.setImage(new Image("/images/SicBo/" + d2 + ".png"));
                diceThree.setImage(new Image("/images/SicBo/" + d3 + ".png"));

                // check result
                checkResult(dice);
                
                int result = game.calculateResult(bettedPaneList, bettedAmountList, winningPanes);
                //int newBlanance = result + Integer.valueOf(accountBalanceLabel.getText());
                db.updateAccountBalance(player.getEmail(), result);
                
                rollButton.setDisable(true);
                                
                Platform.runLater(() -> {          
                    //mainApp.showAlert("SicBo", "Round over, you lost/won "+result+"...What's next?", AlertType.SIC_BO, player, "Play Again");

                    
                    if (result < 0) {
                        mainApp.showAlert("SicBo", "Round over, you lost "+(0-result)+"...What's next?", AlertType.SIC_BO, player, "Play Again");
                    } else if (result > 0) {
                        mainApp.showAlert("SicBo", "Round over, you won "+result+"...What's next?", AlertType.SIC_BO, player, "Play Again");
                    } else if (result == 0) {
                        mainApp.showAlert("SicBo", "Round over, you tied...What's next?", AlertType.SIC_BO, player, "Play Again");
                    }
                });

                return null;
            }
        };

        new Thread(task).start();

    }

    @FXML
    private void handleConfirmAction(ActionEvent event) {
        
        if (betBox.getSelectionModel().getSelectedItem() > this.tempBalance) {
            mainApp.showAlert("Not Enough Money", "Recharge account balance in Menu", AlertType.INFO, player, "Okay");
        } else {
            this.tempBalance -= betBox.getSelectionModel().getSelectedItem();
            String text = betBox.getSelectionModel().getSelectedItem().toString();
            labelList.get(labelList.size() - 1).setText(text);
            chipList.get(chipList.size() - 1).setDisable(false);
        }

    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        resetView();
    }
    
    private void createChip() {
        ImageView chip = new ImageView();
        chip.setImage(new Image("/images/SicBo/chip.png"));

        chip.setFitHeight(55);
        chip.setFitWidth(55);

        anchorPane.getChildren().add(chip);

        chip.setX(660); //660
        chip.setY(618); //618

        chip.setDisable(true);

        chipList.add(chip);

        createLabel("0");
        addChipAction(chip);

        Label l = labelList.get(labelList.size()-1);
       
        FadeTransition ftL = new FadeTransition(Duration.millis(1000), l);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), chip);
        ft.setFromValue(0.2);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ftL.setFromValue(0.2);
        ftL.setToValue(1);
        ftL.setCycleCount(1);
        ftL.setAutoReverse(false);
        
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(ft, ftL);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();
        
    }

    private void addChipAction(ImageView chip) {

        Label label = labelList.get(labelList.size() - 1);

        chip.setOnMouseDragged(event -> {
            //System.out.println("dragged");
            chip.setX(event.getX() - 55 / 2);
            chip.setY(event.getY() - 55 / 2);

            Bounds bound = chip.localToScene(chip.getBoundsInLocal());
            label.setTranslateX(bound.getMinX() + 16);
            label.setTranslateY(bound.getMinY() + 20);
        });

        chip.setOnMouseReleased(event -> {
            //System.out.println("released");

            chip.setX(event.getX() - 55 / 2);
            chip.setY(event.getY() - 55 / 2);

            checkBounds(chip, label);

        });

        chip.setOnMousePressed(e -> {
            //System.out.println("pressed");
        });
    }

    private void createLabel(String s) {
        ImageView chip = chipList.get(chipList.size() - 1);
        Bounds bound = chip.localToScene(chip.getBoundsInLocal());

        Label l = new Label(s);

        l.setDisable(true);
        l.setOpacity(1.0);
        l.setStyle("-fx-text-fill: FF5252");
        l.setFont(new Font(10.0));

        l.setTranslateX(bound.getMinX() + 16);
        l.setTranslateY(bound.getMinY() + 20);

        anchorPane.getChildren().add(l);
        labelList.add(l);
    }

    /**
     * Checks if the Chip has been placed on a valid position
     * @param chip
     * @param label 
     */
    private void checkBounds(ImageView chip, Label label) {

        ObservableList<Node> nodeList = anchorPane.getChildren();
        ArrayList<Pane> paneList = new ArrayList();

        for (Node n : nodeList) {
            if (n instanceof Pane) {
                if (n.getId().equals("singlePane")) {
                    for (Node sPane : singlePane.getChildren()) {
                        paneList.add((Pane) sPane);
                    }
                } else if (n.getId().equals("sumPane")) {
                    for (Node sPane : sumPane.getChildren()) {
                        paneList.add((Pane) sPane);
                    }
                } else if (n.getId().equals("comboPane")) {
                    for (Node cPane : comboPane.getChildren()) {
                        paneList.add((Pane) cPane);
                    }
                } else if (n.getId().equals("doublePane")) {
                    for (Node dPane : doublePane.getChildren()) {
                        paneList.add((Pane) dPane);
                    }           
                } else if (n.getId().equals("tripleContainerPane")) {
                    for (Node tPane : tripleContainerPane.getChildren()) {
                        paneList.add((Pane) tPane);
                    }           
                } else {
                    paneList.add((Pane) n);
                }
            }
        }

        Bounds boundsInScene = chip.localToScene(chip.getBoundsInLocal());

        double minX = boundsInScene.getMinX() + 55 / 2;
        double minY = boundsInScene.getMinY() + 55 / 2;

        int intersects = 0;

        for (int i = 0; i < paneList.size(); i++) {

            Bounds paneBounds = paneList.get(i).localToScene(paneList.get(i).getBoundsInLocal());

            if (paneBounds.intersects(minX, minY, 1, 1)) {
                if (!paneList.get(i).getId().equals("dragPane")) {
                    bettedPaneList.add(paneList.get(i).getId());
                    bettedAmountList.add(Integer.valueOf(label.getText()));
                    intersects++;
                    chip.setDisable(true);
                    label.setDisable(true);
                } else {
                    System.out.println("DRAGPANE WRONG");
                }
            }
        }

        if (intersects < 1) {
            label.setText("0");
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.setAutoReverse(false);
            final KeyValue kv = new KeyValue(chip.xProperty(), 660);
            final KeyValue kvL = new KeyValue(label.translateXProperty(), 680);
            final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            final KeyFrame kfL = new KeyFrame(Duration.millis(500), kvL);
            final KeyValue kv2 = new KeyValue(chip.yProperty(), 618);
            final KeyValue kv2L = new KeyValue(label.translateYProperty(), 632);
            final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
            final KeyFrame kf2L = new KeyFrame(Duration.millis(500), kv2L);
            timeline.getKeyFrames().addAll(kf, kf2, kfL, kf2L);
            timeline.play();
        } else {
            rollButton.setDisable(false);
            createChip();
        }

        System.out.println("intersect amount: " + intersects);
    }

    /**
     * Checks what combinations have been rolled and makes them light up
     * @param dice
     * @throws InterruptedException 
     */
    private void checkResult(int[] dice) throws InterruptedException {

        int sum = dice[0] + dice[1] + dice[2];

        if (sum >= 4 && sum <= 10) {
            // small
            smallPane.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2)");
            winningPanes.add(smallPane.getId());
        } else if (sum >= 11 && sum <= 17) {
            //big
            bigPane.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2)");
            winningPanes.add(bigPane.getId());
        }

        // check sum
        ArrayList<Integer> sums = new ArrayList();
        for (int i = 4; i < 18; i++) {
            sums.add(i);
        }

        for (Integer i : sums) {
            if (i == sum) {
                Node node = sumChildren.get(sums.indexOf(i));
                node.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2)");
                winningPanes.add(node.getId());
            }
        }

        // check single
        ArrayList<Integer> singles = new ArrayList();
        for (int i = 1; i < 7; i++) {
            singles.add(i);
        }

        for (Integer i : dice) {
            for (Integer s : singles) {
                if (s == i) {
                    Node node = singleChildren.get(singles.indexOf(s));
                    node.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2)");
                    winningPanes.add(node.getId());
                }
            }
        }

        // Checks triple        
        for (int i = 1; i < 7; i++) {

            final int f = i;
            boolean match = Arrays.stream(dice).allMatch(x -> x == f);

            if (match) {
                
                for (int j = 1; j < 8; j++) {
                    Node node = tripleChildren.get(j);
                    node.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2)");
                    winningPanes.add(node.getId());
                }
                
            }
        }

        // checks doubles
        int doubleNum = 0;
        
        if (dice[0] == dice[1]) {
            doubleNum = dice[0];
        }
        if (dice[0] == dice[2]) {
            doubleNum = dice[0];
        }
        if (dice[1] == dice[2]) {
            doubleNum = dice[1];
        }
        
        for (int i = 1; i < 7; i++) {
            if (i == doubleNum) {
                Node node = doubleChildren.get(i);
                node.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2)");
                winningPanes.add(node.getId());
                System.out.println("DOUBLE: " + node.getId());
            }
        }

        // fills combos into list
        ArrayList<Integer> comboList = new ArrayList();
        for (int i = 1; i < 6; i++) {
            for (int j = i + 1; j < 7; j++) {
                comboList.add(i * 10 + j);
            }
        }

        // checks combos
        for (Integer i : comboList) {
            Thread.sleep(100);
            if (dice[0] == i / 10 || dice[1] == i / 10 || dice[2] == i / 10) {
                if (dice[0] == i % 10 || dice[1] == i % 10 || dice[2] == i % 10) {
                    Node n = comboChildren.get(comboList.indexOf(i));
                    n.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2)");
                    winningPanes.add(n.getId());
                }
            }
        }
    }

    /**
     * Resets chips and panes and button
     */
    private void resetView() {
        ObservableList<Node> nodeList = anchorPane.getChildren();

        for (Node n : nodeList) {
            if (n instanceof Pane) {
                n.setStyle("-fx-background-color: rgba(255,255,0,0.0)");
            }
        }

        // reset chips
        for (int i = 0; i < chipList.size() - 1; i++) {
            ImageView image = chipList.get(i);
            Label label = labelList.get(i);
            anchorPane.getChildren().remove(image);
            anchorPane.getChildren().remove(label);
        }

        rollButton.setDisable(true);
    }
}

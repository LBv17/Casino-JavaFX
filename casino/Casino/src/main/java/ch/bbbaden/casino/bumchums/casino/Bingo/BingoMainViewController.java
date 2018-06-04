/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Bingo;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.AlertType;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class BingoMainViewController implements Initializable {

    @FXML
    private Label lblAccount, lblName, lblBingoNumber, lblCountDown, lblOut;
    @FXML
    private Button btnQuitGame, btnBingo, btnPlayAgain;
    @FXML
    private Text txtBot1, txtBot2, txtBot3, txtBot4, txtBot5;
    @FXML
    private HBox mainCardHBox, mainCard2HBox, hBoxNumbers, bot1CardHBox, bot1CardHBox2, bot1CardHBox3, bot1CardHBox4, bot1CardHBox5, bot2CardHBox, bot2CardHBox2, bot2CardHBox3, bot2CardHBox4, bot2CardHBox5, bot3CardHBox, bot3CardHBox2, bot3CardHBox3, bot3CardHBox4, bot3CardHBox5, bot4CardHBox, bot4CardHBox2, bot4CardHBox3, bot4CardHBox4, bot4CardHBox5, bot5CardHBox, bot5CardHBox2, bot5CardHBox3, bot5CardHBox4, bot5CardHBox5;
    @FXML
    private VBox mainCardVBox, mainCard2VBox, vBoxNumbers, bot1CardVBox, bot2CardVBox, bot3CardVBox, bot4CardVBox, bot5CardVBox;
    @FXML
    private Pane card1Pane;

    /* **************************************************************************** */
    private Timeline countdownNumber = new Timeline();
    private final int startTime = 5;
    private int count = startTime;
    private int i = 0;
    private int a = 0;
    private int bingoNumber;
    boolean printValue = false;
    private final double fontSize = 23.0;
    private boolean countdownFinished = false;
    private int bingoFail = 0;
    
    private BingoModel model = new BingoModel();

    private BingoBotModel bot = new BingoBotModel("George");
    private BingoBotModel bot2 = new BingoBotModel("Gary");
    private BingoBotModel bot3 = new BingoBotModel("Richard");
    private BingoBotModel bot4 = new BingoBotModel("Lary");
    private BingoBotModel bot5 = new BingoBotModel("John");

    final Label[] botNumbers = new Label[26];

    //Bot arraylist
    private ArrayList<BingoBotModel> bots = new ArrayList<>();

    public final ArrayList<Integer> rndNumbers = model.getCardNumbers();
    public final ArrayList<HBox> hBoxPlayer = new ArrayList<>();
    public final ArrayList<VBox> vBoxPlayer = new ArrayList<>();
    public final ArrayList<HBox> hBoxBots = new ArrayList<>();
    public final ArrayList<VBox> vBoxBots = new ArrayList<>();
    private Player player;
    private MainApp mainApp;
    private Stage stage;
    private DBDefaultUtil db;
    private int bet;

    /* *********************************************************************** */
//
//    public BingoMainViewController(Label lblAccount, Label lblName) {
//        this.lblAccount = lblAccount;
//        this.lblName = lblName;
//    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        db = new DBDefaultUtil();
        
        btnPlayAgain.setVisible(false);
        lblOut.setVisible(false);

        bots.add(bot);
        bots.add(bot2);
        bots.add(bot3);
        bots.add(bot4);
        bots.add(bot5);

        txtBot1.setText(bot.getBotName());
        txtBot2.setText(bot2.getBotName());
        txtBot3.setText(bot3.getBotName());
        txtBot4.setText(bot4.getBotName());
        txtBot5.setText(bot5.getBotName());

        createBotNumbers(); //Generate bot cards
        createCardNumbers(); //Generate card numbers
        countdown(); //start countdownNumber timer
    }

    private void countdown() {
        countdownNumber = new Timeline();
        //new Keyframe that lasts 1 second
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //Set the text of "lblCountdown"
                lblCountDown.setText(count + " seconds.");
                count--; //count down 

                //When timer hits 0 seconds, do this:
                if (count == -1) {
                    countdownNumber.stop();
                    //Change the text with the generated bingo number

                    bingoNumber = model.createBingoNumber();
                    lblBingoNumber.setText(Integer.toString(bingoNumber)); //set text of label
                    System.out.println(bingoNumber + " is the bingo number");
                    lblOut.setVisible(false);

                    for (int i = 0; i < 5; i++) {
                        //checks if bot has the bingo number on its card
                        if (bots.get(i).newNumber(bingoNumber)) {
                            botNumberFound(bots.get(i).getCoveredNumberArray(), i);
                            if (bots.get(i).isWin()) {

                                //which bot got bingo:
                                System.out.println(bots.get(i).getBotName() + " Bingo");
                                lblOut.setText(bots.get(i).getBotName() + " Bingo");
                                //player loses
                                System.out.println("*******************");
                                System.out.println("You lose!");
                                countdownFinished = true;
                                try {
                                    EndOfGame();
                                } catch (IOException ex) {
                                    Logger.getLogger(BingoMainViewController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                    count = 5; //Reset timer variable

                    if (!countdownFinished) {
                        countdown();//start timer again
                    }
                }
            }
        });
        //runs until "stop()" is called
        countdownNumber.setCycleCount(Timeline.INDEFINITE);
        countdownNumber.getKeyFrames().add(keyFrame);

        countdownNumber.play();
    }

    public void createCardNumbers() {

        vBoxPlayer.add(mainCardVBox);
        vBoxPlayer.add(mainCard2VBox);

        for (VBox vbox : vBoxPlayer) {
            for (Node node : vbox.getChildren()) {
                hBoxPlayer.add((HBox) node);
            }
            try {
                final Label[] playerNumbers = new Label[26];

                for (i = 0; i < 25; i++) {
                    playerNumbers[i] = new Label(Integer.toString(rndNumbers.get(i)));
                    playerNumbers[i].setMinSize(56, 43); //Set size of labels
                    playerNumbers[i].setFont(new Font(fontSize)); //set font size

                    if (i % 5 == 0) {
                        hBoxPlayer.get(i / 5).setSpacing(7);
                        hBoxPlayer.get(i / 5).setPadding(new Insets(5, 0, 0, 7));
                        hBoxPlayer.get(i / 5).getChildren().add(playerNumbers[i]);

                    } else {
                        hBoxPlayer.get(i / 5).getChildren().add(playerNumbers[i]);
                    }

                    //When a label is clicked on; do this:
                    playerNumbers[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Label changeLabel = (Label) event.getSource(); //new object

                            //new int with the value of the number on the label
                            int x = Integer.parseInt(changeLabel.getText());

                            //gets the position of a column in a row.
                            model.fillArray(getIndex(x), x);

                            changeLabel.setTextFill(Color.RED); //Change color
                            changeLabel.setFont(Font.font("Oswald", FontWeight.BOLD, 32));
                            changeLabel.setDisable(true); //disable label
                        }
                    });
                }
            } catch (NumberFormatException e) {
            }
            Collections.shuffle(rndNumbers); //shuffle arraylist
            hBoxPlayer.clear();
        }
    }

    /* ********* Bots : ********** */
    private void createBotNumbers() {

        //Add VBoxes to the VBox Arraylist
        vBoxBots.add(bot1CardVBox);
        vBoxBots.add(bot2CardVBox);
        vBoxBots.add(bot3CardVBox);
        vBoxBots.add(bot4CardVBox);
        vBoxBots.add(bot5CardVBox);
        int y = 0;
        for (VBox vbox : vBoxBots) {
            ArrayList<Integer> bC = bots.get(y).getCardNumber();
            for (Node node : vbox.getChildren()) {
                hBoxBots.add((HBox) node);
            }
            try {

                for (i = 0; i < 25; i++) {
                    botNumbers[i] = new Label(Integer.toString(bC.get(i))); //get value from rndNumbers
                    botNumbers[i].setMinSize(25, 22); // set size of label

                    if (i % 5 == 0) {
                        hBoxBots.get(i / 5).setSpacing(7); //set spacing
                        hBoxBots.get(i / 5).setPadding(new Insets(5, 0, 0, 7)); //set padding
                        hBoxBots.get(i / 5).getChildren().add(botNumbers[i]);

                    } else {
                        hBoxBots.get(i / 5).getChildren().add(botNumbers[i]);
                    }
                }
            } catch (NumberFormatException e) {
            }
            hBoxBots.clear();
            Collections.shuffle(rndNumbers);
            y++;
        }
    }

    private int getIndex(int number) {
        for (int i = 0; i < rndNumbers.size(); i++) {
            int a = rndNumbers.get(i);
            if (a == number) {
                return i;
            }
        }
        return -1;
    }

    @FXML //Quit game:
    public void quitGame(ActionEvent event) throws IOException {
        
        Date date = new Date();
        db.updateAccountBalance(player.getEmail(), 0-bet);
        db.updateStats(player, "Bingo", 0, bet, bet, date); 
        mainApp.showMenuView(player);
        
        
    }

    @FXML //Click Bingo:
    public void clickBingo(ActionEvent event) throws IOException {

        if (!model.bingoLegitimacy()) {
            lblOut.setVisible(true);
            lblOut.setText("You have no bingo!");
            System.out.println("*****************");
            System.out.println("You have no bingo!");
            System.out.println("*****************");
            
            if (bingoFail < 1) {
                mainApp.showAlert("Warning", "If you mistakenly press again you will loose!", AlertType.WARNING, player, "Okay");
                bingoFail++;
            } else {
                EndOfGame();
                Date date = new Date();
                db.updateAccountBalance(player.getEmail(), 0-bet);
                db.updateStats(player, "Bingo", 0, bet, bet, date); 
            } 
            
        } else {
            lblOut.setVisible(true);
            lblOut.setText("You have bingo!");
            System.out.println("***************");
            System.out.println("You have bingo!");
            System.out.println("***************");
            EndOfGame();

            Date date = new Date();
            db.updateAccountBalance(player.getEmail(), bet);
            db.updateStats(player, "Bingo", bet*2, 0, bet, date);          
            //add cash to account
            //you win
            //game finished
        }
    }

    private void botNumberFound(boolean[] coveredNumbers, int vboxInt) {
        VBox vbox = vBoxBots.get(vboxInt);
        //Accesses children from vbox
        for (Node node : vbox.getChildren()) {
            hBoxBots.add((HBox) node);
        }
        int i = 0;
        for (HBox node : hBoxBots) {

            for (Node label : node.getChildren()) {
                if (coveredNumbers[i]) {
                    ((Label) label).setTextFill(Color.RED);
                    ((Label) label).setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                }
                i++;
            }

        }

        hBoxBots.clear();

    }

    private void EndOfGame() throws IOException {
        countdownNumber.stop();
        btnPlayAgain.setVisible(true);
        btnBingo.setDisable(true);
        //make button visible

    }

    @FXML
    public void playAgain(ActionEvent event) {
        Bingo bingo = new Bingo();

        mainApp.showBingo(player);
    }

    public void setPlayer(Player player) {
        this.player = player;
        lblAccount.setText(Integer.toString(db.getAccountBalance(player.getEmail())));
        lblName.setText(player.getFirstName() + " " + player.getLastName());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
}

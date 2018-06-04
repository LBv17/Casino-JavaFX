/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Yatzy;

import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nico
 */
public class Screen1Controller implements Initializable {
    
    //variables, arrays and togglegroups get declared
    private final Label dice[] = new Label[5];
    private boolean blockiert[] = new boolean[5];
    private int cancel = 0;
    final ToggleGroup tg = new ToggleGroup();
    private boolean help = false;
    int round = 1;
    int rollstxt = 2;
    String totalo, total1, win;
    int total2;
    //the elements get initialized
    private Label dice1;
    private Label dice2;
    private Label dice3;
    private Label dice4;
    private Label dice5;
    @FXML
    private RadioButton rbOnes;
    @FXML
    private RadioButton rbTwos;
    @FXML
    private RadioButton rbThrees;
    @FXML
    private RadioButton rbFours;
    @FXML
    private RadioButton rbFives;
    @FXML
    private RadioButton rbSixes;
    @FXML
    private RadioButton rbThreeOfKind;
    @FXML
    private RadioButton rbFourOfKind;
    @FXML
    private RadioButton rbFH;
    @FXML
    private RadioButton rbSS;
    @FXML
    private RadioButton rbLS;
    @FXML
    private RadioButton rbYatzy;
    @FXML
    private RadioButton rbChance;
    @FXML
    private Button button2;
    @FXML
    private Button button1;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Label lbOnes;
    @FXML
    private Label lbTwos;
    @FXML
    private Label lbThrees;
    @FXML
    private Label lbFours;
    @FXML
    private Label lbFives;
    @FXML
    private Label lbSixes;
    @FXML
    private Label lbThreeOfKind;
    @FXML
    private Label lbFourOfKind;
    @FXML
    private Label lbFH;
    @FXML
    private Label lbSS;
    @FXML
    private Label lbLS;
    @FXML
    private Label lbYatzy;
    @FXML
    private Label lbChance;
    @FXML
    private Label lbTotal;
    @FXML
    private Label lbRound;

    @FXML
    private Button btnRoll;
    @FXML
    private ImageView iv1;
    @FXML
    private ImageView iv2;
    @FXML
    private ImageView iv3;
    @FXML
    private ImageView iv4;
    @FXML
    private ImageView iv5;
    @FXML
    private Button btnRestart;
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnClose;
    @FXML
    private Label lbOnes2;
    @FXML
    private Label lbTwos2;
    @FXML
    private Label lbThrees2;
    @FXML
    private Label lbFours2;
    @FXML
    private Label lbFives2;
    @FXML
    private Label lbSixes2;
    @FXML
    private Label lbThreeOfKind2;
    @FXML
    private Label lbFourOfKind2;
    @FXML
    private Label lbTotal2;
    @FXML
    private Label lbFH2;
    @FXML
    private Label lbSmallStraight2;
    @FXML
    private Label lbLargeStraight2;
    @FXML
    private Label lbYatzy2;
    @FXML
    private Label lbChance2;
    @FXML
    private ImageView imvHelp1;
    @FXML
    private ImageView imvHelp2;
    @FXML
    private ImageView imvHelp3;
    @FXML
    private ImageView imvHelp4;
    @FXML
    private Label lbHelp1;
    @FXML
    private Label lbHelp2;
    @FXML
    private Label lbHelp3;
    @FXML
    private Label lbHelp4;
    @FXML
    private Label lbBalance1;
    //the object value in the class game gets created
    Game value;
    int total = 0;
    //the object opponent in the class Opponent gets created
    Opponent opponent = new Opponent();
    private Player player;
    private MainApp mainApp;
    private int bet;
    private DBDefaultUtil db;
    private Stage stage;
    @FXML
    public void btnRoll(ActionEvent event) {
        //Checks if already 3 times the dices are rolled
        if (cancel < 3) {
            Game game = new Game(blockiert);
            value = game;
            String die1 = game.getDie1();
            String die2 = game.getDie2();
            String die3 = game.getDie3();
            String die4 = game.getDie4();
            String die5 = game.getDie5();
            btnRoll.setText("Roll[" + rollstxt + "]");
            //loads the image with the random number
            if (blockiert[0] != true) {
                iv1.setImage(new Image(getClass().getResourceAsStream("/images/Yatzy/" + die1 + ".png")));
            }
            if (blockiert[1] != true) {
                iv2.setImage(new Image(getClass().getResourceAsStream("/images/Yatzy/" + die2 + ".png")));
            }
            if (blockiert[2] != true) {
                iv3.setImage(new Image(getClass().getResourceAsStream("/images/Yatzy/" + die3 + ".png")));
            }
            if (blockiert[3] != true) {
                iv4.setImage(new Image(getClass().getResourceAsStream("/images/Yatzy/" + die4 + ".png")));
            }
            if (blockiert[4] != true) {
                iv5.setImage(new Image(getClass().getResourceAsStream("/images/Yatzy/" + die5 + ".png")));
            }
            cancel++;
            rollstxt--;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dice[0] = dice1;
        blockiert[0] = false;
        dice[1] = dice2;
        blockiert[1] = false;
        dice[2] = dice3;
        blockiert[2] = false;
        dice[3] = dice4;
        blockiert[3] = false;
        dice[4] = dice5;
        blockiert[4] = false;
        //Radio button get set into an togglegroup
        rbOnes.setToggleGroup(tg);
        rbTwos.setToggleGroup(tg);
        rbThrees.setToggleGroup(tg);
        rbFours.setToggleGroup(tg);
        rbFives.setToggleGroup(tg);
        rbSixes.setToggleGroup(tg);
        rbThreeOfKind.setToggleGroup(tg);
        rbFourOfKind.setToggleGroup(tg);
        rbFH.setToggleGroup(tg);
        rbSS.setToggleGroup(tg);
        rbLS.setToggleGroup(tg);
        rbYatzy.setToggleGroup(tg);
        rbChance.setToggleGroup(tg);
        
        db = new DBDefaultUtil();
    }

    @FXML
    private void button2(ActionEvent event) {
        //checks if the button is blocked
        blockiert[1] = !blockiert[1];
        if (blockiert[1]) {
            button2.setStyle("-fx-background-color:#ff0000;");
        } else {
            button2.setStyle("-fx-background-color:#ffffff;");
        }
    }

    @FXML
    private void button1(ActionEvent event) {
        //checks if the button is blocked
        blockiert[0] = !blockiert[0];
        if (blockiert[0]) {
            button1.setStyle("-fx-background-color:#ff0000;");
        } else {
            button1.setStyle("-fx-background-color:#ffffff;");
        }

    }

    @FXML
    private void button3(ActionEvent event) {
        //checks if the button is blocked
        blockiert[2] = !blockiert[2];
        if (blockiert[2]) {
            button3.setStyle("-fx-background-color:#ff0000;");
        } else {
            button3.setStyle("-fx-background-color:#ffffff;");
        }
    }

    @FXML
    private void button4(ActionEvent event) {
        //checks if the button is blocked
        blockiert[3] = !blockiert[3];
        if (blockiert[3]) {
            button4.setStyle("-fx-background-color:#ff0000;");
        } else {
            button4.setStyle("-fx-background-color:#ffffff;");
        }
    }

    @FXML
    private void button5(ActionEvent event) {
        //checks if the button is blocked
        blockiert[4] = !blockiert[4];
        if (blockiert[4]) {
            button5.setStyle("-fx-background-color:#ff0000;");
        } else {
            button5.setStyle("-fx-background-color:#ffffff;");
        }

    }

    @FXML
    private void btnConfirm(ActionEvent event) {
        //check in which round you are
        if (round < 13) {
            cancel = 0;
            round++;
            rollstxt = 2;
            btnRoll.setText("Roll[3]");
            dice[0] = dice1;
            blockiert[0] = false;
            dice[1] = dice2;
            blockiert[1] = false;
            dice[2] = dice3;
            blockiert[2] = false;
            dice[3] = dice4;
            blockiert[3] = false;
            dice[4] = dice5;
            blockiert[4] = false;
            //Sets the colour of all buttons back to white
            button1.setStyle("-fx-background-color:#ffffff:");
            button2.setStyle("-fx-background-color:#ffffff:");
            button3.setStyle("-fx-background-color:#ffffff:");
            button4.setStyle("-fx-background-color:#ffffff:");
            button5.setStyle("-fx-background-color:#ffffff:");
            String rounds = String.valueOf(round);
            lbRound.setText(rounds);
            //Sets all dices to the first picture
            iv1.setImage(new Image(getClass().getResourceAsStream("/images/Yatzy/" + 1 + ".png")));
            iv2.setImage(new Image(getClass().getResourceAsStream("/images/Yatzy/" + 1 + ".png")));
            iv3.setImage(new Image(getClass().getResourceAsStream("/images/Yatzy/" + 1 + ".png")));
            iv4.setImage(new Image(getClass().getResourceAsStream("/images/Yatzy/" + 1 + ".png")));
            iv5.setImage(new Image(getClass().getResourceAsStream("/images/Yatzy/" + 1 + ".png")));
            //If a radio is selected it gets disabled and the method gets started
            if (rbOnes.isSelected() == true) {
                lbOnes.setText(String.valueOf(value.ones()));
                total += value.ones();
                rbOnes.setOpacity(0.5);
                rbOnes.setDisable(true);
            }
            if (rbTwos.isSelected() == true) {
                lbTwos.setText(String.valueOf(value.twos()));
                total += value.twos();
                rbTwos.setOpacity(0.5);
                rbTwos.setDisable(true);
            }
            if (rbThrees.isSelected() == true) {
                lbThrees.setText(String.valueOf(value.threes()));
                total += value.threes();
                rbThrees.setOpacity(0.5);
                rbThrees.setDisable(true);
            }
            if (rbFours.isSelected() == true) {
                lbFours.setText(String.valueOf(value.fours()));
                total += value.fours();
                rbFours.setOpacity(0.5);
                rbFours.setDisable(true);
            }
            if (rbFives.isSelected() == true) {
                lbFives.setText(String.valueOf(value.fives()));
                total += value.fives();
                rbFives.setOpacity(0.5);
                rbFives.setDisable(true);
            }
            if (rbSixes.isSelected() == true) {
                lbSixes.setText(String.valueOf(value.sixes()));
                total += value.sixes();
                rbSixes.setOpacity(0.5);
                rbSixes.setDisable(true);
            }
            if (rbThreeOfKind.isSelected() == true) {
                lbThreeOfKind.setText(String.valueOf(value.threeOfKind()));
                total += value.threeOfKind();
                rbThreeOfKind.setOpacity(0.5);
                rbThreeOfKind.setDisable(true);
            }
            if (rbFourOfKind.isSelected() == true) {
                lbFourOfKind.setText(String.valueOf(value.fourOfKind()));
                total += value.fourOfKind();
                rbFourOfKind.setOpacity(0.5);
                rbFourOfKind.setDisable(true);
            }
            if (rbFH.isSelected() == true) {
                lbFH.setText(String.valueOf(value.fullHouse()));
                total += value.fullHouse();
                rbFH.setOpacity(0.5);
                rbFH.setDisable(true);
            }
            if (rbSS.isSelected() == true) {
                lbSS.setText(String.valueOf(value.smallStraight()));
                total += value.smallStraight();
                rbSS.setOpacity(0.5);
                rbSS.setDisable(true);
            }
            if (rbLS.isSelected() == true) {
                lbLS.setText(String.valueOf(value.largeStraight()));
                total += value.largeStraight();
                rbLS.setOpacity(0.5);
                rbLS.setDisable(true);
            }
            if (rbChance.isSelected() == true) {
                lbChance.setText(String.valueOf(value.chance()));
                rbChance.setOpacity(0.5);
                rbChance.setDisable(true);
                rbChance.setSelected(false);
                total += value.chance();
            }
            if (rbYatzy.isSelected() == true) {
                lbYatzy.setText(String.valueOf(value.yatzy()));
                total += value.yatzy();
                rbYatzy.setOpacity(0.5);
                rbYatzy.setDisable(true);
            }
            total1 = String.valueOf(total);
            lbTotal.setText(total1);
            opponent.check();
            //From here the opponent starts playing
            if (opponent.getLab() == "Ones") {
                lbOnes2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "Twos") {
                lbTwos2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "Threes") {
                lbThrees2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "Fours") {
                lbFours2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "Fives") {
                lbFives2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "Sixes") {
                lbSixes2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "ThreeOfKind") {
                lbThreeOfKind2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "FourOfKind") {
                lbFourOfKind2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "FullHouse") {
                lbFH2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "SmallStraight") {
                lbSmallStraight2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "LargeStraight") {
                lbLargeStraight2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "Yatzy") {
                lbYatzy2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            if (opponent.getLab() == "Chance") {
                lbChance2.setText(String.valueOf(opponent.getBiggest()));
                total2 += opponent.getBiggest();
            }
            totalo = String.valueOf(total2);
            lbTotal2.setText(totalo);
            WinTieLose();
        }
    }

    @FXML
    //back to the start site of yatzy
    private void restart(ActionEvent event) throws IOException {
        mainApp.showYatzy(player);
        this.stage.close();
    }

    @FXML
    //back to the casino
    private void exit(ActionEvent event) throws IOException {
        
        mainApp.showMenuView(player);
        this.stage.close();
        
    }

    @FXML
    //Arrows get visible
    private void help(ActionEvent event) {
        if (help == true) {
            imvHelp1.setOpacity(1);
            imvHelp2.setOpacity(1);
            imvHelp3.setOpacity(1);
            imvHelp4.setOpacity(1);
            lbHelp1.setOpacity(1);
            lbHelp2.setOpacity(1);
            lbHelp3.setOpacity(1);
            lbHelp4.setOpacity(1);
            help = false;
        } else if (help == false) {
            imvHelp1.setOpacity(0);
            imvHelp2.setOpacity(0);
            imvHelp3.setOpacity(0);
            imvHelp4.setOpacity(0);
            lbHelp1.setOpacity(0);
            lbHelp2.setOpacity(0);
            lbHelp3.setOpacity(0);
            lbHelp4.setOpacity(0);
            help = true;
        }

    }

    @FXML
    private void bright(MouseEvent event) {
        btnRestart.setOpacity(1);
        btnHelp.setOpacity(1);
        btnExit.setOpacity(1);
        btnClose.setOpacity(1);
    }

    @FXML
    private void notBright(MouseEvent event) {
        btnRestart.setOpacity(0.5);
        btnHelp.setOpacity(0.5);
        btnExit.setOpacity(0.5);
        btnClose.setOpacity(0.5);

    }

    @FXML
    private void btnClose(ActionEvent event) {
        db.updateAccountBalance(player.getEmail(), 0-bet);
        System.exit(0);
    }
    
    //Checks if you won,lost or tied
    public void WinTieLose() {
        if (round == 13) {
            
            Date date = new Date();
            
            if (Integer.parseInt(total1) > Integer.parseInt(totalo)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("WIN");
                alert.setContentText("You WON!  ");
                alert.showAndWait();
                
                db.updateAccountBalance(player.getEmail(), bet);
                
                db.updateStats(player, "Yatzy", bet*2, 0, bet, date);
                
            }
            if (Integer.parseInt(total1) == Integer.parseInt(totalo)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("TIE");
                alert.setContentText("YOU TIED!  " );
                alert.showAndWait();
                
                db.updateStats(player, "Yatzy", bet, 0, bet, date);

            }
            if (Integer.parseInt(total1) < Integer.parseInt(totalo)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("LOSE");
                alert.setContentText("YOU LOSE! ");
                alert.showAndWait();
               
                db.updateAccountBalance(player.getEmail(), 0-bet);
                db.updateStats(player, "Yatzy", 0, bet, bet, date);

            }
            
            this.lbBalance1.setText(Integer.toString(db.getAccountBalance(player.getEmail())));
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        lbBalance1.setText(Integer.toString(db.getAccountBalance(player.getEmail())));
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

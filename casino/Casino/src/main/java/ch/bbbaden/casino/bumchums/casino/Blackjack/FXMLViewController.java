/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.Blackjack;

import static ch.bbbaden.casino.bumchums.casino.Blackjack.CalculatorPlayer.lost;
import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import java.io.IOException;
import static java.lang.Math.random;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Startklar
 */
public class FXMLViewController implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label lbl_points;
    private Alert info;
    private Alert alert;
    private Alert confirm;
    private int count = 0;
    int firstCard, secondCard, thirdCard, fourthCard, fifthCard, sixthCard, seventhCard, eigththCard, ninthCard, tenthCard;
    double bet, luck, granted, doub, doubi, totalWin;
    int result, GroupCount;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private ImageView groupView, groupView2, groupView3, groupView4, groupView5, groupView6, groupView7, groupView8, groupView9, groupView10, black;
    private boolean stop = false;
    private ImageView[] groupviews = {groupView, groupView2, groupView3, groupView4, groupView5, groupView6, groupView7, groupView8, groupView9, groupView10};
    @FXML
    private ImageView thirdcardView, forthcardView, fifthcardView, secondcardView, cardView, sixthcardView, seventhcardView, eighthcardView, ninthcardView, tenthcardView;
    private ImageView[] imageviews = {thirdcardView, forthcardView, fifthcardView, secondcardView, cardView, sixthcardView, seventhcardView, eighthcardView, ninthcardView, tenthcardView};
    private boolean isFirstDraw = true;
    private boolean isSecondDraw = true;
    private boolean isThirdDraw = true;
    private boolean isFourthDraw = true;
    private boolean isFifthDraw = true;
    private boolean isSixthDraw = true;
    private boolean isSeventhDraw = true;
    private boolean isEighthDraw = true;
    private boolean isNinthDraw = true;
    private boolean hold = false;
    private boolean holdBank = true;
    private boolean cancel = false;
    private boolean helpOff = false;
    private boolean insurance = false;
    private boolean betOn = true;
    private boolean insuranceSet = false;
    private boolean firstcardAce = false;
    private boolean insuranceWon = false;
    private boolean insuranceLost = false;
    private boolean gameRunning = false;
    boolean lost = false;
    private boolean isTimesTwo = false;
    private boolean masterSet = false;
    private boolean wrong = false;
    boolean firstcard = true;
    boolean finish = false;
    @FXML
    private Button setInsurance;
    private boolean stopGroup = false;
    private String b, number;
    private double finalinsur;
    private static double credResult;
    int firstIntercard, secondIntercard, thirdIntercard, fourthIntercard, fifthIntercard, sixthIntercard, seventhIntercard, eigththIntercard, ninthIntercard, tenthIntercard, resultGroup, x, i, master;
    private Image image20, image80, image30, image40, image50, image60, image70, image100, image90, image110;
    private Image[] image = {image20, image30, image40, image50, image60, image70, image80, image90, image100, image110};
    private String num20, num30, num40, num50, num60, num70, num80, num90, num100, num110;
    private String[] num = {num20, num30, num40, num50, num60, num70, num80, num90, num100, num110};
    private Integer number20, number30, number40, number50, number60, number70, number80, number90, number100, number110;
    private Integer[] numbers = {number20, number30, number40, number50, number60, number70, number80, number90, number100, number110};
    @FXML
    private Button button, furtherButton;
    ArrayList<Card> cards = new ArrayList<>();
    private String[] types = {"H", "D", "S", "C"};
    private String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    Integer[] ncards = {firstCard, secondCard, thirdCard, fourthCard, fifthCard, sixthCard, seventhCard, eigththCard, ninthCard, tenthCard};
    Integer[] interCards = {firstIntercard, secondIntercard, thirdIntercard, fourthIntercard, fifthIntercard, sixthIntercard, seventhIntercard, eigththIntercard, ninthIntercard, tenthIntercard};
    int counter = 0;
    private CalculatorPlayer calcPlayer;
    private CalculatorGroupier calcGroupier;
    Bank bank;
    private Card card;
    @FXML
    private ImageView view1;
    private boolean nope = true;
    @FXML
    private Label lbl_pointsGroup;
    @FXML
    private TextField gameBet;
    @FXML
    private ImageView arrow2;
    @FXML
    private ImageView arrow1;
    @FXML
    private Label arrowText1;
    @FXML
    private Label arrowText2;
    @FXML
    private Label user2;
    @FXML
    private Label money2;
    @FXML
    private ImageView arrow3;
    @FXML
    private Label arrowText3;
    @FXML
    private ImageView times2;
    @FXML
    private ImageView arrow4;
    @FXML
    private Label arrowText4;
    @FXML
    private Button holdCards;
    @FXML
    private ImageView Back;
    @FXML
    private Label helpM;
    @FXML
    private ImageView returnTo;
    @FXML
    private Button re;
    @FXML
    private Label nam;
    @FXML
    private ImageView icon;
    @FXML
    private Label nam2;
    @FXML
    private TextField betInsur;
    private MainApp mainApp;
    private Player player;
    private Stage stage;
    private DBDefaultUtil db;
    Username username;
	private ImageView gang2;
	private ImageView gang1;

    //Die Ersten Karten des Spielers werden gezogen und die Wette wird gesetzt.
    @FXML
    private void drawFirstCards(ActionEvent event) {
        times2.setDisable(false);
        boolean gameStart = false;
        boolean wrongInput = false;
        gameBet.setOpacity(0);
        holdBank = false;
        stopGroup = true;
        gameRunning = true;
        try {
            label3.setText(gameBet.getText());
            granted = Double.parseDouble(gameBet.getText());
            double cash = bank.getCredit();
            double r = bank.getCredit() - granted;

            if (granted > cash) {
                wrong = true;
                gameStart = true;
                wrongInput = true;
            } else if (granted <= 0 || gameBet.getText() == "") {
                wrong = true;
                gameStart = true;
                wrongInput = true;
            } else {
                label3.setText(gameBet.getText());
                money2.setText(String.valueOf(r));
            }
        } catch (Exception e) {
            wrongInput = true;
        }
        System.out.println("oh");

        if (isFirstDraw == true && granted != 0 && gameStart == false && wrongInput == false) {

            Random randomNumber = new Random();
            int number = randomNumber.nextInt(51);
            cards.remove(number);
            int secondNumber = randomNumber.nextInt(cards.size() - 1);
            Image image = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(number).getName() + cards.get(number).getType() + ".png"));
            Image image2 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(secondNumber).getName() + cards.get(secondNumber).getType() + ".png"));

            String num = cards.get(number).getName();
            String num2 = cards.get(secondNumber).getName();
            ncards[0] = Integer.parseInt(num);
            ncards[1] = Integer.parseInt(num2);
            cards.remove(secondNumber);
            cardView.setImage(image);
            secondcardView.setImage(image2);
            gameStart = true;
            count++;
            isFirstDraw = false;
            for (int i = 0; i <= 1; i++) {
                if (ncards[i] == 12 || ncards[i] == 11 || ncards[i] == 13) {
                    ncards[i] = 10;
                } else if (ncards[i] == 1) {
                    ncards[i] = 11;
                }
            }
            number20 = randomNumber.nextInt(cards.size() - 1);
            //Die aufgerufene Karte wird aus der Arraylist entfernt
            cards.remove(number20);
            //Karte wird aufgerufen
            image20 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(number20).getName() + cards.get(number20).getType() + ".png"));
            //Die Kartennummer wird als String gespeichert
            num20 = cards.get(number20).getName();
            //DIe Kartennummer wird als Integer in einen Array gespeichert.
            interCards[0] = Integer.parseInt(num20);
            if (interCards[0] == 12 || interCards[0] == 11 || interCards[0] == 13) {
                interCards[0] = 10;
            } else if (interCards[0] == 1) {
                interCards[0] = 11;
            }
            GroupCount++;
            lbl_pointsGroup.setText(String.valueOf(interCards[0]));
            groupView.setImage(image20);
            if (interCards[0] == 11) {
                betInsur.setOpacity(1);
                setInsurance.setDisable(false);
                insuranceSet = true;
            }
            calcPlayer.calculate(ncards, count, cards);
        } else if (gameStart == true && wrongInput == false) {
            alert.setContentText("The game is already running");
            alert.showAndWait();
        } else if (granted == 0 && wrongInput == false) {
            alert.setContentText("You must bet before starting the game");
            alert.showAndWait();
        } else if (wrong == true) {

            money2.setText(String.valueOf(bank.getCredit()));
            label3.setText("0");
            alert.setContentText("Wrong input!");
            alert.showAndWait();
            gameBet.setOpacity(1);
            gameBet.setText("");
        } else if (gameBet.getText() == null || granted == 0) {
            alert.setContentText("Your bet must be valid to start the game");
            alert.showAndWait();
            gameBet.setOpacity(1);
        }
    }

    //Weitere karten können hier freiwillig gezogen werden.
    @FXML
    private void furtherDraws(ActionEvent event) {
        System.out.println("yu");
        holdCards.setDisable(false);
        if (gameRunning == true) {
            do {
                try {
                    if (betOn == true) {
//                        bet = bank.getCredit();
                        b = gameBet.getText();
                        bet = Integer.parseInt(b);
                        Double lastBet = Double.parseDouble(label3.getText());
//                        double finalBet = lastBet + bet;
                        b = String.valueOf(lastBet);
                        label3.setText(b);
                        betOn = false;
                    }
                } catch (Exception e) {
                }

            } while (bank.getCredit() < bet);
            gameBet.setOpacity(0);
            if (isFirstDraw == false && isSecondDraw == true && hold == false && cancel == false && masterSet == false) {

                Random randomNumber = new Random();
                int thirdNumber = randomNumber.nextInt(cards.size() - 1);
                Image image3 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(thirdNumber).getName() + cards.get(thirdNumber).getType() + ".png"));
                String num = cards.get(thirdNumber).getName();
                cards.remove(thirdNumber);
                ncards[2] = Integer.parseInt(num);
                thirdcardView.setImage(image3);
                isSecondDraw = false;
                count = 2;
                if (ncards[2] == 12 || ncards[2] == 11 || ncards[2] == 13) {
                    ncards[2] = 10;
                } else if (ncards[2] == 1) {
                    ncards[2] = 11;
                }
                calcPlayer.calculate(ncards, count, cards);
            } else if (isSecondDraw == false && isThirdDraw == true && hold == false && cancel == false && masterSet == false) {
                Random randomNumber = new Random();
                int fourthNumber = randomNumber.nextInt(cards.size() - 1);
                Image image4 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(fourthNumber).getName() + cards.get(fourthNumber).getType() + ".png"));
                String num = cards.get(fourthNumber).getName();
                cards.remove(fourthNumber);
                ncards[3] = Integer.parseInt(num);
                forthcardView.setImage(image4);
                isThirdDraw = false;
                count = 3;
                if (ncards[3] == 12 || ncards[3] == 11 || ncards[3] == 13) {
                    ncards[3] = 10;
                } else if (ncards[3] == 1) {
                    ncards[3] = 11;
                }
                calcPlayer.calculate(ncards, count, cards);
            } else if (isFourthDraw == true && hold == false && cancel == false && masterSet == false) {
                Random randomNumber = new Random();
                int fifthNumber = randomNumber.nextInt(cards.size() - 1);
                Image image5 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(fifthNumber).getName() + cards.get(fifthNumber).getType() + ".png"));
                String num = cards.get(fifthNumber).getName();
                cards.remove(fifthNumber);
                ncards[4] = Integer.parseInt(num);
                fifthcardView.setImage(image5);
                isFourthDraw = false;
                count = 4;
                if (ncards[4] == 12 || ncards[4] == 11 || ncards[4] == 13) {
                    ncards[4] = 10;
                } else if (ncards[4] == 1) {
                    ncards[4] = 11;
                }
                calcPlayer.calculate(ncards, count, cards);
            } else if (isFifthDraw == true && hold == false && cancel == false && masterSet == false) {
                Random randomNumber = new Random();
                int sixthNumber = randomNumber.nextInt(cards.size() - 1);
                Image image6 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(sixthNumber).getName() + cards.get(sixthNumber).getType() + ".png"));
                String num = cards.get(sixthNumber).getName();
                cards.remove(sixthNumber);
                ncards[5] = Integer.parseInt(num);
                sixthcardView.setImage(image6);
                isFifthDraw = false;
                count = 5;
                if (ncards[5] == 12 || ncards[5] == 11 || ncards[5] == 13) {
                    ncards[5] = 10;
                } else if (ncards[5] == 1) {
                    ncards[5] = 11;
                }
                calcPlayer.calculate(ncards, count, cards);
            } else if (isSixthDraw == true && hold == false && cancel == false && masterSet == false) {
                Random randomNumber = new Random();
                int seventhNumber = randomNumber.nextInt(cards.size() - 1);
                Image image7 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(seventhNumber).getName() + cards.get(seventhNumber).getType() + ".png"));
                String num = cards.get(seventhNumber).getName();
                cards.remove(seventhNumber);
                ncards[6] = Integer.parseInt(num);
                seventhcardView.setImage(image7);
                isSixthDraw = false;
                count = 6;
                if (ncards[6] == 12 || ncards[6] == 11 || ncards[6] == 13) {
                    ncards[6] = 10;
                } else if (ncards[6] == 1) {
                    ncards[6] = 11;
                }
                calcPlayer.calculate(ncards, count, cards);
            } else if (isSeventhDraw == true && hold == false && cancel == false && masterSet == false) {
                Random randomNumber = new Random();
                int eigththNumber = randomNumber.nextInt(cards.size() - 1);
                Image image8 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(eigththNumber).getName() + cards.get(eigththNumber).getType() + ".png"));
                String num = cards.get(eigththNumber).getName();
                cards.remove(eigththNumber);
                ncards[7] = Integer.parseInt(num);
                eighthcardView.setImage(image8);
                isSeventhDraw = false;
                count = 7;
                if (ncards[7] == 12 || ncards[7] == 11 || ncards[7] == 13) {
                    ncards[7] = 10;
                } else if (ncards[7] == 1) {
                    ncards[7] = 11;
                }
                calcPlayer.calculate(ncards, count, cards);
            } else if (isEighthDraw == true && hold == false && cancel == false && masterSet == false) {
                Random randomNumber = new Random();
                int ninthNumber = randomNumber.nextInt(cards.size() - 1);
                Image image9 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(ninthNumber).getName() + cards.get(ninthNumber).getType() + ".png"));
                String num = cards.get(ninthNumber).getName();
                cards.remove(ninthNumber);
                ncards[8] = Integer.parseInt(num);
                ninthcardView.setImage(image9);
                isEighthDraw = false;
                count = 8;
                if (ncards[8] == 12 || ncards[8] == 11 || ncards[8] == 13) {
                    ncards[8] = 10;
                } else if (ncards[8] == 1) {
                    ncards[8] = 11;
                }
                calcPlayer.calculate(ncards, count, cards);
            } else if (isNinthDraw == true && hold == false && cancel == false && masterSet == false) {
                Random randomNumber = new Random();
                int tenthNumber = randomNumber.nextInt(cards.size() - 1);
                Image image9 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(tenthNumber).getName() + cards.get(tenthNumber).getType() + ".png"));
                String num = cards.get(tenthNumber).getName();
                cards.remove(tenthNumber);
                ncards[9] = Integer.parseInt(num);
                tenthcardView.setImage(image9);
                isNinthDraw = false;
                count = 9;
                if (ncards[9] == 12 || ncards[9] == 11 || ncards[9] == 13) {
                    ncards[9] = 10;
                } else if (ncards[9] == 1) {
                    ncards[9] = 11;
                }
                calcPlayer.calculate(ncards, count, cards);
            } else if (hold = false && masterSet == false) {
                alert.setContentText("The game hasn't started yet.");
                alert.showAndWait();
            } else if (cancel = true && masterSet == false) {
                info.setContentText("The game has been stopped.");
                info.showAndWait();
            } else if (masterSet == true) {
                boolean fail = false;
                if (master == 0 && fail == false) {
                    fail = true;
                    Random randomNumber = new Random();
                    int thirdNumber = randomNumber.nextInt(cards.size() - 1);
                    Image image3 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(thirdNumber).getName() + cards.get(thirdNumber).getType() + ".png"));
                    String num = cards.get(thirdNumber).getName();
                    cards.remove(thirdNumber);
                    ncards[2] = Integer.parseInt(num);
                    thirdcardView.setImage(image3);
                    isSecondDraw = false;
                    count = 2;
                    if (ncards[2] == 12 || ncards[2] == 11 || ncards[2] == 13) {
                        ncards[2] = 10;
                    } else if (ncards[2] == 1) {
                        ncards[2] = 11;
                    }
                    furtherButton.setDisable(true);
                    calcPlayer.calculate(ncards, count, cards);
                } else if (master == 1 && fail == false) {
                    fail = true;
                    Random randomNumber = new Random();
                    int fourthNumber = randomNumber.nextInt(cards.size() - 1);
                    Image image4 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(fourthNumber).getName() + cards.get(fourthNumber).getType() + ".png"));
                    String num = cards.get(fourthNumber).getName();
                    cards.remove(fourthNumber);
                    ncards[3] = Integer.parseInt(num);
                    forthcardView.setImage(image4);
                    isThirdDraw = false;
                    count = 3;
                    if (ncards[3] == 12 || ncards[3] == 11 || ncards[3] == 13) {
                        ncards[3] = 10;
                    } else if (ncards[3] == 1) {
                        ncards[3] = 11;
                    }
                    furtherButton.setDisable(true);
                    calcPlayer.calculate(ncards, count, cards);
                } else if (master == 2 && fail == false) {
                    fail = true;
                    Random randomNumber = new Random();
                    int fifthNumber = randomNumber.nextInt(cards.size() - 1);
                    Image image5 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(fifthNumber).getName() + cards.get(fifthNumber).getType() + ".png"));
                    String num = cards.get(fifthNumber).getName();
                    cards.remove(fifthNumber);
                    ncards[4] = Integer.parseInt(num);
                    fifthcardView.setImage(image5);
                    isFourthDraw = false;
                    count = 4;
                    if (ncards[4] == 12 || ncards[4] == 11 || ncards[4] == 13) {
                        ncards[4] = 10;
                    } else if (ncards[4] == 1) {
                        ncards[4] = 11;
                    }
                    furtherButton.setDisable(true);
                    calcPlayer.calculate(ncards, count, cards);
                } else if (master == 3 && fail == false) {
                    fail = true;
                    Random randomNumber = new Random();
                    int sixthNumber = randomNumber.nextInt(cards.size() - 1);
                    Image image6 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(sixthNumber).getName() + cards.get(sixthNumber).getType() + ".png"));
                    String num = cards.get(sixthNumber).getName();
                    cards.remove(sixthNumber);
                    ncards[5] = Integer.parseInt(num);
                    sixthcardView.setImage(image6);
                    isFifthDraw = false;
                    count = 5;
                    if (ncards[5] == 12 || ncards[5] == 11 || ncards[5] == 13) {
                        ncards[5] = 10;
                    } else if (ncards[5] == 1) {
                        ncards[5] = 11;
                    }
                    furtherButton.setDisable(true);
                    calcPlayer.calculate(ncards, count, cards);
                } else if (master == 4 && fail == false) {
                    fail = true;
                    Random randomNumber = new Random();
                    int seventhNumber = randomNumber.nextInt(cards.size() - 1);
                    Image image7 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(seventhNumber).getName() + cards.get(seventhNumber).getType() + ".png"));
                    String num = cards.get(seventhNumber).getName();
                    cards.remove(seventhNumber);
                    ncards[6] = Integer.parseInt(num);
                    seventhcardView.setImage(image7);
                    isSixthDraw = false;
                    count = 6;
                    if (ncards[6] == 12 || ncards[6] == 11 || ncards[6] == 13) {
                        ncards[6] = 10;
                    } else if (ncards[6] == 1) {
                        ncards[6] = 11;
                    }
                    furtherButton.setDisable(true);
                    calcPlayer.calculate(ncards, count, cards);
                } else if (master == 5 && fail == false) {
                    fail = true;
                    Random randomNumber = new Random();
                    int eigththNumber = randomNumber.nextInt(cards.size() - 1);
                    Image image8 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(eigththNumber).getName() + cards.get(eigththNumber).getType() + ".png"));
                    String num = cards.get(eigththNumber).getName();
                    cards.remove(eigththNumber);
                    ncards[7] = Integer.parseInt(num);
                    eighthcardView.setImage(image8);
                    isSeventhDraw = false;
                    count = 7;
                    if (ncards[7] == 12 || ncards[7] == 11 || ncards[7] == 13) {
                        ncards[7] = 10;
                    } else if (ncards[7] == 1) {
                        ncards[7] = 11;
                    }
                    furtherButton.setDisable(true);
                    calcPlayer.calculate(ncards, count, cards);
                } else if (master == 6 && fail == false) {
                    fail = true;
                    Random randomNumber = new Random();
                    int ninthNumber = randomNumber.nextInt(cards.size() - 1);
                    Image image9 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(ninthNumber).getName() + cards.get(ninthNumber).getType() + ".png"));
                    String num = cards.get(ninthNumber).getName();
                    cards.remove(ninthNumber);
                    ncards[8] = Integer.parseInt(num);
                    ninthcardView.setImage(image9);
                    isEighthDraw = false;
                    count = 8;
                    if (ncards[8] == 12 || ncards[8] == 11 || ncards[8] == 13) {
                        ncards[8] = 10;
                    } else if (ncards[8] == 1) {
                        ncards[8] = 11;
                    }
                    furtherButton.setDisable(true);
                    calcPlayer.calculate(ncards, count, cards);
                } else if (master == 7 && fail == false) {
                    fail = true;
                    Random randomNumber = new Random();
                    int tenthNumber = randomNumber.nextInt(cards.size() - 1);
                    Image image9 = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(tenthNumber).getName() + cards.get(tenthNumber).getType() + ".png"));
                    String num = cards.get(tenthNumber).getName();
                    cards.remove(tenthNumber);
                    ncards[9] = Integer.parseInt(num);
                    tenthcardView.setImage(image9);
                    isNinthDraw = false;
                    count = 9;
                    if (ncards[9] == 12 || ncards[9] == 11 || ncards[9] == 13) {
                        ncards[9] = 10;
                    } else if (ncards[9] == 1) {
                        ncards[9] = 11;
                    }
                    furtherButton.setDisable(true);
                    calcPlayer.calculate(ncards, count, cards);
                }
            }
            alert.setContentText("Game hasn't started yet.");
        }

    }

    //Hier werden die Punkte angezeigt.
    public void hold(boolean holdBank, int resultBank, int result) {
        holdBank = true;
        if (nope == true) {
            final String resultPlayer = String.valueOf(result);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lbl_points.setText(resultPlayer);
                }
            });

        } else {
            final String resultGroupier = String.valueOf(resultBank);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lbl_pointsGroup.setText(resultGroupier);
                }
            });

        }
    }

    //Damit kann man seine Karten halten und der ZUg wird an den Groupier weitergeleitet.
    @FXML
    private void viewClicked(ActionEvent event) {
        nope = false;
        hold = true;
        boolean grouphold = true;
        GroupCount = 1;
        counter = 1;
        boolean firstcard = true;
        Random randomNumber = new Random();
        if (stopGroup == true && calcPlayer.isStop() == false) {
            for (counter = 1; counter <= 9; counter++) {
                resultGroup = calcGroupier.getResultBank();
                if (resultGroup <= 16 || grouphold == true) {
                    numbers[counter] = randomNumber.nextInt(cards.size() - 1);
                    //Die aufgerufene Karte wird aus der Arraylist entfernt
                    cards.remove(numbers[counter]);
                    //Karte wird aufgerufen
                    image[counter] = new Image(getClass().getResourceAsStream("/images/Blackjack/" + cards.get(numbers[counter]).getName() + cards.get(numbers[counter]).getType() + ".png"));
                    //Die Kartennummer wird als String gespeichert
                    num[counter] = cards.get(numbers[counter]).getName();
                    //DIe Kartennummer wird als Integer in einen Array gespeichert.
                    interCards[counter] = Integer.parseInt(num[counter]);
                    if (interCards[counter] == 12 || interCards[counter] == 11 || interCards[counter] == 13) {
                        interCards[counter] = 10;
                    } else if (interCards[counter] == 1) {
                        interCards[counter] = 11;
                    }
                    GroupCount++;
                    if (counter == 1 && firstcard == true) {
                        firstcardAce = true;
                        firstcard = false;
                        grouphold = false;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(CalculatorPlayer.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        groupView2.setImage(image[counter]);
                        calcGroupier.calculates(interCards, GroupCount, holdBank, cards, counter);
                    } else if (counter == 2 && firstcard == false) {
                        groupView3.setImage(image[counter]);
                        calcGroupier.calculates(interCards, GroupCount, holdBank, cards, counter);
                    } else if (counter == 3 && firstcard == false) {
                        groupView4.setImage(image[counter]);
                        calcGroupier.calculates(interCards, GroupCount, holdBank, cards, counter);
                    } else if (counter == 4 && firstcard == false) {
                        groupView5.setImage(image[counter]);
                        calcGroupier.calculates(interCards, GroupCount, holdBank, cards, counter);
                    } else if (counter == 5 && firstcard == false) {
                        groupView6.setImage(image[counter]);
                        calcGroupier.calculates(interCards, GroupCount, holdBank, cards, counter);
                    } else if (counter == 6 && firstcard == false) {
                        groupView7.setImage(image[counter]);
                        calcGroupier.calculates(interCards, GroupCount, holdBank, cards, counter);
                    } else if (counter == 7 && firstcard == false) {
                        groupView8.setImage(image[counter]);
                        calcGroupier.calculates(interCards, GroupCount, holdBank, cards, counter);
                    } else if (counter == 8 && firstcard == false) {
                        groupView9.setImage(image[counter]);
                        calcGroupier.calculates(interCards, GroupCount, holdBank, cards, counter);
                    } else if (counter == 9 && firstcard == false) {
                        groupView10.setImage(image[counter]);
                        calcGroupier.calculates(interCards, GroupCount, holdBank, cards, counter);
                    }

                } else {
                    if (stop == false && stopGroup == true && firstcard == false) {
                        calcPlayer.MasterMind(resultGroup, stop, hold);
                        stop = true;
                    }
                }

            }

        } else {
            if (calcPlayer.isStop() == true) {
                info.setContentText("You already lost the game.");
                info.showAndWait();
            } else {
                info.setContentText("The game hasn't started yet");
                info.showAndWait();
            }

        }

    }

    //Hier werden die Resultate aktualiesiert.
    public void syncCalculator(int result) {
        calcPlayer.MasterMind(result, stop, hold);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        black.setOpacity(0);
        betInsur.setOpacity(0);
        setInsurance.setDisable(true);
        try {
            bank.getCredit();
            String zaster = String.valueOf(bank.getCredit());
            money2.setText(zaster);
        } catch (Exception e) {
        }

        initCardList();
        info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText(null);
        info.setResizable(false);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setResizable(false);
        confirm = new Alert(Alert.AlertType.INFORMATION);
        confirm.setHeaderText(null);
        confirm.setResizable(false);
        this.calcPlayer = new CalculatorPlayer();
        this.calcPlayer.setCon(this);
        this.calcGroupier = new CalculatorGroupier();
        this.calcGroupier.setCon(this);
        this.bank = new Bank();

        db = new DBDefaultUtil();
    }

    //Hiermit hole ich das Guthaben des Spielers.
    public static double getCredResult() {
        return credResult;
    }

    //Meldung und Berechnung des gewonnenen Betrages.
    public void showLabelWon(boolean won) {
        finish = true;
        if (insuranceSet == true) {
            int a = Integer.parseInt(lbl_pointsGroup.getText());
            if (a == 21) {
                luck = finalinsur * 2;
                insuranceWon = true;
            } else {
                luck = finalinsur;
                insuranceLost = true;
            }
        }
        if (insuranceWon == true && calcPlayer.isWon() == true) {
            doub = Double.parseDouble(label3.getText());
            double game = granted * 2;
            totalWin = game + luck;
            view1.setOpacity(0);
            Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
            info.setContentText("You won: " + totalWin);
            info.showAndWait();
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            holdCards.setDisable(true);
            view1.setOpacity(1);
            String number = String.valueOf(totalWin);
            label4.setText(number);
            credResult = bank.getCredit() + totalWin;
            String cred = String.valueOf(credResult);
            money2.setText(cred);
            gameBet.setDisable(true);
            calcPlayer.forwardTo();
        } else if (insuranceLost == true && calcPlayer.isWon() == true) {
            doub = Double.parseDouble(label3.getText());
            double game = granted * 2;
            boolean u = false;
            if (granted > luck) {
                totalWin = game - luck;
            } else if (luck > game) {
                totalWin = luck - game;
                u = true;
            }
            view1.setOpacity(0);
            Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
            if (u == true) {
                info.setContentText("You lost: " + totalWin);
                info.showAndWait();
                number = String.valueOf(totalWin);
                label4.setText("-" + number);
            } else if (u == false) {
                info.setContentText("You won: " + totalWin);
                info.showAndWait();
                number = String.valueOf(totalWin);
                label4.setText(number);
            }
            view1.setOpacity(1);
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            holdCards.setDisable(true);
            credResult = bank.getCredit() + totalWin;
            String cred = String.valueOf(credResult);
            money2.setText(cred);
            gameBet.setDisable(true);
            calcPlayer.forwardTo();
        } else if (insuranceSet == false && calcPlayer.isWon() == true) {
            doub = Double.parseDouble(label3.getText());
            totalWin = doub * 2;
            view1.setOpacity(0);
            Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
            info.setContentText("You won: " + totalWin);
            info.showAndWait();
            view1.setOpacity(1);
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            holdCards.setDisable(true);
            number = String.valueOf(totalWin);
            label4.setText(number);
            credResult = bank.getCredit() + totalWin;
            String cred = String.valueOf(credResult);
            money2.setText(cred);
            label4.setText(number);
            gameBet.setDisable(true);
            calcPlayer.forwardTo();
        }

        Date date = new Date();
        db.updateAccountBalance(player.getEmail(), (int) totalWin);
        db.updateStats(player, "BlackJack", (int) totalWin, 0, (int) granted, date);
    }

    //Meldung und Berechnung des verlorenen Betrages.
    public void showLabelLost(boolean lost) {
        finish = true;
        if (insuranceSet == true) {
            int a = Integer.parseInt(lbl_pointsGroup.getText());
            if (a == 21) {
                luck = finalinsur * 2;
                insuranceWon = true;
            } else {
                luck = finalinsur;
                insuranceLost = true;
            }
        }
        boolean u = false;
        if (calcPlayer.isLost() == true && insuranceWon == true) {
            doubi = Double.parseDouble(label3.getText());
            if (granted > luck) {
                totalWin = granted - luck;
            } else if (luck > granted) {
                totalWin = luck - granted;
                u = true;
            }
            view1.setOpacity(0);
            Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
            if (u == true) {
                info.setContentText("You won: " + totalWin);
                info.showAndWait();
                number = String.valueOf(totalWin);
                label4.setText(number);
            } else if (u == false) {
                info.setContentText("You lost: " + totalWin);
                info.showAndWait();
                number = String.valueOf(totalWin);
                label4.setText("-" + number);
            }
            view1.setOpacity(1);
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            holdCards.setDisable(true);
            credResult = bank.getCredit() + 0;
            String cred = String.valueOf(credResult);
            money2.setText(cred);
            gameBet.setDisable(true);
            calcPlayer.forwardTo();
        } else if (calcPlayer.isLost() == true && insuranceLost == true) {
            doub = Double.parseDouble(label3.getText());
            totalWin = granted + luck;
            view1.setOpacity(0);
            Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
            info.setContentText("You lost: " + totalWin);
            info.showAndWait();
            view1.setOpacity(1);
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            holdCards.setDisable(true);
            number = String.valueOf(totalWin);
            label4.setText("-" + number);
            credResult = bank.getCredit() - totalWin;
            String cred = String.valueOf(credResult);
            money2.setText(cred);
            gameBet.setDisable(true);
            calcPlayer.forwardTo();
        } else if (insuranceSet == false && calcPlayer.isLost() == true) {
            totalWin = Double.parseDouble(label3.getText());
            view1.setOpacity(0);
            Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
            info.setContentText("You lost: " + totalWin);
            info.showAndWait();
            view1.setOpacity(1);
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            holdCards.setDisable(true);
            number = String.valueOf(totalWin);
            label4.setText("-" + number);
            credResult = bank.getCredit() - totalWin;
            String cred = String.valueOf(credResult);
            money2.setText(cred);
            gameBet.setDisable(true);
            calcPlayer.forwardTo();
        }
        calcPlayer.forwardTo();

        Date date = new Date();

        db.updateAccountBalance(player.getEmail(), 0 - ((int) totalWin));
        db.updateStats(player, "BlackJack", 0, 0 - ((int) totalWin), (int) granted, date);
    }

    //Meldung und Berechnung des gewonnenen Betrages.
    public void showLabelTie() {
        finish = true;
        if (insuranceSet == true) {
            if (insuranceLost == true) {
                double moni = Double.parseDouble(label3.getText());
                view1.setOpacity(0);
                boolean u = false;
                if (granted > luck) {
                    totalWin = granted - luck;
                } else if (luck < granted) {
                    totalWin = luck - granted;
                    u = true;
                }
                totalWin = granted + 0;
                Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
                if (u == true) {
                    info.setContentText("You Lost:" + totalWin);
                    info.showAndWait();
                    number = String.valueOf(totalWin);
                    label4.setText("-" + number);
                } else if (u == false) {
                    info.setContentText("You Lost:" + totalWin);
                    info.showAndWait();
                    number = String.valueOf(totalWin);
                    label4.setText("-" + number);
                }
                view1.setOpacity(1);
                Back.setImage(new Image("/images/Blackjack/sharp.png"));
                holdCards.setDisable(true);
                double l = Double.parseDouble(money2.getText());
                credResult = l + moni + granted;
                String cred = String.valueOf(credResult);
                money2.setText(cred);
                gameBet.setDisable(true);
                calcPlayer.forwardTo();
            } else if (insuranceWon == true) {
                    int a = Integer.parseInt(lbl_pointsGroup.getText());
                    if (a == 21) {
                        luck = finalinsur * 2;
                        insuranceWon = true;
                    } else {
                        luck = finalinsur;
                        insuranceLost = true;
                    }
                double moni = Double.parseDouble(label3.getText());
                view1.setOpacity(0);
                totalWin = granted + luck;
                Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
                info.setContentText("You won:" + totalWin);
                info.showAndWait();
                view1.setOpacity(1);
                Back.setImage(new Image("/images/Blackjack/sharp.png"));
                holdCards.setDisable(true);
                double l = Double.parseDouble(money2.getText());
                number = String.valueOf(totalWin);
                label4.setText(number);
                credResult = l + moni + granted;
                String cred = String.valueOf(credResult);
                money2.setText(cred);
                gameBet.setDisable(true);
                calcPlayer.forwardTo();

            }
        } else if (insuranceSet == false) {
            double moni = Double.parseDouble(label3.getText());
            view1.setOpacity(0);
            totalWin = granted + 0;
            Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
            info.setContentText("You won: nothing!");
            info.showAndWait();
            view1.setOpacity(1);
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            holdCards.setDisable(true);
            double l = Double.parseDouble(money2.getText());
            credResult = l + moni + granted;
            String cred = String.valueOf(credResult);
            money2.setText(cred);
            gameBet.setDisable(true);
            calcPlayer.forwardTo();
        }

        Date date = new Date();
        db.updateStats(player, "BlackJack", (int) granted, 0, (int) granted, date);

    }

    public void initCardList() {
        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++) {
                cards.add(new Card(j + 1, values[j], types[i]));
            }
        }
    }

    //Hier wird der Button "ViewClicked" anhand eines Booleans gesperrt.
    public void syncHold(int nothing) {
        cancel = true;
    }

    //Hier wird der Versicherungs-Button gesperrt.
    public void shout() {
        finish = true;
        if (insuranceSet == true) {
            int a = Integer.parseInt(lbl_pointsGroup.getText());
            if (a == 21) {
                luck = finalinsur * 2;
                insuranceWon = true;
            } else {
                luck = 0;
                insuranceLost = true;
            }
        }
        if (insuranceWon == true && calcPlayer.isWon() == true) {
            info.setContentText("BlackJack!");
            info.showAndWait();
            doub = Double.parseDouble(label3.getText());
            double game = doub * 1.5;
            totalWin = game + luck;
            view1.setOpacity(0);
            Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
            info.setContentText("You won: " + totalWin);
            info.showAndWait();
            view1.setOpacity(1);
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            holdCards.setDisable(true);
            String number = String.valueOf(totalWin);
            label4.setText(number);
            credResult = bank.getCredit() + totalWin;
            String cred = String.valueOf(credResult);
            money2.setText(cred);
            gameBet.setDisable(true);
            calcPlayer.forwardTo();
        } else if (insuranceLost == true && calcPlayer.isWon() == true) {
            doub = Double.parseDouble(label3.getText());
            boolean u = false;
            if (doub > luck) {
                totalWin = doub - luck;
            } else if (luck > doub) {
                totalWin = luck - doub;
                u = true;
            }
            view1.setOpacity(1);
            Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
            if (u == true) {
                info.setContentText("You lost: " + totalWin);
                info.showAndWait();
            } else if (u == false) {
                info.setContentText("You won: " + totalWin);
                info.showAndWait();
            }

            view1.setOpacity(0);
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            holdCards.setDisable(true);
            String number = String.valueOf(totalWin);
            credResult = bank.getCredit() + totalWin;
            String cred = String.valueOf(credResult);
            money2.setText(cred);
            gameBet.setDisable(true);
            calcPlayer.forwardTo();
        } else if (insuranceSet == false && calcPlayer.isWon() == true) {
            doub = Double.parseDouble(label3.getText());
            totalWin = doub * 1.5;
            view1.setOpacity(1);
            Back.setImage(new Image("/images/Blackjack/imageedit_2_2588039688.png"));
            info.setContentText("You won: " + totalWin);
            info.showAndWait();
            view1.setOpacity(0);
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            holdCards.setDisable(true);
            String number = String.valueOf(totalWin);
            credResult = bank.getCredit() + totalWin;
            String cred = String.valueOf(credResult);
            money2.setText(cred);
            label4.setText(number);
            gameBet.setDisable(true);
            calcPlayer.forwardTo();
        }
        Date date = new Date();
        db.updateStats(player, "BlackJack", (int) granted, 0, (int) granted, date);

    }

    //Hier kan man sich gegen einen BlackJack des Gegners versichern.
    @FXML
    private void insure(ActionEvent event) {
        boolean moron = false;
        if (insuranceSet == true) {

            try {
                double n = Double.parseDouble(money2.getText());
                finalinsur = Integer.parseInt(betInsur.getText());
                double k = Double.parseDouble(label3.getText());

                if (finalinsur <= 0 || finalinsur > n) {
                    moron = true;
                } else {
                    double gg = finalinsur + k;
                    label3.setText(String.valueOf(gg));
                    moron = false;
                }
            } catch (Exception e) {
                moron = true;
            }
            if (moron == true) {
                alert.setContentText("Wrong Inpuut!");
                alert.showAndWait();
            }
        } else {
            info.setContentText("You cannot insure yourself if the Groupier's first card is not an Ace.");
            info.showAndWait();
        }
    }

    //Hier kann man eine neue Runde starten.
    @FXML
    public void restart(ActionEvent event) throws IOException {
        if (finish == false) {
            double m = Double.parseDouble(money2.getText());
            double t = m - granted;
            money2.setText(String.valueOf(t));
            Date date = new Date();
            db.updateAccountBalance(player.getEmail(), 0 - ((int) totalWin));
            db.updateStats(player, "BlackJack", 0, 0 - ((int) totalWin), (int) granted, date);
        }
        money2.setText(String.valueOf(bank.getCredit()));
        mainApp.showBlackJackGame(player);
    }

    //Hier werden im Spieler hilfestellungen angezeigt.
    @FXML
    private void helpMe(MouseEvent event) {
        if (helpOff == false) {
            black.setOpacity(0.6);
            label1.setOpacity(0.3);
            label2.setOpacity(0.3);
            lbl_pointsGroup.setOpacity(0.3);
            lbl_points.setOpacity(0.3);
            arrow1.setOpacity(1);
            arrow2.setOpacity(1);
            arrow3.setOpacity(1);
            arrow4.setOpacity(1);
            arrowText1.setOpacity(1);
            arrowText2.setOpacity(1);
            arrowText3.setOpacity(1);
            arrowText4.setOpacity(1);
            re.setDisable(true);
            button.setDisable(true);
            furtherButton.setDisable(true);
            holdCards.setDisable(true);
            gameBet.setDisable(true);
            helpOff = true;
        } else if (helpOff == true) {
            Back.setImage(new Image("/images/Blackjack/sharp.png"));
            label1.setOpacity(1);
            label2.setOpacity(1);
            lbl_pointsGroup.setOpacity(1);
            lbl_points.setOpacity(1);
            black.setOpacity(0);
            arrow1.setOpacity(0);
            arrow2.setOpacity(0);
            arrow3.setOpacity(0);
            arrow4.setOpacity(0);
            arrowText1.setOpacity(0);
            arrowText2.setOpacity(0);
            arrowText3.setOpacity(0);
            arrowText4.setOpacity(0);
            gameBet.setDisable(false);
            re.setDisable(false);
            button.setDisable(false);
            furtherButton.setDisable(false);
            holdCards.setDisable(false);
            helpOff = false;
        }

    }

    //Hier wird man zum StartScreen weitergeleitet.
    @FXML
    private void retrunView(MouseEvent event) throws IOException {
        if (finish == false) {
            double m = Double.parseDouble(money2.getText());
            double t = m - granted;
            money2.setText(String.valueOf(t));
        }
        mainApp.showBlackJack(player);
    }

    //Hier knn man seinen gesetzten Betrag verdoppeln.
    @FXML
    private void timesTwo(MouseEvent event) {
        holdCards.setDisable(true);
        times2.setDisable(true);
        double doubl;
        doubl = granted * 2;
        String num2 = String.valueOf(doubl);
        double s = Double.parseDouble(money2.getText());
        if (doubl > s) {
            times2.setDisable(true);
        }
        label3.setText(num2);
        double j = bank.getCredit() - doubl;
        money2.setText(String.valueOf(j));
        isTimesTwo = true;
        masterSet = true;

        if (isFirstDraw == false && isSecondDraw == true && hold == false && cancel == false) {
            master = 0;
            times2.setDisable(true);
        } else if (isSecondDraw == false && isThirdDraw == true && hold == false && cancel == false) {
            master = 1;
            times2.setDisable(true);
        } else if (isFourthDraw == true && hold == false && cancel == false) {
            master = 2;
            times2.setDisable(true);
        } else if (isFifthDraw == true && hold == false && cancel == false) {
            master = 3;
            times2.setDisable(true);
        } else if (isSixthDraw == true && hold == false && cancel == false) {
            master = 4;
            times2.setDisable(true);
        } else if (isSeventhDraw == true && hold == false && cancel == false) {
            master = 5;
            times2.setDisable(true);
        } else if (isEighthDraw == true && hold == false && cancel == false) {
            master = 6;
            times2.setDisable(true);
        } else if (isNinthDraw == true && hold == false && cancel == false) {
            master = 7;
            times2.setDisable(true);
        }
    }

    public int getFirstIntercard() {
        return interCards[0];
    }

    public int getGroupCount() {
        return GroupCount;
    }

    public void setPlayer(Player player) {
        this.player = player;
        Bank.credit = (double) db.getAccountBalance(player.getEmail());
        this.user2.setText(player.getFirstName() + " " + player.getLastName());
        this.money2.setText(Integer.toString(db.getAccountBalance(player.getEmail())));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}

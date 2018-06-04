/*
Card images: https://commons.wikimedia.org/wiki/Playing_card
Token image: https://fernandesvincent.deviantart.com/art/Casino-Chip-286507771
 */
package ch.bbbaden.casino.bumchums.casino.baccara;

import ch.bbbaden.casino.bumchums.casino.Gameable;
import ch.bbbaden.casino.bumchums.casino.MainApp;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author lucae
 */
public class GameScreenController implements Initializable, Gameable {

    private int bet;
    private Game game;
    private Image back;
    private Alert alert, info, confirm;
    private final Timeline startPoint = new Timeline();
    private final Timeline first2 = new Timeline();
    private final Timeline second2 = new Timeline();
    private final Timeline playerCard3 = new Timeline();
    private final Timeline dealerCard3 = new Timeline();
    private final Timeline coinMove = new Timeline();

    @FXML private HBox tBox, dBox, pBox;
    @FXML private TextField txtFieldBet;
    @FXML private Text tText, dText, pText, pPoints, dPoints, txtBalance;
    @FXML private ImageView pCard1, pCard2, pCard3, dCard1, dCard2, dCard3;
    @FXML private Button tButton, dButton, pButton;

    private final ArrayList<ImageView> cards = new ArrayList<>();
    private final ArrayList<Button> buttons = new ArrayList<>();
    private MainApp mainApp;
    private Player player;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new Game();
        back = new Image("/images/baccara/CardBack.png");
        alert = new Alert(Alert.AlertType.ERROR);
        info = new Alert(Alert.AlertType.INFORMATION);
        confirm = new Alert(Alert.AlertType.CONFIRMATION);
        this.keyFrames();
        this.fillLists();
        startPoint.play();
        game.reset();
        game.startGame();
        alert.setHeaderText(null);
        alert.setResizable(false);
        info.setHeaderText(null);
        info.setResizable(false);
        confirm.setHeaderText(null);
        confirm.setResizable(false);
    }
    
    /**
     * places bet on Tie
     * @param event 
     */
    @FXML
    private void placeTBet(ActionEvent event) {
        this.bet("Tie");
    }
    
    /**
     * places bet on Dealer
     * @param event 
     */
    @FXML
    private void placeDBet(ActionEvent event) {
        this.bet("Dealer");
    }
    
    /**
     * places bet on Player
     * @param event 
     */
    @FXML
    private void placePBet(ActionEvent event) {
        this.bet("Player");
    }
    
    /**
     * places bet and communicates it with the game/ database
     * @param type 
     */
    private void bet(String type) {
        //try catch for the parsing of the input text
        try {
            bet = Integer.parseInt(txtFieldBet.getText());
            //0 can be parsed but bet has to be bigger than 0
            if (bet > 0) {
                //checks if no bet has been placed already
                if (game.getBetPlaced() == false && game.getBetValid(bet) == true) {
                    game.placeBet(bet, type);
                    txtBalance.setText(Integer.toString(game.getBalance()));
                    for (Button button : buttons) button.setDisable(true);
                    txtFieldBet.setDisable(true);
                    this.game();
                } else {
                    alert.setContentText("not enough balance");
                    alert.showAndWait();
                }
            } else {
                alert.setContentText("Bet has to be 1 or higher");
                alert.showAndWait();
            }
        } catch (Exception e) {
            alert.setContentText("Wrong input");
            alert.showAndWait();
        }
    }
    
    /**
     * structure of the game
     */
    private void game() {
        //Setting the coin and text Visible to see how much money was set on wich betType
        if (game.getBetType().equals("Tie")) {
            tBox.setVisible(true);
            tText.setText(Integer.toString(game.getBetAmount()));
        } else if (game.getBetType().equals("Dealer")) {
            dBox.setVisible(true);
            dText.setText(Integer.toString(game.getBetAmount()));
        } else if (game.getBetType().equals("Player")) {
            pBox.setVisible(true);
            pText.setText(Integer.toString(game.getBetAmount()));
        }

        //coinMove is the start of the program, the rest is managed in the eventHandlers
        coinMove.play();

        coinMove.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Starts the actual game after the coin got moved
                first2.play();
            }

        });

        first2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Drawing first 2 Cards and setting the images
                pCard1.setImage(game.drawPlayerCard());
                dCard1.setImage(game.drawDealerCard());

                //updating points
                pPoints.setText(Integer.toString(game.getPlayerPoints() % 10));
                dPoints.setText(Integer.toString(game.getDealerPoints() % 10));

                second2.play();
            }

        });

        second2.setOnFinished(new EventHandler<ActionEvent>() {
            //gets executed after the first cards were moved
            @Override
            public void handle(ActionEvent event) {
                //Drawing second 2 Cards and setting the images
                pCard2.setImage(game.drawPlayerCard());
                dCard2.setImage(game.drawDealerCard());

                //counter of points
                int player = game.getPlayerPoints() % 10;
                int dealer = game.getDealerPoints() % 10;
                pPoints.setText(Integer.toString(player));
                dPoints.setText(Integer.toString(dealer));

                //checks if third card has to be drawn by the player
                if (player < 6) {
                    game.setThirdNeeded(true);
                    game.setPlayerThird(true);
                    playerCard3.play();

                } else {
                    game.setThirdNeeded(false);
                }

                //Rules, when the dealer has to draw another card
                //Rules found on https://www.casinosschweiz.com/baccarat/spielregeln.html 
                if ((dealer < 3) || (dealer == 3 && player != 8) || (dealer == 4 && player > 1 && player < 8) || (dealer == 5 && player > 3 && player < 8) || (dealer == 6 && player > 5 && player < 8)) {
                    game.setThirdNeeded(true);
                    game.setDealerThird(true);
                    dealerCard3.play();
                } else if (game.getPlayerThird() == false) {
                    game.setThirdNeeded(false);
                }

                //This is needed, to make sure that the game is only finished when all Cards are drawn
                if (game.getThirdNeeded() == false) {
                    endGame();
                }
            }
        });

        playerCard3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Draws a card
                pCard3.setImage(game.drawPlayerCard());

                //Displays game info
                pPoints.setText(Integer.toString(game.getPlayerPoints() % 10));
                dPoints.setText(Integer.toString(game.getDealerPoints() % 10));

                //This is needed, doesn't get ended twice
                if (game.getDealerThird() == false) {
                    endGame();
                }
            }
        });

        dealerCard3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Draws a card
                dCard3.setImage(game.drawDealerCard());

                //Displays game info
                pPoints.setText(Integer.toString(game.getPlayerPoints() % 10));
                dPoints.setText(Integer.toString(game.getDealerPoints() % 10));

                endGame();
            }
        });

    }
    
    /**
     * ends game
     */
    private void endGame() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                int player = game.getPlayerPoints() % 10;
                int dealer = game.getDealerPoints() % 10;
                displayInfo(game.checkWin(player, dealer));
            }
        });
    }
    
    /**
     * displays all the infos needed
     * @param win 
     */
    private void displayInfo(boolean win) {
        //Creates Stats in Database
        game.end();
        //informs user about winning/ loss
        if (game.getTie() == true && !game.getBetType().equals("Tie")) {
            info.setContentText("Tie");
            game.getProfit(win);
            info.showAndWait();
        } else if (win == true) {
            info.setContentText("You won: " + game.getProfit(win));
            info.showAndWait();
        } else {
            info.setContentText("You lost: " + game.getProfit(win));
            info.showAndWait();
        }
        
        //resets every card and text on the screen
        for (ImageView card : cards) card.setImage(back);
        for (Button button : buttons) button.setDisable(false);
        tBox.setVisible(false);
        pBox.setVisible(false);
        dBox.setVisible(false);
        txtFieldBet.setDisable(false);
        pPoints.setText("");
        dPoints.setText("");
        txtFieldBet.setText("");
        txtBalance.setText(Integer.toString(game.getDbBalance()));
        game.reset();
        startPoint.play();

        //In case someone would actually play until all 312 Cards are used
        if (game.getCardsLeft() < 6) {
            confirm.setContentText("You ran out of Cards, click ok to shuffle.");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                game.shuffle();
            } else {
                mainApp.showMenuView(player);
            }
        }
    }
    
    /**
     * Keyframes for the animations
     */
    private void keyFrames() {
        //Sets the position of the cards and coins off screen, so they can fly in afterwards
        startPoint.getKeyFrames().addAll(new KeyFrame(
                Duration.millis(1),
                new KeyValue(pCard1.translateXProperty(), 340),
                new KeyValue(pCard2.translateXProperty(), 225),
                new KeyValue(pCard3.translateXProperty(), 110),
                new KeyValue(dCard1.translateXProperty(), -110),
                new KeyValue(dCard2.translateXProperty(), -225),
                new KeyValue(dCard3.translateXProperty(), -340),
                new KeyValue(pCard1.translateYProperty(), -215),
                new KeyValue(pCard2.translateYProperty(), -215),
                new KeyValue(pCard3.translateYProperty(), -215),
                new KeyValue(dCard1.translateYProperty(), -215),
                new KeyValue(dCard2.translateYProperty(), -215),
                new KeyValue(dCard3.translateYProperty(), -215),
                new KeyValue(tBox.translateYProperty(), 500),
                new KeyValue(dBox.translateYProperty(), 500),
                new KeyValue(pBox.translateYProperty(), 500)
        ));

        //Moves all the coins to their 0 point. This is possible, because only one coin is visible at the point of movement.
        coinMove.getKeyFrames().addAll(new KeyFrame(
                Duration.millis(700),
                new KeyValue(tBox.translateYProperty(), 0),
                new KeyValue(dBox.translateYProperty(), 0),
                new KeyValue(pBox.translateYProperty(), 0)
        ));

        //Movement of the first 4 cards, that have to be drawn anyway
        first2.getKeyFrames().addAll(new KeyFrame(
                Duration.millis(700),
                new KeyValue(pCard1.translateXProperty(), 0),
                new KeyValue(dCard1.translateYProperty(), 0),
                new KeyValue(dCard1.translateXProperty(), 0),
                new KeyValue(pCard1.translateYProperty(), 0)
        ));
        second2.getKeyFrames().addAll(new KeyFrame(
                Duration.millis(700),
                new KeyValue(pCard2.translateXProperty(), 0),
                new KeyValue(pCard2.translateYProperty(), 0),
                new KeyValue(dCard2.translateXProperty(), 0),
                new KeyValue(dCard2.translateYProperty(), 0)
        ));

        //Moves the third card of the player, if needed
        playerCard3.getKeyFrames().addAll(new KeyFrame(
                Duration.millis(700),
                new KeyValue(pCard3.translateXProperty(), 0),
                new KeyValue(pCard3.translateYProperty(), 0)
        ));

        //Moves the third card of the dealer, if needed
        dealerCard3.getKeyFrames().addAll(new KeyFrame(
                Duration.millis(700),
                new KeyValue(dCard3.translateXProperty(), 0),
                new KeyValue(dCard3.translateYProperty(), 0)
        ));
    }

    private void fillLists() {
        cards.add(pCard1);
        cards.add(pCard2);
        cards.add(pCard3);
        cards.add(dCard1);
        cards.add(dCard2);
        cards.add(dCard3);

        buttons.add(tButton);
        buttons.add(dButton);
        buttons.add(pButton);
    }
    
    @FXML
    private void exit(ActionEvent event) {
        confirm.setContentText("Are you sure you want to leave? Placed bets will be lost.");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK) {
                mainApp.showMenuView(player);
            } else {
                return;
            }
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
        Bank.player = player;
        txtBalance.setText(Integer.toString(game.getDbBalance()));
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

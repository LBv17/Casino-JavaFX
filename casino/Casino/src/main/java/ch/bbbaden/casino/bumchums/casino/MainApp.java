package ch.bbbaden.casino.bumchums.casino;

import ch.bbbaden.casino.bumchums.casino.Bingo.BingoMainViewController;
import ch.bbbaden.casino.bumchums.casino.Bingo.BingoStartViewController;
import ch.bbbaden.casino.bumchums.casino.Blackjack.FXMLViewController;
import ch.bbbaden.casino.bumchums.casino.Blackjack.StartScreenFXMLController;
import ch.bbbaden.casino.bumchums.casino.SicBo.SicBoViewController;
import ch.bbbaden.casino.bumchums.casino.Yatzy.Screen1Controller;
import ch.bbbaden.casino.bumchums.casino.Yatzy.StartScreenController;
import ch.bbbaden.casino.bumchums.casino.baccara.GameScreenController;
import ch.bbbaden.casino.bumchums.casino.controller.AdminViewController;
import ch.bbbaden.casino.bumchums.casino.controller.AlertViewController;
import ch.bbbaden.casino.bumchums.casino.controller.GameStatsViewController;
import ch.bbbaden.casino.bumchums.casino.controller.SignUpViewController;
import ch.bbbaden.casino.bumchums.casino.controller.MenuViewController;
import ch.bbbaden.casino.bumchums.casino.controller.PasswordResetViewController;
import ch.bbbaden.casino.bumchums.casino.controller.PlayerStatsViewController;
import ch.bbbaden.casino.bumchums.casino.controller.RechargeViewController;
import ch.bbbaden.casino.bumchums.casino.controller.SettingsViewController;
import ch.bbbaden.casino.bumchums.casino.controller.StartViewController;
import ch.bbbaden.casino.bumchums.casino.controller.WithdrawViewController;
import ch.bbbaden.casino.bumchums.casino.model.Admin;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.util.AlertType;
import ch.bbbaden.casino.bumchums.casino.util.DBDefaultUtil;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.ImageIcon;

public class MainApp extends Application {

    // public Object frame;

    Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        this.primaryStage = primaryStage;
        this.primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/images/icon.png")));
        this.primaryStage.setResizable(false);
        this.primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        // App Icon (not working for some reason) I tried ok      
/*        String os = System.getProperty("os.name");
        System.out.println("fuio");
        if (os.contains("Mac")) {
            //java.awt.Image image = new ImageIcon("/images/iconMac.png").getImage();
            //com.apple.eawt.Application.getApplication().setDockIconImage(image);
        } else {
            // Windows / Linux
            this.primaryStage.getIcons().add(new Image("/images/icon.png"));
        }        
        */
        
        // Check intenet dont start program if not connected
        //checkConnection();
        showStartView();
    }
    
    /**
     * Checks if user is connected to the internet and if the database is available
     */
    public void checkConnection() {
        
        String result = "";

        try {
            HttpURLConnection con = (HttpURLConnection) new URL("http://www.google.com").openConnection();
            con.connect();
            if (con.getResponseCode() == 200) {
                // ok
            }
        } catch (Exception exception) {
            result += "No Internet Connection! \n";
        }

        DBDefaultUtil db = new DBDefaultUtil();
        if (!db.testConnection()) {
            result += "No Database Connection!";
        }

        if (!result.equals("")) {
            showAlert("Connection Error", result, AlertType.CONNECTION, null, "Try Again");
        } else {
            showStartView();
        }

    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
 
    /**
     * Loads initial view where users sign in or create an account
     */
    public void showStartView() {
       
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/StartView.fxml"));
            AnchorPane startView = (AnchorPane) loader.load();

            Scene scene = new Scene(startView);
            scene.setFill(Color.TRANSPARENT);

            StartViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }        
    }

    /**
     * Shows view where user can sign up
     */
    public void showSignUpView() {      
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/SignUpView.fxml"));
            AnchorPane signUpView = (AnchorPane) loader.load();

            Stage signUpStage = new Stage();
            signUpStage.initStyle(StageStyle.TRANSPARENT);
            signUpStage.initModality(Modality.WINDOW_MODAL);
            
            Scene scene = new Scene(signUpView);
            scene.setFill(Color.TRANSPARENT);

            signUpStage.setScene(scene);

            SignUpViewController controller = loader.getController();
            controller.setStage(signUpStage);
            controller.setMainApp(this);
            
            signUpStage.show();

        } catch (IOException ioe) {
            System.out.println("IOE: " + ioe);
            
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Shows the overview of the games and other settings
     * @param player 
     */
    public void showMenuView(Player player) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/MenuView.fxml"));
            AnchorPane menuView = (AnchorPane) loader.load();
            
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.TRANSPARENT);
            
            this.primaryStage.close();
            this.primaryStage = menuStage;
            
            Scene scene = new Scene(menuView);
            scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            // Pass values to controller
            MenuViewController controller = loader.getController();
            controller.setUser(player);
            controller.setMainApp(this);
            controller.setStage(this.primaryStage);
            
            if ("0".equals(player.getEmail())) {
                // DEMO PLAYER NO BALANCE
                
            } else {
                controller.loadBalance();
            }
            
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
        }
    }
    
    /**
     * Shows view where user can reset his password
     * @todo add more info to mail
     */
    public void showResetPasswordView() {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/PasswordResetView.fxml"));
            AnchorPane pwResetView = (AnchorPane) loader.load();

            Stage pwResetStage = new Stage();
            pwResetStage.initStyle(StageStyle.TRANSPARENT);
            pwResetStage.initModality(Modality.WINDOW_MODAL);
            
            Scene scene = new Scene(pwResetView);
            scene.setFill(Color.TRANSPARENT);

            pwResetStage.setScene(scene);

            PasswordResetViewController controller = loader.getController();
            controller.setStage(pwResetStage);
            controller.setMainApp(this);
            
            pwResetStage.show();

        } catch (IOException ioe) {
            System.out.println("IOE: " + ioe);
            
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Shows the view for the administrators 
     * @param admin 
     */
    public void showAdminView(Admin admin) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/AdminView.fxml"));
            AnchorPane adminView = (AnchorPane) loader.load();
            
            Stage adminStage = new Stage();
            adminStage.initStyle(StageStyle.TRANSPARENT);
            
            this.primaryStage.close();
            this.primaryStage = adminStage;
            
            Scene scene = new Scene(adminView);
            scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            // Pass values to controller
            AdminViewController controller = loader.getController();
            controller.setUser(admin);
            controller.setMainApp(this);
            controller.setStage(this.primaryStage);
            
            primaryStage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    } 
    
    /**
     * Shows view where statistics from games are displayed
     * @param admin 
     */
    public void showGameStatsView(Admin admin) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/GameStatsView.fxml"));
            AnchorPane gameStatsView = (AnchorPane) loader.load();
            
            Stage gameStatsStage = new Stage();
            gameStatsStage.initStyle(StageStyle.TRANSPARENT);
            gameStatsStage.initModality(Modality.WINDOW_MODAL);
            
            this.primaryStage.close();
            this.primaryStage = gameStatsStage;
            
            Scene scene = new Scene(gameStatsView);
            scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            gameStatsStage.setScene(scene);
            
            // Pass values to controller
            GameStatsViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(gameStatsStage);
            controller.setAdmin(admin);
            gameStatsStage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Shows view where statistics from the players are displayed
     * @param admin 
     */
    public void showPlayerStatsView(Admin admin) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/PlayerStatsView.fxml"));
            AnchorPane playerStatsView = (AnchorPane) loader.load();
            
            Stage playerStatsStage = new Stage();
            playerStatsStage.initStyle(StageStyle.TRANSPARENT);
            playerStatsStage.initModality(Modality.WINDOW_MODAL);
            
            this.primaryStage.close();
            this.primaryStage = playerStatsStage;
            
            Scene scene = new Scene(playerStatsView);
            scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            playerStatsStage.setScene(scene);
            
            // Pass values to controller
            PlayerStatsViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(playerStatsStage);
            controller.setAdmin(admin);
            playerStatsStage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Shows settings like changing email and so on for players
     * @param player 
     */
    public void showPlayerSettings(Player player) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/SettingsView.fxml"));
            AnchorPane playerSettingsView = (AnchorPane) loader.load();
            
            Stage playerSettingsStage = new Stage();
            playerSettingsStage.initStyle(StageStyle.TRANSPARENT);
            playerSettingsStage.initModality(Modality.WINDOW_MODAL);
            
            Scene scene = new Scene(playerSettingsView);
            scene.setFill(Color.TRANSPARENT);
            
            playerSettingsStage.setScene(scene);
            
            // Pass values to controller
            SettingsViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(playerSettingsStage);
            controller.setUser(player);
            playerSettingsStage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Shows settings for the administrator
     * @param admin 
     */
    public void showAdminSettings(Admin admin) {
    try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/SettingsView.fxml"));
            AnchorPane playerSettingsView = (AnchorPane) loader.load();
            
            Stage playerSettingsStage = new Stage();
            playerSettingsStage.initStyle(StageStyle.TRANSPARENT);
            playerSettingsStage.initModality(Modality.WINDOW_MODAL);
            
            Scene scene = new Scene(playerSettingsView);
            scene.setFill(Color.TRANSPARENT);
            
            playerSettingsStage.setScene(scene);
            
            // Pass values to controller
            SettingsViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(playerSettingsStage);
            controller.setUser(admin);
            playerSettingsStage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }}
    
    /**
     * Used for displaying alerts across the casino and some games
     * @param title
     * @param text
     * @param type
     * @param player
     * @param okButtonText 
     */
    public void showAlert(String title, String text, AlertType type, Player player, String okButtonText) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/AlertView.fxml"));
            AnchorPane alertView = (AnchorPane) loader.load();

            Stage alertStage = new Stage();
            alertStage.initStyle(StageStyle.TRANSPARENT);
            alertStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(alertView);
            scene.setFill(Color.TRANSPARENT);

            alertStage.setScene(scene); 
                    
            AlertViewController controller = loader.getController();
            controller.setStage(alertStage);
            controller.setMainApp(this);
            controller.setPlayer(player);
            controller.setTitle(title);
            controller.setText(text);
            controller.setType(type);
            controller.setOkButtonText(okButtonText);
            
            alertStage.show();

        } catch (IOException ioe) {
            System.out.println("IOE: " + ioe);
            
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Shows the stage where the players can recharge their balance
     * @param player 
     */
    public void showRechargeView(Player player) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/RechargeView.fxml"));
            AnchorPane rechargeView = (AnchorPane) loader.load();
            
            Stage rechargeStage = new Stage();
            rechargeStage.initStyle(StageStyle.TRANSPARENT);
            rechargeStage.initModality(Modality.WINDOW_MODAL);
            
            Scene scene = new Scene(rechargeView);
            scene.setFill(Color.TRANSPARENT);
            
            rechargeStage.setScene(scene);
            
            // Pass values to controller
            RechargeViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(rechargeStage);
            controller.setPlayer(player);
            
            rechargeStage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Shows the stage where the players can withdraw cash from their account
     * @param player 
     */
    public void showWithdrawView(Player player) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/WithdrawView.fxml"));
            AnchorPane withdrawView = (AnchorPane) loader.load();
            
            Stage withdrawStage = new Stage();
            withdrawStage.initStyle(StageStyle.TRANSPARENT);
            withdrawStage.initModality(Modality.WINDOW_MODAL);
            
            Scene scene = new Scene(withdrawView);
            scene.setFill(Color.TRANSPARENT);
            
            withdrawStage.setScene(scene);
            
            // Pass values to controller
            WithdrawViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setStage(withdrawStage);
            controller.setPlayer(player);
            
            withdrawStage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // All the following methods are used to display views of the individual
    // Games
    
    public void showSicBo(Player player) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/SicBo/SicBoView.fxml"));
            AnchorPane gameView = (AnchorPane) loader.load();
            
            Stage gameStage = new Stage();
            gameStage.initStyle(StageStyle.TRANSPARENT);
            
            this.primaryStage.close();
            this.primaryStage = gameStage;
            
            Scene scene = new Scene(gameView);
            scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            // Pass values to controller
            SicBoViewController controller = loader.getController();
            controller.setPlayer(player);
            controller.setMainApp(this);
            controller.setStage(this.primaryStage);
            
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
        }
    }
    
    public void showYatzy(Player player) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Yatzy/StartScreenYatzy.fxml"));
            AnchorPane startScreenView = (AnchorPane) loader.load();
            
            Stage startViewStage = new Stage();
            //startViewStage.initStyle(StageStyle.TRANSPARENT);
            
            this.primaryStage.close();
            this.primaryStage = startViewStage;
            
            Scene scene = new Scene(startScreenView);
            //scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            // Pass values to controller
            StartScreenController controller = loader.getController();
            controller.setPlayer(player);
            controller.setMainApp(this);
            controller.setStage(this.primaryStage);
            
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
        }
    }
    
    public void showYatzyGame(Player player, int bet) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Yatzy/Screen1.fxml"));
            AnchorPane startScreenView = (AnchorPane) loader.load();
            
            Stage startViewStage = new Stage();
            //startViewStage.initStyle(StageStyle.TRANSPARENT);
            
            this.primaryStage.close();
            this.primaryStage = startViewStage;
            
            Scene scene = new Scene(startScreenView);
            //scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            // Pass values to controller
            Screen1Controller controller = loader.getController();
            controller.setPlayer(player);
            controller.setMainApp(this);
            controller.setStage(this.primaryStage);
            controller.setBet(bet);
            
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
        }
    }
    
    public void showBaccara(Player player) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/baccara/gameScreen.fxml"));
            AnchorPane gameView = (AnchorPane) loader.load();
            
            Stage gameStage = new Stage();
            //gameStage.initStyle(StageStyle.TRANSPARENT);
            
            this.primaryStage.close();
            this.primaryStage = gameStage;
            
            Scene scene = new Scene(gameView);
            //scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            // Pass values to controller
            GameScreenController controller = loader.getController();
            controller.setPlayer(player);
            controller.setMainApp(this);
            controller.setStage(this.primaryStage);

            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
        }
    }
    
    public void showBlackJack(Player player) {
         try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Blackjack/StartScreenFXML.fxml"));
            AnchorPane blackJackView = (AnchorPane) loader.load();
            
            Stage blackJackStage = new Stage();
            //gameStage.initStyle(StageStyle.TRANSPARENT);
            
            this.primaryStage.close();
            this.primaryStage = blackJackStage;
            
            Scene scene = new Scene(blackJackView);
            //scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            // Pass values to controller
            StartScreenFXMLController controller = loader.getController();
            controller.setPlayer(player);
            controller.setMainApp(this);
            controller.setStage(this.primaryStage);

            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
        }
    }
    
    public void showBlackJackGame(Player player) {
         try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Blackjack/FXMLView.fxml"));
            AnchorPane blackJackView = (AnchorPane) loader.load();
            
            Stage blackJackStage = new Stage();
            //gameStage.initStyle(StageStyle.TRANSPARENT);
            
            this.primaryStage.close();
            this.primaryStage = blackJackStage;
            
            Scene scene = new Scene(blackJackView);
            //scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            // Pass values to controller
            FXMLViewController controller = loader.getController();
            controller.setPlayer(player);
            controller.setMainApp(this);
            controller.setStage(this.primaryStage);
        
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
        }
    }
    
    public void showBingo(Player player) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Bingo/BingoStartView.fxml"));
            AnchorPane bingoStartView = (AnchorPane) loader.load();
            
            Stage bingoStartStage = new Stage();
            //gameStage.initStyle(StageStyle.TRANSPARENT);
            
            this.primaryStage.close();
            this.primaryStage = bingoStartStage;
            
            Scene scene = new Scene(bingoStartView);
            //scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            // Pass values to controller
            BingoStartViewController controller = loader.getController();
            controller.setPlayer(player);
            controller.setMainApp(this);
            controller.setStage(this.primaryStage);
            
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
        }
    }
    
    public void showBingoGame(Player player, int bet) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Bingo/BingoMainView.fxml"));
            AnchorPane bingoMainView = (AnchorPane) loader.load();
            
            Stage bingoMainStage = new Stage();
            //gameStage.initStyle(StageStyle.TRANSPARENT);
            
            this.primaryStage.close();
            this.primaryStage = bingoMainStage;
            
            Scene scene = new Scene(bingoMainView);
            //scene.setFill(Color.TRANSPARENT);
            
            this.primaryStage.setScene(scene);
            
            // Pass values to controller
            BingoMainViewController controller = loader.getController();
            controller.setPlayer(player);
            controller.setMainApp(this);
            controller.setStage(primaryStage);
            controller.setBet(bet);
            
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
        }
    }
        
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }
    

}

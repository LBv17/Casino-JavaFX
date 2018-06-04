/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.util;

import ch.bbbaden.casino.bumchums.casino.model.Admin;
import ch.bbbaden.casino.bumchums.casino.model.GameStatistics;
import ch.bbbaden.casino.bumchums.casino.model.Player;
import ch.bbbaden.casino.bumchums.casino.model.PlayerStatistics;
import ch.bbbaden.casino.bumchums.casino.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LB
 */
public class DBDefaultUtil implements Databasable {

    private Connection connection;
        
    /**
     * Checks if connection is valid, otherwise creates a new one
     */
    private boolean createConnection() {
        
        try {
            
            if(connection != null && connection.isValid(1)) {
                System.out.println("VALID CONNECTION");
            } else {
                String url = DBConfig.getDatabaseURL();
                String user = DBConfig.getDatabaseUser();
                String pw = DBConfig.getDatabasePassword();
                connection = DriverManager.getConnection(url, user, pw);
            }
            
            return true;
            
        } catch (SQLException sqle) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + sqle);
            
            return false;
        }
    } 

    @Override
    public boolean testConnection() {
        return createConnection();           
    }
    
    @Override
    public void addNewUser(String email, String firstName, String lastName, String password) {
        createConnection();
        
        // Create hash and salt for password
        byte[] salt = PasswordUtil.getNextSalt();
        byte[] hash = PasswordUtil.hashPassword(password.toCharArray(), salt);

        String query = "INSERT INTO users (Email, FirstName, LastName, Password, Salt, IsAdmin, IsActive) VALUES " + 
                    "(?, ?, ?, ?, ?, ?, ?)";
        
        try {
            
            PreparedStatement pst = connection.prepareStatement(query);
            
            pst.setString(1, email);
            pst.setString(2, firstName);
            pst.setString(3, lastName); 
            pst.setBytes(4, hash);
            pst.setBytes(5, salt);
            pst.setString(6, "0");
            pst.setString(7, "1");
            
            pst.execute();
            
            pst.close();
            connection.close();
            
        } catch (SQLException sqle) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + sqle);
        } catch (Exception e) {
            System.out.println("---------------------------");
            System.out.println("EXCEPTION: " + e);
        }
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        
        createConnection();
                
//        String query = "SELECT * FROM User WHERE Email LIKE ?";
        String query = "SELECT * FROM users WHERE Email LIKE ?";
        byte[] salt = null;
        byte[] hash = null;
        
        try {
        
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                salt = rs.getBytes("Salt");
                hash = rs.getBytes("Password");
            }
            
            pst.close();
            rs.close();
            connection.close();
            
            return PasswordUtil.checkPassword(password.toCharArray(), salt, hash);
            
        } catch (SQLException sqle) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + sqle);
            return false;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("---------------------------");
            System.out.println("EXCEPTION: " + e);
            return false;
        }
    }

    @Override
    public User getUser(String email) {
        
        createConnection();
        
        String query = "SELECT * FROM users WHERE Email LIKE \"" + email + "\";";
        
        try {
            
            Statement st = connection.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                if (rs.getBoolean("IsAdmin")) {
                    Admin admin = new Admin(rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"), true, rs.getInt("ID_User"));
                    return admin;
                } else {
                    Player player = new Player(rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"), false, rs.getInt("ID_User"));
                    return player;
                }
            }
            
            st.close();
            rs.close();
            connection.close();
            
        } catch (SQLException sqle) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + sqle);
        } catch (Exception e) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + e);
        }
        
        return null;
    }

    @Override
    public ObservableList<Player> getAllPlayers() {
        
        createConnection();
        
        String query = "SELECT FirstName, LastName, Email, ID_User FROM users WHERE IsAdmin = 0;";
        ObservableList<Player> playerList = FXCollections.observableArrayList();
        
        try {
            Statement st = connection.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                Player player = new Player(rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"), false, rs.getInt("ID_User"));
                playerList.add(player);
            }
            
            st.close();
            rs.close();
            connection.close();
            
        } catch (SQLException sqle) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + sqle);
        } catch (Exception e) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + e);
        }
        
        return playerList;
    }
    
    @Override
    public int getAccountBalance(String email) {
        
        createConnection();
        
        String query = "SELECT AccountBalance FROM users WHERE Email LIKE \"" + email +"\";";
        
        try {
            
            Statement st = connection.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                return rs.getInt("AccountBalance");
            }
            
            st.close();
            rs.close();
            connection.close();
            
        } catch (SQLException sqle) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + sqle);
        } catch (Exception e) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + e);
        }
        
        return 0;
    }
    
    @Override
    public void updateAccountBalance(String email, int balanceUpdate) {
    
        createConnection();
        
        balanceUpdate += getAccountBalance(email);
        String query = "UPDATE users SET AccountBalance = ? WHERE Email LIKE \"" + email +"\";";
        
        try {
            
            PreparedStatement update = connection.prepareStatement(query);
            
            update.setInt(1, balanceUpdate);
            
            update.executeUpdate();
            
            update.close();
            connection.close();
            
        } catch (SQLException sqle) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + sqle);
        } catch (Exception e) {
            System.out.println("---------------------------");
            System.out.println("EXCEPTION: " + e);
        }
        
    }

    @Override
    public String updateUser(User user) {
        
        createConnection();
       
        int id = user.getId();
        String fName = user.getFirstName();
        String lName = user.getLastName();
        String email = user.getEmail();
        
        String query = "UPDATE users SET FirstName = ?, LastName = ?, Email = ? WHERE ID_User = ?;";
        
        try {
            
            PreparedStatement update = connection.prepareStatement(query);
            
            update.setString(1, fName);
            update.setString(2, lName);            
            update.setString(3, email);
            update.setInt(4, id);
            
            update.executeUpdate();
            
            update.close();
            connection.close();
            
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return "error";
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
        
        return "ok";
    }

    @Override
    public String updatePassword(User user, String pwd) {
        
        createConnection();
       
        int id = user.getId();
    
        byte[] salt = PasswordUtil.getNextSalt();
        byte[] hash = PasswordUtil.hashPassword(pwd.toCharArray(), salt);
        
        String query = "UPDATE users SET Password = ?, salt = ? WHERE ID_User = ?;";
        
        try {
            
            PreparedStatement update = connection.prepareStatement(query);
            
            update.setBytes(1, hash);
            update.setBytes(2, salt);            
            update.setInt(3, id);
            
            update.executeUpdate();
            
            update.close();
            connection.close();
            
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return "error";
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
        
        return "ok";
    }
    
    @Override
    public void updateStats(User user, String game, int won, int loss, int bet, Date date) {
        
        createConnection();
        
        String query = "INSERT INTO statistics (Game, Won, Lost, Bet, Date, ID_User) VALUES (?,?,?,?,?,?);";
        
        try {
            
            PreparedStatement pst = connection.prepareStatement(query);
            
            pst.setString(1, game);
            pst.setInt(2, won);
            pst.setInt(3, loss); 
            pst.setInt(4, bet);
            //pst.setString(5, date);
            
            pst.setTimestamp(5, new java.sql.Timestamp(date.getTime()));
            
            pst.setInt(6, user.getId());
            
            pst.execute();
            
            pst.close();
            connection.close();
            
        } catch (SQLException sqle) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + sqle);
        } catch (Exception e) {
            System.out.println("---------------------------");
            System.out.println("EXCEPTION: " + e);
        }
    }

    @Override
    public ObservableList<GameStatistics> getGameStats(String game, Date from, Date to) {
        
        createConnection();
        
        String query = "SELECT statistics.*, users.FirstName, users.LastName FROM statistics INNER JOIN users ON users.ID_User = statistics.ID_User WHERE Game LIKE ?;";
        
        ObservableList<GameStatistics> statList = FXCollections.observableArrayList();
        
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, game);

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Date date = rs.getTimestamp("Date");
                String g = rs.getString("Game");
                String n = "" + rs.getString("FirstName") + " " + rs.getString("LastName");
                int w = rs.getInt("Won");
                int l = rs.getInt("Lost");
                int b = rs.getInt("Bet");
              
                if (date.before(to) && date.after(from)) {
                    GameStatistics gs = new GameStatistics(g, n, w, l, b, date);             
                    statList.add(gs);
                }
                
            }
            
            pst.close();
            connection.close();
            
        } catch (SQLException sqle) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + sqle);
        } catch (Exception e) {
            System.out.println("---------------------------");
            System.out.println("EXCEPTION: " + e);
        }
        
        return statList;
    }

    @Override
    public ObservableList<PlayerStatistics> getPlayerStats(String email, String game, Date from, Date to) {
        
        createConnection();
        
        String queryAllGames = "SELECT statistics.*, users.FirstName, users.LastName FROM statistics INNER JOIN users ON users.ID_User = statistics.ID_User WHERE Email LIKE ?;";
        String querySpecificGame = "SELECT statistics.*, users.FirstName, users.LastName FROM statistics INNER JOIN users ON users.ID_User = statistics.ID_User WHERE Game LIKE ? AND Email LIKE ?;";
        ObservableList<PlayerStatistics> statList = FXCollections.observableArrayList();
        
        try {
            
            PreparedStatement pst;
            
            if (game.equals("All")) {
                pst = connection.prepareStatement(queryAllGames);
                pst.setString(1, email);
            } else {
                pst = connection.prepareStatement(querySpecificGame);
                pst.setString(1, game);
                pst.setString(2, email);
            }

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Date date = rs.getTimestamp("Date");
                String g = rs.getString("Game");
                String vorN = "" + rs.getString("FirstName"); 
                String nachN = "" + rs.getString("LastName");
                String n = vorN + " " + nachN;
                int w = rs.getInt("Won");
                int l = rs.getInt("Lost");
                int b = rs.getInt("Bet");
               
                if (date.before(to) && date.after(from)) {
                    PlayerStatistics ps = new PlayerStatistics(n, g, w, l, b, date);             
                    statList.add(ps);
                }
                
            }
            
            pst.close();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("---------------------------");
            System.out.println("SQLEXCEPTION: " + sqle);
        } catch (Exception e) {
            System.out.println("---------------------------");
            System.out.println("EXCEPTION: " + e);
        }
        
        return statList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.model;


/**
 *
 * @author LB
 */
public abstract class User {
    
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean admin;
    
    public User(String fName, String lName, String email, boolean admin, int id) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;

    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
//    public String setAndUpdatePassword(String pwd) {
//        // Call DBUtil
//        return "error";
//    }
    
//    public String getPassword() {
//        return this.password;
//    }
//    
    public String setEmail(String newEmail) {
        // Call DBUtil
        return "error";
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public boolean getAdmin() {
        return this.admin;
    }
    
}

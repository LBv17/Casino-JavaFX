/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.util;

/**
 *
 * @author LB
 */
public class DBConfig {
    
    final private static String DATABASE = "mysql"; 
    
    final private static String SERVER = "SERVER ADDRESS";
    final private static String PORT = "PORT";
    final private static String DATABASE_NAME = "DATABASE NAME";
    final private static String USERNAME = "USERNAME";
    final private static String PASSWORD = "PASSWORD";
   
    public static String getDatabaseURL() {
        return "jdbc:"+DATABASE+"://"+SERVER+":"+PORT+"/"+DATABASE_NAME;
    }
    
    public static String getDatabaseUser() {
        return USERNAME;
    }
    
    public static String getDatabasePassword() {
        return PASSWORD;
    }
    
}

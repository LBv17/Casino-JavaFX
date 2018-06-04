/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.util;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author LB
 */
public class Validator {
    
    /**
     * [_A-Za-z0-9-\\+]     Must start with chars in [] and contain one or more (\\+)
     * +                    Followed by next group
     * (\\.[_A-Za-z0-9-]+)* A (.) and an optional (*) String with chars inside []
     * @                    Followed by an @
     * [A-Za-z0-9-]+        Must start and contain one or more chars inside [], 
     *                      (+) followed by next group
     * (\\.[A-Za-z0-9+])*   First level tld must start and contain one or more chars inside [],
     *                      (*) makes group optional
     * (\\.[A-Za-z]{2,})    Must start with (.) and contain chars in [],
     *                      {2,} lenght must be at least 2 chars  
     * $                    End of line
     * 
     * @credits Was looked up on the web 
     */
    public static final String EMAIL_REGEX = 
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    /**
     * [_A-Za-z0-9-\\+]     Must start and contain at least one char from inside []
     * 
     * @credits was not taken from anywhere i made this :D
     */
    public static final String NAME_REGEX = "^[A-Za-z]+";
    
    /**
     * Checks email against regular expression
     * @param email
     * @return 
     */
    public static boolean validateEmail(String email) {
        
        // @todoCheck if email is in db
        
        return email.matches(EMAIL_REGEX);
    }
    
    /**
     * Checks if first or second name is valid
     * @param name
     * @return 
     */
    public static boolean validateName(String name) {
        return name.matches(NAME_REGEX);
    }
    
    /**
     * Checks if password is valid
     * regular expression was not working so I replaced it with my own method
     * @param password
     * @return 
     */
    public static String validatePassword(String password) {
        
        final String[] SPECIAL_CHARS = {"_","?",".","*","#","=","\\(","\\)","@",",","\\[","\\]"};
        final String[] DIGITS = {"0","1","2","3","4","5","6","7","8","9"};
        final String[] UPPER_CASE = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        final String[] LOWER_CASE = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        final String WHITESPACE = " ";
        
        if (password.isEmpty()) {
            return "Password cannot be empty";
        }
        if (password.length() < 8 || password.length() > 20) {
            return "At least 8 characters";
        }
        if (!Arrays.stream(SPECIAL_CHARS).parallel().anyMatch(password::contains)) {
            return "Special Character missing";
        }
        if (!Arrays.stream(DIGITS).parallel().anyMatch(password::contains)) {
            return "Must contain a number";
        }
        if (!Arrays.stream(LOWER_CASE).parallel().anyMatch(password::contains)) {
            return "Must contain Lower case";
        }
        if (!Arrays.stream(UPPER_CASE).parallel().anyMatch(password::contains)) {
            return "Must conain upper case";
        }
        if (password.contains(WHITESPACE)) {
            return "no spaces allowed";
        }
        
        return "valid";
    }
 
}

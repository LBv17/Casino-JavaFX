/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casino.bumchums.casino.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author LB
 */
public class PasswordUtil {
    
    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    
    private PasswordUtil() {};
    
    /**
     * Returns a randomly generated salt to hash password
     * @return 16 byte salt
     * 
     * @credits Was looked up on Stackoverflow
     */
    public static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }
    
    /**
     * Hashes password using PBKDF2 
     * @param password
     * @param salt
     * @return salted hash as byte array
     * 
     * @credits Was looked up on Stackoverflow
     */
    public static byte[] hashPassword(char[] password, byte[] salt) {
        
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        
        try {
        
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            
            return hash;
        
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println(e);
        }
        
        return null;

    }
    
    /**
     * Used to authenticate user, compares two hashes
     * @param password
     * @param salt
     * @param expectedHash
     * @return 
     */
    public static boolean checkPassword(char[] password, byte[] salt, byte[] expectedHash) {
        
        if(password == null || salt == null) {
            return false;
        }
        
        byte[] pwdHash = hashPassword(password, salt);
        
        for (int i = 0; i < expectedHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) {
                return false;
            }
        }
                
        return true;
    }
    
    
    public static void testPWHash() throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] salt = getNextSalt();
        byte[] hashPassword = hashPassword("test".toCharArray(), salt);
        boolean b = checkPassword("test".toCharArray(), salt, hashPassword);
        System.out.println(b);
    }
    
    
}

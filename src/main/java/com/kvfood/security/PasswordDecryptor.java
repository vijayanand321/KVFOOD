package com.kvfood.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class PasswordDecryptor {
   public static String decryptPassword(String encryptedPassword) {
       try {
           // Decode the Base64 encoded string
           byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
           // Extract the salt from the decoded bytes
           byte[] salt = new byte[16];
           System.arraycopy(decodedBytes, 0, salt, 0, 16);
           // Get the hash bytes (excluding the salt)
           byte[] hashBytes = new byte[decodedBytes.length - 16];
           System.arraycopy(decodedBytes, 16, hashBytes, 0, decodedBytes.length - 16);
           // Create MessageDigest instance for MD5
           MessageDigest md = MessageDigest.getInstance("MD5");
           // Add salt bytes to digest
           md.update(salt);
           // Get the hash bytes for the original password
           byte[] hashedPassword = md.digest(hashBytes);
           // Convert to string and return
           return new String(hashedPassword);
       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
           return null;
       }
   }
   public static void main(String[] args) {
       String encryptedPassword = PasswordEncrypter.encryptPassword("vijay");
       String decryptedPassword = decryptPassword(encryptedPassword);
       System.out.println("Decrypted Password: " + decryptedPassword);
   }
}

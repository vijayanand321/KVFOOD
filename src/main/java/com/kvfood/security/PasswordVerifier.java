package com.kvfood.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class PasswordVerifier {
   public static boolean verifyPassword(String password, String hashedPassword) {
       try {
           // Decode the Base64 encoded string
           byte[] decodedBytes = Base64.getDecoder().decode(hashedPassword);
           // Extract the salt from the decoded bytes
           byte[] salt = new byte[16];
           System.arraycopy(decodedBytes, 0, salt, 0, 16);
           // Get the hash bytes (excluding the salt)
           byte[] storedHashBytes = new byte[decodedBytes.length - 16];
           System.arraycopy(decodedBytes, 16, storedHashBytes, 0, decodedBytes.length - 16);
           // Create MessageDigest instance for MD5
           MessageDigest md = MessageDigest.getInstance("MD5");
           // Add salt bytes to digest
           md.update(salt);
           // Get the hash bytes for the provided password
           byte[] hashBytes = md.digest(password.getBytes());
           // Compare the hashed bytes
           for (int i = 0; i < storedHashBytes.length; i++) {
               if (storedHashBytes[i] != hashBytes[i]) {
                   return false;
               }
           }
           return true;
       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
           return false;
       }
   }
   public static void main(String[] args) {
       String password = "vijay";
       String hashedPassword = PasswordDecryptor.decryptPassword(PasswordEncrypter.encryptPassword("vijay"));
       if (verifyPassword(password, hashedPassword)) {
           System.out.println("Password matches!");
       } else {
           System.out.println("Password does not match!");
       }
   }
}

package com.kvfood.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncrypter {
	   public static String encryptPassword(String password) {
	       try {
	           // Generate a random salt
	           SecureRandom random = new SecureRandom();
	           byte[] salt = new byte[16];
	           random.nextBytes(salt);
	           // Create MessageDigest instance for MD5
	           MessageDigest md = MessageDigest.getInstance("MD5");
	           // Add salt bytes to digest
	           md.update(salt);
	           // Get the hash bytes
	           byte[] hashedPassword = md.digest(password.getBytes());
	           // Concatenate salt and hashed password
	           byte[] combined = new byte[hashedPassword.length + salt.length];
	           System.arraycopy(salt, 0, combined, 0, salt.length);
	           System.arraycopy(hashedPassword, 0, combined, salt.length, hashedPassword.length);
	           // Convert to Base64 and return
	           return Base64.getEncoder().encodeToString(combined);
	       } catch (NoSuchAlgorithmException e) {
	           e.printStackTrace();
	           return null;
	       }
	   }
	   public static void main(String[] args) {
	       String password = "vijay";
	       String encryptedPassword = encryptPassword(password);
	       System.out.println("Encrypted Password: " + encryptedPassword);
	   }
	}

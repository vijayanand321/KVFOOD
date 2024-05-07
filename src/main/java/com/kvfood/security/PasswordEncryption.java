package com.kvfood.security;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class PasswordEncryption {
   private static final String ALGORITHM = "AES";
   private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
   public static String encryptPassword(String password) {
       try {
           Key key = generateKey();
           Cipher cipher = Cipher.getInstance(TRANSFORMATION);
           cipher.init(Cipher.ENCRYPT_MODE, key);
           byte[] encryptedBytes = cipher.doFinal(password.getBytes());
           return Base64.getEncoder().encodeToString(encryptedBytes);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
   public static String decryptPassword(String encryptedPassword) {
       try {
           Key key = generateKey();
           Cipher cipher = Cipher.getInstance(TRANSFORMATION);
           cipher.init(Cipher.DECRYPT_MODE, key);
           byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
           byte[] decryptedBytes = cipher.doFinal(decodedBytes);
           return new String(decryptedBytes);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
   private static Key generateKey() throws NoSuchAlgorithmException {
       return new SecretKeySpec(getSHA256("kvf"), ALGORITHM);
   }
   
   private static byte[] getSHA256(String input) throws NoSuchAlgorithmException {
	MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");   
	return messageDigest.digest(input.getBytes());
   }
   
//   public static void main(String[] args) {
//       String password = "vijay";
//       String encryptedPassword = encryptPassword(password);
//       System.out.println("Encrypted Password: " + encryptedPassword);
//       String decryptedPassword = decryptPassword("mxuqdpCgZtCIy4ZgopBd6Q==");
//       System.out.println("Decrypted Password: " + decryptedPassword);
//   }
}
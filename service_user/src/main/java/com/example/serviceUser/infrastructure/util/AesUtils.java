package com.example.serviceUser.infrastructure.util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtils {

    private final static String KEY = "340392837543210584321234759483209"; //32
    private final static String Alg = "AES/CBC/PKCS5Padding";
    private final static String KEY_128 = KEY.substring(0, 128 / 8);
    private final static String KEY_256 = KEY.substring(0, 256 / 8);

    public static String encrypt(String plainText)  {
        try{
            byte[] key128Data = KEY_128.getBytes("UTF-8");
            byte[] key256Data = KEY_256.getBytes("UTF-8");

            Cipher cipher = Cipher.getInstance(Alg);

            SecretKeySpec keySpec = new SecretKeySpec(key256Data, "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(key128Data);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String cipherText) {
        try{
            byte[] key128Data = KEY_128.getBytes("UTF-8");
            byte[] key256Data = KEY_256.getBytes("UTF-8");

            Cipher cipher = Cipher.getInstance(Alg);

            SecretKeySpec keySpec = new SecretKeySpec(key256Data, "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(key128Data);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes =  Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
}
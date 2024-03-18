package com.example.oauth2.infrastructure.utils;

public interface AesUtils {

    String encrypt(String plainText);

    String decrypt(String cipherText);
}
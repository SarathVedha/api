package com.vedha.service;

public interface JavaScriptSalt {

    String encrypt(String key, String value);

    Object decrypt(String key, String value);

    String base64Encrypt(String value);

    String base64Decrypt(String value);

}

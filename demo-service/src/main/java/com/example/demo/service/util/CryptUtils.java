package com.example.demo.service.util;

import org.soulwing.crypt4j.Crypt;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * We use random seed to generate hash for client validation. It is safe in standalone server.
 * But for server cluster. We have to share the seed among the servers and use AES encrypt
 * method instead.
 */
public class CryptUtils {

    private static final char[] ALPHABET_64 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz./".toCharArray();

    private static String randomSalt() {
        int len = 6;
        byte[] bb = new byte[len];
        new SecureRandom().nextBytes(bb);
        char[] salt = new char[8];
        int j = 0;
        while (len > 0) {
            int n = (bb[--len] << 16 & 0x00ff0000) | (bb[--len] << 8 & 0x0000ff00) | (bb[--len] & 0x000000ff);
            for (int i = 0; i < 4; i++) {
                salt[j++] = ALPHABET_64[n & 0x0000003f];
                n >>>= 6;
            }
        }
        return new String(salt);
    }

    public static String crypt(String rawPassword) {
        return crypt(rawPassword, randomSalt());
    }

    public static String crypt(String rawPassword, String salt) {
        Objects.requireNonNull(rawPassword);
        try {
            return Crypt.crypt(rawPassword.toCharArray(), "$6$" + salt);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Can not happen here.");
    }

    public static boolean validate(String rawPassword, String encodedPassword) {
        if (null == rawPassword || rawPassword.isEmpty()) {
            return false;
        }
        if (null == encodedPassword || encodedPassword.isEmpty()) {
            return false;
        }
        try {
            return Crypt.validate(rawPassword.toCharArray(), encodedPassword);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) {
//        System.out.println(crypt("992a861bbebacd7a7f2d8f82c2edd334"));
//        System.out.println(validate("992a861bbebacd7a7f2d8f82c2edd334","$6$zly1u5Tt$aKfY8GoxYtevc.IdcTL9Pg/lIpFLoh4HlM5jCJ9CfD0KPxxOZDr1inACAVhfGpoDAKoe0srK.A8mznhEhTAZ1/"));
//    }
}
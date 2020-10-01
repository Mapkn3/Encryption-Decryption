package encryptdecrypt;

import cipher.Cipher;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.asList(args));
        Cipher cipher = Cipher.fromArgs(args);
        cipher.processingData();
    }
}

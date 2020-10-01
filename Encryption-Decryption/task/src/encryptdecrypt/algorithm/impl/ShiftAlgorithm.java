package encryptdecrypt.algorithm.impl;

import encryptdecrypt.algorithm.Algorithm;

public class ShiftAlgorithm extends Algorithm {
    @Override
    public int processingChar(int symbol, int key) {
        int a, z;
        if ('a' <= symbol && symbol <= 'z') {
            a = 'a';
            z = 'z';
        } else if ('A' <= symbol && symbol <= 'Z') {
            a = 'A';
            z = 'Z';
        } else {
            return symbol;
        }
        int n = z - a + 1;
        int result = (symbol - a + key) % n + a;
        if (result < a) {
            result += n;
        }
        return result;
    }
}

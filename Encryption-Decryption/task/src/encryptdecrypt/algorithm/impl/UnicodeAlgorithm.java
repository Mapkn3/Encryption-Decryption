package encryptdecrypt.algorithm.impl;

import encryptdecrypt.algorithm.Algorithm;

public class UnicodeAlgorithm extends Algorithm {
    @Override
    public int processingChar(int symbol, int key) {
        return symbol + key;
    }
}

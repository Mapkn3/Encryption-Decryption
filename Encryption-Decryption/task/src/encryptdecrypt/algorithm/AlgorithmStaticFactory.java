package encryptdecrypt.algorithm;

import encryptdecrypt.algorithm.impl.ShiftAlgorithm;
import encryptdecrypt.algorithm.impl.UnicodeAlgorithm;

public class AlgorithmStaticFactory {
    public static Algorithm getAlgorithmByType(AlgorithmType type) {
        switch (type) {
            case SHIFT:
                return new ShiftAlgorithm();
            case UNICODE:
                return new UnicodeAlgorithm();
        }
        return null;
    }
}

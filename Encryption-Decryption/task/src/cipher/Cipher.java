package cipher;

import encryptdecrypt.algorithm.Algorithm;
import encryptdecrypt.algorithm.AlgorithmStaticFactory;
import encryptdecrypt.algorithm.AlgorithmType;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cipher {
    private final CryptMode mode;
    private final int key;
    private final String data;
    private final String in;
    private final String out;
    private final Algorithm algorithm;

    private Cipher(CryptMode mode, int key, String data, String in, String out, Algorithm algorithm) {
        this.mode = mode;
        this.key = key;
        this.data = data;
        this.in = in;
        this.out = out;
        this.algorithm = algorithm;
    }

    public static Cipher fromArgs(String[] args) {
        CryptMode mode = CryptMode.ENC;
        int key = 0;
        String data = "";
        String in = "";
        String out = "";
        Algorithm algorithm = AlgorithmStaticFactory.getAlgorithmByType(AlgorithmType.SHIFT);

        List<String> argsList = Arrays.asList(args);
        ListIterator<String> argsIterator = argsList.listIterator();
        String arg;
        while (argsIterator.hasNext()) {
            arg = argsIterator.next();
            switch (arg) {
                case "-mode":
                    String modeString = argsIterator.next();
                    switch (modeString) {
                        case "enc":
                        default:
                            mode = CryptMode.ENC;
                            break;
                        case "dec":
                            mode = CryptMode.DEC;
                            break;
                    }
                    break;
                case "-key":
                    key = Integer.parseInt(argsIterator.next());
                    break;
                case "-data":
                    data = argsIterator.next();
                    break;
                case "-in":
                    in = argsIterator.next();
                    break;
                case "-out":
                    out = argsIterator.next();
                    break;
                case "-alg":
                    AlgorithmType type;
                    switch (argsIterator.next().toUpperCase()) {
                        case "SHIFT":
                        default:
                            type = AlgorithmType.SHIFT;
                            break;
                        case "UNICODE":
                            type = AlgorithmType.UNICODE;
                            break;
                    }
                    algorithm = AlgorithmStaticFactory.getAlgorithmByType(type);
                    break;
            }
        }
        return new Cipher(mode, key, data, in, out, algorithm);
    }

    public void processingData() {
        try (Scanner scanner = data.isEmpty() ?
                in.isEmpty() ?
                        new Scanner("") :
                        new Scanner(new File(in)) :
                new Scanner(data);
             PrintStream output = out.isEmpty() ? System.out : new PrintStream(out)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String processedString = processingString(line);
                output.print(processedString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processingString(String data) {
        int shiftCount;
        switch (this.mode) {
            case ENC:
                shiftCount = this.key;
                break;
            case DEC:
                shiftCount = -this.key;
                break;
            default:
                shiftCount = 0;
                break;
        }
        return data.chars()
                .map(character -> algorithm.processingChar(character, shiftCount))
                .mapToObj(Character::toString)
                .collect(Collectors.joining(""));
    }
}

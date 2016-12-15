package hackerrank.ieeextreme10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FullAdder {

    private String symbols = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ@#$";

    public void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();

        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            builder.append(Character.toChars(reader.read()));
        }

        // Whole input
        String parts[] = builder.toString().split("\n");

        String[] def = line.split(" ");
        int base = Integer.parseInt(def[0]);

        // Symbol map
        Map<Character, Character> symbolMapping = new HashMap<>();
        Map<Character, Character> numberMapping = new HashMap<>();

        for (int i = 0; i < def[1].length(); i++) {
            // symbol -> number
            symbolMapping.put(def[1].charAt(i), symbols.charAt(i));
            numberMapping.put(symbols.charAt(i), def[1].charAt(i));
        }

        String operand1 = parts[0].trim();
        String operand2 = parts[1].trim().replace("+", "").replaceAll(" ", "");

        BigInteger op1 = convertFromRadix(convertSymbols(operand1, symbolMapping), base);
        BigInteger op2 = convertFromRadix(convertSymbols(operand2, symbolMapping), base);

        String answer = applySymbols(op1.add(op2), numberMapping, base);
        parts[3] = String.format("%" + parts[2].length() + "s", answer);

        System.out.println(line);
        for (String part : parts) {
            System.out.println(part);
        }
    }

    // OK
    private String convertSymbols(String string, Map<Character, Character> symbolMapping) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            builder.append(symbolMapping.get(string.charAt(i)));
        }
        return builder.toString();
    }

    /**
     * Convert a big integer in base-10 to given base
     *
     * @param number number to be converted
     * @param radix  base
     * @return converted number as string
     */
    private String convertToRadix(BigInteger number, int radix) {
        BigInteger base = new BigInteger(Integer.toString(radix));
        Stack<BigInteger> stack = new Stack<>();
        while (number.compareTo(base) >= 0) {
            stack.push(number.mod(base));
            number = number.divide(base);
        }

        int index = Integer.parseInt(number.toString());
        StringBuilder builder = new StringBuilder();
        builder.append(symbols.charAt(index));

        while (!stack.isEmpty()) {
            index = Integer.parseInt(stack.pop().toString());
            builder.append(symbols.charAt(index));
        }
        return builder.toString();
    }

    private BigInteger convertFromRadix(String string, int radix) {
        BigInteger val = BigInteger.ZERO;
        BigInteger base = new BigInteger(Integer.toString(radix));
        for (int i = 0; i < string.length(); i++) {
            // prefix * base ^ x
            char c = string.charAt(string.length() - 1 - i);
            int actualVal = symbols.indexOf(c);
            BigInteger mul = base.pow(i).multiply(new BigInteger(Integer.toString(actualVal)));
            val = val.add(mul);
        }
        return val;
    }


    public String applySymbols(BigInteger number, Map<Character, Character> numberMapping, int base) {
        String converted = convertToRadix(number, base);
        //        String converted = number.toString(base);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < converted.length(); i++) {
            builder.append(numberMapping.get(converted.charAt(i)));
        }
        return builder.toString();
    }

    public static void main(String[] args) throws IOException {
        new FullAdder().solve();
    }
}

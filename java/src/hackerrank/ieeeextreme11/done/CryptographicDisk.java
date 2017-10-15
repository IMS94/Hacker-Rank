/*
 * Copyright to Eduze@UoM 2017
 */

package hackerrank.ieeeextreme11.done;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CryptographicDisk {

    private Map<Character, Double> angles = new HashMap<>();
    private int radius;
    private String phrase;
    private double threadLength;

    private void read() {
        Scanner scanner = new Scanner(System.in);
        radius = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < 26; i++) {
            String[] parts = scanner.nextLine().split(" ");
            angles.put(Character.toUpperCase(parts[0].trim().charAt(0)), Double.parseDouble(parts[1]));
        }

        phrase = scanner.nextLine().trim();
    }

    private void solve() {
        char previousChar = ' ';
        // Adding initial radius to go to first character
        threadLength = 0;

        for (int i = 0; i < phrase.length(); i++) {
            try {
                char currentChar = phrase.charAt(i);
                if (!Character.isLetter(currentChar)) {
                    continue;
                }

                currentChar = Character.toUpperCase(currentChar);
                if (!angles.containsKey(currentChar)) {
                    continue;
                }

                if (previousChar == ' ') {
                    threadLength += radius;
                    previousChar = currentChar;
                    continue;
                }

                if (currentChar == previousChar) {
                    continue;
                }

                if (angles.get(previousChar).doubleValue() == angles.get(currentChar).doubleValue()) {
                    continue;
                }

                double alpha = simpleAngleBetween(angles.get(previousChar), angles.get(currentChar)) / 2;
                double radians = alpha * Math.PI;
                radians = radians / 180.0;

                threadLength += 2 * radius * Math.sin(radians);

                previousChar = currentChar;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println((long) Math.ceil(threadLength));
    }

    private double simpleAngleBetween(double a, double b) {
        double difference;
        if (a > b) {
            difference = a - b;
        } else {
            difference = b - a;
        }

        return difference >= 180.0 ? 360 - difference : difference;
    }

    public static void main(String[] args) {
        CryptographicDisk cryptographicDisk = new CryptographicDisk();
        cryptographicDisk.read();
        cryptographicDisk.solve();
    }

}

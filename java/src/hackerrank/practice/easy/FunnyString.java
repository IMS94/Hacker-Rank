package hackerrank.practice.easy;

import java.util.Scanner;

public class FunnyString {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < testCases; i++) {
            String string = scanner.nextLine();
            int l = string.length();
            boolean funny = true;
            for (int j = 1; j < l; j++) {
                int ordered = Math.abs(string.charAt(j) - string.charAt(j - 1));
                int reversed = Math.abs(string.charAt(l - j - 1) - string.charAt(l - j));
                if (ordered != reversed) {
                    funny = false;
                    break;
                }
            }

            System.out.println(funny ? "Funny" : "Not Funny");
        }
    }

    public static void main(String[] args) {
        new FunnyString().solve();
    }
}

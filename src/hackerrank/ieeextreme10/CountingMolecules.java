package hackerrank.ieeextreme10;

import java.util.Scanner;

public class CountingMolecules {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");

        double c = Double.parseDouble(line[0]);
        double h = Double.parseDouble(line[1]);
        double o = Double.parseDouble(line[2]);

        double glucose = (h / 2 + 2 * c - o) / 12;
        double water = h / 2 - 6 * glucose;
        double co2 = c - 6 * glucose;

        if (glucose < 0 || co2 < 0 || water < 0 || water % 1 != 0 || glucose % 1 != 0 || co2 % 1 != 0) {
            System.out.println("Error");
        } else {
            System.out.printf("%d %d %d", (long) Math.abs(water), (long) Math.abs(co2), (long) Math.abs(glucose));
        }
    }

    public static void main(String[] args) {
        new CountingMolecules().solve();
    }
}

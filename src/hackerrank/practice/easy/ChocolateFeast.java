package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/29/16.
 */
public class ChocolateFeast {

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int trips = scanner.nextInt();
        int[] answers = new int[trips];

        for (int i = 0; i < trips; i++) {
            int dollars = scanner.nextInt();
            int price = scanner.nextInt();
            int wrapperLimit = scanner.nextInt();

            // initial amount of chocolates
            int chocolateCount = dollars / price;
            // initial wrapper count
            int wrappersLeft = chocolateCount;
            while (wrappersLeft >= wrapperLimit) {
                int chocolatesBuyable = wrappersLeft / wrapperLimit;
                wrappersLeft = wrappersLeft % wrapperLimit;
                // add the wrappers of the bonus chocolates
                wrappersLeft += chocolatesBuyable;
                chocolateCount += chocolatesBuyable;
            }
            answers[i] = chocolateCount;
        }

        for (int chocs : answers) {
            System.out.println(chocs);
        }
    }

    public static void main(String[] args) {
        ChocolateFeast chocolateFeast = new ChocolateFeast();
        chocolateFeast.readAndSolve();
    }
}

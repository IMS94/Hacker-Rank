package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/25/16.
 */
public class ServiceLane {
    private int highwayLength;
    private int testCases;
    private int[] widths;
    private int[] answers;

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        highwayLength = scanner.nextInt();
        testCases = scanner.nextInt();
        widths = new int[highwayLength];
        answers = new int[testCases];
        scanner.nextLine();

        //read lane widths
        for (int i = 0; i < highwayLength; i++) {
            widths[i] = scanner.nextInt();
        }

        for (int x = 0; x < testCases; x++) {
            scanner.nextLine();
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            int maxWidth = 3;
            for (int y = i; y <= j; y++) {
                if (widths[y] < maxWidth) {
                    maxWidth = widths[y];
                }
            }
            answers[x] = maxWidth;
        }
    }

    public void printAnswers() {
        for (int ans : answers) {
            System.out.println(ans);
        }
    }

    public static void main(String[] args) {
        ServiceLane serviceLane = new ServiceLane();
        serviceLane.readAndSolve();
        serviceLane.printAnswers();
    }

}

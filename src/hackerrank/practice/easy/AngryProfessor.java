package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/25/16.
 */
public class AngryProfessor {
    private int T;
    private byte[] answers;

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        answers = new byte[T];

        for (int i = 0; i < T; i++) {
            scanner.nextLine();
            int numOfStudents = scanner.nextInt();
            int treshold = scanner.nextInt();
            int presentStudentCount = 0;
            scanner.nextLine();

            for (int j = 0; j < numOfStudents; j++) {
                int arrivalTime = scanner.nextInt();
                if (arrivalTime <= 0) {
                    presentStudentCount++;
                }
            }

            if (presentStudentCount < treshold) {
                answers[i] = 1;
            } else {
                answers[i] = 0;
            }
        }

        for (byte ans : answers) {
            System.out.println(ans == 1 ? "YES" : "NO");
        }
    }

    public static void main(String[] args) {
        AngryProfessor angryProfessor = new AngryProfessor();
        angryProfessor.readAndSolve();
    }
}

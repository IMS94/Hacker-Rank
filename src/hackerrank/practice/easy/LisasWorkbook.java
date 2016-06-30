package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/29/16.
 */
public class LisasWorkbook {

    public void readAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int chapters = scanner.nextInt();
        int questionsPerPage = scanner.nextInt();

        int page = 0;
        int specials = 0;
        int questionsInCurrentChapter = 0;
        int minQuestionNumber = 0;
        while (true) {
            page++;

            if (chapters == 0 && questionsInCurrentChapter <= 0) {
                break;
            }

            if (questionsInCurrentChapter <= 0) {
                questionsInCurrentChapter = scanner.nextInt();
                minQuestionNumber = 1;
                chapters--;
            }

            int maxQuestionNumber = questionsInCurrentChapter < questionsPerPage ?
                    minQuestionNumber + questionsInCurrentChapter - 1 : minQuestionNumber + questionsPerPage - 1;

            if (minQuestionNumber <= page && page <= maxQuestionNumber) {
                specials++;
            }
            minQuestionNumber = maxQuestionNumber + 1;
            questionsInCurrentChapter = questionsInCurrentChapter - questionsPerPage;
        }

        System.out.println(specials);
    }

    public static void main(String[] args) {
        LisasWorkbook lisasWorkbook = new LisasWorkbook();
        lisasWorkbook.readAndSolve();
    }
}

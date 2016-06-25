package hackerrank.practice.easy;

import java.util.Scanner;

/**
 * Created by imesha on 6/24/16.
 */
public class TimeConversion {

    public void readAndConvert() {
        Scanner scanner = new Scanner(System.in);
        String time = scanner.nextLine();
        int hour = Integer.parseInt(time.substring(0, 2));
        String ampm = time.substring(time.length() - 2, time.length());
        if (ampm.equals("AM")) {
            hour = hour == 12 ? 0 : hour;
        } else {
            hour = hour == 12 ? hour : hour + 12;
        }
        System.out.println(String.format("%02d", hour) + time.substring(2, time.length() - 2));
    }

    public static void main(String[] args) {
        TimeConversion timeConversion = new TimeConversion();
        timeConversion.readAndConvert();
    }
}

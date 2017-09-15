package hackerrank.ieeextreme10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Safety {

    private String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String password = reader.readLine();
        int commandCount = Integer.parseInt(reader.readLine());

        Map<Character, Character> alphabetMap = new HashMap<>();
        for (int i = 0; i < alphabet.length(); i++) {
            alphabetMap.put(alphabet.charAt(i), alphabet.charAt((i + 1) % 26));
        }

        StringBuilder builder = new StringBuilder(password);

        for (int iter = 0; iter < commandCount; iter++) {
            String commands[] = reader.readLine().split(" ");
            int command = Integer.parseInt(commands[0]);
            if (command == 1) {
                int i = Integer.parseInt(commands[1]) - 1;
                int j = Integer.parseInt(commands[2]) - 1;
                int k = Integer.parseInt(commands[3]) - 1;
                String str1 = builder.substring(i, j + 1);
                String str2 = builder.substring(k, k + j - i + 1);
                if (str1.length() == str2.length() && str1.equals(str2)) {
                    System.out.println("Y");
                } else {
                    System.out.println("N");
                }
            } else if (command == 2) {
                int i = Integer.parseInt(commands[1]) - 1;
                int j = Integer.parseInt(commands[2]) - 1;
                int k = Integer.parseInt(commands[3]) - 1;
                String str2 = builder.substring(k, k + j - i + 1);
                builder.replace(i, j + 1, str2);
            } else if (command == 3) {
                int i = Integer.parseInt(commands[1]) - 1;
                int j = Integer.parseInt(commands[2]) - 1;
                for (int x = i; x < j + 1; x++) {
                    builder.setCharAt(x, alphabetMap.get(builder.charAt(x)));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Safety().solve();
    }
}

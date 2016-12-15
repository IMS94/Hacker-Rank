package hackerrank.ieeextreme10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FoodTruck {

    public void solve() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] lines = reader.readLine().split(",");
        double latitude = Double.parseDouble(lines[0]);
        double longitude = Double.parseDouble(lines[1]);

        double radius = Double.parseDouble(reader.readLine());

        reader.readLine();

        Map<Long, Date> subscribers = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            builder.append(Character.toChars(reader.read()));
        }

        String[] inputReceived = builder.toString().split("\n");
        for (String line : inputReceived) {
            String[] input = line.split(",");

            Date date = dateFormat.parse(input[0]);
            double lat = Double.parseDouble(input[1]);
            double lon = Double.parseDouble(input[2]);
            long phone = Long.parseLong(input[3]);

            double distance = 2 * 6378.137 *
                    Math.asin(Math.sqrt(Math.pow(Math.sin((Math.PI / 180) * (latitude - lat) / 2), 2) +
                            Math.cos((Math.PI / 180) * lat) * Math.cos((Math.PI / 180) * latitude) * Math.pow(Math.sin((Math.PI / 180) * (longitude - lon) / 2), 2)));

            Date entry = subscribers.get(phone);
            if (entry == null) {
                if (distance < radius) {
                    subscribers.put(phone, date);
                }
            } else {
                if (entry.before(date)) {
                    if (distance < radius) {
                        subscribers.put(phone, date);
                    } else {
                        subscribers.remove(phone);
                    }
                }
            }
        }

        List<Long> phoneNumbers = new ArrayList<>(subscribers.keySet());
        Collections.sort(phoneNumbers);
        for (int num = 0; num < phoneNumbers.size(); num++) {
            System.out.printf(phoneNumbers.get(num) + (num != phoneNumbers.size() - 1 ? "," : ""));
        }
        //        System.out.println("");
    }

    public static void main(String[] args) throws IOException, ParseException {
        new FoodTruck().solve();
    }
}

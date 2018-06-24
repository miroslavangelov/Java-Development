package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        Map<String, String> phoneBook = new HashMap<>();

        while (!input.equals("search")) {
            String[] data = input.split("-");
            String name = data[0];
            String phoneNumber =  data[1];

            phoneBook.put(name, phoneNumber);
            input = reader.readLine();
        }
        input = reader.readLine();
        while (!input.equals("stop")) {
            String searchedName = input;

            if (phoneBook.containsKey(searchedName)) {
                System.out.println(String.format("%s -> %s", searchedName, phoneBook.get(searchedName)));
            } else {
                System.out.println(String.format("Contact %s does not exist.", searchedName));
            }

            input = reader.readLine();
        }
    }
}

package DataStructures.HashTables.Exercise;

import DataStructures.HashTables.Lab.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Phonebook {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashTable<String, String> contacts = new HashTable<>();
        String input = reader.readLine();

        while (!"search".equals(input)) {
            String[] contactData = input.split("-");
            String contactName = contactData[0];
            String contactPhone = contactData[1];

            contacts.addOrReplace(contactName, contactPhone);
            input = reader.readLine();
        }
        input = reader.readLine();

        while (!"end".equals(input)) {
            String currentContact = input;

            if (contacts.containsKey(currentContact)) {
                System.out.println(String.format("%s -> %s", currentContact, contacts.get(currentContact)));
            } else {
                System.out.println(String.format("Contact %s does not exist.", currentContact));
            }

            input = reader.readLine();
        }
    }
}

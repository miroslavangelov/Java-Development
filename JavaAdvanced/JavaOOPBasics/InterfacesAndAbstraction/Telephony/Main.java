package JavaOOPBasics.InterfacesAndAbstraction.Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers  = reader.readLine().split(" ");
        Smartphone smartphone = new Smartphone();
        for (String number: numbers) {
            System.out.println(smartphone.call(number));
        }
        String[] sites  = reader.readLine().split(" ");
        for (String site: sites) {
            System.out.println(smartphone.browse(site));
        }
    }
}

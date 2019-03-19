package JavaAdvanced.IntroToJava;

import java.util.Scanner;

public class ByteParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        int[] numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = Integer.parseInt(scanner.nextLine());
        }
        String row = scanner.nextLine();
        while (!row.equals("party over")) {
            if (row.equals("party over")) break;
            String[] commands = row.split(" ");
            String command = commands[0];
            int position = Integer.parseInt(commands[1]);
            for (int i = 0; i < count; i++) {
                if (command.equals("-1")) {
                    numbers[i] = numbers[i] ^ (1<<position);
                } else if (command.equals("0")) {
                    numbers[i] = numbers[i] & ~(1<<position);
                } else if (command.equals("1")) {
                    numbers[i] = numbers[i] | (1<<position);
                }
            }
            row = scanner.nextLine();
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}

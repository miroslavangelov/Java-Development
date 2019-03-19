package JavaAdvanced.ObjectsClassesAndCollections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SimpleTextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int commandsCount = Integer.parseInt(scanner.nextLine());

        Deque<String> textHistory = new ArrayDeque<>();
        String text = "";

        for (int i = 0; i < commandsCount; i++) {
            String[] currentCommand = scanner.nextLine().split(" ");
            String command = currentCommand[0];

            switch (command) {
                case "1": {
                    textHistory.push(text);
                    text += currentCommand[1];
                    break;
                }
                case "2": {
                    textHistory.push(text);
                    int endIndex = text.length() - Integer.parseInt(currentCommand[1]);
                    text = text.substring(0, endIndex);
                    break;
                }
                case "3":
                    int index = Integer.parseInt(currentCommand[1]) - 1;
                    System.out.println(text.charAt(index));
                    break;
                case "4":
                    text = textHistory.pop();
                    break;
            }
        }
    }
}

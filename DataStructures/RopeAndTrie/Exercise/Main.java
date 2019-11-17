package DataStructures.RopeAndTrie.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TextEditor textEditor = new TextEditorImpl();
        String input = reader.readLine();

        while (!"end".equals(input)) {
            String[] inputData = input.split(" ");

            switch (inputData[0])  {
                case "login":
                    textEditor.login(inputData[1]);
                    break;
                case "users":
                    if (inputData.length > 1) {
                        for (String user: textEditor.users(inputData[1])) {
                            System.out.println(user);
                        }
                    } else {
                        for (String user: textEditor.users("")) {
                            System.out.println(user);
                        }
                    }
                    break;
                case "logout":
                    textEditor.logout(inputData[1]);
                    break;
                default:
                    String userName = inputData[0];

                    switch (inputData[1]) {
                        case "insert":
                            int index = Integer.parseInt(inputData[2]);
                            String str = input.split("\"")[1];
                            textEditor.insert(userName, index, str);
                            break;
                        case "prepend":
                            str = input.split("\"")[1];
                            textEditor.prepend(userName, str);
                            break;
                        case "substring":
                            int startIndex = Integer.parseInt(inputData[2]);
                            int length = Integer.parseInt(inputData[3]);
                            textEditor.substring(userName, startIndex, length);
                            break;
                        case "delete":
                            startIndex = Integer.parseInt(inputData[2]);
                            length = Integer.parseInt(inputData[3]);
                            textEditor.delete(userName, startIndex, length);
                            break;
                        case "clear":
                            textEditor.clear(userName);
                            break;
                        case "length":
                            System.out.println(textEditor.length(userName));
                            break;
                        case "print":
                            System.out.println(textEditor.print(userName));
                            break;
                        case "undo":
                            textEditor.undo(userName);
                            break;
                    }
            }

            input = reader.readLine();
        }
    }
}

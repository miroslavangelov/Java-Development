package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfixToPostfix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        Deque<String> outputQueue = new ArrayDeque<>();
        Deque<String> operatorStack = new ArrayDeque<>();
        for (int i = 0; i < input.length; i++) {
            String currentToken = input[i];

            if (isNumeric(currentToken)) {
                outputQueue.add(currentToken);
                continue;
            }

            if (isOperator(currentToken)) {
                while(!operatorStack.isEmpty() && isEqualOrGreater(operatorStack.peek(), currentToken)) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.push(currentToken);
                continue;
            }

            if (currentToken.equals("(")) {
                operatorStack.push(currentToken);
                continue;
            }

            if (currentToken.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.pop();
            }
        }

        while (!operatorStack.isEmpty()) {
            outputQueue.add(operatorStack.pop());
        }

        StringBuilder result = new StringBuilder();
        for (String token: outputQueue) {
            result.append(token);
            result.append(" ");
        }
        System.out.println(result.toString().trim());
    }

    private static boolean isNumeric(String token)
    {
        String expression = "^[a-z0-9]+$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(token);

        return matcher.find();
    }

    private static boolean isOperator(String token)
    {
        switch (token) {
            case "+":
            case "-":
            case "/":
            case "*":
                return true;
            default:
                return false;
        }
    }

    private static boolean isEqualOrGreater(String lastOperator, String token) {
        if (lastOperator.equals("(")){
            return false;
        }
        boolean isLastOperatorHigh = lastOperator.equals("*") || lastOperator.equals("/");
        boolean isTokenHigh = token.equals("*") || token.equals("/");

        if (!isLastOperatorHigh && isTokenHigh){
            return false;
        }

        return true;
    }
}

package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluateExpression {
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

        Deque<Double> stack = new ArrayDeque<>();

        for (String token: outputQueue) {
            Double numToken = returnNumber(token);
            if (numToken != null){
                stack.push(numToken);
            }
            else {
                Double result = calculateResult(token, stack.pop(), stack.pop());
                stack.push(result);
            }
        }

        System.out.println(String.format("%.2f",stack.peek()));
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

    private static Double calculateResult(String operator, Double num2, Double num1) {
        double result = 0;

        switch (operator){
            case "+":
                result = num1 + num2;break;
            case "-":
                result = num1 - num2;break;
            case "*":
                result = num1 * num2;break;
            case "/":
                result = num1 / num2;break;
        }

        return result;
    }

    private static Double returnNumber(String token) {
        String expression = "^\\d+(?:\\.\\d+)?$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(token);

        if (matcher.find()){
            return Double.valueOf(matcher.group());
        }
        return null;
    }
}

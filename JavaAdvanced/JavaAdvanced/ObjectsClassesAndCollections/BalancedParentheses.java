package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BalancedParentheses {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] parentheses = reader.readLine().toCharArray();
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : parentheses) {
            switch (c) {
                case '{':
                case '[':
                case '(':
                    stack.push(c);
                    break;
                case '}':
                    if (stack.size() == 0 || stack.pop() != '{'){
                        System.out.println("NO");
                        return;
                    }
                    break;
                case ']':
                    if (stack.size() == 0 || stack.pop() != '['){
                        System.out.println("NO");
                        return;
                    }
                    break;
                case ')':
                    if (stack.size() == 0 || stack.pop() != '('){
                        System.out.println("NO");
                        return;
                    }
            }
        }

        System.out.println("YES");
    }
}

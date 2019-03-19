package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class TheStockSpanProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int entriesCount = Integer.parseInt(br.readLine());

        int[] dailyPriceQuotes = new int[entriesCount];

        dailyPriceQuotes[0] = Integer.parseInt(br.readLine());

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        StringBuilder result = new StringBuilder("1\r\n");

        for (int i = 1; i < entriesCount; i++) {
            dailyPriceQuotes[i] = Integer.parseInt(br.readLine());

            while (!stack.isEmpty() && dailyPriceQuotes[stack.peek()] <= dailyPriceQuotes[i]) {
                stack.pop();
            }

            result.append(stack.isEmpty() ? i + 1 : i - stack.peek());
            result.append("\r\n");
            stack.push(i);
        }

        System.out.print(result);
    }
}
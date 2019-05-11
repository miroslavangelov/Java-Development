package Algorithms.Recursion.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RecursiveArraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(arraySum(nums, 0));
    }

    private static int arraySum(int[] nums, int index) {
        if (index == nums.length) {
            return 0;
        }
        return nums[index] + arraySum(nums, index + 1);
    }
}

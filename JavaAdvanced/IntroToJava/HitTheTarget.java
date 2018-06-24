package JavaAdvanced.IntroToJava;

import java.util.Scanner;

public class HitTheTarget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                if(i+j == number){
                    System.out.printf("%d + %d = %d%n",i,j,number);
                }
            }
        }
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                if(j-i == number){
                    System.out.printf("%d - %d = %d%n",j,i,number);
                }
            }
        }
    }
}

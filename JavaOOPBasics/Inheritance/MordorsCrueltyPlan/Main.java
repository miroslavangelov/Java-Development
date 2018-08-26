package JavaOOPBasics.Inheritance.MordorsCrueltyPlan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] foods = reader.readLine().toLowerCase().split(" ");
        Gandalf gandalf = new Gandalf();
        for (String food: foods) {
            gandalf.addFood(food);
        }
        System.out.println(gandalf.getHappiness());
        System.out.println(gandalf.getMood());
    }
}

package JavaOOPBasics.Inheritance.MordorsCrueltyPlan;

import java.util.ArrayList;

public class Gandalf {
    private ArrayList<String> foods;
    private int happiness;

    public Gandalf() {
        this.happiness = 0;
        this.foods = new ArrayList<>();
    }

    public void addFood(String food) {
        this.foods.add(food);
    }

    public int getHappiness() {
        for (String food: foods) {
            switch (food) {
                case "cram": this.happiness += 2;break;
                case "lembas": this.happiness += 3;break;
                case "apple": this.happiness += 1;break;
                case "melon": this.happiness += 1;break;
                case "honeycake": this.happiness += 5;break;
                case "mushrooms": this.happiness -= 10;break;
                default: this.happiness -= 1;break;
            }
        }
        return this.happiness;
    }

    public String getMood() {
        if (this.happiness < -5) {
            return "Angry";
        } else if (this.happiness >= -5 && this.happiness <= 0) {
            return "Sad";
        } else if (this.happiness > 0 && this.happiness <= 15) {
            return "Happy";
        } else {
            return "JavaScript";
        }
    }
}

package JavaOOPBasics.InterfacesAndAbstraction.FoodShortage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        Map<String, Buyer> buyers = new HashMap<>();

        for (int i = 0; i < count; i++) {
            String[] buyerData = reader.readLine().split(" ");
            String buyerName = buyerData[0];
            int buyerAge = Integer.parseInt(buyerData[1]);
            if (buyerData.length == 4) {
                String id = buyerData[2];
                String birthdate = buyerData[3];
                Buyer citizen = new Human(buyerName, buyerAge, id, birthdate);
                buyers.putIfAbsent(buyerName, citizen);
            } else if (buyerData.length == 3) {
                String rebelGroup = buyerData[2];
                Buyer rebel = new Rebel(buyerName, buyerAge, rebelGroup);
                buyers.putIfAbsent(buyerName, rebel);
            }
        }

        String currentBuyer = reader.readLine();

        while (!"End".equals(currentBuyer)) {
            if (buyers.get(currentBuyer) != null) {
                Buyer buyer = buyers.get(currentBuyer);
                buyer.buyFood();
            }
            currentBuyer = reader.readLine();
        }

        int totalFood = 0;
        for (Buyer buyer : buyers.values()) {
            totalFood += buyer.getFood();
        }

        System.out.println(totalFood);
    }
}

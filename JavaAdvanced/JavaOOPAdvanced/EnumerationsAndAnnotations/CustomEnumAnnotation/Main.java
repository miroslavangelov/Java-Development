package JavaOOPAdvanced.EnumerationsAndAnnotations.CustomEnumAnnotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String className = reader.readLine();
        CustomAnnotation customAnnotation = null;

        switch (className) {
            case "Rank":
                Class cardRank = CardRank.class;
                customAnnotation = (CustomAnnotation) cardRank.getAnnotation(CustomAnnotation.class);
                break;
            case "Suit":
                Class cardSuit = CardSuit.class;
                customAnnotation = (CustomAnnotation) cardSuit.getAnnotation(CustomAnnotation.class);
                break;
        }
        if (customAnnotation != null) {
            System.out.println(String.format(
                    "Type = %s, Description = %s",
                    customAnnotation.type(),
                    customAnnotation.description()
            ));
        }
    }
}

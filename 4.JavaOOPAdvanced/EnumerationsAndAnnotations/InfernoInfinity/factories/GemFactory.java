package JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.factories;

import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.models.Gem;

public class GemFactory {
    public static Gem create(String gemType){
        try {
            return Gem.valueOf(gemType.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }
}

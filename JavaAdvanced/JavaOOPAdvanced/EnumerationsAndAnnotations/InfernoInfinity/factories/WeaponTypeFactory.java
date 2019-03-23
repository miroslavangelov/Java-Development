package JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.factories;

import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.models.WeaponType;

public class WeaponTypeFactory {
    public static WeaponType create(String weaponType){
        try {
            return WeaponType.valueOf(weaponType.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }
}

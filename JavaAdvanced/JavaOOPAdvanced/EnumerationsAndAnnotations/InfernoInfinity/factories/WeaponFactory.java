package JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.factories;

import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.models.Weapon;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.models.WeaponType;

public class WeaponFactory {
    public static Weapon create(String name, WeaponType weaponType) {
            return new Weapon(name, weaponType);
    }
}

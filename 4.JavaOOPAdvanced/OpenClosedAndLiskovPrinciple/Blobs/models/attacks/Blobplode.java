package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.attacks;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Attack;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;

public class Blobplode implements Attack {
    @Override
    public void execute(Blob attacker, Blob target) {
        int attackerHealth = attacker.getHealth() - (attacker.getHealth() / 2);

        if (attackerHealth >= 1) {
            attacker.setHealth(attackerHealth);
        }

        target.setHealth(target.getHealth() - (attacker.getDamage() * 2));
    }
}

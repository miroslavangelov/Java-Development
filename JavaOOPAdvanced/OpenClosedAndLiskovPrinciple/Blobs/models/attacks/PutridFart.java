package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.attacks;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Attack;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;

public class PutridFart implements Attack {
    @Override
    public void execute(Blob attacker, Blob target) {
        int currentHealth = target.getHealth();
        currentHealth -= attacker.getDamage();
        target.setHealth(currentHealth);
    }
}

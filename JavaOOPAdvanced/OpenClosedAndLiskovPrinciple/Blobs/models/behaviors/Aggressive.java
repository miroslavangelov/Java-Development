package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.behaviors;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;

public class Aggressive extends AbstractBehavior {
    private static final int AGGRESSIVE_DAMAGE_MULTIPLY = 2;
    private static final int AGGRESSIVE_DAMAGE_DECREMENT = 5;

    private int sourceInitialDamage;


    @Override
    public void trigger(Blob source) {
        super.trigger(source);
        this.sourceInitialDamage = source.getDamage();
        this.setIsTriggered(true);
        source.setDamage(source.getDamage() * AGGRESSIVE_DAMAGE_MULTIPLY);
    }

    @Override
    public void applyRecurrentEffect(Blob source) {
        if (super.toDelayRecurrentEffect()) {
            super.setToDelayRecurrentEffect(false);
        } else {
            source.setDamage(source.getDamage() - AGGRESSIVE_DAMAGE_DECREMENT);

            if (source.getDamage() <= this.sourceInitialDamage) {
                source.setDamage(this.sourceInitialDamage);
            }
        }
    }
}

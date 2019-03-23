package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.behaviors;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Behavior;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;

public abstract class AbstractBehavior implements Behavior {
    private boolean isTriggered;
    private boolean toDelayRecurrentEffect;

    AbstractBehavior() {
        this.toDelayRecurrentEffect = true;
    }

    @Override
    public void trigger(Blob source) {
        if (this.isTriggered) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isTriggered() {
        return this.isTriggered;
    }

    protected boolean toDelayRecurrentEffect() {
        return this.toDelayRecurrentEffect;
    }

    protected void setToDelayRecurrentEffect(boolean toDelayRecurrentEffect){
        this.toDelayRecurrentEffect = toDelayRecurrentEffect;
    }

    protected void setIsTriggered(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }
}

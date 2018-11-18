package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;

public interface Behavior {
    void trigger(Blob source);

    void applyRecurrentEffect(Blob source);

    boolean isTriggered();
}

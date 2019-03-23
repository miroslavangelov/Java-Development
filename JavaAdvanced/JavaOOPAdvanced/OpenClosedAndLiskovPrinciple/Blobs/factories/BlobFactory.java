package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.factories;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Attack;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Behavior;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.observers.Subject;

public class BlobFactory {
    private BlobFactory() {
    }

    public static Blob create(String name, int health, int damage, Behavior behavior, Attack attack, Subject subject, boolean flag) {
        return new Blob(name, health, damage, behavior, attack, subject, flag);
    }
}

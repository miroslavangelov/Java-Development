package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;

public interface Attack {
    void execute(Blob attacker, Blob target);
}

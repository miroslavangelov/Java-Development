package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.observers;

public abstract class AbstractObserver {
    protected Subject subject;

    public abstract void update();
}

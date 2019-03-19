package app.models.specials;

import app.contracts.Special;

public abstract class AbstractSpecial implements Special {
    private boolean isActive;

    protected AbstractSpecial() {
        this.isActive = false;
    }

    protected void setActive(boolean active) {
        isActive = active;
    }

    protected boolean isActive() {
        return this.isActive;
    }
}

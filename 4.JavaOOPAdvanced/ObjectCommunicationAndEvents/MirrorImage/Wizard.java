package JavaOOPAdvanced.ObjectCommunicationAndEvents.MirrorImage;

import java.util.ArrayList;
import java.util.List;

public class Wizard {
    private int id;
    private String name;
    private int magicalPower;
    private List<Wizard> copies;

    public Wizard(int id, String name, int magicalPower) {
        this.id = id;
        this.name = name;
        this.magicalPower = magicalPower;
        this.copies = new ArrayList<>(2);
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public int getMagicalPower() {
        return this.magicalPower;
    }

    public void castFireball() {
        System.out.println(String.format("%s casts Fireball for %d damage", this.toString(), this.getMagicalPower()));
        for (Wizard copy : copies) {
            copy.castFireball();
        }
    }

    public void castReflection() {
        System.out.println(String.format("%s casts Reflection", this.toString()));
        for (Wizard copy : copies) {
            copy.castReflection();
        }

        for (int i = 0; i < 2; i++) {
            if (this.copies.size() >= 2) {
                break;
            }

            Wizard newWizard = WizardFactory.create(this.getName(), this.getMagicalPower() / 2);
            this.copies.add(newWizard);
            Repository.add(newWizard);
        }
    }

    @Override
    public String toString() {
        return String.format("%s %d", this.getName(), this.getId());
    }
}

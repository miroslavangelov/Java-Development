package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.core.commands;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.annotations.Inject;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Executable;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Repository;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;

public class AttackCommand implements Executable {
    @Inject
    private String[] data;

    @Inject
    private Repository<Blob> blobRepository;

    @Override
    public void execute() {
        String attackerName = this.data[0];
        String targetName = this.data[1];

        Blob attacker = this.blobRepository.getByName(attackerName);
        Blob target = this.blobRepository.getByName(targetName);

        attacker.attack(target);
    }
}

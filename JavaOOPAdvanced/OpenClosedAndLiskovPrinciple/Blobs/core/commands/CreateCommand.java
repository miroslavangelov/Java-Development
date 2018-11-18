package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.core.commands;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.annotations.Inject;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.factories.AttackFactory;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.factories.BehaviorFactory;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.factories.BlobFactory;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Attack;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Behavior;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Executable;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Repository;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.observers.Subject;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.utility.MutateBoolean;

public class CreateCommand implements Executable {
    @Inject
    private String[] data;

    @Inject
    private Repository<Blob> blobRepository;

    @Inject
    private Subject subject;

    @Inject
    private MutateBoolean mutateBoolean;

    @Override
    public void execute() {
        String name = this.data[0];
        int health = Integer.parseInt(this.data[1]);
        int damage = Integer.parseInt(this.data[2]);
        String behaviorType = this.data[3];
        String attackType = this.data[4];

        Behavior behavior = BehaviorFactory.create(behaviorType);
        Attack attack = AttackFactory.create(attackType);

        Blob blob = BlobFactory.create(name, health, damage, behavior, attack, this.subject, this.mutateBoolean.getFlag());

        this.blobRepository.add(blob);
    }
}

package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.core.commands;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.annotations.Inject;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Executable;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.utility.MutateBoolean;

public class ReportEventsCommand implements Executable {
    @Inject
    private MutateBoolean mutateBoolean;

    @Override
    public void execute() {
        this.mutateBoolean.setFlag(true);
    }
}

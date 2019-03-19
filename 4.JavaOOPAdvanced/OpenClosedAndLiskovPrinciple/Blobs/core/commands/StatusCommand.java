package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.core.commands;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.annotations.Inject;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Executable;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Repository;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Writer;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;

public class StatusCommand implements Executable {
    @Inject
    private Repository<Blob> blobRepository;

    @Inject
    private Writer writer;

    @Override
    public void execute() {
        this.blobRepository.findAll().forEach((Blob blob) -> this.writer.writeLine(blob.toString()));
    }
}

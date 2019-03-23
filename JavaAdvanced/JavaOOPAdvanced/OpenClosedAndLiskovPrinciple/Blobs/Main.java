package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.core.Engine;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Reader;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Repository;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Writer;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.io.ConsoleInputReader;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.io.ConsoleOutputWriter;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.observers.Subject;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.repositories.BlobRepository;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.utility.MutateBoolean;

public class Main {
    public static void main(String[] args) {
        Reader reader = new ConsoleInputReader();
        Writer writer = new ConsoleOutputWriter();
        Repository<Blob> blobRepository = new BlobRepository();
        Subject subject = new Subject();
        MutateBoolean mutateBoolean = new MutateBoolean(false);

        Engine engine = new Engine(reader, writer, blobRepository, subject, mutateBoolean);
        engine.run();
    }
}

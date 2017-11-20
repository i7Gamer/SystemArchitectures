package at.fhv.itb.sem5.exercise1.a;

import at.fhv.itb.sem5.lib.pmp.filter.Sink;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.Collection;

public class IndexSink extends Sink<Collection<Line>> {

    private BufferedWriter writer;
    private boolean skipEmptyLines = true;

    public IndexSink(Readable<Collection<Line>> input, String fileToWrite, boolean skipEmptyLines) throws InvalidParameterException, FileNotFoundException {
        super(input);
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileToWrite))));
        this.skipEmptyLines = skipEmptyLines;
    }

    public IndexSink(String fileToWrite, boolean skipEmptyLines) throws FileNotFoundException {
        super();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileToWrite))));
        this.skipEmptyLines = skipEmptyLines;
    }

    @Override
    public void write(Collection<Line> value) throws StreamCorruptedException {
        if (value == null) return;
        try {
            for (Line l : value) {
                if (l.isEmpty() && skipEmptyLines) continue;
                writer.write(l.toString());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            throw new StreamCorruptedException(e.getMessage());
        }
    }
}

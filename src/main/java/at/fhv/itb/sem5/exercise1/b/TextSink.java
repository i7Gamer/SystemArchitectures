package at.fhv.itb.sem5.exercise1.b;

import at.fhv.itb.sem5.exercise1.a.Alignment;
import at.fhv.itb.sem5.exercise1.a.Line;
import at.fhv.itb.sem5.lib.pmp.filter.Sink;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;

import java.io.*;
import java.security.InvalidParameterException;

public class TextSink extends Sink<Line> {

    private BufferedWriter writer;
    int limit;
    Alignment alignment;

    public TextSink(Readable<Line> input, String fileToWrite, int limit, Alignment alignment) throws InvalidParameterException, FileNotFoundException {
        super(input);
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileToWrite))));
        this.limit = limit;
        this.alignment = alignment;
    }

    public TextSink(String fileToWrite, int limit, Alignment alignment) throws FileNotFoundException {
        super();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileToWrite))));
        this.limit = limit;
        this.alignment = alignment;
    }

    @Override
    public void write(Line value) throws StreamCorruptedException {
        if (value == null) return;
        try {
            writer.write(value.asString(limit, alignment));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new StreamCorruptedException(e.getMessage());
        }
    }
}

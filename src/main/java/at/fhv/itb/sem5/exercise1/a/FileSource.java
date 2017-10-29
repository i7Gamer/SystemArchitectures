package at.fhv.itb.sem5.exercise1.a;

import javafx.util.Pair;
import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.*;
import java.security.InvalidParameterException;

public class FileSource extends Source<Pair<String,Long>>{

    private BufferedReader reader;
    private long lineNumber = 0;

    public FileSource(Writeable<Pair<String,Long>> output, String fileToRead) throws InvalidParameterException, FileNotFoundException {
        super(output);

        reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileToRead))));
    }

    public FileSource(String fileToRead) throws FileNotFoundException {
        super();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileToRead))));
    }

    @Override
    public Pair<String,Long> read() throws StreamCorruptedException {
        try {
            String line = reader.readLine();
            lineNumber++;
            if(line==null) return null;
            return new Pair<>(line.trim(), lineNumber);
        } catch (Exception e) {
            throw new StreamCorruptedException(e.getMessage());
        }
    }
}

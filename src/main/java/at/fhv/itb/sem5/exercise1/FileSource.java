package at.fhv.itb.sem5.exercise1;

import javafx.util.Pair;
import pmp.filter.Source;

import java.io.*;

public class FileSource extends Source<Pair<String,Long>>{

    private BufferedReader reader;
    private long lineNumber = 0;

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

    public void setFileToRead(String fileToRead) throws IOException {
        if(reader != null) reader.close();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileToRead))));
    }
}

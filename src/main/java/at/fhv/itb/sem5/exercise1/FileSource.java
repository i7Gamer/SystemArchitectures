package at.fhv.itb.sem5.exercise1;

import javafx.util.Pair;
import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.*;

/**
 * Created by timorzipa on 23.10.17.
 */
public class FileSource extends Source<Pair<String,Long>>{

    BufferedReader br;
    long i = 0;

    public FileSource(File file, Writeable<Pair<String,Long>> out) throws FileNotFoundException {
        super(out);
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    }

    @Override
    public Pair<String,Long> read() throws StreamCorruptedException {
        try {
            return new Pair<String,Long>(br.readLine(), ++i);
        } catch (IOException e) {
            throw new StreamCorruptedException(e.getMessage());
        }
    }
}
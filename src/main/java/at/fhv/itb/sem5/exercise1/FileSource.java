package at.fhv.itb.sem5.exercise1;

import pmp.filter.Source;

import java.io.*;

/**
 * Created by timorzipa on 23.10.17.
 */
public class FileSource extends Source<String>{

    BufferedReader br;

    public FileSource(File file) throws FileNotFoundException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    }

    @Override
    public String read() throws StreamCorruptedException {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new StreamCorruptedException(e.getMessage());
        }
    }
}
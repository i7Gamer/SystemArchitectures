package at.fhv.itb.sem5.exercise1.b;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.*;
import java.security.InvalidParameterException;

public class StreamSource extends Source<Character> {

    BufferedReader bufferedReader;

    public StreamSource(Writeable<Character> output, String fileName) throws InvalidParameterException, FileNotFoundException {
        super(output);
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
    }

    public StreamSource(String fileName) throws FileNotFoundException {
        super();
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
    }

    @Override
    public Character read() throws StreamCorruptedException {
        try {
            int character = bufferedReader.read();
            if (character == -1) {
                return null;
            }
            return (char) character;
        } catch (IOException e) {
            throw new StreamCorruptedException(e.getMessage());
        }
    }
}

package at.fhv.itb.sem5.exercise1;

import pmp.filter.Source;

import java.io.File;
import java.io.FileNotFoundException;

public class App
{.
    public static void main( String[] args ) throws FileNotFoundException {

        FileSource source = new FileSource(new File("aufgabe1/aliceInWonderland.txt"));

        Tokenizer t = new Tokenizer(source);
        IndexOutput sink = new IndexOutput(new AlphabeticalSorter(new FrequentWordsRemover(new Shifter(new Tokenizer(source)))));

    }
}

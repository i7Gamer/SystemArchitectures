package at.fhv.itb.sem5.sa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class App {

    private static String sourceFileName = "exercise1/aliceInWonderland.txt";
    private static String sinkFileName = "exercise1/aliceInWonderlandIndex.txt";
    private static String textSinkFileName = "exercise1/aliceInWonderlandText.txt";
    private static String frequentEnglishWords = "exercise1/frequentEnglishWords.txt";

    private static HashSet<String> frequentWords;

    public static void main(String[] args) throws IOException {
        frequentWords = getFrequentWords(frequentEnglishWords);

        Scanner s = new Scanner(System.in);

        System.out.println("A - generate Index | b - generate formatted index | anything else to exit");

        String input = s.next();

        if("A".equals(input)){
            doA();
        } else if("b".equals(input)){
            doB(s);
        }
    }

    private static void doA() throws FileNotFoundException {
        IndexSink fileSink = new IndexSink(sinkFileName, true);

        AlphabeticalSorter alphabeticalSorter = new AlphabeticalSorter((Writeable) fileSink);

        FrequentWordsRemover frequentWordsRemover = new FrequentWordsRemover((Writeable) alphabeticalSorter, frequentWords);

        Shifter shifter = new Shifter((Writeable) frequentWordsRemover);

        Tokenizer tokenizer = new Tokenizer((Writeable) shifter);

        FileSource fileSource = new FileSource((Writeable) tokenizer, sourceFileName);

        fileSource.run();
    }

    private static void doB(Scanner s) throws FileNotFoundException {
        String formatmode = "";
        int maxLength = 0;

        System.out.println("Please input maximum line-length in characters: ");
        maxLength = s.nextInt();

        while(!"L".equals(formatmode) && !"C".equals(formatmode) && !"R".equals(formatmode)) {
            System.out.println("Please select the alignment: [L] LEFT, [C] CENTER, [R] RIGHT");
            formatmode = s.next();
        }

        Alignment alignment;
        switch (formatmode){
            default:
            case "L":
                alignment = Alignment.LEFT;
                break;
            case "R":
                alignment = Alignment.RIGHT;
                break;
            case "C":
                alignment = Alignment.CENTER;
                break;
        }

        // index sink
        IndexSink indexSink = new IndexSink(sinkFileName, true);
        AlphabeticalSorter alphabeticalSorter = new AlphabeticalSorter((Writeable) indexSink);
        FrequentWordsRemover frequentWordsRemover = new FrequentWordsRemover((Writeable) alphabeticalSorter, frequentWords);
        Shifter shifter = new Shifter((Writeable) frequentWordsRemover);

        // text sink
        TextSink textSink = new TextSink(textSinkFileName, maxLength, alignment);

        DoublePipe<Line> doublePipe = new DoublePipe(shifter, textSink);
        LineBuilder lineBuilder = new LineBuilder(doublePipe, maxLength);
        WordBuilder wordBuilder = new WordBuilder(lineBuilder);
        StreamSource streamSource = new StreamSource(wordBuilder, sourceFileName);

        streamSource.run();
    }

    private static HashSet<String> getFrequentWords(String file) throws IOException {

        List<String> allLines = Files.readAllLines(Paths.get(file));

        HashSet<String> freqWords = new HashSet<>();
        for (String line : allLines) {
            String[] split = line.split("\t");
            freqWords.add(split[1].toLowerCase());
        }
        return freqWords;
    }
}

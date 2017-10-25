package at.fhv.itb.sem5.exercise1;

import javafx.util.Pair;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {

    public static void main(String[] args) throws IOException {

        String sourceFileName = "aufgabe1/aliceInWonderland.txt";
        String sinkFileName = "aufgabe1/aliceInWonderlandIndex.txt";

        // left side
        FileSource source = new FileSource();
        source.setFileToRead(sourceFileName);

        SimplePipe<Pair<String, Long>> source_tokenizer = new SimplePipe<>(source);

        Tokenizer tokenizer = new Tokenizer(source_tokenizer);
        tokenizer.setSplitRegex("\\s");

        SimplePipe<Pair<List<String>, Long>> tokenizer_shifter = new SimplePipe<Pair<List<String>, Long>>(tokenizer);

        // right side
        FileSink sink = new FileSink();
        sink.setFileToWrite(sinkFileName);
        sink.setSkipEmptyLines(true);

        SimplePipe<Pair<List<String>, Long>> alphabeticalSorter_sink = new SimplePipe<>(sink);

        //AlphabeticalSorter alphabeticalSorter = new AlphabeticalSorter(alphabeticalSorter_sink);

//        SimplePipe<Pair<List<String>, Long>> frequentWordsRemover_alphabeticalSorter = new SimplePipe<Pair<List<String>, Long>>(alphabeticalSorter);
//
//        FrequentWordsRemover frequentWordsRemover = new FrequentWordsRemover(frequentWordsRemover_alphabeticalSorter);

        //TODO: initialize
        //Set<String> wordsToRemove = new HashSet<>();

        //frequentWordsRemover.setFrequentWords(wordsToRemove);

        SimplePipe<Pair<List<String>, Long>> shifter_frequentWordsRemover = alphabeticalSorter_sink;
                //new SimplePipe<Pair<List<String>, Long>>(frequentWordsRemover);

        Shifter shifter = new Shifter(tokenizer_shifter, shifter_frequentWordsRemover);
        shifter.run();



        //        Sink<Collection<Pair<List<String>, Long>>> sink = new Sink<>() {
//            //private final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(sinkFileName))));
//
//            @Override
//            public void write(Collection<Pair<List<String>, Long>> value) throws StreamCorruptedException {
//                if (value == null) return;
//                for (Pair<List<String>, Long> line : value) {
//                    for (String word : line.getKey()) {
//                        System.out.print(word + " ");
//                    }
//                    System.out.print("\t" + line.getValue() + "\n");
//                }
////                try {
////                    for (Pair<List<String>, Long> line : value) {
////                        for (String word : line.getKey()) {
////                            writer.write(word);
////                            writer.write(" ");
////                        }
////                        writer.write(line.getValue().toString());
////                        writer.newLine();
////                    }
////                } catch (IOException e) {
////                    throw new StreamCorruptedException(e.getMessage());
////                }
//            }
//
//            @Override
//            public void epilogue() {
////                try {
////                    writer.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//            }
//        };
//
//        SimplePipe<Collection<Pair<List<String>, Long>>> alphabeticalSorter_sink = new SimplePipe<>(sink);
//
//        DataCompositionFilter<Pair<List<List<String>>, Long>, Collection<Pair<List<String>, Long>>> alphabeticalSorter = new DataCompositionFilter<Pair<List<List<String>>, Long>, Collection<Pair<List<String>, Long>>>(alphabeticalSorter_sink) {
//
//            @Override
//            protected boolean fillEntity(Pair<List<List<String>>, Long> nextVal, Collection<Pair<List<String>, Long>> entity) {
//                Collection<Pair<List<String>, Long>> temp = new PriorityQueue<>();
//
//                nextVal.getKey().forEach(l -> temp.add(new Pair<>(l, nextVal.getValue())));
//
//                return entity.addAll(temp);
//            }
//
//            @Override
//            protected Collection<Pair<List<String>, Long>> getNewEntityObject() {
//                return new PriorityQueue<>();
//            }
//        };
//
//        SimplePipe<Pair<List<List<String>>, Long>> frequentWordsRemover_alphabeticalSorter = new SimplePipe<Pair<List<List<String>>, Long>>(alphabeticalSorter);
//
//        DataTransformationFilter1<Pair<List<List<String>>, Long>> frequentWordsRemover = new DataTransformationFilter1<Pair<List<List<String>>, Long>>(frequentWordsRemover_alphabeticalSorter) {
//
//            @Override
//            protected void process(Pair<List<List<String>>, Long> entity) {
//
//            }
//        };
//
//
//        DataTransformationFilter3<Pair<LinkedList<String>, Long>, Pair<LinkedList<String>, Long>> shifter = new DataTransformationFilter3<Pair<LinkedList<String>, Long>, Pair<LinkedList<String>, Long>>() {
//
//            @Override
//            protected ArrayList<Pair<LinkedList<String>, Long>> process(Pair<LinkedList<String>, Long> entity) {
//                ArrayList<Pair<LinkedList<String>, Long>> array = new ArrayList<>();
//
//                for (int i = 0; i < entity.getKey().size(); i++) {
//
//                    Pair<LinkedList<String>, Long> copy = new Pair<>(entity.getKey(), entity.getValue());
//                    array.add(copy);
//
//                    String s = entity.getKey().removeFirst();
//                    entity.getKey().addLast(s);
//                }
//
//                return array;
//            }

        // Simple Sample


        /*List<Integer> simpleList = Arrays.asList(1, 2, 3, 4, null);

        Sink<Integer> simpleSink = new Sink<>() {
            @Override
            public void write(Integer value) throws StreamCorruptedException {
                if (value != null) System.out.println(value);
            }
        };

        SimplePipe<Integer> simplePipe = new SimplePipe<>(simpleSink);

        Source<Integer> simpleSource = new Source<>(simplePipe) {
            Iterator<Integer> iterable = simpleList.iterator();

            @Override
            public Integer read() throws StreamCorruptedException {
                return iterable.next();
            }
        };

        simpleSource.run();*/
    }
}

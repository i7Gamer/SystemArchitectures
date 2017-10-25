package at.fhv.itb.sem5.exercise1;

import javafx.util.Pair;
import pmp.filter.Sink;

import java.io.*;
import java.util.List;

public class FileSink extends Sink<Pair<List<String>, Long>> {

    private BufferedWriter writer;
    private boolean skipEmptyLines = true;

    @Override
    public void write(Pair<List<String>, Long> value) throws StreamCorruptedException {
        if (value == null) return;
        try {
            StringBuilder lineBuilder = new StringBuilder();
            for (String word : value.getKey()) {
                lineBuilder.append(word);
                lineBuilder.append(" ");
            }
            String line = lineBuilder.toString().trim();
            if(line.isEmpty() && skipEmptyLines) return;
            // line
            writer.write(line);
            // tab
            writer.write("\t");
            // line number
            writer.write(value.getValue().toString());
            writer.newLine();
        } catch (IOException e) {
            throw new StreamCorruptedException(e.getMessage());
        }
    }

    public void setFileToWrite(String fileToWrite) throws IOException {
        if (writer != null) writer.close();
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileToWrite))));
    }

    public void setSkipEmptyLines(boolean skipEmptyLines) {
        this.skipEmptyLines = skipEmptyLines;
    }
}

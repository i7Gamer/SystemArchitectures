package at.fhv.itb.sem5.exercise1.a;

import java.util.LinkedList;

/**
 * Created by timor on 29.10.2017.
 */
public class Line implements Comparable<Line>{

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    protected Long index = -1L;
    protected LinkedList<String> words = new LinkedList<>();

    public void append(String s) {
        if(s == null || s.isEmpty()) {
            return;
        }

        words.add(s);
    }

    public int getLength(){
        int count = 0;

        for(String s : words)
        {
            count++;
            count+=s.length();
        }

        return count;
    }

    public Line shift() {
        Line l = new Line();
        l.words = new LinkedList<>(words);
        l.index = index;

        String temp = l.words.removeFirst();
        l.words.addLast(temp);
        return l;
    }

    @Override
    public int compareTo(Line o) {


        String word1 = words.getFirst();
        String word2 = o.words.getFirst();

        return word1.compareToIgnoreCase(word2);
//        int wordComparison = word1.compareToIgnoreCase(word2);
//        return wordComparison == 0 ? index.compareTo(o.index) : wordComparison;
    }

    public String toString(){
        StringBuilder contentBuilder = new StringBuilder();
        for (String s : words) {
            contentBuilder.append(s);
            contentBuilder.append(" ");
        }
        String content = contentBuilder.toString().trim();

        return content + " " + index;
    }

    public boolean isEmpty(){
        return words.isEmpty();
    }

    public String asString(int lengthLimit, Alignment alignment){
        StringBuilder contentBuilder = new StringBuilder();
        for (String s : words) {
            contentBuilder.append(s);
            contentBuilder.append(" ");
        }
        String content = contentBuilder.toString().trim();

        int filler = lengthLimit - content.length();

        StringBuilder before = new StringBuilder();
        StringBuilder after = new StringBuilder();

        if(alignment == Alignment.LEFT) {
            for(int i = 0; i < filler; i++){
                after.append(" ");
            }
        }
        else if(alignment == Alignment.CENTER){
            for(int i = 0; i < filler/2; i++){
                before.append(" ");
                after.append(" ");
            }
        }
        else if(alignment == Alignment.RIGHT){
            for(int i = 0; i < filler; i++){
                before.append(" ");
            }
        }

        return before.toString() + content + after.toString() + " " + index;
    }
}

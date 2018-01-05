package agh.cs.constituition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static java.lang.System.out;


public class Constituition extends Document {

    private List<Chapter> chapters = new LinkedList<>();



    public List <Chapter> getChapters() {
        return chapters; }

    public void setChapters(Chapter c) {
        this.chapters.add(c);
    }



    public Chapter findChapter (String number) {
        for (Chapter c: this.chapters) if (c.getNumber().equals(number) ) return c;
        return null;
    }


}

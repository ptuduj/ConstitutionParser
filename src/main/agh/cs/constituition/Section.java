package agh.cs.constituition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static java.lang.System.out;

public class Section {          // dział
    private List<Chapter> chapters = new ArrayList<>();
    private String number;              // number of section
    private String title;

    public String getNumber() { return this.number; }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getTitle() { return this.title; }

    public void setTitle (String s) {
        this.title = s;
    }

    public void setChapters(Chapter c) {
        this.chapters.add(c);
    }


    public Chapter findChapter (String number) {
        out.println("DZIAŁ " + this.number);
        for (Chapter c: this.chapters) if (c.getNumber().equals(number) ) return c;
        return null;
    }

    public void tableOfContents() {
        out.println("DZIAŁ " + this.number);      // number and title of section
        out.println(this.title);
           for (Chapter c: this.chapters)  c.tableOfContents();
    }


}

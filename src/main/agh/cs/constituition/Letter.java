package agh.cs.constituition;

import java.util.LinkedList;
import java.util.List;
import static java.lang.System.out;

public class Letter {
    private String letter;
    private List<String> text = new LinkedList<>();



    public String getLetter() {
        return this.letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }


    public List<String> getText() {
        return this.text;
    }

    public void setText (String s) {
        this.text.add(s);
    }


    public void print () {
       for (String s: this.text)  out.println(s);
    }

}

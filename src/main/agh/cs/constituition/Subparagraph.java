package agh.cs.constituition;

import java.util.LinkedList;
import java.util.List;
import static java.lang.System.out;

public class Subparagraph {       // punkt
    private List<String> text = new LinkedList<>();
    private List<Letter> letters = new LinkedList <>();
    private String num;


    public List <Letter> getLetters() { return letters; }

    public void setLetters (Letter l) {
        this.letters.add(l);
    }

    public List <String> getText() { return text; }

    public void setText(String text) {
        this.text.add(text);
    }

    public String getNum() { return num; }

    public void setNum(String num) {
        this.num = num;
    }



    public void print() {
        for (String s: this.text)  out.println(s);
        for (Letter l: this.letters)  l.print();
    }

    public  Letter findLetter (String num) {
        for (Letter l: this.letters) {
            if (num.equals( l.getLetter()) ) return l;
        }
        return null;
    }
}

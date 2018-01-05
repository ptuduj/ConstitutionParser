package agh.cs.constituition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static java.lang.System.out;

public class Article {

    private List<String> text = new LinkedList<>();  // text before paragraphs
    private List<Paragraph> paragraphs = new ArrayList <>();
    private String num;


    public String getNum() { return num; }

    public void setNum(String number) { this.num = number; }

    public List <Paragraph> getParagraphs() { return paragraphs; }

    public void setParagraphs(Paragraph p) {
        this.paragraphs.add(p);
    }

    public List <String> getText() { return text; }

    public void setText(String text) {
        this.text.add(text);
    }

    public void print () {
        out.print("Art. " + this.num + ". ");
        for (String s: this.text)  out.println(s);
        for (Paragraph p: this.paragraphs) p.print();
    }

    public  Paragraph findParagraph (String num) {
        for (Paragraph paragraph: this.paragraphs) {
            if (num.equals(paragraph.getNum())) return paragraph;
        }
        return null;
    }

}

package agh.cs.constituition;

import java.util.LinkedList;
import java.util.List;
import static java.lang.System.out;

public class Paragraph {
    private String num;
    private List<String> text = new LinkedList<>();
    private List<Subparagraph> subp = new LinkedList <>();




    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


    public List <String> getText() {
        return text;
    }

    public void setText( String s) {
        this.text.add(s);
    }

    public List <Subparagraph> getSubp() {
        return subp;
    }

    public void setSubp (Subparagraph subparagraph) {
        this.subp.add(subparagraph);
    }



    public void print () {
        for (String s: this.text) out.println(s);
        for (Subparagraph sp: this.subp) sp.print();
    }

    public  Subparagraph findSubParagraph (String num) {
        for (Subparagraph sp: this.subp) {
            if (num.equals(sp.getNum())) return sp;
        }
        return null;
    }
}

package agh.cs.constituition;

import java.util.ArrayList;
import java.util.LinkedList;
import static java.lang.System.out;
import java.util.List;


public class Chapter {
    private List<String> titles = new ArrayList<>();  // list of titles for each chapter
    private List<Article> articles = new LinkedList<>();
    private String number;



    public String getNumber() {
        return number; }

    public void setNumber (String number) {
        this.number = number; }


    public List <Article> getArticles() {
        return articles; }

    public void setArticles(Article a) {
        this.articles.add(a); }


    public List <String> getTitles() {
        return titles; }

    public void setTitles (String title) {
        this.titles.add(title); }



    public boolean ifChapter (String num) {
        if ( this.number.equals(num) ) return true;
        return false;
    }

   public void print() {
        out.println("Rozdział " + this.number);
        for (String title: titles) out.println(title);
        for (Article a: articles) a.print(); }

    public void tableOfContents () {
        if (! this.number.equals("0")) {
        out.println("Rozdział " + this.number);
        for (String title: this.titles) out.println(title);  }
    }
}

package agh.cs.constituition;

import java.util.LinkedList;
import java.util.List;

public class Document {

    protected List<String> introduction = new LinkedList<>();
    protected List<Article> art = new LinkedList <>();


    public  Article findArticle (String num) {
        for (Article art: this.art) {
            if (num.equals(art.getNum())) return art;
        }
        return null;
    }

}

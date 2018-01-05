package agh.cs.constituition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import static java.lang.System.out;

public class Show {

   private static Document d;


        public static void parseAruments ( String[] args ) {

            int length = args.length - 1;

            for (int i = 0; i < args.length; i++) {
                if (i <= length && args[i].equals("-p")) {

                    Parser p = new Parser();
                     d = p.parse(args[++i]);
                     if (d == null) {
                         out.println("Incorrect path!");
                         break;
                     }
                }

                else if (i <= length && args[i].equals("-a")) {
                    Article art = d.findArticle(args[++i]);

                    if ((++i) < length && ( args[i].equals("-pr") ||( (args[i].equals("-sp")) && (d instanceof Act)))) {
                        String parNum = args[i];
                        Paragraph par = art.findParagraph(args[++i]);
                        Subparagraph sp = null;

                        if (parNum .equals("-sp")) {
                            par= art.findParagraph("0");
                            if(par!=null)   sp = par.findSubParagraph(args[i]); }


                        if (++i <= length && (args[i].equals("-sp") || sp!= null))  {
                            if (sp == null) {sp = par.findSubParagraph(args[++i]); i++; }

                            if ( i < length && args[i].equals("-l")) {
                                Letter l = sp.findLetter(args[++i]);
                               if (l!= null) l.print();
                               else out.println("Letter doesn't exist!");
                            } else {
                                if (sp != null ) sp.print();
                                else out.println("Subparagraph doesn't exist"); }

                        } else {

                            if (sp != null) sp.print();
                            else if (parNum.equals("-sp")) out.println("Subparagraph doesn't exist!");
                            else if (par != null ) par.print();
                            else if (par == null) out.println("Paragraph doesn't exist!");
                        }

                    }
                    else {     // print couple of articles
                        if (i<=length && args[i].equals("-sp") && d instanceof Document) {out.println("Subparagraph doesn't exist!"); break; }

                        if (args[--i].contains("-")) {

                            int iend = args[i].indexOf("-");
                            String beginning = args[i].substring(0, iend);
                            String end="";

                            if ((args[i].length()-iend-1) != 0) end=args[i].substring( iend+1 );
                            if (end.equals("")) {out.println("Incorrect argument!"); break;}
                            if (!end.equals("") && Integer.valueOf(beginning) > Integer.valueOf(end)) {out.println("Incorrect argument!"); break;}

                            List<Article> artciles = d.art;
                            boolean ifStart= false;
                           for (Article a: artciles) {
                               if (a.getNum().equals(beginning))  ifStart = true;
                               if ( ifStart &&  !(a.getNum().equals(end)) ) a.print();
                               if (a.getNum().equals(end)) {a.print(); break;}
                           }
                        }
                        else  {                       // print only 1 article
                             if (i==length && art != null)  art.print();
                             else if (i<length)  { out.println("Incorrect argument!"); break; }
                             else out.println("Article doesn't exist!");
                        }
                    }
                }

                else if (i == length && args[i].equals("-t") ) {    // display all table of contents

                    for (int j = 0; j < 3; j++) {                    // display only 3 lines of introduction
                        out.println(d.introduction.get(j)); }
                    if (d instanceof Act) {
                        for (Section s : ((Act) d).getSections()) { s.tableOfContents();
                        }
                    }
                    else if (d instanceof Constituition) {
                        for (Chapter c : ((Constituition) d).getChapters()) c.tableOfContents();
                    }
                }

                else if (i < length && args[i].equals("-s") && (d instanceof Act) ) {
                    Section sec = ((Act) d).findSection( (args[++i]) );

                    // check next argumennt, if "-c" display chapter
                    if ((++i) <= length && args[i].equals("-c")) {  // display chapter

                        Chapter c = null;
                        if (sec != null) c = sec.findChapter( args[++i] );
                        if (c!= null)  c.print();
                        else out.println("Chapter doesn't exist!");

                    }
                    // display section
                    else {
                        if (sec != null) sec.print();
                        else out.println("Section doesn't exist!"); }
                }

                else if (i+1 == length && args[i].equals("-c")) {   // print chapter
                    if (d instanceof Constituition) {
                        Chapter c = ((Constituition) d).findChapter(args[++i]);
                        if (c != null) { c.print(); }
                        else  out.println("Chapter doesn't exist!");
                    }
                }

                else if (i+1 == length && args[i].equals("-ts") && d instanceof Act) {   // table of contents for section

                        Section s = ((Act) d).findSection(args[++i]);
                        if (s != null) { s.tableOfContents(); }
                        else  out.println("Section doesn't exist!");
                }


                else {
                    out.println("Incorrect argummet!");
                    break; }
            }
        }

}









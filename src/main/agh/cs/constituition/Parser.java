package agh.cs.constituition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Parser {

    private String prevLine = " ";
    private Integer ChapterNumber = 0;

    private Document doc;     // current variables
    private Chapter chap;
    private Section sec;
    private Article art;
    private Paragraph par;
    private Subparagraph subparagraph;
    private Letter letter;

    private boolean isParagraph = false;
    private boolean isSubParagraph = false;
    private boolean isLetter = false;
    private boolean isChapterName = false;



    public Document parse (String path) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line=br.readLine();
             if (line.startsWith("©Kancelaria Sejmu s. 1/73")) {
                 this.doc = new Act();
             }
             else if (line.startsWith("©Kancelaria Sejmu")) {
                 this.doc= new Constituition();
             }
            else return null;


            for ( ; line != null; ) {

                // concatenate2lines
                String nextLine = br.readLine();
                if ( line.substring(line.length()-1).equals("-")) {
                    int index = nextLine.indexOf(" ");
                    if (index == -1) index = nextLine.length()-1;

                    String temp = nextLine.substring(0, index);
                    line = line.substring(0, line.length()-1) + temp;

                    if (index+1 == nextLine.length()) nextLine= nextLine + " ";
                    nextLine = nextLine.substring(index+1 );
                }

                check(line);
                line = nextLine;

            }

        } catch (IOException e) {
            System.out.println("Problem occured while reading file " + e.getMessage());
        }

        return doc;
    }




    public void check (String line) {

        if (line.startsWith("\u00a9") || prevLine.startsWith("\u00a9") || line.length() == 1) {} // ommit

        else if (line.startsWith("DZIAŁ")) {
            sec = new Section();
            sec.setNumber( line.substring(6) );


            ((Act) doc).setSections(sec);
            ChapterNumber = 0;  }

        else if (line.startsWith("Rozdział")) {
            chap = new Chapter();
            isChapterName = true;

            if (doc instanceof Constituition) {
                ((Constituition) doc).setChapters(chap);          // number for chapter
                  chap.setNumber( (++ChapterNumber).toString() );
            }
            else if (doc instanceof Act) {
                String line1 = line.substring(9);
                chap.setNumber(line1);
                sec.setChapters(chap);
            }
        }

        else if (ChapterNumber == 0 && sec == null ) {           // intruduction for both
            doc.introduction.add(line);
        }

        else if (prevLine.startsWith("DZIAŁ")) {
            sec.setTitle(line);                              // section's title
        }

        else if (prevLine.startsWith("Rozdział")) {       // new title
            chap.setTitles(line);
        }

        else if (line.startsWith("Art")) {       // new article
            art = new Article();

            isChapterName = false;
            isSubParagraph = false;
            isParagraph = false;
            isLetter = false;
            par = null;

            String line1 = line.substring(5);
            int iend = line1.indexOf(".");
            art.setNum( line.substring(5, iend+5) );


          if (chap == null) {                  // happens only for (Act).d
              chap = new Chapter();               // Article without chapter
              chap.setNumber("0");
              sec.setChapters(chap);
          }

            doc.art.add(art);
            chap.setArticles(art);

            if (line.length() >= 10) {         // edit rest of line, when it doesn't end in "Art. (num)"
                line = line.substring( line.indexOf(".", 5) +2);
                process_Article(line);
            }
        }

        else if (isChapterName) {
            chap.setTitles(line); }

        else if (line.toUpperCase().equals(line)) {}

        else  { process_Article(line); }

        prevLine = line;
    }


    public void process_Article (String line) {

        if (line.length() >= 2 && (line.charAt(1) == '.' || line.charAt(2) == '.')) {  // new paragraph
            par = new Paragraph();

            int iend = line.indexOf(".");             // paragraph's number
            par.setNum(line.substring(0, iend));

            art.setParagraphs(par);
            par.setText(line);

            isParagraph = true;           // still process paragraph
            isSubParagraph = false;
            isLetter = false;

        } else if (Character.isDigit(line.charAt(0)) && (line.charAt(1) == ')' || line.charAt(2) == ')')) {

            subparagraph = new Subparagraph();                 // new subparagraph

            int iend = line.indexOf(")");
            subparagraph.setNum(line.substring(0, iend));

            subparagraph.setText(line);

            if (par == null) {
                par = new Paragraph();
                par.setNum("0");                       // Subparagraph without Paragraph
                art.setParagraphs(par);
            }

            par.setSubp(subparagraph);

            isSubParagraph = true;
            isParagraph = false;
            isLetter = false;
        }

        else if (Character.isLetter(line.charAt(0)) && line.charAt(1) == ')') {     // new Letter
            isLetter = true;
            isParagraph = false;
            isSubParagraph = false;

            letter = new Letter();
            letter.setLetter(line.substring(0, 1));
            letter.setText(line);
            subparagraph.setLetters(letter);
        }

        else if (isParagraph) {              // still process Paragraph
            par.setText(line);
        }

        else if (isSubParagraph) {           // still process Subparagraph
            subparagraph.setText(line);
        }

        else if (isLetter) {                 // still process Letter
            letter.setText(line);
        }
        else {                            // process Article, not Subpargraph or Letter
            art.setText(line);
        }
    }





}


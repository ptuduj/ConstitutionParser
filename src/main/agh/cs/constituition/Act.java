package agh.cs.constituition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static java.lang.System.out;


public class Act  extends Document {          // uokik.txt

  private List<Section> sections = new ArrayList<>();


  public List <Section> getSections() {
    return sections; }

  public void setSections (Section sec) {
    this.sections.add(sec); }



  public Section findSection (String number) {
    for (Section s: this.sections) {
      if (s.getNumber().equals(number)) return s;
    }
    return null;
  }

}

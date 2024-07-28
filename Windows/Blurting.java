import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class Blurting extends Window {
  private JTextPane passage = new JTextPane();
  private String passageText = "";
  private ArrayList<String> keywords = new ArrayList<String>();
  private String fillerWords = "a an the is are was be were to as of so this that it who what where which when how i we he she you they them him her your me my her his their our would could should and";
  private String enteredText = "";
  private int score;
  private JTextPane scorePane = new JTextPane();
  private JButton blurtButton = new JButton();
  private JButton checkButton = new JButton();

  Blurting() {

    super("Blurting");

    title.setVisible(false);
    
    intro.setText("Blurting is a popular active recall technique, where you read a chunk of information, hide it, and write as much as you remember. In the box below, type out the passage you're studying. When you're ready to blurt, click the button. When you're finished, click 'Check'. We'll compare the original passage and the information you've entered to see how much you remembered.");
    intro.setBounds(50, 20, 400, 115);

    JScrollPane passageScroll = new JScrollPane(passage);
        passageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    passageScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    passageScroll.setBounds(50, 145, 400, 210);

    blurtButton.setText("Blurt");
    blurtButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) { 
        if (!passage.getText().equals("")) {
          passageText = passage.getText();
        }
        passage.setText("");
        scorePane.setVisible(false);
        keywords.clear();
      } 
    } );
    blurtButton.setBounds(50, 365, 80, 20);
    blurtButton.setBackground(Color.white);
    blurtButton.setForeground(new Color(7, 83, 196));
    blurtButton.setFont(new Font(Font.SERIF, Font.BOLD, 12));

    checkButton.setText("Check");
    checkButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        compareText();
      }
    });
    checkButton.setBounds(150, 365, 80, 20);
    checkButton.setBackground(Color.white);
    checkButton.setForeground(new Color(7, 83, 196));
    checkButton.setFont(new Font(Font.SERIF, Font.BOLD, 12));

    scorePane.setBounds(260, 365, 100, 20);
    scorePane.setEditable(false);
    scorePane.setVisible(false);

    panel.add(passageScroll);
    panel.add(blurtButton);
    panel.add(checkButton);
    panel.add(scorePane);
    
  }

  public void compareText() {
    score = 0;
    enteredText = passage.getText();    

    String npPassageText = passageText.replaceAll("\\p{Punct}", "");
    String[] passageArray = npPassageText.split(" ");
    for(int i = 0; i < passageArray.length; i++) {
      if (!fillerWords.contains(passageArray[i])) {
        keywords.add(passageArray[i].toLowerCase());
      }
    }

    int totalScore = keywords.size();
    for(int i = 0; i < keywords.size(); i++) {
      enteredText = enteredText.toLowerCase().replaceAll("\\p{Punct}", "");
      if(enteredText.contains(keywords.get(i))) {
        System.out.println(keywords.get(i));
        score++;
      }
    }

    passage.setText(passageText);
    scorePane.setText(score + "/" + totalScore + " words");
    scorePane.setVisible(true);
    System.out.println(enteredText);
    
  }
  
}

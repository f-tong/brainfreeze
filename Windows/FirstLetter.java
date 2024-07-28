import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

public class FirstLetter extends Window {
  private JTextPane passage = new JTextPane();
  private JButton hideButton = new JButton();
  private JButton showButton = new JButton();
  private String passageText;
  private String previousText;
  private int hideClicks = 0;
  
  FirstLetter() {
    super("First Letter Technique");

    title.setVisible(false);

    intro.setText("The first letter technique is good for memorizing short passages and speeches. Type your passage in below, and read it over a couple times. When you're ready, click 'Hide'. Recite the passage from only the first letters. Click again, and recite without any prompts. If you need a refresher, click 'Show'.");
    intro.setBounds(50, 20, 400, 100);

    JScrollPane passageScroll = new JScrollPane(passage);
    passageScroll.setBounds(50, 140, 400, 150);
        passageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    passageScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    hideButton.setText("Hide");
    hideButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) { 
        hideText();
      } 
    } );
    hideButton.setBounds(50, 310, 80, 20);
    hideButton.setBackground(Color.white);
    hideButton.setForeground(new Color(7, 83, 196));
    hideButton.setFont(new Font(Font.SERIF, Font.BOLD, 12));

    showButton.setText("Show");
    showButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showText();
      }
    });
    showButton.setBounds(150, 310, 80, 20);
    showButton.setBackground(Color.white);
    showButton.setForeground(new Color(7, 83, 196));
    showButton.setFont(new Font(Font.SERIF, Font.BOLD, 12));
    
    panel.add(passageScroll);
    panel.add(hideButton);
    panel.add(showButton);
  }

  public void hideText() {
    passageText = passage.getText();
    if (!passageText.equals("")) {
      if (hideClicks == 0) {
        String[] passageArray = passageText.split(" ");
        char[] lettersArray = new char[passageArray.length];
        String firstLetters = "";
        for (int i = 0; i < passageArray.length; i++) {
          lettersArray[i] = passageArray[i].charAt(0);
          firstLetters = firstLetters + lettersArray[i] + " ";
        }
        passage.setText(firstLetters);
        passage.setEditable(false);
        previousText = passageText;
        hideClicks++;
      } else {
        passage.setText("");
        hideClicks = 0;
      }
    }
  }

  public void showText() {
    passage.setText(previousText);
    passage.setEditable(true);
    hideClicks = 0;
  }
  
}
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Set;
import java.lang.Object;
import java.util.ArrayList;

public class Flashcards extends Window {
  private Window createWindow = new Window("Edit Sets");
  private JTextPane nameField = new JTextPane();
  private JTextPane termField = new JTextPane();
  private JTextPane defField = new JTextPane();
  private JButton addButton = new JButton();
  private JButton delButton = new JButton();
  private HashMap<String, HashMap> sets = new HashMap<String, HashMap>();
  private JComboBox setOptions = new JComboBox();
  private JTextPane flashcard = new JTextPane();
  private JButton nextButton = new JButton();
  private JButton prevButton = new JButton();
  private JButton showButton = new JButton();
  private int cardIndex = 0;
  private HashMap<String, String> currentSet;
  private JButton chooseButton = new JButton();
  private ArrayList<Object> keysArray = new ArrayList<Object>();
  
  Flashcards() {
    super("Flashcards");

    title.setVisible(false);

    intro.setText("Flashcards are great for active recall. After creating sets, choose one to study and click 'Choose'. Click the 'Show' button to flip between the term and definition.");
    intro.setBounds(50, 20, 400, 50);

    createWindow.intro.setText("To create or edit a set, type its name into the 'Name' field. Type in terms and definitions into their fields, then click 'Add Term'. If there is already that exact term in the chosen set, it will replace the definiton with the new one. To delete a term, type the set and term into their fields and click 'Delete'. To delete an entire set, type the name of the set only, and click 'Delete'.");
    createWindow.intro.setBounds(50, 20, 400, 110);
    createWindow.title.setVisible(false);

    JTextPane nameLabel = new JTextPane();
    nameLabel.setText("Name:");
    nameLabel.setBounds(30, 150, 60, 20);
    nameLabel.setEditable(false);
    JScrollPane nameScroll = new JScrollPane(nameField);
    nameScroll.setBounds(30, 180, 170, 30);
    nameLabel.putClientProperty("Nimbus.Overrides", defaults);
    nameLabel.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
    nameLabel.setBackground(new Color(217, 235, 247));
    nameLabel.setForeground(Color.black);
        nameScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    nameScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    JTextPane termLabel = new JTextPane();
    termLabel.setText("Term:");
    termLabel.setBounds(30, 230, 60, 20);
    termLabel.setEditable(false);
    JScrollPane termScroll = new JScrollPane(termField);
    termScroll.setBounds(30, 260, 150, 40);
    termScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    termScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    termLabel.putClientProperty("Nimbus.Overrides", defaults);
    termLabel.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
    termLabel.setBackground(new Color(217, 235, 247));
    termLabel.setForeground(Color.black);

    JTextPane defLabel = new JTextPane();
    defLabel.setText("Definition:");
    defLabel.setBounds(220, 230, 100, 20);
    defLabel.setEditable(false);
    JScrollPane defScroll = new JScrollPane(defField);
    defScroll.setBounds(220, 260, 250, 60);
        defScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    defScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    defLabel.putClientProperty("Nimbus.Overrides", defaults);
    defLabel.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
    defLabel.setBackground(new Color(217, 235, 247));
    defLabel.setForeground(Color.black);

    addButton.setText("Add term");
    addButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) {
        addTerm();
      } 
    } );
    addButton.setBounds(30, 350, 110, 20);
    addButton.setBackground(Color.white);
    addButton.setForeground(new Color(7, 83, 196));
    addButton.setFont(new Font(Font.SERIF, Font.BOLD, 12));

    delButton.setText("Delete");
    delButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        delete();
      }
    });
    delButton.setBounds(160, 350, 110, 20);
    delButton.setBackground(new Color(170, 3, 3));
    delButton.setForeground(Color.white);
    delButton.setFont(new Font(Font.SERIF, Font.BOLD, 12));

    setOptions.setEditable(false);
    setOptions.setBounds(300, 110, 150, 30);

    flashcard.setEditable(false);
    JScrollPane flashScroll = new JScrollPane(flashcard);
    flashScroll.setBounds(100, 210, 300, 80);
        flashScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    flashScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    nextButton.setText("Next");
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (cardIndex == currentSet.size() - 1) {
          cardIndex = -1;
        }
        cardIndex++;
        flashcard.setText(keysArray.get(cardIndex).toString());
      }
    });
    nextButton.setBounds(220, 300, 80, 20);
    nextButton.setBackground(Color.white);
    nextButton.setForeground(new Color(7, 83, 196));
    nextButton.setFont(new Font(Font.SERIF, Font.BOLD, 12));
    nextButton.setVisible(false);

    prevButton.setText("Previous");
    prevButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (cardIndex == 0) {
          cardIndex = currentSet.size();
        }
        cardIndex--;
        flashcard.setText(keysArray.get(cardIndex).toString());
      }
    });
    prevButton.setBounds(100, 300, 110, 20);
    prevButton.setBackground(Color.white);
    prevButton.setForeground(new Color(7, 83, 196));
    prevButton.setFont(new Font(Font.SERIF, Font.BOLD, 12));
    prevButton.setVisible(false);

    showButton.setText("Show");
    showButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String currentTerm = flashcard.getText();
        if (currentSet.containsKey(currentTerm)) {
          flashcard.setText(currentSet.get(currentTerm));
        }
        else {
          flashcard.setText(keysArray.get(cardIndex).toString());
        }
        
      }
    });
    showButton.setBounds(320, 300, 80, 20);
    showButton.setBackground(Color.white);
    showButton.setForeground(new Color(15, 118, 88));
    showButton.setFont(new Font(Font.SERIF, Font.BOLD, 12));
    showButton.setVisible(false);

    chooseButton.setText("Choose");
    chooseButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        useFlashcards();
      }
    });
    chooseButton.setBounds(300, 155, 100, 20);
    chooseButton.setBackground(Color.white);
    chooseButton.setForeground(new Color(7, 83, 196));
    chooseButton.setFont(new Font(Font.SERIF, Font.BOLD, 12));
    chooseButton.setVisible(false);
    
    createWindow.panel.add(nameLabel);
    createWindow.panel.add(nameScroll);
    createWindow.panel.add(termLabel);
    createWindow.panel.add(termScroll);
    createWindow.panel.add(defLabel);
    createWindow.panel.add(defScroll);
    createWindow.panel.add(addButton);
    createWindow.panel.add(delButton);

    panel.add(setOptions);
    panel.add(chooseButton);
    panel.add(flashScroll);
    panel.add(nextButton);
    panel.add(prevButton);
    panel.add(showButton);
    
  }

  public Window getCreateWindow() {
    return createWindow;
  }


  public void addTerm() {
    String name = nameField.getText();
    String term = termField.getText();
    
    if(!(name.equals("") || term.equals(""))) {
      if (!(sets.containsKey(name))) {
        HashMap<String, String> newSet = new HashMap <String, String>();
        sets.put(name, newSet);
        setOptions.addItem(name);
      }

      HashMap currentSet = sets.get(name);
      currentSet.put(term, defField.getText());

      termField.setText("");
      defField.setText("");

      nextButton.setVisible(false);
      prevButton.setVisible(false);
      showButton.setVisible(false);

      chooseButton.setVisible(true);
    }
    
  }

  public void useFlashcards() {
    String setName = setOptions.getSelectedItem().toString();
    //System.out.println(setName);
    currentSet = sets.get(setName);

    /*for (Object name : currentSet.keySet()) {
      String key = name.toString();
      String value = currentSet.get(key).toString();
      System.out.println(key + ": " + value);
    }*/

    if (!(currentSet.size() == 0)) {
      keysArray.clear();
      keysArray.addAll(currentSet.keySet());
      flashcard.setText(keysArray.get(0).toString());
      cardIndex = 0;

      nextButton.setVisible(true);
      prevButton.setVisible(true);
      showButton.setVisible(true);
    }
        
  }

  public void delete() {
    String setName = nameField.getText();
    if (sets.containsKey(setName)) {
      if (termField.getText().equals("")) {
        sets.remove(setName);
        setOptions.removeItem(setName);
        nameField.setText("");
        if (sets.size() == 0) {
          chooseButton.setVisible(false);
        }
      }
      else {
        HashMap<String, String> currentSet = sets.get(setName);
        currentSet.remove(termField.getText());
        termField.setText("");
      }
      flashcard.setText("");
    }

    nextButton.setVisible(false);
    prevButton.setVisible(false);
    showButton.setVisible(false);
    
  }
  
}
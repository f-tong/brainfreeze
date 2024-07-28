import javax.swing.*;
import javax.swing.text.*;
import java.awt.Color;
import java.awt.Font;

public class Window {
  protected JFrame window = new JFrame();
  protected JPanel panel = new JPanel();
  protected JTextPane intro = new JTextPane();
  protected JTextPane title = new JTextPane();
  protected UIDefaults defaults = new UIDefaults();

  Window(String windowName) {
    window.setSize(500, 500);
    window.setTitle(windowName);
    window.setVisible(false);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    panel.setBounds(0, 0, 500, 500);
    panel.setLayout(null);
    panel.setVisible(true);
    panel.setBackground(new Color(217, 235, 247));

    StyledDocument introStyle = intro.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
    introStyle.setParagraphAttributes(0, introStyle.getLength(), center, false);
    intro.setVisible(true);
    intro.setEditable(false);

    title.setText("Brain Freeze");
    StyledDocument titleStyle = title.getStyledDocument();
    title.putClientProperty("Nimbus.Overrides", defaults);
    title.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
    title.setBackground(new Color(217, 235, 247));
    title.setForeground(new Color(8, 34, 89));
    titleStyle.setParagraphAttributes(0, introStyle.getLength(), center, false);
    Font titleFont = new Font(Font.SERIF, Font.BOLD, 24);
    title.setFont(titleFont);
    title.setBounds(100, 20, 300, 30);

    panel.add(intro);
    panel.add(title);
    window.add(panel);
    
  }

  public JFrame getWindow() {
    return window;
  }

  public JPanel getPanel() {
    return panel;
  }
  
  public void show() {
    window.setVisible(true);
  }
  
}
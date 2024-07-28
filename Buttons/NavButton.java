import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;

public class NavButton {
  protected JButton button = new JButton();
  protected Font buttonFont = new Font(Font.SERIF, Font.BOLD, 12);

  NavButton(Window lastPage, Window nextPage, String text){
    button.setText(text);

    button.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) { 
        lastPage.getWindow().setVisible(false);
        nextPage.getWindow().setVisible(true);
  } 
} );

    button.setFont(buttonFont);
    button.setBackground(Color.white);
    button.setForeground(new Color(8, 34, 89));

    lastPage.getPanel().add(button);

  }

  public void positionButton(int x, int y, int w, int h){
    button.setBounds(x, y, w, h);
  }

  public JButton getButton(){
    return button;
  }
}
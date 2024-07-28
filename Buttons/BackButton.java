import javax.swing.*;

public class BackButton extends NavButton{

  BackButton(Window lastPage, Window nextPage) {

    super(lastPage, nextPage, "Back");
    
    button.setBounds(390, 410, 70, 30);
    
  }
  
}

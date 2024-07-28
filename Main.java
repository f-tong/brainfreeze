
class Main {
  public static void main(String[] args) {
    Window homeWindow = new Window("Home");
    homeWindow.show();
    Window flWindow = new FirstLetter();
    Window blurtWindow = new Blurting();
    Window fcWindow = new Flashcards();
    Window createWindow = ((Flashcards) fcWindow).getCreateWindow();

    NavButton flButton = new NavButton(homeWindow, flWindow, "First Letter Technique");
    flButton.positionButton(150, 90, 200, 30);
    NavButton blurtButton = new NavButton(homeWindow, blurtWindow, "Blurting");
    blurtButton.positionButton(150, 155, 200, 30);
    NavButton fcButton = new NavButton(homeWindow, fcWindow, "Flashcards");
    fcButton.positionButton(150, 220, 200, 30);
    NavButton createButton = new NavButton(fcWindow, createWindow, "Edit sets");
    createButton.positionButton(60, 110, 140, 20);

    BackButton flBack = new BackButton(flWindow, homeWindow);
    BackButton blurtBack = new BackButton(blurtWindow, homeWindow);
    BackButton fcBack = new BackButton(fcWindow, homeWindow);
    BackButton createBack = new BackButton(createWindow, fcWindow);
    
  }
}
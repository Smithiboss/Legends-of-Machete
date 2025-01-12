package main;

public class Game {
    // ...
    public Game() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.setup_game();
        gamePanel.startGameThread();
        GameWindow gameWindow = new GameWindow(gamePanel);
    }
}

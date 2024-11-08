package main;

public class Game {

    public Game() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.startGameThread();
        GameWindow gameWindow = new GameWindow(gamePanel);
    }
}

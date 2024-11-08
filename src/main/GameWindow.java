package main;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel) {

        // terminates the program if window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Anus");
        // adds the game panel to the window
        add(gamePanel);
        // fits the window to the size of the game panel
        pack();
        // positions the window in the center of the screen
        setLocationRelativeTo(null);
        // makes it visible
        setVisible(true);

    }



}

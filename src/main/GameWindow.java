package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.windowFocusLost();
            }
        });

    }



}

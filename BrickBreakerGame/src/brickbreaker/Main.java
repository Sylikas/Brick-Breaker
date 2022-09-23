package brickbreaker;

import javax.swing.*;

public class Main {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public static void main(String[] args){

        JFrame theFrame = new JFrame("Brick Breaker");
        GamePanel thePanel = new GamePanel();

        theFrame.setResizable(false);
        theFrame.setSize(WIDTH, HEIGHT);
        theFrame.add(thePanel);
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        theFrame.setLocationRelativeTo(null);
        theFrame.setVisible(true);

        thePanel.playGame();
    }


}
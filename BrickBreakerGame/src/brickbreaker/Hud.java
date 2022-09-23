package brickbreaker;

import java.awt.*;

public class Hud {

    private int score;


    public Hud(){

        init();

    }

    public void init(){

        score = 0;

    }


    public void draw(Graphics2D g){

        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 18));
        g.drawString("Score: " + score, 20, 20);

    }


    public void addScore(int scoreToAdd){

        score += scoreToAdd;

    }

}
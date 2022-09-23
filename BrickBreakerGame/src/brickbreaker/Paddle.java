package brickbreaker;

import java.awt.*;

public class Paddle {

    private double x;
    private final int PADDLE_WIDTH;
    private final int PADDLE_HEIGHT;

    public static final int YPOS = Main.HEIGHT - 100;

    public Paddle(){

        PADDLE_WIDTH = 100;
        PADDLE_HEIGHT = 20;

        x = Main.WIDTH / 2 - PADDLE_WIDTH / 2;

    }

    public void draw(Graphics2D g){

        g.setColor(Color.DARK_GRAY);
        g.fillRect((int)x, YPOS, PADDLE_WIDTH, PADDLE_HEIGHT);

    }

    public void mouseMoved(int mouseXPos){

        x = mouseXPos;

        if(x > Main.WIDTH - PADDLE_WIDTH){
            x = Main.WIDTH - PADDLE_WIDTH;
        }
    }

    public Rectangle getPaddleRect(){

        return new Rectangle((int) x, YPOS, PADDLE_WIDTH, PADDLE_HEIGHT);

    }

    public int getPADDLE_WIDTH(){return PADDLE_WIDTH;}

}
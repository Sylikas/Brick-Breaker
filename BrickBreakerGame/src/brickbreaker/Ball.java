package brickbreaker;

import java.awt.*;

public class Ball {

    private double x;
    private double y;
    private double dx;
    private double dy;
    private static final int BALL_SIZE = 30;

    public Ball(){

        x = 200;
        y = 200;
        dx = 1;
        dy = 4;

    }

    public void update(){

        setPosition();

    }

    public void setPosition(){

        x += dx;
        y += dy;

        if(x < 0) {

            dx = -dx;

        }
        if(y < 0){

            dy = -dy;

        }
        if(x > Main.WIDTH - 1.5 * BALL_SIZE){

            dx = -dx;

        }
        if(y > Main.HEIGHT - BALL_SIZE){

            dy = -dy;

        }
    }

    public void draw(Graphics2D g){

        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(2));
        g.drawOval((int)x, (int)y, BALL_SIZE, BALL_SIZE);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval((int)x, (int)y, BALL_SIZE, BALL_SIZE);

    }

    public Rectangle getBallRect(){

        return new Rectangle((int) x, (int) y, BALL_SIZE, BALL_SIZE);

    }

    public double getX() {return x;}

    public void setDY(double theDY){ dy = theDY;}

    public double getDY(){ return dy; }

    public void setDX(double theDX) { dx = theDX; }

    public double getDX() {return dx;}


    public boolean youLose(){

        return y > Main.HEIGHT - BALL_SIZE * 2;

    }

}
package brickbreaker;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    //Fields
    private boolean running;
    private BufferedImage image;
    private Graphics2D g;
    private MyMouseMotionListener theMouseListener;
    private int mouseX;

    //Entities
    Ball ball;
    Paddle paddle;
    Map map;
    Hud hud;

    //Constructor
    public GamePanel() {

        init();

    }

    public void init() {

        mouseX = 0;

        ball = new Ball();
        paddle = new Paddle();
        theMouseListener = new MyMouseMotionListener();
        map = new Map(4, 5);
        hud = new Hud();


        addMouseMotionListener(theMouseListener);

        running = true;

        image = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);

        g = (Graphics2D) image.getGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }

    public void playGame() {

        while (running) {

            update();

            draw();

            repaint();


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public void checkCollisions() {

        Rectangle ballRect = ball.getBallRect();
        Rectangle paddleRect = paddle.getPaddleRect();

        if (ballRect.intersects(paddleRect)) {

            ball.setDY(-ball.getDY());

            if (ball.getX() < mouseX + paddle.getPADDLE_WIDTH() / 4) {
                ball.setDX(ball.getDX() - .5);
            }
            if (ball.getX() < mouseX + paddle.getPADDLE_WIDTH() && ball.getX() > mouseX + paddle.getPADDLE_WIDTH() / 4) {
                ball.setDX(ball.getDX() + .5);
            }
        }

        for (int row = 0; row < map.getMapArray().length; row++) {
            for (int col = 0; col < map.getMapArray()[0].length; col++) {

                if (map.getMapArray()[row][col] > 0) {

                    int brickX = col * map.getBrickWidth() + Map.HOR_PAD;
                    int brickY = row * map.getBrickHeight() + Map.VERT_PAD;
                    int brickWidth = map.getBrickWidth();
                    int brickHeight = map.getBrickHeight();

                    Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);

                    if (ballRect.intersects(brickRect)) {

                        map.hitBrick(row, col);

                        ball.setDY(-ball.getDY());

                        hud.addScore(50);

                        break;

                    }
                }
            }
        }
    }

    public void update() {

        checkCollisions();

        ball.update();

    }

    public void draw() {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        ball.draw(g);

        hud.draw(g);

        map.draw(g);

        paddle.draw(g);

        if(map.isThereAWinner()){

            drawWinner();
            running = false;

        }

        if(ball.youLose()){

            drawLoser();
            running = false;

        }

    }

    public void drawWinner(){

        g.setColor(Color.GREEN);
        g.setFont(new Font("Times New Roman", Font.BOLD, 50));
        g.drawString(("You win!"), 225, 225);

    }

    public void drawLoser(){

        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 50));
        g.drawString(("You lose!"), 225, 225);

    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(image, 0, 0, Main.WIDTH, Main.HEIGHT, null);
        g2.dispose();

    }

    private class MyMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            //Method not needed
        }

        @Override
        public void mouseMoved(MouseEvent e) {

            mouseX = e.getX();

            paddle.mouseMoved(e.getX());

        }
    }

}
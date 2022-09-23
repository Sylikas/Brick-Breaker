package brickbreaker;

import java.awt.*;

public class Map {

    private int[][] theMap;
    private final int BRICK_WIDTH;
    private final int BRICK_HEIGHT;

    public static final int HOR_PAD = 80;
    public static final int VERT_PAD = 50;

    public Map(int row, int col){

        initMap(row, col);

        BRICK_WIDTH = (Main.WIDTH - 2 * HOR_PAD) / col;
        BRICK_HEIGHT = (Main.HEIGHT / 2 - VERT_PAD * 2) / row;

    }

    public void initMap(int row, int col){

        theMap = new int[row][col];

        for(int i = 0; i < theMap.length; i++){
            for (int j = 0; j < theMap[0].length; j++) {

                int r = (int) (Math.random() * 4 + 1);
                theMap[i][j] = r;

            }
        }
    }

    public void draw(Graphics2D g){



        for(int row = 0; row < theMap.length; row++){

            for(int col = 0; col < theMap[0].length; col++){

                if(theMap[row][col] > 0) {

                    if(theMap[row][col] == 1){
                        g.setColor(new Color(100, 200, 200));
                    }
                    if(theMap[row][col] == 2){
                        g.setColor(new Color(150, 100, 200));
                    }
                    if(theMap[row][col] == 3){
                        g.setColor(new Color(150, 50,190));
                    }
                    if(theMap[row][col] == 4){
                        g.setColor(new Color(200, 0,200));
                    }


                    g.fillRect(col * BRICK_WIDTH + HOR_PAD, row * BRICK_HEIGHT + VERT_PAD, BRICK_WIDTH, BRICK_HEIGHT);
                    g.setStroke(new BasicStroke(2));
                    g.setColor(Color.BLACK);
                    g.drawRect(col * BRICK_WIDTH + HOR_PAD, row * BRICK_HEIGHT + VERT_PAD, BRICK_WIDTH, BRICK_HEIGHT);

                }
            }
        }
    }

    public boolean isThereAWinner(){

        boolean thereIsAWinner = false;

        int bricksRemaining = 0;

        for (int[] ints : theMap) {

            for (int col = 0; col < theMap[0].length; col++) {

                bricksRemaining += ints[col];
            }

        }


        if(bricksRemaining == 0){

            thereIsAWinner = true;

        }

        return thereIsAWinner;

    }

    public int[][] getMapArray(){ return theMap; }

    public int getBrickWidth(){ return BRICK_WIDTH; }
    public int getBrickHeight(){ return BRICK_HEIGHT; }

    public void hitBrick(int row, int col){

        theMap[row][col] -= 1;

        if(theMap[row][col] < 0){

            theMap[row][col] = 0;

        }

    }

}

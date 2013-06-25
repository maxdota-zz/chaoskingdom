/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Ngoc
 */
public class Utility {
    public static final String TARGET_IMG_PATH = "img/target.png";
    public static final int COLOR_NUM = 4;
    public static final int BLACK = 0;
    public static final int WHITE = 1;
    public static final int BLUE = 2;
    public static final int RED = 3;
    public static final int FIELD_DISPLAY_WIDTH = 700;
    public static final int FIELD_DISPLAY_HEIGHT = 550;
    public static final int TARGET_SIZE = 30;
    public static final char LOCK_TARGET = 0;
    public static final char SINGLE_SHOT = 1;
    public static final char RESET_FRAME = 2;
    public static final int BULLET_WIDTH = 16;
    public static final int BULLET_HEIGHT = 15;
    public static final Random rand = new Random();
    
    public static Point randomPoint(int minX, int minY, int maxX, int maxY) {
        int ranX = rand.nextInt(maxX - minX);
        int ranY = rand.nextInt(maxY - minY);
        return new Point(ranX + minX, ranY + minY);
    }
    
    public static Point shootingVector(int shooterX, int shooterY, int targetX, int targetY, int speed) {
        int dx = targetX - shooterX;
        int dy = targetY - shooterY;
        int distance = (int) Math.round(Math.sqrt(dx * dx + dy * dy));
        int x = speed * dx / distance;
        int y = speed * dy / distance;
        
        return new Point(x, y);
    }
    
    public static void main(String[] args) {
        System.out.println(shootingVector(300, 300, 600, 700, 200));
    }
}

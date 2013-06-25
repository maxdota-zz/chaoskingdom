/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import game.Utility;
import java.awt.Point;
import java.awt.Rectangle;
import view.Bullet;

/**
 *
 * @author Ngoc
 */
public class Trash extends Sprite {

    public static final String IMG_PATH = "img/trash.png";
    public static final int HP = 10;
    public static final int ATTACK = 10;
    public static final int SPEED = 5;
    public static final int BULLET_SPEED = 20;

    public Trash(int x, int y) {
        super(x, y, IMG_PATH, 50, 50, HP, ATTACK, SPEED, BULLET_SPEED, Utility.RED);
    }

    public void goAround() {
        if ((destination.x == x) && (destination.y == y)) {
            destination = Utility.randomPoint(0, 0, Utility.FIELD_DISPLAY_WIDTH - width, Utility.FIELD_DISPLAY_HEIGHT - height);
        }
    }
    
    @Override
    public void die() {
        System.out.println("Creep has died");
        updateView(false);
    }
}

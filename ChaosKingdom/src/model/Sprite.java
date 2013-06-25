package model;

import game.Utility;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import view.SpriteView;

import java.io.Serializable;
import java.util.Observable;
import view.Bullet;
import view.FieldView;

/**
 * Created with IntelliJ IDEA. User: Ngoc Date: 2/28/13 Time: 1:04 AM To change
 * this template use File | Settings | File Templates.
 */
public abstract class Sprite extends Observable implements Serializable {

    public static final int ROW = 5;
    public static final int COL = 8;
    public static final int UP = 0;
    public static final int DOWN = 20;
    public static final int RIGHT = 10;
    public static final int LEFT = 30;
    public static final int UPLEFT = 25;
    public static final int DOWNLEFT = 35;
    public static final int UPRIGHT = 5;
    public static final int DOWNRIGHT = 15;
    private int currentAnimation;
    private int attack;
    private int hp;
    private int speed;
    private int bulletSpeed;
    private String imgPath;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Object target;
    
    public void shoot(int x, int y) {
        target = new int[]{x, y};
    }

    public void setTarget(Sprite target) {
        this.target = target;
    }

    public void setTarget(int x, int y) {
        target = new Point(x, y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    protected Point destination;
    private int direction;
    protected int color;

    public int getColor() {
        return color;
    }

    public Sprite(int x, int y, String imgPath, int w, int h, int hp, int attack, int speed, int bulletSpeed, int color) {
        this.imgPath = imgPath;
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        destination = new Point(x, y);
        // set the attributes
        this.color = color;
        this.hp = hp;
        this.attack = attack;
        this.speed = speed;
        this.bulletSpeed = bulletSpeed;
        target = null;
    }

    // each sprite model will have a different view
    public void setView(int id, FieldView fv) {
        SpriteView view = new SpriteView(id, fv.getSpriteController());

        view.setLocation(x, y);
        fv.add(view);
        addObserver(view);
        File f = new File(imgPath);
        updateView(f);
    }

    public void setDestination(int x, int y) {
        destination = new Point(x - width / 2, y - height / 2);
    }

    public void nextAnimation(int direction) {
        if (currentAnimation < 4) {
            currentAnimation++;
        } else {
            currentAnimation = 0;
        }
        Object[] objects = {direction, currentAnimation};
        updateView(objects);
    }

    public void move() {
        if ((destination.x == x) && (destination.y == y)) {
            return;
        }
        int cx = destination.x - x;
        int cy = destination.y - y;
        if (cx < -speed) {
            // Left
            x -= speed;
            direction = LEFT;
        } else if (cx > speed) {
            // Right
            direction = RIGHT;
            x += speed;
        } else {
            x += cx;
        }
        if (cy < -speed) {
            // Up
            switch (direction) {
                case LEFT:
                    direction = UPLEFT;
                    break;
                case RIGHT:
                    direction = UPRIGHT;
                    break;
                default:
                    direction = UP;
            }
            y -= speed;
        } else if (cy > speed) {
            // Down
            switch (direction) {
                case LEFT:
                    direction = DOWNLEFT;
                    break;
                case RIGHT:
                    direction = DOWNRIGHT;
                    break;
                default:
                    direction = DOWN;
            }
            y += speed;
        } else {
            y += cy;
        }

//        if (x != previousX || y != previousY) {
//            nextAnimation(direction);
//        }
//        previousX = x;
//        previousY = y;
        nextAnimation(direction);
        updateView(new Point(x, y));
    }
    
        public void shootBullet(Field f) {
        if (target == null) {
            return;
        }
        if (target instanceof Sprite) {
            Sprite tar = (Sprite) target;
            f.addBullet(new Bullet(x, y, color, this, Utility.shootingVector(x, y, tar.x, tar.y, bulletSpeed)));
        } else if (target instanceof Point) {
            Point tar =(Point) target;
            f.addBullet(new Bullet(x, y, color, this, Utility.shootingVector(x, y, tar.x, tar.y, bulletSpeed)));
        } else {
            int[] tar = (int[]) target;
            f.addBullet(new Bullet(x, y, color, this, Utility.shootingVector(x, y, tar[0], tar[1], bulletSpeed)));
            target = null;
        }
    }

    public void updateView(Object o) {
        setChanged();
        notifyObservers(o);
    }
    
    public abstract void die();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import game.Utility;

/**
 *
 * @author Ngoc
 */
public class Hero extends Sprite {
    public static final String IMG_PATH = "img/hero.png";
    public static final int WIDTH = 36;    
    public static final int HEIGHT = 44;
    public static final int HP = 10;
    public static final int ATTACK = 10;
    public static final int SPEED = 10;
    public static final int BULLET_SPEED = 20;
    
    public Hero(int x, int y) {
        super(x, y, IMG_PATH, WIDTH, HEIGHT, HP, ATTACK, SPEED, BULLET_SPEED, Utility.BLACK);
    }
    
    @Override
    public void die() {
        System.out.println("You are dead meat!");
        updateView(false);
    }
}

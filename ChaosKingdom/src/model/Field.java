/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import game.GameViewLoop;
import game.Utility;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import view.Bullet;
import view.FieldView;

/**
 *
 * @author Ngoc
 */
public class Field extends Observable {

    private ArrayList<Sprite> sprites;
    private ArrayList<Bullet> bullets;
    private FieldView fieldView;
    private GameViewLoop viewLoop;

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    public Field() {
        sprites = new ArrayList<Sprite>();
        bullets = new ArrayList<Bullet>();
    }

    public void init() {
        addObserver(fieldView);
        setChanged();
        notifyObservers(sprites);
        setChanged();
        notifyObservers(bullets);
    }

    public void addSprite(Sprite sp) {
        sprites.add(sp);
        updateView(sp);
    }

    public void addBullet(Bullet bl) {
        bullets.add(bl);
        updateView(bl);
    }

    public void moveTo(int x, int y) {
        if (!sprites.isEmpty()) {
            sprites.get(0).setDestination(x, y);
        }
    }

    public void lockTarget(int x, int y) {
        if (!sprites.isEmpty()) {
            sprites.get(0).setTarget(x, y);
            updateView(Utility.LOCK_TARGET + "|" + x + "|" + y);
        }
    }

    public void singleShot(int x, int y) {
        if (!sprites.isEmpty()) {
            sprites.get(0).shoot(x, y);
            updateView(Utility.SINGLE_SHOT + "");
        }
    }

    public void reset() {
        System.out.println("Game restarted");
        sprites.removeAll(sprites);
        bullets.removeAll(bullets);
        if (viewLoop.isEnd()) {
            addObserver(fieldView);

        }
        updateView(Utility.RESET_FRAME + "");
        initialGame();
    }

    public void updateView(Object o) {
        setChanged();
        notifyObservers(o);
    }

    public FieldView getFieldView() {
        return fieldView;
    }

    public void setFieldView(FieldView fieldView) {
        this.fieldView = fieldView;
    }

    public void initialGame() {
        Sprite sp = new Hero(400, 300);
        Trash tr = new Trash(200, 100);
        Trash tr2 = new Trash(0, 300);
        addSprite(sp);
        addSprite(tr);
        addSprite(tr2);
        tr.setTarget(sp);
        tr2.setTarget(sp);

        if ((viewLoop == null) || (viewLoop.isEnd())) {
            viewLoop = new GameViewLoop(this);
            new Thread(viewLoop).start();
        }
    }
}

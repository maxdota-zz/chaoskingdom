/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import game.Utility;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Sprite;

/**
 *
 * @author Ngoc
 */
public class Bullet extends JLabel {

    public static final String PATH = "img/bullet.png";
    public static final int SPEED = 12;
    private static ImageIcon[] bullets;
    private Sprite shooter;
    private Point direction;
    private int color;

    public static void initialBullets() {
        Graphics2D g;
        BufferedImage bi;
        File f = new File(PATH);
        BufferedImage img = null;
        try {
            img = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bullets = new ImageIcon[Utility.COLOR_NUM];
        for (int i = 0; i < Utility.COLOR_NUM; i++) {
            // create an empty image
            bi = new BufferedImage(Utility.BULLET_WIDTH, Utility.BULLET_HEIGHT, img.getType());
            // draw into the image
            g = bi.createGraphics();
            g.drawImage(img, 0, 0, Utility.BULLET_WIDTH, Utility.BULLET_HEIGHT, i * Utility.BULLET_WIDTH, 0, Utility.BULLET_WIDTH * i + Utility.BULLET_WIDTH, Utility.BULLET_HEIGHT, null);
            g.dispose();
            bullets[i] = new ImageIcon(bi);
        }
    }

    public Bullet(int x, int y, int color, Sprite shooter, Point direction) {
        this.shooter = shooter;
        this.color = color;
        setIcon(bullets[color]);
        setSize(getPreferredSize());
        setLocation(x, y);
        this.direction = direction;
    }

    public boolean move() {
        setLocation(getX() + direction.x, getY() + direction.y);
        if ((getX() >= Utility.FIELD_DISPLAY_WIDTH) || (getX() < 0) || (getY() >= Utility.FIELD_DISPLAY_HEIGHT) || (getY() < 0)) {
            return false;
        }
        return true;
    }

    public boolean isCollide(Sprite sp) {
        if (color == sp.getColor()) {
            return false;
        }
        Rectangle blRec = getBounds();
        Rectangle spRec = new Rectangle(sp.getX(), sp.getY(), sp.getHeight(), sp.getWidth());
        return blRec.intersects(spRec);
    }
}

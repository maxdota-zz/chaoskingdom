package game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Field;
import model.Hero;
import model.Sprite;
import model.Trash;
import view.Bullet;

/**
 * Created with IntelliJ IDEA. User: Cao Anh Date: 3/12/13 Time: 12:17 PM To
 * change this template use File | Settings | File Templates.
 */
public class GameViewLoop implements Runnable {

    private Field f;
    private boolean end;

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public GameViewLoop(Field f) {
        this.f = f;
        end = false;
    }

    @Override
    public void run() {
        ArrayList<Sprite> sprites = f.getSprites();
        ArrayList<Bullet> bullets = f.getBullets();
        int count = 0;
        while (true) {
            // for each sprite
            for (int i = 0; i < sprites.size(); i++) {
                Sprite sp = sprites.get(i);
                sp.move();
                if (count == 0) {
                    sp.shootBullet(f);
                }
                if (sp instanceof Trash) {
                    Trash tr = (Trash) sp;
                    tr.goAround();
                }
            }

            // for each bullet
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bl = bullets.get(i);
                if (!bl.move()) {
                    bullets.remove(bl);
                    bl.setVisible(false);
                }

                for (int j = 0; j < sprites.size(); j++) {
                    Sprite sp = sprites.get(j);
                    if (bl.isCollide(sp)) {
                        sp.die();
                        sprites.remove(sp);
                        if (j == 0) {
                            f.deleteObservers();
                            end = true;
                            return;
                        }
                    }
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count++;
            if (count == 20) {
                count = 0;
            }
        }
    }
}

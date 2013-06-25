/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.FieldController;
import controller.SpriteController;
import game.Utility;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import model.Sprite;

/**
 *
 * @author Ngoc
 */
public class FieldView extends JPanel implements Observer {

    private ArrayList<Sprite> sprites;
    private SpriteController spriteController;
    private FieldController fieldController;
    private JLabel target;
    private ImageIcon targetIcon;
    public SpriteController getSpriteController() {
        return spriteController;
    }

    public FieldView(int width, int height) {
        setLayout(null);
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);
        
        targetIcon = new ImageIcon(Utility.TARGET_IMG_PATH);
        target = new JLabel(targetIcon);
        target.setSize(target.getPreferredSize());
        target.setVisible(false);
        spriteController = new SpriteController();
    }

    public void init(){
        addMouseListener(fieldController);
        addMouseMotionListener(fieldController);
        add(target);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ArrayList) {
            sprites = (ArrayList<Sprite>) arg;
        } else if (arg instanceof Sprite) {
            ((Sprite) arg).setView(WIDTH, this);
            repaint();
        } else if (arg instanceof Bullet) {
            add((Bullet) arg);
            repaint();
        } else if (arg instanceof String) {
            String st = ((String) arg);
            switch (st.charAt(0)) {
                case Utility.LOCK_TARGET:
                    String[] parts = st.split("\\|");
                    target.setLocation(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    target.setVisible(true);
                    break;
                case Utility.SINGLE_SHOT:
                    target.setVisible(false);
                    break;
                case Utility.RESET_FRAME:
                    removeAll();
                    add(target);
                    target.setVisible(false);
                    break;
            }
            repaint();
        } else {
            add((JLabel) arg);
            repaint();
        }
    }

    public FieldController getFieldController() {
        return fieldController;
    }

    public void setFieldController(FieldController fieldController) {
        this.fieldController = fieldController;
    }
}

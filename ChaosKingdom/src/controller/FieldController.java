package controller;

import game.Utility;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.Field;

/**
 * Created with IntelliJ IDEA. User: s3357672 Date: 12/03/13 Time: 10:27 AM To
 * change this template use File | Settings | File Templates.
 */
public class FieldController implements MouseListener, MouseMotionListener {

    private Field f;
    private int currentButton;

    public FieldController(Field f) {
        this.f = f;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (e.getClickCount() == 2) {
                f.lockTarget(e.getX() - Utility.TARGET_SIZE / 2, e.getY() - Utility.TARGET_SIZE / 2);
            } else {
                f.singleShot(e.getX(), e.getY());
            }
        } else {
            f.moveTo(e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // mouse pressed is trigged before mouse clicked and mouse dragged
        currentButton = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentButton != MouseEvent.BUTTON3) {
            f.moveTo(e.getX(), e.getY()); 
        } else {
            f.lockTarget(e.getX() - Utility.TARGET_SIZE / 2, e.getY() - Utility.TARGET_SIZE / 2);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

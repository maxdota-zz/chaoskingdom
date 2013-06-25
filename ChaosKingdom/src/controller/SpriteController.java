package controller;


import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Ngoc
 * Date: 5/5/13
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpriteController extends MouseAdapter {

    public SpriteController() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() instanceof JLabel) {
            JLabel target = (JLabel) e.getSource();
        }
    }
}

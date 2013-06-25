/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import model.Field;

/**
 *
 * @author Ngoc
 */
public class ButtonController implements ActionListener{
    private Field f;
    
    public ButtonController(Field f) {
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        f.reset();
    }
}

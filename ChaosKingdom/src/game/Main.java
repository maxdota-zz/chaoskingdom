/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import controller.FieldController;
import model.Field;
import model.Hero;
import model.Sprite;
import model.Trash;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import view.Bullet;
import view.FieldView;

/**
 *
 * @author Ngoc
 */
public class Main {
    
    private Field field;
//    private Connector connector;
    
    public Main() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        field = (Field)ctx.getBean("field");

        Bullet.initialBullets();
        field.initialGame();

    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}

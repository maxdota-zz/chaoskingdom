/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ngoc
 */
public class Connector {
    public static final char LOG_IN_DONE = 0;
    public static final char LOG_IN_FAIL = 1;
    public static final char ACCOUNT_IN_USE = 2;
    
    private Main main;
    private Socket client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    
    public Connector(Main m) {
        main = m;
        
        try {
            client = new Socket("localhost", 2003);
            objectInputStream = new ObjectInputStream(client.getInputStream());
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public String login(String username, String password) {
        String message = null;
        try {
            objectOutputStream.writeObject(username + "|" + password);
            message = (String) objectInputStream.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
}

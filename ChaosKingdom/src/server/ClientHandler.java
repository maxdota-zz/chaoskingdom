/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import game.Connector;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Ngoc
 */
public class ClientHandler implements Runnable {

    private Server server;
    private Socket client;

    public ClientHandler(Server server, Socket client) {
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            while (true) {
                try {
                    Object o = objectInputStream.readObject();
                    if (o instanceof String) {
                        String s = (String) o;
                        String[] parts = s.split("\\|");
                        String temp = null;
                        if (parts.length == 2) {
                            String username = parts[0];
                            String password = parts[1];
                            temp = server.getPlayerDAO().logIn(username, password);
                        }
                        
                        if (temp == null) {
                            objectOutputStream.writeObject(Connector.LOG_IN_FAIL + "");
                        } else {
                            if (server.getActivePlayers().contains(temp)) {
                                objectOutputStream.writeObject(Connector.ACCOUNT_IN_USE + "");
                            } else {
                                server.getActivePlayers().add(temp);
                                objectOutputStream.writeObject(Connector.LOG_IN_DONE + "|" + temp);
                            }
                        }
                    }
                } catch (SocketException ex) {
                    objectInputStream.close();
                    objectOutputStream.close();
                    return;
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
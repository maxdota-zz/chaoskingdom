/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springJDBC.PlayerDAO;

public class Server {
    private PlayerDAO playerDAO;

    public PlayerDAO getPlayerDAO() {
        return playerDAO;
    }
    private Socket client;

    private ArrayList<String> activePlayers;

    public void start() {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringJDBC/Spring-Module.xml");
        playerDAO = (PlayerDAO) context.getBean("playerDAO");
        activePlayers = new ArrayList<String>();
        try {
            ServerSocket server = new ServerSocket(2003);
            while (true) {
                client = server.accept();
                new Thread(new ClientHandler(this, client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Server().start();
    }

    public ArrayList<String> getActivePlayers() {
        return activePlayers;
    }
}

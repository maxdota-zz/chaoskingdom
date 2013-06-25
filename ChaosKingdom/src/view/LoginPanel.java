package view;

import game.Connector;
import game.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: Cao Anh Date: 4/2/13 Time: 10:48 AM To
 * change this template use File | Settings | File Templates.
 */
public class LoginPanel extends JPanel {

    private JLabel viewName;
    private JLabel nameLabel;
    private JLabel passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton clearBtn;
    private JButton backBtn;
    private Connector connector;

    public LoginPanel(Connector con) {
        connector = con;

        viewName = new JLabel("Log in");
        viewName.setFont(new Font("Tahoma", Font.PLAIN, 32));
        viewName.setForeground(Color.WHITE);
        viewName.setPreferredSize(new Dimension(250, 75));
        Dimension size = viewName.getPreferredSize();
        viewName.setBounds(320, 0, size.width, size.height);
        add(viewName);

        nameField = new JTextField();
        nameField.setColumns(20);
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        size = nameField.getPreferredSize();
        nameField.setBounds(335, 80, size.width, size.height);
        add(nameField);

        passwordField = new JPasswordField();
        passwordField.setColumns(20);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        size = passwordField.getPreferredSize();
        passwordField.setBounds(335, 110, size.width, size.height);
        add(passwordField);

        nameLabel = new JLabel("UserName");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nameLabel.setForeground(Color.WHITE);
        size = nameLabel.getPreferredSize();
        nameLabel.setBounds(210, 80, size.width, size.height);
        add(nameLabel);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordLabel.setForeground(Color.WHITE);
        size = passwordLabel.getPreferredSize();
        passwordLabel.setBounds(210, 110, size.width, size.height);
        add(passwordLabel);

        backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(100, 30));
        size = backBtn.getPreferredSize();
        backBtn.setBounds(260, 180, size.width, size.height);
        add(backBtn);

        loginBtn = new JButton("Login");
        loginBtn.setPreferredSize(new Dimension(100, 30));
        size = loginBtn.getPreferredSize();
        loginBtn.setBounds(370, 180, size.width, size.height);
        add(loginBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setPreferredSize(new Dimension(100, 30));
        size = clearBtn.getPreferredSize();
        clearBtn.setBounds(480, 180, size.width, size.height);
        add(clearBtn);

        // temporary solution
        add(new JButton(""));

        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login(nameField.getText(), passwordField.getText());
            }
        });

        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameField.setText("");
                passwordField.setText("");
            }
        });

        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Back");
            }
        });
        
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login(nameField.getText(), passwordField.getText());
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login(nameField.getText(), passwordField.getText());
                }
            }
        });

        setSize(new Dimension(800, 300));
        setLayout(null);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        Connector c = new Connector(null);
        LoginPanel v = new LoginPanel(c);
        f.add(v);
        f.setVisible(true);
        f.setSize(800, 600);
    }

    public void setAutoFocus() {
        nameField.requestFocus();
    }

    public void login(String username, String password) {
        if ((username.equals("")) || (password.equals(""))) {
            System.out.println("Somethinghere");
            JOptionPane.showMessageDialog(null, "Username or password cannot be empty");
            return;
        }
        String message = connector.login(username, password);
        if (message == null) {
            return;
        }
        switch (message.charAt(0)) {
            case Connector.LOG_IN_FAIL:
                JOptionPane.showMessageDialog(null, "Wrong username or password");
                break;
            case Connector.ACCOUNT_IN_USE:
                JOptionPane.showConfirmDialog(null, "Your account is being used by another application/computer. Please contact admin to change your password immediately.", "Account already logged in", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                break;
            case Connector.LOG_IN_DONE:
                String[] parts = message.split("\\|");
                System.out.println(parts[1] + " logged in");
                break;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ButtonController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ngoc
 */
public class Game extends JFrame {
    private ChatPanel chatPanel;
    private FieldView fieldView;
    private JPanel playerList;
    private JLabel [] player;
    private ButtonController buttonController;

    public ButtonController getButtonController() {
        return buttonController;
    }

    public void setButtonController(ButtonController buttonController) {
        this.buttonController = buttonController;
    }
    private JPanel bottomPanel;
    private JPanel miniMap;
    public Game() {
        setLayout(new BorderLayout());
        setVisible(true);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init()
    {
        JPanel left = new JPanel(null);
        
        left.setPreferredSize(new Dimension(800, 700));
        
        playerList=new JPanel(new GridLayout(4, 1));
        playerList.setPreferredSize(new Dimension(100, 500));
        playerList.setBounds(700, 0, 100, 500);
        left.add(playerList);
       
        player=new JLabel[4];
        for (int i = 0; i <4 ; i++) {
            player[i]=new JLabel();
            player[i].setText(i+1+"");
            player[i].setBorder(BorderFactory.createLineBorder(Color.blue, 3, true));
            player[i].setFont(new Font("French Script MT", Font.BOLD , 25));
            playerList.add(player[i]);
        }
        player[0].setText("<html>1  Max <br/>Level:50 Score:50</html>");
        
        bottomPanel=new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(800, 150));
        bottomPanel.setBounds(0,500,800,150);
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.yellow,3,true));
        left.add(bottomPanel);
        
        JButton restart = new JButton("Restart");
        bottomPanel.add(restart, BorderLayout.EAST);
        restart.addActionListener(buttonController);
        
        fieldView.setPreferredSize(new Dimension(700, 550));
        fieldView.setBounds(0,0,700,550);
        left.add(fieldView);
        
        add(left, BorderLayout.WEST);
        add(chatPanel, BorderLayout.EAST);
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public void setChatPanel(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    public FieldView getFieldView() {
        return fieldView;
    }

    public void setFieldView(FieldView fieldView) {
        this.fieldView = fieldView;
    }
}

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: Cao Anh
 * Date: 3/16/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatPanel extends JPanel {

    private JPanel chatOption;
    private JScrollPane outputScrollPane;
    private Font font;
    private JPanel bottomPanel;
    private JTextArea output;
    private JTextArea input;
    private JButton sendBtn;
    private ButtonGroup buttonGroup;
    private JRadioButton all;
    private JRadioButton ally;
    private JRadioButton one;
    private JComboBox pmPlayersComboBox;
    private DefaultComboBoxModel<String> pmPlayerModel;
    private JPanel upperPanel;

    private String chatChoice;

    public ChatPanel() {

        setLayout(new BorderLayout(0, 0));
        setPreferredSize(new Dimension(200, 400));
        sendBtn = new JButton("Send");
        input = new JTextArea();
        output = new JTextArea();
        chatOption = new JPanel();
        buttonGroup = new ButtonGroup();
//        chatChoice = GameConnector.ALL_CHAT + "";

        all = new JRadioButton("All", true);
        all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pmPlayersComboBox.setEnabled(false);
//                chatChoice = GameConnector.ALL_CHAT + "";
            }
        });
        ally = new JRadioButton("Ally          ", true);
        ally.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pmPlayersComboBox.setEnabled(false);
//                chatChoice = GameConnector.ALLY_CHAT + "";
            }
        });
        one = new JRadioButton("Player");
        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pmPlayersComboBox.setEnabled(true);
//                chatChoice = GameConnector.PRIVATE_CHAT + "|" + pmPlayerModel.getSelectedItem();
            }
        });

        pmPlayersComboBox = new JComboBox();
        pmPlayersComboBox.setEnabled(false);
        pmPlayerModel = new DefaultComboBoxModel<String>();
        pmPlayersComboBox.setModel(pmPlayerModel);
        pmPlayersComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                chatChoice = GameConnector.PRIVATE_CHAT + "|" + pmPlayerModel.getSelectedItem();
            }
        });

        buttonGroup.add(all);
        buttonGroup.add(one);
        buttonGroup.add(ally);
        chatOption.add(new JLabel("      Send message to            "));
        chatOption.add(all);
        chatOption.add(ally);
        chatOption.add(one);
        chatOption.add(pmPlayersComboBox);
        chatOption.setPreferredSize(new Dimension(100, 300));
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                gameConnector.sendMessage(chatChoice, input.getText());
                input.setText("");
            }
        });

        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    gameConnector.sendMessage(chatChoice, input.getText());
                    input.setText("");
                    e.consume();
                }
            }
        });

        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setPreferredSize(new Dimension(200, 150));
        upperPanel = new JPanel(new BorderLayout());
        upperPanel.setPreferredSize(new Dimension(200, 500));
        outputScrollPane = new JScrollPane(output);
        font = new Font("Verdana", Font.BOLD, 12);
        input.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        input.setFont(font);
        input.setLineWrap(true);
        input.setForeground(Color.red);
        output.setLineWrap(true);
        output.setEditable(false);
        input.setPreferredSize(new Dimension(200, 50));
        outputScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(200, 170));
        bottomPanel.add(chatOption, BorderLayout.CENTER);
        bottomPanel.add(sendBtn, BorderLayout.NORTH);

        bottomPanel.add(input, BorderLayout.SOUTH);
        upperPanel.add(outputScrollPane);
        add(upperPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setOutput(String st) {
        output.setText(st);
    }

    public void addName(String name) {
        pmPlayerModel.addElement(name);
        resetChatChoice();
    }

    public void resetChatChoice() {
        buttonGroup.clearSelection();
        all.setSelected(true);
//        chatChoice = GameConnector.ALL_CHAT + "";
    }
}

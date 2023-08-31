package com.fiatalis.windows;

import com.fiatalis.command.Attribute;
import com.fiatalis.command.Save;
import com.fiatalis.entity.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class OptionsWindows extends JFrame {
    private final JPanel allPanel = new JPanel(new GridLayout(10, 1));
    private final JPanel p1 = new JPanel(new GridLayout(1, 4));
    private final JPanel p2 = new JPanel(new GridLayout(1, 4));
    private final JPanel p3 = new JPanel(new GridLayout(1, 4));
    private final JPanel p4 = new JPanel(new GridLayout(1, 4));
    private final JPanel panelButton = new JPanel(new GridLayout(1, 2));
    private final JTextField connectAddress = new JTextField();
    private final JTextField connectPort = new JTextField();
    private final JTextField userName = new JTextField();
    private final JPasswordField userPass = new JPasswordField();
    private final JTextField serverAddress = new JTextField();
    private final JTextField serverPort = new JTextField();
    private final JTextField directoryAddress = new JTextField();
    private final JComboBox intLang;
    private final JComboBox intShell;
    private final JButton save;
    private final JButton cancel;
    private ResourceBundle rb;

    private final JLabel user = new JLabel("  User");
    private final JLabel directory = new JLabel("  Directory");
    private final JLabel connect = new JLabel("  Connect");
    private final JLabel server = new JLabel("  Server");
    private final JLabel shell = new JLabel("  Interface");

    {
        rb = ResourceBundle.getBundle("interface", Language.getInstance().getLocate());
        save = new JButton(rb.getString("save"));
        cancel = new JButton(rb.getString("cancel"));
        intLang = new JComboBox(new String[]{"RU", "ENG"});
        intLang.setSelectedItem(Language.getInstance().getLanguage());
        intShell = new JComboBox(new String[]{"true", "false"});
        connectAddress.setText(Connect.getInstance().getName());
        connectPort.setText(Connect.getInstance().getPort());
        userName.setText(User.getInstance().getName());
        userPass.setText("x");
        serverAddress.setText(Server.getInstance().getName());
        serverPort.setText(Server.getInstance().getPort());
        directoryAddress.setText(Directory.getInstance().getName());
        p1.setBorder(new BevelBorder(0));
        p2.setBorder(new BevelBorder(0));
        p3.setBorder(new BevelBorder(0));
        p4.setBorder(new BevelBorder(0));
        this.setTitle("Options");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 500));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public OptionsWindows() {
        addComponent();
        listeners();
    }

    private void addComponent() {
        allPanel.add(user);
        p1.add(new JLabel("                   Name"));
        p1.add(userName);
        p1.add(new JLabel("                    pass"));
        p1.add(userPass);
        allPanel.add(p1);
        allPanel.add(connect);
        p2.add(new JLabel("                   IP"));
        p2.add(connectAddress);
        p2.add(new JLabel("                    port"));
        p2.add(connectPort);
        allPanel.add(p2);
        allPanel.add(server);
        p3.add(new JLabel("                   IP"));
        p3.add(serverAddress);
        p3.add(new JLabel("                    port"));
        p3.add(serverPort);
        allPanel.add(p3);
        allPanel.add(directory);
        allPanel.add(directoryAddress);
        allPanel.add(shell);
        p4.add(new JLabel("         On/Off shell"));
        p4.add(intShell);
        p4.add(new JLabel("            Language"));
        p4.add(intLang);
        allPanel.add(p4);
        panelButton.add(cancel, BorderLayout.WEST);
        panelButton.add(save, BorderLayout.EAST);
        this.add(allPanel, BorderLayout.NORTH);
        this.add(panelButton, BorderLayout.SOUTH);
    }


    private void listeners() {
        cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().getWindows().setVisible(true);
                Controller.getInstance().getOptionsWindows().setVisible(false);
            }
        });
        save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connect.getInstance().setName(connectAddress.getText());
                Connect.getInstance().setPort(connectPort.getText());
                User.getInstance().setName(userName.getText());
                if (userPass.getPassword()[0] != 'x' && userPass.getPassword().length != 1) {
                    User.getInstance().setPassword(userPass.getPassword().toString());
                }
                Server.getInstance().setName(serverAddress.getText());
                Server.getInstance().setPort(serverPort.getText());
                Directory.getInstance().setName(directoryAddress.getText());
                Skin.getInstance().setSkin(intShell.getSelectedItem().equals("true"));
                if (!Language.getInstance().getLanguage().equals(intLang.getSelectedItem())) {
                    Language.getInstance().setLanguage(intLang.getSelectedItem().toString());
                    Controller.getInstance().getWindows().language();
                }
                new Save(new Attribute("save opt")).optionsHandler();
                Controller.getInstance().getWindows().setVisible(true);
                Controller.getInstance().getOptionsWindows().setVisible(false);
            }
        });
    }
}

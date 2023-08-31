package com.fiatalis.windows;

import com.fiatalis.entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class OptionsWindows extends JFrame {

    private final JPanel panel = new JPanel(new GridLayout(6, 2));
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

    private final JLabel user = new JLabel("user");
    private final JLabel directory = new JLabel("directory");
    private final JLabel connect = new JLabel("connect");
    private final JLabel server = new JLabel("server");
    private final JLabel shell = new JLabel("interface");

    {
        rb = ResourceBundle.getBundle("interface", Language.getInstance().getLocate());
        save = new JButton(rb.getString("save"));
        cancel = new JButton(rb.getString("cancel"));
        intLang = new JComboBox(new String[]{"RU", "ENG"});
        intShell = new JComboBox(new String[]{"true", "false"});
        connectAddress.setText(Connect.getInstance().getName());
        connectPort.setText(Connect.getInstance().getPort());
        userName.setText(User.getInstance().getName());
        userPass.setText(String.valueOf(User.getInstance().getPassHash()));
        serverAddress.setText(Server.getInstance().getName());
        serverPort.setText(Server.getInstance().getPort());
        directoryAddress.setText(Directory.getInstance().getName());
        this.setTitle("Options");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 500));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public OptionsWindows() {
        addComponent();
        listeners();
    }

    private void addComponent() {
        panel.add(user);
        panel.add(userName);
        panel.add(userPass);
        panel.add(connect);
        panel.add(connectAddress);
        panel.add(connectPort);
        panel.add(server);
        panel.add(serverAddress);
        panel.add(serverPort);
        panel.add(directory);
        panel.add(directoryAddress);
        panel.add(shell);
        panel.add(intShell);
        panel.add(intLang);
        panelButton.add(cancel, BorderLayout.WEST);
        panelButton.add(save, BorderLayout.EAST);
        this.add(panel,BorderLayout.NORTH);
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
    }
}

package com.fiatalis.windows;

import com.fiatalis.client.Client;
import com.fiatalis.entity.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Windows extends JFrame {

    private DefaultListModel defaultListModel = new DefaultListModel<>();
    private final JList list = new JList(defaultListModel);
    private final JPanel menu = new JPanel(new GridLayout(1, 3));
    private final JTextField connectAddres = new JTextField();
    private final JTextField connectPort = new JTextField();
    private final JButton buttonConnect = new JButton("Connect");

    public Windows() {
        this.setTitle("Transfer Elite");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 500));
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        connectAddres.setText(Connect.getInstance().getName());
        connectPort.setText(Connect.getInstance().getPort());
        addComponent();
        listeners();
        //addMouseListener();
    }

    private void addComponent() {
        connectAddres.setHorizontalAlignment(0);
        connectPort.setHorizontalAlignment(0);
        menu.add(connectAddres);
        menu.add(connectPort);
        menu.add(buttonConnect);
        this.add(menu, BorderLayout.NORTH);
        this.add(list, BorderLayout.CENTER);
    }

    private void listeners() {
        buttonConnect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = Client.getInstance();
                if (!client.isAuthorized()) {
                    Connect.getInstance().setName(connectAddres.getText());
                    Connect.getInstance().setPort(connectPort.getText());
                    client.connect(Connect.getInstance());
                    client.authentication("pc","123");
                }
                updateList();
            }
        });
    }

    private void updateList() {
        try {
            Client.getInstance().updateServerViewPath();
            while (Client.getInstance().getServerView().size() == 0) ;
            defaultListModel.removeAllElements();
            defaultListModel.addAll(Client.getInstance().getServerView());
        } catch (NullPointerException e) {
        } catch (ArrayIndexOutOfBoundsException e){}
    }


    public JFrame getFrame() {
        return this;
    }
}

package com.fiatalis.windows;

import com.fiatalis.client.Client;
import com.fiatalis.client.command.Attribute;
import com.fiatalis.entity.Connect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Windows extends JFrame {

    private DefaultTableModel tableModel = new DefaultTableModel();
    private final JTable table = new JTable(tableModel);
    private final JPanel menu = new JPanel(new GridLayout(1, 3));
    private final JLabel connectAddres = new JLabel();
    private final JLabel connectPort = new JLabel();
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
        tableModel.addColumn("Наименование");
        menu.add(connectAddres);
        menu.add(connectPort);
        menu.add(buttonConnect);
        this.add(menu, BorderLayout.NORTH);
        this.add(table, BorderLayout.CENTER);
    }

    private void listeners() {
        buttonConnect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new com.fiatalis.client.command.Connect(new Attribute("connect"));
                updateTable();
            }
        });
    }

    private void updateTable() {
        try {
            Client.getInstance().updateServerViewPath();
            while (Client.getInstance().getServerView().size() == 0) ;
            tableModel.insertRow(0, Client.getInstance().getServerView().toArray());
        } catch (NullPointerException e) {
            System.out.println("Требуется соедениться с сервером");
        }
    }


    public JFrame getFrame() {
        return this;
    }
}

package com.fiatalis.windows;

import com.fiatalis.client.Client;
import com.fiatalis.entity.Connect;
import com.fiatalis.modelMessage.FileMessage;
import com.fiatalis.modelMessage.FileRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Windows extends JFrame {

    private DefaultListModel defaultListModel = new DefaultListModel<>();
    private final JList list = new JList(defaultListModel);
    private final JPanel menuUp = new JPanel(new GridLayout(1, 3));
    private final JPanel menuDown = new JPanel(new GridLayout(1, 2));
    private final JTextField connectAddres = new JTextField();
    private final JTextField connectPort = new JTextField();
    private final JButton buttonConnect = new JButton("Connect");
    private final JButton buttonDownload = new JButton("Download");
    private final JButton buttonUpload = new JButton("Upload");
    private final JFileChooser fileChooser = new JFileChooser();

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
        menuUp.add(connectAddres);
        menuUp.add(connectPort);
        menuUp.add(buttonConnect);
        menuDown.add(buttonDownload);
        menuDown.add(buttonUpload);
        this.add(menuUp, BorderLayout.NORTH);
        this.add(list, BorderLayout.CENTER);
        this.add(menuDown, BorderLayout.SOUTH);
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
                    client.authentication("pc", "123");
                }
                updateList();
            }
        });
        buttonUpload.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setMultiSelectionEnabled(true);
                fileChooser.showOpenDialog(new JFileChooser());
                File[] files = fileChooser.getSelectedFiles();
                for (int i = 0; i < files.length; i++) {
                    while (Client.getInstance().isTransfer()) ;
                    try {
                        Client.getInstance().getOos().writeObject(new FileMessage(files[i].toPath()));
                        Client.getInstance().setTransfer(true);

                    } catch (IOException w) {
                    }
                }
                updateList();
            }
        });
        buttonDownload.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = list.getSelectedIndices();
                String[] files = new String[selectedIndices.length];
                for (int i = 0; i < selectedIndices.length; i++) {
                    files[i] =  String.valueOf(list.getModel().getElementAt(selectedIndices[i]));
                }
                for (int i = 0; i < files.length; i++) {
                    while (Client.getInstance().isTransfer()) ;
                    try {
                        Client.getInstance().getOos().writeObject(new FileRequest(files[i], false));
                        Client.getInstance().setTransfer(true);

                    } catch (IOException w) {
                    }
                }
                updateList();
            }
        });
    }

    private void updateList() {
        try {
            Client.getInstance().updateServerViewPath();
            while (Client.getInstance().isTransfer()) ;
            defaultListModel.removeAllElements();
            defaultListModel.addAll(Client.getInstance().getServerView());
        } catch (NullPointerException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public JFrame getFrame() {
        return this;
    }
}

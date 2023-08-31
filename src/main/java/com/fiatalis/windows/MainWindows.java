package com.fiatalis.windows;

import com.fiatalis.client.Client;
import com.fiatalis.entity.Connect;
import com.fiatalis.entity.Language;
import com.fiatalis.modelMessage.FileMessage;
import com.fiatalis.modelMessage.FileRequest;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class MainWindows extends JFrame {

    private DefaultListModel defaultListModel = new DefaultListModel<>();
    private final JList list = new JList(defaultListModel);
    private final JPanel menuUp = new JPanel(new GridLayout(1, 3));
    private final JPanel menuDown = new JPanel(new GridLayout(1, 2));
    private final JPanel menuDownIndic = new JPanel(new GridLayout(1, 3));
    private final JPanel panel = new JPanel(new GridLayout(2, 1));
    private final JTextField connectAddress = new JTextField();
    private JLabel status;
    private JLabel statusUpDown = new JLabel("", SwingConstants.LEFT);
    private final JTextField connectPort = new JTextField();
    private final JButton buttonConnect;
    private final JButton buttonDownload;
    private final JButton buttonUpload;
    private final JFileChooser fileChooser = new JFileChooser();
    private final JMenuBar menuBar;
    private final JMenu optionsMenu;
    private ResourceBundle rb;

    {
        rb = ResourceBundle.getBundle("interface", Language.getInstance().getLocate());
        status = new JLabel(rb.getString("notConnect") + "  ", SwingConstants.RIGHT);
        buttonConnect = new JButton(rb.getString("buttonConnect"));
        buttonDownload = new JButton(rb.getString("buttonDownload"));
        buttonUpload = new JButton(rb.getString("buttonUpload"));
        connectAddress.setText(Connect.getInstance().getName());
        connectPort.setText(Connect.getInstance().getPort());
        menuBar = new JMenuBar();
        optionsMenu = new JMenu("Опции");
        iniMenu();
        this.setTitle("Transfer Elite");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 500));
        this.setLocationRelativeTo(null);
        this.setResizable(true);

    }

    private void iniMenu() {
        Font font = new Font("Verdana", Font.PLAIN, 12);
        optionsMenu.setFont(font);
        menuBar.add(optionsMenu);
    }

    public MainWindows() {
        addComponent();
        listeners();
        language();
    }

    private void addComponent() {
        connectAddress.setHorizontalAlignment(0);
        connectPort.setHorizontalAlignment(0);
        menuUp.add(connectAddress);
        menuUp.add(connectPort);
        menuUp.add(buttonConnect);
        menuDown.add(buttonDownload);
        menuDown.add(buttonUpload);
        menuDownIndic.add(statusUpDown);
        panel.add(menuDown, BorderLayout.NORTH);
        panel.add(menuDownIndic, BorderLayout.SOUTH);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(status);
        menuBar.add(buttonConnect);
        this.add(menuBar, BorderLayout.NORTH);
        this.add(list, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
    }

    private void listeners() {
        buttonConnect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = Client.getInstance();
                if (!client.isAuthorized()) {
                    Connect.getInstance().setName(connectAddress.getText());
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
                        statusUpDown.setText(rb.getString("sending") + files[i].getName());
                        Client.getInstance().getOos().writeObject(new FileMessage(files[i].toPath()));
                        Client.getInstance().setTransfer(true);
                    } catch (IOException w) {
                    }
                }
                updateList();
                statusUpDown.setText(rb.getString("sending") + " " + rb.getString("complete"));
            }
        });
        buttonDownload.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = list.getSelectedIndices();
                String[] files = new String[selectedIndices.length];
                for (int i = 0; i < selectedIndices.length; i++) {
                    files[i] = String.valueOf(list.getModel().getElementAt(selectedIndices[i]));
                }
                for (int i = 0; i < files.length; i++) {
                    while (Client.getInstance().isTransfer()) ;
                    try {
                        statusUpDown.setText(rb.getString("downloading") + " " + files[i]);
                        Client.getInstance().getOos().writeObject(new FileRequest(files[i], false));
                        Client.getInstance().setTransfer(true);

                    } catch (IOException w) {
                    }
                }
                updateList();
                statusUpDown.setText(rb.getString("downloading") + " " + rb.getString("complete"));
            }
        });
        optionsMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                Controller.getInstance().getWindows().setVisible(false);
                Controller.getInstance().startOptions();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
    }

    public void language() {
        rb = ResourceBundle.getBundle("interface", Language.getInstance().getLocate());
        status.setText(rb.getString("notConnect") + "  ");
        buttonConnect.setText(rb.getString("buttonConnect"));
        buttonDownload.setText(rb.getString("buttonDownload"));
        buttonUpload.setText(rb.getString("buttonUpload"));
    }


    private void updateList() {
        try {
            Client.getInstance().updateServerViewPath();
            while (Client.getInstance().isTransfer()) ;
            status.setText(rb.getString("connectTo") + Connect.getInstance().getName() + ":" + Connect.getInstance().getPort() + "   ");
            defaultListModel.removeAllElements();
            defaultListModel.addAll(Client.getInstance().getServerView());
        } catch (NullPointerException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }
}

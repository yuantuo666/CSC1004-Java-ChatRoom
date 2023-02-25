/*
 * Created by JFormDesigner on Fri Feb 24 13:49:23 CST 2023
 */

package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Yuan_Tuo
 */
public class Chatroom extends JFrame {
    public JScrollBar jscrollBar = null;

    public Chatroom() {
        initComponents();
        // set the windows size
        jscrollBar = scrollPane1.getVerticalScrollBar();
        heartBeat();
    }

    private void heartBeat() {
        final long timeInterval = 10000;
        Runnable runnable = () -> {
            while (true) {
                if (Client.client != null) {
                    Client.send.send("/heartbeat");
                }
                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void send(ActionEvent e) {
        String text = SendText.getText();
        // send the message
        Client.send.send(text);
        // clean input
        SendText.setText("");
    }

    public void receive(String s){
        ReceiveText.setText(ReceiveText.getText()+"\n"+s);
        if (jscrollBar != null) {
            jscrollBar.setValue(jscrollBar.getMaximum());
        }
    }

    private void thisWindowClosing(WindowEvent e) {
        Client.send.send("/bye");
        System.exit(0);
    }

    private void thisWindowClosed(WindowEvent e) {
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - yuantuo666
        scrollPane1 = new JScrollPane();
        ReceiveText = new JTextPane();
        scrollPane2 = new JScrollPane();
        SendText = new JEditorPane();
        send = new JButton();

        //======== this ========
        setTitle("ChatRoom");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[615,fill]" +
            "[82,fill]",
            // rows
            "[]" +
            "[394]" +
            "[]" +
            "[60]"));

        //======== scrollPane1 ========
        {
            scrollPane1.setMinimumSize(new Dimension(16, 400));

            //---- ReceiveText ----
            ReceiveText.setMinimumSize(new Dimension(7, 400));
            ReceiveText.setEditable(false);
            scrollPane1.setViewportView(ReceiveText);
        }
        contentPane.add(scrollPane1, "cell 1 1 2 1");

        //======== scrollPane2 ========
        {
            scrollPane2.setMinimumSize(new Dimension(16, 100));
            scrollPane2.setViewportView(SendText);
        }
        contentPane.add(scrollPane2, "cell 1 2 2 1");

        //---- send ----
        send.setText("Send");
        send.addActionListener(e -> send(e));
        contentPane.add(send, "cell 2 3");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - yuantuo666
    private JScrollPane scrollPane1;
    private JTextPane ReceiveText;
    private JScrollPane scrollPane2;
    private JEditorPane SendText;
    private JButton send;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

/*
 * Created by JFormDesigner on Thu Feb 23 22:19:59 CST 2023
 */

package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;
import net.miginfocom.swing.*;

/**
 * @author Yuan_Tuo
 */
public class Login extends JFrame {
    public static Client client;
    public Login() {
        Client client = new Client();
        this.client = client;
        initComponents();
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new Login().setVisible(true);
    }

    private void login(ActionEvent e) {
        System.out.println(username.getText()+password.getText());
        client.send.send("/login "+username.getText()+" "+password.getText());
    }

    private void register(ActionEvent e) {
        client.register= new Register(client);
        client.register.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - yuantuo666
        label1 = new JLabel();
        label2 = new JLabel();
        username = new JTextField();
        label3 = new JLabel();
        password = new JTextField();
        login = new JButton();
        register = new JButton();

        //======== this ========
        setTitle("ChatRoom Login");
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[69,fill]" +
            "[187,fill]" +
            "[60,fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[20]" +
            "[37]" +
            "[28]"));

        //---- label1 ----
        label1.setText("ChatRoom Login");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 8f));
        contentPane.add(label1, "cell 1 1");

        //---- label2 ----
        label2.setText("Username");
        contentPane.add(label2, "cell 0 3");
        contentPane.add(username, "cell 1 3");

        //---- label3 ----
        label3.setText("Password");
        contentPane.add(label3, "cell 0 4");
        contentPane.add(password, "cell 1 4");

        //---- login ----
        login.setText("Login");
        login.addActionListener(e -> login(e));
        contentPane.add(login, "cell 1 6");

        //---- register ----
        register.setText("Go To Register");
        register.addActionListener(e -> register(e));
        contentPane.add(register, "cell 1 7");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - yuantuo666
    private JLabel label1;
    private JLabel label2;
    private JTextField username;
    private JLabel label3;
    private JTextField password;
    private JButton login;
    private JButton register;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

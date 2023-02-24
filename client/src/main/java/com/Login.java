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
    public Login() {
        initComponents();
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new Login().setVisible(true);
    }

    private void login(ActionEvent e) {
        // TODO add your code here
        System.out.println("click login"+username.getText()+password.getText());

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

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[71,fill]" +
            "[251,fill]" +
            "[60,fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("ChatRoom Login");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 6f));
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

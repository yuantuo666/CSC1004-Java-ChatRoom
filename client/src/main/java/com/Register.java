/*
 * Created by JFormDesigner on Fri Feb 24 12:54:12 CST 2023
 */

package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Yuan_Tuo
 */
public class Register extends JFrame {
    Client client;
    public Register(Client client) {
        this.client = client;
        initComponents();
    }

    private void submit(ActionEvent e) {
        String username_ = username.getText().replace(' ','_');
        String password_ = password.getText().replace(' ','_');
        String age_ = age.getText().replace(' ','_');
        String address_ = address.getText().replace(' ','_');
        String command = "/register "+username_+" "+password_+" "+age_+" "+gender.getSelectedIndex()+" "+address_;
        System.out.println(command);
        client.send.send(command);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - yuantuo666
        label1 = new JLabel();
        label2 = new JLabel();
        username = new JTextField();
        label3 = new JLabel();
        password = new JTextField();
        label4 = new JLabel();
        age = new JTextField();
        label5 = new JLabel();
        gender = new JComboBox<>();
        label6 = new JLabel();
        address = new JTextField();
        submit = new JButton();

        //======== this ========
        setTitle("Register");
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[85,fill]" +
            "[221,fill]" +
            "[53,fill]",
            // rows
            "[41]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[25]" +
            "[]"));

        //---- label1 ----
        label1.setText("Register");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 6f));
        contentPane.add(label1, "cell 1 0");

        //---- label2 ----
        label2.setText("Username");
        label2.setHorizontalAlignment(SwingConstants.TRAILING);
        contentPane.add(label2, "cell 0 2");
        contentPane.add(username, "cell 1 2");

        //---- label3 ----
        label3.setText("Password");
        label3.setHorizontalAlignment(SwingConstants.TRAILING);
        contentPane.add(label3, "cell 0 3");
        contentPane.add(password, "cell 1 3");

        //---- label4 ----
        label4.setText("Age");
        label4.setHorizontalAlignment(SwingConstants.TRAILING);
        contentPane.add(label4, "cell 0 4");
        contentPane.add(age, "cell 1 4");

        //---- label5 ----
        label5.setText("Gender");
        label5.setHorizontalAlignment(SwingConstants.TRAILING);
        contentPane.add(label5, "cell 0 5");

        //---- gender ----
        gender.setModel(new DefaultComboBoxModel<>(new String[] {
            "Male",
            "Female"
        }));
        contentPane.add(gender, "cell 1 5");

        //---- label6 ----
        label6.setText("Address");
        label6.setHorizontalAlignment(SwingConstants.TRAILING);
        contentPane.add(label6, "cell 0 6");
        contentPane.add(address, "cell 1 6");

        //---- submit ----
        submit.setText("Submit");
        submit.addActionListener(e -> submit(e));
        contentPane.add(submit, "cell 1 7");
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
    private JLabel label4;
    private JTextField age;
    private JLabel label5;
    private JComboBox<String> gender;
    private JLabel label6;
    private JTextField address;
    private JButton submit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

/*
 * Created by JFormDesigner on Fri Feb 24 12:34:12 CST 2023
 */

package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Yuan_Tuo
 */
public class Dialog extends JDialog {
    public Dialog(String title ,String msg) {
        initComponents();
        this.setTitle(title);
        msgLable.setText(msg);
    }

    private void ok(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - yuantuo666
        dialogPane = new JPanel();
        buttonBar = new JPanel();
        okButton = new JButton();
        msgLable = new JLabel();

        //======== this ========
        setAlwaysOnTop(true);
        setMinimumSize(new Dimension(400, 180));
        setPreferredSize(new Dimension(400, 180));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(2, 2));

        //======== dialogPane ========
        {
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]",
                    // rows
                    null));

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, "cell 0 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            //---- msgLable ----
            msgLable.setText("Msg");
            dialogPane.add(msgLable, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - yuantuo666
    private JPanel dialogPane;
    private JPanel buttonBar;
    private JButton okButton;
    private JLabel msgLable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

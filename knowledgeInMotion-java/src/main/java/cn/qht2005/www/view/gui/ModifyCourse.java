/*
 * Created by JFormDesigner on Sat Jun 01 11:22:02 CST 2024
 */

package cn.qht2005.www.view.gui;

import java.awt.*;
import javax.swing.*;

/**
 * @author 覃
 */
public class ModifyCourse extends JDialog {
    public ModifyCourse(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("text");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(5, 5), label1.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(335, 345));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

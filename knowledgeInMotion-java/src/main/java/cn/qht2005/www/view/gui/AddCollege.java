/*
 * Created by JFormDesigner on Sat Jun 01 15:41:47 CST 2024
 */

package cn.qht2005.www.view.gui;

import cn.qht2005.www.service.impl.AdministratorServiceImpl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 覃
 */
public class AddCollege extends JDialog {
    public AddCollege(Window owner) {
        super(owner);
        initComponents();
    }
    // 提交按钮被点击
    private void buttonSubmitMouseClicked(MouseEvent e) {
        addCollege();
    }
    // 添加学院
    private void addCollege(){
        // 获取学院名称
        String collegeName = inputCollegeName.getText();
        if (collegeName.isEmpty()){
            JOptionPane.showMessageDialog(this, "学院名称不能为空");
            return;
        }
        boolean res = new AdministratorServiceImpl().addCollege(collegeName);
        if (res){
            JOptionPane.showMessageDialog(this, "添加成功");
        }else{
            JOptionPane.showMessageDialog(this, "添加失败");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        inputCollegeName = new JTextField();
        buttonSubmit = new JButton();

        //======== this ========
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5b66\u9662\u540d\u79f0");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(10, 10), label1.getPreferredSize()));
        contentPane.add(inputCollegeName);
        inputCollegeName.setBounds(70, 5, 130, inputCollegeName.getPreferredSize().height);

        //---- buttonSubmit ----
        buttonSubmit.setText("\u6dfb\u52a0");
        buttonSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonSubmitMouseClicked(e);
            }
        });
        contentPane.add(buttonSubmit);
        buttonSubmit.setBounds(new Rectangle(new Point(215, 5), buttonSubmit.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(305, 70));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField inputCollegeName;
    private JButton buttonSubmit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

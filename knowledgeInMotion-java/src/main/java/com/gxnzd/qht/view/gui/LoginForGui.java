/*
 * Created by JFormDesigner on Sun May 12 14:39:54 CST 2024
 */

package com.gxnzd.qht.view.gui;

import java.awt.event.*;

import com.gxnzd.qht.service.impl.AdministratorServiceImpl;
import com.gxnzd.qht.service.impl.StudentServiceImpl;
import com.gxnzd.qht.service.impl.TeacherServiceImpl;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author 覃
 */
public class LoginForGui extends JFrame {
    public LoginForGui() {
        initComponents();
    }

    private void loginButtonOnClick(ActionEvent e)  {
        String userName = inputUserName.getText();
        String passWord = inputPassWord.getText();
        // 防止空指针异常
        if (userName == null || "".equals(userName) || passWord == null || "".equals(passWord)){
            JOptionPane.showMessageDialog(this, "用户名及密码都要输入哦！");
            return;
        }

        String loginType = (String) checkBoxLoginType.getSelectedItem();
        if ("学生".equals(loginType)) {
            try {
                studentLogin(userName, passWord);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if ("教师".equals(loginType)) {
            try {
                teacherLogin(userName, passWord);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if ("管理员".equals(loginType)) {
            adminLogin(userName, passWord);
        }
    }
    private void adminLogin(String userName, String passWord) {
        AdministratorServiceImpl adminService = new AdministratorServiceImpl();
        try {
            boolean res = adminService.login(userName, passWord);
            if (res) {
                JOptionPane.showMessageDialog(this, "登录成功");
            } else {
                JOptionPane.showMessageDialog(this, "登录失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    private void teacherLogin(String userName, String passWord) throws Exception {
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        boolean res = teacherService.login(userName, passWord);
        if (res) {
            JOptionPane.showMessageDialog(this, "登录成功");
        } else {
            JOptionPane.showMessageDialog(this, "登录失败");
        }
    }
    public void studentLogin(String userName, String passWord) throws Exception {
        StudentServiceImpl studentService = new StudentServiceImpl();
        boolean res = studentService.login(userName, passWord);
        if (res) {
            JOptionPane.showMessageDialog(this, "登录成功");
        } else {
            JOptionPane.showMessageDialog(this, "登录失败");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        inputUserName = new JTextField();
        label2 = new JLabel();
        inputPassWord = new JTextField();
        buttonLogin = new JButton();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        checkBoxLoginType = new JComboBox<>();
        label6 = new JLabel();
        //======== this ========
        setTitle("\u884c\u77e5\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf -\u767b\u5f55 by\u8983\u60e0\u901a");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7528 \u6237\u540d");
        contentPane.add(label1);
        label1.setBounds(30, 70, 55, 15);
        contentPane.add(inputUserName);
        inputUserName.setBounds(100, 65, 130, 30);

        //---- label2 ----
        label2.setText("\u5bc6  \u7801");
        contentPane.add(label2);
        label2.setBounds(30, 130, 40, 15);
        contentPane.add(inputPassWord);
        inputPassWord.setBounds(100, 115, 130, 30);

        //---- buttonLogin ----
        buttonLogin.setText("\u767b\u5f55");
        buttonLogin.addActionListener(e -> loginButtonOnClick(e));
        contentPane.add(buttonLogin);
        buttonLogin.setBounds(new Rectangle(new Point(225, 190), buttonLogin.getPreferredSize()));

        //---- label3 ----
        label3.setText("---\u6b22\u8fce\u4f7f\u7528\u884c\u77e5\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf---");
        label3.setForeground(Color.red);
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(100, 20), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText("\u5fd8\u8bb0\u5bc6\u7801\uff1f");
        label4.setForeground(Color.blue);
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(250, 125), label4.getPreferredSize()));

        //---- label5 ----
        label5.setText("\u7528\u6237\u540d:\u6559\u5e08\u4e3a\u5de5\u53f7\uff0c\u5b66\u751f\u4e3a\u5b66\u53f7\u3002");
        contentPane.add(label5);
        label5.setBounds(235, 70, 195, label5.getPreferredSize().height);

        //---- checkBoxLoginType ----
        checkBoxLoginType.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u5b66\u751f",
            "\u6559\u5e08",
            "\u7ba1\u7406\u5458"
        }));
        contentPane.add(checkBoxLoginType);
        checkBoxLoginType.setBounds(105, 190, 110, checkBoxLoginType.getPreferredSize().height);

        //---- label6 ----
        label6.setText("\u767b\u5f55\u7c7b\u578b");
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(30, 195), label6.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(420, 285));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField inputUserName;
    private JLabel label2;
    private JTextField inputPassWord;
    private JButton buttonLogin;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JComboBox<String> checkBoxLoginType;
    private JLabel label6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


    public static void main(String[] args) {
        new LoginForGui().setVisible(true);
    }
}


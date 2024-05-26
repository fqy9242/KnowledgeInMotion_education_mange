/*
 * Created by JFormDesigner on Sun May 26 12:14:31 CST 2024
 */

package cn.qht2005.www.view.gui;

import java.awt.*;
import javax.swing.*;

/**
 * @author 覃
 */
public class ForgotPassWord extends JDialog {
    // 用户ID
    private String usrName;
    public ForgotPassWord(Window owner, String userName) {
        super(owner);
        initComponents();
        this.usrName = userName;
        init();
    }
    // 初始化
    private void init(){
        inputUserName.setText(usrName);
    }
    // 提交找回密码
    private void findPassWord(){

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        inputUserName = new JTextField();
        inputName = new JTextField();
        inputPhoneNumber = new JTextField();
        inputNewPassWord = new JPasswordField();
        passwordField2 = new JPasswordField();
        bottomSubmit = new JButton();
        radioButtonStudent = new JRadioButton();
        radioButtonTeacher = new JRadioButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8d26\u53f7:");
        contentPane.add(label1);
        label1.setBounds(5, 20, label1.getPreferredSize().width, 12);

        //---- label2 ----
        label2.setText("\u59d3\u540d:");
        contentPane.add(label2);
        label2.setBounds(5, 60, 29, 17);

        //---- label3 ----
        label3.setText("\u624b\u673a\u53f7:");
        contentPane.add(label3);
        label3.setBounds(5, 100, 55, 17);

        //---- label4 ----
        label4.setText("\u65b0\u5bc6\u7801:");
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(5, 145), label4.getPreferredSize()));

        //---- label5 ----
        label5.setText("\u786e\u8ba4\u5bc6\u7801:");
        contentPane.add(label5);
        label5.setBounds(5, 180, 65, 17);
        contentPane.add(inputUserName);
        inputUserName.setBounds(65, 10, 110, 30);
        contentPane.add(inputName);
        inputName.setBounds(65, 50, 110, 30);
        contentPane.add(inputPhoneNumber);
        inputPhoneNumber.setBounds(65, 95, 110, 30);
        contentPane.add(inputNewPassWord);
        inputNewPassWord.setBounds(65, 135, 105, 30);
        contentPane.add(passwordField2);
        passwordField2.setBounds(65, 170, 105, 30);

        //---- bottomSubmit ----
        bottomSubmit.setText("\u63d0\u4ea4");
        contentPane.add(bottomSubmit);
        bottomSubmit.setBounds(new Rectangle(new Point(105, 220), bottomSubmit.getPreferredSize()));

        //---- radioButtonStudent ----
        radioButtonStudent.setText("\u5b66\u751f");
        contentPane.add(radioButtonStudent);
        radioButtonStudent.setBounds(new Rectangle(new Point(5, 225), radioButtonStudent.getPreferredSize()));

        //---- radioButtonTeacher ----
        radioButtonTeacher.setText("\u6559\u5e08");
        contentPane.add(radioButtonTeacher);
        radioButtonTeacher.setBounds(55, 225, 55, 21);

        contentPane.setPreferredSize(new Dimension(205, 285));
        pack();
        setLocationRelativeTo(getOwner());

        //---- userType ----
        var userType = new ButtonGroup();
        userType.add(radioButtonStudent);
        userType.add(radioButtonTeacher);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField inputUserName;
    private JTextField inputName;
    private JTextField inputPhoneNumber;
    private JPasswordField inputNewPassWord;
    private JPasswordField passwordField2;
    private JButton bottomSubmit;
    private JRadioButton radioButtonStudent;
    private JRadioButton radioButtonTeacher;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

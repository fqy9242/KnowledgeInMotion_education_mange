/*
 * Created by JFormDesigner on Sun May 12 14:39:54 CST 2024
 */

package cn.qht2005.www.view.gui.main;

import java.awt.event.*;

import cn.qht2005.www.service.impl.AdministratorServiceImpl;
import cn.qht2005.www.service.impl.StudentServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import cn.qht2005.www.view.gui.ForgotPassWord;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.util.prefs.Preferences;
import javax.swing.*;

/**
 * @author 覃
 */
public class LoginForGui extends JFrame {
    // 账户配置文件
    private Preferences pref;
    public LoginForGui() {
        initComponents();
        init();
    }
    // 初始化
    private void init(){
        pref = Preferences.userNodeForPackage(LoginForGui.class);
        // 读取账户信息
        inputUserName.setText(pref.get("userName", ""));
        inputPassWord.setText(pref.get("passWord", ""));
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
            new TeacherControllerGui(userName).setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "登录失败");
        }
    }
    public void studentLogin(String userName, String passWord) throws Exception {
        StudentServiceImpl studentService = new StudentServiceImpl();
        boolean res = studentService.login(userName, passWord);
        if (res) {
            // JOptionPane.showMessageDialog(this, "登录成功");
            // 跳转到学生界面
            new StudentControllerGui(userName).setVisible(true);
//            new StudentControllerGui(userName).setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "登录失败");
        }
    }
    // 登录按钮被点击
    private void loginButtonOnClick(ActionEvent e) {
        String userName = inputUserName.getText();
        String passWord = inputPassWord.getText();
        // 防止空指针异常
        if (userName == null || userName.isEmpty() || passWord == null || passWord.isEmpty()){
            JOptionPane.showMessageDialog(this, "用户名及密码都要输入哦！");
            return;
        }
        // 判断用户是否勾选了记住账户 若，则保存。
        if (checkBoxSaveAccount.isSelected()){
            pref.put("userName", userName);
            pref.put("passWord", passWord);
        }else {
            // 否则，清空保存过的账户信息
            pref.remove("userName");
            pref.remove("passWord");
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
    // 找回密码按钮被点击
    private void label4MouseClicked() {
        new ForgotPassWord(this, inputUserName.getText()).setVisible(true);
    }


    // 忘记密码标签被点击
    private void labelFindPassWordMouseClicked(MouseEvent e) {
        new ForgotPassWord(this, inputUserName.getText()).setVisible(true);

    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        inputUserName = new JTextField();
        label2 = new JLabel();
        buttonLogin = new JButton();
        label3 = new JLabel();
        labelFindPassWord = new JLabel();
        label5 = new JLabel();
        checkBoxLoginType = new JComboBox<>();
        label6 = new JLabel();
        inputPassWord = new JPasswordField();
        checkBoxSaveAccount = new JCheckBox();

        //======== this ========
        setTitle("\u884c\u77e5\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf -\u767b\u5f55    by\u8983\u60e0\u901a");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
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
        label2.setBounds(30, 135, 40, 15);

        //---- buttonLogin ----
        buttonLogin.setText("\u767b\u5f55");
        buttonLogin.addActionListener(e -> loginButtonOnClick(e));
        contentPane.add(buttonLogin);
        buttonLogin.setBounds(new Rectangle(new Point(235, 190), buttonLogin.getPreferredSize()));

        //---- label3 ----
        label3.setText("---\u6b22\u8fce\u4f7f\u7528\u884c\u77e5\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf---");
        label3.setForeground(Color.red);
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(100, 20), label3.getPreferredSize()));

        //---- labelFindPassWord ----
        labelFindPassWord.setText("\u5fd8\u8bb0\u5bc6\u7801\uff1f");
        labelFindPassWord.setForeground(Color.blue);
        labelFindPassWord.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                labelFindPassWordMouseClicked(e);
            }
        });
        contentPane.add(labelFindPassWord);
        labelFindPassWord.setBounds(new Rectangle(new Point(320, 140), labelFindPassWord.getPreferredSize()));

        //---- label5 ----
        label5.setText("\u7528\u6237\u540d:\u6559\u5e08\u4e3a\u5de5\u53f7\uff0c\u5b66\u751f\u4e3a\u5b66\u53f7\u3002");
        contentPane.add(label5);
        label5.setBounds(230, 70, 195, 20);

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
        contentPane.add(inputPassWord);
        inputPassWord.setBounds(100, 130, 130, 30);

        //---- checkBoxSaveAccount ----
        checkBoxSaveAccount.setText("\u8bb0\u4f4f\u8d26\u6237");
        checkBoxSaveAccount.setSelected(true);
        contentPane.add(checkBoxSaveAccount);
        checkBoxSaveAccount.setBounds(new Rectangle(new Point(240, 140), checkBoxSaveAccount.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(420, 265));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField inputUserName;
    private JLabel label2;
    private JButton buttonLogin;
    private JLabel label3;
    private JLabel labelFindPassWord;
    private JLabel label5;
    private JComboBox<String> checkBoxLoginType;
    private JLabel label6;
    private JPasswordField inputPassWord;
    private JCheckBox checkBoxSaveAccount;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


    public static void main(String[] args) {
        // 使用FlatLaf皮肤包
        FlatLightLaf.install();
        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("皮肤包导入失败！");
        }
        new LoginForGui().setVisible(true);
    }

}


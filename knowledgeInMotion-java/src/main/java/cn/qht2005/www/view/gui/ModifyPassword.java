/*
 * Created by JFormDesigner on Thu May 16 18:17:06 CST 2024
 */

package cn.qht2005.www.view.gui;

import java.awt.event.*;

import cn.qht2005.www.pojo.people.People;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.pojo.people.Teacher;
import cn.qht2005.www.service.impl.StudentServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import cn.qht2005.www.view.gui.main.LoginForGui;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import javax.swing.*;

/**
 * @author 覃
 */
public class ModifyPassword extends JFrame {
    // 学生对象
    private Student student = null;
    // 教师对象
    private Teacher teacher = null;

    public <T extends People> ModifyPassword(T people) {
        if ("Student".equals(people.getClass().getSimpleName())){
            // 也就是传进来的是个学生类型的对象
            student = (Student) people;
        }else{
            teacher = (Teacher) people;
        }
        initComponents();
    }
    // 修改密码
    private void modifyPassWord() throws Exception {
        if (inputNowPassWord.getPassword().length == 0){
            JOptionPane.showMessageDialog(this, "请输入原密码！");
            return;
		} else if (inputNewPassWord.getPassword().length == 0){
            JOptionPane.showMessageDialog(this, "请输入新密码！");
            return;
        }else if (inputNewPassWordRe.getPassword().length == 0){
            JOptionPane.showMessageDialog(this, "请再次输入新密码！");
            return;
        }else if (!new String(inputNewPassWord.getPassword()).equals(new String(inputNewPassWordRe.getPassword()))){
            JOptionPane.showMessageDialog(this, "两次输入的新密码不一致！");
            return;
        }/*else if (!(new StudentServiceImpl().login(id, new String(inputNowPassWord.getPassword())))){
            JOptionPane.showMessageDialog(this, "原密码错误！");
            return;
        }*/
        // 开始那啥
        // 没报错就是修改成功（大概） 报错就是修改失败
        try {
            boolean row;
            if (student != null){
                // 学生用户修改密码
                row = new StudentServiceImpl().modifyPassWord(student.getStudentId(), new String(inputNowPassWord.getPassword()),
						new String(inputNewPassWord.getPassword())) == 1;
            }else{
                // 教师用户修改密码
                row = new TeacherServiceImpl().modifyPassWord(teacher.getTeacherId(), new String((inputNowPassWord.getPassword())),
                        new String(inputNewPassWord.getPassword()));
            }
            if (!row){
                JOptionPane.showMessageDialog(this, "修改失败！请再次检查重试！");
                return;
            }
            JOptionPane.showMessageDialog(this, "修改成功！");
            JOptionPane.showMessageDialog(this, "登录已失效，请重新登录！");
            {
                // 关闭所有窗口
                Window[] windows = getWindows();
                for (Window window : windows) {
                    window.dispose();
                }
            }
            new LoginForGui().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "修改失败！请再次检查重试！");
		}
    }
    // 确认修改按钮被点击
    private void button1MouseClicked(MouseEvent e) {
        try {
            modifyPassWord();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        button1 = new JButton();
        inputNowPassWord = new JPasswordField();
        inputNewPassWord = new JPasswordField();
        inputNewPassWordRe = new JPasswordField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("\u4fee\u6539\u5bc6\u7801");
        setAlwaysOnTop(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);
        }
        contentPane.add(panel1);
        panel1.setBounds(new Rectangle(new Point(0, 0), panel1.getPreferredSize()));

        //---- label1 ----
        label1.setText("\u539f\u5bc6\u7801");
        contentPane.add(label1);
        label1.setBounds(0, 15, 38, 17);

        //---- label2 ----
        label2.setText("\u65b0\u5bc6\u7801\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(0, 50, 70, 17);

        //---- label3 ----
        label3.setText("\u786e\u8ba4\u65b0\u5bc6\u7801");
        contentPane.add(label3);
        label3.setBounds(0, 85, 70, 17);

        //---- button1 ----
        button1.setText("\u786e\u8ba4\u4fee\u6539");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(45, 125, 95, 30);
        contentPane.add(inputNowPassWord);
        inputNowPassWord.setBounds(75, 10, 95, 30);
        contentPane.add(inputNewPassWord);
        inputNewPassWord.setBounds(75, 45, 95, 30);
        contentPane.add(inputNewPassWordRe);
        inputNewPassWordRe.setBounds(75, 80, 95, 30);

        contentPane.setPreferredSize(new Dimension(205, 205));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton button1;
    private JPasswordField inputNowPassWord;
    private JPasswordField inputNewPassWord;
    private JPasswordField inputNewPassWordRe;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


    public static void main(String[] args) {
        // 使用FlatLaf皮肤包
        FlatLightLaf.install();
        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("皮肤包导入失败！");
        }
        Student s =  new Student();
        s.setStudentId("2331020130229");
        new ModifyPassword(s).setVisible(true);
    }
}

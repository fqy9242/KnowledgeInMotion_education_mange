/*
 * Created by JFormDesigner on Fri May 31 22:43:20 CST 2024
 */

package cn.qht2005.www.view.gui;

import java.awt.event.*;
import cn.qht2005.www.pojo.Enumeration.UserType;
import cn.qht2005.www.pojo.people.People;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.pojo.people.Teacher;
import cn.qht2005.www.service.impl.CollegeServiceImpl;
import cn.qht2005.www.service.impl.StudentServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;

/**
 * @author 覃
 */
public class UpdateStudentAndTeacher extends JDialog {
    // 学生用户
    private Student student = null;
    // 教师用户
    private Teacher teacher = null;

    public UpdateStudentAndTeacher(Window owner, People people) {
        super(owner);
        initComponents();
        try {
            init(people);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 自定义的初始化
    private void init(People people) throws Exception {
        showCollegeListToSelectBox();
        // 判断传进来的是教师对象还是学生对象
        if (people.getClass().toString().contains("Student")){
            this.student = (Student) people;
        }else{
            this.teacher = (Teacher) people;
        }

        if (student != null) {

            // 为那些文本框赋值
            inputClass.setText(student.getClassId());
            inputName.setText(student.getName());
            inputAge.setText(String.valueOf(student.getAge()));
            selectSex.setSelectedIndex(student.getSex() == 1 ? 0 : 1);
            inputPhoneNumber.setText(student.getPhoneNumber());
            inputPassword.setText(student.getPassWord());
            inputAddressAndPosition.setText(student.getAddress());
            selectBoxCollege.setSelectedItem(new CollegeServiceImpl().getCollegeNameById(student.getCollegeId()));

        } else {
            label2.setText("管理班级");
            label8.setText("职位");
            inputAddressAndPosition.setSize(85, 30);
            // 为那些文本框赋值
            inputClass.setText(teacher.getMangeClassId());
            inputName.setText(teacher.getName());
            inputAge.setText(String.valueOf(teacher.getAge()));
            selectSex.setSelectedIndex(teacher.getSex() == 1 ? 0 : 1);
            inputPhoneNumber.setText(teacher.getPhoneNumber());
            inputPassword.setText(teacher.getPassWord());
            inputAddressAndPosition.setText(teacher.getPosition());
            selectBoxCollege.setSelectedItem(new CollegeServiceImpl().getCollegeNameById(teacher.getCollegeId()));

        }
    }
    // 提交修改按钮被点击
    private void buttonModifyMouseClicked(MouseEvent e) {
        if (student != null){
            try {
                upDateStudent();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (teacher != null) {
            try {
                upDateTeacher();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        selectBoxCollege = new JComboBox();
        label2 = new JLabel();
        inputClass = new JTextField();
        label3 = new JLabel();
        inputName = new JTextField();
        label4 = new JLabel();
        inputAge = new JTextField();
        label5 = new JLabel();
        selectSex = new JComboBox<>();
        label6 = new JLabel();
        inputPhoneNumber = new JTextField();
        label7 = new JLabel();
        inputPassword = new JTextField();
        label8 = new JLabel();
        inputAddressAndPosition = new JTextField();
        buttonModify = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5b66\u9662");
        contentPane.add(label1);
        label1.setBounds(20, 25, 40, label1.getPreferredSize().height);
        contentPane.add(selectBoxCollege);
        selectBoxCollege.setBounds(73, 20, 107, selectBoxCollege.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u73ed\u7ea7");
        contentPane.add(label2);
        label2.setBounds(20, 60, 50, label2.getPreferredSize().height);
        contentPane.add(inputClass);
        inputClass.setBounds(73, 60, 85, 30);

        //---- label3 ----
        label3.setText("\u59d3\u540d");
        contentPane.add(label3);
        label3.setBounds(20, 95, 45, label3.getPreferredSize().height);
        contentPane.add(inputName);
        inputName.setBounds(73, 95, 85, 30);

        //---- label4 ----
        label4.setText("\u5e74\u9f84");
        contentPane.add(label4);
        label4.setBounds(20, 140, 55, label4.getPreferredSize().height);
        contentPane.add(inputAge);
        inputAge.setBounds(73, 135, 85, 30);

        //---- label5 ----
        label5.setText("\u6027\u522b");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(20, 185), label5.getPreferredSize()));

        //---- selectSex ----
        selectSex.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u7537",
            "\u5973"
        }));
        contentPane.add(selectSex);
        selectSex.setBounds(new Rectangle(new Point(73, 180), selectSex.getPreferredSize()));

        //---- label6 ----
        label6.setText("\u8054\u7cfb\u7535\u8bdd");
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(20, 230), label6.getPreferredSize()));
        contentPane.add(inputPhoneNumber);
        inputPhoneNumber.setBounds(73, 225, 85, 30);

        //---- label7 ----
        label7.setText("\u767b\u5f55\u5bc6\u7801");
        contentPane.add(label7);
        label7.setBounds(new Rectangle(new Point(20, 270), label7.getPreferredSize()));
        contentPane.add(inputPassword);
        inputPassword.setBounds(73, 265, 85, 30);

        //---- label8 ----
        label8.setText("\u5bb6\u5ead\u4f4f\u5740");
        contentPane.add(label8);
        label8.setBounds(new Rectangle(new Point(20, 310), label8.getPreferredSize()));
        contentPane.add(inputAddressAndPosition);
        inputAddressAndPosition.setBounds(73, 305, 197, inputAddressAndPosition.getPreferredSize().height);

        //---- buttonModify ----
        buttonModify.setText("\u63d0\u4ea4\u4fee\u6539");
        buttonModify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonModifyMouseClicked(e);
            }
        });
        contentPane.add(buttonModify);
        buttonModify.setBounds(new Rectangle(new Point(185, 20), buttonModify.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(300, 375));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // 修改学生信息
    private void upDateStudent() throws Exception {
        // 创建一个学生对象然后封装
        Student stu = new Student();
        // 获取学院名
        String collegeName = Objects.requireNonNull(selectBoxCollege.getSelectedItem()).toString();
        if (collegeName !=null){
            // 获取学院id
            Integer collegeId = new CollegeServiceImpl().getCollegeIdByName(collegeName);
            stu.setCollegeId(collegeId);
        }
        // 获取班级id
        if (inputClass != null){
            String classId = inputClass.getText();
            stu.setClassId(classId);
        }
        // 获取姓名
        String name = inputName.getText();
        stu.setName(name);
        // 获取年龄
        if (inputAge.getText() != null){
            Integer age= Integer.valueOf(inputAge.getText());
            stu.setAge(age);
        }
        // 获取性别
        Short sex = (short) (selectSex.getSelectedIndex() == 0 ? 1 : 2);
        stu.setSex(sex);
        // 获取手机号
        String phoneNumber = inputPhoneNumber.getText();
        stu.setPhoneNumber(phoneNumber);
        // 获取登录密码
        String passWord = inputPassword.getText();
        stu.setPassWord(passWord);
        // 获取家庭住址
        String address = inputAddressAndPosition.getText();
        stu.setAddress(address);
        // 调用修改学生信息的方法
        Student res = new StudentServiceImpl().modifyStudentByDynamic(student.getStudentId(), stu);
        if (res != null){
            JOptionPane.showMessageDialog(this, "修改成功");
        }else{
            JOptionPane.showMessageDialog(this, "修改失败");
        }

    }
    // 修改教师信息
    private void upDateTeacher() throws Exception {
        // 创建一个教师对象然后封装
        Teacher tea = new Teacher();
        tea.setTeacherId(teacher.getTeacherId());
        // 获取学院名
        String collegeName = Objects.requireNonNull(selectBoxCollege.getSelectedItem()).toString();
        if (collegeName !=null){
            // 获取学院id
            Integer collegeId = new CollegeServiceImpl().getCollegeIdByName(collegeName);
            tea.setCollegeId(collegeId);
        }
        // 获取管理班级id
        if (inputClass != null){
            String classId = inputClass.getText();
            tea.setMangeClassId(classId);
        }
        // 获取姓名
        String name = inputName.getText();
        tea.setName(name);
        // 获取年龄
        if (inputAge.getText() != null){
            Integer age= Integer.valueOf(inputAge.getText());
            tea.setAge(age);
        }
        // 获取性别
        Short sex = (short) (selectSex.getSelectedIndex() == 0 ? 1 : 2);
        tea.setSex(sex);
        // 获取手机号
        String phoneNumber = inputPhoneNumber.getText();
        tea.setPhoneNumber(phoneNumber);
        // 获取登录密码
        String passWord = inputPassword.getText();
        tea.setPassWord(passWord);
        // 获取职位
        String position = inputAddressAndPosition.getText();
        tea.setPosition(position);
        // 调用修改学生信息的方法
        boolean res = new TeacherServiceImpl().setTeacherByTeacher(tea);
        if (res){
            JOptionPane.showMessageDialog(this, "修改成功");
        }else{
            JOptionPane.showMessageDialog(this, "修改失败");
        }

    }
    // 获取所有学院，然后展示到列表框
    private void showCollegeListToSelectBox() {
        try {
            new CollegeServiceImpl().getAllCollege().forEach(college -> selectBoxCollege.addItem(college.getCollegeName()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JComboBox selectBoxCollege;
    private JLabel label2;
    private JTextField inputClass;
    private JLabel label3;
    private JTextField inputName;
    private JLabel label4;
    private JTextField inputAge;
    private JLabel label5;
    private JComboBox<String> selectSex;
    private JLabel label6;
    private JTextField inputPhoneNumber;
    private JLabel label7;
    private JTextField inputPassword;
    private JLabel label8;
    private JTextField inputAddressAndPosition;
    private JButton buttonModify;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

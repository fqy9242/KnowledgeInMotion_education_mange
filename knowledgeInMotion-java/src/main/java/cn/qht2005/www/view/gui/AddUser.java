/*
 * Created by JFormDesigner on Thu May 30 16:41:45 CST 2024
 */

package cn.qht2005.www.view.gui;

import java.awt.event.*;

import cn.qht2005.www.pojo.Enumeration.UserType;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.pojo.people.Teacher;
import cn.qht2005.www.service.impl.AdministratorServiceImpl;
import cn.qht2005.www.service.impl.CollegeServiceImpl;
import cn.qht2005.www.util.IdUtil;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import javax.swing.*;

/**
 * @author 覃
 */
public class AddUser extends JDialog {
    // 用户类型
    private final UserType USER_TYPE;
    public AddUser(Window owner, UserType userType) {
        super(owner);
        initComponents();
        this.USER_TYPE  = userType;
        init();
    }
    // 自定义的初始化
    private void init() {
        // 显示学院列表到下拉框
        try {
            showCollegeListToSelectBox();
            if (USER_TYPE == UserType.STUDENT){
                // 设置标题
                setTitle("添加学生");
                // 设置班级输入框可见
                label2.setVisible(true);
                inputClassId.setVisible(true);
            } else if (USER_TYPE == UserType.TEACHER) {
                // 设置标题
                setTitle("添加教师");
                // 设置班级输入框不可见
                label2.setVisible(false);
                inputClassId.setVisible(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 显示学院列表到下拉框
    private void showCollegeListToSelectBox() throws Exception {
        new CollegeServiceImpl().getAllCollege().forEach(college -> selectBoxCollege.addItem(college.getCollegeName()));
    }
    // 添加按钮被点击
    private void buttonAddMouseClicked(MouseEvent e) {
        if (USER_TYPE == UserType.STUDENT){
            // 添加学生
            try {
                Student student = addStudent();
                if (student != null) {
                    JOptionPane.showMessageDialog(this, "添加成功！\n学号：" + student.getStudentId());
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (USER_TYPE == UserType.TEACHER) {
            // 添加教师
            try {
                Teacher teacher = addTeacher();
                if (teacher != null) {
                    JOptionPane.showMessageDialog(this, "添加成功！\n工号：" + teacher.getTeacherId());
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        inputName = new JTextField();
        label2 = new JLabel();
        inputClassId = new JTextField();
        label3 = new JLabel();
        selectBoxCollege = new JComboBox();
        selectBoxSex = new JComboBox<>();
        label4 = new JLabel();
        buttonAdd = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u59d3\u540d");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(5, 15), label1.getPreferredSize()));
        contentPane.add(inputName);
        inputName.setBounds(39, 10, 95, 30);

        //---- label2 ----
        label2.setText("\u73ed\u7ea7");
        contentPane.add(label2);
        label2.setBounds(5, 50, 25, 17);
        contentPane.add(inputClassId);
        inputClassId.setBounds(40, 45, 95, 30);

        //---- label3 ----
        label3.setText("\u5b66\u9662");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(5, 115), label3.getPreferredSize()));
        contentPane.add(selectBoxCollege);
        selectBoxCollege.setBounds(39, 115, 111, selectBoxCollege.getPreferredSize().height);

        //---- selectBoxSex ----
        selectBoxSex.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u7537",
            "\u5973"
        }));
        contentPane.add(selectBoxSex);
        selectBoxSex.setBounds(39, 80, 85, 30);

        //---- label4 ----
        label4.setText("\u6027\u522b");
        contentPane.add(label4);
        label4.setBounds(5, 85, 25, 17);

        //---- buttonAdd ----
        buttonAdd.setText("\u6dfb\u52a0");
        buttonAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonAddMouseClicked(e);
            }
        });
        contentPane.add(buttonAdd);
        buttonAdd.setBounds(new Rectangle(new Point(25, 165), buttonAdd.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(160, 235));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField inputName;
    private JLabel label2;
    private JTextField inputClassId;
    private JLabel label3;
    private JComboBox selectBoxCollege;
    private JComboBox<String> selectBoxSex;
    private JLabel label4;
    private JButton buttonAdd;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    // 添加教师
    private Teacher addTeacher() throws Exception {
         // 姓名
       String name = inputName.getText();
         // 学院名
       String collegeName = (String) selectBoxCollege.getSelectedItem();
         // 性别
       Integer sex = selectBoxSex.getSelectedIndex() == 0 ? 1 : 2;
         // 学院id
       Integer collegeId = new CollegeServiceImpl().getCollegeIdByName(collegeName);
         // 生成教师工号
       String id = IdUtil.generateTeacherId(collegeId);
       Teacher teacher = new Teacher();
       teacher.setTeacherId(id);
       teacher.setName(name);
       teacher.setSex(sex.shortValue());
       teacher.setCollegeId(collegeId);
        boolean b = new AdministratorServiceImpl().addTeacher(teacher);
        if (b){
            return teacher;
        }else{
            return null;
        }

    }
    // 添加学生
    private Student addStudent() throws Exception {
        // 获取学生姓名
        String name = inputName.getText();
        // 获取学生班级id
        String classId = inputClassId.getText();
        // 获取学院名
        String collegeName = (String) selectBoxCollege.getSelectedItem();
        // 获取学院id
        Integer collegeId = new CollegeServiceImpl().getCollegeIdByName(collegeName);
        // 获取学生性别
        int sex = selectBoxSex.getSelectedIndex() == 0 ? 1 : 2;
        // 生成学号
        String id = IdUtil.generateStudentId(collegeId, classId);
        // 创建一个学生对象
        Student student = new Student();
        student.setStudentId(id);
        student.setName(name);
        student.setClassId(classId);
        student.setCollegeId(collegeId);
        student.setSex((short) sex);
        // 添加学生
        boolean b = new AdministratorServiceImpl().addStudent(student);
        if (b){
            return student;
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        // 使用FlatLaf皮肤包
        FlatLightLaf.install();
        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("皮肤包导入失败！");
        }
        new AddUser(null, UserType.TEACHER).setVisible(true);
    }
}

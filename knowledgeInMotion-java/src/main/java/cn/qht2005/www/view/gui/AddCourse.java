/*
 * Created by JFormDesigner on Sat Jun 01 09:59:02 CST 2024
 */

package cn.qht2005.www.view.gui;

import java.awt.event.*;
import cn.qht2005.www.pojo.Course;
import cn.qht2005.www.service.impl.AdministratorServiceImpl;
import cn.qht2005.www.service.impl.CollegeServiceImpl;

import java.awt.*;
import javax.swing.*;

/**
 * @author 覃
 */
public class AddCourse extends JDialog {
    public AddCourse(Window owner) {
        super(owner);
        initComponents();
        try {
            getCollegeList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 添加课程
    private void addCourse() throws Exception {
        // 获取课程名
        String courseName = inputCourseName.getText();
        // 获取学院名
        String collegeName = (String) selectCourseCollege.getSelectedItem();
        // 获取学院id
        int collegeId = new CollegeServiceImpl().getCollegeIdByName(collegeName);
        // 创建一个课程对象
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCollegeId(collegeId);
        // 添加课程
        boolean res = new AdministratorServiceImpl().addCourse(course);
        if (res){
            JOptionPane.showMessageDialog(this, "添加成功");
        }else{
            JOptionPane.showMessageDialog(this, "添加失败");
        }
    }
    // 添加课程按钮点击事件
    private void buttonAddCourseMouseClicked(MouseEvent e) {
        try {
            addCourse();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    // 获取学院列表并展示到下拉框
    private void getCollegeList() throws Exception {
        // 获取所有学院
        var colleges = new CollegeServiceImpl().getAllCollege();
        // 遍历学院列表
        for (var college : colleges){
            // 添加学院名到下拉框
            selectCourseCollege.addItem(college.getCollegeName());
        }
    }
    // 窗口关闭事件
    private void thisWindowClosed(WindowEvent e) {
        // 关闭窗口
        this.dispose();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        inputCourseName = new JTextField();
        buttonAddCourse = new JButton();
        label2 = new JLabel();
        selectCourseCollege = new JComboBox();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8bfe\u7a0b\u540d\u79f0");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(5, 15), label1.getPreferredSize()));
        contentPane.add(inputCourseName);
        inputCourseName.setBounds(60, 10, 100, inputCourseName.getPreferredSize().height);

        //---- buttonAddCourse ----
        buttonAddCourse.setText("\u6dfb\u52a0\u8bfe\u7a0b");
        buttonAddCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonAddCourseMouseClicked(e);
            }
        });
        contentPane.add(buttonAddCourse);
        buttonAddCourse.setBounds(new Rectangle(new Point(165, 10), buttonAddCourse.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u5f00\u8bbe\u5b66\u9662");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(5, 70), label2.getPreferredSize()));
        contentPane.add(selectCourseCollege);
        selectCourseCollege.setBounds(60, 65, 95, selectCourseCollege.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(255, 140));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField inputCourseName;
    private JButton buttonAddCourse;
    private JLabel label2;
    private JComboBox selectCourseCollege;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

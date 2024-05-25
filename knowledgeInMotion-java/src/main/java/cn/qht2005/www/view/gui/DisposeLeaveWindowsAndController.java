/*
 * Created by JFormDesigner on Sat May 25 23:08:15 CST 2024
 */

package cn.qht2005.www.view.gui;

import cn.qht2005.www.pojo.Leave;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.service.impl.StudentServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

/**
 * @author 覃
 */
public class DisposeLeaveWindowsAndController extends JDialog {
    // 请假对象
    private final Leave leave;
    public DisposeLeaveWindowsAndController(Window owner, Leave leave) {
        super(owner);
        this.leave = leave;
        initComponents();
    }
    // 初始化
    private void init() throws Exception {
        // 格式化时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // 获取学生对象
        Student student = new TeacherServiceImpl().getStudentById(leave.getUserId());
        labelForName.setText(student.getName());
        labelForUserId.setText(student.getStudentId());
        labelForLeaveType.setText(leave.getLeaveType() == 1? "事假" : leave.getLeaveType() == 2? "病假" : "其他");
        labelForLeaveStartTime.setText(leave.getLeaveStartTime().format(dateTimeFormatter));
        labelForLeaveEndTime.setText(leave.getLeaveEndTime().format(dateTimeFormatter));
        textAreaForApplyReason.setText(leave.getLeaveReason());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        textAreaResponse = new JTextArea();
        buttonCommit = new JButton();
        radioButtonAgree = new JRadioButton();
        radioButtonResulse = new JRadioButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        labelForName = new JLabel();
        labelForUserId = new JLabel();
        label6 = new JLabel();
        labelForLeaveType = new JLabel();
        label8 = new JLabel();
        labelForLeaveStartTime = new JLabel();
        label10 = new JLabel();
        labelForLeaveEndTime = new JLabel();
        scrollPane2 = new JScrollPane();
        textAreaForApplyReason = new JTextArea();
        label12 = new JLabel();

        //======== this ========
        setTitle("\u6279\u5047");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textAreaResponse);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(25, 50, 270, 135);

        //---- buttonCommit ----
        buttonCommit.setText("\u63d0\u4ea4");
        contentPane.add(buttonCommit);
        buttonCommit.setBounds(new Rectangle(new Point(155, 5), buttonCommit.getPreferredSize()));

        //---- radioButtonAgree ----
        radioButtonAgree.setText("\u540c\u610f");
        contentPane.add(radioButtonAgree);
        radioButtonAgree.setBounds(new Rectangle(new Point(0, 5), radioButtonAgree.getPreferredSize()));

        //---- radioButtonResulse ----
        radioButtonResulse.setText("\u62d2\u7edd");
        contentPane.add(radioButtonResulse);
        radioButtonResulse.setBounds(75, 5, 48, 21);

        //---- label1 ----
        label1.setText("\u56de\u590d");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(130, 35), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u59d3\u540d");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(0, 195), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u5b66\u53f7");
        contentPane.add(label3);
        label3.setBounds(0, 230, 25, 17);

        //---- labelForName ----
        labelForName.setText("text");
        contentPane.add(labelForName);
        labelForName.setBounds(35, 195, 65, labelForName.getPreferredSize().height);

        //---- labelForUserId ----
        labelForUserId.setText("text");
        contentPane.add(labelForUserId);
        labelForUserId.setBounds(35, 230, 80, labelForUserId.getPreferredSize().height);

        //---- label6 ----
        label6.setText("\u8bf7\u5047\u7c7b\u578b");
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(160, 195), label6.getPreferredSize()));

        //---- labelForLeaveType ----
        labelForLeaveType.setText("text");
        contentPane.add(labelForLeaveType);
        labelForLeaveType.setBounds(new Rectangle(new Point(220, 195), labelForLeaveType.getPreferredSize()));

        //---- label8 ----
        label8.setText("\u8bf7\u5047\u5f00\u59cb\u65f6\u95f4");
        contentPane.add(label8);
        label8.setBounds(new Rectangle(new Point(-5, 255), label8.getPreferredSize()));

        //---- labelForLeaveStartTime ----
        labelForLeaveStartTime.setText("text");
        contentPane.add(labelForLeaveStartTime);
        labelForLeaveStartTime.setBounds(new Rectangle(new Point(70, 255), labelForLeaveStartTime.getPreferredSize()));

        //---- label10 ----
        label10.setText("\u8bf7\u5047\u7ed3\u675f\u65f6\u95f4");
        contentPane.add(label10);
        label10.setBounds(new Rectangle(new Point(-5, 280), label10.getPreferredSize()));

        //---- labelForLeaveEndTime ----
        labelForLeaveEndTime.setText("text");
        contentPane.add(labelForLeaveEndTime);
        labelForLeaveEndTime.setBounds(new Rectangle(new Point(75, 280), labelForLeaveEndTime.getPreferredSize()));

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(textAreaForApplyReason);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(25, 330, 270, 110);

        //---- label12 ----
        label12.setText("\u8bf7\u5047\u7406\u7531");
        contentPane.add(label12);
        label12.setBounds(new Rectangle(new Point(130, 310), label12.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(365, 485));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTextArea textAreaResponse;
    private JButton buttonCommit;
    private JRadioButton radioButtonAgree;
    private JRadioButton radioButtonResulse;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel labelForName;
    private JLabel labelForUserId;
    private JLabel label6;
    private JLabel labelForLeaveType;
    private JLabel label8;
    private JLabel labelForLeaveStartTime;
    private JLabel label10;
    private JLabel labelForLeaveEndTime;
    private JScrollPane scrollPane2;
    private JTextArea textAreaForApplyReason;
    private JLabel label12;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

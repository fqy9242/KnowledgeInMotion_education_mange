/*
 * Created by JFormDesigner on Fri May 31 16:42:28 CST 2024
 */

package cn.qht2005.www.view.gui;

import java.awt.event.*;
import cn.qht2005.www.pojo.Notice;
import cn.qht2005.www.service.impl.AdministratorServiceImpl;

import java.awt.*;
import java.time.LocalDateTime;
import javax.swing.*;

/**
 * @author 覃
 */
public class AddNotice extends JDialog {
    public AddNotice(Window owner) {
        super(owner);
        initComponents();
    }
    // 发布公告按钮被点击
    private void buttonPublishMouseClicked(MouseEvent e) {
        addNotice();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        checkBoxStudent = new JCheckBox();
        checkBoxTeacher = new JCheckBox();
        label3 = new JLabel();
        inputNoaticeTitle = new JTextField();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        textAreaNoticeBody = new JTextArea();
        inputPublisher = new JTextField();
        buttonPublish = new JButton();

        //======== this ========
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u53d1\u6587\u673a\u5173");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(35, 30), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u4e3b\u9001\u673a\u5173");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(35, 70), label2.getPreferredSize()));

        //---- checkBoxStudent ----
        checkBoxStudent.setText("\u5168\u4f53\u5b66\u751f");
        contentPane.add(checkBoxStudent);
        checkBoxStudent.setBounds(new Rectangle(new Point(100, 65), checkBoxStudent.getPreferredSize()));

        //---- checkBoxTeacher ----
        checkBoxTeacher.setText("\u5168\u4f53\u6559\u804c\u5de5");
        contentPane.add(checkBoxTeacher);
        checkBoxTeacher.setBounds(180, 65, 95, 22);

        //---- label3 ----
        label3.setText("\u516c\u544a\u6807\u9898");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(35, 105), label3.getPreferredSize()));
        contentPane.add(inputNoaticeTitle);
        inputNoaticeTitle.setBounds(90, 100, 105, inputNoaticeTitle.getPreferredSize().height);

        //---- label4 ----
        label4.setText("\u516c\u544a\u5185\u5bb9");
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(105, 145), label4.getPreferredSize()));

        //======== scrollPane1 ========
        {

            //---- textAreaNoticeBody ----
            textAreaNoticeBody.setLineWrap(true);
            scrollPane1.setViewportView(textAreaNoticeBody);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 170, 240, 155);

        //---- inputPublisher ----
        inputPublisher.setText("\u7ba1\u7406\u5458");
        contentPane.add(inputPublisher);
        inputPublisher.setBounds(95, 25, 70, inputPublisher.getPreferredSize().height);

        //---- buttonPublish ----
        buttonPublish.setText("\u53d1\u5e03");
        buttonPublish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonPublishMouseClicked(e);
            }
        });
        contentPane.add(buttonPublish);
        buttonPublish.setBounds(new Rectangle(new Point(175, 25), buttonPublish.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(275, 360));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // 发布公告
    private void addNotice() {
        // 获取公告标题
        String noticeTitle = inputNoaticeTitle.getText();
        // 获取公告内容
        String noticeBody = textAreaNoticeBody.getText();
        // 获取发布者
        String noticePublisher = inputPublisher.getText();
        // 获取接收者
        short noticeRecipient = 0;
        if (checkBoxStudent.isSelected()) {
            // 全体学生
            noticeRecipient = 1;
        } else if (checkBoxTeacher.isSelected()) {
            // 全体教职工
            noticeRecipient = 2;
        } else {
            // 没选
            JOptionPane.showMessageDialog(this, "请选择接收者!");
            return;
        }
        // 获取发布时间
        LocalDateTime noticeTime = LocalDateTime.now();
        // 创建一个公告对象
        Notice notice = new Notice();
        notice.setTitle(noticeTitle);
        notice.setBody(noticeBody);
        notice.setPublisher(noticePublisher);
        notice.setRecipient(noticeRecipient);
        notice.setPublishDate(noticeTime);
        // 调用服务层发布公告
        if (new AdministratorServiceImpl().addNotice(notice)){
            JOptionPane.showMessageDialog(this, "发布成功!");
        } else {
            JOptionPane.showMessageDialog(this, "发布失败!");
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JCheckBox checkBoxStudent;
    private JCheckBox checkBoxTeacher;
    private JLabel label3;
    private JTextField inputNoaticeTitle;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JTextArea textAreaNoticeBody;
    private JTextField inputPublisher;
    private JButton buttonPublish;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

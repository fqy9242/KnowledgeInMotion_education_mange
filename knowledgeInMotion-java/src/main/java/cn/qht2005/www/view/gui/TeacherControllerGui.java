/*
 * Created by JFormDesigner on Fri May 17 10:01:37 CST 2024
 */

package cn.qht2005.www.view.gui;

import cn.qht2005.www.pojo.College;
import cn.qht2005.www.pojo.Student;
import cn.qht2005.www.service.impl.CollegeServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * @author 覃
 */
public class TeacherControllerGui extends JFrame {
    public TeacherControllerGui() {
        initComponents();
        showCollegeToCheckBox();
    }

    private void thisPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }

    private void tabbedPaneMainStateChanged(ChangeEvent e) {
        if (tabbedPaneMain.getSelectedIndex() == 0){

        }else if (tabbedPaneMain.getSelectedIndex() == 1){

        } else if (tabbedPaneMain.getSelectedIndex() == 2){
            showAllStudent();
        }
    }

    private void buttonModifyMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void buttonPrintMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void labelPhotoMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void buttonModifyPasswordMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tabbedPaneMain = new JTabbedPane();
        panelInfo = new JPanel();
        label1 = new JLabel();
        inputTeacherId = new JTextField();
        label2 = new JLabel();
        inputTeacherName = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        inputAge = new JTextField();
        label5 = new JLabel();
        inputPhoneNumber = new JTextField();
        checkboxSex = new JComboBox<>();
        buttonModify = new JButton();
        buttonPrint = new JButton();
        labelPhoto = new JLabel();
        label7 = new JLabel();
        inputCollegeId = new JTextField();
        label10 = new JLabel();
        inputClassId = new JTextField();
        labelPhotoTip = new JLabel();
        label12 = new JLabel();
        labelTime = new JLabel();
        buttonModifyPassword = new JButton();
        label8 = new JLabel();
        inputPosition = new JTextField();
        panel1 = new JPanel();
        panel2 = new JPanel();
        buttonExport = new JButton();
        scrollPane1 = new JScrollPane();
        tableStudentInfo = new JTable();
        buttonQuery = new JButton();
        label6 = new JLabel();
        textField1 = new JTextField();
        label9 = new JLabel();
        textField2 = new JTextField();
        label11 = new JLabel();
        checkboxSexForStudent = new JComboBox<>();
        label13 = new JLabel();
        checkboxCollege = new JComboBox();

        //======== this ========
        setTitle("\u884c\u77e5\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf-\u6559\u5e08\u7528\u6237      by\u8983\u60e0\u901a");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addPropertyChangeListener("selectPageOnAler", e -> thisPropertyChange(e));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPaneMain ========
        {
            tabbedPaneMain.setTabPlacement(SwingConstants.LEFT);
            tabbedPaneMain.addChangeListener(e -> tabbedPaneMainStateChanged(e));

            //======== panelInfo ========
            {
                panelInfo.setLayout(null);

                //---- label1 ----
                label1.setText("\u5de5\u53f7");
                panelInfo.add(label1);
                label1.setBounds(5, 45, 30, 15);

                //---- inputTeacherId ----
                inputTeacherId.setEditable(false);
                panelInfo.add(inputTeacherId);
                inputTeacherId.setBounds(40, 40, 110, inputTeacherId.getPreferredSize().height);

                //---- label2 ----
                label2.setText("\u59d3\u540d");
                panelInfo.add(label2);
                label2.setBounds(165, 50, 30, 15);

                //---- inputTeacherName ----
                inputTeacherName.setEditable(false);
                panelInfo.add(inputTeacherName);
                inputTeacherName.setBounds(210, 40, 110, 34);

                //---- label3 ----
                label3.setText("\u6027\u522b");
                panelInfo.add(label3);
                label3.setBounds(5, 95, 30, 15);

                //---- label4 ----
                label4.setText("\u5e74\u9f84");
                panelInfo.add(label4);
                label4.setBounds(160, 95, 30, 15);

                //---- inputAge ----
                inputAge.setEditable(false);
                panelInfo.add(inputAge);
                inputAge.setBounds(210, 85, 55, 34);

                //---- label5 ----
                label5.setText("\u624b\u673a\u53f7");
                panelInfo.add(label5);
                label5.setBounds(5, 205, 70, 15);

                //---- inputPhoneNumber ----
                inputPhoneNumber.setEditable(false);
                panelInfo.add(inputPhoneNumber);
                inputPhoneNumber.setBounds(40, 195, 110, 30);

                //---- checkboxSex ----
                checkboxSex.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u7537",
                    "\u5973"
                }));
                checkboxSex.setSelectedIndex(-1);
                checkboxSex.setEnabled(false);
                panelInfo.add(checkboxSex);
                checkboxSex.setBounds(new Rectangle(new Point(40, 85), checkboxSex.getPreferredSize()));

                //---- buttonModify ----
                buttonModify.setText("\u4fee\u6539");
                buttonModify.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        buttonModifyMouseClicked(e);
                    }
                });
                panelInfo.add(buttonModify);
                buttonModify.setBounds(40, 5, 78, 29);

                //---- buttonPrint ----
                buttonPrint.setText("\u5bfc\u51fa");
                buttonPrint.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        buttonPrintMouseClicked(e);
                    }
                });
                panelInfo.add(buttonPrint);
                buttonPrint.setBounds(215, 5, 78, 29);

                //---- labelPhoto ----
                labelPhoto.setBorder(new CompoundBorder(
                    new TitledBorder("text"),
                    new EmptyBorder(5, 5, 5, 5)));
                labelPhoto.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        labelPhotoMouseClicked(e);
                    }
                });
                panelInfo.add(labelPhoto);
                labelPhoto.setBounds(345, 15, 180, 170);

                //---- label7 ----
                label7.setText("\u5b66\u9662");
                panelInfo.add(label7);
                label7.setBounds(5, 170, 30, 15);

                //---- inputCollegeId ----
                inputCollegeId.setEditable(false);
                panelInfo.add(inputCollegeId);
                inputCollegeId.setBounds(40, 160, 110, 30);

                //---- label10 ----
                label10.setText("\u73ed\u7ea7");
                panelInfo.add(label10);
                label10.setBounds(5, 130, 30, 15);

                //---- inputClassId ----
                inputClassId.setEditable(false);
                panelInfo.add(inputClassId);
                inputClassId.setBounds(40, 120, 110, 30);

                //---- labelPhotoTip ----
                labelPhotoTip.setText("\u70b9\u51fb\u7167\u7247\u5373\u53ef\u4e0a\u4f20\u7167\u7247\u66f4\u6362");
                labelPhotoTip.setForeground(Color.red);
                labelPhotoTip.setVisible(false);
                panelInfo.add(labelPhotoTip);
                labelPhotoTip.setBounds(350, 185, 190, 20);

                //---- label12 ----
                label12.setText("\u767b\u5f55\u65f6\u95f4");
                panelInfo.add(label12);
                label12.setBounds(5, 365, 55, 17);

                //---- labelTime ----
                labelTime.setText("date");
                panelInfo.add(labelTime);
                labelTime.setBounds(75, 365, 365, 15);

                //---- buttonModifyPassword ----
                buttonModifyPassword.setText("\u4fee\u6539\u5bc6\u7801");
                buttonModifyPassword.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        buttonModifyPasswordMouseClicked(e);
                    }
                });
                panelInfo.add(buttonModifyPassword);
                buttonModifyPassword.setBounds(new Rectangle(new Point(165, 195), buttonModifyPassword.getPreferredSize()));

                //---- label8 ----
                label8.setText("\u804c\u4f4d");
                panelInfo.add(label8);
                label8.setBounds(155, 170, 30, 15);

                //---- inputPosition ----
                inputPosition.setEditable(false);
                panelInfo.add(inputPosition);
                inputPosition.setBounds(210, 160, 110, 30);
            }
            tabbedPaneMain.addTab("\u4e2a\u4eba\u4fe1\u606f", panelInfo);

            //======== panel1 ========
            {
                panel1.setLayout(null);
            }
            tabbedPaneMain.addTab("\u5b66\u751f\u6210\u7ee9\u5f55\u5165", panel1);

            //======== panel2 ========
            {
                panel2.setLayout(null);

                //---- buttonExport ----
                buttonExport.setText("\u5bfc\u51fa");
                panel2.add(buttonExport);
                buttonExport.setBounds(new Rectangle(new Point(450, 345), buttonExport.getPreferredSize()));

                //======== scrollPane1 ========
                {

                    //---- tableStudentInfo ----
                    tableStudentInfo.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "\u5b66\u53f7", "\u59d3\u540d", "\u6027\u522b", "\u5b66\u9662", "\u73ed\u7ea7", "\u624b\u673a\u53f7"
                        }
                    ) {
                        Class<?>[] columnTypes = new Class<?>[] {
                            String.class, String.class, String.class, String.class, String.class, String.class
                        };
                        boolean[] columnEditable = new boolean[] {
                            false, false, false, false, false, false
                        };
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                            return columnTypes[columnIndex];
                        }
                        @Override
                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return columnEditable[columnIndex];
                        }
                    });
                    scrollPane1.setViewportView(tableStudentInfo);
                }
                panel2.add(scrollPane1);
                scrollPane1.setBounds(0, 35, 555, 380);

                //---- buttonQuery ----
                buttonQuery.setText("\u67e5\u8be2");
                panel2.add(buttonQuery);
                buttonQuery.setBounds(new Rectangle(new Point(470, 3), buttonQuery.getPreferredSize()));

                //---- label6 ----
                label6.setText("\u59d3\u540d");
                panel2.add(label6);
                label6.setBounds(5, 15, 25, 15);
                panel2.add(textField1);
                textField1.setBounds(35, 5, 80, 25);

                //---- label9 ----
                label9.setText("\u73ed\u7ea7");
                panel2.add(label9);
                label9.setBounds(120, 10, 40, 17);
                panel2.add(textField2);
                textField2.setBounds(145, 5, 80, 25);

                //---- label11 ----
                label11.setText("\u6027\u522b");
                panel2.add(label11);
                label11.setBounds(340, 10, 30, label11.getPreferredSize().height);

                //---- checkboxSexForStudent ----
                checkboxSexForStudent.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u7537",
                    "\u5973"
                }));
                checkboxSexForStudent.setSelectedIndex(-1);
                panel2.add(checkboxSexForStudent);
                checkboxSexForStudent.setBounds(370, 5, 78, 25);

                //---- label13 ----
                label13.setText("\u5b66\u9662");
                panel2.add(label13);
                label13.setBounds(228, 10, 30, 17);
                panel2.add(checkboxCollege);
                checkboxCollege.setBounds(255, 5, 85, 25);
            }
            tabbedPaneMain.addTab("\u5b66\u751f\u67e5\u8be2", panel2);
        }
        contentPane.add(tabbedPaneMain);
        tabbedPaneMain.setBounds(0, 0, 770, 475);

        contentPane.setPreferredSize(new Dimension(650, 410));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTabbedPane tabbedPaneMain;
    private JPanel panelInfo;
    private JLabel label1;
    private JTextField inputTeacherId;
    private JLabel label2;
    private JTextField inputTeacherName;
    private JLabel label3;
    private JLabel label4;
    private JTextField inputAge;
    private JLabel label5;
    private JTextField inputPhoneNumber;
    private JComboBox<String> checkboxSex;
    private JButton buttonModify;
    private JButton buttonPrint;
    private JLabel labelPhoto;
    private JLabel label7;
    private JTextField inputCollegeId;
    private JLabel label10;
    private JTextField inputClassId;
    private JLabel labelPhotoTip;
    private JLabel label12;
    private JLabel labelTime;
    private JButton buttonModifyPassword;
    private JLabel label8;
    private JTextField inputPosition;
    private JPanel panel1;
    private JPanel panel2;
    private JButton buttonExport;
    private JScrollPane scrollPane1;
    private JTable tableStudentInfo;
    private JButton buttonQuery;
    private JLabel label6;
    private JTextField textField1;
    private JLabel label9;
    private JTextField textField2;
    private JLabel label11;
    private JComboBox<String> checkboxSexForStudent;
    private JLabel label13;
    private JComboBox checkboxCollege;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    // 获取到所有的学院 并且显示到下拉框中
    private void showCollegeToCheckBox(){
        try {
            CollegeServiceImpl collegeService = new CollegeServiceImpl();
            List<College> allColleges = collegeService.getAllCollege();
            for (College college : allColleges) {
                if (college != null){
                    checkboxCollege.addItem(college.getCollegeName());
                }
            }
            checkboxCollege.setSelectedIndex(-1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 展示所有学生
    private void showAllStudent(){
        try {
            List<Student> students = new TeacherServiceImpl().getAllStudent();
            // 往表格添加数据
            DefaultTableModel model = (DefaultTableModel) tableStudentInfo.getModel();
            model.setRowCount(0);
            for (Student student : students) {
                Object[] row = new Object[]{
                        student.getStudentId(),
                        student.getName(),
                        student.getSex() == 1 ? "男" : "女",
                        new CollegeServiceImpl().getCollegeNameById(student.getCollegeId()),
                        student.getClassId(),
                        student.getPhoneNumber()
                        };
                model.addRow(row);
            }
            // 设置居中对齐
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            tableStudentInfo.setDefaultRenderer(Object.class, r);

        } catch (Exception e) {
            throw new RuntimeException(e);
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
        new TeacherControllerGui().setVisible(true);
    }
}

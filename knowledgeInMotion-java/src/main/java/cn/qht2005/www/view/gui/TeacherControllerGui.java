/*
 * Created by JFormDesigner on Fri May 17 10:01:37 CST 2024
 */

package cn.qht2005.www.view.gui;

import cn.qht2005.www.pojo.College;
import cn.qht2005.www.pojo.Student;
import cn.qht2005.www.pojo.Teacher;
import cn.qht2005.www.service.impl.CollegeServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import cn.qht2005.www.util.ImgUtil;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * @author 覃
 */
public class TeacherControllerGui extends JFrame {
    // 教师工号，主键
    private final String teacherId;
    // 当前登录的教师对象
    private Teacher teacher;
    public TeacherControllerGui(String teacherId) {
        this.teacherId = teacherId;
        initComponents();
        upDateTimeNow();
    }

    private void thisPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }
    // 那啥文件夹菜单被切换
    private void tabbedPaneMainStateChanged(ChangeEvent e) {
        // 展示个人信息菜单
        if (tabbedPaneMain.getSelectedIndex() == 0){
            showTeacherInfo();

        }else if (tabbedPaneMain.getSelectedIndex() == 1){


        } else if (tabbedPaneMain.getSelectedIndex() == 2){
            // 查询学生页
            try {
                showCollegeToCheckBox();
                showAllStudent(new TeacherServiceImpl().getAllStudent());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    // 获取当前时间
    private void upDateTimeNow(){
        LocalDateTime now = LocalDateTime.now();
        // 格式化时间
        String time = now.getYear() + "年" + now.getMonthValue() +
                "月" + now.getDayOfMonth() + "日" + now.getHour() + "时" + now.getMinute() + "分" + now.getSecond() + "秒";
        labelTime.setText(time);
    }

    private void buttonModifyMouseClicked(MouseEvent e) {
        if (buttonModify.getText().equals("修改")) {
            buttonModify.setText("完成");
            // 将那几个输入框设置为可编辑
            letInfoEditable(true);
            labelPhotoTip.setVisible(true);
        }
    }

    private void buttonPrintMouseClicked(MouseEvent e) {
        // TODO add your code here
    }
    // 修改信息按钮被点击
    private void labelPhotoMouseClicked(MouseEvent e) {
        // 将那几个输入框设置为可编辑
        letInfoEditable(true);
    }

    // 修改密码按钮被点击 然后弹出修改密码的对话框
    private void buttonModifyPasswordMouseClicked(MouseEvent e) {
//        new ModifyPassword().setVisible(true);
    }
    // 查询按钮被点击

    private void buttonQueryMouseClicked(MouseEvent e) {
        try {
            showQueryResult();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    // 根据条件动态查询学生信息
    private void showQueryResult() throws Exception{
        String studentName = inputStudentName.getText();
        String classId = inputClass.getText();
        String collegeName = Objects.requireNonNull(checkboxCollege.getSelectedItem()).toString();
        Student s = new Student();
        s.setName(studentName);
        s.setClassId(classId);
        s.setSex(checkboxSex.getSelectedIndex() == -1 ? null :
                checkboxSex.getSelectedIndex() == 0 ? (short) 1 : (short) 0);
        s.setCollegeId(new CollegeServiceImpl().getCollegeIdByName(collegeName));
        s.setCollegeId(new CollegeServiceImpl().getCollegeIdByName(collegeName));
        List<Student> students = new TeacherServiceImpl().getStudentByDynamic(s);
        showAllStudent(students);
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
        inputStudentName = new JTextField();
        label9 = new JLabel();
        inputClass = new JTextField();
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
                label2.setBounds(160, 50, 30, 15);

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
                labelPhoto.setBounds(345, 15, 190, 200);

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
                label12.setBounds(5, 445, 55, 17);

                //---- labelTime ----
                labelTime.setText("date");
                panelInfo.add(labelTime);
                labelTime.setBounds(75, 445, 365, 15);

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
                label8.setBounds(160, 170, 30, 15);

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
                buttonExport.setBounds(new Rectangle(new Point(575, 435), buttonExport.getPreferredSize()));

                //======== scrollPane1 ========
                {

                    //---- tableStudentInfo ----
                    tableStudentInfo.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "\u5b66\u53f7", "\u59d3\u540d", "\u6027\u522b", "\u5e74\u9f84", "\u5b66\u9662", "\u73ed\u7ea7", "\u624b\u673a\u53f7"
                        }
                    ) {
                        Class<?>[] columnTypes = new Class<?>[] {
                            String.class, String.class, String.class, String.class, String.class, String.class, String.class
                        };
                        boolean[] columnEditable = new boolean[] {
                            false, false, false, true, false, false, false
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
                scrollPane1.setBounds(0, 35, 660, 455);

                //---- buttonQuery ----
                buttonQuery.setText("\u67e5\u8be2");
                buttonQuery.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        buttonQueryMouseClicked(e);
                    }
                });
                panel2.add(buttonQuery);
                buttonQuery.setBounds(new Rectangle(new Point(470, 3), buttonQuery.getPreferredSize()));

                //---- label6 ----
                label6.setText("\u59d3\u540d");
                panel2.add(label6);
                label6.setBounds(5, 15, 25, 15);
                panel2.add(inputStudentName);
                inputStudentName.setBounds(35, 5, 80, 25);

                //---- label9 ----
                label9.setText("\u73ed\u7ea7");
                panel2.add(label9);
                label9.setBounds(120, 10, 40, 17);
                panel2.add(inputClass);
                inputClass.setBounds(145, 5, 80, 25);

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
        tabbedPaneMain.setBounds(0, 0, 770, 485);

        contentPane.setPreferredSize(new Dimension(760, 500));
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
    private JTextField inputStudentName;
    private JLabel label9;
    private JTextField inputClass;
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
            // 先清空学院下拉框
            checkboxCollege.removeAllItems();
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
    private void showAllStudent(List<Student> students){
        try {
            // 往表格添加数据
            DefaultTableModel model = (DefaultTableModel) tableStudentInfo.getModel();
            model.setRowCount(0);
            for (Student student : students) {
                Object[] row = new Object[]{
                        student.getStudentId(),
                        student.getName(),
                        student.getSex() == 1 ? "男" : "女",
                        student.getAge(),
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
    // 修改个人信息
    private void modifyTeacherInfo(){
        try {
            TeacherServiceImpl service = new TeacherServiceImpl();
            Teacher teacher = new Teacher();
            teacher.setTeacherId(inputTeacherId.getText());
            teacher.setName(inputTeacherName.getText());
            teacher.setAge(Integer.parseInt(inputAge.getText()));
            teacher.setPhoneNumber(inputPhoneNumber.getText());
            teacher.setSex(checkboxSex.getSelectedIndex() == 0 ? (short) 1 : (short) 0);
//            service.updateTeacher(teacher);
            JOptionPane.showMessageDialog(this, "修改成功！");
            buttonModify.setText("修改");
            letInfoEditable(false);
            labelPhotoTip.setVisible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 将当前登录的教师信息显示到个人信息界面上
    private void showTeacherInfo(){
        try {
            TeacherServiceImpl service = new TeacherServiceImpl();
            Teacher teacher = service.getTeacherById(teacherId);
            this.teacher = teacher;
            inputTeacherId.setText(teacher.getTeacherId());
            inputTeacherName.setText(teacher.getName());
            inputAge.setText(teacher.getAge().toString());
            inputPhoneNumber.setText(teacher.getPhoneNumber());
            inputCollegeId.setText(new CollegeServiceImpl().getCollegeNameById(teacher.getCollegeId()));
            inputPosition.setText(teacher.getPosition());
            String sex = teacher.getSex() == 1 ? "男":"女";
            checkboxSex.setSelectedItem(sex);
            // 获取证件照url并展示到那啥标签上
            BufferedImage photograph; // 证件照url
            try {
                photograph = ImgUtil.getImgByUrl(teacher.getPhotograph());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ImageIcon photo = new ImageIcon(photograph);
            photo.setImage(photo.getImage().getScaledInstance(labelPhoto.getWidth(), labelPhoto.getHeight(), Image.SCALE_DEFAULT)); // 设置图片大小
            labelPhoto.setIcon(photo);  // 展示图片到标签
            // 判断教师对象的管理班级id是否为空，空则说明不是班主任
            if (teacher.getMangeClassId() == null){
                inputClassId.setText("非班主任；未管理班级");
            }else{
                inputClassId.setText(teacher.getMangeClassId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 设置那几个输入框是否可编辑
    private void letInfoEditable(boolean flag){
        inputTeacherName.setEditable(flag);
        inputAge.setEditable(flag);
        inputPhoneNumber.setEditable(flag);
        checkboxSex.setEnabled(flag);
    }

    public static void main(String[] args) {
        // 使用FlatLaf皮肤包
        FlatLightLaf.install();
        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("皮肤包导入失败！");
        }
        new TeacherControllerGui("2001333").setVisible(true);
    }
}

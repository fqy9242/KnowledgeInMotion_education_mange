/*
 * Created by JFormDesigner on Tue May 14 18:27:58 CST 2024
 */

package cn.qht2005.www.view.gui;

import cn.qht2005.www.pojo.Score;
import cn.qht2005.www.pojo.Student;
import cn.qht2005.www.service.impl.StudentServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author 覃
 */
public class StudentControllerGui extends JFrame {
    private static final StudentServiceImpl studentService;     // 学生服务对象
    private static final TeacherServiceImpl teacherServiceImpl;     // 教师服务对象
    private String studentId;                                   // 学生id
    private final Student student;   // 当前登录的学生对象
    static {
        try {
            studentService = new StudentServiceImpl();
            teacherServiceImpl = new TeacherServiceImpl();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public StudentControllerGui(String studentId) {
        this.studentId = studentId;
        student = teacherServiceImpl.getStudentById(studentId);
        initComponents();

    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tabbedPane1 = new JTabbedPane();
        panelInfo = new JPanel();
        label1 = new JLabel();
        inputStudentId = new JTextField();
        label2 = new JLabel();
        inputStudentName = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        inputAge = new JTextField();
        label5 = new JLabel();
        inputPhoneNumber = new JTextField();
        label6 = new JLabel();
        inputAdrress = new JTextField();
        checkboxSex = new JComboBox<>();
        buttonModify = new JButton();
        buttonPrint = new JButton();
        panelScore = new JPanel();
        scrollPane1 = new JScrollPane();
        tableScore = new JTable();

        //======== this ========
        setTitle("\u884c\u77e5\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf-\u5b66\u751f\u7528\u6237      by\u8983\u60e0\u901a");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {
            tabbedPane1.setTabPlacement(SwingConstants.LEFT);

            //======== panelInfo ========
            {
                panelInfo.setLayout(null);

                //---- label1 ----
                label1.setText("\u5b66\u53f7");
                panelInfo.add(label1);
                label1.setBounds(5, 55, 30, 15);
                panelInfo.add(inputStudentId);
                inputStudentId.setBounds(35, 45, 110, inputStudentId.getPreferredSize().height);

                //---- label2 ----
                label2.setText("\u59d3\u540d");
                panelInfo.add(label2);
                label2.setBounds(165, 55, 30, 15);
                panelInfo.add(inputStudentName);
                inputStudentName.setBounds(195, 45, 110, 34);

                //---- label3 ----
                label3.setText("\u6027\u522b");
                panelInfo.add(label3);
                label3.setBounds(5, 105, 30, 15);

                //---- label4 ----
                label4.setText("\u5e74\u9f84");
                panelInfo.add(label4);
                label4.setBounds(155, 110, 30, 15);
                panelInfo.add(inputAge);
                inputAge.setBounds(200, 100, 55, 34);

                //---- label5 ----
                label5.setText("\u624b\u673a\u53f7");
                panelInfo.add(label5);
                label5.setBounds(0, 155, 70, 15);
                panelInfo.add(inputPhoneNumber);
                inputPhoneNumber.setBounds(50, 145, 110, inputPhoneNumber.getPreferredSize().height);

                //---- label6 ----
                label6.setText("\u5730\u5740");
                panelInfo.add(label6);
                label6.setBounds(165, 155, 30, 15);
                panelInfo.add(inputAdrress);
                inputAdrress.setBounds(200, 150, 145, 34);

                //---- checkboxSex ----
                checkboxSex.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u7537",
                    "\u5973"
                }));
                checkboxSex.setSelectedIndex(-1);
                panelInfo.add(checkboxSex);
                checkboxSex.setBounds(new Rectangle(new Point(40, 95), checkboxSex.getPreferredSize()));

                //---- buttonModify ----
                buttonModify.setText("\u4fee\u6539");
                panelInfo.add(buttonModify);
                buttonModify.setBounds(new Rectangle(new Point(10, 5), buttonModify.getPreferredSize()));

                //---- buttonPrint ----
                buttonPrint.setText("\u5bfc\u51fa");
                panelInfo.add(buttonPrint);
                buttonPrint.setBounds(135, 5, 78, 34);
            }
            tabbedPane1.addTab("\u4e2a\u4eba\u4fe1\u606f", panelInfo);
            showInfo();

            //======== panelScore ========
            {
                panelScore.setLayout(null);

                //======== scrollPane1 ========
                {

                    //---- tableScore ----
                    tableScore.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "\u8bfe\u7a0b\u7f16\u53f7", "\u8bfe\u7a0b\u540d\u79f0", "\u6210\u7ee9"
                        }
                    ) {
                        Class<?>[] columnTypes = new Class<?>[] {
                            String.class, String.class, Double.class
                        };
                        boolean[] columnEditable = new boolean[] {
                            false, false, false
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
                    tableScore.setShowHorizontalLines(false);
                    tableScore.setShowVerticalLines(false);
                    tableScore.setRequestFocusEnabled(false);
                    scrollPane1.setViewportView(tableScore);
                }
                panelScore.add(scrollPane1);
                scrollPane1.setBounds(0, 0, 555, 475);
            }
            tabbedPane1.addTab("\u4e2a\u4eba\u6210\u7ee9", panelScore);
        }
        contentPane.add(tabbedPane1);
        tabbedPane1.setBounds(0, 0, 770, 475);

        contentPane.setPreferredSize(new Dimension(615, 410));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        showScore();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTabbedPane tabbedPane1;
    private JPanel panelInfo;
    private JLabel label1;
    private JTextField inputStudentId;
    private JLabel label2;
    private JTextField inputStudentName;
    private JLabel label3;
    private JLabel label4;
    private JTextField inputAge;
    private JLabel label5;
    private JTextField inputPhoneNumber;
    private JLabel label6;
    private JTextField inputAdrress;
    private JComboBox<String> checkboxSex;
    private JButton buttonModify;
    private JButton buttonPrint;
    private JPanel panelScore;
    private JScrollPane scrollPane1;
    private JTable tableScore;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    // 展示学生所有科目成绩
    public void showScore() {
        // 将表格数据居中显示
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        tableScore.setDefaultRenderer(Object.class, r);
        tableScore.setDefaultRenderer(Double.class, r);
        // 学生所有科目成绩
        List<Score> scores = studentService.getScoreById(studentId);
        // 将学生成绩添加到表格
        DefaultTableModel model = (DefaultTableModel) tableScore.getModel();
        for (Score score : scores) {
            model.addRow(new Object[]{score.getCourseId(), score.getCourseName(), score.getScore()});
        }
    }
    // 将学生的个人信息展示到文本框
    private void showInfo(){
        // 获取个人信息
        String studentId = this.studentId;
        String name = student.getName();
        Integer age = student.getAge();
//        String sex = student.getSex() == 0 ? "男" : "女";
        Short sex = student.getSex();
        String phoneNumber = student.getPhoneNumber();
        String address = student.getAddress();
        // 展示到文本框
        inputStudentId.setText(studentId);
        inputStudentName.setText(name);
        inputAge.setText(age.toString());
        inputPhoneNumber.setText(phoneNumber);
        inputAdrress.setText(address);
        if (sex == 0){
            checkboxSex.setSelectedIndex(0);
        }else{
            checkboxSex.setSelectedIndex(1);
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
        new StudentControllerGui("2331020130229").setVisible(true);
    }
}

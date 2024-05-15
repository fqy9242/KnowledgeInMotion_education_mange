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
    private final Student student = teacherServiceImpl.getStudentById(studentId);   // 当前登录的学生对象
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
        initComponents();
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tabbedPane1 = new JTabbedPane();
        panelInfo = new JPanel();
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
            }
            tabbedPane1.addTab("\u4e2a\u4eba\u4fe1\u606f", panelInfo);

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

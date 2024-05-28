/*
 * Created by JFormDesigner on Mon May 27 11:00:12 CST 2024
 */

package cn.qht2005.www.view.gui.main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import cn.qht2005.www.pojo.College;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.pojo.people.Teacher;
import cn.qht2005.www.service.impl.AdministratorServiceImpl;
import cn.qht2005.www.service.impl.CollegeServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import com.formdev.flatlaf.FlatLightLaf;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * @author 覃
 */
public class AdministratorControllerGui extends JFrame {
    private List<Student> students = null;
    private List<Teacher> teachers = null;
    public AdministratorControllerGui() {
        initComponents();
        init();
    }
    {
        try {
            // 获取所有学生对象
            students = new TeacherServiceImpl().getAllStudent();
            // 获取所有的教职工对象
            teachers = new TeacherServiceImpl().getAllTeacher();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 那啥文件夹改变
    private void tabbedPaneMenuStateChanged(ChangeEvent e) {
        if (tabbedPaneMenu.getSelectedIndex() == 0) {
            // 总览
            showMain();
        } else if (tabbedPaneMenu.getSelectedIndex() == 1) {
            // 学生管理
            try {
                showStudentInfoToTable();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }
    // 自定义的初始化
    private void init()  {
        try {
            // 默认展示总览
            if (tabbedPaneMenu.getSelectedIndex() == 0)showMain();
            // 将学生总人数展示到总览的标签上
            labelStudentCount.setText(students.size() + "");
            // 将教职工总人数展示到总览的标签上
            labelTeacherCount.setText(teachers.size() + "");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    // 总览
    private void showMain() {
        // 展示学生男女人数
        showStudentSexCountToChart();
        // 展示教师各年龄段人数
        showTeacherAgeCountToChart();
    }
    // 将学生男女人数显示到图表
    private void showStudentSexCountToChart(){
        try {
            // 创建服务对象
            TeacherServiceImpl service = new TeacherServiceImpl();
            // 获取男女人数
            Map<String, Integer> studentCountBySex = service.getStudentCountBySex(students);
            // 将其可视化
            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("boy", studentCountBySex.get("男"));
            dataset.setValue("girl", studentCountBySex.get("女"));
            JFreeChart pieChart = ChartFactory.createPieChart(
                    "studentCountBySex", // 图标题
                    dataset, // 数据集
                    true, true, true);
            PiePlot plot = (PiePlot) pieChart.getPlot();
            // 关闭图表文字抗锯齿
            pieChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
/*             设置标签生成器
             "{0}" 表示 section name
             "{1}" 表示 section value
             "{2}" 表示 Percent*/
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}-{2}"));
            ChartPanel chartPanel = new ChartPanel(pieChart);
            chartPanel.setBounds(0, 0, panelForStudentSexCount.getWidth(), panelForStudentSexCount.getHeight());
            panelForStudentSexCount.add(chartPanel);
            panelForStudentSexCount.revalidate();
            panelForStudentSexCount.repaint();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    // 将各年龄段教师人数展示到图表
    private void showTeacherAgeCountToChart(){
        Map<String, Integer> teacherCountByAge = new AdministratorServiceImpl().getTeacherCountByAge();
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("under35", teacherCountByAge.get("under35"));
        dataset.setValue("35to50", teacherCountByAge.get("35to50"));
        dataset.setValue("above50", teacherCountByAge.get("above50"));
        JFreeChart pieChart = ChartFactory.createPieChart(
                "teacherCountByAge", // 图标题
                dataset, // 数据集
                true, true, true);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        // 关闭图表文字抗锯齿
        pieChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
/*             设置标签生成器
             "{0}" 表示 section name
             "{1}" 表示 section value
             "{2}" 表示 Percent*/
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}-{2}"));
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setBounds(0, 0, panelForTeacherAgeCount.getWidth(), panelForTeacherAgeCount.getHeight());
        panelForTeacherAgeCount.add(chartPanel);
        panelForTeacherAgeCount.revalidate();
        panelForTeacherAgeCount.repaint();


    }

    // 窗口将要被关闭
    private void thisWindowClosing(WindowEvent e) {
        //确认对话框
        int option = JOptionPane.showConfirmDialog(null, "狗子，你真的要离开我么？", "翠花含情脉脉",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION){
            // 关闭程序
            this.dispose();
        }
    }
    // 展示学生学院比例到图表上
    private void showStudentByCollegeToChart(){
        try {
            // 获取学院列表
            List<College> colleges = new CollegeServiceImpl().getAllCollege();

            // 将其可视化
            DefaultPieDataset dataset = new DefaultPieDataset();

            JFreeChart pieChart = ChartFactory.createPieChart(
                    "studentCountBySex", // 图标题
                    dataset, // 数据集
                    true, true, true);
            PiePlot plot = (PiePlot) pieChart.getPlot();
            // 关闭图表文字抗锯齿
            pieChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
/*             设置标签生成器
             "{0}" 表示 section name
             "{1}" 表示 section value
             "{2}" 表示 Percent*/
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}-{2}"));
            ChartPanel chartPanel = new ChartPanel(pieChart);
            chartPanel.setBounds(0, 0, panelForStudentSexCount.getWidth(), panelForStudentSexCount.getHeight());
            panelForStudentSexCount.add(chartPanel);
            panelForStudentSexCount.revalidate();
            panelForStudentSexCount.repaint();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tabbedPaneMenu = new JTabbedPane();
        panelMain = new JPanel();
        panelForStudentSexCount = new JPanel();
        label1 = new JLabel();
        labelStudentCount = new JLabel();
        panelForTeacherAgeCount = new JPanel();
        label2 = new JLabel();
        labelTeacherCount = new JLabel();
        panelStudentMange = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        buttoneExport = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        buttoneExport2 = new JButton();
        panelCountStudentByCollege = new JPanel();
        panelTeacherMain = new JPanel();
        panelCollegeMain = new JPanel();
        panelCourseMange = new JPanel();
        panelNoticeMange = new JPanel();

        //======== this ========
        setTitle("\u884c\u77e5\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf-\u7ba1\u7406\u5458\u4e3b\u9875\u9762   by \u8983\u60e0\u901a");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPaneMenu ========
        {
            tabbedPaneMenu.setTabPlacement(SwingConstants.LEFT);
            tabbedPaneMenu.addChangeListener(e -> tabbedPaneMenuStateChanged(e));

            //======== panelMain ========
            {
                panelMain.setLayout(null);

                //======== panelForStudentSexCount ========
                {
                    panelForStudentSexCount.setLayout(null);
                }
                panelMain.add(panelForStudentSexCount);
                panelForStudentSexCount.setBounds(0, 395, 420, 275);

                //---- label1 ----
                label1.setText("\u5728\u6821\u5b66\u751f\u4eba\u6570:");
                panelMain.add(label1);
                label1.setBounds(new Rectangle(new Point(5, 25), label1.getPreferredSize()));

                //---- labelStudentCount ----
                labelStudentCount.setText("text");
                panelMain.add(labelStudentCount);
                labelStudentCount.setBounds(105, 25, 70, labelStudentCount.getPreferredSize().height);

                //======== panelForTeacherAgeCount ========
                {
                    panelForTeacherAgeCount.setLayout(null);
                }
                panelMain.add(panelForTeacherAgeCount);
                panelForTeacherAgeCount.setBounds(425, 395, 440, 275);

                //---- label2 ----
                label2.setText("\u5728\u6821\u6559\u804c\u5de5\u4eba\u6570:");
                panelMain.add(label2);
                label2.setBounds(5, 55, 105, 17);

                //---- labelTeacherCount ----
                labelTeacherCount.setText("text");
                panelMain.add(labelTeacherCount);
                labelTeacherCount.setBounds(105, 55, 50, labelTeacherCount.getPreferredSize().height);
            }
            tabbedPaneMenu.addTab("\u603b\u89c8", panelMain);

            //======== panelStudentMange ========
            {
                panelStudentMange.setLayout(null);

                //======== scrollPane1 ========
                {

                    //---- table1 ----
                    table1.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "\u5b66\u53f7", "\u5b66\u9662", "\u73ed\u7ea7", "\u59d3\u540d", "\u5e74\u9f84", "\u6027\u522b", "\u8054\u7cfb\u7535\u8bdd", "\u767b\u5f55\u5bc6\u7801", "\u5bb6\u5ead\u4f4f\u5740"
                        }
                    ) {
                        Class<?>[] columnTypes = new Class<?>[] {
                            String.class, String.class, Integer.class, String.class, Integer.class, String.class, String.class, String.class, String.class
                        };
                        boolean[] columnEditable = new boolean[] {
                            false, false, false, false, false, false, false, false, false
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
                    scrollPane1.setViewportView(table1);
                }
                panelStudentMange.add(scrollPane1);
                scrollPane1.setBounds(0, 50, 865, scrollPane1.getPreferredSize().height);

                //---- buttoneExport ----
                buttoneExport.setText("\u5bfc\u51fa");
                panelStudentMange.add(buttoneExport);
                buttoneExport.setBounds(new Rectangle(new Point(780, 5), buttoneExport.getPreferredSize()));

                //---- button4 ----
                button4.setText("\u6dfb\u52a0");
                panelStudentMange.add(button4);
                button4.setBounds(5, 5, 78, 30);

                //---- button5 ----
                button5.setText("\u4fee\u6539");
                panelStudentMange.add(button5);
                button5.setBounds(90, 5, 78, 30);

                //---- button6 ----
                button6.setText("\u5220\u9664");
                panelStudentMange.add(button6);
                button6.setBounds(185, 5, 78, 30);

                //---- buttoneExport2 ----
                buttoneExport2.setText("\u67e5\u8be2");
                panelStudentMange.add(buttoneExport2);
                buttoneExport2.setBounds(690, 5, 78, 30);

                //======== panelCountStudentByCollege ========
                {
                    panelCountStudentByCollege.setLayout(null);
                }
                panelStudentMange.add(panelCountStudentByCollege);
                panelCountStudentByCollege.setBounds(0, 475, 215, 200);
            }
            tabbedPaneMenu.addTab("\u5b66\u751f\u7ba1\u7406", panelStudentMange);

            //======== panelTeacherMain ========
            {
                panelTeacherMain.setLayout(null);
            }
            tabbedPaneMenu.addTab("\u6559\u804c\u5de5\u7ba1\u7406", panelTeacherMain);

            //======== panelCollegeMain ========
            {
                panelCollegeMain.setLayout(null);
            }
            tabbedPaneMenu.addTab("\u5b66\u9662\u7ba1\u7406", panelCollegeMain);

            //======== panelCourseMange ========
            {
                panelCourseMange.setLayout(null);
            }
            tabbedPaneMenu.addTab("\u8bfe\u7a0b\u7ba1\u7406", panelCourseMange);

            //======== panelNoticeMange ========
            {
                panelNoticeMange.setLayout(null);
            }
            tabbedPaneMenu.addTab("\u516c\u544a\u7ba1\u7406", panelNoticeMange);
        }
        contentPane.add(tabbedPaneMenu);
        tabbedPaneMenu.setBounds(5, 10, 955, 670);

        contentPane.setPreferredSize(new Dimension(960, 710));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // 展示学生列表到表格上
    private void showStudentInfoToTable() throws Exception {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        for (Student student : students) {
            model.addRow(new Object[]{
                    student.getStudentId(),
                    new CollegeServiceImpl().getCollegeNameById(student.getCollegeId())
                    ,
                    student.getClassId(),
                    student.getName(),
                    student.getAge(),
                    student.getSex() == 1 ? "男" : "女",
                    student.getPhoneNumber(),
                    student.getPassWord(),
                    student.getAddress()
            });
            // 设置表格内容居中
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            table1.setDefaultRenderer(Object.class, r);
            table1.setDefaultRenderer(Integer.class, r);

        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTabbedPane tabbedPaneMenu;
    private JPanel panelMain;
    private JPanel panelForStudentSexCount;
    private JLabel label1;
    private JLabel labelStudentCount;
    private JPanel panelForTeacherAgeCount;
    private JLabel label2;
    private JLabel labelTeacherCount;
    private JPanel panelStudentMange;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton buttoneExport;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton buttoneExport2;
    private JPanel panelCountStudentByCollege;
    private JPanel panelTeacherMain;
    private JPanel panelCollegeMain;
    private JPanel panelCourseMange;
    private JPanel panelNoticeMange;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static void main(String[] args) {
        // 使用FlatLaf皮肤包
        FlatLightLaf.install();
        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("皮肤包导入失败！");
        }
        new AdministratorControllerGui().setVisible(true);

    }
    }


/*
 * Created by JFormDesigner on Mon May 27 11:00:12 CST 2024
 */

package cn.qht2005.www.view.gui.main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
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
                showStudentCountByCollegeToChart();
                showCollegeListToSelectBox(selectBoxStudentCollege);


            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }
    // 展示学院列表到选择框上 形参：一个选择框
    private void showCollegeListToSelectBox(JComboBox<String> selectBox) {
        try {
            new CollegeServiceImpl().getAllCollege().forEach(college -> selectBox.addItem(college.getCollegeName()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    // 展示各学院人数并展示到图表上
    private void showStudentCountByCollegeToChart() {
        try {
            // 获取学院列表
            List<College> colleges = new CollegeServiceImpl().getAllCollege();
            // 获取各学院人数
            Map<String, Integer> studentCountByCollege = new AdministratorServiceImpl().getStudentCountByCollege();
            // 将其可视化
            DefaultPieDataset dataset = new DefaultPieDataset();
            for (College college : colleges) {
                dataset.setValue(college.getCollegeName(), studentCountByCollege.get(college.getCollegeName()));
            }
            JFreeChart pieChart = ChartFactory.createPieChart(
                    "studentCountByCollege", // 图标题
                    dataset, // 数据集
                    true, true, true);
            PiePlot plot = (PiePlot) pieChart.getPlot();
            // 关闭图表文字抗锯齿
            pieChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
            ChartPanel chartPanel = new ChartPanel(pieChart);
            // 为那些玩意设置中文字体，不然不显示
            Font LegendFont = new Font("宋体", Font.PLAIN, 15);
            LegendTitle legend = pieChart.getLegend(0);
            legend.setItemFont(LegendFont);
            plot.setLabelFont(new Font("黑体", Font.PLAIN, 13));
            // 设置那啥位置，让其能够显示
            chartPanel.setBounds(0, 0, panelCountStudentByCollege.getWidth(), panelCountStudentByCollege.getHeight());
            // 刷新一下组件
            panelCountStudentByCollege.add(chartPanel);
            panelCountStudentByCollege.revalidate();
            panelCountStudentByCollege.repaint();


        } catch (Exception e) {
			throw new RuntimeException(e);
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
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}-{2}"));
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


    // 查询按钮被点击
    private void buttonQueryMouseClicked(MouseEvent e) {
        try {
            queryStudent();
            showStudentInfoToTable();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
    // 动态条件查询学生
    private void queryStudent() throws Exception {
        // 姓名
        String name = inputStudentName.getText();
        // 学号
        String id = inputStudentId.getText();
        // 性别
        Integer sex = selectBoxStudentSex.getSelectedIndex() == 0 ? null : selectBoxStudentSex.getSelectedIndex() == 1 ? 1 : 0;
        // 学院
        String collegeName = Objects.equals(Objects.requireNonNull(selectBoxStudentCollege.getSelectedItem())
                .toString(), "不限") ? null : selectBoxStudentCollege.getSelectedItem().toString();
        // 创建学生对象
        Student student = new Student();
        student.setName(name);
        student.setStudentId(id);
        if (collegeName != null) {
            student.setCollegeId(new CollegeServiceImpl().getCollegeIdByName(collegeName));
        }

        if (sex != null) {
            student.setSex(sex.shortValue());
        }
        // 将查询到的学生列表赋值给全局变量students
        students = new TeacherServiceImpl().getStudentByDynamic(student);
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
        tableStudentList = new JTable();
        buttoneExport = new JButton();
        buttonAddStudent = new JButton();
        buttonUpdateStudent = new JButton();
        buttonDeleteStudent = new JButton();
        buttonQuery = new JButton();
        panelCountStudentByCollege = new JPanel();
        label3 = new JLabel();
        inputStudentId = new JTextField();
        label4 = new JLabel();
        inputStudentName = new JTextField();
        label5 = new JLabel();
        selectBoxStudentSex = new JComboBox<>();
        label6 = new JLabel();
        selectBoxStudentCollege = new JComboBox<>();
        label7 = new JLabel();
        labelStudentListCount = new JLabel();
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

                    //---- tableStudentList ----
                    tableStudentList.setModel(new DefaultTableModel(
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
                    scrollPane1.setViewportView(tableStudentList);
                }
                panelStudentMange.add(scrollPane1);
                scrollPane1.setBounds(5, 50, 855, scrollPane1.getPreferredSize().height);

                //---- buttoneExport ----
                buttoneExport.setText("\u5bfc\u51fa");
                panelStudentMange.add(buttoneExport);
                buttoneExport.setBounds(780, 5, 78, 30);

                //---- buttonAddStudent ----
                buttonAddStudent.setText("\u6dfb\u52a0");
                panelStudentMange.add(buttonAddStudent);
                buttonAddStudent.setBounds(5, 5, 78, 30);

                //---- buttonUpdateStudent ----
                buttonUpdateStudent.setText("\u4fee\u6539");
                panelStudentMange.add(buttonUpdateStudent);
                buttonUpdateStudent.setBounds(90, 5, 78, 30);

                //---- buttonDeleteStudent ----
                buttonDeleteStudent.setText("\u5220\u9664");
                panelStudentMange.add(buttonDeleteStudent);
                buttonDeleteStudent.setBounds(185, 5, 78, 30);

                //---- buttonQuery ----
                buttonQuery.setText("\u67e5\u8be2");
                buttonQuery.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        buttonQueryMouseClicked(e);
                    }
                });
                panelStudentMange.add(buttonQuery);
                buttonQuery.setBounds(690, 5, 78, 30);

                //======== panelCountStudentByCollege ========
                {
                    panelCountStudentByCollege.setLayout(null);
                }
                panelStudentMange.add(panelCountStudentByCollege);
                panelCountStudentByCollege.setBounds(0, 475, 345, 200);

                //---- label3 ----
                label3.setText("\u59d3\u540d");
                panelStudentMange.add(label3);
                label3.setBounds(new Rectangle(new Point(265, 15), label3.getPreferredSize()));
                panelStudentMange.add(inputStudentId);
                inputStudentId.setBounds(395, 10, 70, 25);

                //---- label4 ----
                label4.setText("\u5b66\u53f7");
                panelStudentMange.add(label4);
                label4.setBounds(new Rectangle(new Point(365, 15), label4.getPreferredSize()));
                panelStudentMange.add(inputStudentName);
                inputStudentName.setBounds(290, 10, 70, 25);

                //---- label5 ----
                label5.setText("\u6027\u522b");
                panelStudentMange.add(label5);
                label5.setBounds(new Rectangle(new Point(470, 15), label5.getPreferredSize()));

                //---- selectBoxStudentSex ----
                selectBoxStudentSex.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u4e0d\u9650",
                    "\u7537",
                    "\u5973"
                }));
                panelStudentMange.add(selectBoxStudentSex);
                selectBoxStudentSex.setBounds(500, 10, 60, selectBoxStudentSex.getPreferredSize().height);

                //---- label6 ----
                label6.setText("\u5b66\u9662");
                panelStudentMange.add(label6);
                label6.setBounds(new Rectangle(new Point(565, 15), label6.getPreferredSize()));

                //---- selectBoxStudentCollege ----
                selectBoxStudentCollege.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u4e0d\u9650"
                }));
                panelStudentMange.add(selectBoxStudentCollege);
                selectBoxStudentCollege.setBounds(595, 10, 90, selectBoxStudentCollege.getPreferredSize().height);

                //---- label7 ----
                label7.setText("\u5217\u8868\u4eba\u6570");
                panelStudentMange.add(label7);
                label7.setBounds(new Rectangle(new Point(360, 495), label7.getPreferredSize()));

                //---- labelStudentListCount ----
                labelStudentListCount.setText("text");
                panelStudentMange.add(labelStudentListCount);
                labelStudentListCount.setBounds(415, 495, 70, labelStudentListCount.getPreferredSize().height);
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
        DefaultTableModel model = (DefaultTableModel) tableStudentList.getModel();
        model.setRowCount(0);
        for (Student student : students) {
            model.addRow(new Object[]{
                    student.getStudentId(),
                    new CollegeServiceImpl().getCollegeNameById(student.getCollegeId()),
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
            tableStudentList.setDefaultRenderer(Object.class, r);
            tableStudentList.setDefaultRenderer(Integer.class, r);
        }
        // 展示总人数到那啥标签上
        labelStudentListCount.setText(model.getRowCount() + "");
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
    private JTable tableStudentList;
    private JButton buttoneExport;
    private JButton buttonAddStudent;
    private JButton buttonUpdateStudent;
    private JButton buttonDeleteStudent;
    private JButton buttonQuery;
    private JPanel panelCountStudentByCollege;
    private JLabel label3;
    private JTextField inputStudentId;
    private JLabel label4;
    private JTextField inputStudentName;
    private JLabel label5;
    private JComboBox<String> selectBoxStudentSex;
    private JLabel label6;
    private JComboBox<String> selectBoxStudentCollege;
    private JLabel label7;
    private JLabel labelStudentListCount;
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


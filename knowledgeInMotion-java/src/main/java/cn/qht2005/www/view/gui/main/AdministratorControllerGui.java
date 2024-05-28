/*
 * Created by JFormDesigner on Mon May 27 11:00:12 CST 2024
 */

package cn.qht2005.www.view.gui.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.*;

import cn.qht2005.www.pojo.people.Student;
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
    public AdministratorControllerGui() {
        initComponents();
        init();
    }
    // 那啥文件夹改变
    private void tabbedPaneMenuStateChanged(ChangeEvent e) {
        if (tabbedPaneMenu.getSelectedIndex() == 1) {
            // 总览
            showMain();

        }
    }
    // 自定义的初始化
    private void init()  {
        try {
            // 获取所有学生对象
            students = new TeacherServiceImpl().getAllStudent();
            // 默认展示总览
            if (tabbedPaneMenu.getSelectedIndex() == 0)showMain();
            labelStudentCount.setText(students.size() + "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    // 总览
    private void showMain() {
        // 展示学生男女人数
        showStudentSexCountToChart();
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


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tabbedPaneMenu = new JTabbedPane();
        panelMain = new JPanel();
        panelForStudentSexCount = new JPanel();
        label1 = new JLabel();
        labelStudentCount = new JLabel();
        panelForTeacherSexCount = new JPanel();
        panelStudentMange = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panelTeacherMain = new JPanel();
        panelCollegeMain = new JPanel();
        panelCourseMange = new JPanel();
        panelNoticeMange = new JPanel();

        //======== this ========
        setTitle("\u884c\u77e5\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf-\u7ba1\u7406\u5458\u4e3b\u9875\u9762   by \u8983\u60e0\u901a");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
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
                labelStudentCount.setBounds(95, 25, 70, labelStudentCount.getPreferredSize().height);

                //======== panelForTeacherSexCount ========
                {
                    panelForTeacherSexCount.setLayout(null);
                }
                panelMain.add(panelForTeacherSexCount);
                panelForTeacherSexCount.setBounds(425, 395, 440, 275);
            }
            tabbedPaneMenu.addTab("\u603b\u89c8", panelMain);

            //======== panelStudentMange ========
            {
                panelStudentMange.setLayout(null);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table1);
                }
                panelStudentMange.add(scrollPane1);
                scrollPane1.setBounds(0, 50, 870, scrollPane1.getPreferredSize().height);
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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTabbedPane tabbedPaneMenu;
    private JPanel panelMain;
    private JPanel panelForStudentSexCount;
    private JLabel label1;
    private JLabel labelStudentCount;
    private JPanel panelForTeacherSexCount;
    private JPanel panelStudentMange;
    private JScrollPane scrollPane1;
    private JTable table1;
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


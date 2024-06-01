/*
 * Created by JFormDesigner on Fri May 17 10:01:37 CST 2024
 */

package cn.qht2005.www.view.gui.main;

import cn.qht2005.www.pojo.College;
import cn.qht2005.www.pojo.Leave;
import cn.qht2005.www.pojo.Notice;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.pojo.people.Teacher;
import cn.qht2005.www.service.impl.CollegeServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import cn.qht2005.www.util.AliOSSUtil;
import cn.qht2005.www.util.ImgUtil;
import cn.qht2005.www.view.gui.DisposeLeaveWindowsAndController;
import cn.qht2005.www.view.gui.ModifyPassword;
import com.formdev.flatlaf.FlatLightLaf;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

/**
 * @author 覃
 */
public class TeacherControllerGui extends JFrame {
    // 教师工号，主键
    private final String teacherId;
    // 当前登录的教师对象
    private Teacher teacher;
    // 上传的图片
    private File uploadPhoto;
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
            showNotice();

        }else if (tabbedPaneMain.getSelectedIndex() == 1){
            // 查询学生页
            try {
                showCollegeToCheckBox();
                showAllStudent(new TeacherServiceImpl().getAllStudent());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (tabbedPaneMain.getSelectedIndex() == 2){
            // 请假页
            showLeaveApplyList();

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
    // 修改个人信息按钮被点击事件
    private void buttonModifyMouseClicked(MouseEvent e) {
        // 如果按钮的那啥是修改，就证明是要修改信息 否则就是完成修改信息
        if ("修改".equals(buttonModify.getText())) {
            buttonModify.setText("完成");
            buttonPrint.setText("取消");
            // 将那几个输入框设置为可编辑
            letInfoEditable(true);
            labelPhotoTip.setVisible(true);
        } else if ("完成".equals(buttonModify.getText())) {
            // 完成修改
            modifyInfo();
            modifyInfoSuccess();
        }
    }
    // 修改信息成功后的状态
    private void modifyInfoSuccess(){
            buttonModify.setText("修改");
            buttonPrint.setText("导出");
            letInfoEditable(false);
            labelPhotoTip.setVisible(false);
    }
    // 修改个人信息
    private void modifyInfo(){
        try {
            TeacherServiceImpl service = new TeacherServiceImpl();
            Teacher teacher = new Teacher();
            teacher.setTeacherId(inputTeacherId.getText());
            teacher.setAge(Integer.parseInt(inputAge.getText()));
            teacher.setPhoneNumber(inputPhoneNumber.getText());
            teacher.setSex(checkboxSex.getSelectedIndex() == 0 ? (short) 1 : (short) 0);
            if (uploadPhoto != null){
                // 把图片上传到阿里云OSS并获取url
                String photoUrl = AliOSSUtil.uploadFile(uploadPhoto);
                // 设置图片url
                teacher.setPhotograph(photoUrl);
            }
            boolean res = service.setTeacherByTeacher(teacher);
            if (res) {
                // 那啥返回true 表示修改成功
                JOptionPane.showMessageDialog(this, "修改成功！");

            }else{
                // 修改失败
                JOptionPane.showMessageDialog(this, "修改失败！");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "修改失败！");
        }

    }
    // 导出个人信息按钮点击事件
    private void buttonPrintMouseClicked(MouseEvent e) {
        if ("取消".equals(buttonPrint.getText())){
            buttonModify.setText("修改");
            buttonPrint.setText("导出");
            letInfoEditable(false);
            labelPhotoTip.setVisible(false);
            showTeacherInfo();
        }else{
            // 导出
            try {
                JOptionPane.showMessageDialog(this, "突然发现这个功能没什么用，没实现！！");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }
    // 放图片那个标签被点击事件
    private void labelPhotoMouseClicked(MouseEvent e) {
        if ("完成".equals(buttonModify.getText())){
            uploadImage();
        }
    }
    // 上传图片
    public void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        // 限制选择的文件类型
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            uploadPhoto = fileChooser.getSelectedFile();
            try {
                // 将上传的文件展示到标签
                BufferedImage image = ImageIO.read(uploadPhoto);
                ImageIcon imageIcon = new ImageIcon(image);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(labelPhoto.getWidth(), labelPhoto.getHeight(), Image.SCALE_DEFAULT)); // 设置图片大小
                labelPhoto.setIcon(imageIcon);
/*                // 上传图片
                String photoUrl = AliOSSUtil.uploadFile(photo);
                student.setPhotograph(photoUrl);*/
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 修改密码按钮被点击 然后弹出修改密码的对话框
    private void buttonModifyPasswordMouseClicked(MouseEvent e) {
        new ModifyPassword(teacher).setVisible(true);
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
    // 导出学生信息 传进去一个学生id的集合
    private void exportStudent(List<String> studentIds) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("student_info");
        // 操作表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("学号");
        headerRow.createCell(1).setCellValue("姓名");
        headerRow.createCell(2).setCellValue("性别");
        headerRow.createCell(3).setCellValue("年龄");
        headerRow.createCell(4).setCellValue("学院");
        headerRow.createCell(5).setCellValue("班级");
        headerRow.createCell(6).setCellValue("手机号");
        // 行数据
        for (int i = 0; i < studentIds.size(); i++) {
            // 学号
            String id = studentIds.get(i);
            // 根据学号拿到对应的学生对象
            Student student = new TeacherServiceImpl().getStudentById(id);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(student.getStudentId());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getSex() == 1 ? "男":"女");
            row.createCell(3).setCellValue(student.getAge());
            row.createCell(4).setCellValue(new CollegeServiceImpl().getCollegeNameById(student.getCollegeId()));
            row.createCell(5).setCellValue(student.getClassId());
            row.createCell(6).setCellValue(student.getPhoneNumber());
        }
        // 输出文件
        // 创建一个文件选择器
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择保存位置");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // 显示文件选择器对话框
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // 如果用户点击了"保存"，则获取用户选择的目录
            File fileToSave = fileChooser.getSelectedFile();
            // 输出文件
            String fileName = fileToSave.getAbsolutePath() + "/"  + "导出的学生信息.xlsx"; // 导出的文件名，啊不，是路径。
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(this, "导出成功！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        workbook.close();
    }
    // 导出查询到的学生信息按钮被点击
    private void buttonExportMouseClicked(MouseEvent e) {
        try {
            // 获取表格模型
            DefaultTableModel model = (DefaultTableModel) tableStudentInfo.getModel();
            // 获取学生信息
            List<String> students = new ArrayList<>();
            for (int i = 0; i < model.getRowCount(); i++) {
                // 从表格模型中拿到学号
                students.add(model.getValueAt(i, 0).toString());
            }
            // 导出学生信息
            exportStudent(students);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    // 批假按钮被点击
    private void buttonDisposeMouseClicked(MouseEvent e) {
        // 获取选中行的索引
        int selectedRow = tableForLeaveApply.getSelectedRow();
        // 如果有行被选中
        Integer leaveId = null;
        if (selectedRow != -1) {
            // 获取选中行的请假id
            leaveId = (Integer) tableForLeaveApply.getValueAt(selectedRow, 0);
        }else{
            JOptionPane.showMessageDialog(this, "请选择要处理的请假申请！");
            return;
        }
        try {
            Leave leave = new TeacherServiceImpl().getLeaveByLeaveId(String.valueOf(leaveId));
            // 弹出处理请假的对话框
            new DisposeLeaveWindowsAndController(this,leave).setVisible(true);
            // 回到主窗口，然后更新状态
            showLeaveApplyList();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
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
        buttonModifyPassword = new JButton();
        label8 = new JLabel();
        inputPosition = new JTextField();
        scrollPaneNotice = new JScrollPane();
        tableNotice = new JTable();
        label12 = new JLabel();
        labelTime = new JLabel();
        panelSeachInfo = new JPanel();
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
        panelDisposeScore = new JPanel();
        scrollPaneForLeaveApply = new JScrollPane();
        tableForLeaveApply = new JTable();
        buttonDispose = new JButton();
        notClassMangeTip = new JLabel();
        disposeTip = new JLabel();

        //======== this ========
        setTitle("\u884c\u77e5\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf-\u6559\u5e08\u7528\u6237      by\u8983\u60e0\u901a");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addPropertyChangeListener("selectPageOnAler", e -> thisPropertyChange(e));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
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
                labelPhoto.setBounds(345, 15, 190, 195);

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
                labelPhotoTip.setBounds(350, 205, 190, 20);

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

                //======== scrollPaneNotice ========
                {

                    //---- tableNotice ----
                    tableNotice.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "\u516c\u544aid", "\u53d1\u6587", "\u65f6\u95f4", "\u6807\u9898", "\u516c\u544a"
                        }
                    ) {
                        Class<?>[] columnTypes = new Class<?>[] {
                            String.class, String.class, String.class, String.class, String.class
                        };
                        boolean[] columnEditable = new boolean[] {
                            false, false, false, false, false
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
                    scrollPaneNotice.setViewportView(tableNotice);
                }
                panelInfo.add(scrollPaneNotice);
                scrollPaneNotice.setBounds(0, 230, 655, 235);

                //---- label12 ----
                label12.setText("\u767b\u5f55\u65f6\u95f4");
                panelInfo.add(label12);
                label12.setBounds(50, 465, 55, label12.getPreferredSize().height);

                //---- labelTime ----
                labelTime.setText("date");
                labelTime.setForeground(Color.red);
                panelInfo.add(labelTime);
                labelTime.setBounds(120, 465, 365, 15);
            }
            tabbedPaneMain.addTab("\u4e2a\u4eba\u4fe1\u606f", panelInfo);

            //======== panelSeachInfo ========
            {
                panelSeachInfo.setLayout(null);

                //---- buttonExport ----
                buttonExport.setText("\u5bfc\u51fa");
                buttonExport.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        buttonExportMouseClicked(e);
                    }
                });
                panelSeachInfo.add(buttonExport);
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
                panelSeachInfo.add(scrollPane1);
                scrollPane1.setBounds(0, 35, 660, 455);

                //---- buttonQuery ----
                buttonQuery.setText("\u67e5\u8be2");
                buttonQuery.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        buttonQueryMouseClicked(e);
                    }
                });
                panelSeachInfo.add(buttonQuery);
                buttonQuery.setBounds(new Rectangle(new Point(470, 3), buttonQuery.getPreferredSize()));

                //---- label6 ----
                label6.setText("\u59d3\u540d");
                panelSeachInfo.add(label6);
                label6.setBounds(5, 15, 25, 15);
                panelSeachInfo.add(inputStudentName);
                inputStudentName.setBounds(35, 5, 80, 25);

                //---- label9 ----
                label9.setText("\u73ed\u7ea7");
                panelSeachInfo.add(label9);
                label9.setBounds(120, 10, 40, 17);
                panelSeachInfo.add(inputClass);
                inputClass.setBounds(145, 5, 80, 25);

                //---- label11 ----
                label11.setText("\u6027\u522b");
                panelSeachInfo.add(label11);
                label11.setBounds(340, 10, 30, label11.getPreferredSize().height);

                //---- checkboxSexForStudent ----
                checkboxSexForStudent.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u7537",
                    "\u5973"
                }));
                checkboxSexForStudent.setSelectedIndex(-1);
                panelSeachInfo.add(checkboxSexForStudent);
                checkboxSexForStudent.setBounds(370, 5, 78, 25);

                //---- label13 ----
                label13.setText("\u5b66\u9662");
                panelSeachInfo.add(label13);
                label13.setBounds(228, 10, 30, 17);
                panelSeachInfo.add(checkboxCollege);
                checkboxCollege.setBounds(255, 5, 85, 25);
            }
            tabbedPaneMain.addTab("\u5b66\u751f\u67e5\u8be2", panelSeachInfo);

            //======== panelDisposeScore ========
            {
                panelDisposeScore.setLayout(null);

                //======== scrollPaneForLeaveApply ========
                {

                    //---- tableForLeaveApply ----
                    tableForLeaveApply.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "\u8bf7\u5047ID", "\u5b66\u53f7", "\u59d3\u540d", "\u7c7b\u578b", "\u7406\u7531", "\u5f00\u59cb\u65f6\u95f4", "\u7ed3\u675f\u65f6\u95f4", "\u72b6\u6001"
                        }
                    ) {
                        Class<?>[] columnTypes = new Class<?>[] {
                            String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
                        };
                        boolean[] columnEditable = new boolean[] {
                            false, false, false, false, false, false, false, false
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
                    tableForLeaveApply.setShowHorizontalLines(false);
                    tableForLeaveApply.setShowVerticalLines(false);
                    tableForLeaveApply.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    scrollPaneForLeaveApply.setViewportView(tableForLeaveApply);
                }
                panelDisposeScore.add(scrollPaneForLeaveApply);
                scrollPaneForLeaveApply.setBounds(0, 40, 650, 435);

                //---- buttonDispose ----
                buttonDispose.setText("\u5904\u7406");
                buttonDispose.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        buttonDisposeMouseClicked(e);
                    }
                });
                panelDisposeScore.add(buttonDispose);
                buttonDispose.setBounds(new Rectangle(new Point(510, 5), buttonDispose.getPreferredSize()));

                //---- notClassMangeTip ----
                notClassMangeTip.setText("\u8be5\u7cfb\u7edf\u4ec5\u53ef\u73ed\u4e3b\u4efb\u6279\u5047\u672c\u73ed\u5b66\u751f\uff01");
                notClassMangeTip.setForeground(new Color(0xcc3300));
                notClassMangeTip.setVisible(false);
                panelDisposeScore.add(notClassMangeTip);
                notClassMangeTip.setBounds(new Rectangle(new Point(15, 10), notClassMangeTip.getPreferredSize()));

                //---- disposeTip ----
                disposeTip.setText("\u9009\u4e2d\u884c\u540e\u70b9\u51fb\u5904\u7406\u5373\u53ef\u6279\u5047\uff01");
                disposeTip.setForeground(new Color(0xff0033));
                panelDisposeScore.add(disposeTip);
                disposeTip.setBounds(240, 10, 205, disposeTip.getPreferredSize().height);
            }
            tabbedPaneMain.addTab("\u6279\u5047", panelDisposeScore);
        }
        contentPane.add(tabbedPaneMain);
        tabbedPaneMain.setBounds(0, 0, 770, 480);

        contentPane.setPreferredSize(new Dimension(760, 510));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // 获取请假申请列表
    private void showLeaveApplyList(){
        if (teacher.getMangeClassId() == null){
            // 如果没有管理的班级
            // 将那啥标签设置为可见
            notClassMangeTip.setEnabled(true);
            // 将批假小提示设置为不可见
            disposeTip.setVisible(false);
            // 将批假按钮设置为不可用
            buttonDispose.setEnabled(false);
            return;
        }
        try {
            List<Leave> leaveApplies = new TeacherServiceImpl().getLeaveByClassId(teacher.getMangeClassId());
            // 往表格添加数据
            DefaultTableModel model = (DefaultTableModel) tableForLeaveApply.getModel();
            model.setRowCount(0);
            for (Leave leaveApply : leaveApplies) {
                // 创建一个格式化时间对象
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
                // 获取学生对象
                Student student = new TeacherServiceImpl().getStudentById(leaveApply.getUserId());
                Object[] row = new Object[]{
                        // 请假id
                        leaveApply.getLeaveId(),
                        // 学号
                        leaveApply.getUserId(),
                        // 姓名
                        student.getName(),
                        // 请假类型
                        leaveApply.getLeaveType() == 1 ? "事假" : leaveApply.getLeaveType() == 2 ? "病假" : "其他",
                        leaveApply.getLeaveReason(),
                        leaveApply.getLeaveStartTime().format(dateTimeFormatter),
                        leaveApply.getLeaveEndTime().format(dateTimeFormatter),
                        leaveApply.getApplicationStatus() == -1 ? "未处理" :
                                leaveApply.getApplicationStatus() == 1 ? "已同意" : "已拒绝"
                };
                model.addRow(row);
            }
            // 设置居中对齐
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            tableForLeaveApply.setDefaultRenderer(Object.class, r);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    private JButton buttonModifyPassword;
    private JLabel label8;
    private JTextField inputPosition;
    private JScrollPane scrollPaneNotice;
    private JTable tableNotice;
    private JLabel label12;
    private JLabel labelTime;
    private JPanel panelSeachInfo;
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
    private JPanel panelDisposeScore;
    private JScrollPane scrollPaneForLeaveApply;
    private JTable tableForLeaveApply;
    private JButton buttonDispose;
    private JLabel notClassMangeTip;
    private JLabel disposeTip;
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
    // 获取公告并展示
    private void showNotice(){
        List<Notice> notice = null;
        try {
            notice = new TeacherServiceImpl().getNotice();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 往表格添加数据
        DefaultTableModel model = (DefaultTableModel) tableNotice.getModel();
        model.setRowCount(0);

        for (int i = notice.size() - 1; i >= 0; i--) {
            Notice n = notice.get(i);
            LocalDateTime publishDate = n.getPublishDate();
            // 格式化时间
            String date = publishDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Object[] row = new Object[]{
                    n.getNoticeId(),
                    n.getPublisher(),
                    date,
                    n.getTitle(),
                    n.getBody()
            };
            model.addRow(row);
        }
        // 设置居中对齐
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        tableNotice.setDefaultRenderer(Object.class, r);
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

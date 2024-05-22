/*
 * Created by JFormDesigner on Tue May 14 18:27:58 CST 2024
 */

package cn.qht2005.www.view.gui;

import java.awt.event.*;
import java.beans.*;
import javax.imageio.ImageIO;
import javax.swing.event.*;

import cn.qht2005.www.dao.CollegeMapper;
import cn.qht2005.www.pojo.Score;
import cn.qht2005.www.pojo.Student;
import cn.qht2005.www.service.impl.CollegeServiceImpl;
import cn.qht2005.www.service.impl.StudentServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import cn.qht2005.www.util.AliOSSUtil;
import cn.qht2005.www.util.ImgUtil;
import com.formdev.flatlaf.FlatLightLaf;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;


/**
 * @author 覃
 */
public class StudentControllerGui extends JFrame {
    private static final StudentServiceImpl studentService;     // 学生服务对象
    private static final TeacherServiceImpl teacherServiceImpl;     // 教师服务对象
    private File uploadPhoto;                                         // 上传的图片
    private String studentId;                                   // 学生id
    private Student student;   // 当前登录的学生对象
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
        upDateTimeNow();


    }

    private void thisPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
    }
    // 那啥玩意改变
    private void tabbedPaneMainStateChanged(ChangeEvent e) {
        int index = tabbedPaneMain.getSelectedIndex();
        if (index == 0) {
            showInfo();
        } else if (index == 1) {
            showScore();
        }
    }

    private void labelPhotoMouseClicked(MouseEvent e) {
        if ("完成".equals(buttonModify.getText())){
            uploadImage();
        }

    }
    // 修改按钮点击事件
    private void buttonModifyMouseClicked(MouseEvent e) {
        if ("修改".equals(buttonModify.getText())){
            buttonModify.setText("完成");
            buttonPrint.setText("取消");
            letInfoEditable(true);
        }else{
            try {
                upDateInfo();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            buttonModify.setText("修改");
            buttonPrint.setText("导出");
            letInfoEditable(false);
        }
    }
    // 提交修改个人信息
    private void upDateInfo() throws Exception{
        student.setStudentId(inputStudentId.getText());
        student.setAge(Integer.parseInt(inputAge.getText()));
        student.setPhoneNumber(inputPhoneNumber.getText());
        student.setAddress(inputAdrress.getText());
        if (uploadPhoto != null){
            // 把图片上传到阿里云OSS并获取url
            String photoUrl = AliOSSUtil.uploadFile(uploadPhoto);
            // 设置图片url
            student.setPhotograph(photoUrl);
        }
        // 提交修改
        studentService.modifyStudentByDynamic(studentId, student);
    }

    // 导出/取消按钮点击事件
    private void buttonPrintMouseClicked(MouseEvent e) {
        if (buttonPrint.getText().equals(("取消"))){
            // 那啥按钮是完成 也就是用户点击了修改
            // 取消事件逻辑
            cancelModify();
            return;

        }// 用户没点击过修改 那就是导出
        JOptionPane.showMessageDialog(this, "这功能突然感觉没l用，不实现了！！");
    }
    private void cancelModify(){
        // 取消修改
        student = teacherServiceImpl.getStudentById(studentId);
        showInfo();
        uploadPhoto = null;
        buttonModify.setText("修改");
        buttonPrint.setText("导出");
        student = teacherServiceImpl.getStudentById(studentId);
        letInfoEditable(false);
    }
    // 修改密码按钮被点击
    private void buttonModifyPasswordMouseClicked(MouseEvent e) {
        new ModifyPassword(studentId).setVisible(true);
    }
    // 导出按钮被点击事件
    private void button1MouseClicked(MouseEvent e) {
        // 导出所有成绩
        List<Score> scores = studentService.getScoreById(studentId);
        exportScore(scores);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tabbedPaneMain = new JTabbedPane();
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
        labelPhoto = new JLabel();
        label7 = new JLabel();
        inputCollegeId = new JTextField();
        label8 = new JLabel();
        inputClassTeacher = new JTextField();
        label9 = new JLabel();
        InputClassTeacherPhoneNumber = new JTextField();
        label10 = new JLabel();
        inputClassId = new JTextField();
        labelPhotoTip = new JLabel();
        label12 = new JLabel();
        labelTime = new JLabel();
        buttonModifyPassword = new JButton();
        panelScore = new JPanel();
        scrollPane1 = new JScrollPane();
        tableScore = new JTable();
        button1 = new JButton();

        //======== this ========
        setTitle("\u884c\u77e5\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf-\u5b66\u751f\u7528\u6237      by\u8983\u60e0\u901a");
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
                label1.setText("\u5b66\u53f7");
                panelInfo.add(label1);
                label1.setBounds(5, 45, 30, 15);

                //---- inputStudentId ----
                inputStudentId.setEditable(false);
                panelInfo.add(inputStudentId);
                inputStudentId.setBounds(40, 40, 110, inputStudentId.getPreferredSize().height);

                //---- label2 ----
                label2.setText("\u59d3\u540d");
                panelInfo.add(label2);
                label2.setBounds(165, 50, 30, 15);

                //---- inputStudentName ----
                inputStudentName.setEditable(false);
                panelInfo.add(inputStudentName);
                inputStudentName.setBounds(210, 40, 110, 34);

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
                label5.setBounds(5, 275, 70, 15);

                //---- inputPhoneNumber ----
                inputPhoneNumber.setEditable(false);
                panelInfo.add(inputPhoneNumber);
                inputPhoneNumber.setBounds(43, 265, 110, 30);

                //---- label6 ----
                label6.setText("\u8054\u7cfb\u5730\u5740");
                panelInfo.add(label6);
                label6.setBounds(155, 130, 65, 15);

                //---- inputAdrress ----
                inputAdrress.setEditable(false);
                panelInfo.add(inputAdrress);
                inputAdrress.setBounds(210, 120, 130, 34);

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
                labelPhoto.setBounds(345, 15, 195, 170);

                //---- label7 ----
                label7.setText("\u5b66\u9662");
                panelInfo.add(label7);
                label7.setBounds(5, 170, 30, 15);

                //---- inputCollegeId ----
                inputCollegeId.setEditable(false);
                panelInfo.add(inputCollegeId);
                inputCollegeId.setBounds(43, 160, 110, 30);

                //---- label8 ----
                label8.setText("\u73ed\u4e3b\u4efb");
                panelInfo.add(label8);
                label8.setBounds(5, 225, 45, 15);

                //---- inputClassTeacher ----
                inputClassTeacher.setEditable(false);
                panelInfo.add(inputClassTeacher);
                inputClassTeacher.setBounds(43, 215, 110, 30);

                //---- label9 ----
                label9.setText("\u73ed\u4e3b\u4efb\u8054\u7cfb\u7535\u8bdd");
                panelInfo.add(label9);
                label9.setBounds(165, 220, 90, 20);

                //---- InputClassTeacherPhoneNumber ----
                InputClassTeacherPhoneNumber.setEditable(false);
                panelInfo.add(InputClassTeacherPhoneNumber);
                InputClassTeacherPhoneNumber.setBounds(255, 215, 110, 30);

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
                buttonModifyPassword.setBounds(new Rectangle(new Point(165, 265), buttonModifyPassword.getPreferredSize()));
            }
            tabbedPaneMain.addTab("\u4e2a\u4eba\u4fe1\u606f", panelInfo);

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
                scrollPane1.setBounds(0, 35, 555, 440);

                //---- button1 ----
                button1.setText("\u5bfc\u51fa");
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                    }
                });
                panelScore.add(button1);
                button1.setBounds(5, 0, button1.getPreferredSize().width, 35);
            }
            tabbedPaneMain.addTab("\u4e2a\u4eba\u6210\u7ee9", panelScore);
        }
        contentPane.add(tabbedPaneMain);
        tabbedPaneMain.setBounds(0, 0, 770, 475);

        contentPane.setPreferredSize(new Dimension(615, 410));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
//        showScore();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTabbedPane tabbedPaneMain;
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
    private JLabel labelPhoto;
    private JLabel label7;
    private JTextField inputCollegeId;
    private JLabel label8;
    private JTextField inputClassTeacher;
    private JLabel label9;
    private JTextField InputClassTeacherPhoneNumber;
    private JLabel label10;
    private JTextField inputClassId;
    private JLabel labelPhotoTip;
    private JLabel label12;
    private JLabel labelTime;
    private JButton buttonModifyPassword;
    private JPanel panelScore;
    private JScrollPane scrollPane1;
    private JTable tableScore;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
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
        model.setRowCount(0); // 先删后增
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
        String classId = student.getClassId();
        Integer collegeId = student.getCollegeId();
        // 获取学院名称
        String collegeName = getCollegeName(collegeId);
        BufferedImage photograph; // 证件照url
        try {
            photograph = ImgUtil.getImgByUrl(student.getPhotograph());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ImageIcon photo = new ImageIcon(photograph);
        photo.setImage(photo.getImage().getScaledInstance(labelPhoto.getWidth(), labelPhoto.getHeight(), Image.SCALE_DEFAULT)); // 设置图片大小
        labelPhoto.setIcon(photo);  // 展示图片到标签
        // 展示到文本框
        inputStudentId.setText(studentId);
        inputStudentName.setText(name);
        inputAge.setText(age.toString());
        inputPhoneNumber.setText(phoneNumber);
        inputAdrress.setText(address);
        inputCollegeId.setText(collegeName);
        inputClassId.setText(classId);
        if (sex == 1){
            checkboxSex.setSelectedIndex(0);
        }else{
            checkboxSex.setSelectedIndex(1);
        }

    }
    // 让个人信息那一页的文本框可/不可编辑
    private void letInfoEditable(boolean flag){
        inputAge.setEditable(flag);
        inputPhoneNumber.setEditable(flag);
        inputAdrress.setEditable(flag);
        checkboxSex.setEnabled(flag);
        labelPhotoTip.setVisible(flag);
    }
    // 根据学院id获取学院名称
    private String getCollegeName(Integer collegeId){
        try {
            return new CollegeServiceImpl().getCollegeNameById(collegeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 导出所有成绩 成excel
    public void exportScore(List<Score> scores) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Scores");

        // 操作表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("课程编号");
        headerRow.createCell(1).setCellValue("课程名称");
        headerRow.createCell(2).setCellValue("成绩");

        // 行数据
        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(score.getCourseId());
            row.createCell(1).setCellValue(score.getCourseName());
            row.createCell(2).setCellValue(score.getScore());
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
            String fileName = fileToSave.getAbsolutePath() + "/" + studentId + "的成绩.xlsx"; // 导出的文件名，啊不，是路径。
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(this, "导出成功！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void upDateTimeNow(){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 格式化时间
        String time = now.getYear() + "年" + now.getMonthValue() +
                "月" + now.getDayOfMonth() + "日" + now.getHour() + "时" + now.getMinute() + "分" + now.getSecond() + "秒";
        labelTime.setText(time);
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

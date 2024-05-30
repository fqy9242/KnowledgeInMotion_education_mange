package cn.qht2005.www.util;

import cn.qht2005.www.service.impl.TeacherServiceImpl;

import java.time.LocalDate;
import java.util.Random;

/**
 * @author 覃
 * id工具类
 */
public class IdUtil {
	// 随机数
	private static final Random RANDOM = new Random();

	private IdUtil() {
	}
	// 生成教师工号
	public static String generateTeacherId(Integer collegeId) throws Exception {
		// 工号例子： 202301313321 年份+学院编号+教师编号（6位）
		// 获取当前年份
		int year = LocalDate.now().getYear();
		StringBuilder teacherId = new StringBuilder();
		while (true) {
			teacherId.delete(0, teacherId.length());
			teacherId.append(year);
			teacherId.append(collegeId);
			for (int i = 0; i < 6; i++){
				teacherId.append(RANDOM.nextInt(10));
			}
			if (new TeacherServiceImpl().getTeacherById(teacherId.toString()) == null) {
				break;
			}
		}
		return teacherId.toString();
	}
	// 生成学生学号
	public static String generateStudentId(Integer collegeId, String classId) throws Exception {
		//  年份+学院编号+班级编号+学生编号（7位）
		// 获取当前年份
		int year = LocalDate.now().getYear();
		StringBuilder studentId = new StringBuilder();
		while (true) {
			studentId.delete(0, studentId.length());
			studentId.append(year);
			studentId.append(collegeId);
			studentId.append(classId);
			for (int i = 0; i < 7; i++){
				studentId.append(RANDOM.nextInt(10));
			}
			if (new TeacherServiceImpl().getStudentById(studentId.toString()) == null) {
				break;
			}

		}
		return studentId.toString();
	}

}

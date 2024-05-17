package cn.qht2005.www.service;

import cn.qht2005.www.pojo.Student;

import java.util.List;

public interface TeacherService {
	/**
	 *
	 * @param teacherId
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
    boolean login(String teacherId, String passWord) throws Exception;
	Student getStudentById(String studentId);
	// 获取所有的学生信息
	List<Student> getAllStudent();
}

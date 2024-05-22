package cn.qht2005.www.service;

import cn.qht2005.www.pojo.Student;
import cn.qht2005.www.pojo.Teacher;

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
	// 动态查询学生
	List<Student> getStudentByDynamic(Student student);
	// 根据id 查询教师信息
	Teacher getTeacherById(String teacherId);
	// 根据教师对象修改教师信息
	boolean setTeacherByTeacher(Teacher teacher);
	// 修改教师密码
	boolean modifyPassWord(String teacherId, String passWord, String passWordNew);

}

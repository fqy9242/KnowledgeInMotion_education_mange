package com.gxnzd.qht.knowledgeinmotion.service;
import com.gxnzd.qht.knowledgeinmotion.pojo.Student;
import java.util.List;
/**
 * 管理员业务逻辑层接口
 */
public interface AdministratorService {
	/**
	 * 获取所有学生信息
	 * @return 学生信息列表
	 */
	List<Student> getAllStudent();
	/**
	 * 根据学生id获取学生信息
	 * @param studentId 学生id
	 * @return 学生信息
	 */
	Student getStudentById(Integer studentId);
	/**
	 * 根据学生姓名获取学生信息 模糊查询
	 * @param studentName 学生姓名
	 * @return 学生信息
	 */
	List<Student> getStudentByNameForLike(String studentName);


}

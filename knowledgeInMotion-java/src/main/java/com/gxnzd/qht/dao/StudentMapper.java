package com.gxnzd.qht.dao;

import com.gxnzd.qht.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
public interface StudentMapper {
	/**
	 *  根据id查询学生对象
	 * @param studentId
	 * @return
	 */
	@Select("select * from t_student where student_id = #{studentId}")
	@ResultMap("studentResultMap")
	public Student getStudentById(String studentId);
	/**
	 * 根据id和密码查询学生对象
	 * @param studentId  学号
	 * @param passWord 登录密码
	 * @return
	 */
	@Select("select * from t_student where student_id = #{studentId} and student_login_password = #{passWord}")
	@ResultMap("studentResultMap")
	public Student getStudentByIdAndPassword(@Param("studentId") String studentId, @Param("passWord") String passWord);
}

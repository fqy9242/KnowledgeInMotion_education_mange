package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.Student;
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
	Student getStudentByIdAndPassword(@Param("studentId") String studentId, @Param("passWord") String passWord);

	/**
	 * 动态修改学生信息
	 */
	Integer modifyByDynamic(@Param("studentId") String studentId, @Param("student") Student student);
	/**
	 * 修改学生密码
	 * @param studentId 学号
	 * @param passWord 原密码
	 * @param passWordNew 新密码
	 * @return
	 */
	@Select("update t_student set student_login_password = #{passWordNew} where student_id = #{studentId} and student_login_password = #{passWord}")
	@ResultMap("studentResultMap")
	Integer modifyPassWord(@Param("studentId") String studentId, @Param("passWord") String passWord, @Param("passWordNew") String passWordNew);


}

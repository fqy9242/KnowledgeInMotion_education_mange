package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.people.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {
	/**
	 *  根据id查询教师对象
	 * @param teacherId
	 * @return
	 */
	@Select("select * from t_teacher where teacher_id = #{teacherId}")
	@ResultMap("teacherResultMap")
	Teacher getTeacherById(String teacherId);
	/**
	 * 根据id和密码查询教师对象
	 * @param teacherId  工号
	 * @param passWord 登录密码
	 * @return
	 */
	@Select("select * from t_teacher where teacher_id = #{teacherId} and teacher_login_password = #{passWord}")
	@ResultMap("teacherResultMap")
	Teacher getTeacherByIdAndPassword(@Param("teacherId") String teacherId, @Param("passWord") String passWord);
	/**
	 * 根据teacher对象修改教师信息
	 */

	Integer modifyTeacherByTeacher (Teacher teacher);
	/**
	 * 修改教师密码
	 * @param teacherId 工号
	 * @param passWord 原密码
	 * @param passWordNew 新密码
	 * @return 返回影响行数？
	 */
	Integer modifyPassWord(@Param("teacherId") String teacherId, @Param("passWord") String passWord,
						   @Param("passWordNew") String passWordNew);

}

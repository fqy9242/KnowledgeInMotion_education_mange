package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.Teacher;
import cn.qht2005.www.pojo.Student;
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
	Student getTeacherById(String teacherId);
	/**
	 * 根据id和密码查询教师对象
	 * @param teacherId  工号
	 * @param passWord 登录密码
	 * @return
	 */
	@Select("select * from t_teacher where teacher_id = #{teacherId} and teacher_login_password = #{passWord}")
	@ResultMap("teacherResultMap")
	Teacher getTeacherByIdAndPassword(@Param("teacherId") String teacherId, @Param("passWord") String passWord);
}

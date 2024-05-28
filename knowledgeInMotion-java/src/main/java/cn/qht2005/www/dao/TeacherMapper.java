package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.people.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 覃
 */
public interface TeacherMapper {
	/**
	 *  根据id查询教师对象
	 * @param teacherId 工号
	 * @return 教师id
	 */
	@Select("select * from t_teacher where teacher_id = #{teacherId}")
	@ResultMap("teacherResultMap")
	Teacher getTeacherById(String teacherId);
	/**
	 * 根据id和密码查询教师对象
	 * @param teacherId  工号
	 * @param passWord 登录密码
	 * @return 教师对象
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

	/**
	 * 找回密码
	 *
	 * @param teacherId   教师工号
	 * @param teacherName 教师姓名
	 * @param phoneNumber 教师手机号
	 * @param passWordNew 新密码
	 */
	@Select("update t_teacher set teacher_login_password = #{passWordNew} where teacher_id = #{teacherId} " +
			"and teacher_name = #{teacherName} and teacher_phone_number = #{phoneNumber}")
	void updatePassWordForgot(@Param("teacherId") String teacherId,
								 @Param("teacherName") String teacherName,
								 @Param("phoneNumber") String phoneNumber,
								 @Param("passWordNew") String passWordNew);

	/**
	 * 根据管理班级id来查询教师对象
	 * @param manageClassId 管理班级id 如果不是班主任，则为null
	 * @return 教师实体对俩
	 */
	@Select("select * from t_teacher where teacher_mange_class_id = #{manageClassId}")
	@ResultMap("teacherResultMap")
	Teacher selectByMangeClassId(String manageClassId);
	/**
	 * 获取所有教师
	 * @return 教师列表
	 */
	@Select("select * from t_teacher")
	@ResultMap("teacherResultMap")
	List<Teacher> selectAll();


}



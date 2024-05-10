package com.gxnzd.qht.knowledgeinmotion.dao;


import com.gxnzd.qht.knowledgeinmotion.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
/**
 * 学生数据访问层接口
 */
public interface StudentMapper {
	/**
	 * 获取所有学生信息
	 * @return 学生信息列表
	 */

	@Select("select * from t_student")
	@ResultMap("studentResultMap")
	List<Student> selectAll();
	/**
	 * 根据学生id获取学生信息
	 * @param studentId 学生id
	 * @return 学生信息
	 */
	@Select("select * from t_student where student_id = #{studentId}")
	@ResultMap("studentResultMap")
	Student selectById(Integer studentId);
}

package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 课程持久层接口
 * @author 覃
 */
public interface CourseMapper {
	/**
	 * 查询所有课程
	 * @return 一个包含所有课程的List集合
	 */
	@Select("select * from t_course")
	@ResultMap("courseResultMap")
	List<Course> selectAll();

	/**
	 * 添加课程
	 * @param course 课程对象
	 */
	@Insert("insert into t_course(course_name, course_college_id) values(#{courseName}, #{collegeId})")
	void insertCourse(Course course);

	/**
	 * 根据课程id删除课程
	 * @param courseList 课程列表
	 */
	void deleteByCourseList(List<Course> courseList);

	/**
	 * 根据课程查询
	 * @param course 课程对象
	 * @return 课程列表
	 */
	List<Course>selectByCourse(Course course);


}

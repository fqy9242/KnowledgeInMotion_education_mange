package cn.qht2005.www.service;

import cn.qht2005.www.pojo.Course;
import cn.qht2005.www.pojo.Notice;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.pojo.people.Teacher;

import java.util.List;
import java.util.Map;

public interface AdministratorService {
	boolean login(String userName, String passWord) throws Exception;

	/**
	 * 获取教师各年龄段的人数
	 * @return 一个map集合，key为年龄段，value为人数
	 */
	Map<String, Integer> getTeacherCountByAge();
	/**
	 * 获取学生各学院的人数
	 * @return 一个map集合，key为学院名，value为人数
	 */
	Map<String, Integer> getStudentCountByCollege() throws Exception;

	/**
	 * 获取教师各职位的人数
	 * @return 一个map集合，key为职位，value为人数
	 */
	Map<String, Integer> getTeacherCountByPosition(List<Teacher> teachers);

	/**
	 *  添加教师
	 * @param teacher 教师对象
	 * @return 是否成功
	 */
	boolean addTeacher(Teacher teacher);

	/**
	 * 添加学生
	 * @param student 学生对象
	 * @return 是否成功
	 */
	boolean addStudent(Student student);

	/**
	 * 获取所有公告
	 * @return 公告列表
	 */
	List<Notice> getAllNotice();

	/**
	 * 发布公告
	 * @param notice 公告实体对俩
	 * @return 是否成功
	 */
	boolean addNotice(Notice notice);

	/**
	 * 根据条件查询通知
	 * @param notice 通知实体对象
	 * @return 符合条件的通知列表
	 */
	List<Notice>getNoticeByNotice(Notice notice);

	/**
	 * 批量删除公告
	 * @param noticeList 公告列表
	 * @return 是否成功
	 */
	boolean deleteNotices(List<Notice> noticeList);

	/**
	 * 根据学生列表批量删除学生
	 * @param studentList 学生列表
	 * @return 是否成功
	 */

	boolean deleteByStudentList(List<Student> studentList);

	/**
	 *  根据教师列表批量删除教师
	 * @param teacherList 教师列表
	 * @return 是否成功
	 */
	boolean deleteByTeacherList(List<Teacher> teacherList);

	/**
	 * 获取所有课程
	 * @return 课程列表
	 */
	List<Course> getAllCourse();

	/**
	 * 添加课程
	 * @param course 课程对象
	 * @return 是否成功
	 */
	boolean addCourse(Course course);

	/**
	 * 根据课程列表批量删除课程
	 * @param courseList 课程列表
	 * @return 是否成功
	 */
	boolean deleteByCourseList(List<Course> courseList);

	/**
	 * 根据课程查询
	 * @param course 课程对象
	 * @return 课程列表
	 */
	List<Course> getCourseByCourse(Course course);
}

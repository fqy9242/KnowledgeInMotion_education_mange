package cn.qht2005.www.service;

import cn.qht2005.www.pojo.Leave;
import cn.qht2005.www.pojo.Notice;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.pojo.people.Teacher;

import java.util.List;
import java.util.Map;

/**
 * @author 覃
 */
public interface TeacherService {
	/**
	 *
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
	// 获取公告
	List<Notice> getNotice();
	// 处理请假申请
	boolean disposeApply(Leave leave);
	// 根据班级获取请假列表
	List<Leave> getLeaveByClassId(String classId);
	// 根据请假id获取请假信息
	Leave getLeaveByLeaveId(String leaveId);
	// 找回密码
	boolean findPassWord(String teacherId, String teacherName, String phoneNumber, String passWordNew);
	// 根据管理班级id来获取教师实体对象
	Teacher getTeacherByManageClassId(String manageClassId);
	// 获取学生男女人数
	Map<String, Integer>getStudentCountBySex(List<Student> students);


}

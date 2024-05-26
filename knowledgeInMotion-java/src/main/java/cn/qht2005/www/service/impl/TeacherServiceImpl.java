package cn.qht2005.www.service.impl;

import cn.qht2005.www.dao.LeaveMapper;
import cn.qht2005.www.dao.NoticeMapper;
import cn.qht2005.www.dao.StudentMapper;
import cn.qht2005.www.pojo.Leave;
import cn.qht2005.www.pojo.Notice;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.pojo.people.Teacher;
import cn.qht2005.www.dao.TeacherMapper;
import cn.qht2005.www.service.TeacherService;
import cn.qht2005.www.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author 覃
 */
public class TeacherServiceImpl implements TeacherService {
	SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
	SqlSession sqlSession = sqlSessionFactory.openSession(true);
	// 通过sqlSession获取学生mapper接口的代理对象
	private final StudentMapper STUDENT_MAPPER = sqlSession.getMapper(StudentMapper.class);
	// 通过sqlSession获取教师mapper接口的代理对象
	private final TeacherMapper TEACHER_MAPPER = sqlSession.getMapper(TeacherMapper.class);
	// 通过sqlSession获取公告mapper接口的代理对象
	private final NoticeMapper NOTICE_MAPPER = sqlSession.getMapper(NoticeMapper.class);
	// 获取请假的映射代理对象
	private final LeaveMapper LEAVE_MAPPER = sqlSession.getMapper(LeaveMapper.class);

    public TeacherServiceImpl() throws Exception {

    }

    /**
	 * 教师服务层实现类
	 */
	@Override
	public boolean login(String studentId, String passWord) {
		Teacher res = TEACHER_MAPPER.getTeacherByIdAndPassword(studentId, passWord);
		return res != null;
	}


	@Override
	public Student getStudentById(String studentId) {
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
		Student res = studentMapper.getStudentById(studentId);
		return res;
	}

	@Override
	public List<Student> getAllStudent() {
		return STUDENT_MAPPER.selectAll();
	}

	@Override
	public List<Student> getStudentByDynamic(Student student) {
		return STUDENT_MAPPER.selectDynamic(student);
	}

	/**
	 * 根据id 查询教师信息
	 * @param teacherId 教师工号
	 * @return 教师实体对象
	 */
	@Override
	public Teacher getTeacherById(String teacherId) {
		return TEACHER_MAPPER.getTeacherById(teacherId);
	}
	/**
	 * 根据教师对象修改教师信息
	 * @param teacher 教师对象
	 * @return 是否修改成功
	 */
	@Override
	public boolean setTeacherByTeacher(Teacher teacher) {
		try {
			TEACHER_MAPPER.modifyTeacherByTeacher(teacher);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 修改教师密码
	 * @param teacherId 教师工号
	 * @param passWord 原密码
	 * @param passWordNew 新密码
	 * @return 是否修改成功
	 */
	@Override
	public boolean modifyPassWord(String teacherId, String passWord, String passWordNew) {
		try {
			TEACHER_MAPPER.modifyPassWord(teacherId, passWord, passWordNew);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Notice> getNotice() {
		List<Notice> noticeForTeacher = NOTICE_MAPPER.getByRecipient(Short.valueOf("0"));
		noticeForTeacher.addAll(NOTICE_MAPPER.getByRecipient(Short.valueOf("2")));
		return noticeForTeacher;
	}
	/**
	 * 处理请假申请
	 * @param leave 请假实体对象
	 * @return 是否处理成功
	 */
	@Override
	public boolean disposeApply(Leave leave) {
		try {
			LEAVE_MAPPER.updateByLeave(leave);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Leave> getLeaveByClassId(String classId) {
		return LEAVE_MAPPER.selectByClassId(classId);
	}
	/**
	 * 根据请假id获取请假信息
	 * @param leaveId 请假id
	 * @return 请假实体对象
	 */
	@Override
	public Leave getLeaveByLeaveId(String leaveId) {
		return LEAVE_MAPPER.selectByLeaveId(leaveId);
	}
	// 找回密码
	@Override
	public boolean findPassWord(String teacherId, String teacherName, String phoneNumber, String passWordNew) {
		try {
			TEACHER_MAPPER.updatePassWordForgot(teacherId, teacherName, phoneNumber, passWordNew);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	// 根据管理班级id来获取教师实体对象
	@Override
	public Teacher getTeacherByManageClassId(String manageClassId) {
		return TEACHER_MAPPER.selectByMangeClassId(manageClassId);
	}

}

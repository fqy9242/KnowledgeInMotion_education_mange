package cn.qht2005.www.service.impl;

import cn.qht2005.www.dao.StudentMapper;
import cn.qht2005.www.pojo.Student;
import cn.qht2005.www.pojo.Teacher;
import cn.qht2005.www.dao.TeacherMapper;
import cn.qht2005.www.service.TeacherService;
import cn.qht2005.www.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
	SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
	SqlSession sqlSession = sqlSessionFactory.openSession(true);
	// 通过sqlSession获取学生mapper接口的代理对象
	private final StudentMapper STUDENT_MAPPER = sqlSession.getMapper(StudentMapper.class);
	// 通过sqlSession获取教师mapper接口的代理对象
	private final TeacherMapper TEACHER_MAPPER = sqlSession.getMapper(TeacherMapper.class);

    public TeacherServiceImpl() throws Exception {

    }

    /**
	 * 教师服务层实现类
	 * @param studentId
	 * @param passWord
	 * @return
     */
	@Override
	public boolean login(String studentId, String passWord) {
		Teacher res = TEACHER_MAPPER.getTeacherByIdAndPassword(studentId, passWord);
		if (res == null) {
			return false;
		}
		return true;
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
}

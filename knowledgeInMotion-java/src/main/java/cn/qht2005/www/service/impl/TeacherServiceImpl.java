package cn.qht2005.www.service.impl;

import cn.qht2005.www.dao.StudentMapper;
import cn.qht2005.www.pojo.Student;
import cn.qht2005.www.pojo.Teacher;
import cn.qht2005.www.dao.TeacherMapper;
import cn.qht2005.www.service.TeacherService;
import cn.qht2005.www.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TeacherServiceImpl implements TeacherService {
	SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
	SqlSession sqlSession = sqlSessionFactory.openSession();

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

		TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
		Teacher res = mapper.getTeacherByIdAndPassword(studentId, passWord);
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
}

package cn.qht2005.www.service.impl;

import cn.qht2005.www.pojo.Teacher;
import cn.qht2005.www.dao.TeacherMapper;
import cn.qht2005.www.service.StudentService;
import cn.qht2005.www.service.TeacherService;
import cn.qht2005.www.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TeacherServiceImpl implements TeacherService {
	/**
	 * 教师服务层实现类
	 * @param studentId
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean login(String studentId, String passWord) throws Exception {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
		Teacher res = mapper.getTeacherByIdAndPassword(studentId, passWord);
		if (res == null) {
			return false;
		}
		return true;
	}
}

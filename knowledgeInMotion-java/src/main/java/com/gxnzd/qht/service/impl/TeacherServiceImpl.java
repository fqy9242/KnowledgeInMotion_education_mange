package com.gxnzd.qht.service.impl;

import com.gxnzd.qht.dao.StudentMapper;
import com.gxnzd.qht.dao.TeacherMapper;
import com.gxnzd.qht.pojo.Student;
import com.gxnzd.qht.pojo.Teacher;
import com.gxnzd.qht.service.StudentService;
import com.gxnzd.qht.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TeacherServiceImpl implements StudentService {
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

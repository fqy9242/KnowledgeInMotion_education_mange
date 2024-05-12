package com.gxnzd.qht.service.impl;

import com.gxnzd.qht.dao.StudentMapper;
import com.gxnzd.qht.pojo.Student;
import com.gxnzd.qht.service.StudentService;
import com.gxnzd.qht.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class StudentServiceImpl implements StudentService {
	@Override
	public boolean login(String studentId, String passWord) throws Exception {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		Student res = mapper.getStudentByIdAndPassword(studentId, passWord);
		if (res == null) {
			return false;
		}
		return true;
	}
}

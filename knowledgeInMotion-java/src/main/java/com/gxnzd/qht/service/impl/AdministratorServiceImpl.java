package com.gxnzd.qht.service.impl;

import com.gxnzd.qht.dao.AdministratorMapper;
import com.gxnzd.qht.dao.TeacherMapper;
import com.gxnzd.qht.pojo.Administrator;
import com.gxnzd.qht.pojo.Teacher;
import com.gxnzd.qht.service.AdministratorService;
import com.gxnzd.qht.service.StudentService;
import com.gxnzd.qht.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class AdministratorServiceImpl implements AdministratorService {
	@Override
	public boolean login(String userName, String passWord) throws Exception {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AdministratorMapper mapper = sqlSession.getMapper(AdministratorMapper.class);
		Administrator res = mapper.getAdministratorByIdAndPassword(userName, passWord);
		if (res == null) {
			return false;
		}
		return true;
	}
}

package cn.qht2005.www.service.impl;

import cn.qht2005.www.dao.AdministratorMapper;
import cn.qht2005.www.pojo.Administrator;
import cn.qht2005.www.util.SqlSessionFactoryUtil;
import cn.qht2005.www.service.AdministratorService;
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

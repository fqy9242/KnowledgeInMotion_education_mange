package cn.qht2005.www.service.impl;

import cn.qht2005.www.dao.AdministratorMapper;
import cn.qht2005.www.dao.TeacherMapper;
import cn.qht2005.www.pojo.people.Administrator;
import cn.qht2005.www.pojo.people.Teacher;
import cn.qht2005.www.util.SqlSessionFactoryUtil;
import cn.qht2005.www.service.AdministratorService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministratorServiceImpl implements AdministratorService {
	// 教师服务对象映射对象
	private static TeacherMapper TEACHER_MAPPER = null;
	static {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
			TEACHER_MAPPER = sqlSession.getMapper(TeacherMapper.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
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
	// 获取教师各年龄段人数 返回一个map集合，key为年龄段，value为人数
	@Override
	public Map<String, Integer> getTeacherCountByAge() {
		// 获取包含所有教职工对象的list列表
		List<Teacher> teachers = TEACHER_MAPPER.selectAll();
		// 创建一个map集合，用于存放各年龄段的人数
		Map<String, Integer> map = new HashMap<>();
		// 遍历教职工列表
		teachers.forEach(teacher -> {
			// 获取教职工年龄
			int age = teacher.getAge();
			if (age < 35){
				map.put("under35", map.getOrDefault("under35", 0) + 1);
			}else if (age < 50) {
				map.put("35to50", map.getOrDefault("35to50", 0) + 1);
			}else {
				map.put("above50", map.getOrDefault("above50", 0) + 1);
			}
		});

		return map;
	}
}

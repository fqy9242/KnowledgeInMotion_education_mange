package cn.qht2005.www.service.impl;

import cn.qht2005.www.dao.CollegeMapper;
import cn.qht2005.www.pojo.College;
import cn.qht2005.www.service.CollegeService;
import cn.qht2005.www.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
/**
 * 学院服务实现类
 * @author 覃
 */
public class CollegeServiceImpl implements CollegeService {
	/**
	 * 获取所有学院
	 */
	private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
	private final SqlSession session = sqlSessionFactory.openSession();
	private CollegeMapper mapper = session.getMapper(CollegeMapper.class);

	public CollegeServiceImpl() throws Exception {
	}

	/**
	 * 获取所有学院
	 */
	@Override
	public List<College> getAllCollege() {
		return mapper.selectAll();
	}

	/**
	 * 根据学院id查询学院名称
	 * @param college
	 * @return
	 */
	@Override
	public String getCollegeNameById(int college) {
		return mapper.selectCollegeNameById(college).getCollegeName();
	}

	@Override
	public int getCollegeIdByName(String collegeName) {
		return mapper.selectCollegeIdByName(collegeName).getCollegeId();
	}
}

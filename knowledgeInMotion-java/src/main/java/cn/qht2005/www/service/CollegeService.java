package cn.qht2005.www.service;

import cn.qht2005.www.pojo.College;

import java.util.List;

/**
 * 学院服务接口
 * @author 覃
 */
public interface CollegeService {
	/**
	 * 获取所有学院
	 */
	List<College> getAllCollege();
	/**
	 * 根据学院id查询学院名称
	 * @param collegeId 学院id
	 * @return 学院名称
	 */
	String getCollegeNameById(int collegeId);
}

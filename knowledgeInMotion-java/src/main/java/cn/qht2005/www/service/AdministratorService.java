package cn.qht2005.www.service;

import java.util.Map;

public interface AdministratorService {
	boolean login(String userName, String passWord) throws Exception;

	/**
	 * 获取教师各年龄段的人数
	 * @return 一个map集合，key为年龄段，value为人数
	 */
	Map<String, Integer> getTeacherCountByAge();

}

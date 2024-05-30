package cn.qht2005.www.service;

import cn.qht2005.www.pojo.people.Teacher;

import java.util.List;
import java.util.Map;

public interface AdministratorService {
	boolean login(String userName, String passWord) throws Exception;

	/**
	 * 获取教师各年龄段的人数
	 * @return 一个map集合，key为年龄段，value为人数
	 */
	Map<String, Integer> getTeacherCountByAge();
	/**
	 * 获取学生各学院的人数
	 * @return 一个map集合，key为学院名，value为人数
	 */
	Map<String, Integer> getStudentCountByCollege() throws Exception;

	/**
	 * 获取教师各职位的人数
	 * @return 一个map集合，key为职位，value为人数
	 * @throws Exception 异常它老爹
	 */
	Map<String, Integer> getTeacherCountByPosition(List<Teacher> teachers);

}

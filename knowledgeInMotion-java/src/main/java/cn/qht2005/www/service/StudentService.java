package cn.qht2005.www.service;

import cn.qht2005.www.pojo.Score;

import java.util.List;

public interface StudentService {
	/**
	 * 学生登录
	 * @param studentId
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
	boolean login(String studentId, String passWord) throws Exception;
	/**
	 * 根据学生id查询所有科目成绩
	 * @param studentId
	 * @return
	 */
	List<Score> getScoreById(String studentId);
}

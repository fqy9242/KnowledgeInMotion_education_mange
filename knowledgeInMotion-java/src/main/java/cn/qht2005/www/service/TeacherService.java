package cn.qht2005.www.service;

public interface TeacherService {
	/**
	 *
	 * @param teacherId
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
	public boolean login(String teacherId, String passWord) throws Exception;
}

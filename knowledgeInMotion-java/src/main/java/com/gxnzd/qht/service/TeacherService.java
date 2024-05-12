package com.gxnzd.qht.service;

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

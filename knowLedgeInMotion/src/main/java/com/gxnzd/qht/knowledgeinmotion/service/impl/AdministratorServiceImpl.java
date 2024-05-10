package com.gxnzd.qht.knowledgeinmotion.service.impl;
import com.gxnzd.qht.knowledgeinmotion.dao.StudentMapper;
import com.gxnzd.qht.knowledgeinmotion.pojo.Student;
import com.gxnzd.qht.knowledgeinmotion.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员业务逻辑层实现类
 * @author 覃
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
	@Autowired
	private StudentMapper studentMapper;
	/**
	 * 获取所有学生信息
	 * @return 学生信息列表
	 */
	@Override
	public List<Student> getAllStudent() {
		return studentMapper.selectAll();
	}
	/**
	 * 根据学生id获取学生信息
	 * @param studentId 学生id
	 * @return 学生信息
	 */
	@Override
	public Student getStudentById(Integer studentId) {
		return null;
	}
	/**
	 * 根据学生姓名获取学生信息 模糊查询
	 * @param studentName 学生姓名
	 * @return 学生信息
	 */
	@Override
	public List<Student> getStudentByNameForLike(String studentName) {
		return null;
	}
}

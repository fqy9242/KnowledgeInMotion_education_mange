package cn.qht2005.www.service.impl;

import cn.qht2005.www.dao.ScoreMapper;
import cn.qht2005.www.dao.StudentMapper;
import cn.qht2005.www.pojo.Score;
import cn.qht2005.www.util.SqlSessionFactoryUtil;
import cn.qht2005.www.pojo.Student;
import cn.qht2005.www.service.StudentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StudentServiceImpl implements StudentService {
	SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
	SqlSession sqlSession = sqlSessionFactory.openSession();
	StudentMapper StudentMapper = sqlSession.getMapper(StudentMapper.class);
	ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);

	public StudentServiceImpl() throws Exception {
	}

	@Override
	public boolean login(String studentId, String passWord) throws Exception {
		Student res = StudentMapper.getStudentByIdAndPassword(studentId, passWord);
		if (res == null) {
			return false;
		}
		return true;
	}
	/**
	 * 根据学生id查询所有科目成绩
	 * @param studentId
	 * @return
	 */
	@Override
	public List<Score> getScoreById(String studentId) {
		List<Score> scores = scoreMapper.selectAllScoreByStudentId(studentId);
		return scores;
	}
}

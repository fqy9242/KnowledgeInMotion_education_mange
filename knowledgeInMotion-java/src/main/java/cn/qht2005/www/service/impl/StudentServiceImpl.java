package cn.qht2005.www.service.impl;

import cn.qht2005.www.dao.LeaveMapper;
import cn.qht2005.www.dao.NoticeMapper;
import cn.qht2005.www.dao.ScoreMapper;
import cn.qht2005.www.dao.StudentMapper;
import cn.qht2005.www.pojo.Leave;
import cn.qht2005.www.pojo.Notice;
import cn.qht2005.www.pojo.Score;
import cn.qht2005.www.util.SqlSessionFactoryUtil;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.service.StudentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author 覃
 */
public class StudentServiceImpl implements StudentService {
	SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
	SqlSession sqlSession = sqlSessionFactory.openSession(true);
	private final StudentMapper STUDENT_MAPPER = sqlSession.getMapper(StudentMapper.class);
	ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);
	private final NoticeMapper NOTICEMAPPER = sqlSession.getMapper(NoticeMapper.class);
	// 请假映射对象
	private final LeaveMapper LEAVE_MAPPER = sqlSession.getMapper(LeaveMapper.class);
	public StudentServiceImpl() throws Exception {
	}

	@Override
	public boolean login(String studentId, String passWord) throws Exception {
		Student res = STUDENT_MAPPER.getStudentByIdAndPassword(studentId, passWord);
		if (res == null) {
			return false;
		}
		return true;
	}
	/**
	 * 根据学生id查询所有科目成绩
	 * @param studentId 学号
	 * @return 成绩列表
	 */
	@Override
	public List<Score> getScoreById(String studentId) {
		return scoreMapper.selectAllScoreByStudentId(studentId);
	}

	@Override
	public Student modifyStudentByDynamic(String studentId, Student student) {
		Integer upDateRow = STUDENT_MAPPER.modifyByDynamic(studentId, student);
		if (upDateRow == 1){
			// 更新成功
			return STUDENT_MAPPER.getStudentById(studentId);
		}
		return null;
	}

	@Override
	public boolean modifyPassWord(String studentId, String passWord, String passWordNew) {
		try {
			return STUDENT_MAPPER.modifyPassWord(studentId, passWord, passWordNew) == 1;
		} catch (Exception e) {
			return false;
		}
	}
	// 获取所有学生可以接收的公告
	@Override
	public List<Notice> getNotice() {
		// 获取接收对象为全体师生的公告列表
		List<Notice> notices = NOTICEMAPPER.getByRecipient(Short.valueOf("0"));
		// 获取接收列表为学生的公告列表 然后和上面的拼接在一起
		notices.addAll(NOTICEMAPPER.getByRecipient(Short.valueOf("1")));
		return notices;
	}
	// 学生请假
	@Override
	public boolean leaveForStudent(Leave leave) {
		try {
			LEAVE_MAPPER.insertByLeave(leave);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Leave> getLeaveByStudentId(String studentId) {
		return LEAVE_MAPPER.selectByUserId(studentId);
	}
	// 找回密码
	@Override
	public boolean updatePassWordForgot(String studentId, String studentName, String phoneNumber, String passWordNew) {
		try {
			STUDENT_MAPPER.updatePassWordForgot(studentId, studentName, phoneNumber, passWordNew);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}

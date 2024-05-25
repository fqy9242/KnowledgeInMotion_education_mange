package cn.qht2005.www.service;

import cn.qht2005.www.pojo.Leave;
import cn.qht2005.www.pojo.Notice;
import cn.qht2005.www.pojo.Score;
import cn.qht2005.www.pojo.people.Student;

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

	/**
	 *  动态修改学生信息
	 * @param studentId
	 * @param student
	 * @return
	 */
	Student modifyStudentByDynamic(String studentId, Student student);
	/**
	 * 修改学生密码
	 * @param studentId 学号
	 * @param passWord	原密码
	 * @param passWordNew	新密码
	 * @return 影响行数
	 */
	Integer modifyPassWord(String studentId, String passWord, String passWordNew);

	/**
	 * 学生对象获取公告
	 * @return  学生接收的公告内容
	 */
	List<Notice>getNotice();
	/**
	 * 学生请假
	 * @param leave 请假对象
	 * @return 是否申请成功
	 */
	boolean leaveForStudent(Leave leave);
	/**
	 * 根据学生id查询请假数据
	 * @param studentId 学号
	 * @return 请假数据
	 */
	List<Leave> getLeaveByStudentId(String studentId);

}

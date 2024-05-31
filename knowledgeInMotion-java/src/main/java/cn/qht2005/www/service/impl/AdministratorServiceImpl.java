package cn.qht2005.www.service.impl;

import cn.qht2005.www.dao.AdministratorMapper;
import cn.qht2005.www.dao.NoticeMapper;
import cn.qht2005.www.dao.StudentMapper;
import cn.qht2005.www.dao.TeacherMapper;
import cn.qht2005.www.pojo.Notice;
import cn.qht2005.www.pojo.people.Administrator;
import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.pojo.people.Teacher;
import cn.qht2005.www.util.SqlSessionFactoryUtil;
import cn.qht2005.www.service.AdministratorService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministratorServiceImpl implements AdministratorService {
	// 教师数据对象映射对象
	private static TeacherMapper TEACHER_MAPPER = null;
	// 学生数据映射对象
	private static StudentMapper STUDENT_MAPPER = null;
	// 公告数据映射对象
	private static NoticeMapper NOTICE_MAPPER = null;
	static {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
			TEACHER_MAPPER = sqlSession.getMapper(TeacherMapper.class);
			STUDENT_MAPPER = sqlSession.getMapper(StudentMapper.class);
			NOTICE_MAPPER = sqlSession.getMapper(NoticeMapper.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	@Override
	public boolean login(String userName, String passWord) throws Exception {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AdministratorMapper mapper = sqlSession.getMapper(AdministratorMapper.class);
		Administrator res = mapper.getAdministratorByIdAndPassword(userName, passWord);
		if (res == null) {
			return false;
		}
		return true;
	}
	// 获取教师各年龄段人数 返回一个map集合，key为年龄段，value为人数
	@Override
	public Map<String, Integer> getTeacherCountByAge() {
		// 获取包含所有教职工对象的list列表
		List<Teacher> teachers = TEACHER_MAPPER.selectAll();
		// 创建一个map集合，用于存放各年龄段的人数
		Map<String, Integer> map = new HashMap<>();
		// 遍历教职工列表
		teachers.forEach(teacher -> {
			// 获取教职工年龄
			if (teacher.getAge() != null){
				int age = teacher.getAge();
				if (age < 35){
					map.put("under35", map.getOrDefault("under35", 0) + 1);
				}else if (age < 50) {
					map.put("35to50", map.getOrDefault("35to50", 0) + 1);
				}else {
					map.put("above50", map.getOrDefault("above50", 0) + 1);
				}
			}

		});

		return map;
	}

	/**
	 * 获取各学院学生人数
	 * @return 一个map集合，key为学院名，value为人数
	 */
	@Override
	public Map<String, Integer> getStudentCountByCollege() throws Exception {
		// 创建一个用于存放各学院人数的map集合
		Map<String, Integer> map = new HashMap<>();
		// 获取所有学生
		List<Student> students = STUDENT_MAPPER.selectAll();
		students.forEach(student -> {
			// 获取学生的学院名
			try {
				String collegeName = new CollegeServiceImpl().getCollegeNameById(student.getCollegeId());
				// 将学生的学院名和人数存入map集合
				map.put(collegeName, map.getOrDefault(collegeName, 0) + 1);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		return map;
	}

	/**
	 *  获取教师各职位人数
	 * @return 一个map集合
	 * @throws Exception
	 */

	@Override
	public Map<String, Integer> getTeacherCountByPosition(List<Teacher> teachers){
		// 存放人数的集合
		Map<String, Integer> map = new HashMap<>();
/*		// 获取所有教师
		List<Teacher> teachers = TEACHER_MAPPER.selectAll();*/
		teachers.forEach(t -> {
			// 获取教师的职位
			String positionName = t.getPosition();
			// 将其放入集合
			map.put(positionName, map.getOrDefault(positionName, 0) +1);
		});

		return map;
	}

	/**
	 * 添加教师
	 * @param teacher 教师对象
	 * @return 是否成功
	 */
	@Override
	public boolean addTeacher(Teacher teacher) {
		try {
			TEACHER_MAPPER.insertTeacher(teacher);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addStudent(Student student) {
		try {
			STUDENT_MAPPER.insertStudent(student);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取所有公告
	 * @return 公告列表
	 */
	@Override
	public List<Notice> getAllNotice() {
		return NOTICE_MAPPER.selectAll();
	}

	/**
	 *  发布公告
	 * @param notice 公告实体对象
	 * @return 是否成功
	 */
	@Override
	public boolean addNotice(Notice notice) {
		try {
			NOTICE_MAPPER.insert(notice);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Notice> getNoticeByNotice(Notice notice) {
		return NOTICE_MAPPER.selectByNotice(notice);
	}

	/**
	 * 批量删除公告
	 * @param noticeList 公告列表
	 * @return 是否成功
	 */
	@Override
	public boolean deleteNotices(List<Notice> noticeList) {
		try {
			NOTICE_MAPPER.deleteByNoticeList(noticeList);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

}

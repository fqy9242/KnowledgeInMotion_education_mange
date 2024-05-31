import cn.qht2005.www.pojo.people.Student;
import cn.qht2005.www.pojo.people.Teacher;
import cn.qht2005.www.service.impl.AdministratorServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class TestAdministrator {
	/**
	 * 管理员 测试类
	 */

	@Test
	public void testCountTeacherByAge(){
		Map<String, Integer> teacherCountByAge = new AdministratorServiceImpl().getTeacherCountByAge();
		System.out.println(teacherCountByAge);
	}
	@Test
	// 测试获取学生各学院的人数
	public void testCountStudentByCollege() throws Exception {
		Map<String, Integer> studentCountByCollege = new AdministratorServiceImpl().getStudentCountByCollege();
		System.out.println(studentCountByCollege);
	}
	@Test
	// 测试获取教师各职位人数
	public void testCountTeacherByPosition() throws Exception{
		List<Teacher> teachers = new TeacherServiceImpl().getAllTeacher();
		Map<String, Integer> teacherCountByPosition = new AdministratorServiceImpl().getTeacherCountByPosition(teachers);
		System.out.println(teacherCountByPosition);
	}

	// 测试删除多个学生
	@Test
	public void testDeleteStudents() {
		Student s1 = new Student();
		s1.setStudentId("202411113108299");
		Student s2 = new Student();
		s2.setStudentId("2024211111125815");
		List<Student> studentIds = List.of(s1, s2);
		boolean result = new AdministratorServiceImpl().deleteByStudentList(studentIds);
		if (result) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
	}


}

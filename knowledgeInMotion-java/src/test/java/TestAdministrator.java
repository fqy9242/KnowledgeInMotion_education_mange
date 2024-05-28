import cn.qht2005.www.service.impl.AdministratorServiceImpl;
import org.junit.jupiter.api.Test;

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

}

import cn.qht2005.www.pojo.Student;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestForTeacher {
	/**
	 * 测试学生登录
	 * author:覃惠通
	 */
	@Test
	public void testForTeacherLogin() throws Exception {
		boolean res = new TeacherServiceImpl().login("2001333", "iloveChina");
		assertTrue(res);
	}
	@Test
	public void testgetStudentById() throws Exception {
		Student s = new TeacherServiceImpl().getStudentById("2331020130229");
		assertNotNull(s);
	}
	@Test
	public void testGetTime(){
		LocalDateTime now = LocalDateTime.now();
		String s = now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));
		System.out.println(s);

	}
}

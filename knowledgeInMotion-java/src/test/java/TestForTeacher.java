import cn.qht2005.www.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;

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
}

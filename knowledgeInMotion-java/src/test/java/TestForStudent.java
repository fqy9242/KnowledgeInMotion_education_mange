import cn.qht2005.www.pojo.Score;
import cn.qht2005.www.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestForStudent {
	/**
	 * 测试学生登录
	 * author:覃惠通
	 */
	@Test
	public void testForStudentLogin() throws Exception {
		boolean res = new StudentServiceImpl().login("2331020130229", "qht123");
		assertTrue(res);
	}
	/*
	 * 测试学生查看自己的成绩
	 */
	@Test

	public void testForgetScore() throws Exception {
		List<Score> scores = new StudentServiceImpl().getScoreById("2331020130229");
		System.out.println(scores);

	}
}

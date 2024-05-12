import com.gxnzd.qht.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
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
}

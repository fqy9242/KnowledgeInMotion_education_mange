import cn.qht2005.www.pojo.Notice;
import cn.qht2005.www.service.impl.StudentServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestForNotice {
/**
 * 公告测试类
 * @author 覃
 */

	/**
	 * 测试根据接收者查询通知
	 */
	@Test
	public void testGetNoticeByRecipientForTeacher() throws Exception {
		List<Notice> notice = new TeacherServiceImpl().getNotice();
		System.out.println(notice);
	}

	/**
	 * 测试 学生那啥 。
	 * @throws Exception
	 */
	@Test
	public void testGetNoticeByRecipientForStudent() throws Exception {
		List<Notice> notice = new StudentServiceImpl().getNotice();
		System.out.println(notice);
	}
}

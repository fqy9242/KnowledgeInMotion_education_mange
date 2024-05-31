import cn.qht2005.www.pojo.Notice;
import cn.qht2005.www.service.impl.AdministratorServiceImpl;
import cn.qht2005.www.service.impl.StudentServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
	// 测试批量删除通知
	@Test
	public void testDeleteNotices(){
		List<Notice> notices = new ArrayList<>();
		Notice notice1 = new Notice();
		notice1.setNoticeId(2);
		notices.add(notice1);
		Notice notice2 = new Notice();
		notice2.setNoticeId(3);
		notices.add(notice2);
		boolean result = new AdministratorServiceImpl().deleteNotices(notices);
		if (result) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}

	}

}

import cn.qht2005.www.pojo.Leave;
import cn.qht2005.www.service.impl.StudentServiceImpl;
import cn.qht2005.www.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TestLeave {
	// 测试请假功能
	@Test
	public void testLeaveForStudent() throws Exception {
		Leave leave = new Leave();
		leave.setUserType((short) 1);
		leave.setUserId("2331020130229");
		leave.setCollegeId(1);
		leave.setClassId("39");
		leave.setLeaveType((short) 1);
		leave.setLeaveReason("身体舒服");
		leave.setLeaveStartTime(LocalDateTime.now());
		leave.setLeaveEndTime(LocalDateTime.now().plusDays(3));
		StudentServiceImpl studentService = new StudentServiceImpl();
		boolean result = studentService.leaveForStudent(leave);
		if (result) {
			System.out.println("请假成功");
		} else {
			System.out.println("请假失败");
		}
	}
	// 测试教师批假
	@Test
	public void testForTeacherDisposeLeave() throws Exception {
		Leave leave = new Leave();
		leave.setLeaveId(1);
		leave.setApplicationStatus((short) 0);
		leave.setResponse("注意休息！");
		boolean result = new TeacherServiceImpl().disposeApply(leave);
		if (result) {
			System.out.println("批假成功");
		} else {
			System.out.println("批假失败");
		}
	}
}

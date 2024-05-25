package cn.qht2005.www.pojo;

import java.time.LocalDateTime;

/**
 * @author 覃
 * 请假那啥实体类
 */
public class Leave {
	// 请假id
	private Integer leaveId;
	// 请假人类型
	private Short userType;
	// 请假人id
	private String userId;
	// 请假人学院id
	private Integer collegeId;
	// 请假人班级id
	private String classId;
	// 请假类型
	private Short leaveType;
	// 请假理由
	private String leaveReason;
	// 请假开始时间
	private LocalDateTime leaveStartTime;
	// 请假结束时间
	private LocalDateTime leaveEndTime;
	// 申请状态
	private Short applicationStatus;
	// 回复
	private String response;

	public Leave() {
	}

	public Leave(Integer leaveId, Short userType, String userId, Integer collegeId, String classId, Short leaveType,
				 String leaveReason, LocalDateTime leaveStartTime, LocalDateTime leaveEndTime,
				 Short applicationStatus, String response) {
		this.leaveId = leaveId;
		this.userType = userType;
		this.userId = userId;
		this.collegeId = collegeId;
		this.classId = classId;
		this.leaveType = leaveType;
		this.leaveReason = leaveReason;
		this.leaveStartTime = leaveStartTime;
		this.leaveEndTime = leaveEndTime;
		this.applicationStatus = applicationStatus;
		this.response = response;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Short getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(Short leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public LocalDateTime getLeaveStartTime() {
		return leaveStartTime;
	}

	public void setLeaveStartTime(LocalDateTime leaveStartTime) {
		this.leaveStartTime = leaveStartTime;
	}

	public LocalDateTime getLeaveEndTime() {
		return leaveEndTime;
	}

	public void setLeaveEndTime(LocalDateTime leaveEndTime) {
		this.leaveEndTime = leaveEndTime;
	}

	public Short getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(Short applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Leave{" +
				"leaveId=" + leaveId +
				", userType=" + userType +
				", userId='" + userId + '\'' +
				", collegeId=" + collegeId +
				", classId='" + classId + '\'' +
				", leaveType=" + leaveType +
				", leaveReason='" + leaveReason + '\'' +
				", leaveStartTime=" + leaveStartTime +
				", leaveEndTime=" + leaveEndTime +
				", applicationStatus=" + applicationStatus +
				", response='" + response + '\'' +
				'}';
	}
}

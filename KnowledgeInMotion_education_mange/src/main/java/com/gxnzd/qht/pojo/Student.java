package com.gxnzd.qht.pojo;

/**
 * 学生实体类
 * by 覃惠通
 */
public class Student {
	// 学号 主键
	private String studentId;
	// 密码 初始为123456
	private String studentPassword;
	// 姓名
	private String studentName;
	// 学院id
	private Integer collegeId;
	// 班级id
	private String classId;
	// 年龄
	private Integer studentAge;
	// 性别 0-男 1-女
	// 学生电话号码
	private String studentPhoneNumber;
	private Short studentSex;
	// 学生照片 存储数据为照片url
	private String photograph;

	public Student() {
	}

	public Student(String studentId, String studentPassword, String studentName,
				   Integer collegeId, String classId, Integer studentAge,
				   String studentPhoneNumber, Short studentSex, String photograph) {
		this.studentId = studentId;
		this.studentPassword = studentPassword;
		this.studentName = studentName;
		this.collegeId = collegeId;
		this.classId = classId;
		this.studentAge = studentAge;
		this.studentPhoneNumber = studentPhoneNumber;
		this.studentSex = studentSex;
		this.photograph = photograph;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public Integer getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}

	public String getStudentPhoneNumber() {
		return studentPhoneNumber;
	}

	public void setStudentPhoneNumber(String studentPhoneNumber) {
		this.studentPhoneNumber = studentPhoneNumber;
	}

	public Short getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(Short studentSex) {
		this.studentSex = studentSex;
	}

	public String getPhotograph() {
		return photograph;
	}

	public void setPhotograph(String photograph) {
		this.photograph = photograph;
	}

	@Override
	public String toString() {
		return "Student{" +
				"studentId='" + studentId + '\'' +
				", studentPassword='" + studentPassword + '\'' +
				", studentName='" + studentName + '\'' +
				", collegeId=" + collegeId +
				", classId='" + classId + '\'' +
				", studentAge=" + studentAge +
				", studentPhoneNumber='" + studentPhoneNumber + '\'' +
				", studentSex=" + studentSex +
				", photograph='" + photograph + '\'' +
				'}';
	}
}

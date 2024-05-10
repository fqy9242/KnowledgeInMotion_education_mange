package com.gxnzd.qht.knowledgeinmotion.pojo;
/**
 * 成绩实体类
 * @author 覃
 */
public class Score {
	// 课程编号，主键
	private Integer courseId;
	// 课程名
	private String courseName;
	// 学生学号，主键
	private String studentId;
	// 成绩
	private Integer score;

	public Score() {
	}

	public Score(Integer courseId, String courseName, String studentId, Integer score) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.studentId = studentId;
		this.score = score;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Score{" +
				"courseId=" + courseId +
				", courseName='" + courseName + '\'' +
				", studentId='" + studentId + '\'' +
				", score=" + score +
				'}';
	}
}

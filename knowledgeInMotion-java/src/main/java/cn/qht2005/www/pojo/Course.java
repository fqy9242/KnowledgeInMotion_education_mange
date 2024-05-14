package cn.qht2005.www.pojo;
/**
 * 课程实体类
 * @author 覃
 */
public class Course {
	// 课程id 主键
	private Integer courseId;
	// 课程名
	private String courseName;
	// 开设学院
	private Integer collegeId;

	public Course() {
	}

	public Course(Integer courseId, String courseName, Integer collegeId) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.collegeId = collegeId;
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

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	@Override
	public String toString() {
		return "Course{" +
				"courseId=" + courseId +
				", courseName='" + courseName + '\'' +
				", collegeId=" + collegeId +
				'}';
	}
}

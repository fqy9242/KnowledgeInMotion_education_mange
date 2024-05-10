package com.gxnzd.qht.knowledgeinmotion.pojo;
/**
 * 学院实体类
 * @author 覃
 */
public class College {
	// 学院id 主键。
	private Integer collegeId;
	// 学院名称
	private String collegeName;

	public College() {
	}

	public College(Integer collegeId, String collegeName) {
		this.collegeId = collegeId;
		this.collegeName = collegeName;
	}

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Override
	public String toString() {
		return "College{" +
				"collegeId=" + collegeId +
				", collegeName='" + collegeName + '\'' +
				'}';
	}
}

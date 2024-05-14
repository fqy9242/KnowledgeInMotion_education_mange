package cn.qht2005.www.pojo;

/**
 * 教师实体类
 * @author 覃
 */
public class Teacher {
	// 教师id、工号 主键
	private String teacherId;
	// 所属学院id
	private Integer collegeId;
	// 管理班级id 为空则说明不是班主任
	private String mangeClassId;
	// 教师姓名
	private String name;
	// 教师级别、职位
	private String position;
	// 年龄
	private Integer age;
	// 性别 1.男 2.女
	private Short sex;
	// 教师电话号码
	private String phoneNumber;
	// 教师登录密码
	private String passWord;
	// 教师照片 存储数据为照片url
	private String photograph;

	public Teacher() {
	}

	public Teacher(String teacherId, Integer collegeId, String mangeClassId, String name,
				   String position, Integer age, Short sex,
				   String phoneNumber, String passWord, String photograph) {
		this.teacherId = teacherId;
		this.collegeId = collegeId;
		this.mangeClassId = mangeClassId;
		this.name = name;
		this.position = position;
		this.age = age;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
		this.passWord = passWord;
		this.photograph = photograph;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public String getMangeClassId() {
		return mangeClassId;
	}

	public void setMangeClassId(String mangeClassId) {
		this.mangeClassId = mangeClassId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhotograph() {
		return photograph;
	}

	public void setPhotograph(String photograph) {
		this.photograph = photograph;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"teacherId='" + teacherId + '\'' +
				", collegeId=" + collegeId +
				", mangeClassId='" + mangeClassId + '\'' +
				", name='" + name + '\'' +
				", position='" + position + '\'' +
				", age=" + age +
				", sex=" + sex +
				", phoneNumber='" + phoneNumber + '\'' +
				", passWord='" + passWord + '\'' +
				", photograph='" + photograph + '\'' +
				'}';
	}
}

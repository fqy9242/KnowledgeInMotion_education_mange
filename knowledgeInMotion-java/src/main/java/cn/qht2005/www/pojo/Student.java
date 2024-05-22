package cn.qht2005.www.pojo;

/**
 * 学生实体类
 * @author 覃
 */
public class Student extends People{
	// 学号 主键
	private String studentId;
	// 密码 初始为123456
	private String passWord;
	// 姓名
	private String name;
	// 学院id
	private Integer collegeId;
	// 班级id
	private String classId;
	// 年龄
	private Integer age;
	// 学生电话号码
	private String phoneNumber;
	// 性别 0-男 1-女
	private Short sex;
	// 学生户籍地
	private String address;
	// 学生照片 存储数据为照片url
	private String photograph;

	public Student() {
	}

	public Student(String studentId, String passWord, String name,
				   Integer collegeId, String classId, Integer age,
				   String phoneNumber, Short sex, String address, String photograph) {
		this.studentId = studentId;
		this.passWord = passWord;
		this.name = name;
		this.collegeId = collegeId;
		this.classId = classId;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.sex = sex;
		this.address = address;
		this.photograph = photograph;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
				", passWord='" + passWord + '\'' +
				", Name='" + name + '\'' +
				", collegeId=" + collegeId +
				", classId='" + classId + '\'' +
				", age=" + age +
				", phoneNumber='" + phoneNumber + '\'' +
				", sex=" + sex +
				", Address='" + address + '\'' +
				", photograph='" + photograph + '\'' +
				'}';
	}
}

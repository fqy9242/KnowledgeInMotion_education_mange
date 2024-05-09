-- 创建数据库
CREATE DATABASE knowledgeInMotion_education_mamge;
-- 选择数据库
USE knowledgeInMotion_education_mamge;
-- 创建学生表
CREATE TABLE t_student(
	-- 学号，主键
	student_id VARCHAR(50) PRIMARY KEY,
	-- 所属学院代码
	student_colllege_id TINYINT,
	-- 所属班级代码
	student_class_id INT NOT NULL,
	-- 姓名
	student_name VARCHAR(20) NOT NULL,
	-- 年龄
	student_age INT NOT NULL,
	-- 性别 1.男 2.女
	student_sex TINYINT NOT NULL,
	-- 联系电话
	student_phone_number VARCHAR(20),
	-- 登录密码 默认123456
	student_login_password VARCHAR(20) DEFAULT '123456',
	-- 学生户籍地
	student_address VARCHAR(30),
	-- 学生照片 存储数据为url地址
	student_photograph VARCHAR(100)
);
-- 查询学生表
SELECT * FROM t_student;

-- 创建教师表
CREATE TABLE t_teacher(
	-- 主键 工号
	teacher_id VARCHAR(30) PRIMARY KEY,
	-- 所属学院id
	teacher_college_id TINYINT,
	-- 管理班级；为空则非班主任
	teacher_mange_class_id INT,
	-- 姓名
	teacher_name VARCHAR(20) NOT NULL,
	-- 级别，职位。
	teacher_positon VARCHAR(10),
	-- 年龄
	teacher_age INT,
	-- 性别 1.男 2.女
	teacher_sex TINYINT NOT NULL,
	-- 联系电话
	teacher_phone_number VARCHAR(20),
	-- 登录密码 默认123456
	teacher_login_password VARCHAR(20) DEFAULT '123456',
	-- 教师照片 存储数据为url地址
	teacher_photograph VARCHAR(100)
);
-- 查询教师表
SELECT * FROM t_teacher;
-- 创建学院表
CREATE TABLE t_college(
	-- 学院id 主键,自动增长
	college_id TINYINT PRIMARY KEY auto_increment,
	-- 学院名
	college_name VARCHAR(16) NOT NULL,
);

-- 创建成绩表
CREATE TABLE t_score(
	-- 课程id，主键
	course_id int PRIMARY KEY NOT NULL,
	-- 学生id 主键
	student_id VARCHAR(30) PRIMARY KEY NOT NULL,
	-- 课程名
	course_name VARCHAR(16),
	-- 成绩
	course_score DECIMAL,
);

-- 创建课程表
CREATE TABLE t_course(
	-- 课程id 主键 自动增长
	course_id PRIMARY KEY auto_increment,
	-- 课程名
	course_name VARCHAR(16) NOT NULL,
	-- 开课学院id
	course_college_id TINYINT,
);



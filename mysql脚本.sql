-- 创建数据库
CREATE DATABASE knowledgeInMotion_education_mange;
-- 选择数据库
USE knowledgeInMotion_education_mange;
-- 创建学生表
CREATE TABLE t_student(
	-- 学号，主键
	student_id VARCHAR(50) PRIMARY KEY,
	-- 所属学院代码
	student_college_id TINYINT,
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
	student_photograph VARCHAR(500)
);
-- 学生表添加初始数据
INSERT INTO t_student VALUES("2331020130229",3,39,'覃惠通',19,1,'17777402163','qht123','广西梧州',
'https://pic1.zhimg.com/v2-3810812c3e201f922e8b25460a2f50db_r.jpg');

INSERT INTO t_student VALUES("2331020133333",1,22,'杜维',13,1,'13888882222','zxc2342','贵州六盘水', null);

INSERT INTO t_student VALUES("2331020133133",2, 23, '王大锤', 37, 1, '17999999999','wdc520','菏泽曹县', null);
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
	teacher_position VARCHAR(10),
	-- 年龄
	teacher_age INT,
	-- 性别 1.男 2.女
	teacher_sex TINYINT NOT NULL,
	-- 联系电话
	teacher_phone_number VARCHAR(20),
	-- 登录密码 默认123456
	teacher_login_password VARCHAR(20) DEFAULT '123456',
	-- 教师照片 存储数据为url地址
	teacher_photograph VARCHAR(500)
);
-- 往学生表添加初始数据
INSERT INTO t_teacher VALUES(2005333,3, 39, '李白','班主任', 35, 1, '18888888888','gxnzd123','http://pic.wankacn.com/2017-05-19_591f05253b542.jpeg');
INSERT INTO t_teacher VALUES(2005355,2, 33, '杜甫','班主任', 42, 1, '19999999999', null , null);
INSERT INTO t_teacher VALUES(2001333,3, null, '冯诺依曼','专任教师', 555, 1, '13333333333','iloveChina','https://www.caiwanghao.com/wp-content/uploads/2023/03/2023030413544352.jpg');
-- 查询教师表
SELECT * FROM t_teacher;
-- 创建学院表
CREATE TABLE t_college(
	-- 学院id 主键,自动增长
	college_id TINYINT PRIMARY KEY AUTO_INCREMENT,
	-- 学院名
	college_name VARCHAR(16) NOT NULL
);
-- 往学院表添加初始数据
INSERT INTO t_college VALUES(null,'麻省理工学院');
INSERT INTO t_college VALUES(null,'文学院');
INSERT INTO t_college VALUES(null,'信息工程学院');
-- 查询学院表
SELECT * FROM t_college;

-- 创建课程表
CREATE TABLE t_course(
	-- 课程id 主键 自动增长
	course_id INT PRIMARY KEY AUTO_INCREMENT,
	-- 课程名
	course_name VARCHAR(16) NOT NULL,
	-- 开课学院id
	course_college_id TINYINT
);
-- 往课程表插入初始数据
INSERT INTO t_course VALUES(null, '小学生精通java',3);
INSERT INTO t_course VALUES(null, '前台与后厨基础',3);
INSERT INTO t_course VALUES(null, '小学语文',2);
-- 查询课程表
SELECT * FROM t_course;

-- 创建成绩表
CREATE TABLE t_score(
	-- 课程id，主键
	course_id INT NOT NULL,
	-- 学生id 主键
	student_id VARCHAR(30) NOT NULL,
	-- 课程名
	course_name VARCHAR(16),
	-- 成绩
	course_score DECIMAL,
	-- 联合主键
	PRIMARY KEY (course_id, student_id)
);
-- 往成绩表插入数据
INSERT INTO t_score VALUES(1, '2331020130229', '小学生精通java', 99);
INSERT INTO t_score VALUES(2, '2331020130229', '前台与后厨基础', 99);
INSERT INTO t_score VALUES(3, '2331020130229', '小学语文', 99);
-- 查询成绩表
SELECT * FROM t_score;



-- 创建管理员用户表
CREATE TABLE t_administrator(
-- 用户名
	user_name VARCHAR(16) PRIMARY KEY,
-- 密码
	password varchar(32)
);
-- 往管理员表添加数据
INSERT INTO t_administrator VALUES('root', '123456');
-- 查询管理员表
SELECT * FROM t_administrator;

-- 创建一个公告表
CREATE TABLE t_notice(
	-- id 主键，自动增长
	notice_id INT PRIMARY KEY auto_increment,
	-- 发文人
	notice_publisher VARCHAR(64),
	-- 接收人 类型tinyInt 0:所有人 1:学生用户 2:教师用户
	notice_recipient TINYINT NOT NULL,
	-- 公告标题
	notice_titile VARCHAR(32),
	-- 公告内容
	notice_body VARCHAR(255) NOT NULL
);
-- 往公告表中添加一条数据
INSERT INTO t_notice VALUES(null, '教务处', 0 , '新学期', '新的学期，共同努力！');
-- 查询公告表中的数据
SELECT * FROM t_notice;
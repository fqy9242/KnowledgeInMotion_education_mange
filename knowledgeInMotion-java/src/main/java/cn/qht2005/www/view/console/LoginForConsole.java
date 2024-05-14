package cn.qht2005.www.view.console;

import cn.qht2005.www.service.impl.StudentServiceImpl;

import java.util.Scanner;

public class LoginForConsole {


	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("欢迎使用行知教务管理系统,请先登录。");
		System.out.println("请选择您需要登录的身份：1.学生 2.教师 3.管理员");
		String role = sc.next();
		switch (role) {
			case "1":
				System.out.println("学生登录");
				System.out.println("请输入您的学号：");
				String studentId = sc.next();
				System.out.println("请输入您的密码：");
				String passWord = sc.next();
				StudentServiceImpl studentService = new StudentServiceImpl();
				boolean login = studentService.login(studentId, passWord);
				if (login) {
					System.out.println("登录成功");
				} else {
					System.out.println("登录失败");
				}

				break;
			case "2":
				System.out.println("教师登录");
				break;
			case "3":
				System.out.println("管理员登录");
				break;
			default:
				System.out.println("输入有误，请重新输入");
				break;
		}
	}
}

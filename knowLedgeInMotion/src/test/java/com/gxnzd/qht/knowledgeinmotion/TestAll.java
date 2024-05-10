package com.gxnzd.qht.knowledgeinmotion;

import com.gxnzd.qht.knowledgeinmotion.pojo.Student;
import com.gxnzd.qht.knowledgeinmotion.service.AdministratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestAll {
	@Autowired
	private AdministratorService administratorService;
	@Test
	public void getAllStudentTest() {
		List<Student> allStudent = administratorService.getAllStudent();
		System.out.println(allStudent);
	}
}

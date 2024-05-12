package com.gxnzd.qht.dao;

import com.gxnzd.qht.pojo.Administrator;
import com.gxnzd.qht.pojo.Student;
import com.gxnzd.qht.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface AdministratorMapper {

	@Select(("select  * from t_administrator where user_name = #{userName} and password = #{passWord}"))
	@ResultMap("administratorResultMap")
	Administrator getAdministratorByIdAndPassword(@Param("userName") String userName, @Param("passWord") String passWord);
}

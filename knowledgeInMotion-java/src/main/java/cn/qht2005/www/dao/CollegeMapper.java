package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.College;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 学院映射器
 */
public interface CollegeMapper {
	/**
	 * 查询所有学院
	 * @return
	 */
	@Select("select * from t_college")
	List<College> selectAll();
	/**
	 * 根据学院id查询学院名称
	 * @param college
	 * @return
	 */
	@Select("select college_name from t_college where college_id = #{college}")
	String selectCollegeNameById(int college);
}

package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.College;
import org.apache.ibatis.annotations.ResultMap;
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
	@ResultMap("collegeResultMapper")
	List<College> selectAll();
	/**
	 * 根据学院id查询学院名称
	 * @param college
	 * @return
	 */
	@Select("select * from t_college where college_id = #{college}")
	@ResultMap("collegeResultMapper")
	College selectCollegeNameById(int college);
	/**
	 * 根据学院名称查询学院id
	 * @param collegeName
	 * @return
	 */
	@Select("select * from t_college where college_name = #{collegeName}")
	@ResultMap("collegeResultMapper")
	College selectCollegeIdByName(String collegeName);
}

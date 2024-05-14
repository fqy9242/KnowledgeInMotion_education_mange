package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.Score;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 评分数据访问接口
 */
public interface ScoreMapper {
	/**
	 * 根据学生id查询所有科目成绩
	 * @param studentId
	 * @return
	 */
	@Select("select * from t_score where student_id = #{studentId}")
	@ResultMap("scoreResultMap")
	List<Score> selectAllScoreByStudentId(String studentId);

}

package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.Leave;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
	请假数据表映射类
 * @author 覃
 */
public interface LeaveMapper {
	/**
	 * 查询请假表中的所有数据
	 * @return 一个包含所有请假数据的List集合
	 */
	@Select("select * from t_leave")
	@ResultMap("leaveResultMap")
	List<Leave> selectAll();
	// 往请假表中添加数据
	Integer insertByLeave(Leave leave);
	/**
	 * 根据用户id查询请假数据
	 */
	@Select("select * from t_leave where user_id = #{userId}")
	@ResultMap("leaveResultMap")
	List<Leave> selectByUserId(String userId);
	/**
	 * 根据请假id更新请假数据
	 */
	Integer updateByLeave(Leave leave);

}

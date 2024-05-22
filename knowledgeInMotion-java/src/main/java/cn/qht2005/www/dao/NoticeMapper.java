package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.Notice;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *  通知映射类
 *  by 覃惠通
 * @author 覃
 */
public interface NoticeMapper {
	/**
	 *  根据接收者查询通知
	 * @param recipient 接收者类型 0.所有 1.学生 2.教师
	 * @return 通知列表
	 */
	@Select(" select * from t_notice where notice_recipient = #{recipient}")
	@ResultMap("noticeResultMap")
	List<Notice>getByRecipient(Short recipient);
}

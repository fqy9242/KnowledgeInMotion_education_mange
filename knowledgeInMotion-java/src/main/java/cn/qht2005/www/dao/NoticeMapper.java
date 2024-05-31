package cn.qht2005.www.dao;

import cn.qht2005.www.pojo.Notice;
import org.apache.ibatis.annotations.Insert;
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

	/**
	 * 查询所有通知
	 * @return 通知列表
	 */
	@Select(" select * from t_notice")
	@ResultMap("noticeResultMap")
	List<Notice> selectAll();

	/**
	 *  发布公告
	 * @param notice 公告类实体对象
	 */
	@Insert("insert into t_notice(notice_publisher, notice_time, notice_recipient, notice_title, notice_body) VALUES " +
			"(#{publisher}, #{publishDate}, #{recipient}, #{title}, #{body})")
	void insert(Notice notice);

	/**
	 * 根据条件查询通知
	 * @param notice 通知类实体对象
	 * @return 符合条件的通知列表
	 */
	List<Notice> selectByNotice(Notice notice);

	/**
	 *  批量删除公告
	 * @param noticeList 公告列表
	 */

	void deleteByNoticeList(List<Notice> noticeList);
}

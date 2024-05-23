package cn.qht2005.www.pojo;

import java.time.LocalDateTime;
import java.util.Date;

// 公告实体类
public class Notice {
	// 公告id 主键
	private Integer noticeId;
	// 发文人
	private String publisher;
	// 发文时间
	private LocalDateTime publishDate;
	// 接收人 0全体师生 1学生 2教职工
	private short recipient;
	// 公告标题
	private String title;
	// 公告内容
	private String body;

	public Notice() {
	}

	public Notice(String publisher, LocalDateTime publishDate, short recipient, String title, String body) {
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.recipient = recipient;
		this.title = title;
		this.body = body;
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public short getRecipient() {
		return recipient;
	}

	public void setRecipient(short recipient) {
		this.recipient = recipient;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}


	public LocalDateTime getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDateTime publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "Notice{" +
				"noticeId=" + noticeId +
				", publisher='" + publisher + '\'' +
				", publishDate=" + publishDate +
				", recipient=" + recipient +
				", title='" + title + '\'' +
				", body='" + body + '\'' +
				'}';
	}
}

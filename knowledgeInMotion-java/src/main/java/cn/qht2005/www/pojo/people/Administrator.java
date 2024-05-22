package cn.qht2005.www.pojo.people;

public class Administrator extends People{
	private String userName;
	private String passWord;

	public Administrator(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}

	public Administrator() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "Administrator{" +
				"userName='" + userName + '\'' +
				", passWord='" + passWord + '\'' +
				'}';
	}
}

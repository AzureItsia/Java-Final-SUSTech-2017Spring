package TiketReserveSystem;

public class admin 
{
	//管理员账户与密码
	private String userName = new String();
	private String password = new String();
	//设置管理员账户名
	public void setUserName(String newUserName)
	{
		userName = newUserName;
	}
	//读取管理员账户名
	public String getUserName()
	{
		return userName;
	}
	//设置管理员密码
	public void setPassword(String newPassword)
	{
		password = newPassword;
	}
	//读取管理员密码
	public String getPassword()
	{
		return password;
	}
	
}


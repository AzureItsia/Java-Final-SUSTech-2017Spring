package FlightTicketReservationSystem;

public class Admin {

	//����Ա�˻�������
	private String userName = null;
	private String password = null;
	//���ù���Ա�˻���
	public void setUserName(String newUserName)
	{
		userName = newUserName;
	}
	//��ȡ����Ա�˻���
	public String getUserName()
	{
		return userName;
	}
	//���ù���Ա����
	public void setPassword(String newPassword)
	{
		password = newPassword;
	}
	//��ȡ����Ա����
	public String getPassword()
	{
		return password;
	}
	
}

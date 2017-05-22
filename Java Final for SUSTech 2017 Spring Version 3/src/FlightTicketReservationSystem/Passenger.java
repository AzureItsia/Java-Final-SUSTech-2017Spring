package FlightTicketReservationSystem;

import java.util.ArrayList;


public class Passenger {

	private int uniqueID;//�˿�ע�����
	private String realName = new String();
	private String idNumber = new String();//���֤��
	private String password = new String();
	private ArrayList<Order> orderList = new ArrayList<Order>();//���������б�
	
	//���ó˿�ע�����
	void setUniqueID(int newUniqueID)
	{
		uniqueID = newUniqueID;
	}
	
	//��ȡ�˿�ע�����
	int getUniqueID()
	{
		return uniqueID;
	}

	
	//������ʵ����
	void setRealName(String newName)
	{
		realName = newName;
	}
	
	//��ȡ��ʵ����
	String getRealName()
	{
		return realName;
	}
	
	//�������֤��
	void setIdNumber(String newIdNumber)
	{
		idNumber = newIdNumber;
	}
	
	//��ȡ���֤��
	String getIdNumber()
	{
		return idNumber;
	}
	
	//��������
	void setPassword(String newPassword)
	{
		password = newPassword;
	}
	
	//��ȡ����
	String getPassword()
	{
		return password;
	}
	
	//���Ӷ����������б�
	void addOrder(Order newOrder)
	{
		orderList.add(newOrder);
	}
	//��ȡ�����б���
	int getOrderListSize()
	{
		return orderList.size();
	}
	//ɾ���б���ָ������
	void deleteOrder(Order aimOrder)
	{
		orderList.remove(aimOrder);
	}
	//��ȡָ����Ŷ���
	Order getOrder(int index)
	{
		return orderList.get(index);
	}
	
	Order getOrder(Flight flight)
	{
		for(int index = 0; index < orderList.size(); index ++)
		{
			if(flight == orderList.get(index).getFlight())
			{
				return orderList.get(index);
			}
		}
		return null;
	}
	
	//��ʾ������Ϣ
	void showOrder(Order aimOrder)
	{
		Function.starSeparation(80);
		System.out.printf("%-20s%s%s\n", "||| Order Index", ":", orderList.indexOf(aimOrder));
		System.out.printf("%-20s%s%s\n", "||| Real Name" , ":", aimOrder.getRealName());
		System.out.printf("%-20s%s%s\n", "||| Unique ID" , ":", aimOrder.getUniqueID());
		System.out.printf("%-20s%s%s\n", "||| seat" , ":", aimOrder.getSeatNum());
		System.out.printf("||| Fligh:\n");
		Function.showFlight_Passenger(aimOrder.getFlight());
		System.out.printf("%-20s%s%s\n", "||| Create Time" , ":", aimOrder.getCreateTime());
		System.out.printf("%-20s%s%s\n", "||| Status" , ":", aimOrder.getStatus());
		Function.starSeparation(80);
	}
}

package TiketReserveSystem;

import java.util.Date;

public class Order {

	private String passengerRN = new String();//�˿���ʵ����
	private int passengerID;//�˿�ע�����
	private int seat;//��λ��
	private Flight flight = new Flight();//��Ӧ����
	private String createDate = new String(); //��������ʱ��
	private OrderStatus status = OrderStatus.UNPAID;//ȱʡ����״̬Ϊδ֧��
	
	//���ó˿���ʵ����
	void setPassengerRN(String realName)
	{
		passengerRN = realName;
	}
	
	//��ȡ�˿���ʵ����
	String getPassengerRN()
	{
		return passengerRN;
	}
	
	//���ó˿�ע�����
	void setPassengerID(int PID)
	{
		passengerID = PID;
	}
	
	//��ȡ�˿�ע�����
	int getPassengerID()
	{
		return passengerID;
	}
	
	//���ó˿���λ��
	void setSeat(int seatNum)
	{
		seat = seatNum;
	}
	
	//��ȡ�˿���λ��
	int getSeat()
	{
		return seat;
	}
	
	//���ö�Ӧ����
	void setFlight(Flight orderFlight)
	{
		flight = orderFlight;
	}
	
	//��ȡ��Ӧ����
	Flight getOderFlight()
	{
		return flight;
	}
	
	//���ô���ʱ��
	void setCreateDate(String date)
	{
		createDate = date;
	}
	
	//��ȡ����ʱ��
	String getCreateDate()
	{
		return createDate;
	}
	
	//���ö���״̬Ϊ��֧��
	void setOrderPaid()
	{
		status = OrderStatus.PAID;
	}
	
	//���ö���״̬Ϊδ֧��
	void setOrderUnpaid()
	{
		status = OrderStatus.UNPAID;
	}
	
	//���ö���״̬Ϊȡ��
	void setOrderCancel()
	{
		status = OrderStatus.CANCEL;
	}
	
	//��ȡ����״̬�������ַ���
	String getStrStatus()
	{
		String orderStatus = new String();
		switch(status)
		{
		case UNPAID:
			orderStatus = "UNPAID";
			break;
		case PAID:
			orderStatus = "PAID";
			break;
		case CANCEL:
			orderStatus = "CANCEL";
			break;
		}
		return orderStatus;
	}
	
	//��ȡ����״̬������ö��ֵ
	OrderStatus getEnumStatus()
	{
		return status;
	}
	
}

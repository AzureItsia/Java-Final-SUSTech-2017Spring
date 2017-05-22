package FlightTicketReservationSystem;

public class Order {
	
	private String realName = null;//�˿���ʵ����
	private int uniqueID;//�˿�ע�����
	private int seat;//��λ��
	private Flight flight = null;//��Ӧ����
	private String createTime = null; //��������ʱ��
	private OrderStatus status = OrderStatus.UNPAID;//ȱʡ����״̬Ϊδ֧��
	
	//���ó˿���ʵ����
	void setRealName(String newName)
	{
		realName = newName;
	}
	
	//��ȡ�˿���ʵ����
	String getRealName()
	{
		return realName;
	}
	
	//���ó˿�Ψһ���
	void setUniqueID(int UID)
	{
		uniqueID = UID;
	}
	
	//��ȡ�˿�Ψһ���
	int getUniqueID()
	{
		return uniqueID;
	}
	
	//���ó˿���λ��
	void setSeatNum(int seatNum)
	{
		seat = seatNum;
	}
	
	//��ȡ�˿���λ��
	int getSeatNum()
	{
		return seat;
	}
	
	//���ö�Ӧ����
	void setFlight(Flight newFlight)
	{
		flight = newFlight;
	}
	
	//��ȡ��Ӧ����
	Flight getFlight()
	{
		return flight;
	}
	
	//���ô���ʱ��
	void setCreateTime(String newDate)
	{
		createTime = newDate;
	}
	
	//��ȡ����ʱ��
	String getCreateTime()
	{
		return createTime;
	}
	
	//���ö���״̬Ϊ��֧��
	void setOrderPAID()
	{
		status = OrderStatus.PAID;
	}
	
	//���ö���״̬Ϊδ֧��
	void setOrderUNPAID()
	{
		status = OrderStatus.UNPAID;
	}
	
	//���ö���״̬Ϊȡ��
	void setOrderCANCEL()
	{
		status = OrderStatus.CANCEL;
	}
	
	//��ȡ����״̬������ö��ֵ
	OrderStatus getStatus()
	{
		return status;
	}
	
	//��ʾ������Ϣ
	void showOrder_Brief(Order aimOrder)
	{
		Function.starSeparation(80);
		System.out.printf("%-20s%s%s\n", "||| Order Index", ":", Data.orderList.indexOf(aimOrder));
		System.out.printf("%-20s%s%s\n", "||| Real Name" , ":", aimOrder.getRealName());
		System.out.printf("%-20s%s%s\n", "||| Flight Index" , ":", Data.flightList.indexOf(aimOrder.getFlight()));
		Function.starSeparation(80);
	}
	
	//��ʾ������Ϣ
	void showOrder_Detail(Order aimOrder)
	{
		Function.starSeparation(80);
		System.out.printf("%-20s%s%s\n", "||| Order Index", ":", Data.orderList.indexOf(aimOrder));
		System.out.printf("%-20s%s%s\n", "||| Real Name" , ":", aimOrder.getRealName());
		System.out.printf("%-20s%s%s\n", "||| Unique ID" , ":", aimOrder.getUniqueID());
		System.out.printf("%-20s%s%s\n", "||| seat" , ":", aimOrder.getSeatNum());
		System.out.printf("||| Flight:\n");
		Function.showFlight_Admin(aimOrder.getFlight());
		System.out.printf("%-20s%s%s\n", "||| Create Time" , ":", aimOrder.getCreateTime());
		System.out.printf("%-20s%s%s\n", "||| Status" , ":", aimOrder.getStatus());
		Function.starSeparation(80);
	}
	
	//��ȡ��Ӧ�˿�
	Passenger getPassenger()
	{
		return Data.passengerList.get(uniqueID);
	}
}

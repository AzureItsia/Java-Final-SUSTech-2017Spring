package TiketReserveSystem;

import java.util.ArrayList;

public class passenger {

	private int passengerID;//�˿�ע�����
	private String realName = new String();
	private String identityID = new String();//���֤��
	private String password = new String();
	private ArrayList<Order> orderList = new ArrayList<Order>();//���������б�
	
	//���ó˿�ע�����
	void setPassengerID(int newPassengerID)
	{
		passengerID = newPassengerID;
	}
	
	//��ȡ�˿�ע�����
	int getPassengerID()
	{
		return passengerID;
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
	void setIdentityID(String newIdentityID)
	{
		identityID = newIdentityID;
	}
	
	//��ȡ���֤��
	String getIdentityID()
	{
		return identityID;
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
	
	//ͨ��������������
	void createOrder(Flight flight)
	{
		Order newOrder = new Order();
		newOrder.setPassengerRN(realName); //��д�˿���ʵ����
		newOrder.setPassengerID(passengerID);//��д�˿�ע�����
		newOrder.setFlight(flight);//��д��Ӧ����
		newOrder.setSeat(flight.arrangeSeat());//��д��λ��
		newOrder.setCreateDate(function.getCurrentTime());//��д��������ʱ��
		orderList.add(newOrder);//���ö�������������б�
		//����ǰ���߳˿�������˶�����Ӧ����
		Data.flightList.get(Data.flightList.indexOf(flight)).addPassenger(Data.signUpPassengerList.get(Data.currentPassengerIndex));
		//��Ӧ����˿���+1
		Data.flightList.get(Data.flightList.indexOf(flight)).currentPassengersPlus1();
	}
	
	//ͨ������ȡ������
	void unsubscribeOrder(Flight flight)
	{
		int index = getFlightOrderNumber(flight);
		orderList.remove(index);//���ö����Ӷ����б��Ƴ�
	}
	
	//��ʾ������Ϣ
	void listOrder(Order order)
	{
		function.starSepa(50);
		System.out.printf("Name:%s", order.getPassengerRN());
		System.out.printf("PID:%s", order.getPassengerID());
		System.out.printf("Seat:%d", order.getSeat());
		System.out.printf("Status:%s", order.getStrStatus());
		System.out.printf("Flight:%s", order.getOderFlight().getFlightID());
		System.out.printf("Create date:%s", order.getCreateDate());
		function.starSepa(50);
	}

	//��ʾ���ж�����Ϣ
	void listAllOrder()
	{
		for(int counter = 0; counter < orderList.size(); counter++)
		{
			function.starSepa(50);
			System.out.printf("Number:%d\n", counter);
			function.starSepa(50);
			listOrder(orderList.get(counter));
		}
	}

	//�Ƿ�֧������
	void isPaidForOrder()
	{
		function.starSepa(50);
		System.out.println("Do you want to paid for order?(yes/no)");
		String isPaid = function.input.nextLine();
		if (isPaid.toLowerCase().compareTo("yes") == 0)
		{
			System.out.println("Enter the Number of order:");
			int num = function.input.nextInt();
			function.input.nextLine();
			if(num >= 0 & num < orderList.size())//��źϷ�
			{
				if(orderList.get(num).getEnumStatus() == OrderStatus.UNPAID)//�˶���δ֧��
				{
					orderList.get(num).setOrderPaid();
					System.out.println("Success!");
				}
				else
				{
					System.out.println("Error! This order was PAID/CANCEL.");
					isPaidForOrder();
				}

			}
			else//��Ų��Ϸ�
			{
				System.out.println("Error! Plese input the correct Number:");
				isPaidForOrder();
			}
		}
	}
	
	//��ȡ�����б��С
	int getOrderListSize()
	{
		int size = orderList.size();
		return size;
	}
	
	//��ö�Ӧ�����ĺ���
	Flight getOrderFlight(int orderNumber)
	{
		Flight flight = orderList.get(orderNumber).getOderFlight();
		return flight;
	}
	
	//��ȡ����
	Order getOrder(int index)
	{
		return orderList.get(index);
	}

	//��ö�Ӧ����Ķ�����
	int getFlightOrderNumber(Flight flight)
	{	
		int orderNumber = orderList.indexOf(flight);
		return orderNumber;
	}
}

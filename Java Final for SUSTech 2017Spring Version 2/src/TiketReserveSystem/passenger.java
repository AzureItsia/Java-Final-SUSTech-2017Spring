package TiketReserveSystem;

import java.util.ArrayList;

public class passenger {

	private int passengerID;//乘客注册序号
	private String realName = new String();
	private String identityID = new String();//身份证号
	private String password = new String();
	private ArrayList<Order> orderList = new ArrayList<Order>();//创建订单列表
	
	//设置乘客注册序号
	void setPassengerID(int newPassengerID)
	{
		passengerID = newPassengerID;
	}
	
	//读取乘客注册序号
	int getPassengerID()
	{
		return passengerID;
	}
	
	//设置真实姓名
	void setRealName(String newName)
	{
		realName = newName;
	}
	
	//读取真实姓名
	String getRealName()
	{
		return realName;
	}
	
	//设置身份证号
	void setIdentityID(String newIdentityID)
	{
		identityID = newIdentityID;
	}
	
	//读取身份证号
	String getIdentityID()
	{
		return identityID;
	}
	
	//设置密码
	void setPassword(String newPassword)
	{
		password = newPassword;
	}
	
	//读取密码
	String getPassword()
	{
		return password;
	}
	
	//通过航班新增订单
	void createOrder(Flight flight)
	{
		Order newOrder = new Order();
		newOrder.setPassengerRN(realName); //填写乘客真实姓名
		newOrder.setPassengerID(passengerID);//填写乘客注册序号
		newOrder.setFlight(flight);//填写对应航班
		newOrder.setSeat(flight.arrangeSeat());//填写座位号
		newOrder.setCreateDate(function.getCurrentTime());//填写订单创建时间
		orderList.add(newOrder);//将该订单添加至订单列表
		//将当前在线乘客添加至此订单对应航班
		Data.flightList.get(Data.flightList.indexOf(flight)).addPassenger(Data.signUpPassengerList.get(Data.currentPassengerIndex));
		//对应航班乘客数+1
		Data.flightList.get(Data.flightList.indexOf(flight)).currentPassengersPlus1();
	}
	
	//通过航班取消订单
	void unsubscribeOrder(Flight flight)
	{
		int index = getFlightOrderNumber(flight);
		orderList.remove(index);//将该订单从订单列表移除
	}
	
	//显示订单信息
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

	//显示所有订单信息
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

	//是否支付订单
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
			if(num >= 0 & num < orderList.size())//序号合法
			{
				if(orderList.get(num).getEnumStatus() == OrderStatus.UNPAID)//此订单未支付
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
			else//序号不合法
			{
				System.out.println("Error! Plese input the correct Number:");
				isPaidForOrder();
			}
		}
	}
	
	//获取航班列表大小
	int getOrderListSize()
	{
		int size = orderList.size();
		return size;
	}
	
	//获得对应订单的航班
	Flight getOrderFlight(int orderNumber)
	{
		Flight flight = orderList.get(orderNumber).getOderFlight();
		return flight;
	}
	
	//读取订单
	Order getOrder(int index)
	{
		return orderList.get(index);
	}

	//获得对应航班的订单号
	int getFlightOrderNumber(Flight flight)
	{	
		int orderNumber = orderList.indexOf(flight);
		return orderNumber;
	}
}

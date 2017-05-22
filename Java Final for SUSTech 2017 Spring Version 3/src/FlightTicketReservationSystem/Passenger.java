package FlightTicketReservationSystem;

import java.util.ArrayList;


public class Passenger {

	private int uniqueID;//乘客注册序号
	private String realName = new String();
	private String idNumber = new String();//身份证号
	private String password = new String();
	private ArrayList<Order> orderList = new ArrayList<Order>();//创建订单列表
	
	//设置乘客注册序号
	void setUniqueID(int newUniqueID)
	{
		uniqueID = newUniqueID;
	}
	
	//读取乘客注册序号
	int getUniqueID()
	{
		return uniqueID;
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
	void setIdNumber(String newIdNumber)
	{
		idNumber = newIdNumber;
	}
	
	//读取身份证号
	String getIdNumber()
	{
		return idNumber;
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
	
	//增加订单到订单列表
	void addOrder(Order newOrder)
	{
		orderList.add(newOrder);
	}
	//读取订单列表长度
	int getOrderListSize()
	{
		return orderList.size();
	}
	//删除列表内指定订单
	void deleteOrder(Order aimOrder)
	{
		orderList.remove(aimOrder);
	}
	//读取指定序号订单
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
	
	//显示订单信息
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

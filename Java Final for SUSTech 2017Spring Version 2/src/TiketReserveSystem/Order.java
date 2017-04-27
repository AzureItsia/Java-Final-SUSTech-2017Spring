package TiketReserveSystem;

import java.util.Date;

public class Order {

	private String passengerRN = new String();//乘客真实姓名
	private int passengerID;//乘客注册序号
	private int seat;//座位号
	private Flight flight = new Flight();//对应航班
	private String createDate = new String(); //订单创建时间
	private OrderStatus status = OrderStatus.UNPAID;//缺省订单状态为未支付
	
	//设置乘客真实姓名
	void setPassengerRN(String realName)
	{
		passengerRN = realName;
	}
	
	//读取乘客真实姓名
	String getPassengerRN()
	{
		return passengerRN;
	}
	
	//设置乘客注册序号
	void setPassengerID(int PID)
	{
		passengerID = PID;
	}
	
	//读取乘客注册序号
	int getPassengerID()
	{
		return passengerID;
	}
	
	//设置乘客座位号
	void setSeat(int seatNum)
	{
		seat = seatNum;
	}
	
	//读取乘客座位号
	int getSeat()
	{
		return seat;
	}
	
	//设置对应航班
	void setFlight(Flight orderFlight)
	{
		flight = orderFlight;
	}
	
	//读取对应航班
	Flight getOderFlight()
	{
		return flight;
	}
	
	//设置创建时间
	void setCreateDate(String date)
	{
		createDate = date;
	}
	
	//读取创建时间
	String getCreateDate()
	{
		return createDate;
	}
	
	//设置订单状态为已支付
	void setOrderPaid()
	{
		status = OrderStatus.PAID;
	}
	
	//设置订单状态为未支付
	void setOrderUnpaid()
	{
		status = OrderStatus.UNPAID;
	}
	
	//设置订单状态为取消
	void setOrderCancel()
	{
		status = OrderStatus.CANCEL;
	}
	
	//读取订单状态，返回字符串
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
	
	//读取订单状态，返回枚举值
	OrderStatus getEnumStatus()
	{
		return status;
	}
	
}

package FlightTicketReservationSystem;

public class Order {
	
	private String realName = null;//乘客真实姓名
	private int uniqueID;//乘客注册序号
	private int seat;//座位号
	private Flight flight = null;//对应航班
	private String createTime = null; //订单创建时间
	private OrderStatus status = OrderStatus.UNPAID;//缺省订单状态为未支付
	
	//设置乘客真实姓名
	void setRealName(String newName)
	{
		realName = newName;
	}
	
	//读取乘客真实姓名
	String getRealName()
	{
		return realName;
	}
	
	//设置乘客唯一编号
	void setUniqueID(int UID)
	{
		uniqueID = UID;
	}
	
	//读取乘客唯一编号
	int getUniqueID()
	{
		return uniqueID;
	}
	
	//设置乘客座位号
	void setSeatNum(int seatNum)
	{
		seat = seatNum;
	}
	
	//读取乘客座位号
	int getSeatNum()
	{
		return seat;
	}
	
	//设置对应航班
	void setFlight(Flight newFlight)
	{
		flight = newFlight;
	}
	
	//读取对应航班
	Flight getFlight()
	{
		return flight;
	}
	
	//设置创建时间
	void setCreateTime(String newDate)
	{
		createTime = newDate;
	}
	
	//读取创建时间
	String getCreateTime()
	{
		return createTime;
	}
	
	//设置订单状态为已支付
	void setOrderPAID()
	{
		status = OrderStatus.PAID;
	}
	
	//设置订单状态为未支付
	void setOrderUNPAID()
	{
		status = OrderStatus.UNPAID;
	}
	
	//设置订单状态为取消
	void setOrderCANCEL()
	{
		status = OrderStatus.CANCEL;
	}
	
	//读取订单状态，返回枚举值
	OrderStatus getStatus()
	{
		return status;
	}
	
	//显示订单信息
	void showOrder_Brief(Order aimOrder)
	{
		Function.starSeparation(80);
		System.out.printf("%-20s%s%s\n", "||| Order Index", ":", Data.orderList.indexOf(aimOrder));
		System.out.printf("%-20s%s%s\n", "||| Real Name" , ":", aimOrder.getRealName());
		System.out.printf("%-20s%s%s\n", "||| Flight Index" , ":", Data.flightList.indexOf(aimOrder.getFlight()));
		Function.starSeparation(80);
	}
	
	//显示订单信息
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
	
	//读取对应乘客
	Passenger getPassenger()
	{
		return Data.passengerList.get(uniqueID);
	}
}

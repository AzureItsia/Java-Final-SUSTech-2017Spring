package FlightTicketReservationSystem;

import java.util.ArrayList;

public class Flight {
	private String flightID;
	private String startTime;
	private String arrivalTime;
	private String startCity;
	private String arrivalCity;
	private String departureDate;
	private int price;
	private int currentPassengers;//当前乘客数
	private int seatCapacity;
	private boolean[] seatList;
	private FlightStatus flightStatus = FlightStatus.UNPUBLISHED;
	private ArrayList<Passenger> passengersList = new ArrayList<Passenger>();//创建当前航班乘客列表
	
	Flight()
	{
		createSeatList();
		initializeSeatList();//座位分配情况清零
	}
	
	//设置航班编号
	void setFlightID(String newFlightID)
	{
		flightID = newFlightID;
	}
	//读取航班编号
	String getFlightID()
	{
		return flightID;
	}
	//设置起飞时间
	void setStartTime(String newStartTime)
	{
		startTime = newStartTime;
	}
	//读取起飞时间
	String getStartTime()
	{
		return startTime;
	}
	//设置到达时间
	void setArrivalTime(String newArrivalTime)
	{
		arrivalTime = newArrivalTime;
	}
	//读取到达时间
	String getArrivalTime()
	{
		return arrivalTime;
	}
	//设置起飞城市
	void setStartCity(String newStartCity)
	{
		startCity = newStartCity;
	}
	//读取起飞城市
	String getStartCity()
	{
		return startCity;
	}
	//设置目的城市
	void setArrivalCity(String newArrivalCity)
	{
		arrivalCity = newArrivalCity;
	}
	//读取目的城市
	String getArrivalCity()
	{
		return arrivalCity;
	}
	//设置起飞日期
	void setDepartureDate(String newDepartureDate)
	{
		departureDate = newDepartureDate;
	}
	//读取起飞日期
	String getDepartureDate()
	{
		return departureDate;
	}
	//设置价格
	void setPrice(int newPrice)
	{
		price = newPrice;
	}
	//读取价格
	int getPrice()
	{
		return price;
	}
	//更新当前乘客数
	void updataCurrentPassengers()
	{
		currentPassengers = passengersList.size();
	}
	//读取当前乘客数
	int getCurrentPassengers()
	{
		return currentPassengers;
	}
	//设置航班容量
	void setSeatCapacity(int newSeatCapacity)
	{
		seatCapacity = newSeatCapacity;
	}
	//读取航班容量
	int getSeatCapacity()
	{
		return seatCapacity;
	}
	//设置航班状态为未发布
	void setFlightUNPUBLISHED()
	{
		flightStatus = FlightStatus.UNPUBLISHED;
	}
	//设置航班状态为有效
	void setFlightAVAILABLE()
	{
		flightStatus = FlightStatus.AVAILABLE;
	}
	//设置航班状态为终止
	void setFlightTERMINATE()
	{
		flightStatus = FlightStatus.TERMINATE;
	}
	//设置航班为已满
	void setFlightFULL()
	{
		flightStatus = FlightStatus.FULL;
	}
	//读取航班状态，返回枚举值
	FlightStatus getStatus()
	{
		return flightStatus;
	}
	//增加乘客至列表
	void addPassenger(Passenger newPassenger)
	{
		passengersList.add(newPassenger);
	}
	//读取乘客
	Passenger getPassenger(int index)
	{
		return passengersList.get(index);
	}
	//从列表移除乘客
	void deletePassenger(Passenger aimPassenger)
	{
		passengersList.remove(aimPassenger);
	}
	//读取剩余座位数
	int getAvailableSeat()
	{
		updataCurrentPassengers();
		return seatCapacity - currentPassengers;
	}
	//创建座位列表
	void createSeatList()
	{
		seatList = new boolean[seatCapacity];
	}
	//初始化座位列表
	void initializeSeatList()
	{
		//遍历座位列表，设置所有座位为空
		for(int counter = 0; counter < seatList.length ; counter ++)
		{
			seatList[counter] = false;
		}
	}
	//释放座位
	void releaseSeat(int seatNumber)
	{
		seatList[seatNumber] = false;
	}
	//占据座位
	void occupySeat(int seatNumber)
	{
		seatList[seatNumber] = true;
	}
	//读取座位数
	int getSeatNumbers()
	{
		return seatList.length;
	}
	//读取该座位情况
	boolean getSeatStatus(int seatNumber)
	{
		return seatList[seatNumber];
	}
}

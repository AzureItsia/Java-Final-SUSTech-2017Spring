package TiketReserveSystem;

import java.util.ArrayList;

public class Flight {

	private String flightID = new String();
	private String startTime = new String();
	private String arrivalTime = new String();
	private String startCity = new String();
	private String arrivalCity = new String();
	private String departureDate = new String();
	private int price;
	private static int currentPassengers = 0;//当前乘客数
	private int seatCapacity;
	private int availableSeat;
	private int[] seat = new int[seatCapacity];
	private FlightStatus flightStatus = FlightStatus.UNPUBLISHED;//缺省航班状态为未发布
	private ArrayList<passenger> passengersList = new ArrayList<passenger>();//创建当前航班乘客列表
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
	void setstartTime(String newStartTime)
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
	//当前乘客数+1
	void currentPassengersPlus1()
	{
		currentPassengers++;
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
	//发布飞机
	void publishFlight()
	{
		flightStatus = FlightStatus.AVAILABLE;
	}
	//读取航班状态，返回字符串
	String getStrStatus()
	{
		String status = null;
		switch(flightStatus)
		{
			case UNPUBLISHED:
				status = "UNPUBLISHED";
				break;
			case AVAILABLE:
				status = "AVALIABLE";
				break;
			case FULL:
				status = "FULL";
				break;
			case TERMINATE:
				status = "TERMINATE";
				break;
		}
		return status;
	}
	//读取航班状态，返回枚举值
	FlightStatus getEnumStatus()
	{
		return flightStatus;
	}
	//设置航班为有效
	void setFlightAvailable()
	{
		flightStatus = FlightStatus.AVAILABLE;
	}
	//设置航班为已满
	void setFlightFull()
	{
		flightStatus = FlightStatus.FULL;
	}
	//设置航班为终止
	void setFlightTerminate()
	{
		flightStatus = FlightStatus.TERMINATE;
	}
	//增加乘客
	void addPassenger(passenger newPassenger)
	{
		passengersList.add(newPassenger);
	}
	//删除乘客
	void deletePassenger(passenger aimPassenger)
	{
		passengersList.remove(aimPassenger);
	}
	//初始化座位表
	void initializeSeat()
	{
		for(int counter = 0; counter < seat.length; counter++)
		{
			seat[counter] = 0;//0代表没人坐
		}
	}
	//读取剩余座位数
	int getAvailableSeat()
	{
		int rest = seatCapacity - currentPassengers;
		return rest;
	}
	//找到第一个空座位
	int getFirstAvaSeat()
	{
		int index = -1;
		for(int counter = 0; counter < seat.length; counter++)
		{
			if(seat[counter] == 0)
			{
				index = counter;
				break;
			}
		}
		return index;
	}
	//分配座位
	int arrangeSeat()
	{
		int seatNum = getFirstAvaSeat();
		if(seatNum != -1)
		{
			seat[seatNum] = 1;
		}
		return seatNum;
	}
	//读取乘客
	passenger getPassenger(int index)
	{
		return passengersList.get(index);
	}
}

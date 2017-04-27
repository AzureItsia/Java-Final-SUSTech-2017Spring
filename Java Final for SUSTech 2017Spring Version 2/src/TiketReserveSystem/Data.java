package TiketReserveSystem;

import java.util.ArrayList;

public class Data {

	public static boolean isLogin= false;//缺省是否登陆标记为假
	public static boolean isPassengerLogin = false;//缺省乘客是否登陆标记为假
	public static boolean isAdminLogin = false;//缺省管理员是否登陆标记为假
	public static ArrayList<Flight> flightList = new ArrayList<Flight>();//创建航班列表
	public static ArrayList<admin> adminList = new ArrayList<admin>();//创建管理员列表
	public static int currentAdminIndex;//当前在线管理员序号
	public static ArrayList<passenger> signUpPassengerList = new ArrayList<passenger>();//创建已注册乘客列表
	public static int signUpPassengers = 0;//缺省无已注册用户人数
	public static int currentPassengerIndex;//当前在线乘客序号
	
	//初始化缺省管理员账户
	public static void initializeAdmin()
	{
		admin defaultAdmin = new admin();
		defaultAdmin.setUserName("admin");
		defaultAdmin.setPassword("admin");
		adminList.add(defaultAdmin);
	}
	
	//初始化一个航班并加入航班列表
	public static void initializeFlight(Flight flight, String FlightID, String StartTime, String ArrivalTime,String StartCity, String ArrivalCity, String DepartureDate, int Price, int SeatCapacity)
	{
		flight.setFlightID(FlightID);
		flight.setstartTime(StartTime);
		flight.setArrivalTime(ArrivalTime);
		flight.setStartCity(StartCity);
		flight.setArrivalCity(ArrivalCity);
		flight.setDepartureDate(DepartureDate);
		flight.setPrice(Price);
		flight.setSeatCapacity(SeatCapacity);
		Data.flightList.add(flight);
	}
	
	//初始化航班列表
	public static void initializeFlightList()
	{
		//深圳到北京
		Flight f1 = new Flight();
		initializeFlight(f1, "CA4301", "7:40", "10:30", "Shenzhen", "Beijing", function.getDateAfterOneDay(), 1930, 324);
		Flight f2 = new Flight();
		initializeFlight(f2, "CA4301", "7:40", "10:30", "Shenzhen", "Beijing", function.getDateAfterOneWeek(), 1930, 324);
		Flight f3 = new Flight();
		initializeFlight(f3, "CA4301", "7:40", "10:30", "Shenzhen", "Beijing", function.getDateAfterOneMonth(), 1930, 324);
		Flight rf1 = new Flight();
		initializeFlight(rf1, "CA4301", "7:40", "10:30", "Beijing", "Shenzhen", function.getDateAfterOneDay(), 1930, 324);
		Flight rf2 = new Flight();
		initializeFlight(rf2, "CA4301", "7:40", "10:30", "Beijing", "Shenzhen", function.getDateAfterOneWeek(), 1930, 324);
		Flight rf3 = new Flight();
		initializeFlight(rf3, "CA4301", "7:40", "10:30", "Beijing", "Shenzhen", function.getDateAfterOneMonth(), 1930, 324);
		//深圳到上海
		Flight f4 = new Flight();
		initializeFlight(f4, "9C8882", "14:00", "16:30", "Shenzhen", "Shanghai", function.getDateAfterOneDay(), 580, 242);
		Flight f5 = new Flight();
		initializeFlight(f5, "9C8882", "14:00", "16:30", "Shenzhen", "Shanghai", function.getDateAfterOneWeek(), 580, 242);
		Flight f6 = new Flight();
		initializeFlight(f6, "9C8882", "14:00", "16:30", "Shenzhen", "Shanghai", function.getDateAfterOneMonth(), 580, 242);
		Flight rf4 = new Flight();
		initializeFlight(rf4, "9C8882", "14:00", "16:30", "Shanghai", "Shenzhen", function.getDateAfterOneDay(), 580, 242);
		Flight rf5 = new Flight();
		initializeFlight(rf5, "9C8882", "14:00", "16:30", "Shanghai", "Shenzhen", function.getDateAfterOneWeek(), 580, 242);
		Flight rf6 = new Flight();
		initializeFlight(rf6, "9C8882", "14:00", "16:30", "Shanghai", "Shenzhen", function.getDateAfterOneMonth(), 580, 242);
	}
	
}

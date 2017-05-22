package FlightTicketReservationSystem;

import java.util.ArrayList;


public class Data {
	
	static boolean isPassengerLogin = false;//缺省乘客是否登陆标记为假
	static boolean isAdminLogin = false;//缺省管理员是否登陆标记为假
	static int currentAdminIndex;//当前在线管理员序号
	static int currentPassengerIndex;//当前在线乘客序号
	static ArrayList<Admin> adminList = new ArrayList<Admin>();//创建管理员列表
	static ArrayList<Flight> flightList = new ArrayList<Flight>();//创建航班列表
	static int signUpPassengers = 1;//缺省无已注册用户人数
	static ArrayList<Passenger> passengerList = new ArrayList<Passenger>();//创建已注册乘客列表
	static String[] city = new String[3];
	static ArrayList<Order> orderList = new ArrayList<Order>();

}

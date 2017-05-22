package FlightTicketReservationSystem;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class Function {

// 输入接收器
static Scanner input = new Scanner(System.in);

// 查询航班关键字
final static int STARTCITY = 1;
final static int ARRIVALCITY = 2;
final static int DEPARTUREDATE = 3;

// 匹配判据
static boolean isMatch = false;
//判断判据
static boolean flg = false;
//账户是否可用
static boolean isAvailable = true;

//登陆关键字
final static int ADMIN =1;
final static int PASSENGER = 2;

// 星形分割线：
// 输出指定个数的*并换行
static void starSeparation(int starNumber) {
	for (int counter = 1; counter <= starNumber; counter++) {
		System.out.print("*");
		if (counter == starNumber)
			System.out.println();
	}
}

//是否纯数字
//空输入返回假，纯数字返回真，其他返回假
static boolean isPureNumber(String inputStr)
{
	boolean isPure = false;//定义是否纯数字判据，缺省为假
	boolean isNumber = false;//定义当前位数是否为数字判据，缺省为假
	//从第一位到最后一位做判断
	for(int counter = 0; counter < inputStr.length(); counter ++)
	{
		isNumber = false;//重置当前位数是否为数字判据为假
		//比较当前位数是否是为数字
		for(int number = 0; number <= 9; number ++)
		{
			if(inputStr.substring(counter, counter+1).compareTo("" + number) == 0)
			{
				isNumber = true;//设置当前位数为数字判据为真
			}
		}
		if(!isNumber)//如果当前位数不是数字，结束循环；否则进行下一次循环即下一位数的判定
			break;
		if(counter == inputStr.length() - 1 & isNumber)//如果到最后一位仍是数字
			isPure = true;//设置是否是纯数字判据为真
	}
	return isPure;
}

// 菜单：主界面
static void primaryMenu() {
	starSeparation(120);
	System.out.println("Welcome to Flight Ticket Reservation System!");
	starSeparation(120);
	System.out.println("What do you want to do ?");
	System.out.println("1: Flight query.");
	System.out.println("2: Login as administrator.");
	System.out.println("3: Login as passenger.");
	System.out.println("4: Sign up a passenger account.");
	System.out.println("5: Close.");
	System.out.print("Enter the index:");
	String indexStr = input.nextLine();
	if (isPureNumber(indexStr)) {
		int index = Integer.parseInt(indexStr);
		switch (index) {
		default:
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			input.nextLine();
			primaryMenu();
		case 1:
			updateFlight();
			queryMenu();
		case 2:
			login(ADMIN);
			adminMenu();
		case 3:
			login(PASSENGER);
			passengerMenu();
		case 4:
			signUp();
		case 5:
			System.exit(0);
		}
	} else {
		System.out.println("Error: Please check your input!");
		System.out.print("(press Enter to continue)");
		input.nextLine();
		primaryMenu();
	}
}

// 初始化
static void initialize() {

	initializeAdmin();//初始化管理员
	initializeCity();//初始化城市
	initializeFlight();//初始化航班
	initializePassenger();//初始化乘客
}

//初始化城市
static void initializeCity()
{
	Data.city[0]="Shenzhen";
	Data.city[1]="Beijing";
	Data.city[2]="Shanghai";
}

//管理员初始化
static void initializeAdmin()
{
	// 设置缺省管理员
	Admin defaultAdmin = new Admin();
	defaultAdmin.setUserName("admin");
	defaultAdmin.setPassword("***");
	Data.adminList.add(defaultAdmin);
}

//乘客初始化
static void initializePassenger()
{
	// 设置缺省乘客
	Passenger defaultPassenger = new Passenger();
	defaultPassenger.setUniqueID(0);//乘客注册序号
	defaultPassenger.setRealName("default");;
	defaultPassenger.setIdNumber("000000000000000000");;//身份证号
	defaultPassenger.setPassword("psw");;
	Data.passengerList.add(defaultPassenger);
}	

//航班初始化
static void initializeFlight()
{
	setFlight("CA1358", "10:00", "13:20", Data.city[0], Data.city[1], getDate(0), 2040, 324);
	setFlight("CA3403", "10:00", "13:20", Data.city[0], Data.city[1], getDate(1), 2040, 324);
	setFlight("CA4301", "10:00", "13:20", Data.city[0], Data.city[1], getDate(2), 2040, 324);
	setFlight("ZH9505", "11:40", "16:10", Data.city[0], Data.city[2], getDate(0), 840, 306);
	setFlight("ZH9515", "11:40", "16:10", Data.city[0], Data.city[2], getDate(1), 840, 306);
	setFlight("ZH9501", "11:40", "16:10", Data.city[0], Data.city[2], getDate(2), 840, 306);
	
	setFlight("HU7707", "12:30", "16:30", Data.city[1], Data.city[0], getDate(0), 1420, 262);
	setFlight("HU7705", "12:30", "16:30", Data.city[1], Data.city[0], getDate(1), 1420, 262);
	setFlight("HU7709", "12:30", "16:30", Data.city[1], Data.city[0], getDate(2), 1420, 262);
	setFlight("KN5987", "20:40", "22:15", Data.city[1], Data.city[2], getDate(0), 578, 242);
	setFlight("KN5955", "20:40", "22:15", Data.city[1], Data.city[2], getDate(1), 578, 242);
	setFlight("KN5977", "20:40", "22:15", Data.city[1], Data.city[2], getDate(2), 578, 242);
	
	setFlight("KN5955", "13:00", "15:00", Data.city[2], Data.city[0], getDate(0),456, 224);
	setFlight("KN5956", "13:00", "15:00", Data.city[2], Data.city[0], getDate(1), 456, 224);
	setFlight("KN5978", "13:00", "15:00", Data.city[2], Data.city[0], getDate(2), 456, 224);
	setFlight("HO1151", "13:00", "15:25", Data.city[2], Data.city[1], getDate(0), 690, 286);
	setFlight("HO1155", "13:00", "15:25", Data.city[2], Data.city[1], getDate(1), 690, 286);
	setFlight("KN1201", "13:00", "15:25", Data.city[2], Data.city[1], getDate(2), 690, 286);
}

//获取日期
static String getDate(int displaceNumber)
{
	String combineDate = null;
	Date date = new Date();
	int year = date.getYear() + 1900;
	int month = date.getMonth() + 1;
	int day = date.getDate();
	day = day + displaceNumber;
	do
	{
		if(day > numberOfDays(year, month))//如果号数大于当前月份天数
		{
			day -= numberOfDays(year, month);//号数减去当前月份天数
			month += 1;//月份数加1
			if(month > 12)//如果月份数大于12
			{year += 1;//年份数加1
				month -= 12;//月份数减12
			}
			
		}
	}while(day > numberOfDays(year, month));//如果号数大于当天月份的天数，则进行循环
	String monthStr;
	String dayStr;
	if(month >= 1 & month <=9) 
		monthStr = "0" + month;
	else
		monthStr = "" + month;
	if(day >= 1 & day <= 9)
		dayStr = "0" + day;
	else
		dayStr = "" + day;
	combineDate = "" + year + "-" + monthStr + "-" + dayStr;
	return combineDate;
}

//获取时间
static String getTime_HourAndMinute()
{
	Date date = new Date();
	int hour = date.getHours();
	int minute = date.getMinutes();
	String currentTime = null;
	String hourStr = null;
	String minuteStr = null;
	if(hour >= 0 & hour <=9) 
		hourStr = "0" + hour;
	else
		hourStr = "" + hour;
	if(minute >= 0 & minute <= 9)
		minuteStr = "0" + minute;
	else
		minuteStr = "" + minute;
	currentTime = hourStr + ":" + minuteStr;
	return currentTime;
}

//获取详细时间
static String getTime()
{
	return getDate(0) + " " + getTime_HourAndMinute();
}

//是否是闰年
static boolean isLeapYear(int year)
{
	if (year % 400 == 0 || ((year % 4 == 0) & (year % 100 != 0)))
		return true;
	else return false;
}

//对应月份的天数
static int numberOfDays(int year, int month)
{
	int number = 0;
	if(isLeapYear(year))
	{
		switch(month)
		{
		case 1:
			number = 31;
		case 2:
			number = 29;
		case 3:
			number = 31;
		case 4:
			number = 30;
		case 5:
			number = 31;
		case 6:
			number = 30;
		case 7:
			number = 31;
		case 8:
			number = 31;
		case 9:
			number = 30;
		case 10:
			number = 31;
		case 11:
			number = 30;
		case 12:
			number = 31;
		}
	}
	else
	{
		switch(month)
		{
		case 1:
			number = 31;
		case 2:
			number = 28;
		case 3:
			number = 31;
		case 4:
			number = 30;
		case 5:
			number = 31;
		case 6:
			number = 30;
		case 7:
			number = 31;
		case 8:
			number = 31;
		case 9:
			number = 30;
		case 10:
			number = 31;
		case 11:
			number = 30;
		case 12:
			number = 31;
		}
	}
	return number;
}
	
//初始化一个航班并加入航班列表
static void setFlight(String FlightID, String StartTime, String ArrivalTime,
		String StartCity, String ArrivalCity, String DepartureDate, int Price, int SeatCapacity)
{
	Flight flight = new Flight();
	flight.setFlightID(FlightID);
	flight.setStartTime(StartTime);
	flight.setArrivalTime(ArrivalTime);
	flight.setStartCity(StartCity);
	flight.setArrivalCity(ArrivalCity);
	flight.setDepartureDate(DepartureDate);
	flight.setPrice(Price);
	flight.setSeatCapacity(SeatCapacity);
	flight.createSeatList();
	flight.initializeSeatList();
	Data.flightList.add(flight);
}

// 菜单：查询航班
static void queryMenu() {
	starSeparation(120);
	System.out.println("[Flight　Query]");
	starSeparation(120);
	System.out.println("What do you want to do ?");
	System.out.println("1: Query by start city.");
	System.out.println("2: Query by arrival city.");
	System.out.println("3: Query by depature date.");
	System.out.println("4: Back to primary menu.");
	System.out.print("Enter the index:");
	String indexStr = input.nextLine();
	if (isPureNumber(indexStr)) {
		int index = Integer.parseInt(indexStr);
		switch (index) {
		default:
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			input.nextLine();
			queryMenu();
		case 1:
			flightQuery_Passenger(STARTCITY);// 按起飞城市查询
			if(isMatch)
				isReservation();
			queryMenu();
		case 2:
			flightQuery_Passenger(ARRIVALCITY);// 按到达城市查询
			if(isMatch)
				isReservation();
			queryMenu();
		case 3:
			flightQuery_Passenger(DEPARTUREDATE);// 按起飞日期查询
			if(isMatch)
				isReservation();
			queryMenu();
		case 4:
			primaryMenu();
		}
	} else {
		System.out.println("Error: Please check your input!");
		System.out.print("(press Enter to continue)");
		input.nextLine();
		queryMenu();
	}
}

// 子菜单：乘客查询航班
static void queryMenu_Passenger() {
	starSeparation(120);
	System.out.println("[Flight　Query]");
	starSeparation(120);
	System.out.println("What do you want to do ?");
	System.out.println("1: Query by start city.");
	System.out.println("2: Query by arrival city.");
	System.out.println("3: Query by depature date.");
	System.out.println("4: Back to privious menu.");
	System.out.print("Enter the index:");
	String indexStr = input.nextLine();
	if (isPureNumber(indexStr)) {
		int index = Integer.parseInt(indexStr);
		switch (index) {
		default:
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			input.nextLine();
			queryMenu();
		case 1:
			flightQuery_Passenger(STARTCITY);
			if(isMatch)
				isReservation();
			queryMenu_Passenger();// 按起飞城市查询
			break;
		case 2:
			flightQuery_Passenger(ARRIVALCITY);
			if(isMatch)
				isReservation();
			queryMenu_Passenger();// 按到达城市查询
			break;
		case 3:
			flightQuery_Passenger(DEPARTUREDATE);
			if(isMatch)
				isReservation();
			queryMenu_Passenger();// 按起飞日期查询
			break;
		case 4:
			passengerMenu();
		}
	} else {
		System.out.println("Error: Please check your input!");
		System.out.print("(press Enter to continue)");
		input.nextLine();
		queryMenu_Passenger();
	}
}

//子菜单：管理员查询航班
static void queryMenu_Admin() {
	starSeparation(120);
	System.out.println("[Flight　Query]");
	starSeparation(120);
	System.out.println("What do you want to do ?");
	System.out.println("1: Query by start city.");
	System.out.println("2: Query by arrival city.");
	System.out.println("3: Query by depature date.");
	System.out.println("4: Back to privious menu.");
	System.out.print("Enter the index:");
	String indexStr = input.nextLine();
	if (isPureNumber(indexStr)) {
		int index = Integer.parseInt(indexStr);
		switch (index) {
		default:
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			input.nextLine();
			queryMenu();
		case 1:
			flightQuery_Admin(STARTCITY);
			queryMenu_Admin();// 按起飞城市查询
			break;
		case 2:
			flightQuery_Admin(ARRIVALCITY);
			queryMenu_Passenger();// 按到达城市查询
			break;
		case 3:
			flightQuery_Admin(DEPARTUREDATE);
			queryMenu_Admin();// 按起飞日期查询
			break;
		case 4:
			adminMenu();
		}
	} else {
		System.out.println("Error: Please check your input!");
		System.out.print("(press Enter to continue)");
		input.nextLine();
		queryMenu_Admin();
	}
}

// 次级菜单：通过关键词查询航班
// 根据关键词、用户输入来查找是否有匹配的航班，匹配则显示航班序号及信息，无一匹配则报错
static void flightQuery_Passenger(int keywordIndex) {
	switch (keywordIndex) {
	default:
		break;
	case STARTCITY:
		starSeparation(120);
		System.out.println("[Query By Start City]");
		starSeparation(120);
		String startCity = null;
		System.out.print("Please input the start city:");
		startCity = getInputStr(startCity);
		matchFlight_Passenger(STARTCITY, startCity);
		break;
	case ARRIVALCITY:
		starSeparation(120);
		System.out.println("[Query By Arrival City]");
		starSeparation(120);
		String arrivalCity = null;
		System.out.print("Please input the arrival city:");
		arrivalCity = getInputStr(arrivalCity);
		matchFlight_Passenger(ARRIVALCITY, arrivalCity);
		break;
	case DEPARTUREDATE:
		starSeparation(120);
		System.out.println("[Query By Departure Date]");
		starSeparation(120);
		String departureDate = null;
		System.out.print("Please input the departure date(Format:yyyy-mm-dd):");
		departureDate = getInputStr(departureDate);
		matchFlight_Passenger(DEPARTUREDATE, departureDate);
		break;
	}
}

//管理员普通查询
static void flightQuery_Admin(int keywordIndex) {
	switch (keywordIndex) {
	default:
		break;
	case STARTCITY:
		starSeparation(120);
		System.out.println("[Query By Start City]");
		starSeparation(120);
		String startCity = null;
		System.out.print("Start city:");
		startCity = getInputStr(startCity);
		matchFlight_Admin(STARTCITY, startCity);
		break;
	case ARRIVALCITY:
		starSeparation(120);
		System.out.println("[Query By Arrival City]");
		starSeparation(120);
		String arrivalCity = null;
		System.out.print("Arrival city:");
		arrivalCity = getInputStr(arrivalCity);
		matchFlight_Admin(ARRIVALCITY, arrivalCity);
		break;
	case DEPARTUREDATE:
		starSeparation(120);
		System.out.println("[Query By Departure Date]");
		starSeparation(120);
		String departureDate = null;
		System.out.print("Departure date(Format:yyyy-mm-dd):");
		departureDate = getInputStr(departureDate);
		matchFlight_Admin(DEPARTUREDATE, departureDate);
		break;
	}
}

// 获取非空（回车）输入
static String getInputStr(String inputStr) {
	do {
		inputStr = input.nextLine();
		if (inputStr.isEmpty())
			System.out.println("Error! Please check your input!");
	} while (inputStr.isEmpty());
	return inputStr;
}

// 根据关键词匹配航班
// keywordIndex为1、2、3依次代表以起飞城市、目的城市、起飞日期为关键词进行查询
// 如果用户输入有匹配，则打印匹配航班序号、匹配航班信息；如果无一匹配，则报错
static void matchFlight_Admin(int keywordIndex, String keyword) {
	updateFlight();
	isMatch = false;// 重置匹配判据
	switch (keywordIndex)// 选择按照何种关键词查询
	{
	default:
		break;
	case STARTCITY:// 按照起飞城市查询航班
		for (int index = 0; index < Data.flightList.size(); index++) {
			if (keyword.toLowerCase().compareTo(Data.flightList.get(index).getStartCity().toLowerCase()) == 0) {
				isMatch = true;
				showFlight_Admin(Data.flightList.get(index));
			}
		}
		if (!isMatch) {
			System.out.println("Error! No flight match.");
		}
		break;
	case ARRIVALCITY:// 按照到达城市查询航班
		for (int index = 0; index < Data.flightList.size(); index++) {
			if (keyword.toLowerCase().compareTo(Data.flightList.get(index).getArrivalCity().toLowerCase()) == 0) {
				isMatch = true;
				System.out.println("Flight Index:" + index);
				showFlight_Admin(Data.flightList.get(index));
			}
		}
		if (!isMatch) {
			System.out.println("Error! No flight match.");
		}
		break;
	case DEPARTUREDATE:// 按照起飞日期查询航班
		for (int index = 0; index < Data.flightList.size(); index++) {
			if (keyword.toLowerCase().compareTo(Data.flightList.get(index).getDepartureDate().toLowerCase()) == 0) {
				isMatch = true;
				System.out.println("Flight Index:" + index);
				showFlight_Admin(Data.flightList.get(index));
			}
		}
		if (!isMatch) {
			System.out.println("Error! No flight match.");
		}
		break;
	}
}


// 根据关键词匹配航班
// keywordIndex为1、2、3依次代表以起飞城市、目的城市、起飞日期为关键词进行查询
// 如果用户输入有匹配，则打印匹配航班序号、匹配航班信息；如果无一匹配，则报错
static void matchFlight_Passenger(int keywordIndex, String keyword) {
	updateFlight();
	isMatch = false;// 重置匹配判据
	switch (keywordIndex)// 选择按照何种关键词查询
	{
	default:
		break;
	case STARTCITY:// 按照起飞城市查询航班
		for (int index = 0; index < Data.flightList.size(); index++) {
			if ((keyword.toLowerCase().compareTo(Data.flightList.get(index).getStartCity().toLowerCase()) == 0 )
					& Data.flightList.get(index).getStatus() != FlightStatus.UNPUBLISHED 
					& Data.flightList.get(index).getStatus() != FlightStatus.TERMINATE) 
			{
				isMatch = true;
				showFlight_Passenger(Data.flightList.get(index));
			}
		}
		if (!isMatch) {
			System.out.print("Error! No flight match.(Press Enter to continue)");
			input.nextLine();
		}
		break;
	case ARRIVALCITY:// 按照到达城市查询航班
		for (int index = 0; index < Data.flightList.size(); index++) {
			if (keyword.toLowerCase().compareTo(Data.flightList.get(index).getArrivalCity().toLowerCase()) == 0
					& (Data.flightList.get(index).getStatus() != FlightStatus.UNPUBLISHED 
					& Data.flightList.get(index).getStatus() != FlightStatus.TERMINATE)) {
				isMatch = true;
				System.out.println("Flight Index:" + index);
				showFlight_Passenger(Data.flightList.get(index));
			}
		}
		if (!isMatch) {
			System.out.print("Error! No flight match.(Press Enter to continue)");
			input.nextLine();
		}
		break;
	case DEPARTUREDATE:// 按照起飞日期查询航班
		for (int index = 0; index < Data.flightList.size(); index++) {
			if (keyword.compareTo(Data.flightList.get(index).getDepartureDate()) == 0
					& (Data.flightList.get(index).getStatus() != FlightStatus.UNPUBLISHED 
					& Data.flightList.get(index).getStatus() != FlightStatus.TERMINATE)) {
				isMatch = true;
				showFlight_Passenger(Data.flightList.get(index));
			}
		}
		if (!isMatch) {
			System.out.print("Error! No flight match.(Press Enter to continue)");
			input.nextLine();
		}
		break;
	}
}

//是否预定航班
static void isReservation()
{
	System.out.print("Do you want to book flight?(Yes/No)");
	String tempInput = input.nextLine();
	if(tempInput.toLowerCase().compareTo("yes") == 0)
		flightReservation();
}

//预定航班
static void flightReservation()
{
	do
	{
		int flightIndex = -1;
		do
		{
		flg = false;//重置预定判据
		System.out.print("Plese enter the index of the flight you want to book:");
		System.out.print("(Enter 'back' back to privious menu)");
		String indexStr = input.nextLine();
		if(indexStr.toLowerCase().compareTo("back") == 0)
			passengerMenu();
		if(isPureNumber(indexStr))
		{
			flightIndex = Integer.parseInt(indexStr);
			flg = true;
			break;
		}
		else
		{
			System.out.print("Error! Plese check your input.(Press Enter to continue)");
			input.nextLine();
		}
		}while(!flg);
		flg = false;
		if(flightIndex >= 0 & flightIndex < Data.flightList.size())//如果输入的序号合法
		{
			if(Data.flightList.get(flightIndex).getStatus() != FlightStatus.UNPUBLISHED
					& Data.flightList.get(flightIndex).getStatus() != FlightStatus.TERMINATE)//如果目标航班有效
			{
				showFlight_Passenger(Data.flightList.get(flightIndex));//显示目标航班信息
				System.out.print("Is it the flight you want to book?(yes/no)");
				String tempInput = input.nextLine();
				if(tempInput.toLowerCase().compareTo("yes") == 0)//如果确认要预定
				{
					//遍历当前乘客订单列表
					for(int index = 0; index < Data.passengerList.get(Data.currentPassengerIndex)
							.getOrderListSize(); index ++)
					{
						//如果欲预定航班订单已存在，则报错
						if(Data.flightList.get(flightIndex) == Data.passengerList.get(Data.currentPassengerIndex)
								.getOrder(index).getFlight() & Data.passengerList.get(Data.currentPassengerIndex)
								.getOrder(index).getStatus() != OrderStatus.CANCEL)
						{
							flg = true;//设置匹配判据为真
							System.out.print("You already book this flight.(Press Enter to continue)");
							input.nextLine();
							break;
						}
					}
					if(flg)
						break;//如果匹配判据为真，则退出循环
					flg = false;//重置匹配判据
					if(Data.flightList.get(flightIndex).getStatus() == FlightStatus.FULL)//如果目标航班已满
					{
						System.out.print("Sorry! This flight was full.(Press Enter to continue)");
						input.nextLine();
					}
					else//如果目标航班可定
					{
						if(Data.isPassengerLogin)//如果乘客已登录
						{
							Order newOrder = new Order();
							//设置真实姓名
							newOrder.setRealName(Data.passengerList.get(Data.currentPassengerIndex).getRealName());
							//设置唯一编号
							newOrder.setUniqueID(Data.passengerList.get(Data.currentPassengerIndex).getUniqueID());
							//设置座位号
							newOrder.setSeatNum(assginSeat(Data.flightList.get(flightIndex)));
							//设置对应航班
							newOrder.setFlight(Data.flightList.get(flightIndex));
							//对应航班乘客列表添加此乘客
							newOrder.getFlight().addPassenger(newOrder.getPassenger());
							//对应航班乘客数更新
							Data.flightList.get(flightIndex).updataCurrentPassengers();
							//设置创建时间
							newOrder.setCreateTime(getTime());
							//询问是否支付
							System.out.print("Do you want to paid it now?(yes/no)");
							String isSure = input.nextLine();
							//立即支付
							if (isSure.toLowerCase().compareTo("yes") == 0) {
								newOrder.setOrderPAID();
								System.out.print("Success!(Press Enter to continue)");
								input.nextLine();
							}
							//乘客订单列表添加次订单
							Data.passengerList.get(Data.currentPassengerIndex).addOrder(newOrder);
							//总订单列表添加此订单
							Data.orderList.add(newOrder);
							System.out.print("Success!(Press enter to continue)");
							flg = true;
							input.nextLine();
							break;
						}
						else//如果乘客未登陆
						{
							System.out.println("Sorry, you need to sign in first.");
							//乘客登陆
							login(PASSENGER);
							Order newOrder = new Order();
							//设置真实姓名
							newOrder.setRealName(Data.passengerList.get(Data.currentPassengerIndex).getRealName());
							//设置唯一编号
							newOrder.setUniqueID(Data.passengerList.get(Data.currentPassengerIndex).getUniqueID());
							//设置座位号
							newOrder.setSeatNum(assginSeat(Data.flightList.get(flightIndex)));
							//设置对应航班
							newOrder.setFlight(Data.flightList.get(flightIndex));
							//对应航班乘客列表添加此乘客
							newOrder.getFlight().addPassenger(newOrder.getPassenger());
							//对应航班乘客数更新
							Data.flightList.get(flightIndex).updataCurrentPassengers();
							//设置创建时间
							newOrder.setCreateTime(getTime());
							//是否立即支付
							System.out.print("Do you want to paid it now?(yes/no)");
							String isSure = input.nextLine();
							//立即支付
							if (isSure.toLowerCase().compareTo("yes") == 0) {
								newOrder.setOrderPAID();
								System.out.print("Success!(Press Enter to continue)");
								input.nextLine();
							}
							//对应航班乘客列表添加此乘客
							newOrder.getFlight().addPassenger(newOrder.getPassenger());
							//此乘客订单列表添加此订单
							Data.passengerList.get(Data.currentPassengerIndex).addOrder(newOrder);
							//总订单列表添加次订单
							Data.orderList.add(newOrder);
							System.out.print("Success!(Press enter to continue)");
							flg = true;//预定判据设为真
							input.nextLine();
							break;
						}
					}
				}
				else//如果乘客选择不预定
				{
					flightReservation();
				}
			}
			else//目标航班无效
			{
				System.out.print("Sorry!This flight is unavailable now.(Press Enter to continue)");
				input.nextLine();
				flg = true;//预定判据设为真
			}
		}
		else//如果乘客输入的flight index非法
		{
			System.out.println("Error! Please input the correct flight index number");
		}
	}while(!flg);//未预定成功则进行循环
}

//退订航班
static void flightUnsubscribtion()
{
	do
	{
		flg = false;//重置退订判据
		//如果当前乘客的订单列表为空
		if(Data.passengerList.get(Data.currentPassengerIndex).getOrderListSize() == 0)
		{
			System.out.println("No order.(Press enter to continue)");
			input.nextLine();
			break;
		}
		//遍历当前乘客订单列表，并显示订单信息
		for(int index = 0; index < Data.passengerList.get(Data.currentPassengerIndex).getOrderListSize(); index ++)
		{
			starSeparation(100);
			System.out.printf("%-20s%s%d\n","||| Order Index",":", index);
			System.out.printf("%-20s%s%d\n","||| Seat",":", Data.passengerList.get(Data.currentPassengerIndex)
					.getOrder(index).getSeatNum());
			System.out.println("||| Order Flight:");
			showFlight_Passenger(Data.passengerList.get(Data.currentPassengerIndex).getOrder(index).getFlight());
			System.out.printf("%-20s%s%s\n","||| Order Status",":", Data.passengerList.get(Data.currentPassengerIndex).getOrder(index).getStatus());
			starSeparation(100);
		}
		//询问欲退订的订单编号
		int orderIndex = -1;
		do
		{
			flg = false;
			System.out.print("Plese enter the index of the order you want to unsubscrib:");
			System.out.print("(Enter 'back' back to privious menu)");
			String orderIndexStr = input.nextLine();
			if(isPureNumber(orderIndexStr))
			{
				orderIndex = Integer.parseInt(orderIndexStr);
				break;
			}
			else
			{
				System.out.print("Error! Please check your input.(Press enter to continue)");
				input.nextLine();
			}
		}while(!flg);
		if(orderIndex == -1)
			passengerMenu();
		//如果序号合法
		if(orderIndex >= 0 & orderIndex < Data.passengerList.get(Data.currentPassengerIndex).getOrderListSize())
		{
			//显示目标订单信息
			Data.passengerList.get(Data.currentPassengerIndex).showOrder
				(Data.passengerList.get(Data.currentPassengerIndex).getOrder(orderIndex));
			//询问是否欲退订该订单
			System.out.print("Is it the order you want to unsubscribe?(yes/no)");
			String tempInput = input.nextLine();
			//退订订单
			if(tempInput.toLowerCase().compareTo("yes") == 0)
			{
				//从乘客订单列表中设置此订单状态为取消
				Data.passengerList.get(Data.currentPassengerIndex).getOrder(orderIndex).setOrderCANCEL();
				//从对应航班的乘客列表中移除此乘客
				Data.passengerList.get(Data.currentPassengerIndex).getOrder(orderIndex)
					.getFlight().deletePassenger(Data.passengerList.get(Data.currentPassengerIndex));
				//对应航班乘客数更新
				Data.passengerList.get(Data.currentPassengerIndex).getOrder(orderIndex)
					.getFlight().updataCurrentPassengers();	
				//释放该乘客对应的座位
				Data.passengerList.get(Data.currentAdminIndex).getOrder(orderIndex).getFlight().releaseSeat
					(Data.passengerList.get(Data.currentAdminIndex).getOrder(orderIndex).getSeatNum() - 1);
				flg = true;//设置退订判据为真
				System.out.print("Success!(Press enter to continue)");
				input.nextLine();
			}
			else//如果乘客选择不退订
			{
				flightUnsubscribtion();
			}
		}
		else//如果乘客输入的order index非法
		{
			System.out.println("Error! Please input the correct order index number");
			System.out.print("(Press enter to continue)");
			input.nextLine();
		}
	}while(!flg);//未预定成功则进行循环
}

//显示订单
static void showAllOrder()
{
	if(Data.passengerList.get(Data.currentPassengerIndex).getOrderListSize() == 0)
	{
		System.out.print("No found.(Press enter to continue)");
		input.nextLine();
	}
	else
	{
		for(int index = 0; index < Data.passengerList.get(Data.currentPassengerIndex).getOrderListSize(); index++)
		{
			Data.passengerList.get(Data.currentPassengerIndex)
			.showOrder(Data.passengerList.get(Data.currentPassengerIndex).getOrder(index));
		}
		System.out.print("(Press enter to continue)");
		input.nextLine();
	}
}

//支付订单
static void paidOrder()
{
	System.out.print("Do you want to paid order?(yes/no)");
	String isSure = input.nextLine();
	if (isSure.toLowerCase().compareTo("yes") == 0) //支付订单
	{
		int orderIndex = -1;
		do
		{
			flg = false;
			System.out.println("Please input the index of order which you want to paid.");
			System.out.print("(Enter 'back' to cancel)");
			String orderIndexStr = input.nextLine();
			if(orderIndexStr.toLowerCase().compareTo("back") == 0)
				passengerMenu();
			if(isPureNumber(orderIndexStr))
			{
				orderIndex = Integer.parseInt(orderIndexStr);
				flg = true;
				break;
			}
			else
			{
				System.out.print("Error! Pleace check your input.(Press Enter to continue)");
				input.nextLine();
				break;
			}
		}while(!flg);
		if(orderIndex != -1)
		{
			Order aimOrder = Data.passengerList.get(Data.currentPassengerIndex).getOrder(orderIndex);
			if(aimOrder.getStatus() == OrderStatus.UNPAID)
			{
				aimOrder.setOrderPAID();
				System.out.print("Success!(Press Enter to continue)");
				input.nextLine();
			}
			else if(aimOrder.getStatus() == OrderStatus.CANCEL)
			{
				System.out.print("Error! This order already cancel.(Press Enter to continue)");
				input.nextLine();
			}
			else
			{
				System.out.print("Error! This order already paid.(Press Enter to continue)");
				input.nextLine();
			}
		}
	}
}

//分配座位
static int assginSeat(Flight flight)
{
	int seatNumber = -1;//缺省座位号为-1
	//遍历对应航班的座位列表，将第一个为空的座位返回，并将其设置为占用
	for(int index = 0; index < flight.getSeatNumbers(); index++)
	{
		if(!flight.getSeatStatus(index))
		{
			seatNumber = index + 1;
			flight.occupySeat(index);
			break;
		}
	}
	return seatNumber;
}

//创建新航班
static void createFlight() {

	starSeparation(120);
	System.out.println("[Create Flight]");
	starSeparation(120);
	Flight newFlight = new Flight();
	System.out.print("Flight ID:");
	newFlight.setFlightID(input.nextLine());//设置航班号
	//设置起飞时间
	String startTime;
	do
	{
		flg = false;//重置设置判据
		System.out.print("Start time(hh:mm):");
		startTime = input.nextLine();
		if(startTime.length() != 5)//如果起飞时间长度不合法
		{
			System.out.print("Error! Check your input.(Press enter to continue)");
			input.nextLine();
		}
		//否则如果起飞时间格式不合法
		else if(startTime.substring(2, 3).compareTo(":") != 0 
				| !isPureNumber(startTime.substring(0,2)) | !isPureNumber(startTime.substring(4)))
		{
			System.out.print("Error! Check your format.(Press enter to continue)");
			input.nextLine();
		}
		//否则如果起飞时间数字不合法
		else if(Integer.parseInt(startTime.substring(0,2)) < 0 | Integer.parseInt(startTime.substring(0,2)) > 24
				| Integer.parseInt(startTime.substring(4)) < 0 | Integer.parseInt(startTime.substring(4)) > 60)
		{
			System.out.print("Error! Check your input.(Press enter to continue)");
			input.nextLine();
		}
		//否则设置判据设为真
		else 
			flg = true;
	}while(!flg);
	newFlight.setStartTime(startTime);
	String arrivalTime;
	do
	{
		flg = false;
		System.out.print("Arrival time(hh:mm):");
		arrivalTime = input.nextLine();
		if(arrivalTime.length() != 5)
		{
			System.out.print("Error! Check your input.(Press enter to continue)");
			input.nextLine();
		}
		else if(arrivalTime.substring(2, 3).compareTo(":") != 0 
				| !isPureNumber(arrivalTime.substring(0,2)) | !isPureNumber(arrivalTime.substring(4)))
		{
			System.out.print("Error! Check your format.(Press enter to continue)");
			input.nextLine();
		}
		else if(Integer.parseInt(arrivalTime.substring(0,2)) < 0 | Integer.parseInt(arrivalTime.substring(0,2)) >= 24
				| Integer.parseInt(arrivalTime.substring(4)) < 0 | Integer.parseInt(arrivalTime.substring(4)) >= 60)
		{
			System.out.print("Error! Check your input.(Press enter to continue)");
			input.nextLine();
		}
		else 
			flg = true;
	}while(!flg);
	newFlight.setArrivalTime(arrivalTime);
	System.out.print("Start city:");
	newFlight.setStartCity(input.nextLine());
	System.out.print("Arrival city:");
	newFlight.setArrivalCity(input.nextLine());
	String departureData;
	do
	{
		flg = false;
		System.out.print("Departure Date(Date Format:yyyy-mm-dd):");
		departureData = input.nextLine();
		if(departureData.length() != 10)
		{
			System.out.print("Error! Check your input.(Press enter to continue)");
			input.nextLine();
		}
		else if(departureData.substring(4, 5).compareTo("-") != 0 & departureData.substring(7, 8).compareTo("-") != 0
				& !isPureNumber(departureData.substring(0,4)) & !isPureNumber(departureData.substring(5,7))
				& !isPureNumber(departureData.substring(8)))
		{
			System.out.print("Error! Check your format.(Press enter to continue)");
			input.nextLine();
		}
		else if(Integer.parseInt(departureData.substring(0,4)) < 2017 | Integer.parseInt(departureData.substring(5,7)) < 0
				| Integer.parseInt(departureData.substring(5,7)) > 12 | Integer.parseInt(departureData.substring(8)) < 0 
						| Integer.parseInt(departureData.substring(8)) > 
						numberOfDays(Integer.parseInt(departureData.substring(0,4)), Integer.parseInt(departureData.substring(5,7))))
		{
			System.out.print("Error! Check your input.(Press enter to continue)");
			input.nextLine();
		}
		else 
			flg = true;
	}while(!flg);
	newFlight.setDepartureDate(departureData);
	do
	{
		flg = false;
		System.out.print("Price:");
		String priceStr = input.nextLine();
		if(isPureNumber(priceStr))
		{
			newFlight.setPrice(Integer.parseInt(priceStr));
			flg = true;
		}
		else
		{
			System.out.print("Error! Integer only.(Press Enter to continue)");
			input.nextLine();
		}
	}while(!flg);
	do
	{
		flg = false;
		System.out.print("Seat capacity:");
		String seatCapacityStr = input.nextLine();
		if(isPureNumber(seatCapacityStr))
		{
			newFlight.setSeatCapacity(Integer.parseInt(seatCapacityStr));
			flg = true;
		}
		else
		{
			System.out.print("Error! Integer only.(Press Enter to continue)");
			input.nextLine();
		}
	}while(!flg);
	newFlight.initializeSeatList();
	Data.flightList.add(newFlight);
	System.out.print("Success!(Press Enter to continue)");
	input.nextLine();

}

//操作：修改未发布航班的信息
static void modifyUnpublishedFlight(Flight flight) {
	starSeparation(120);
	System.out.println("[Modify Flight]");
	System.out.println("1:Start time");
	System.out.println("2:Arrival time");
	System.out.println("3:Start city");
	System.out.println("4:Arrival city");
	System.out.println("5:Departure Date");
	System.out.println("6:Price");
	System.out.println("7:Seat capacity");
	System.out.print("Which information you want to adjust:");
	String indexStr = input.nextLine();
	if (isPureNumber(indexStr)) {
		int index = Integer.parseInt(indexStr);
		switch (index) {
		default:
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			input.nextLine();
			modifyUnpublishedFlight(flight);
		case 1:// 修改起飞时间
			System.out.print("New Start time:");
			flight.setStartTime(input.nextLine());
			break;
		case 2:// 修改到达时间
			System.out.print("New Arrival time:");
			flight.setArrivalTime(input.nextLine());
			break;
		case 3:// 修改起飞城市
			System.out.print("New Start city:");
			flight.setStartCity(input.nextLine());
			break;
		case 4:// 修改目标城市
			System.out.print("New Arrival city:");
			flight.setArrivalCity(input.nextLine());
			break;
		case 5:// 修改起飞日期
			System.out.print("New Departure Date:");
			flight.setDepartureDate(input.nextLine());
			break;
		case 6:// 修改价格
			System.out.print("New Price:");
			flight.setPrice(input.nextInt());
			input.nextLine();//接收nextInt的回车符'\n'
			break;
		case 7:// 修改航班容量
			System.out.print("New Seat capacity:");
			flight.setSeatCapacity(input.nextInt());
			input.nextLine();//接收nextInt的回车符'\n'
			break;
		}
	} else {
		System.out.println("Error: Please check your input!");
		System.out.print("(press Enter to continue)");
		input.nextLine();
		modifyUnpublishedFlight(flight);
	}

	int index = Data.flightList.indexOf(flight);
	Data.flightList.set(index, flight);
	System.out.print("Success!(press Enter to continue)");
	input.nextLine();
}

// 操作：修改已发布航班的信息
static void modifyPublishedFlight(Flight flight) {
		starSeparation(120);
		System.out.println("[Modify Flight]");
		System.out.println("1:Price");
		System.out.println("2:Seat capacity");
		System.out.print("Which information you want to adjust:");
		String indexStr = input.nextLine();
		if (isPureNumber(indexStr))//如果输入合法
		{
			int index = Integer.parseInt(indexStr);//转化为整型
			switch (index)//判断选择
			{
			default:
				System.out.println("Error: Please check your input!");
				System.out.print("(press Enter to continue)");
				input.nextLine();
				modifyPublishedFlight(flight);
				break;
			case 1:// 修改价格
				int newPrice = -1;
				do
				{
					flg = false;
					System.out.print("New Price:");
					String newPriceStr = input.nextLine();
					if(isPureNumber(newPriceStr))
					{
						newPrice = Integer.parseInt(newPriceStr);
						flg = true;
					}
					else
					{
						System.out.print("Error! Integer only.(Press enter to continue)");
						input.nextLine();
					}
				}while(!flg);
				flight.setPrice(newPrice);
				int flightIndex1 = Data.flightList.indexOf(flight);
				Data.flightList.set(flightIndex1, flight);
				System.out.print("Success!(press Enter to continue)");
				input.nextLine();
				break;
			case 2:// 修改航班容量	
				int seatCapacity = -1;
				do
				{	
					flg = false;
					System.out.print("New Seat capacity:");
					String seatCapacityStr = input.nextLine();
					if(isPureNumber(seatCapacityStr))
						seatCapacity = Integer.parseInt(seatCapacityStr);
					else
					{
						System.out.print("Error! Integer only.(Press enter to continue)");
						input.nextLine();
					}
					if(flight.getStatus() == FlightStatus.FULL)
					{
						if(seatCapacity >= flight.getSeatCapacity())
						{
							flight.setSeatCapacity(seatCapacity);
							flg = true;
							break;
						}
						else
						{
							System.out.print("Error! Passenger number overflow.(Press enter to continue)");
							input.nextLine();
						}
					}
					flight.setSeatCapacity(seatCapacity);
					flg = true;
				}while(!flg);
				int flightIndex2 = Data.flightList.indexOf(flight);
				Data.flightList.set(flightIndex2, flight);
				System.out.print("Success!(press Enter to continue)");
				input.nextLine();
				break;
			}
		}
		else// 输入不合法 
		{
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			input.nextLine();
			modifyPublishedFlight(flight);
		}	
}

//删除航班
static void deleteFlight() {
	updateFlight();
	System.out.println("All fight show as follows:");
	for (int index = 0; index < Data.flightList.size(); index++) // 显示所有未发布或终止的航班的信息
	{
		if(Data.flightList.get(index).getStatus() == FlightStatus.UNPUBLISHED 
				| Data.flightList.get(index).getStatus() == FlightStatus.TERMINATE)
			showFlight_Admin(Data.flightList.get(index));
	}
	int flightIndex = -1;
	do
	{
		flg = false;
		System.out.println("The flight index of which you want to delete:");
		System.out.print("(Enter 'back' back to privious menu):");
		String flightIndexStr = input.nextLine();
		if(flightIndexStr.toLowerCase().compareTo("back") == 0)
			adminMenu();
		if(isPureNumber(flightIndexStr))
		{
			flightIndex = Integer.parseInt(flightIndexStr);
			flg = true;
			break;
		}
		else
		{
			System.out.println("Eror! Integer only.(Press Enter to continue)");
			input.nextLine();
		}
	}while(!flg);
	if (flightIndex != -1)  {
		System.out.printf("Waring:Are you sure to delete Flight%d?(yes/no)", flightIndex);
		String isSure = input.nextLine();
		if (isSure.toLowerCase().compareTo("yes") == 0) {
			if(Data.flightList.get(flightIndex).getStatus() == FlightStatus.UNPUBLISHED
					| Data.flightList.get(flightIndex).getStatus() == FlightStatus.TERMINATE)
			{
				Data.flightList.remove(flightIndex);// 从航班列表从移除该航班
				System.out.print("Success!(Press Enter to continue)");
				input.nextLine();
			}
			else
			{
				System.out.print("Error!(Press Enter to continue)");
				input.nextLine();
			}
			
		}
	}
}

//发布航班
static void publishFlight() {
	updateFlight();
	starSeparation(120);
	// 显示所有未发布航班
	System.out.println("All unpublished fight show as follows:");
	for (int index = 0; index < Data.flightList.size(); index++)
	{
		if(Data.flightList.get(index).getStatus() == FlightStatus.UNPUBLISHED)
			showFlight_Admin(Data.flightList.get(index));
	}
	System.out.print("Plese enter the flight index which you want to publish:");
	System.out.print("(Enter 'back' back to privious menu)");
	String indexStr = input.nextLine();
	if(indexStr.toLowerCase().compareTo("back") == 0)
		adminMenu();
	if (isPureNumber(indexStr)) 
	{
		int flightIndex = Integer.parseInt(indexStr);
		if(flightIndex < Data.flightList.size())
		{		
			System.out.printf("NOTE:Are you sure to publish Flight%d?(yes/no)", flightIndex);
			String isSure = input.nextLine();
			if (isSure.toLowerCase().compareTo("yes") == 0) {
				Data.flightList.get(flightIndex).setFlightAVAILABLE();;// 修改目标航班状态为已发布
				System.out.print("Success!(Press enter to continue)");
				input.nextLine();
			}
		}
		else
		{
			System.out.print("Error! Index overflow.(Press enter to continue)");
			input.nextLine();
		}
	}
	else
	{
		System.out.print("Error! Please check your input.(Press enter to continue)");
		input.nextLine();
	}
}

//更新航班
static void updateFlight()
{
	//日期格式：yyyy-mm-dd hh-mm
	String currentTime = getTime();//获取当前日期
	String yearStr = currentTime.substring(0,4);//获取年子串
	String monthStr = currentTime.substring(5,7);//获取月子串
	String dayStr = currentTime.substring(8,10);//获取日子串
	String hourStr = currentTime.substring(11,13);//获取时子串
	String minuteStr = currentTime.substring(14);//获取分子串
	int year = Integer.parseInt(yearStr);//转化为年份
	int month = Integer.parseInt(monthStr);//转化为月份
	int day = Integer.parseInt(dayStr);//转化为号数
	int hour = Integer.parseInt(hourStr);//转化为小时数
	int minute = Integer.parseInt(minuteStr);//转化为分钟数
	String startTime = null;//用以存储航班的起飞时间
	String departureDate = null;//用以存储航班的起飞日期
	int departureDate_Year;//用以存储航班起飞日期的年份
	int departureDate_Month;//用以存储航班起飞日期的月份
	int departureDate_Day;//用以存储航班起飞日期的号数
	int startTime_hour;//用以存储航班起飞时间的小时数
	int startTime_minute;//用以存储航班起飞时间的分钟数
	int passTime_Current;//用以存储当前时间数
	int passTime_Flight;//用以存储航班事件数
	for(int index = 0; index < Data.flightList.size(); index ++)
	{
		//对于已发布的航班，可以修改有效状态
		if(Data.flightList.get(index).getStatus() != FlightStatus.UNPUBLISHED &
				Data.flightList.get(index).getStatus() != FlightStatus.TERMINATE)
		{
			//如果当前乘客数小于容量数，设置航班状态为可用
			if(Data.flightList.get(index).getCurrentPassengers() < Data.flightList.get(index).getSeatCapacity())
				Data.flightList.get(index).setFlightAVAILABLE();
			//如果当前乘客数等于容量数，设置航班状态为已满
			if(Data.flightList.get(index).getCurrentPassengers() == Data.flightList.get(index).getSeatCapacity())
				Data.flightList.get(index).setFlightFULL();
		}
		//获取航班起飞日期的子项年份、月份、号数
		departureDate = Data.flightList.get(index).getDepartureDate();
		departureDate_Year = Integer.parseInt(departureDate.substring(0,4));
		departureDate_Month = Integer.parseInt(departureDate.substring(5,7));
		departureDate_Day = Integer.parseInt(departureDate.substring(8,10));
		//获取航班起飞时间的子项小时数、分钟数
		startTime = Data.flightList.get(index).getStartTime();
		startTime_hour = Integer.parseInt(startTime.substring(0,2));
		startTime_minute = Integer.parseInt(startTime.substring(3,5));
		//如果航班为当日航班且当前时间为起飞时间前两小时之内，设置航班状态为终止
		//当前号数与起飞日期同一天
		if(year == departureDate_Year & month == departureDate_Month & day == departureDate_Day)
		{
			passTime_Current = hour*60 + minute;
			passTime_Flight = startTime_hour*60 + startTime_minute;
			if(passTime_Flight - passTime_Current <= 120)
			{
				Data.flightList.get(index).setFlightTERMINATE();
			}
		}
		//当前号数为起飞日期的前一天
		if(year == departureDate_Year & month == departureDate_Month & day == departureDate_Day - 1)
		{
			passTime_Current = day*24*60 + hour*60 + minute;
			passTime_Flight = departureDate_Day*24*60 + startTime_hour*60 + startTime_minute;
			if(passTime_Flight - passTime_Current <= 120)
			{
				Data.flightList.get(index).setFlightTERMINATE();
			}
		}
		//当前号数比起飞日期晚
		if(year >= departureDate_Year & month >= departureDate_Month & day > departureDate_Day)
		{
			Data.flightList.get(index).setFlightTERMINATE();
		}
		
	}
}

//用户管理
static void userManagement()
{
	starSeparation(120);	
	System.out.println("[User Management]");
	starSeparation(120);	
	System.out.println("1:Add admin");
	System.out.println("2:Modify information");
	System.out.println("3:Privious menu");
	System.out.print("Enter the index:");
	String indexStr = input.nextLine();
	if (isPureNumber(indexStr)) {
		int index = Integer.parseInt(indexStr);
		switch (index) {
		default:
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			userManagement();
		case 1:
			addAdmin();
			userManagement();
		case 2:
			modifyAdminInf();
			userManagement();
		case 3:
			adminMenu();
		}
	} else {
		System.out.println("Error: Please check your input!");
		System.out.print("(press Enter to continue)");
		input.nextLine();
		userManagement();
	}	
}

//添加管理员
static void addAdmin()
{
	starSeparation(120);
	System.out.println("[Add a new admin]");
	Admin newAdmin = new Admin();
	System.out.print("Set username:");
	String newUserName = input.nextLine();
	newAdmin.setUserName(newUserName);
	for(int index = 0; index < Data.adminList.size(); index ++)
	{
		flg = false;//重置判据
		if(newUserName.compareTo(Data.adminList.get(index).getUserName()) != 0)
			flg = true;
	}
	if(!flg)
	{
		System.out.print("User name has been exist!(Press Enter to continue)");
		input.nextLine();
		addAdmin();
	}
	else
	{
		System.out.print("Set password:");
		String newPassword = input.nextLine();
		newAdmin.setPassword(newPassword);
		Data.adminList.add(newAdmin);
		System.out.print("Success!(Press Enter to continue)");
		input.nextLine();
	}
}
	
//更改管理员信息
static void modifyAdminInf()
{
	starSeparation(120);	
	System.out.printf("Admin Index:%d\n", Data.currentAdminIndex);
	System.out.printf("Username:%s\n", Data.adminList.get(Data.currentAdminIndex).getUserName());
	System.out.printf("Password:%s\n", Data.adminList.get(Data.currentAdminIndex).getPassword());
	starSeparation(120);	
	System.out.println("Which you want to change:");
	System.out.println("1:User name");
	System.out.println("2:Password");
	System.out.println("3:Cancel");
	System.out.print("Enter the index:");
	String indexStr = input.nextLine();
	if (isPureNumber(indexStr)) {
		int index = Integer.parseInt(indexStr);
		switch (index) {
		default:
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			modifyAdminInf();
		case 1:
			System.out.print("new username:");
			String newUserName = input.nextLine();
			Data.adminList.get(Data.currentAdminIndex).setUserName(newUserName);
			System.out.print("Success!(Press Enter to continue)");
			input.nextLine();
			break;
		case 2:
			do
			{
				flg = false;//重置判据
				System.out.print("(If you want back to privous menu, enter 'back')");
				System.out.print("New password:");
				String newPassword1 = input.nextLine();
				if(newPassword1.toLowerCase().compareTo("back") == 0)
					break;
				System.out.print("Enter password twice:");
				String newPassword2 = input.nextLine();
				if(newPassword2.toLowerCase().compareTo("back") == 0)
					break;
				if(newPassword1.compareTo(newPassword2) == 0)
				{
					flg = true;
					Data.adminList.get(Data.currentAdminIndex).setPassword(newPassword1);
					System.out.print("Success!(Press Enter to continue)");
					input.nextLine();
				}
				else
				{
					System.out.println("Error: Please enter the same password!");
				}
			}while(!flg);
			break;
		case 3:
			adminMenu();
			break;
		}
	}
	else
	{
		System.out.println("Error: Please check your input!");
		System.out.print("(press Enter to continue)");
		input.nextLine();
		modifyAdminInf();
	}
}

//超级查询
static void superQuery()
{
	//查询某航班所有的信息
	//查询订单
	//普通查询
	updateFlight();
	starSeparation(120);
	System.out.println("[Super　Query]");
	starSeparation(120);
	System.out.println("Function list");
	System.out.println("1: Show all information about a flight.");
	System.out.println("2: show all information about a order.");
	System.out.println("3: list all flight by start city.");
	System.out.println("4: list all flight by arrival city.");
	System.out.println("5: list all flight by departure date.");
	System.out.println("6: Back to privious menu.");
	System.out.print("Enter the index:");
	String indexStr = input.nextLine();
	if (isPureNumber(indexStr)) {
		int index = Integer.parseInt(indexStr);
		switch (index) {
		default:
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			superQuery();
		case 1:
			viewFlight();
			superQuery();
		case 2:
			viewOrder();
			superQuery();
		case 3:
			flightQuery_Admin(STARTCITY);// 按起飞城市查询
			System.out.print("Success!(Press Enter to continue)");
			input.nextLine();
			superQuery();
		case 4:
			flightQuery_Admin(ARRIVALCITY);// 按到达城市查询
			System.out.print("Success!(Press Enter to continue)");
			input.nextLine();
			superQuery();
		case 5:
			flightQuery_Admin(DEPARTUREDATE);// 按起飞日期查询
			System.out.print("Success!(Press Enter to continue)");
			input.nextLine();
			superQuery();
		case 6:
			adminMenu();
		}
	} else {
		System.out.println("Error: Please check your input!");
		System.out.print("(press Enter to continue)");
		input.nextLine();
		queryMenu();
	}
}

//管理员：查看订单
static void viewOrder()
{
	updateFlight();
	System.out.println("All order show as follows:");
	for (int index = 0; index < Data.orderList.size(); index++) // 显示所有订单的信息
	{
		Data.orderList.get(index).showOrder_Brief(Data.orderList.get(index));
	}
	int orderIndex = -1;
	do
	{
		flg = false;
		System.out.println("The order index of which you want to view detail:");
		System.out.print("(Enter 'back' back to privious menu):");
		String orderIndexStr = input.nextLine();
		if(orderIndexStr.toLowerCase().compareTo("back") == 0)
			superQuery();
		if(isPureNumber(orderIndexStr))
		{
			orderIndex = Integer.parseInt(orderIndexStr);
			flg = true;
			break;
		}
		else
		{
			System.out.println("Eror! Integer only.(Press Enter to continue)");
			input.nextLine();
		}
	}while(!flg);
	if (orderIndex == -1) {
		superQuery();
	} 
	else if (orderIndex >= 0 & orderIndex < Data.orderList.size())//所输入的订单号合法 
	{
		Data.orderList.get(orderIndex).showOrder_Detail(Data.orderList.get(orderIndex));
		System.out.print("Press Enter to continue.");
		input.nextLine();
	}
	else
	{
		System.out.print("Eror! Index overflow.(Press Enter to continue)");
		input.nextLine();
	}
}

//管理员：查看航班详细信息
static void viewFlight()
{
	updateFlight();
	System.out.println("All fight show as follows:");
	for (int index = 0; index < Data.flightList.size(); index++) // 显示所有航班的信息
	{
		showFlight_Admin(Data.flightList.get(index));
	}
	int flightIndex = -1;
	do
	{
		flg = false;
		System.out.println("Which flight's information you want to extend?");
		System.out.print("(Enter 'back' to cancel) Index:");
		String flightIndexStr = input.nextLine();
		if(flightIndexStr.toLowerCase().compareTo("back") == 0)
			superQuery();
		if(isPureNumber(flightIndexStr))
		{
			flightIndex = Integer.parseInt(flightIndexStr);
			flg = true;
			break;
		}
		else
		{
			System.out.println("Eror! Integer only.(Press Enter to continue)");
			input.nextLine();
		}
	}while(!flg);
	//如果对应航班当前乘客数非零
	if(Data.flightList.get(flightIndex).getCurrentPassengers() != 0)
	{
		for(int index = 0; index < Data.flightList.get(flightIndex).getCurrentPassengers(); index++)
		{
			Data.flightList.get(flightIndex).getPassenger(index)
			.getOrder(Data.flightList.get(flightIndex)).showOrder_Detail
			(Data.flightList.get(flightIndex).getPassenger(index).getOrder(Data.flightList.get(flightIndex)));
		}
		System.out.print("Success!(Press Enter to continue)");
		input.nextLine();
	}
	else//对应航班乘客数为零
	{
		System.out.print("No passenger.(Press Enter to countinue)");
		input.nextLine();
	}

}

//界面：修改航班信息
static void modifyFlight() {
	updateFlight();
	Flight aimFlight;
	starSeparation(120);
	System.out.println("All fight show as follows:");
	for (int index = 0; index < Data.flightList.size(); index++) // 显示所有航班的信息
	{
		showFlight_Admin(Data.flightList.get(index));
	}
	int aimIndex = -1;
	String aimIndexStr;
	do
	{
		flg = false;
		System.out.println("Plese enter the Index of flight you want to modify:");
		System.out.print("(Enter 'back' back to privious menu)");
		aimIndexStr = input.nextLine();
		if(aimIndexStr.toLowerCase().compareTo("back") == 0)
		{
			adminMenu();
		}
		if(isPureNumber(aimIndexStr))
		{
			aimIndex = Integer.parseInt(aimIndexStr);
			flg = true;
		}
		else
		{
			System.out.println("Error! Integer only.(Presss enter to continue)");
			input.nextLine();
		}
	}while(!flg);
	aimFlight = Data.flightList.get(aimIndex);
	if (aimFlight.getStatus() == FlightStatus.UNPUBLISHED) {
		modifyUnpublishedFlight(aimFlight);
	} else if (aimFlight.getStatus() == FlightStatus.AVAILABLE | aimFlight.getStatus() == FlightStatus.FULL) {
		modifyPublishedFlight(aimFlight);
	} else// 输入航班不可修改，返回到修改航班
	{
		System.out.print("Error! Flight can not be modify.(Press enter to continue)");
		input.nextLine();
		modifyFlight();
	}
}

//界面：用户注册
//输入不合法则重复当前阶段输入，注册成功则返回主菜单
static void signUp()
{
	starSeparation(120);
	System.out.println("[Acount Creation]");
	starSeparation(120);
	System.out.println("If you want back to primary menu, enter 'primary menu'.");
	System.out.print("Plese enter your real name:");// 真实姓名
	String tempRealName = input.nextLine();
	if(tempRealName.toLowerCase().compareTo("primary menu") == 0 )
	{
		primaryMenu();
	}
	String tempIdentityID1 = new String();//第一次输入的身份证
	String tempIdentityID2 = new String();//第二次输入的身份证
	String tempPassword1 = new String();//第一次输入的密码
	String tempPassword2 = new String();//第二次输入的密码

	do
	{
		do
		{
			System.out.print("Plese enter your identity ID as your account:");// 身份证作账户名
			tempIdentityID1 = input.nextLine();
			//当输入primary menu时结束管理员登陆，返回到初始菜单
			if(tempIdentityID1.compareTo("primary menu") == 0)
			{
				primaryMenu();
			}
			if(!isCorrectID(tempIdentityID1))
			{
				System.out.println("Please enter correct ID number!");
			}
			//检查用户名是否有效
			for(int index = 0; index < Data.adminList.size(); index++)
			{
				if(tempIdentityID1.compareTo(Data.adminList.get(index).getUserName()) == 0)
				{
					System.out.println("Account already exists, please enter a new account.");
					isAvailable = false;
				}
			}
		}while(!isCorrectID(tempIdentityID1) | !isAvailable);//身份证格式不对或账户名已存在时进行循环
		System.out.print("Plese enter your identity ID agian:");// 身份证二次确认
		tempIdentityID2 = input.nextLine();
		if(tempIdentityID2.compareTo("primary menu") == 0)
		{
			primaryMenu();
		}
		if(tempIdentityID1.compareTo(tempIdentityID2) != 0)
			System.out.println("Error! Please input the same ID number.");
	}while(tempIdentityID1.compareTo(tempIdentityID2) != 0);
	// 创建密码
	do {
		// 密码二次确认
		System.out.print("Plese create a password for your account:");
		tempPassword1 = input.nextLine();
		if(tempPassword1.compareTo("primary menu") == 0)
		{
			primaryMenu();
		}
		System.out.print("Plese enter the password again:");
		tempPassword2 = input.nextLine();
		if(tempPassword2.compareTo("primary menu") == 0)
		{
			primaryMenu();
		}
		if (tempPassword1.compareTo(tempPassword2) != 0) {
			System.out.println("Error! Plese enter the same password.");
		} else {
			Data.signUpPassengers++;// 已注册用户数+1
			// 将当前注册乘客录入已注册乘客列表
			Passenger newPassenger = new Passenger();
			newPassenger.setUniqueID(Data.signUpPassengers - 1);
			newPassenger.setRealName(tempRealName);
			newPassenger.setIdNumber(tempIdentityID1);
			newPassenger.setPassword(tempPassword1);
			Data.passengerList.add(newPassenger);
			System.out.print("Success!(Press Enter to continue)");
			input.nextLine();
			primaryMenu();//返回初始菜单
		}
	} while (tempPassword1.compareTo(tempPassword2) != 0);
}

//检查身份证号是否合法
//18位数字或17位数字加末位X为合法身份证号,合法返回真，非法返回假
static boolean isCorrectID(String identityID) {
	boolean isCorrect = false;
	//检查是否十八位
	if (identityID.length() != 18) {
		return false;
	} 
	//检查各个位是否合法
	else
	{
		for(int index = 0; index < 18; index++)
		{
			for(int range = 1; range <=9; range++)
			{
				if(identityID.substring(index, index+1).compareTo("" + range) == 0)
					isCorrect = true;
			}
			//检查第十八位是否为X
			if(identityID.substring(17,18).toLowerCase().compareTo("x") == 0)
				isCorrect = true;
		}
		if(!isCorrect)
			return false;
		else
			return true;
	}
}

//界面：登陆
//根据关键词来进入管理员登陆界面或用户登陆界面
static void login(int keyword)
{
	switch(keyword)
	{
	default:
		break;
	case ADMIN:
		starSeparation(120);
		System.out.println("[Admin Login]");
		starSeparation(120);
		if(Data.isAdminLogin)//管理员未注销则直接进入管理菜单
		{
			break;
		}
		do
		{
			System.out.println("If you want back to primary menu, enter 'primary menu'.");
			System.out.print("Account:");
			String tempAccount = input.nextLine();
			//当输入primary menu时结束管理员登陆，返回到初始菜单
			if(tempAccount.toLowerCase().compareTo("primary menu") == 0 )
			{
				primaryMenu();
			}
			System.out.print("Password:");
			String tempPassword = input.nextLine();
			//当输入primary menu时结束管理员登陆，返回到初始菜单
			if(tempPassword.toLowerCase().compareTo("primary menu") == 0)
			{
				primaryMenu();
			}
			for(int index = 0; index < Data.adminList.size(); index ++)
			{
				//重置匹配判据
				isMatch = false;
				if(tempAccount.compareTo(Data.adminList.get(index).getUserName()) == 0)
				{
					if(tempPassword.compareTo(Data.adminList.get(index).getPassword()) == 0)
					{
						isMatch = true;
						Data.isAdminLogin = true;
						Data.currentAdminIndex = index;
						System.out.println("Success!");
						System.out.print("Admin Login.(Press enter to continue)");
						input.nextLine();
						break;
					}
				}
			}
			if(!isMatch)
			{
				System.out.println("Error! Please check your account or password.");
			}
		}while(!isMatch);//如果无匹配则进行循环
		break;
	case PASSENGER:
		starSeparation(120);
		System.out.println("[Passenger Login]");
		if(Data.isPassengerLogin)//乘客未注销则直接进入乘客菜单
		{
			break;
		}
		do
		{
			starSeparation(120);
			System.out.println("If you want back to primary menu, enter 'primary menu'.");
			System.out.print("ID number:");
			String tempAccount = input.nextLine();
			//当输入primary menu时结束管理员登陆，返回到初始菜单
			if(tempAccount.toLowerCase().compareTo("primary menu") == 0)
			{
				primaryMenu();
			}
			System.out.print("Password:");
			String tempPassword = input.nextLine();
			//当输入primary menu时结束管理员登陆，返回到初始菜单
			if(tempPassword.toLowerCase().compareTo("primary menu") == 0)
			{
				primaryMenu();
			}
			if(Data.passengerList.isEmpty())
			{
				System.out.println("Error! Please check your account or password.");
				login(PASSENGER);
			}
			for(int index = 0; index < Data.passengerList.size(); index ++)
			{
				//重置匹配判据
				isMatch = false;
				if(tempAccount.compareTo(Data.passengerList.get(index).getIdNumber()) == 0)
				{
					if(tempPassword.compareTo(Data.passengerList.get(index).getPassword()) == 0)
					{
						isMatch = true;
						Data.isPassengerLogin = true;
						Data.currentPassengerIndex = index;
						System.out.println("Success!");
						System.out.print("Passenger Login.(Press enter to continue)");
						input.nextLine();
						break;
					}
				}
			}
			if(!isMatch)
			{
				System.out.println("Error! Please check your account or password.");
			}
		}while(!isMatch);//如果无匹配则进行循环
		break;
	}
}

//菜单：管理员菜单
static void adminMenu()
{
	starSeparation(120);
	System.out.println("[Admin Menu]");
	starSeparation(120);
	System.out.println("Function List:");
	System.out.println("1:Create Filght.");
	System.out.println("2:Publish Filght.");
	System.out.println("3:Modify Filght.");
	System.out.println("4:Delete Filght.");
	System.out.println("5:Update Filght.");
	System.out.println("6:Suery Query.");
	System.out.println("7:User Management.");
	System.out.println("8:Logout.");
	System.out.print("Enter the index:");
	String indexStr = input.nextLine();
	if (isPureNumber(indexStr)) {
		int index = Integer.parseInt(indexStr);
		switch (index) {
		default:
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			adminMenu();
		case 1:
			createFlight();
			adminMenu();
		case 2:
			updateFlight();
			publishFlight();
			adminMenu(); 
		case 3:
			updateFlight();
			modifyFlight();
			adminMenu();
		case 4:
			updateFlight();
			deleteFlight();
			adminMenu();
		case 5:
			updateFlight();
			System.out.print("Success!(Press enter to continue)");
			input.nextLine();
			adminMenu();
		case 6:
			updateFlight();
			superQuery();
			adminMenu();
		case 7:
			userManagement();
			adminMenu();
		case 8:
			Data.isAdminLogin = false;
			System.out.print("Success!(Press enter to continue.)");
			input.nextLine();
			primaryMenu();
		}
	} else {
		System.out.println("Error: Please check your input!");
		System.out.print("(press Enter to continue)");
		input.nextLine();
		adminMenu();
	}
}

//菜单：乘客菜单
static void passengerMenu()
{
	starSeparation(120);
	System.out.println("[Passenger Menu]");
	starSeparation(120);
	System.out.println("What do you want to do ?");
	System.out.println("1: Flight Query.");
	System.out.println("2: Flight Reservation.");
	System.out.println("3: Flight Unsubscribtion.");
	System.out.println("4: My orders.");
	System.out.println("5: Logout.");
	System.out.print("Enter the index:");
	String indexStr = input.nextLine();
	if (isPureNumber(indexStr)) {
		int index = Integer.parseInt(indexStr);
		switch (index) {
		default:
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			passengerMenu();
		case 1:
			updateFlight();
			queryMenu_Passenger();
		case 2:
			updateFlight();
			flightReservation();
			passengerMenu();
		case 3:
			updateFlight();
			flightUnsubscribtion();
			passengerMenu();
		case 4:
			showAllOrder();
			if(Data.passengerList.get(Data.currentPassengerIndex).getOrderListSize() != 0)
				paidOrder();
			passengerMenu();
		case 5:
			Data.isPassengerLogin = false;
			System.out.print("Success!(Press enter to continue.)");
			input.nextLine();
			primaryMenu();
		}
	} else {
		System.out.println("Error: Please check your input!");
		System.out.print("(press Enter to continue)");
		input.nextLine();
		passengerMenu();
	}
}

//显示航班信息管理员版
static void showFlight_Admin(Flight flight)
{
	starSeparation(60);
	System.out.printf("%-20s%s%s\n", "||| Flight Index", ":", Data.flightList.indexOf(flight));
	System.out.printf("%-20s%s%s\n", "||| Flight ID" , ":", flight.getFlightID());
	System.out.printf("%-20s%s%s\n", "||| Start City" , ":", flight.getStartCity());
	System.out.printf("%-20s%s%s\n", "||| Arrival City" , ":", flight.getArrivalCity());
	System.out.printf("%-20s%s%s\n", "||| Start Time" , ":", flight.getStartTime());
	System.out.printf("%-20s%s%s\n", "||| Arrival Time" , ":", flight.getArrivalTime());
	System.out.printf("%-20s%s%s\n", "||| Departure Day" , ":", flight.getDepartureDate());
	System.out.printf("%-20s%s%s\n", "||| Seat Capacity" , ":", flight.getSeatCapacity());
	System.out.printf("%-20s%s%s\n", "||| Price" , ":", flight.getPrice());
	System.out.printf("%-20s%s%s\n", "||| Flight Status" , ":", flight.getStatus());
	starSeparation(60);
}

//显示航班信息乘客版
static void showFlight_Passenger(Flight flight)
{
	starSeparation(60);
	System.out.printf("%-20s%s%s\n", "||| Flight Index", ":", Data.flightList.indexOf(flight));
	System.out.printf("%-20s%s%s\n", "||| Flight ID" , ":", flight.getFlightID());
	System.out.printf("%-20s%s%s\n", "||| Start City" , ":", flight.getStartCity());
	System.out.printf("%-20s%s%s\n", "||| Arrival City" , ":", flight.getArrivalCity());
	System.out.printf("%-20s%s%s\n", "||| Start Time" , ":", flight.getStartTime());
	System.out.printf("%-20s%s%s\n", "||| Arrival Time" , ":", flight.getArrivalTime());
	System.out.printf("%-20s%s%s\n", "||| Departure Day" , ":", flight.getDepartureDate());
	System.out.printf("%-20s%s%s\n", "||| Price" , ":", flight.getPrice());
	starSeparation(60);
	}
	
}//Function Class

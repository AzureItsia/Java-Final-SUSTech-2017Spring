package FlightTicketReservationSystem;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class Function {

// ���������
static Scanner input = new Scanner(System.in);

// ��ѯ����ؼ���
final static int STARTCITY = 1;
final static int ARRIVALCITY = 2;
final static int DEPARTUREDATE = 3;

// ƥ���о�
static boolean isMatch = false;
//�ж��о�
static boolean flg = false;
//�˻��Ƿ����
static boolean isAvailable = true;

//��½�ؼ���
final static int ADMIN =1;
final static int PASSENGER = 2;

// ���ηָ��ߣ�
// ���ָ��������*������
static void starSeparation(int starNumber) {
	for (int counter = 1; counter <= starNumber; counter++) {
		System.out.print("*");
		if (counter == starNumber)
			System.out.println();
	}
}

//�Ƿ�����
//�����뷵�ؼ٣������ַ����棬�������ؼ�
static boolean isPureNumber(String inputStr)
{
	boolean isPure = false;//�����Ƿ������оݣ�ȱʡΪ��
	boolean isNumber = false;//���嵱ǰλ���Ƿ�Ϊ�����оݣ�ȱʡΪ��
	//�ӵ�һλ�����һλ���ж�
	for(int counter = 0; counter < inputStr.length(); counter ++)
	{
		isNumber = false;//���õ�ǰλ���Ƿ�Ϊ�����о�Ϊ��
		//�Ƚϵ�ǰλ���Ƿ���Ϊ����
		for(int number = 0; number <= 9; number ++)
		{
			if(inputStr.substring(counter, counter+1).compareTo("" + number) == 0)
			{
				isNumber = true;//���õ�ǰλ��Ϊ�����о�Ϊ��
			}
		}
		if(!isNumber)//�����ǰλ���������֣�����ѭ�������������һ��ѭ������һλ�����ж�
			break;
		if(counter == inputStr.length() - 1 & isNumber)//��������һλ��������
			isPure = true;//�����Ƿ��Ǵ������о�Ϊ��
	}
	return isPure;
}

// �˵���������
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

// ��ʼ��
static void initialize() {

	initializeAdmin();//��ʼ������Ա
	initializeCity();//��ʼ������
	initializeFlight();//��ʼ������
	initializePassenger();//��ʼ���˿�
}

//��ʼ������
static void initializeCity()
{
	Data.city[0]="Shenzhen";
	Data.city[1]="Beijing";
	Data.city[2]="Shanghai";
}

//����Ա��ʼ��
static void initializeAdmin()
{
	// ����ȱʡ����Ա
	Admin defaultAdmin = new Admin();
	defaultAdmin.setUserName("admin");
	defaultAdmin.setPassword("***");
	Data.adminList.add(defaultAdmin);
}

//�˿ͳ�ʼ��
static void initializePassenger()
{
	// ����ȱʡ�˿�
	Passenger defaultPassenger = new Passenger();
	defaultPassenger.setUniqueID(0);//�˿�ע�����
	defaultPassenger.setRealName("default");;
	defaultPassenger.setIdNumber("000000000000000000");;//���֤��
	defaultPassenger.setPassword("psw");;
	Data.passengerList.add(defaultPassenger);
}	

//�����ʼ��
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

//��ȡ����
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
		if(day > numberOfDays(year, month))//����������ڵ�ǰ�·�����
		{
			day -= numberOfDays(year, month);//������ȥ��ǰ�·�����
			month += 1;//�·�����1
			if(month > 12)//����·�������12
			{year += 1;//�������1
				month -= 12;//�·�����12
			}
			
		}
	}while(day > numberOfDays(year, month));//����������ڵ����·ݵ������������ѭ��
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

//��ȡʱ��
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

//��ȡ��ϸʱ��
static String getTime()
{
	return getDate(0) + " " + getTime_HourAndMinute();
}

//�Ƿ�������
static boolean isLeapYear(int year)
{
	if (year % 400 == 0 || ((year % 4 == 0) & (year % 100 != 0)))
		return true;
	else return false;
}

//��Ӧ�·ݵ�����
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
	
//��ʼ��һ�����ಢ���뺽���б�
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

// �˵�����ѯ����
static void queryMenu() {
	starSeparation(120);
	System.out.println("[Flight��Query]");
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
			flightQuery_Passenger(STARTCITY);// ����ɳ��в�ѯ
			if(isMatch)
				isReservation();
			queryMenu();
		case 2:
			flightQuery_Passenger(ARRIVALCITY);// ��������в�ѯ
			if(isMatch)
				isReservation();
			queryMenu();
		case 3:
			flightQuery_Passenger(DEPARTUREDATE);// ��������ڲ�ѯ
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

// �Ӳ˵����˿Ͳ�ѯ����
static void queryMenu_Passenger() {
	starSeparation(120);
	System.out.println("[Flight��Query]");
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
			queryMenu_Passenger();// ����ɳ��в�ѯ
			break;
		case 2:
			flightQuery_Passenger(ARRIVALCITY);
			if(isMatch)
				isReservation();
			queryMenu_Passenger();// ��������в�ѯ
			break;
		case 3:
			flightQuery_Passenger(DEPARTUREDATE);
			if(isMatch)
				isReservation();
			queryMenu_Passenger();// ��������ڲ�ѯ
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

//�Ӳ˵�������Ա��ѯ����
static void queryMenu_Admin() {
	starSeparation(120);
	System.out.println("[Flight��Query]");
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
			queryMenu_Admin();// ����ɳ��в�ѯ
			break;
		case 2:
			flightQuery_Admin(ARRIVALCITY);
			queryMenu_Passenger();// ��������в�ѯ
			break;
		case 3:
			flightQuery_Admin(DEPARTUREDATE);
			queryMenu_Admin();// ��������ڲ�ѯ
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

// �μ��˵���ͨ���ؼ��ʲ�ѯ����
// ���ݹؼ��ʡ��û������������Ƿ���ƥ��ĺ��࣬ƥ������ʾ������ż���Ϣ����һƥ���򱨴�
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

//����Ա��ͨ��ѯ
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

// ��ȡ�ǿգ��س�������
static String getInputStr(String inputStr) {
	do {
		inputStr = input.nextLine();
		if (inputStr.isEmpty())
			System.out.println("Error! Please check your input!");
	} while (inputStr.isEmpty());
	return inputStr;
}

// ���ݹؼ���ƥ�亽��
// keywordIndexΪ1��2��3���δ�������ɳ��С�Ŀ�ĳ��С��������Ϊ�ؼ��ʽ��в�ѯ
// ����û�������ƥ�䣬���ӡƥ�亽����š�ƥ�亽����Ϣ�������һƥ�䣬�򱨴�
static void matchFlight_Admin(int keywordIndex, String keyword) {
	updateFlight();
	isMatch = false;// ����ƥ���о�
	switch (keywordIndex)// ѡ���պ��ֹؼ��ʲ�ѯ
	{
	default:
		break;
	case STARTCITY:// ������ɳ��в�ѯ����
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
	case ARRIVALCITY:// ���յ�����в�ѯ����
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
	case DEPARTUREDATE:// ����������ڲ�ѯ����
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


// ���ݹؼ���ƥ�亽��
// keywordIndexΪ1��2��3���δ�������ɳ��С�Ŀ�ĳ��С��������Ϊ�ؼ��ʽ��в�ѯ
// ����û�������ƥ�䣬���ӡƥ�亽����š�ƥ�亽����Ϣ�������һƥ�䣬�򱨴�
static void matchFlight_Passenger(int keywordIndex, String keyword) {
	updateFlight();
	isMatch = false;// ����ƥ���о�
	switch (keywordIndex)// ѡ���պ��ֹؼ��ʲ�ѯ
	{
	default:
		break;
	case STARTCITY:// ������ɳ��в�ѯ����
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
	case ARRIVALCITY:// ���յ�����в�ѯ����
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
	case DEPARTUREDATE:// ����������ڲ�ѯ����
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

//�Ƿ�Ԥ������
static void isReservation()
{
	System.out.print("Do you want to book flight?(Yes/No)");
	String tempInput = input.nextLine();
	if(tempInput.toLowerCase().compareTo("yes") == 0)
		flightReservation();
}

//Ԥ������
static void flightReservation()
{
	do
	{
		int flightIndex = -1;
		do
		{
		flg = false;//����Ԥ���о�
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
		if(flightIndex >= 0 & flightIndex < Data.flightList.size())//����������źϷ�
		{
			if(Data.flightList.get(flightIndex).getStatus() != FlightStatus.UNPUBLISHED
					& Data.flightList.get(flightIndex).getStatus() != FlightStatus.TERMINATE)//���Ŀ�꺽����Ч
			{
				showFlight_Passenger(Data.flightList.get(flightIndex));//��ʾĿ�꺽����Ϣ
				System.out.print("Is it the flight you want to book?(yes/no)");
				String tempInput = input.nextLine();
				if(tempInput.toLowerCase().compareTo("yes") == 0)//���ȷ��ҪԤ��
				{
					//������ǰ�˿Ͷ����б�
					for(int index = 0; index < Data.passengerList.get(Data.currentPassengerIndex)
							.getOrderListSize(); index ++)
					{
						//�����Ԥ�����ඩ���Ѵ��ڣ��򱨴�
						if(Data.flightList.get(flightIndex) == Data.passengerList.get(Data.currentPassengerIndex)
								.getOrder(index).getFlight() & Data.passengerList.get(Data.currentPassengerIndex)
								.getOrder(index).getStatus() != OrderStatus.CANCEL)
						{
							flg = true;//����ƥ���о�Ϊ��
							System.out.print("You already book this flight.(Press Enter to continue)");
							input.nextLine();
							break;
						}
					}
					if(flg)
						break;//���ƥ���о�Ϊ�棬���˳�ѭ��
					flg = false;//����ƥ���о�
					if(Data.flightList.get(flightIndex).getStatus() == FlightStatus.FULL)//���Ŀ�꺽������
					{
						System.out.print("Sorry! This flight was full.(Press Enter to continue)");
						input.nextLine();
					}
					else//���Ŀ�꺽��ɶ�
					{
						if(Data.isPassengerLogin)//����˿��ѵ�¼
						{
							Order newOrder = new Order();
							//������ʵ����
							newOrder.setRealName(Data.passengerList.get(Data.currentPassengerIndex).getRealName());
							//����Ψһ���
							newOrder.setUniqueID(Data.passengerList.get(Data.currentPassengerIndex).getUniqueID());
							//������λ��
							newOrder.setSeatNum(assginSeat(Data.flightList.get(flightIndex)));
							//���ö�Ӧ����
							newOrder.setFlight(Data.flightList.get(flightIndex));
							//��Ӧ����˿��б���Ӵ˳˿�
							newOrder.getFlight().addPassenger(newOrder.getPassenger());
							//��Ӧ����˿�������
							Data.flightList.get(flightIndex).updataCurrentPassengers();
							//���ô���ʱ��
							newOrder.setCreateTime(getTime());
							//ѯ���Ƿ�֧��
							System.out.print("Do you want to paid it now?(yes/no)");
							String isSure = input.nextLine();
							//����֧��
							if (isSure.toLowerCase().compareTo("yes") == 0) {
								newOrder.setOrderPAID();
								System.out.print("Success!(Press Enter to continue)");
								input.nextLine();
							}
							//�˿Ͷ����б���Ӵζ���
							Data.passengerList.get(Data.currentPassengerIndex).addOrder(newOrder);
							//�ܶ����б���Ӵ˶���
							Data.orderList.add(newOrder);
							System.out.print("Success!(Press enter to continue)");
							flg = true;
							input.nextLine();
							break;
						}
						else//����˿�δ��½
						{
							System.out.println("Sorry, you need to sign in first.");
							//�˿͵�½
							login(PASSENGER);
							Order newOrder = new Order();
							//������ʵ����
							newOrder.setRealName(Data.passengerList.get(Data.currentPassengerIndex).getRealName());
							//����Ψһ���
							newOrder.setUniqueID(Data.passengerList.get(Data.currentPassengerIndex).getUniqueID());
							//������λ��
							newOrder.setSeatNum(assginSeat(Data.flightList.get(flightIndex)));
							//���ö�Ӧ����
							newOrder.setFlight(Data.flightList.get(flightIndex));
							//��Ӧ����˿��б���Ӵ˳˿�
							newOrder.getFlight().addPassenger(newOrder.getPassenger());
							//��Ӧ����˿�������
							Data.flightList.get(flightIndex).updataCurrentPassengers();
							//���ô���ʱ��
							newOrder.setCreateTime(getTime());
							//�Ƿ�����֧��
							System.out.print("Do you want to paid it now?(yes/no)");
							String isSure = input.nextLine();
							//����֧��
							if (isSure.toLowerCase().compareTo("yes") == 0) {
								newOrder.setOrderPAID();
								System.out.print("Success!(Press Enter to continue)");
								input.nextLine();
							}
							//��Ӧ����˿��б���Ӵ˳˿�
							newOrder.getFlight().addPassenger(newOrder.getPassenger());
							//�˳˿Ͷ����б���Ӵ˶���
							Data.passengerList.get(Data.currentPassengerIndex).addOrder(newOrder);
							//�ܶ����б���Ӵζ���
							Data.orderList.add(newOrder);
							System.out.print("Success!(Press enter to continue)");
							flg = true;//Ԥ���о���Ϊ��
							input.nextLine();
							break;
						}
					}
				}
				else//����˿�ѡ��Ԥ��
				{
					flightReservation();
				}
			}
			else//Ŀ�꺽����Ч
			{
				System.out.print("Sorry!This flight is unavailable now.(Press Enter to continue)");
				input.nextLine();
				flg = true;//Ԥ���о���Ϊ��
			}
		}
		else//����˿������flight index�Ƿ�
		{
			System.out.println("Error! Please input the correct flight index number");
		}
	}while(!flg);//δԤ���ɹ������ѭ��
}

//�˶�����
static void flightUnsubscribtion()
{
	do
	{
		flg = false;//�����˶��о�
		//�����ǰ�˿͵Ķ����б�Ϊ��
		if(Data.passengerList.get(Data.currentPassengerIndex).getOrderListSize() == 0)
		{
			System.out.println("No order.(Press enter to continue)");
			input.nextLine();
			break;
		}
		//������ǰ�˿Ͷ����б�����ʾ������Ϣ
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
		//ѯ�����˶��Ķ������
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
		//�����źϷ�
		if(orderIndex >= 0 & orderIndex < Data.passengerList.get(Data.currentPassengerIndex).getOrderListSize())
		{
			//��ʾĿ�궩����Ϣ
			Data.passengerList.get(Data.currentPassengerIndex).showOrder
				(Data.passengerList.get(Data.currentPassengerIndex).getOrder(orderIndex));
			//ѯ���Ƿ����˶��ö���
			System.out.print("Is it the order you want to unsubscribe?(yes/no)");
			String tempInput = input.nextLine();
			//�˶�����
			if(tempInput.toLowerCase().compareTo("yes") == 0)
			{
				//�ӳ˿Ͷ����б������ô˶���״̬Ϊȡ��
				Data.passengerList.get(Data.currentPassengerIndex).getOrder(orderIndex).setOrderCANCEL();
				//�Ӷ�Ӧ����ĳ˿��б����Ƴ��˳˿�
				Data.passengerList.get(Data.currentPassengerIndex).getOrder(orderIndex)
					.getFlight().deletePassenger(Data.passengerList.get(Data.currentPassengerIndex));
				//��Ӧ����˿�������
				Data.passengerList.get(Data.currentPassengerIndex).getOrder(orderIndex)
					.getFlight().updataCurrentPassengers();	
				//�ͷŸó˿Ͷ�Ӧ����λ
				Data.passengerList.get(Data.currentAdminIndex).getOrder(orderIndex).getFlight().releaseSeat
					(Data.passengerList.get(Data.currentAdminIndex).getOrder(orderIndex).getSeatNum() - 1);
				flg = true;//�����˶��о�Ϊ��
				System.out.print("Success!(Press enter to continue)");
				input.nextLine();
			}
			else//����˿�ѡ���˶�
			{
				flightUnsubscribtion();
			}
		}
		else//����˿������order index�Ƿ�
		{
			System.out.println("Error! Please input the correct order index number");
			System.out.print("(Press enter to continue)");
			input.nextLine();
		}
	}while(!flg);//δԤ���ɹ������ѭ��
}

//��ʾ����
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

//֧������
static void paidOrder()
{
	System.out.print("Do you want to paid order?(yes/no)");
	String isSure = input.nextLine();
	if (isSure.toLowerCase().compareTo("yes") == 0) //֧������
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

//������λ
static int assginSeat(Flight flight)
{
	int seatNumber = -1;//ȱʡ��λ��Ϊ-1
	//������Ӧ�������λ�б�����һ��Ϊ�յ���λ���أ�����������Ϊռ��
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

//�����º���
static void createFlight() {

	starSeparation(120);
	System.out.println("[Create Flight]");
	starSeparation(120);
	Flight newFlight = new Flight();
	System.out.print("Flight ID:");
	newFlight.setFlightID(input.nextLine());//���ú����
	//�������ʱ��
	String startTime;
	do
	{
		flg = false;//���������о�
		System.out.print("Start time(hh:mm):");
		startTime = input.nextLine();
		if(startTime.length() != 5)//������ʱ�䳤�Ȳ��Ϸ�
		{
			System.out.print("Error! Check your input.(Press enter to continue)");
			input.nextLine();
		}
		//����������ʱ���ʽ���Ϸ�
		else if(startTime.substring(2, 3).compareTo(":") != 0 
				| !isPureNumber(startTime.substring(0,2)) | !isPureNumber(startTime.substring(4)))
		{
			System.out.print("Error! Check your format.(Press enter to continue)");
			input.nextLine();
		}
		//����������ʱ�����ֲ��Ϸ�
		else if(Integer.parseInt(startTime.substring(0,2)) < 0 | Integer.parseInt(startTime.substring(0,2)) > 24
				| Integer.parseInt(startTime.substring(4)) < 0 | Integer.parseInt(startTime.substring(4)) > 60)
		{
			System.out.print("Error! Check your input.(Press enter to continue)");
			input.nextLine();
		}
		//���������о���Ϊ��
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

//�������޸�δ�����������Ϣ
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
		case 1:// �޸����ʱ��
			System.out.print("New Start time:");
			flight.setStartTime(input.nextLine());
			break;
		case 2:// �޸ĵ���ʱ��
			System.out.print("New Arrival time:");
			flight.setArrivalTime(input.nextLine());
			break;
		case 3:// �޸���ɳ���
			System.out.print("New Start city:");
			flight.setStartCity(input.nextLine());
			break;
		case 4:// �޸�Ŀ�����
			System.out.print("New Arrival city:");
			flight.setArrivalCity(input.nextLine());
			break;
		case 5:// �޸��������
			System.out.print("New Departure Date:");
			flight.setDepartureDate(input.nextLine());
			break;
		case 6:// �޸ļ۸�
			System.out.print("New Price:");
			flight.setPrice(input.nextInt());
			input.nextLine();//����nextInt�Ļس���'\n'
			break;
		case 7:// �޸ĺ�������
			System.out.print("New Seat capacity:");
			flight.setSeatCapacity(input.nextInt());
			input.nextLine();//����nextInt�Ļس���'\n'
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

// �������޸��ѷ����������Ϣ
static void modifyPublishedFlight(Flight flight) {
		starSeparation(120);
		System.out.println("[Modify Flight]");
		System.out.println("1:Price");
		System.out.println("2:Seat capacity");
		System.out.print("Which information you want to adjust:");
		String indexStr = input.nextLine();
		if (isPureNumber(indexStr))//�������Ϸ�
		{
			int index = Integer.parseInt(indexStr);//ת��Ϊ����
			switch (index)//�ж�ѡ��
			{
			default:
				System.out.println("Error: Please check your input!");
				System.out.print("(press Enter to continue)");
				input.nextLine();
				modifyPublishedFlight(flight);
				break;
			case 1:// �޸ļ۸�
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
			case 2:// �޸ĺ�������	
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
		else// ���벻�Ϸ� 
		{
			System.out.println("Error: Please check your input!");
			System.out.print("(press Enter to continue)");
			input.nextLine();
			modifyPublishedFlight(flight);
		}	
}

//ɾ������
static void deleteFlight() {
	updateFlight();
	System.out.println("All fight show as follows:");
	for (int index = 0; index < Data.flightList.size(); index++) // ��ʾ����δ��������ֹ�ĺ������Ϣ
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
				Data.flightList.remove(flightIndex);// �Ӻ����б���Ƴ��ú���
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

//��������
static void publishFlight() {
	updateFlight();
	starSeparation(120);
	// ��ʾ����δ��������
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
				Data.flightList.get(flightIndex).setFlightAVAILABLE();;// �޸�Ŀ�꺽��״̬Ϊ�ѷ���
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

//���º���
static void updateFlight()
{
	//���ڸ�ʽ��yyyy-mm-dd hh-mm
	String currentTime = getTime();//��ȡ��ǰ����
	String yearStr = currentTime.substring(0,4);//��ȡ���Ӵ�
	String monthStr = currentTime.substring(5,7);//��ȡ���Ӵ�
	String dayStr = currentTime.substring(8,10);//��ȡ���Ӵ�
	String hourStr = currentTime.substring(11,13);//��ȡʱ�Ӵ�
	String minuteStr = currentTime.substring(14);//��ȡ���Ӵ�
	int year = Integer.parseInt(yearStr);//ת��Ϊ���
	int month = Integer.parseInt(monthStr);//ת��Ϊ�·�
	int day = Integer.parseInt(dayStr);//ת��Ϊ����
	int hour = Integer.parseInt(hourStr);//ת��ΪСʱ��
	int minute = Integer.parseInt(minuteStr);//ת��Ϊ������
	String startTime = null;//���Դ洢��������ʱ��
	String departureDate = null;//���Դ洢������������
	int departureDate_Year;//���Դ洢����������ڵ����
	int departureDate_Month;//���Դ洢����������ڵ��·�
	int departureDate_Day;//���Դ洢����������ڵĺ���
	int startTime_hour;//���Դ洢�������ʱ���Сʱ��
	int startTime_minute;//���Դ洢�������ʱ��ķ�����
	int passTime_Current;//���Դ洢��ǰʱ����
	int passTime_Flight;//���Դ洢�����¼���
	for(int index = 0; index < Data.flightList.size(); index ++)
	{
		//�����ѷ����ĺ��࣬�����޸���Ч״̬
		if(Data.flightList.get(index).getStatus() != FlightStatus.UNPUBLISHED &
				Data.flightList.get(index).getStatus() != FlightStatus.TERMINATE)
		{
			//�����ǰ�˿���С�������������ú���״̬Ϊ����
			if(Data.flightList.get(index).getCurrentPassengers() < Data.flightList.get(index).getSeatCapacity())
				Data.flightList.get(index).setFlightAVAILABLE();
			//�����ǰ�˿������������������ú���״̬Ϊ����
			if(Data.flightList.get(index).getCurrentPassengers() == Data.flightList.get(index).getSeatCapacity())
				Data.flightList.get(index).setFlightFULL();
		}
		//��ȡ����������ڵ�������ݡ��·ݡ�����
		departureDate = Data.flightList.get(index).getDepartureDate();
		departureDate_Year = Integer.parseInt(departureDate.substring(0,4));
		departureDate_Month = Integer.parseInt(departureDate.substring(5,7));
		departureDate_Day = Integer.parseInt(departureDate.substring(8,10));
		//��ȡ�������ʱ�������Сʱ����������
		startTime = Data.flightList.get(index).getStartTime();
		startTime_hour = Integer.parseInt(startTime.substring(0,2));
		startTime_minute = Integer.parseInt(startTime.substring(3,5));
		//�������Ϊ���պ����ҵ�ǰʱ��Ϊ���ʱ��ǰ��Сʱ֮�ڣ����ú���״̬Ϊ��ֹ
		//��ǰ�������������ͬһ��
		if(year == departureDate_Year & month == departureDate_Month & day == departureDate_Day)
		{
			passTime_Current = hour*60 + minute;
			passTime_Flight = startTime_hour*60 + startTime_minute;
			if(passTime_Flight - passTime_Current <= 120)
			{
				Data.flightList.get(index).setFlightTERMINATE();
			}
		}
		//��ǰ����Ϊ������ڵ�ǰһ��
		if(year == departureDate_Year & month == departureDate_Month & day == departureDate_Day - 1)
		{
			passTime_Current = day*24*60 + hour*60 + minute;
			passTime_Flight = departureDate_Day*24*60 + startTime_hour*60 + startTime_minute;
			if(passTime_Flight - passTime_Current <= 120)
			{
				Data.flightList.get(index).setFlightTERMINATE();
			}
		}
		//��ǰ���������������
		if(year >= departureDate_Year & month >= departureDate_Month & day > departureDate_Day)
		{
			Data.flightList.get(index).setFlightTERMINATE();
		}
		
	}
}

//�û�����
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

//��ӹ���Ա
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
		flg = false;//�����о�
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
	
//���Ĺ���Ա��Ϣ
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
				flg = false;//�����о�
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

//������ѯ
static void superQuery()
{
	//��ѯĳ�������е���Ϣ
	//��ѯ����
	//��ͨ��ѯ
	updateFlight();
	starSeparation(120);
	System.out.println("[Super��Query]");
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
			flightQuery_Admin(STARTCITY);// ����ɳ��в�ѯ
			System.out.print("Success!(Press Enter to continue)");
			input.nextLine();
			superQuery();
		case 4:
			flightQuery_Admin(ARRIVALCITY);// ��������в�ѯ
			System.out.print("Success!(Press Enter to continue)");
			input.nextLine();
			superQuery();
		case 5:
			flightQuery_Admin(DEPARTUREDATE);// ��������ڲ�ѯ
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

//����Ա���鿴����
static void viewOrder()
{
	updateFlight();
	System.out.println("All order show as follows:");
	for (int index = 0; index < Data.orderList.size(); index++) // ��ʾ���ж�������Ϣ
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
	else if (orderIndex >= 0 & orderIndex < Data.orderList.size())//������Ķ����źϷ� 
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

//����Ա���鿴������ϸ��Ϣ
static void viewFlight()
{
	updateFlight();
	System.out.println("All fight show as follows:");
	for (int index = 0; index < Data.flightList.size(); index++) // ��ʾ���к������Ϣ
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
	//�����Ӧ���൱ǰ�˿�������
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
	else//��Ӧ����˿���Ϊ��
	{
		System.out.print("No passenger.(Press Enter to countinue)");
		input.nextLine();
	}

}

//���棺�޸ĺ�����Ϣ
static void modifyFlight() {
	updateFlight();
	Flight aimFlight;
	starSeparation(120);
	System.out.println("All fight show as follows:");
	for (int index = 0; index < Data.flightList.size(); index++) // ��ʾ���к������Ϣ
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
	} else// ���뺽�಻���޸ģ����ص��޸ĺ���
	{
		System.out.print("Error! Flight can not be modify.(Press enter to continue)");
		input.nextLine();
		modifyFlight();
	}
}

//���棺�û�ע��
//���벻�Ϸ����ظ���ǰ�׶����룬ע��ɹ��򷵻����˵�
static void signUp()
{
	starSeparation(120);
	System.out.println("[Acount Creation]");
	starSeparation(120);
	System.out.println("If you want back to primary menu, enter 'primary menu'.");
	System.out.print("Plese enter your real name:");// ��ʵ����
	String tempRealName = input.nextLine();
	if(tempRealName.toLowerCase().compareTo("primary menu") == 0 )
	{
		primaryMenu();
	}
	String tempIdentityID1 = new String();//��һ����������֤
	String tempIdentityID2 = new String();//�ڶ�����������֤
	String tempPassword1 = new String();//��һ�����������
	String tempPassword2 = new String();//�ڶ������������

	do
	{
		do
		{
			System.out.print("Plese enter your identity ID as your account:");// ���֤���˻���
			tempIdentityID1 = input.nextLine();
			//������primary menuʱ��������Ա��½�����ص���ʼ�˵�
			if(tempIdentityID1.compareTo("primary menu") == 0)
			{
				primaryMenu();
			}
			if(!isCorrectID(tempIdentityID1))
			{
				System.out.println("Please enter correct ID number!");
			}
			//����û����Ƿ���Ч
			for(int index = 0; index < Data.adminList.size(); index++)
			{
				if(tempIdentityID1.compareTo(Data.adminList.get(index).getUserName()) == 0)
				{
					System.out.println("Account already exists, please enter a new account.");
					isAvailable = false;
				}
			}
		}while(!isCorrectID(tempIdentityID1) | !isAvailable);//���֤��ʽ���Ի��˻����Ѵ���ʱ����ѭ��
		System.out.print("Plese enter your identity ID agian:");// ���֤����ȷ��
		tempIdentityID2 = input.nextLine();
		if(tempIdentityID2.compareTo("primary menu") == 0)
		{
			primaryMenu();
		}
		if(tempIdentityID1.compareTo(tempIdentityID2) != 0)
			System.out.println("Error! Please input the same ID number.");
	}while(tempIdentityID1.compareTo(tempIdentityID2) != 0);
	// ��������
	do {
		// �������ȷ��
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
			Data.signUpPassengers++;// ��ע���û���+1
			// ����ǰע��˿�¼����ע��˿��б�
			Passenger newPassenger = new Passenger();
			newPassenger.setUniqueID(Data.signUpPassengers - 1);
			newPassenger.setRealName(tempRealName);
			newPassenger.setIdNumber(tempIdentityID1);
			newPassenger.setPassword(tempPassword1);
			Data.passengerList.add(newPassenger);
			System.out.print("Success!(Press Enter to continue)");
			input.nextLine();
			primaryMenu();//���س�ʼ�˵�
		}
	} while (tempPassword1.compareTo(tempPassword2) != 0);
}

//������֤���Ƿ�Ϸ�
//18λ���ֻ�17λ���ּ�ĩλXΪ�Ϸ����֤��,�Ϸ������棬�Ƿ����ؼ�
static boolean isCorrectID(String identityID) {
	boolean isCorrect = false;
	//����Ƿ�ʮ��λ
	if (identityID.length() != 18) {
		return false;
	} 
	//������λ�Ƿ�Ϸ�
	else
	{
		for(int index = 0; index < 18; index++)
		{
			for(int range = 1; range <=9; range++)
			{
				if(identityID.substring(index, index+1).compareTo("" + range) == 0)
					isCorrect = true;
			}
			//����ʮ��λ�Ƿ�ΪX
			if(identityID.substring(17,18).toLowerCase().compareTo("x") == 0)
				isCorrect = true;
		}
		if(!isCorrect)
			return false;
		else
			return true;
	}
}

//���棺��½
//���ݹؼ������������Ա��½������û���½����
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
		if(Data.isAdminLogin)//����Աδע����ֱ�ӽ������˵�
		{
			break;
		}
		do
		{
			System.out.println("If you want back to primary menu, enter 'primary menu'.");
			System.out.print("Account:");
			String tempAccount = input.nextLine();
			//������primary menuʱ��������Ա��½�����ص���ʼ�˵�
			if(tempAccount.toLowerCase().compareTo("primary menu") == 0 )
			{
				primaryMenu();
			}
			System.out.print("Password:");
			String tempPassword = input.nextLine();
			//������primary menuʱ��������Ա��½�����ص���ʼ�˵�
			if(tempPassword.toLowerCase().compareTo("primary menu") == 0)
			{
				primaryMenu();
			}
			for(int index = 0; index < Data.adminList.size(); index ++)
			{
				//����ƥ���о�
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
		}while(!isMatch);//�����ƥ�������ѭ��
		break;
	case PASSENGER:
		starSeparation(120);
		System.out.println("[Passenger Login]");
		if(Data.isPassengerLogin)//�˿�δע����ֱ�ӽ���˿Ͳ˵�
		{
			break;
		}
		do
		{
			starSeparation(120);
			System.out.println("If you want back to primary menu, enter 'primary menu'.");
			System.out.print("ID number:");
			String tempAccount = input.nextLine();
			//������primary menuʱ��������Ա��½�����ص���ʼ�˵�
			if(tempAccount.toLowerCase().compareTo("primary menu") == 0)
			{
				primaryMenu();
			}
			System.out.print("Password:");
			String tempPassword = input.nextLine();
			//������primary menuʱ��������Ա��½�����ص���ʼ�˵�
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
				//����ƥ���о�
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
		}while(!isMatch);//�����ƥ�������ѭ��
		break;
	}
}

//�˵�������Ա�˵�
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

//�˵����˿Ͳ˵�
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

//��ʾ������Ϣ����Ա��
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

//��ʾ������Ϣ�˿Ͱ�
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

package TiketReserveSystem;

import java.util.ArrayList;
import java.util.Date;

public class function {

	static java.util.Scanner input = new java.util.Scanner(System.in);

	// 初始化
	public static void initialize() {
		Data.initializeAdmin();// 设置缺省管理员
		Data.initializeFlightList();// 设置缺省航班
	}

	// 主菜单
	public static void mainMenu() {
		starSepa(50);
		System.out.println("\tTikeyReserveSystem");
		System.out.println("admin enter 1.\npassenger enter 2.\njust query enter 3.\nSign up enter 4.\nClose enter 0.");
		String in = input.nextLine();
		mainMenuIIP(protecter(4, in));// 非法输入保护
		int choice = protecter(4, in);// 获取合法输入
		switch (choice) {
		case 0:// 关闭系统
			System.exit(0);
		case 1:// 管理员登陆
			adminLogin();
			break;
		case 2:// 乘客登陆
			passengerLogin();
			break;
		case 3:// 显示一般登陆菜单
			normalQuery();
			break;
		case 4:// 显示乘客注册界面
			signUp();
			break;
		}
	}

	// 输入保护
	public static int protecter(int indexRange, String illegalInput) {
		int legalInput = -1;
		if(!illegalInput.isEmpty())
		{
			for (int index = 0; index <= indexRange; index++) {
				if (illegalInput.substring(0, 1).compareTo("" + index) == 0) {
					legalInput = index;
					break;
				}
			}
			if (illegalInput.length() > 1)
				legalInput = -1;
		}
		return legalInput;
	}

	// 主菜单输入保护
	public static void mainMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			mainMenu();
		}
	}

	// 管理员菜单输入保护
	public static void adminMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			adminMenu();
		}
	}

	// 乘客菜单输入保护
	public static void passengerMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			passengerMenu();
		}
	}

	// 一般查询菜单输入保护
	public static void normalQueryMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			normalQuery();
		}
	}
	
	// 乘客一般查询菜单输入保护
	public static void normalQueryPSGMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			normalQueryPSG();
		}
	}

	// 管理员一般查询菜单输入保护
	public static void normalQueryAdminMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			normalQueryAdmin();
		}
	}
	
	// 修改航班菜单输入保护
	public static void adjustUnpublishedFlightMenuIIP(Flight flight, int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			adjustUnpublishedFlight(flight);
		}
	}
	
	// 修改航班菜单输入保护
	public static void adjustPublishedFlightMenuIIP(Flight flight, int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			adjustPublishedFlight(flight);
		}
	}

	// 管理员登陆界面
	public static void adminLogin() {
		// 是否误操作需返回主菜单
		starSepa(50);
		System.out.println("[Administrator Login]");
		System.out.println("If you want go back to MainMenu, enter MainMenu.");
		System.out.println("Else press enter to contunue.");
		String temp = input.nextLine();
		if (temp.toLowerCase().compareTo("mainmenu") == 0) {
			mainMenu();
		}
		starSepa(50);
		System.out.print("Acount:");
		String tempID1 = input.nextLine();
		System.out.print("Password:");
		String tempPW1 = input.nextLine();
		// 管理员列表为空，登陆失败，返回主菜单
		if (Data.adminList.size() == 0) {
			System.out.println("Error!");
			mainMenu();
		}
		// 管理员登陆验证
		for (int counter = 0; counter < Data.adminList.size(); counter++) {
			if ((tempID1.compareTo(Data.adminList.get(counter).getUserName()) == 0)
					& (tempPW1.compareTo(Data.adminList.get(counter).getPassword()) == 0)) {
				System.out.println("Admin Login.");
				Data.isLogin = true;// 登陆标记设为真
				Data.isAdminLogin = true;// 管理员登陆标记设为真
				Data.isPassengerLogin = false;// 乘客登陆标记设为假
				Data.currentAdminIndex = counter;// 更新当前在线管理员序号
				adminMenu();
				break;
			}
			// 验证失败返回主菜单
			else {
				System.out.print("Error!\n");
				mainMenu();
			}
		}
	}

	// 管理员菜单
	public static void adminMenu() {
		starSepa(50);
		System.out.println("[Administrator]");
		System.out.println("0:Logout and back to Mainmenu.");
		System.out.println("1:create filght.");
		System.out.println("2:publish filght.");
		System.out.println("3:adjust filght.");
		System.out.println("4:delete filght.");
		System.out.println("5:update filght.");
		System.out.println("6:query filght.");
		System.out.println("7:super query.");
		System.out.println("8:user management.");
		System.out.print("Plese enter the index:");
		String in = input.nextLine();
		adminMenuIIP(protecter(7, in));// 非法输入保护
		int choice = protecter(7, in);// 获取合法输入
		if (choice >= 0 & choice <= 6) {
			switch (choice) {
			case 0:
				Data.isAdminLogin = false;// 管理员登出
				Data.currentAdminIndex = -1;// 当前无管理员在线
				mainMenu();
				break;
			case 1:
				createFlight();
				adminMenu();
				break;
			case 2:
				publishFlight();
				adminMenu();
				break;
			case 3:
				adjustFlight();
				adminMenu();
				break;
			case 4:
				deleteFlight();
				adminMenu();
				break;
			case 5:
				updateFlight();
				adminMenu();
				break;
			case 6:
				adminMenu();
				break;
			case 7:
				superQuery();
				adminMenu();
				break;
			case 8:
				break;
			}
		} else {
			System.out.println("Request error, plese check your request.");
			adminMenu();
		}
	}

	//过程：自动更新航班状态
	public static void updateFlight()
	{
		for(int counter = 0; counter < Data.flightList.size(); counter ++)
		{
			//更新已发布未终止航班的状态
			if(Data.flightList.get(counter).getEnumStatus() != FlightStatus.UNPUBLISHED & Data.flightList.get(counter).getEnumStatus() != FlightStatus.TERMINATE)
			{
				//当前乘客数等于容量时，更新航班状态为已满
				if(Data.flightList.get(counter).getCurrentPassengers() == Data.flightList.get(counter).getSeatCapacity())
				{
					Data.flightList.get(counter).setFlightFull();
				}
				//当前乘客数等于容量时，更新航班状态为有效
				if(Data.flightList.get(counter).getCurrentPassengers() != Data.flightList.get(counter).getSeatCapacity())
				{
					Data.flightList.get(counter).setFlightAvailable();
				}
			}
			//更新即将起飞航班为终止
			//更新过期航班为终止
		}
	}
	
	// 乘客登陆界面
	public static void passengerLogin() {
		// 是否误操作需返回主菜单
		starSepa(50);
		System.out.println("[Passenger Login]");
		System.out.println("If you want go back to MainMenu, enter MainMenu.");
		System.out.println("Else press enter to contunue.");
		String temp = input.nextLine();
		if (temp.toLowerCase().compareTo("mainmenu") == 0) {
			mainMenu();
		}
		starSepa(50);
		System.out.print("Acount:");
		String tempID2 = input.nextLine();
		System.out.print("Password:");
		String tempPW2 = input.nextLine();
		// 已注册乘客列表为空，登陆失败，返回主菜单
		if (Data.signUpPassengerList.size() == 0) {
			System.out.println("Error!");
			mainMenu();
		}
		// 乘客登陆验证
		for (int counter = 0; counter < Data.signUpPassengerList.size(); counter++) {
			if ((tempID2.compareTo(Data.signUpPassengerList.get(counter).getIdentityID()) == 0)
					& (tempPW2.compareTo(Data.signUpPassengerList.get(counter).getPassword()) == 0)) {
				System.out.println("Passenger Login.");
				Data.isLogin = true;// 登陆标记设为真
				Data.isPassengerLogin = true;// 乘客登陆标记设为真
				Data.isAdminLogin = false;// 管理员登陆标记设为假
				Data.currentPassengerIndex = counter;// 更新当前在线乘客序号
				normalQuery();// 显示一般查询菜单
				break;
			}
			// 验证失败，返回主菜单
			if ((!Data.isPassengerLogin) & counter == Data.signUpPassengerList.size() - 1) {
				System.out.print("Error!\n");
				mainMenu();
			}
		}
	}

	// 乘客菜单
	public static void passengerMenu() {
		starSepa(50);
		System.out.println("[Passenger]");
		System.out.println("0:Logout and back to Mainmenu.");
		System.out.println("1:Query.");
		System.out.println("2:Reserve flight.");
		System.out.println("3:Unsubscribe flight.");
		System.out.println("4:List order.");
		System.out.print("Plese enter the index:");
		String in = input.nextLine();
		passengerMenuIIP(protecter(4, in));// 非法输入保护
		int choice = protecter(4, in);// 获取合法输入
		if (choice >= 0 & choice <= 4) {
			switch (choice) {
			case 0:
				Data.isPassengerLogin = false;// 乘客登出
				Data.currentPassengerIndex = -1;// 当前无乘客在线
				mainMenu();
				break;
			case 1:
				normalQueryPSG();
				passengerMenu();
				break;
			case 2:
				isBookingPSG();
				passengerMenu();
				break;
			case 3:
				isUnsubscribe();
				passengerMenu();
				break;
			case 4:
				Data.signUpPassengerList.get(Data.currentPassengerIndex).listAllOrder();// 读取当前在线用户列出其所有订单
				Data.signUpPassengerList.get(Data.currentPassengerIndex).isPaidForOrder();// 询问当前用户是否支付订单
				passengerMenu();
				break;
			}
		} else {
			System.out.println("Request error, plese check your request.");
			adminMenu();
		}
	}

	// 一般查询
	public static void normalQuery() {
		starSepa(50);
		System.out.println("[Normal Query]");
		System.out.println("0:back to Mainmenu.");
		System.out.println("1:by start city.");
		System.out.println("2:by arrival city.");
		System.out.println("3:by departure date.");
		System.out.print("Plese enter the index:");
		String in = input.nextLine();
		normalQueryMenuIIP(protecter(3, in));// 非法输入保护
		int choice = protecter(3, in);// 获取合法输入
		if (choice >= 0 & choice <= 3) {
			switch (choice) {
			case 0:
				mainMenu();
				break;
			case 1:
				qByStartCity();
				isBooking();
				break;
			case 2:
				qByArrivalCity();
				isBooking();
				break;
			case 3:
				qByDepartureDate();
				isBooking();
				break;
			}
		} else {
			System.out.println("Request error, plese check your request.");
			normalQuery();
		}
	}

	// 乘客的一般查询
	public static void normalQueryPSG() {
		starSepa(50);
		System.out.println("[Normal Query]");
		System.out.println("0:back to PassengerMenu.");
		System.out.println("1:by start city.");
		System.out.println("2:by arrival city.");
		System.out.println("3:by departure date.");
		System.out.print("Plese enter the index:");
		String in = input.nextLine();
		normalQueryPSGMenuIIP(protecter(3,in));
		int choice = protecter(3,in);
		input.nextLine();
		if (choice >= 0 & choice <= 3) {
			switch (choice) {
			case 0:
				passengerMenu();
				break;
			case 1:
				qByStartCity();
				isBookingPSG();
				break;
			case 2:
				qByArrivalCity();
				isBookingPSG();
				break;
			case 3:
				qByDepartureDate();
				isBookingPSG();
				break;
			}
		} else {
			System.out.println("Request error, plese check your request.");
			normalQuery();
		}
	}

	//管理员的一般查询
	public static void normalQueryAdmin() {
		starSepa(50);
		System.out.println("[Normal Query]");
		System.out.println("0:back to adminMenu.");
		System.out.println("1:by start city.");
		System.out.println("2:by arrival city.");
		System.out.println("3:by departure date.");
		System.out.print("Plese enter the index:");
		String in = input.nextLine();
		normalQueryAdminMenuIIP(protecter(3,in));
		int choice = protecter(3,in);
		if (choice >= 0 & choice <= 3) {
			switch (choice) {
			case 0:
				adminMenu();
				break;
			case 1:
				qByStartCity();
				adminMenu();
				break;
			case 2:
				qByArrivalCity();
				adminMenu();
				break;
			case 3:
				qByDepartureDate();
				adminMenu();
				break;
			}
		} else {
			System.out.println("Request error, plese check your request.");
			normalQuery();
		}
	}
	
	// 创建新航班
	public static void createFlight() {

		starSepa(50);
		Flight newFlight = new Flight();
		System.out.print("Flight ID:");
		newFlight.setFlightID(input.nextLine());
		System.out.print("Start time:");
		newFlight.setstartTime(input.nextLine());
		System.out.print("Arrival time:");
		newFlight.setArrivalTime(input.nextLine());
		System.out.print("Start city:");
		newFlight.setStartCity(input.nextLine());
		System.out.print("Arrival city:");
		newFlight.setArrivalTime(input.nextLine());
		System.out.print("Departure Date:");
		newFlight.setDepartureDate(input.nextLine());
		System.out.print("Price:");
		newFlight.setPrice(input.nextInt());
		System.out.print("Seat capacity:");
		newFlight.setSeatCapacity(input.nextInt());
		Data.flightList.add(newFlight);
		System.out.println("Success!");

	}

	// 显示航班的所有信息
	public static void listFlightInf(Flight flight) {

		starSepa(50);
		System.out.printf("Flight ID:%s\n", flight.getFlightID());
		System.out.printf("Start time:%s\n", flight.getStartTime());
		System.out.printf("Arrival time:%s\n", flight.getArrivalTime());
		System.out.printf("Start city:%s\n", flight.getStartCity());
		System.out.printf("Arrival city:%s\n", flight.getArrivalCity());
		System.out.printf("Departure Date:%S\n", flight.getDepartureDate());
		System.out.printf("Price:%d\n", flight.getPrice());
		System.out.printf("Seat capacity:%d\n", flight.getSeatCapacity());
		starSepa(50);
	}

	// 显示航班对乘客开放的信息
	public static void listFlightTP(Flight flight) {
		starSepa(50);
		System.out.printf("Flight ID:%s\n", flight.getFlightID());
		System.out.printf("Start time:%s\n", flight.getStartTime());
		System.out.printf("Arrival time:%s\n", flight.getArrivalTime());
		System.out.printf("Start city:%s\n", flight.getStartCity());
		System.out.printf("Arrival city:%s\n", flight.getArrivalCity());
		System.out.printf("Departure Date:%S\n", flight.getDepartureDate());
		System.out.printf("Price:%d\n", flight.getPrice());
		switch (flight.getEnumStatus()) {
		default:
			break;
		case AVAILABLE:
			System.out.printf("Status:%s\n", flight.getStrStatus());
			break;
		case FULL:
			System.out.printf("Status:%s\n", flight.getStrStatus());
			break;
		}
		starSepa(50);
	}

	// 通过航班号读取该航班在航班列表内的序号
	public static int getIndexByFID(String fID) {
		int index = -1;
		for (int counter = 0; counter < Data.flightList.size(); counter++) {
			Flight flight = Data.flightList.get(counter);
			if (flight.getFlightID().compareTo(fID) == 0) {
				index = counter;
			}
		}

		return index;
	}

	// 界面:修改航班信息
	public static void adjustFlight() {
		Flight aimFlight = null;
		for (int index = 0; index < Data.flightList.size(); index++) // 显示所有航班的信息
		{
			starSepa(50);
			System.out.printf("Index:%d\n", index);
			listFlightInf(Data.flightList.get(index));// 显示航班信息
		}
		starSepa(50);
		System.out.print("Plese enter the Index of flight you want to adjust:");
		int aimIndex = input.nextInt();
		input.nextLine();
		aimFlight = Data.flightList.get(aimIndex);
		if (aimFlight.getEnumStatus() == FlightStatus.UNPUBLISHED) {
			adjustUnpublishedFlight(aimFlight);
		} else if (aimFlight.getEnumStatus() == FlightStatus.AVAILABLE) {
			adjustPublishedFlight(aimFlight);
		} else// 输入航班不可修改，返回管理员菜单
		{
			System.out.println("Error!");
			adminMenu();
		}
	}

	// 操作：修改未发布航班的信息
	public static void adjustUnpublishedFlight(Flight flight) {
		Flight adjustion = flight;// 拷贝目标航班至修改版
		starSepa(50);
		System.out.println("\t[Adjust Flight Information]");
		System.out.println("1:Start time");
		System.out.println("2:Arrival time");
		System.out.println("3:Start city");
		System.out.println("4:Arrival city");
		System.out.println("5:Departure Date");
		System.out.println("6:Price");
		System.out.println("7:Seat capacity");
		System.out.print("Which information you want to adjust:");
		String in = input.nextLine();
		adjustUnpublishedFlightMenuIIP(flight, protecter(7, in));// 非法输入保护
		int choice = protecter(7, in);// 获取合法输入
		switch (choice) {
		case 1:// 修改起飞时间
			System.out.print("New Start time:");
			adjustion.setstartTime(input.nextLine());
			break;
		case 2:// 修改到达时间
			System.out.print("New Arrival time:");
			adjustion.setArrivalTime(input.nextLine());
			break;
		case 3:// 修改起飞城市
			System.out.print("New Start city:");
			adjustion.setStartCity(input.nextLine());
			break;
		case 4:// 修改目标城市
			System.out.print("New Arrival city:");
			adjustion.setArrivalCity(input.nextLine());
			break;
		case 5:// 修改起飞日期
			System.out.print("New Departure Date:");
			adjustion.setDepartureDate(input.nextLine());
			break;
		case 6:// 修改价格
			System.out.print("New Price:");
			adjustion.setPrice(input.nextInt());
			break;
		case 7:// 修改航班容量
			System.out.print("New Seat capacity:");
			adjustion.setSeatCapacity(input.nextInt());
			break;
		}
		int index = Data.flightList.indexOf(flight);
		Data.flightList.set(index, adjustion);
		System.out.println("Success!");
	}

	// 操作：修改已发布航班的信息
	public static void adjustPublishedFlight(Flight flight) {
		Flight adjustion = flight;// 拷贝目标航班至修改版
		starSepa(50);
		System.out.println("\t[Adjust Flight Information]");
		System.out.println("1:Current price");
		System.out.println("2:Seat capacity");
		System.out.print("Which information you want to adjust:");
		String in = input.nextLine();
		adjustPublishedFlightMenuIIP(flight, protecter(1, in) + 1);// 非法输入保护
		int choice = protecter(1, in) + 1;// 获取合法输入
		switch (choice) {
		case 1:// 修改价格
			System.out.print("New Price:");
			adjustion.setPrice(input.nextInt());
			break;
		case 2:// 修改航班容量
			System.out.print("New Seat capacity:");
			adjustion.setSeatCapacity(input.nextInt());
			break;
		}
		int index = Data.flightList.indexOf(flight);
		Data.flightList.set(index, adjustion);
		System.out.println("Success!");
	}

	// 通过航班号删除航班
	public static void deleteFlight() {
		System.out.print("The flight ID of which you want to delete:");
		String flightID = input.nextLine();
		int index = getIndexByFID(flightID);
		if (index == -1) {
			System.out.println("Flight ID error, plese check agian.");
		} else {
			System.out.printf("Waring:Are you sure to delete Flight%s?(yes/no)", flightID);
			String isSure = input.nextLine();
			if (isSure.compareTo("yes") == 0) {
				Data.flightList.remove(index);// 从航班列表从移除该航班
				System.out.println("Success!");
			}
		}
	}

	// 通过起飞城市查询，匹配则显示航班，无匹配则报错
	public static void qByStartCity() {
		System.out.print("Plese enter the start city:");
		String sc = input.nextLine();
		boolean match = false;
		for (int counter = 0; counter < Data.flightList.size(); counter++) {
			Flight flight = Data.flightList.get(counter);
			if (flight.getEnumStatus() == FlightStatus.AVAILABLE)// 有效的航班可查
			{
				if (flight.getStartCity().compareTo(sc) == 0) {
					listFlightInf(Data.flightList.get(counter));// 显示匹配航班
					match = true;
				}
			}
		}
		if (!match) {
			System.out.printf("Error, flight start form %s do not exist.\n", sc);// 报错
		}
	}

	// 通过目的城市查询，匹配则显示航班，无匹配则报错
	public static void qByArrivalCity() {
		System.out.print("Plese enter the arrival city:");
		String ac = input.nextLine();
		boolean match = false;
		for (int counter = 0; counter < Data.flightList.size(); counter++) {
			Flight flight = Data.flightList.get(counter);
			if (flight.getEnumStatus() == FlightStatus.AVAILABLE)// 有效的航班可查
			{
				if (flight.getArrivalCity().compareTo(ac) == 0) {
					listFlightInf(Data.flightList.get(counter));// 显示匹配航班
					match = true;
				}
			}
		}
		if (!match) {
			System.out.printf("Error, flight go to %s do not exist.\n", ac);// 报错
		}
	}

	// 通过起飞日期查询，匹配则显示航班，无匹配则报错
	public static void qByDepartureDate() {
		System.out.print("Plese enter the departure date(yyyy mm dd):");
		String dd = input.nextLine();
		boolean match = false;
		for (int counter = 0; counter < Data.flightList.size(); counter++) {
			Flight flight = Data.flightList.get(counter);
			if (flight.getEnumStatus() == FlightStatus.AVAILABLE)// 有效的航班可查
			{
				if (flight.getDepartureDate().compareTo(dd) == 0) {
					listFlightInf(Data.flightList.get(counter));// 显示匹配航班
					match = true;
				}
			}
		}
		if (!match) {
			System.out.printf("Error, %s has no flight.\n", dd);// 报错
		}
	}

	// 过程：是否预定
	public static void isBooking() {
		System.out.print("Do you want to book?(yes/no)");
		String isBook = input.nextLine();
		if (isBook.toLowerCase().compareTo("yes") == 0) {
			if (Data.isPassengerLogin)// 乘客已登录，可以预定
			{
				// 通过航班号预定
				System.out.print("Enter the flight ID:");
				String bookFID = input.nextLine();
				if (getIndexByFID(bookFID) == -1) {
					System.out.println("Error, plese check again.");
					isBooking();
				} else {
					// 显示意预定航班的信息
					starSepa(50);
					System.out.println("Flight Information:");
					starSepa(50);
					listFlightInf(Data.flightList.get(getIndexByFID(bookFID)));
					starSepa(50);
					if(Data.flightList.get(getIndexByFID(bookFID)).getStrStatus() != "AVAILABLE")
					{
						System.out.printf("Error:The Flight%s is not availabale.\n",
						Data.flightList.get(getIndexByFID(bookFID)).getFlightID());
						isBooking();
					}
					if (Data.flightList.get(getIndexByFID(bookFID)).getStrStatus() == "AVAILABLE") {
						System.out.println("Is it you want to book?(yes/no)");
						String isWant = input.nextLine();
						if (isWant.compareTo("yes") == 0) {
							// 以此航班为当前在线乘客订单
							Data.signUpPassengerList.get(Data.currentPassengerIndex)
									.createOrder(Data.flightList.get(getIndexByFID(bookFID)));
							System.out.println("Booking Success!");
						} 
						else {
							isBooking();// 询问是否预定
						}
					} 
				}
			} else// 乘客未登录，转至乘客登陆操作
			{
				System.out.println("Plese sign up and login as passenger and try agian.");
				passengerLogin();
			}
		} else {
			normalQuery();// 返回一般查询菜单
		}
	}

	// 订单号检查
	public static int checkOrderNumber(int orderNumber) {
		// 判断输入是否在当前在线乘客的订单大小之内
		if (orderNumber >= 0
				& orderNumber <= Data.signUpPassengerList.get(Data.currentPassengerIndex).getOrderListSize()) {
			return orderNumber;
		} else {
			return -1;
		}
	}

	// 界面：是否退定
	public static void isUnsubscribe() {
		System.out.print("Do you want to unsubscribe?(yes/no)");
		String isBook = input.nextLine();
		if (isBook.toLowerCase().compareTo("yes") == 0) {
			// 通过订单号退订
			System.out.println("Enter the OrderNumber:");
			int UnsubscribeOrderNunmber = input.nextInt();
			input.nextLine();
			checkOrderNumber(UnsubscribeOrderNunmber);// 检查输入是否合法
			if (UnsubscribeOrderNunmber == -1) {
				System.out.println("Error, plese check again.");
				isUnsubscribe();// 询问是否退订
			} else {
				// 显示意退订航班的信息
				starSepa(50);
				System.out.println("Flight Information:");
				starSepa(50);
				// 获取当前在线乘客此订单号对应的航班
				Flight aimFlight = Data.signUpPassengerList.get(Data.currentPassengerIndex)
						.getOrderFlight(UnsubscribeOrderNunmber);
				// 获取对应航班在航班列表里的序号
				int aimFID = getIndexByFID(aimFlight.getFlightID());
				// 显示该航班信息
				listFlightInf(Data.flightList.get(aimFID));
				starSepa(50);
				System.out.println("Is it you want to unsubscribe?(yes/no)");
				String isWant = input.nextLine();
				if (isWant.toLowerCase().compareTo("yes") == 0) {
					// 从订单列表中删除
					Data.signUpPassengerList.get(Data.currentPassengerIndex).unsubscribeOrder(aimFlight);
					System.out.println("Booking Success!");
				} else {
					isUnsubscribe();// 询问是否退订
				}
			}
		} else {
			normalQueryPSG();// 返回乘客菜单
		}
	}

	// 过程：登陆乘客是否预定
	public static void isBookingPSG() {
		System.out.print("Do you want to book?(yes/no)");
		String isBook = input.nextLine();
		if (isBook.toLowerCase() == "yes") {
			// 通过航班号预定
			System.out.println("Enter the flight ID:");
			String bookFID = input.nextLine();
			if (getIndexByFID(bookFID) == -1) {
				System.out.println("Error, plese check again.");
				isBookingPSG();
			} else {
				// 显示意预定航班的信息
				starSepa(50);
				System.out.println("Flight Information:");
				starSepa(50);
				if(Data.flightList.get(getIndexByFID(bookFID)).getStrStatus() != "AVAILABLE")
				{
					System.out.printf("Error:The Flight%s is not availabale.\n",
					Data.flightList.get(getIndexByFID(bookFID)).getFlightID());
					isBooking();
				}
				if (Data.flightList.get(getIndexByFID(bookFID)).getStrStatus() == "AVAILABLE") {
					System.out.println("Is it you want to book?(yes/no)");
					String isWant = input.nextLine();
					if (isWant.compareTo("yes") == 0) {
						// 以此航班为当前在线乘客订单
						Data.signUpPassengerList.get(Data.currentPassengerIndex)
								.createOrder(Data.flightList.get(getIndexByFID(bookFID)));
						System.out.println("Booking Success!");
					} 
					else {
						isBooking();// 询问是否预定
					}
				} 
			}
}
			else 
				{
			normalQueryPSG();// 返回乘客菜单
		}
	}

	// 验证身份证，18位则返回真，否则返回假
	public static boolean checkIdentityID(String identityID) {
		if (identityID.length() != 18) {
			return false;
		} else
			return true;
	}

	// 乘客注册界面
	public static void signUp() {
		starSepa(50);
		System.out.print("Plese enter your real name:");// 真实姓名
		String tempRealName = input.nextLine();
		System.out.print("Plese enter your identity ID as your account:");// 身份证作账户名
		String tempIdentityID1 = input.nextLine();
		if (checkIdentityID(tempIdentityID1)) {
			System.out.print("Plese enter your identity ID agian:");// 身份证二次确认
			String tempIdentityID2 = input.nextLine();
			if (tempIdentityID1.compareTo(tempIdentityID2) == 0) {
				// 创建密码
				String tempPassword1 = new String();
				String tempPassword2 = new String();
				do {
					// 密码二次确认
					System.out.print("Plese create a password for your account:");
					tempPassword1 = input.nextLine();
					System.out.print("Plese enter the password again:");
					tempPassword2 = input.nextLine();
					if (tempPassword1.compareTo(tempPassword2) != 0) {
						System.out.println("Error! Plese enter the same password.");
					} else {
						Data.signUpPassengers++;// 已注册用户数+1
						// 将当前注册乘客录入已注册乘客列表
						passenger newPassenger = new passenger();
						newPassenger.setPassengerID(Data.signUpPassengers);
						newPassenger.setRealName(tempRealName);
						newPassenger.setIdentityID(tempIdentityID1);
						newPassenger.setPassword(tempPassword1);
						Data.signUpPassengerList.add(newPassenger);
						System.out.println("Success!");
						mainMenu();
					}
				} while (tempPassword1.compareTo(tempPassword2) != 0);
			} else {
				System.out.println("Error! Plese enter the same identity ID.");
				mainMenu();// 返回主菜单
			}
		} else {
			System.out.println("Error! Plese check agian.(18 digit only)");
			mainMenu();// 返回主菜单
		}
	}

	// 显示乘客信息
	public static void listPassenger(passenger passenger) {
		starSepa(50);
		System.out.printf("%s\n", passenger.getRealName());
		System.out.printf("Passenger ID：%d\n", passenger.getPassengerID());
		System.out.printf("Identity ID: %s\n", passenger.getIdentityID());
		starSepa(50);
	}

	// 显示未发布的航班
	public static void listUnpublishedFlight(Flight flight) {
		if (flight.getEnumStatus() == FlightStatus.UNPUBLISHED) {
			starSepa(50);
			System.out.printf("Flight ID:%s\n", flight.getFlightID());
			System.out.printf("Start time:%s\n", flight.getStartTime());
			System.out.printf("Arrival time:%s\n", flight.getArrivalTime());
			System.out.printf("Start city:%s\n", flight.getStartCity());
			System.out.printf("Arrival city:%s\n", flight.getArrivalCity());
			System.out.printf("Departure Date:%S\n", flight.getDepartureDate());
			System.out.printf("Price:%d\n", flight.getPrice());
			System.out.printf("Seat capacity:%d\n", flight.getSeatCapacity());
			starSepa(50);
		}
	}

	// 显示所有未发布的航班
	public static void listAllUnpublishedFlight() {
		starSepa(50);
		System.out.println("All the unpublished flight were list below:");
		starSepa(50);
		for (int index = 0; index < Data.flightList.size(); index++) {
			System.out.printf("Number:%-3d%n", index + 1);
			listUnpublishedFlight(Data.flightList.get(index));
		}
	}

	// 发布航班
	public static void publishFlight() {
		starSepa(50);
		listAllUnpublishedFlight();// 显示所有未发布航班
		System.out.print("Plese enter the Flight ID you want to publish:");
		String tempFID = input.nextLine();
		int index = getIndexByFID(tempFID);
		if (index == -1) {
			System.out.println("Flight ID error, plese check agian.");
		} else {
			System.out.printf("NOTE:Are you sure to publish Flight%s?(yes/no)", tempFID);
			String isSure = input.nextLine();
			if (isSure.compareTo("yes") == 0) {
				Data.flightList.get(index).publishFlight();// 修改目标航班状态为已发布
				System.out.println("Success!");
			}
		}
	}

	// 读取当前日期
	public static String getCurrentDate() {
		String formatDate = null;
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate();
		formatDate = "" + year + "-" + month + "-" + day;
		return formatDate;
	}

	// 保证日与年月匹配
	public static int correctDay(int year, int month, int day) {
		if (year % 400 == 0 || ((year % 4 == 0) & (year % 100 == 0)))// 判断是否为闰年
		{
			if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)// 大月
			{
				day %= 31;
			} else if (month == 2)// 闰月
			{
				day %= 29;
			} else// 小月
			{
				day %= 30;
			}
		} else {
			if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)// 大月
			{
				day %= 31;
			} else if (month == 2)// 闰月
			{
				day %= 28;
			} else// 小月
			{
				day %= 30;
			}
		}
		return day;
	}

	// 读取明天的日期
	public static String getDateAfterOneDay() {
		String formatDate = null;
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate() + 1;
		day = correctDay(year, month, day);
		formatDate = "" + year + "-" + month + "-" + day;
		return formatDate;
	}

	// 读取一周后的日期
	public static String getDateAfterOneWeek() {
		String formatDate = null;
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate() + 7;
		day = correctDay(year, month, day);
		formatDate = "" + year + "-" + month + "-" + day;
		return formatDate;
	}

	// 读取下个月的今天
	public static String getDateAfterOneMonth() {
		String formatDate = null;
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 2;
		month %= 12;
		int day = date.getDate();
		formatDate = "" + year + "-" + month + "-" + day;
		return formatDate;
	}

	// 读取当前完整时间串
	public static String getCurrentCompleteTime() {
		String formatTime = null;
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate();
		int hour = date.getHours();
		int minute = date.getMinutes();
		formatTime = "" + year + "-" + month + "-" + day + " " + hour + ":" + minute;
		return formatTime;
	}
	
	// 读取当前小时与分钟
	public static String getCurrentTime() {
		String formatTime = null;
		Date date = new Date();
		int hour = date.getHours();
		int minute = date.getMinutes();
		formatTime = "" + hour + ":" + minute;
		return formatTime;
	}

	// 星号分割线生成器
	public static void starSepa(int width) {
		for (int counter = 1; counter <= width; counter++) {
			System.out.print("*");
			if (counter == width)
				System.out.println();
		}
	}

	// 界面：超级查找
	public static void superQuery()
	{
		for (int index = 0; index < Data.flightList.size(); index++) // 显示所有航班的信息
		{
			starSepa(50);
			System.out.printf("Index:%d\n", index);
			listFlightInf(Data.flightList.get(index));// 显示航班信息
		}
		starSepa(50);
		System.out.println("Plese enter the Index of flight you want to clarify.");
		int index = input.nextInt();
		input.nextLine();
		Flight aimFlight = Data.flightList.get(index);
		listFlightInf(aimFlight);
		for(int counter = 0; counter <= aimFlight.getCurrentPassengers(); counter++)
		{
			int orderIdex = aimFlight.getPassenger(counter).getFlightOrderNumber(Data.flightList.get(index));
			Order order = aimFlight.getPassenger(counter).getOrder(index);
			Data.flightList.get(index).getPassenger(counter).listOrder(order);
		}
	}
	
}// class

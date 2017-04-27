package TiketReserveSystem;

import java.util.ArrayList;
import java.util.Date;

public class function {

	static java.util.Scanner input = new java.util.Scanner(System.in);

	// ��ʼ��
	public static void initialize() {
		Data.initializeAdmin();// ����ȱʡ����Ա
		Data.initializeFlightList();// ����ȱʡ����
	}

	// ���˵�
	public static void mainMenu() {
		starSepa(50);
		System.out.println("\tTikeyReserveSystem");
		System.out.println("admin enter 1.\npassenger enter 2.\njust query enter 3.\nSign up enter 4.\nClose enter 0.");
		String in = input.nextLine();
		mainMenuIIP(protecter(4, in));// �Ƿ����뱣��
		int choice = protecter(4, in);// ��ȡ�Ϸ�����
		switch (choice) {
		case 0:// �ر�ϵͳ
			System.exit(0);
		case 1:// ����Ա��½
			adminLogin();
			break;
		case 2:// �˿͵�½
			passengerLogin();
			break;
		case 3:// ��ʾһ���½�˵�
			normalQuery();
			break;
		case 4:// ��ʾ�˿�ע�����
			signUp();
			break;
		}
	}

	// ���뱣��
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

	// ���˵����뱣��
	public static void mainMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			mainMenu();
		}
	}

	// ����Ա�˵����뱣��
	public static void adminMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			adminMenu();
		}
	}

	// �˿Ͳ˵����뱣��
	public static void passengerMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			passengerMenu();
		}
	}

	// һ���ѯ�˵����뱣��
	public static void normalQueryMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			normalQuery();
		}
	}
	
	// �˿�һ���ѯ�˵����뱣��
	public static void normalQueryPSGMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			normalQueryPSG();
		}
	}

	// ����Աһ���ѯ�˵����뱣��
	public static void normalQueryAdminMenuIIP(int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			normalQueryAdmin();
		}
	}
	
	// �޸ĺ���˵����뱣��
	public static void adjustUnpublishedFlightMenuIIP(Flight flight, int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			adjustUnpublishedFlight(flight);
		}
	}
	
	// �޸ĺ���˵����뱣��
	public static void adjustPublishedFlightMenuIIP(Flight flight, int IIPer) {
		if (IIPer == -1) {
			System.out.println("Error! Plese input correct index number!");
			adjustPublishedFlight(flight);
		}
	}

	// ����Ա��½����
	public static void adminLogin() {
		// �Ƿ�������践�����˵�
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
		// ����Ա�б�Ϊ�գ���½ʧ�ܣ��������˵�
		if (Data.adminList.size() == 0) {
			System.out.println("Error!");
			mainMenu();
		}
		// ����Ա��½��֤
		for (int counter = 0; counter < Data.adminList.size(); counter++) {
			if ((tempID1.compareTo(Data.adminList.get(counter).getUserName()) == 0)
					& (tempPW1.compareTo(Data.adminList.get(counter).getPassword()) == 0)) {
				System.out.println("Admin Login.");
				Data.isLogin = true;// ��½�����Ϊ��
				Data.isAdminLogin = true;// ����Ա��½�����Ϊ��
				Data.isPassengerLogin = false;// �˿͵�½�����Ϊ��
				Data.currentAdminIndex = counter;// ���µ�ǰ���߹���Ա���
				adminMenu();
				break;
			}
			// ��֤ʧ�ܷ������˵�
			else {
				System.out.print("Error!\n");
				mainMenu();
			}
		}
	}

	// ����Ա�˵�
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
		adminMenuIIP(protecter(7, in));// �Ƿ����뱣��
		int choice = protecter(7, in);// ��ȡ�Ϸ�����
		if (choice >= 0 & choice <= 6) {
			switch (choice) {
			case 0:
				Data.isAdminLogin = false;// ����Ա�ǳ�
				Data.currentAdminIndex = -1;// ��ǰ�޹���Ա����
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

	//���̣��Զ����º���״̬
	public static void updateFlight()
	{
		for(int counter = 0; counter < Data.flightList.size(); counter ++)
		{
			//�����ѷ���δ��ֹ�����״̬
			if(Data.flightList.get(counter).getEnumStatus() != FlightStatus.UNPUBLISHED & Data.flightList.get(counter).getEnumStatus() != FlightStatus.TERMINATE)
			{
				//��ǰ�˿�����������ʱ�����º���״̬Ϊ����
				if(Data.flightList.get(counter).getCurrentPassengers() == Data.flightList.get(counter).getSeatCapacity())
				{
					Data.flightList.get(counter).setFlightFull();
				}
				//��ǰ�˿�����������ʱ�����º���״̬Ϊ��Ч
				if(Data.flightList.get(counter).getCurrentPassengers() != Data.flightList.get(counter).getSeatCapacity())
				{
					Data.flightList.get(counter).setFlightAvailable();
				}
			}
			//���¼�����ɺ���Ϊ��ֹ
			//���¹��ں���Ϊ��ֹ
		}
	}
	
	// �˿͵�½����
	public static void passengerLogin() {
		// �Ƿ�������践�����˵�
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
		// ��ע��˿��б�Ϊ�գ���½ʧ�ܣ��������˵�
		if (Data.signUpPassengerList.size() == 0) {
			System.out.println("Error!");
			mainMenu();
		}
		// �˿͵�½��֤
		for (int counter = 0; counter < Data.signUpPassengerList.size(); counter++) {
			if ((tempID2.compareTo(Data.signUpPassengerList.get(counter).getIdentityID()) == 0)
					& (tempPW2.compareTo(Data.signUpPassengerList.get(counter).getPassword()) == 0)) {
				System.out.println("Passenger Login.");
				Data.isLogin = true;// ��½�����Ϊ��
				Data.isPassengerLogin = true;// �˿͵�½�����Ϊ��
				Data.isAdminLogin = false;// ����Ա��½�����Ϊ��
				Data.currentPassengerIndex = counter;// ���µ�ǰ���߳˿����
				normalQuery();// ��ʾһ���ѯ�˵�
				break;
			}
			// ��֤ʧ�ܣ��������˵�
			if ((!Data.isPassengerLogin) & counter == Data.signUpPassengerList.size() - 1) {
				System.out.print("Error!\n");
				mainMenu();
			}
		}
	}

	// �˿Ͳ˵�
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
		passengerMenuIIP(protecter(4, in));// �Ƿ����뱣��
		int choice = protecter(4, in);// ��ȡ�Ϸ�����
		if (choice >= 0 & choice <= 4) {
			switch (choice) {
			case 0:
				Data.isPassengerLogin = false;// �˿͵ǳ�
				Data.currentPassengerIndex = -1;// ��ǰ�޳˿�����
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
				Data.signUpPassengerList.get(Data.currentPassengerIndex).listAllOrder();// ��ȡ��ǰ�����û��г������ж���
				Data.signUpPassengerList.get(Data.currentPassengerIndex).isPaidForOrder();// ѯ�ʵ�ǰ�û��Ƿ�֧������
				passengerMenu();
				break;
			}
		} else {
			System.out.println("Request error, plese check your request.");
			adminMenu();
		}
	}

	// һ���ѯ
	public static void normalQuery() {
		starSepa(50);
		System.out.println("[Normal Query]");
		System.out.println("0:back to Mainmenu.");
		System.out.println("1:by start city.");
		System.out.println("2:by arrival city.");
		System.out.println("3:by departure date.");
		System.out.print("Plese enter the index:");
		String in = input.nextLine();
		normalQueryMenuIIP(protecter(3, in));// �Ƿ����뱣��
		int choice = protecter(3, in);// ��ȡ�Ϸ�����
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

	// �˿͵�һ���ѯ
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

	//����Ա��һ���ѯ
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
	
	// �����º���
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

	// ��ʾ�����������Ϣ
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

	// ��ʾ����Գ˿Ϳ��ŵ���Ϣ
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

	// ͨ������Ŷ�ȡ�ú����ں����б��ڵ����
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

	// ����:�޸ĺ�����Ϣ
	public static void adjustFlight() {
		Flight aimFlight = null;
		for (int index = 0; index < Data.flightList.size(); index++) // ��ʾ���к������Ϣ
		{
			starSepa(50);
			System.out.printf("Index:%d\n", index);
			listFlightInf(Data.flightList.get(index));// ��ʾ������Ϣ
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
		} else// ���뺽�಻���޸ģ����ع���Ա�˵�
		{
			System.out.println("Error!");
			adminMenu();
		}
	}

	// �������޸�δ�����������Ϣ
	public static void adjustUnpublishedFlight(Flight flight) {
		Flight adjustion = flight;// ����Ŀ�꺽�����޸İ�
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
		adjustUnpublishedFlightMenuIIP(flight, protecter(7, in));// �Ƿ����뱣��
		int choice = protecter(7, in);// ��ȡ�Ϸ�����
		switch (choice) {
		case 1:// �޸����ʱ��
			System.out.print("New Start time:");
			adjustion.setstartTime(input.nextLine());
			break;
		case 2:// �޸ĵ���ʱ��
			System.out.print("New Arrival time:");
			adjustion.setArrivalTime(input.nextLine());
			break;
		case 3:// �޸���ɳ���
			System.out.print("New Start city:");
			adjustion.setStartCity(input.nextLine());
			break;
		case 4:// �޸�Ŀ�����
			System.out.print("New Arrival city:");
			adjustion.setArrivalCity(input.nextLine());
			break;
		case 5:// �޸��������
			System.out.print("New Departure Date:");
			adjustion.setDepartureDate(input.nextLine());
			break;
		case 6:// �޸ļ۸�
			System.out.print("New Price:");
			adjustion.setPrice(input.nextInt());
			break;
		case 7:// �޸ĺ�������
			System.out.print("New Seat capacity:");
			adjustion.setSeatCapacity(input.nextInt());
			break;
		}
		int index = Data.flightList.indexOf(flight);
		Data.flightList.set(index, adjustion);
		System.out.println("Success!");
	}

	// �������޸��ѷ����������Ϣ
	public static void adjustPublishedFlight(Flight flight) {
		Flight adjustion = flight;// ����Ŀ�꺽�����޸İ�
		starSepa(50);
		System.out.println("\t[Adjust Flight Information]");
		System.out.println("1:Current price");
		System.out.println("2:Seat capacity");
		System.out.print("Which information you want to adjust:");
		String in = input.nextLine();
		adjustPublishedFlightMenuIIP(flight, protecter(1, in) + 1);// �Ƿ����뱣��
		int choice = protecter(1, in) + 1;// ��ȡ�Ϸ�����
		switch (choice) {
		case 1:// �޸ļ۸�
			System.out.print("New Price:");
			adjustion.setPrice(input.nextInt());
			break;
		case 2:// �޸ĺ�������
			System.out.print("New Seat capacity:");
			adjustion.setSeatCapacity(input.nextInt());
			break;
		}
		int index = Data.flightList.indexOf(flight);
		Data.flightList.set(index, adjustion);
		System.out.println("Success!");
	}

	// ͨ�������ɾ������
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
				Data.flightList.remove(index);// �Ӻ����б���Ƴ��ú���
				System.out.println("Success!");
			}
		}
	}

	// ͨ����ɳ��в�ѯ��ƥ������ʾ���࣬��ƥ���򱨴�
	public static void qByStartCity() {
		System.out.print("Plese enter the start city:");
		String sc = input.nextLine();
		boolean match = false;
		for (int counter = 0; counter < Data.flightList.size(); counter++) {
			Flight flight = Data.flightList.get(counter);
			if (flight.getEnumStatus() == FlightStatus.AVAILABLE)// ��Ч�ĺ���ɲ�
			{
				if (flight.getStartCity().compareTo(sc) == 0) {
					listFlightInf(Data.flightList.get(counter));// ��ʾƥ�亽��
					match = true;
				}
			}
		}
		if (!match) {
			System.out.printf("Error, flight start form %s do not exist.\n", sc);// ����
		}
	}

	// ͨ��Ŀ�ĳ��в�ѯ��ƥ������ʾ���࣬��ƥ���򱨴�
	public static void qByArrivalCity() {
		System.out.print("Plese enter the arrival city:");
		String ac = input.nextLine();
		boolean match = false;
		for (int counter = 0; counter < Data.flightList.size(); counter++) {
			Flight flight = Data.flightList.get(counter);
			if (flight.getEnumStatus() == FlightStatus.AVAILABLE)// ��Ч�ĺ���ɲ�
			{
				if (flight.getArrivalCity().compareTo(ac) == 0) {
					listFlightInf(Data.flightList.get(counter));// ��ʾƥ�亽��
					match = true;
				}
			}
		}
		if (!match) {
			System.out.printf("Error, flight go to %s do not exist.\n", ac);// ����
		}
	}

	// ͨ��������ڲ�ѯ��ƥ������ʾ���࣬��ƥ���򱨴�
	public static void qByDepartureDate() {
		System.out.print("Plese enter the departure date(yyyy mm dd):");
		String dd = input.nextLine();
		boolean match = false;
		for (int counter = 0; counter < Data.flightList.size(); counter++) {
			Flight flight = Data.flightList.get(counter);
			if (flight.getEnumStatus() == FlightStatus.AVAILABLE)// ��Ч�ĺ���ɲ�
			{
				if (flight.getDepartureDate().compareTo(dd) == 0) {
					listFlightInf(Data.flightList.get(counter));// ��ʾƥ�亽��
					match = true;
				}
			}
		}
		if (!match) {
			System.out.printf("Error, %s has no flight.\n", dd);// ����
		}
	}

	// ���̣��Ƿ�Ԥ��
	public static void isBooking() {
		System.out.print("Do you want to book?(yes/no)");
		String isBook = input.nextLine();
		if (isBook.toLowerCase().compareTo("yes") == 0) {
			if (Data.isPassengerLogin)// �˿��ѵ�¼������Ԥ��
			{
				// ͨ�������Ԥ��
				System.out.print("Enter the flight ID:");
				String bookFID = input.nextLine();
				if (getIndexByFID(bookFID) == -1) {
					System.out.println("Error, plese check again.");
					isBooking();
				} else {
					// ��ʾ��Ԥ���������Ϣ
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
							// �Դ˺���Ϊ��ǰ���߳˿Ͷ���
							Data.signUpPassengerList.get(Data.currentPassengerIndex)
									.createOrder(Data.flightList.get(getIndexByFID(bookFID)));
							System.out.println("Booking Success!");
						} 
						else {
							isBooking();// ѯ���Ƿ�Ԥ��
						}
					} 
				}
			} else// �˿�δ��¼��ת���˿͵�½����
			{
				System.out.println("Plese sign up and login as passenger and try agian.");
				passengerLogin();
			}
		} else {
			normalQuery();// ����һ���ѯ�˵�
		}
	}

	// �����ż��
	public static int checkOrderNumber(int orderNumber) {
		// �ж������Ƿ��ڵ�ǰ���߳˿͵Ķ�����С֮��
		if (orderNumber >= 0
				& orderNumber <= Data.signUpPassengerList.get(Data.currentPassengerIndex).getOrderListSize()) {
			return orderNumber;
		} else {
			return -1;
		}
	}

	// ���棺�Ƿ��˶�
	public static void isUnsubscribe() {
		System.out.print("Do you want to unsubscribe?(yes/no)");
		String isBook = input.nextLine();
		if (isBook.toLowerCase().compareTo("yes") == 0) {
			// ͨ���������˶�
			System.out.println("Enter the OrderNumber:");
			int UnsubscribeOrderNunmber = input.nextInt();
			input.nextLine();
			checkOrderNumber(UnsubscribeOrderNunmber);// ��������Ƿ�Ϸ�
			if (UnsubscribeOrderNunmber == -1) {
				System.out.println("Error, plese check again.");
				isUnsubscribe();// ѯ���Ƿ��˶�
			} else {
				// ��ʾ���˶��������Ϣ
				starSepa(50);
				System.out.println("Flight Information:");
				starSepa(50);
				// ��ȡ��ǰ���߳˿ʹ˶����Ŷ�Ӧ�ĺ���
				Flight aimFlight = Data.signUpPassengerList.get(Data.currentPassengerIndex)
						.getOrderFlight(UnsubscribeOrderNunmber);
				// ��ȡ��Ӧ�����ں����б�������
				int aimFID = getIndexByFID(aimFlight.getFlightID());
				// ��ʾ�ú�����Ϣ
				listFlightInf(Data.flightList.get(aimFID));
				starSepa(50);
				System.out.println("Is it you want to unsubscribe?(yes/no)");
				String isWant = input.nextLine();
				if (isWant.toLowerCase().compareTo("yes") == 0) {
					// �Ӷ����б���ɾ��
					Data.signUpPassengerList.get(Data.currentPassengerIndex).unsubscribeOrder(aimFlight);
					System.out.println("Booking Success!");
				} else {
					isUnsubscribe();// ѯ���Ƿ��˶�
				}
			}
		} else {
			normalQueryPSG();// ���س˿Ͳ˵�
		}
	}

	// ���̣���½�˿��Ƿ�Ԥ��
	public static void isBookingPSG() {
		System.out.print("Do you want to book?(yes/no)");
		String isBook = input.nextLine();
		if (isBook.toLowerCase() == "yes") {
			// ͨ�������Ԥ��
			System.out.println("Enter the flight ID:");
			String bookFID = input.nextLine();
			if (getIndexByFID(bookFID) == -1) {
				System.out.println("Error, plese check again.");
				isBookingPSG();
			} else {
				// ��ʾ��Ԥ���������Ϣ
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
						// �Դ˺���Ϊ��ǰ���߳˿Ͷ���
						Data.signUpPassengerList.get(Data.currentPassengerIndex)
								.createOrder(Data.flightList.get(getIndexByFID(bookFID)));
						System.out.println("Booking Success!");
					} 
					else {
						isBooking();// ѯ���Ƿ�Ԥ��
					}
				} 
			}
}
			else 
				{
			normalQueryPSG();// ���س˿Ͳ˵�
		}
	}

	// ��֤���֤��18λ�򷵻��棬���򷵻ؼ�
	public static boolean checkIdentityID(String identityID) {
		if (identityID.length() != 18) {
			return false;
		} else
			return true;
	}

	// �˿�ע�����
	public static void signUp() {
		starSepa(50);
		System.out.print("Plese enter your real name:");// ��ʵ����
		String tempRealName = input.nextLine();
		System.out.print("Plese enter your identity ID as your account:");// ���֤���˻���
		String tempIdentityID1 = input.nextLine();
		if (checkIdentityID(tempIdentityID1)) {
			System.out.print("Plese enter your identity ID agian:");// ���֤����ȷ��
			String tempIdentityID2 = input.nextLine();
			if (tempIdentityID1.compareTo(tempIdentityID2) == 0) {
				// ��������
				String tempPassword1 = new String();
				String tempPassword2 = new String();
				do {
					// �������ȷ��
					System.out.print("Plese create a password for your account:");
					tempPassword1 = input.nextLine();
					System.out.print("Plese enter the password again:");
					tempPassword2 = input.nextLine();
					if (tempPassword1.compareTo(tempPassword2) != 0) {
						System.out.println("Error! Plese enter the same password.");
					} else {
						Data.signUpPassengers++;// ��ע���û���+1
						// ����ǰע��˿�¼����ע��˿��б�
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
				mainMenu();// �������˵�
			}
		} else {
			System.out.println("Error! Plese check agian.(18 digit only)");
			mainMenu();// �������˵�
		}
	}

	// ��ʾ�˿���Ϣ
	public static void listPassenger(passenger passenger) {
		starSepa(50);
		System.out.printf("%s\n", passenger.getRealName());
		System.out.printf("Passenger ID��%d\n", passenger.getPassengerID());
		System.out.printf("Identity ID: %s\n", passenger.getIdentityID());
		starSepa(50);
	}

	// ��ʾδ�����ĺ���
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

	// ��ʾ����δ�����ĺ���
	public static void listAllUnpublishedFlight() {
		starSepa(50);
		System.out.println("All the unpublished flight were list below:");
		starSepa(50);
		for (int index = 0; index < Data.flightList.size(); index++) {
			System.out.printf("Number:%-3d%n", index + 1);
			listUnpublishedFlight(Data.flightList.get(index));
		}
	}

	// ��������
	public static void publishFlight() {
		starSepa(50);
		listAllUnpublishedFlight();// ��ʾ����δ��������
		System.out.print("Plese enter the Flight ID you want to publish:");
		String tempFID = input.nextLine();
		int index = getIndexByFID(tempFID);
		if (index == -1) {
			System.out.println("Flight ID error, plese check agian.");
		} else {
			System.out.printf("NOTE:Are you sure to publish Flight%s?(yes/no)", tempFID);
			String isSure = input.nextLine();
			if (isSure.compareTo("yes") == 0) {
				Data.flightList.get(index).publishFlight();// �޸�Ŀ�꺽��״̬Ϊ�ѷ���
				System.out.println("Success!");
			}
		}
	}

	// ��ȡ��ǰ����
	public static String getCurrentDate() {
		String formatDate = null;
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate();
		formatDate = "" + year + "-" + month + "-" + day;
		return formatDate;
	}

	// ��֤��������ƥ��
	public static int correctDay(int year, int month, int day) {
		if (year % 400 == 0 || ((year % 4 == 0) & (year % 100 == 0)))// �ж��Ƿ�Ϊ����
		{
			if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)// ����
			{
				day %= 31;
			} else if (month == 2)// ����
			{
				day %= 29;
			} else// С��
			{
				day %= 30;
			}
		} else {
			if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)// ����
			{
				day %= 31;
			} else if (month == 2)// ����
			{
				day %= 28;
			} else// С��
			{
				day %= 30;
			}
		}
		return day;
	}

	// ��ȡ���������
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

	// ��ȡһ�ܺ������
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

	// ��ȡ�¸��µĽ���
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

	// ��ȡ��ǰ����ʱ�䴮
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
	
	// ��ȡ��ǰСʱ�����
	public static String getCurrentTime() {
		String formatTime = null;
		Date date = new Date();
		int hour = date.getHours();
		int minute = date.getMinutes();
		formatTime = "" + hour + ":" + minute;
		return formatTime;
	}

	// �Ǻŷָ���������
	public static void starSepa(int width) {
		for (int counter = 1; counter <= width; counter++) {
			System.out.print("*");
			if (counter == width)
				System.out.println();
		}
	}

	// ���棺��������
	public static void superQuery()
	{
		for (int index = 0; index < Data.flightList.size(); index++) // ��ʾ���к������Ϣ
		{
			starSepa(50);
			System.out.printf("Index:%d\n", index);
			listFlightInf(Data.flightList.get(index));// ��ʾ������Ϣ
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

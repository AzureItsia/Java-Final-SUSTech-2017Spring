package FlightTicketReservationSystem;

import java.util.ArrayList;


public class Data {
	
	static boolean isPassengerLogin = false;//ȱʡ�˿��Ƿ��½���Ϊ��
	static boolean isAdminLogin = false;//ȱʡ����Ա�Ƿ��½���Ϊ��
	static int currentAdminIndex;//��ǰ���߹���Ա���
	static int currentPassengerIndex;//��ǰ���߳˿����
	static ArrayList<Admin> adminList = new ArrayList<Admin>();//��������Ա�б�
	static ArrayList<Flight> flightList = new ArrayList<Flight>();//���������б�
	static int signUpPassengers = 1;//ȱʡ����ע���û�����
	static ArrayList<Passenger> passengerList = new ArrayList<Passenger>();//������ע��˿��б�
	static String[] city = new String[3];
	static ArrayList<Order> orderList = new ArrayList<Order>();

}

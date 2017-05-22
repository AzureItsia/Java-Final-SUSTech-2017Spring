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
	private int currentPassengers;//��ǰ�˿���
	private int seatCapacity;
	private boolean[] seatList;
	private FlightStatus flightStatus = FlightStatus.UNPUBLISHED;
	private ArrayList<Passenger> passengersList = new ArrayList<Passenger>();//������ǰ����˿��б�
	
	Flight()
	{
		createSeatList();
		initializeSeatList();//��λ�����������
	}
	
	//���ú�����
	void setFlightID(String newFlightID)
	{
		flightID = newFlightID;
	}
	//��ȡ������
	String getFlightID()
	{
		return flightID;
	}
	//�������ʱ��
	void setStartTime(String newStartTime)
	{
		startTime = newStartTime;
	}
	//��ȡ���ʱ��
	String getStartTime()
	{
		return startTime;
	}
	//���õ���ʱ��
	void setArrivalTime(String newArrivalTime)
	{
		arrivalTime = newArrivalTime;
	}
	//��ȡ����ʱ��
	String getArrivalTime()
	{
		return arrivalTime;
	}
	//������ɳ���
	void setStartCity(String newStartCity)
	{
		startCity = newStartCity;
	}
	//��ȡ��ɳ���
	String getStartCity()
	{
		return startCity;
	}
	//����Ŀ�ĳ���
	void setArrivalCity(String newArrivalCity)
	{
		arrivalCity = newArrivalCity;
	}
	//��ȡĿ�ĳ���
	String getArrivalCity()
	{
		return arrivalCity;
	}
	//�����������
	void setDepartureDate(String newDepartureDate)
	{
		departureDate = newDepartureDate;
	}
	//��ȡ�������
	String getDepartureDate()
	{
		return departureDate;
	}
	//���ü۸�
	void setPrice(int newPrice)
	{
		price = newPrice;
	}
	//��ȡ�۸�
	int getPrice()
	{
		return price;
	}
	//���µ�ǰ�˿���
	void updataCurrentPassengers()
	{
		currentPassengers = passengersList.size();
	}
	//��ȡ��ǰ�˿���
	int getCurrentPassengers()
	{
		return currentPassengers;
	}
	//���ú�������
	void setSeatCapacity(int newSeatCapacity)
	{
		seatCapacity = newSeatCapacity;
	}
	//��ȡ��������
	int getSeatCapacity()
	{
		return seatCapacity;
	}
	//���ú���״̬Ϊδ����
	void setFlightUNPUBLISHED()
	{
		flightStatus = FlightStatus.UNPUBLISHED;
	}
	//���ú���״̬Ϊ��Ч
	void setFlightAVAILABLE()
	{
		flightStatus = FlightStatus.AVAILABLE;
	}
	//���ú���״̬Ϊ��ֹ
	void setFlightTERMINATE()
	{
		flightStatus = FlightStatus.TERMINATE;
	}
	//���ú���Ϊ����
	void setFlightFULL()
	{
		flightStatus = FlightStatus.FULL;
	}
	//��ȡ����״̬������ö��ֵ
	FlightStatus getStatus()
	{
		return flightStatus;
	}
	//���ӳ˿����б�
	void addPassenger(Passenger newPassenger)
	{
		passengersList.add(newPassenger);
	}
	//��ȡ�˿�
	Passenger getPassenger(int index)
	{
		return passengersList.get(index);
	}
	//���б��Ƴ��˿�
	void deletePassenger(Passenger aimPassenger)
	{
		passengersList.remove(aimPassenger);
	}
	//��ȡʣ����λ��
	int getAvailableSeat()
	{
		updataCurrentPassengers();
		return seatCapacity - currentPassengers;
	}
	//������λ�б�
	void createSeatList()
	{
		seatList = new boolean[seatCapacity];
	}
	//��ʼ����λ�б�
	void initializeSeatList()
	{
		//������λ�б�����������λΪ��
		for(int counter = 0; counter < seatList.length ; counter ++)
		{
			seatList[counter] = false;
		}
	}
	//�ͷ���λ
	void releaseSeat(int seatNumber)
	{
		seatList[seatNumber] = false;
	}
	//ռ����λ
	void occupySeat(int seatNumber)
	{
		seatList[seatNumber] = true;
	}
	//��ȡ��λ��
	int getSeatNumbers()
	{
		return seatList.length;
	}
	//��ȡ����λ���
	boolean getSeatStatus(int seatNumber)
	{
		return seatList[seatNumber];
	}
}

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
	private static int currentPassengers = 0;//��ǰ�˿���
	private int seatCapacity;
	private int availableSeat;
	private int[] seat = new int[seatCapacity];
	private FlightStatus flightStatus = FlightStatus.UNPUBLISHED;//ȱʡ����״̬Ϊδ����
	private ArrayList<passenger> passengersList = new ArrayList<passenger>();//������ǰ����˿��б�
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
	void setstartTime(String newStartTime)
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
	//��ǰ�˿���+1
	void currentPassengersPlus1()
	{
		currentPassengers++;
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
	//�����ɻ�
	void publishFlight()
	{
		flightStatus = FlightStatus.AVAILABLE;
	}
	//��ȡ����״̬�������ַ���
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
	//��ȡ����״̬������ö��ֵ
	FlightStatus getEnumStatus()
	{
		return flightStatus;
	}
	//���ú���Ϊ��Ч
	void setFlightAvailable()
	{
		flightStatus = FlightStatus.AVAILABLE;
	}
	//���ú���Ϊ����
	void setFlightFull()
	{
		flightStatus = FlightStatus.FULL;
	}
	//���ú���Ϊ��ֹ
	void setFlightTerminate()
	{
		flightStatus = FlightStatus.TERMINATE;
	}
	//���ӳ˿�
	void addPassenger(passenger newPassenger)
	{
		passengersList.add(newPassenger);
	}
	//ɾ���˿�
	void deletePassenger(passenger aimPassenger)
	{
		passengersList.remove(aimPassenger);
	}
	//��ʼ����λ��
	void initializeSeat()
	{
		for(int counter = 0; counter < seat.length; counter++)
		{
			seat[counter] = 0;//0����û����
		}
	}
	//��ȡʣ����λ��
	int getAvailableSeat()
	{
		int rest = seatCapacity - currentPassengers;
		return rest;
	}
	//�ҵ���һ������λ
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
	//������λ
	int arrangeSeat()
	{
		int seatNum = getFirstAvaSeat();
		if(seatNum != -1)
		{
			seat[seatNum] = 1;
		}
		return seatNum;
	}
	//��ȡ�˿�
	passenger getPassenger(int index)
	{
		return passengersList.get(index);
	}
}

package TiketReserveSystem;

import java.util.ArrayList;

public class Run 
{
	public static void main(String[] args)
	{
		function.initialize();// ��ʼ��ϵͳ
		while(!Data.isLogin)//�����˵�½ʱ����ʾ���˵�
			function.mainMenu();
	}
}
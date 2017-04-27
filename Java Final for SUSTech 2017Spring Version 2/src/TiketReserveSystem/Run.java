package TiketReserveSystem;

import java.util.ArrayList;

public class Run 
{
	public static void main(String[] args)
	{
		function.initialize();// 初始化系统
		while(!Data.isLogin)//当无人登陆时，显示主菜单
			function.mainMenu();
	}
}
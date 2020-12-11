package question1.tryit;
import java.util.*;

import question1.sold.West2FriedChickenRestaurant;

public class tryit {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("欢迎您来到西二炸鸡店");
		System.out.println("请先设置本金");
		// 获取最初余额值
		double balance=sc.nextDouble();
		West2FriedChickenRestaurant restaurant=new West2FriedChickenRestaurant(balance);
		System.out.println("设置成功！");
		
		//记录用户操作
		int choose=0;
		boolean loopFlag=true;
		while (loopFlag) {
			String menuList ="您可以进行：\n";
			menuList += "1.点餐\n";
			menuList += "2.查看库存\n";
			menuList += "3.开始进货\n";
			menuList += "4.查看餐厅余额\n";
			menuList += "5.离开\n";
			menuList += "请选择您所需要的服务：";

			System.out.println(menuList);
			try {
				choose = sc.nextInt();
			} catch (Exception e1) {
				
				System.out.println("无法识别");
			}

			switch (choose) {
			// 点餐
			case 1:
				try {
					restaurant.sellMeal();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} 
				break;
			// 查看库存
			case 2:
				restaurant.VeiwInventory();
				break;
 			// 开始进货
			case 3:
				try {
					restaurant.purchase();
				} catch (Exception e) {
					
					System.out.println(e.getMessage());
				}
				break;
 			// 查看餐厅余额
			case 4:
				System.out.println("当前余额："+restaurant.getBalance()+"元");
				break;
			// 离开
			case 5:
				System.out.println("确认退出请输 Y/y，继续其他操作请输 N/n");
				char isExit=sc.next().charAt(0);
				if(isExit=='Y'||isExit=='y') {
					loopFlag=false;
					System.out.println("欢迎下次光临！");
				}
				break;
			default:
				System.out.println("无法识别，请重新输入");
				break;
			}
		}		
	}

}


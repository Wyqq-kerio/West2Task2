package question1.tryit;
import java.util.*;

import question1.sold.West2FriedChickenRestaurant;

public class tryit {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("��ӭ����������ը����");
		System.out.println("�������ñ���");
		// ��ȡ������ֵ
		double balance=sc.nextDouble();
		West2FriedChickenRestaurant restaurant=new West2FriedChickenRestaurant(balance);
		System.out.println("���óɹ���");
		
		//��¼�û�����
		int choose=0;
		boolean loopFlag=true;
		while (loopFlag) {
			String menuList ="�����Խ��У�\n";
			menuList += "1.���\n";
			menuList += "2.�鿴���\n";
			menuList += "3.��ʼ����\n";
			menuList += "4.�鿴�������\n";
			menuList += "5.�뿪\n";
			menuList += "��ѡ��������Ҫ�ķ���";

			System.out.println(menuList);
			try {
				choose = sc.nextInt();
			} catch (Exception e1) {
				
				System.out.println("�޷�ʶ��");
			}

			switch (choose) {
			// ���
			case 1:
				try {
					restaurant.sellMeal();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} 
				break;
			// �鿴���
			case 2:
				restaurant.VeiwInventory();
				break;
 			// ��ʼ����
			case 3:
				try {
					restaurant.purchase();
				} catch (Exception e) {
					
					System.out.println(e.getMessage());
				}
				break;
 			// �鿴�������
			case 4:
				System.out.println("��ǰ��"+restaurant.getBalance()+"Ԫ");
				break;
			// �뿪
			case 5:
				System.out.println("ȷ���˳����� Y/y������������������ N/n");
				char isExit=sc.next().charAt(0);
				if(isExit=='Y'||isExit=='y') {
					loopFlag=false;
					System.out.println("��ӭ�´ι��٣�");
				}
				break;
			default:
				System.out.println("�޷�ʶ������������");
				break;
			}
		}		
	}

}


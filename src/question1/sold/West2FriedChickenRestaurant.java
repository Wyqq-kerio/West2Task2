package question1.sold;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

import question1.sold.IngredientSortOutException;
import question1.sold.OverdraftBalanceException;
import question1.goods.*;

public class West2FriedChickenRestaurant implements FriedChickenRestaurant {  // ʵ��ը����ӿ�

	// �������
	private double balance;
	private  ArrayList <Beer> beerList;
	private  ArrayList <Juice> juiceList;
	private final static ArrayList <SetMeal> setMealList;
	
	/* ѡ��Arraylist��ԭ��
	   1�������±����Ԫ��Ч�ʽϸߡ�
       2�������±����Ԫ��Ч�ʽϸߡ�
       3��������Ļ����Ϸ�װ�˶�Ԫ�ز����ķ�����
       4�������Զ����ݡ� 
    */

	public West2FriedChickenRestaurant() {
		super();
		this.balance = 0.0;
		this.beerList = new ArrayList<Beer>();
		this.juiceList = new ArrayList<Juice>();
	}
	public West2FriedChickenRestaurant(double balance) {
		super();
		this.balance = balance;
		this.beerList = new ArrayList<Beer>();
		this.juiceList = new ArrayList<Juice>();
	}

	static {  // ���ײ��б���г�ʼ��
		setMealList=new ArrayList<SetMeal>();
		setMealList.add(new SetMeal("�ײ�1", 25, "Aը��", new Beer("Xơ��", 8, LocalDate.now(), 3.1f)));
		setMealList.add(new SetMeal("�ײ�2", 26, "Bը��", new Beer("Yơ��", 9, LocalDate.now(), 3.6f)));
		setMealList.add(new SetMeal("�ײ�3", 20, "Cը��", new Juice("M��֭", 5, LocalDate.now())));
		setMealList.add(new SetMeal("�ײ�4", 21, "Dը��", new Juice("N��֭", 6, LocalDate.now())));
	}


	public void use(Beer beer) {  // ����use����,����Beer����
		// �Ƴ�����ơ��
		if(beerList!=null) {
			for(Beer beer1:beerList) {
				if(beer1.checkOverDue()) {
					beerList.remove(beer1);
				}
			}
		}
		// �ж��Ƿ��ж�Ӧ��ơ��
		boolean found=false;		
		if(beerList!=null) {
			for(Beer oneBeer:beerList) {
				// ��ѡ����۵�һ������������,���Ƴ�
				if(oneBeer.getDName().equals(beer.getDName())) {
					beerList.remove(oneBeer);
					found=true;
					break;
				}
			}
		}
		//  û���ҵ����׳��쳣
		if(!found) {
			String msg="û��"+beer.getDName()+"�ˣ�������˼��";
			throw new IngredientSortOutException(msg);
		}
	}

	public void use(Juice juice) {  // ����use����,����Juice����
		// �Ƴ����ڹ�֭
		if(juiceList!=null) {
			for(Juice juice1:juiceList) {
				if(juice1.checkOverDue()) {
					juiceList.remove(juice1);
				}
			}
		}
		// �ж��Ƿ��ж�Ӧ��֭
		boolean found=false;	
		if(juiceList!=null) {
			for(Juice oneJuice:juiceList) {
				if(oneJuice.getDName().equals(juice.getDName())) {
					// ��ѡ����۵�һ������������,���Ƴ�
					juiceList.remove(oneJuice);
					found=true;
					break;
				}
			}
		}
		// û���ҵ����׳��쳣
		if(!found) {
			String msg="û��"+juice.getDName()+"�ˣ�������˼��";
			throw new IngredientSortOutException(msg);
		}
	}

	public void use(String setMealName ) {
		// �ж��Ƿ��ж�Ӧ�ײ�
		boolean found=false;
		SetMeal choose = null;
		if(setMealList!=null) {
			for(SetMeal meal:setMealList) {
				if(meal.getMName().equals(setMealName)) {
					found=true;
					choose=meal;
					break;
				}
			}
		}
		
		if(found) {
			// �ҵ����ڳ����ײ�ͬʱ�Ƴ���Ӧ������
			boolean checkDrinks=true;
			Drinks dType=choose.getDType();
			try {
				// ѡ����۵�һ������������
				// �����ơ��
				if(dType instanceof Beer) {
					Beer beer=(Beer)dType;
					use(beer);
				}else if(dType instanceof Drinks) {
				// ����ǹ�֭
					Juice juice=(Juice)dType;
					use(juice);
				}
			} catch (Exception e) {
				checkDrinks=false;
				throw new IngredientSortOutException("û�������ˣ�������˼��");
			}
			
			if(checkDrinks) {
				balance+=choose.getMPrice();
				System.out.println("����ɹ���ף���ò����!");
			}
		}else{
			// û���ҵ����׳��쳣
			String msg="û��"+setMealName+"�ˣ�������˼��";
			throw new IngredientSortOutException(msg);
		}
		
	}

	// ��������
	public void sellMeal() {		
		System.out.println("��ã���ѡ����Ҫ������ײ�:");			
		for(SetMeal meal:setMealList) {
			System.out.println(meal);
		}
		Scanner sc = new Scanner(System.in);
		
		int choose=0;
		try {
			choose = sc.nextInt();
		} catch (Exception e1) {
			throw new RuntimeException("�޷�ʶ������������");
		}
		
		String chooseName="�ײ�"+choose;
		
		try {
			use(chooseName);
		} catch (Exception e) {
			throw new RuntimeException("��������ײͲ����ڣ������ۿա�");
		}
		
	}

	// ��������
	public void purchase() {
		Scanner sc=new Scanner(System.in);
		while(true) {

			System.out.println("��ã���ѡ������Ҫ��������Ʒ:");
			System.out.println("1.����\n2.��֭\n3.�뿪");
			int choose=0;
			try {
				choose = sc.nextInt();
			} catch (Exception e1) {
				throw new RuntimeException("�޷�ʶ������������");
			}
			switch (choose) {
//			-----------------------------------------------------------------------
			case 1:
				System.out.println("������ࡪ����ѡ��ơ������");
				System.out.println("ơ��X / ơ��Y");
				System.out.println("������1��ʾXơ�ƣ�����2��ʾYơ��");
				int dtype1=sc.nextInt();
					switch (dtype1) {
					
					// ����ơ��X
					case 1:
						System.out.println("������Ҫ���������:");
						try {
							int count1=sc.nextInt();
				
					    // ���㹺��Ԥ�ƻ���,���������Ƿ��㹻
						double expectMoney1=count1*6;
						if(balance-expectMoney1>=0) {
							balance-=expectMoney1;
						}else {
								double overBudget=-(balance-expectMoney1);
								throw new OverdraftBalanceException("��֧�ˣ�����"+overBudget+"Ԫ.");
						}
						// ѡ����Ʒ
						int i1=0;
						System.out.println("��������Ҫ�������Ʒ����������");
						System.out.println("���������ꡢ�¡��գ�");
						int year=sc.nextInt();
						int month=sc.nextInt();
						int day=sc.nextInt();
						beerList.add(new Beer("Xơ��", 8, LocalDate.of(year, month, day), 3.1f));
						count1--;
						System.out.println("����ɹ���");
						}catch(Exception e) {
							throw new RuntimeException("�޷�ʶ��");
						}
						break;
					// ����ơ��Y
					case 2:
						System.out.println("������Ҫ�����������");
						try {
							int count2=sc.nextInt();
						
					    // ���㹺��Ԥ�ƻ���,���������Ƿ��㹻
						double expectMoney2=count2*7;
						if(balance-expectMoney2>=0) {
							balance-=expectMoney2;
						}else {
								double overBudget=-(balance-expectMoney2);
								throw new OverdraftBalanceException("��֧�ˣ�����"+overBudget+"Ԫ.");
						}
						
						// ѡ����Ʒ
						System.out.println("��������Ҫ�������Ʒ����������");
						System.out.println("���������ꡢ�¡��գ�");
						int year=sc.nextInt();
						int month=sc.nextInt();
						int day=sc.nextInt();
						beerList.add(new Beer("Yơ��", 9, LocalDate.of(year, month, day), 3.6f));
						count2--;
						System.out.println("����ɹ���");
						}catch(Exception e) {
							throw new RuntimeException("�޷�ʶ��");
						}
						break;
						
					default:
						System.out.println("����ʧ�ܣ�����������");
						break;
					}
					
					break;
//-------------------------------------------------------------------------------------
					// �����֭
					case 2:
						System.out.println("�����֭������ѡ���֭����");
						System.out.println("M��֭ / N��֭");
						System.out.println("������1��ʾM��֭������2��ʾN��֭");
						int dtype2=sc.nextInt();
						
							switch (dtype2) {
			
							// �����֭M
							case 1:
								System.out.println("������Ҫ���������:");
								try {
									int count1=sc.nextInt();
									
								// ���㹺��Ԥ�ƻ���,���������Ƿ��㹻
								double expectMoney1=count1*4;
								if(balance-expectMoney1>=0) {
									balance-=expectMoney1;
								}else {

									double overBudget=-(balance-expectMoney1);
									throw new OverdraftBalanceException("��֧�ˣ�����"+overBudget+"Ԫ.");
								}
			
								// ѡ����Ʒ
 								System.out.println("��������Ҫ�������Ʒ����������");
								System.out.println("���������ꡢ�¡��գ�");
								int year=sc.nextInt();
								int month=sc.nextInt();
								int day=sc.nextInt();
								juiceList.add(new Juice("M��֭", 5, LocalDate.of(year, month, day)));
								count1--;
								System.out.println("����ɹ���");
								}catch(Exception e) {
									throw new RuntimeException("�޷�ʶ��");
								}
								break;
								
							// �����֭N
							case 2:
								System.out.println("������Ҫ���������:");
								try {
									
								int count2=sc.nextInt();
								
								// ���㹺��Ԥ�ƻ���,���������Ƿ��㹻
								double expectMoney2=count2*5;
								if(balance-expectMoney2>=0) {
									balance-=expectMoney2;
								}else {
									double overBudget=-(balance-expectMoney2);
									throw new OverdraftBalanceException("��֧�ˣ�����"+overBudget+"Ԫ.");
								}
								
//								// ѡ����Ʒ��������
 								System.out.println("��������Ҫ�������Ʒ����������");
								System.out.println("���������ꡢ�¡��գ�");
								int year=sc.nextInt();
								int month=sc.nextInt();
								int day=sc.nextInt();
								juiceList.add(new Juice("M��֭", 6, LocalDate.of(year, month, day)));
								count2--;

								System.out.println("����ɹ���");
								}catch(Exception e) {
									throw new RuntimeException("�޷�ʶ��");
								}
								break;
								
							default:
								System.out.println("����ʧ�ܣ�����������");
								break;
							}
							
							break;



			default:
				System.out.println("�޷�ʶ��");
				break;
			}
			System.out.println("��ѡ�� 1.�������� 2.�뿪");
			int choose1=sc.nextInt();
			switch (choose1) {
			case 1:
				//��������
				break;
			case 2:
				System.out.println("��������!");
				return;
			default:
				System.out.println("�޷�ʶ������������");
				break;
			}
		}
		
	}

    // �鿴���
	public double getBalance() {
		return balance;
	}

	// �鿴���
	public void VeiwInventory () {
		int XCount=0,YCount=0,MCount=0,NCount=0;
		for(Beer beer:beerList) {
			String name=beer.getDName();
			switch (name) {
			case "Xơ��":
				XCount++;
				break;
			case "Yơ��":
				YCount++;
				break;
			default:
				break;
			}

		}
		for(Juice juice:juiceList) {
			String name=juice.getDName();
			switch (name) {
			case "M��֭":
				MCount++;
				break;
			case "N��֭":
				NCount++;
				break;
			default:
				break;
			}
		

	}
		System.out.println("�ײ�1����"+XCount+"�ס�");
		System.out.println("�ײ�2����"+YCount+"�ס�");
		System.out.println("�ײ�3����"+MCount+"�ס�");
		System.out.println("�ײ�4����"+NCount+"�ס�");
		
}

}
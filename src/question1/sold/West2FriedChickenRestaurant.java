package question1.sold;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

import question1.sold.IngredientSortOutException;
import question1.sold.OverdraftBalanceException;
import question1.goods.*;

public class West2FriedChickenRestaurant implements FriedChickenRestaurant {  // 实现炸鸡店接口

	// 定义变量
	private double balance;
	private  ArrayList <Beer> beerList;
	private  ArrayList <Juice> juiceList;
	private final static ArrayList <SetMeal> setMealList;
	
	/* 选用Arraylist的原因：
	   1、根据下标遍历元素效率较高。
       2、根据下标访问元素效率较高。
       3、在数组的基础上封装了对元素操作的方法。
       4、可以自动扩容。 
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

	static {  // 对套餐列表进行初始化
		setMealList=new ArrayList<SetMeal>();
		setMealList.add(new SetMeal("套餐1", 25, "A炸鸡", new Beer("X啤酒", 8, LocalDate.now(), 3.1f)));
		setMealList.add(new SetMeal("套餐2", 26, "B炸鸡", new Beer("Y啤酒", 9, LocalDate.now(), 3.6f)));
		setMealList.add(new SetMeal("套餐3", 20, "C炸鸡", new Juice("M果汁", 5, LocalDate.now())));
		setMealList.add(new SetMeal("套餐4", 21, "D炸鸡", new Juice("N果汁", 6, LocalDate.now())));
	}


	public void use(Beer beer) {  // 重载use函数,接受Beer参数
		// 移除过期啤酒
		if(beerList!=null) {
			for(Beer beer1:beerList) {
				if(beer1.checkOverDue()) {
					beerList.remove(beer1);
				}
			}
		}
		// 判断是否有对应的啤酒
		boolean found=false;		
		if(beerList!=null) {
			for(Beer oneBeer:beerList) {
				// 在选择出售第一个符合条件的,并移除
				if(oneBeer.getDName().equals(beer.getDName())) {
					beerList.remove(oneBeer);
					found=true;
					break;
				}
			}
		}
		//  没有找到则抛出异常
		if(!found) {
			String msg="没有"+beer.getDName()+"了，不好意思。";
			throw new IngredientSortOutException(msg);
		}
	}

	public void use(Juice juice) {  // 重载use函数,接受Juice参数
		// 移除过期果汁
		if(juiceList!=null) {
			for(Juice juice1:juiceList) {
				if(juice1.checkOverDue()) {
					juiceList.remove(juice1);
				}
			}
		}
		// 判断是否有对应果汁
		boolean found=false;	
		if(juiceList!=null) {
			for(Juice oneJuice:juiceList) {
				if(oneJuice.getDName().equals(juice.getDName())) {
					// 在选择出售第一个符合条件的,并移除
					juiceList.remove(oneJuice);
					found=true;
					break;
				}
			}
		}
		// 没有找到则抛出异常
		if(!found) {
			String msg="没有"+juice.getDName()+"了，不好意思。";
			throw new IngredientSortOutException(msg);
		}
	}

	public void use(String setMealName ) {
		// 判断是否有对应套餐
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
			// 找到则在出售套餐同时移除对应的饮料
			boolean checkDrinks=true;
			Drinks dType=choose.getDType();
			try {
				// 选择出售第一个符合条件的
				// 如果是啤酒
				if(dType instanceof Beer) {
					Beer beer=(Beer)dType;
					use(beer);
				}else if(dType instanceof Drinks) {
				// 如果是果汁
					Juice juice=(Juice)dType;
					use(juice);
				}
			} catch (Exception e) {
				checkDrinks=false;
				throw new IngredientSortOutException("没有饮料了，不好意思。");
			}
			
			if(checkDrinks) {
				balance+=choose.getMPrice();
				System.out.println("购买成功，祝您用餐愉快!");
			}
		}else{
			// 没有找到则抛出异常
			String msg="没有"+setMealName+"了，不好意思。";
			throw new IngredientSortOutException(msg);
		}
		
	}

	// 进行售卖
	public void sellMeal() {		
		System.out.println("你好，请选择您要购买的套餐:");			
		for(SetMeal meal:setMealList) {
			System.out.println(meal);
		}
		Scanner sc = new Scanner(System.in);
		
		int choose=0;
		try {
			choose = sc.nextInt();
		} catch (Exception e1) {
			throw new RuntimeException("无法识别，请重新输入");
		}
		
		String chooseName="套餐"+choose;
		
		try {
			use(chooseName);
		} catch (Exception e) {
			throw new RuntimeException("您输入的套餐不存在，或已售空。");
		}
		
	}

	// 批量进货
	public void purchase() {
		Scanner sc=new Scanner(System.in);
		while(true) {

			System.out.println("你好，请选择您需要进货的商品:");
			System.out.println("1.酒类\n2.果汁\n3.离开");
			int choose=0;
			try {
				choose = sc.nextInt();
			} catch (Exception e1) {
				throw new RuntimeException("无法识别，请重新输入");
			}
			switch (choose) {
//			-----------------------------------------------------------------------
			case 1:
				System.out.println("购买酒类——请选择啤酒类型");
				System.out.println("啤酒X / 啤酒Y");
				System.out.println("请输入1表示X啤酒，输入2表示Y啤酒");
				int dtype1=sc.nextInt();
					switch (dtype1) {
					
					// 购买啤酒X
					case 1:
						System.out.println("请输入要购买的数量:");
						try {
							int count1=sc.nextInt();
				
					    // 计算购买预计花费,并检查余额是否足够
						double expectMoney1=count1*6;
						if(balance-expectMoney1>=0) {
							balance-=expectMoney1;
						}else {
								double overBudget=-(balance-expectMoney1);
								throw new OverdraftBalanceException("超支了，还差"+overBudget+"元.");
						}
						// 选择商品
						int i1=0;
						System.out.println("请输入您要购买的商品的生产日期");
						System.out.println("依次输入年、月、日：");
						int year=sc.nextInt();
						int month=sc.nextInt();
						int day=sc.nextInt();
						beerList.add(new Beer("X啤酒", 8, LocalDate.of(year, month, day), 3.1f));
						count1--;
						System.out.println("购买成功！");
						}catch(Exception e) {
							throw new RuntimeException("无法识别");
						}
						break;
					// 购买啤酒Y
					case 2:
						System.out.println("请输入要购买的数量：");
						try {
							int count2=sc.nextInt();
						
					    // 计算购买预计花费,并检查余额是否足够
						double expectMoney2=count2*7;
						if(balance-expectMoney2>=0) {
							balance-=expectMoney2;
						}else {
								double overBudget=-(balance-expectMoney2);
								throw new OverdraftBalanceException("超支了，还差"+overBudget+"元.");
						}
						
						// 选择商品
						System.out.println("请输入您要购买的商品的生产日期");
						System.out.println("依次输入年、月、日：");
						int year=sc.nextInt();
						int month=sc.nextInt();
						int day=sc.nextInt();
						beerList.add(new Beer("Y啤酒", 9, LocalDate.of(year, month, day), 3.6f));
						count2--;
						System.out.println("购买成功！");
						}catch(Exception e) {
							throw new RuntimeException("无法识别");
						}
						break;
						
					default:
						System.out.println("购买失败，请重新输入");
						break;
					}
					
					break;
//-------------------------------------------------------------------------------------
					// 购买果汁
					case 2:
						System.out.println("购买果汁——请选择果汁类型");
						System.out.println("M果汁 / N果汁");
						System.out.println("请输入1表示M果汁，输入2表示N果汁");
						int dtype2=sc.nextInt();
						
							switch (dtype2) {
			
							// 购买果汁M
							case 1:
								System.out.println("请输入要购买的数量:");
								try {
									int count1=sc.nextInt();
									
								// 计算购买预计花费,并检查余额是否足够
								double expectMoney1=count1*4;
								if(balance-expectMoney1>=0) {
									balance-=expectMoney1;
								}else {

									double overBudget=-(balance-expectMoney1);
									throw new OverdraftBalanceException("超支了，还差"+overBudget+"元.");
								}
			
								// 选择商品
 								System.out.println("请输入您要购买的商品的生产日期");
								System.out.println("依次输入年、月、日：");
								int year=sc.nextInt();
								int month=sc.nextInt();
								int day=sc.nextInt();
								juiceList.add(new Juice("M果汁", 5, LocalDate.of(year, month, day)));
								count1--;
								System.out.println("购买成功！");
								}catch(Exception e) {
									throw new RuntimeException("无法识别");
								}
								break;
								
							// 购买果汁N
							case 2:
								System.out.println("请输入要购买的数量:");
								try {
									
								int count2=sc.nextInt();
								
								// 计算购买预计花费,并检查余额是否足够
								double expectMoney2=count2*5;
								if(balance-expectMoney2>=0) {
									balance-=expectMoney2;
								}else {
									double overBudget=-(balance-expectMoney2);
									throw new OverdraftBalanceException("超支了，还差"+overBudget+"元.");
								}
								
//								// 选择商品生产日期
 								System.out.println("请输入您要购买的商品的生产日期");
								System.out.println("依次输入年、月、日：");
								int year=sc.nextInt();
								int month=sc.nextInt();
								int day=sc.nextInt();
								juiceList.add(new Juice("M果汁", 6, LocalDate.of(year, month, day)));
								count2--;

								System.out.println("购买成功！");
								}catch(Exception e) {
									throw new RuntimeException("无法识别");
								}
								break;
								
							default:
								System.out.println("购买失败，请重新输入");
								break;
							}
							
							break;



			default:
				System.out.println("无法识别");
				break;
			}
			System.out.println("请选择 1.继续进货 2.离开");
			int choose1=sc.nextInt();
			switch (choose1) {
			case 1:
				//继续进货
				break;
			case 2:
				System.out.println("进货结束!");
				return;
			default:
				System.out.println("无法识别，请重新输入");
				break;
			}
		}
		
	}

    // 查看余额
	public double getBalance() {
		return balance;
	}

	// 查看库存
	public void VeiwInventory () {
		int XCount=0,YCount=0,MCount=0,NCount=0;
		for(Beer beer:beerList) {
			String name=beer.getDName();
			switch (name) {
			case "X啤酒":
				XCount++;
				break;
			case "Y啤酒":
				YCount++;
				break;
			default:
				break;
			}

		}
		for(Juice juice:juiceList) {
			String name=juice.getDName();
			switch (name) {
			case "M果汁":
				MCount++;
				break;
			case "N果汁":
				NCount++;
				break;
			default:
				break;
			}
		

	}
		System.out.println("套餐1现有"+XCount+"套。");
		System.out.println("套餐2现有"+YCount+"套。");
		System.out.println("套餐3现有"+MCount+"套。");
		System.out.println("套餐4现有"+NCount+"套。");
		
}

}
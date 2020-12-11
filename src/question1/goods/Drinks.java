package question1.goods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public abstract class Drinks {  // 抽象类
	
    // 定义变量
	protected String dName;
	protected double cost;
	protected LocalDate produceDate;
	protected int period;	
	
	public Drinks() {
		super();
	}
	
	// 全参构造函数
	public Drinks(String dName, double cost, LocalDate produceDate, int period) {
		super();
		this.dName = dName;
		this.cost = cost;
		this.produceDate = produceDate;
		this.period = period;
	}
	
    // 获取饮料的名字
	public String getDName() {
		return dName;
	}
	// 设置饮料的名字
	public void setDName(String s) {
		 dName=s;
	}
    
	// 抽象方法toString
	public abstract String toString();

    // 判断是否过期
	public boolean checkOverDue() {
		DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start=LocalDate.parse(produceDate+"",format);
		LocalDate end=LocalDate.parse(LocalDate.now()+"",format);
		long time=start.until(end, ChronoUnit.DAYS);
		
		if(time>period) {
			return true;
		}
		return false;
		
	}

}
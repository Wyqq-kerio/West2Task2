package question1.goods;

import java.time.LocalDate;

public class Beer extends Drinks{  // 继承自饮料类
	
	// 变量酒精度数
	private float aDegree;
	
	public Beer() {
		super();
	}
	
	public Beer(String dName, double cost, LocalDate produceDate,float aDegree) {
		super(dName, cost, produceDate, 30);  // 保质期为30天
		this.aDegree=aDegree;
	}

	// 获取酒精度数
	public float getADegree() {
		return aDegree;
	}

	public String toString() {
		String details="啤酒："+dName+" 商品价格："+cost;
		return details;

	}

}

package question1.goods;

import java.time.LocalDate;

public class Juice extends Drinks { // 继承自饮料类

	public Juice() {
		super();

	}

	public Juice(String dName, double cost, LocalDate produceDate) {
		super(dName, cost, produceDate, 2);  // 保质期为2天
	}

	public String toString() {
		return "果汁：" + dName + " 商品价格：" + cost;
	}

}

package question1.goods;

import java.time.LocalDate;

public class Juice extends Drinks { // �̳���������

	public Juice() {
		super();

	}

	public Juice(String dName, double cost, LocalDate produceDate) {
		super(dName, cost, produceDate, 2);  // ������Ϊ2��
	}

	public String toString() {
		return "��֭��" + dName + " ��Ʒ�۸�" + cost;
	}

}

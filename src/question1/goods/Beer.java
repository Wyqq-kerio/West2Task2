package question1.goods;

import java.time.LocalDate;

public class Beer extends Drinks{  // �̳���������
	
	// �����ƾ�����
	private float aDegree;
	
	public Beer() {
		super();
	}
	
	public Beer(String dName, double cost, LocalDate produceDate,float aDegree) {
		super(dName, cost, produceDate, 30);  // ������Ϊ30��
		this.aDegree=aDegree;
	}

	// ��ȡ�ƾ�����
	public float getADegree() {
		return aDegree;
	}

	public String toString() {
		String details="ơ�ƣ�"+dName+" ��Ʒ�۸�"+cost;
		return details;

	}

}

package question1.goods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public abstract class Drinks {  // ������
	
    // �������
	protected String dName;
	protected double cost;
	protected LocalDate produceDate;
	protected int period;	
	
	public Drinks() {
		super();
	}
	
	// ȫ�ι��캯��
	public Drinks(String dName, double cost, LocalDate produceDate, int period) {
		super();
		this.dName = dName;
		this.cost = cost;
		this.produceDate = produceDate;
		this.period = period;
	}
	
    // ��ȡ���ϵ�����
	public String getDName() {
		return dName;
	}
	// �������ϵ�����
	public void setDName(String s) {
		 dName=s;
	}
    
	// ���󷽷�toString
	public abstract String toString();

    // �ж��Ƿ����
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
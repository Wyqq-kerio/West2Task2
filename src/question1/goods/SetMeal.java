package question1.goods;

public class SetMeal {

	// �������
	private String mName;
	private double mPrice;
	private String chickenName;
	private Drinks dType;

	public SetMeal() {
		super();
	}

	public SetMeal(String mName, double mPrice, String chickenName, Drinks dType) {
		super();
		this.mName = mName;
		this.mPrice = mPrice;
		this.chickenName = chickenName;
		this.dType = dType;
	}
	
	public String getMName() {
		return mName;
	}
	
	public void setMName(String s){
		mName=s;		
	}

	public double getMPrice() {
		return mPrice;
	}

	public void setMprice(int n){
		mPrice=n;		
	}

	public String getChickenName() {
		return chickenName;
	}
	
	public void setChickenName(String s){
		chickenName=s;
		
	}

	public Drinks getDType() {
		return dType;
	}

	// ��дtoString����
	public String toString() {
		String details = "[ �ײ���Ϣ ]";
		details += "\n�ײ�����" + mName;
		details += "\n�ײͼ۸�:" + mPrice+"Ԫ";
		details += "\nը������" + chickenName;
		details += "\n�������ͣ�" + dType;
		return details;
	}

	
}

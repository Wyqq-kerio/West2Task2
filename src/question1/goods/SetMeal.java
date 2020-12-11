package question1.goods;

public class SetMeal {

	// 定义变量
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

	// 覆写toString方法
	public String toString() {
		String details = "[ 套餐信息 ]";
		details += "\n套餐名：" + mName;
		details += "\n套餐价格:" + mPrice+"元";
		details += "\n炸鸡名：" + chickenName;
		details += "\n饮料类型：" + dType;
		return details;
	}

	
}

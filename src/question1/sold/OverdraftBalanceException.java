package question1.sold;

public class OverdraftBalanceException extends RuntimeException{  // 进货费用超出拥有余额的异常类

	static final long serialVersionUID=-1526748329876444L;
	
	public OverdraftBalanceException() {
	
	}
	public OverdraftBalanceException(String msg) {
		super(msg);
	}
}
package question1.sold;

public class OverdraftBalanceException extends RuntimeException{  // �������ó���ӵ�������쳣��

	static final long serialVersionUID=-1526748329876444L;
	
	public OverdraftBalanceException() {
	
	}
	public OverdraftBalanceException(String msg) {
		super(msg);
	}
}
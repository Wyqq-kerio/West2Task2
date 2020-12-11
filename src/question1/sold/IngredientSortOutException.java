package question1.sold;

public class IngredientSortOutException extends RuntimeException{ //果汁或啤酒出售完的异常类

	static final long serialVersionUID=-152674832929414L;
	
	public IngredientSortOutException() {
		
	}
	public IngredientSortOutException(String msg) {
		super(msg);
	}
}

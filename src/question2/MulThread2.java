package question2;

// 这个部分的内容还没有学得很明白，所以有请教其他的同学TAT，会抽时间继续好好学习的！！！

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MulThread2 {

	public static void main(String[] args) {
		
		// 输入要查找的数字
		System.out.println("请输入：");
		Scanner scanner = new Scanner(System.in);		
		int x = scanner.nextInt();
		double time1=System.currentTimeMillis();
		// 创建Callable接口实现类的对象
		// 将FutureTask的对象作为参数传递到Thread类的构造器中，创建对象调用start()
		MyThread numThread1=new MyThread(x,1,250000000);
		FutureTask futureTask1=new FutureTask (numThread1);
	
		MyThread numThread2=new MyThread(x,250000000,500000000);
		FutureTask futureTask2=new FutureTask (numThread2);
		
		MyThread numThread3=new MyThread(x,500000000,750000000);
		FutureTask futureTask3=new FutureTask (numThread3);
		
		MyThread numThread4=new MyThread(x,750000000,1000000000);
		FutureTask futureTask4=new FutureTask (numThread4);
		 
		new Thread(futureTask1).start();
		new Thread(futureTask2).start();
		new Thread(futureTask3).start();
		new Thread(futureTask4).start();
		
		// 获取Callable中call方法的返回值
		try {
			Long sum1=(Long) futureTask1.get();
	
			Long sum2=(Long) futureTask2.get();
		
			Long sum3=(Long) futureTask3.get();

			Long sum4=(Long) futureTask4.get();

			long sum=sum1+sum2+sum3+sum4;
			System.out.println("结果："+sum);
		}catch (Exception e) {
			System.out.println("错误原因："+e.getMessage());
		}
		double time2=System.currentTimeMillis();
		System.out.println("运行时间："+(time2-time1)+" ms");
	}
}
//创建一个实现Callable的实现类
class MyThread implements Callable<Long> {
	public long sum=0;
	private int x;
	private int start;
	private int end;
	
	// 无参和有参的构造函数
	public MyThread () {

	}
	public MyThread (int x,int start,int end) {
		this.x=x;
		this.start=start;
		this.end=end;
	}

    //  实现call方法
	public Long call() throws Exception {
		
		for (long i = start; i < end; i++) { // 1000000000
			// 判断是否有所需数字
			if (contain(i, x)) {
				sum += i;
			}
		}
		return sum;
	}
	
	private static boolean contain(long num, int x) {
		return String.valueOf(num).contains(String.valueOf(x));
	}
}


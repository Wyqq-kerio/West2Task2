package question2;

// 这个部分的内容还没有学得很明白，所以有的部分请教了其他的同学TAT，会抽时间继续好好学习的！！！

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
//		创建Callable接口实现类的对象
		MyThread numThread1=new MyThread(x,1,100000000);
		MyThread numThread2=new MyThread(x,100000000,200000000);
		MyThread numThread3=new MyThread(x,200000000,300000000);
		MyThread numThread4=new MyThread(x,300000000,400000000);
		MyThread numThread5=new MyThread(x,400000000,500000000);
		MyThread numThread6=new MyThread(x,500000000,600000000);
		MyThread numThread7=new MyThread(x,600000000,700000000);
		MyThread numThread8=new MyThread(x,700000000,800000000);
		MyThread numThread9=new MyThread(x,800000000,900000000);
		MyThread numThread10=new MyThread(x,900000000,1000000000);
		
//		将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
		FutureTask futureTask1=new FutureTask (numThread1);	
		FutureTask futureTask2=new FutureTask (numThread2);
		FutureTask futureTask3=new FutureTask (numThread3);
		FutureTask futureTask4=new FutureTask (numThread4);
		FutureTask futureTask5=new FutureTask (numThread5);
		FutureTask futureTask6=new FutureTask (numThread6);
		FutureTask futureTask7=new FutureTask (numThread7);
		FutureTask futureTask8=new FutureTask (numThread8);
		FutureTask futureTask9=new FutureTask (numThread9);
		FutureTask futureTask10=new FutureTask (numThread10);
		
//		  将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
		new Thread(futureTask1).start();
		new Thread(futureTask2).start();
		new Thread(futureTask3).start();
		new Thread(futureTask4).start();
		new Thread(futureTask5).start();
		new Thread(futureTask6).start();
		new Thread(futureTask7).start();
		new Thread(futureTask8).start();
		new Thread(futureTask9).start();
		new Thread(futureTask10).start();
		
		try {
			
//			获取Callable中call方法的返回值
			Long sum1=(Long) futureTask1.get();
			Long sum2=(Long) futureTask2.get();
			Long sum3=(Long) futureTask3.get();
			Long sum4=(Long) futureTask4.get();
			Long sum5=(Long) futureTask5.get();
			Long sum6=(Long) futureTask6.get();
			Long sum7=(Long) futureTask7.get();
			Long sum8=(Long) futureTask8.get();
			Long sum9=(Long) futureTask9.get();
			Long sum10=(Long) futureTask10.get();
			long sum=sum1+sum2+sum3+sum4+sum5+sum6+sum7+sum8+sum9+sum10;
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
		
		for (long i = start; i < end; i++) {
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


package lab10;

import org.junit.experimental.max.MaxCore;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements
This is a single threaded version.
Create a multi-thread version with 3 threads:
one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]
Compare the results of the three threads and print at console the max value.
 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		new FindMax().printMax();
	}

	public void printMax() {
		// this is a single threaded version
		//int max = findMax(0, array.length - 1);
		Task task1 = new Task(0, 29);
		Task task2 = new Task(30, 59);
		Task task3 = new Task(60, 89);
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		Thread t3 = new Thread(task3);
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int max = (task1.getOutput() > task2.getOutput()) ? (task1.getOutput()>task3.getOutput()? task1.getOutput():task3.getOutput()):(task2.getOutput()>task3.getOutput()? task2.getOutput():task3.getOutput()); 		
		System.out.println("the max value is " + max);
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
	
	private class Task implements Runnable{

		int max = 0;
		int begin;
		int end;
		Task(int b, int e){
			begin = b;
			end = e;
		}
		@Override
		public void run() {
			max = findMax(begin, end);
		}
		
		public int getOutput() {
			return max;
		}
		
	}
}

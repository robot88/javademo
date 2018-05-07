package com.robot.recursivetask;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * Created by robot on 2018/4/13.
 */
public class SumTaskDemo extends RecursiveTask<Integer> {
	private final int THRESHOLD = 100;
	private Integer[] nums;
	private int start;
	private int end;

	public SumTaskDemo(Integer[] nums, int start, int end) {
		this.nums = nums;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		if ((end - start) <= THRESHOLD) {
			int sum = 0;
			for (int i = start; i < end; i++) {
				sum += nums[i];
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(String.format("compute %d~%d = %d", start, end, sum));
			return sum;
		}

		int middle = (start + end) / 2;
		System.out.println(String.format("start=%d,end=%d,middle=%d", start, end, middle));
		SumTaskDemo sumTask1 = new SumTaskDemo(nums, start, middle);
		SumTaskDemo sumTask2 = new SumTaskDemo(nums, middle, end);
		invokeAll(sumTask1, sumTask2);
		int result1 = sumTask1.join();
		int result2 = sumTask2.join();
		return result1 + result2;
	}

	public static void main(String[] args) {
		Integer[] nums = new Integer[400];
		IntStream.range(0, nums.length).forEach(i -> nums[i] = 1);
		SumTaskDemo sumTaskDemo = new SumTaskDemo(nums, 0, nums.length);
		System.out.println(sumTaskDemo.compute());
	}
}

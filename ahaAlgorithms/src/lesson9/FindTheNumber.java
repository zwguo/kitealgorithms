package lesson9;

import java.util.Arrays;
import java.util.Random;

/**
 * 第9章 还能更好吗？微软亚洲研究院面试 1、寻找出指定序列中，出现频率超过50%的一个数字
 * 
 * @author kwz
 *
 */
public class FindTheNumber {

	public static void main(String[] args) {
		test3();
		//另外的考虑方式是排序后，超过50%的数字肯定是在n/2的位置。如果不确定一定有，则需要在扫一遍那个位置的数字。
		//书中又提到另外一种方式，利用快速排序，选定一个数字，把大于它的移动到右边，小于它的移动到左边，此时分界点位置是k，如果k>n/2，则继续方式在左边找，否则在右边找，大概O(N)。
		//最后一种方式，这种序列有个特性，去掉两个不一样的数字后，在原序列出现次数>50%的数字，在新序列仍是>50%。见test3
	}

	/**
	 * O(N)，最快的方法，但是耗内存
	 */
	public static void test1() {
		int[] array = { 3, 3, 1, 1, 3, 2, 3 };
		int[] numbers = new int[100];
		int number = 0;
		int maxCount = 0;
		for (int i = 0; i < array.length; i++) {
			int a = array[i];
			numbers[a] += 1;
			if (maxCount < numbers[a]) {
				maxCount = numbers[a];
				number = a;
			}
		}
		System.out.println("maxcount number:" + number + ", count:" + maxCount + ",rate(%):"
				+ (int) ((maxCount / (1.0 * array.length)) * 100));
	}

	/**
	 * O(N*N) 没想到最简单的方法。。。
	 */
	public static void test2() {
		int[] array = { 3, 3, 1, 1, 3, 2, 3 };
		int maxCount = 0;
		int number = 0;
		for (int i = 0; i < array.length; i++) {
			int a = array[i];
			int count = 0;
			for (int j = 0; j < array.length; j++) {
				if (a == array[j]) {
					count++;
				}
			}
			if (maxCount < count) {
				maxCount = count;
				number = a;
			}
		}
		System.out.println("maxcount number:" + number + ", count:" + maxCount + ",rate(%):"
				+ (int) ((maxCount / (1.0 * array.length)) * 100));
	}
	
	/**
	 * 最简单的方式，利用主元素问题
	 */
	public static void test3() {
		//int[] array = { 3, 3, 1, 1, 3, 2, 3 };
		Random r = new Random();
		int totalCount = r.nextInt(5) + 5;
		int[] array = new int[totalCount];
		for(int i=0; i<totalCount; i++) {
			array[i] = r.nextInt(5) + 1;
		}
		System.out.println(Arrays.toString(array));
		int count = 0;
		int number = 0;
		for(int i=0; i<array.length; i++) {
			if(number == 0) {
				number = array[i];
				count ++;
			}
			else {
				if(number != array[i]) {
					count --;
				}
				else {
					count ++;
				}
			}
			if(count == 0) {
				number = 0;
				if(i == array.length - 2) {
					System.out.println("没有最大个数的值");
				}
			}
		}
		System.out.println("maxcount number:" + number);
	}
}
/**
 * output：
 * [3, 1, 5, 1, 2, 2, 5]
maxcount number:2
 */
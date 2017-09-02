package lesson6.lesson6_2_Dijkstra;

import java.util.Arrays;

/**
 * 第6章 第2节 Dijkstra算法-通过边实现松弛 给定顶点，计算出到其他点的最短距离
 * 
 * @author kwz
 *
 */
public class Dijkstra {

	public static void main(String[] args) {
		int[][] tuArray = generateData();
		System.out.println("origin:");
		display(tuArray);
		for (int i = 1; i < tuArray.length; i++) {
			int[] result = computed(tuArray, i);
			System.out.println("computed-" + i + ":");
			System.out.println(Arrays.toString(result));
		}
	}

	/**
	 * 生成测试数据
	 * 
	 * @return
	 */
	private static int[][] generateData() {
		final int wuqiong = 999;
		int[][] array = new int[][] { {}, { 0, 0, 2, 6, 4 }, { 0, wuqiong, 0, 3, wuqiong }, { 0, 7, wuqiong, 0, 1 },
				{ 0, 5, wuqiong, 12, 0 } };

		//array = new int[][] { {}, { 0, 0, 2, 2, wuqiong }, { 0, wuqiong, 0, wuqiong, 2 }, { 0, wuqiong, wuqiong, 0, 1 }, {0, wuqiong, wuqiong, wuqiong, 0}};
		return array;
	}

	/**
	 * 计算给定顶点到其他点的距离
	 * 
	 * @param tuArray
	 * @param start
	 * @return
	 */
	private static int[] computed(int[][] tuArray, int start) {
		int length = tuArray.length;
		int[] result = Arrays.copyOf(tuArray[start], tuArray.length);
		int[] books = new int[length];
		books[start] = 1;
		for (int i = 1; i < length - 1; i++) {
			dijkstraMethod(tuArray, books, result);
		}
		return result;
	}

	/**
	 * dijkstra算法
	 * 
	 * @param tuArray
	 * @param books
	 * @param result
	 */
	private static void dijkstraMethod(int[][] tuArray, int[] books, int[] result) {
		int min = Integer.MAX_VALUE;
		int start = -1;
		for (int i = 1; i < result.length; i++) {
			if (books[i] == 0 && result[i] < min) {
				min = result[i];
				start = i;
			}
		}
		if (start == -1) {
			return;
		}
		books[start] = 1;
		int[] distances = Arrays.copyOf(tuArray[start], tuArray.length);
		for (int i = 1; i < distances.length; i++) {
			if (distances[i] != 0 && distances[i] != Integer.MAX_VALUE && start != i) {
				if (result[i] > result[start] + distances[i]) {
					result[i] = result[start] + distances[i];
				}
			}
		}
	}

	/**
	 * 展示
	 * 
	 * @param array
	 */
	private static void display(final int[][] array) {
		if (array == null) {
			return;
		}
		for (int i = 1; i < array.length; i++) {
			System.out.println(Arrays.toString(array[i]));
		}
	}
}

/**
 * origin:
[0, 0, 2, 6, 4]
[0, 999, 0, 3, 999]
[0, 7, 999, 0, 1]
[0, 5, 999, 12, 0]
computed-1:
[0, 0, 2, 5, 4]
computed-2:
[0, 9, 0, 3, 4]
computed-3:
[0, 6, 8, 0, 1]
computed-4:
[0, 5, 7, 10, 0]
 * 
 */

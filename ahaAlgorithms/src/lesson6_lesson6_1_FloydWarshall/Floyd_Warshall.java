package lesson6_lesson6_1_FloydWarshall;

import java.util.Arrays;

/**
 * 第6章 第1节 只有五行的算法 Floyd-Warshall，给定图，计算出任意两点间的距离
 * 
 * @author kwz
 *
 */
public class Floyd_Warshall {

	public static void main(String[] args) {
		int[][] tuArray = generateData();
		System.out.println("origin:");
		display(tuArray);
		floydWarshall(tuArray);
		System.out.println("computed:");
		display(tuArray);
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
		return array;
	}
	
	/**
	 * 在两点之间先加入一个节点，缩短距离，然后再加入第二个、第三个直到所有可能性都覆盖到
	 * @param array
	 */
	private static void floydWarshall(int[][] array) {
		//逐个的加入节点，比如加入第一个节点，看下是否任意一对节点的距离缩短了
		for(int k=1; k<array.length; k++) {
			for(int i=1; i<array.length; i++) {
				for(int j=1; j<array.length; j++) {
					if(array[i][j] > array[i][k] + array[k][j]) {
						array[i][j] = array[i][k] + array[k][j];
					}
				}
			}
		}
	}
	
	/**
	 * 展示
	 * @param array
	 */
	private static void display(final int[][] array) {
		if(array == null) {
			return;
		}
		for(int i=1; i<array.length; i++) {
			System.out.println(Arrays.toString(array[i]));
		}
	}

}

/**
 * output:
 * origin:
[0, 0, 2, 6, 4]
[0, 999, 0, 3, 999]
[0, 7, 999, 0, 1]
[0, 5, 999, 12, 0]
computed:
[0, 0, 2, 5, 4]
[0, 9, 0, 3, 4]
[0, 6, 8, 0, 1]
[0, 5, 7, 10, 0]

 * */

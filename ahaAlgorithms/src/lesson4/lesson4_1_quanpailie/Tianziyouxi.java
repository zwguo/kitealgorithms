package lesson4.lesson4_1_quanpailie;

import java.util.Arrays;

/**
 * 填字游戏，在4.1节后有讲到 比如有9张牌，从1到9，需要填入如下的表达式中，使之成立，和Quanpailie类似 *** + *** = ***
 * 
 * @author kwz
 *
 */
public class Tianziyouxi {

	/**
	 * 主入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] bookArray = new byte[9];
		int[] output = new int[9];
		dfs(bookArray, 0, output);
	}

	/**
	 * 参见QuanPailie解释，此类只是练习巩固
	 * 
	 * @param bookArray
	 *            标记，表示哪个数字被使用了，从0到m-1
	 * @param outputCount
	 * @param output
	 *            当前已使用的数字个数
	 */
	public static void dfs(byte[] bookArray, int outputCount, int[] output) {
		if (outputCount == 9) {
			if (output[0] * 100 + output[1] * 10 + output[2] * 1 + output[3] * 100 + output[4] * 10
					+ output[5] * 1 == output[6] * 100 + output[7] * 10 + output[8] * 1) {
				System.out.println(Arrays.toString(output));
			}
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (bookArray[i] == 0) {
				output[outputCount] = i + 1;
				bookArray[i] = 1;
				dfs(bookArray, outputCount + 1, output);
				bookArray[i] = 0;
			}
		}

	}
}

/**
 * output: [1, 2, 4, 6, 5, 9, 7, 8, 3] [1, 2, 5, 7, 3, 9, 8, 6, 4] [1, 2, 7, 3,
 * 5, 9, 4, 8, 6] [1, 2, 7, 3, 6, 8, 4, 9, 5] [1, 2, 8, 3, 6, 7, 4, 9, 5] [1, 2,
 * 8, 4, 3, 9, 5, 6, 7] [1, 2, 9, 3, 5, 7, 4, 8, 6] [1, 2, 9, 4, 3, 8, 5, 6, 7]
 * [1, 2, 9, 6, 5, 4, 7, 8, 3] [1, 2, 9, 7, 3, 5, 8, 6, 4] [1, 3, 4, 6, 5, 8, 7,
 * 9, 2] [1, 3, 5, 7, 2, 9, 8, 6, 4] [1, 3, 8, 4, 2, 9, 5, 6, 7] [1, 3, 8, 6, 5,
 * 4, 7, 9, 2] [1, 3, 9, 4, 2, 8, 5, 6, 7] [1, 3, 9, 7, 2, 5, 8, 6, 4] [1, 4, 2,
 * 5, 9, 6, 7, 3, 8] [1, 4, 2, 6, 9, 5, 8, 3, 7] [1, 4, 3, 5, 8, 6, 7, 2, 9] [1,
 * 4, 5, 6, 9, 2, 8, 3, 7] [1, 4, 6, 5, 8, 3, 7, 2, 9] [1, 4, 6, 5, 9, 2, 7, 3,
 * 8] [1, 5, 2, 4, 8, 7, 6, 3, 9] [1, 5, 2, 7, 8, 4, 9, 3, 6] [1, 5, 4, 6, 2, 9,
 * 7, 8, 3] [1, 5, 4, 6, 3, 8, 7, 9, 2] [1, 5, 4, 7, 8, 2, 9, 3, 6] [1, 5, 7, 3,
 * 2, 9, 4, 8, 6] [1, 5, 7, 4, 8, 2, 6, 3, 9] [1, 5, 8, 6, 3, 4, 7, 9, 2] [1, 5,
 * 9, 3, 2, 7, 4, 8, 6] [1, 5, 9, 6, 2, 4, 7, 8, 3] [1, 6, 2, 3, 8, 7, 5, 4, 9]
 * [1, 6, 2, 7, 8, 3, 9, 4, 5] [1, 6, 3, 7, 8, 2, 9, 4, 5] [1, 6, 7, 3, 2, 8, 4,
 * 9, 5] [1, 6, 7, 3, 8, 2, 5, 4, 9] [1, 6, 8, 3, 2, 7, 4, 9, 5] [1, 7, 3, 2, 8,
 * 6, 4, 5, 9] [1, 7, 3, 2, 9, 5, 4, 6, 8] [1, 7, 5, 2, 9, 3, 4, 6, 8] [1, 7, 6,
 * 2, 8, 3, 4, 5, 9] [1, 8, 2, 3, 6, 7, 5, 4, 9] [1, 8, 2, 3, 9, 4, 5, 7, 6] [1,
 * 8, 2, 4, 5, 7, 6, 3, 9] [1, 8, 2, 4, 9, 3, 6, 7, 5] [1, 8, 2, 7, 5, 4, 9, 3,
 * 6] [1, 8, 2, 7, 6, 3, 9, 4, 5] [1, 8, 3, 2, 7, 6, 4, 5, 9] [1, 8, 3, 4, 9, 2,
 * 6, 7, 5] [1, 8, 3, 5, 4, 6, 7, 2, 9] [1, 8, 3, 7, 6, 2, 9, 4, 5] [1, 8, 4, 3,
 * 9, 2, 5, 7, 6] [1, 8, 4, 7, 5, 2, 9, 3, 6] [1, 8, 6, 2, 7, 3, 4, 5, 9] [1, 8,
 * 6, 5, 4, 3, 7, 2, 9] [1, 8, 7, 3, 6, 2, 5, 4, 9] [1, 8, 7, 4, 5, 2, 6, 3, 9]
 * [1, 9, 2, 3, 8, 4, 5, 7, 6] [1, 9, 2, 4, 8, 3, 6, 7, 5] [1, 9, 2, 5, 4, 6, 7,
 * 3, 8] [1, 9, 2, 6, 4, 5, 8, 3, 7] [1, 9, 3, 2, 7, 5, 4, 6, 8] [1, 9, 3, 4, 8,
 * 2, 6, 7, 5] [1, 9, 4, 3, 8, 2, 5, 7, 6] [1, 9, 5, 2, 7, 3, 4, 6, 8] [1, 9, 5,
 * 6, 4, 2, 8, 3, 7] [1, 9, 6, 5, 4, 2, 7, 3, 8] [2, 1, 4, 5, 6, 9, 7, 8, 3] [2,
 * 1, 4, 6, 5, 9, 8, 7, 3] [2, 1, 5, 4, 7, 8, 6, 9, 3] [2, 1, 5, 7, 4, 8, 9, 6,
 * 3] [2, 1, 6, 3, 7, 8, 5, 9, 4] [2, 1, 6, 7, 3, 8, 9, 5, 4] [2, 1, 8, 3, 4, 9,
 * 5, 6, 7] [2, 1, 8, 3, 7, 6, 5, 9, 4] [2, 1, 8, 4, 3, 9, 6, 5, 7] [2, 1, 8, 4,
 * 7, 5, 6, 9, 3] [2, 1, 8, 7, 3, 6, 9, 5, 4] [2, 1, 8, 7, 4, 5, 9, 6, 3] [2, 1,
 * 9, 3, 4, 8, 5, 6, 7] [2, 1, 9, 4, 3, 8, 6, 5, 7] [2, 1, 9, 5, 6, 4, 7, 8, 3]
 * [2, 1, 9, 6, 5, 4, 8, 7, 3] [2, 3, 4, 6, 5, 7, 8, 9, 1] [2, 3, 5, 7, 4, 6, 9,
 * 8, 1] [2, 3, 6, 7, 1, 8, 9, 5, 4] [2, 3, 6, 7, 4, 5, 9, 8, 1] [2, 3, 7, 6, 5,
 * 4, 8, 9, 1] [2, 3, 8, 4, 1, 9, 6, 5, 7] [2, 3, 8, 7, 1, 6, 9, 5, 4] [2, 3, 9,
 * 4, 1, 8, 6, 5, 7] [2, 4, 1, 5, 9, 6, 8, 3, 7] [2, 4, 3, 5, 7, 6, 8, 1, 9] [2,
 * 4, 3, 6, 7, 5, 9, 1, 8] [2, 4, 5, 6, 7, 3, 9, 1, 8] [2, 4, 5, 7, 1, 8, 9, 6,
 * 3] [2, 4, 5, 7, 3, 6, 9, 8, 1] [2, 4, 6, 5, 7, 3, 8, 1, 9] [2, 4, 6, 5, 9, 1,
 * 8, 3, 7] [2, 4, 6, 7, 3, 5, 9, 8, 1] [2, 4, 8, 3, 1, 9, 5, 6, 7] [2, 4, 8, 7,
 * 1, 5, 9, 6, 3] [2, 4, 9, 3, 1, 8, 5, 6, 7] [2, 5, 1, 3, 9, 7, 6, 4, 8] [2, 5,
 * 4, 6, 1, 9, 8, 7, 3] [2, 5, 4, 6, 3, 7, 8, 9, 1] [2, 5, 7, 3, 9, 1, 6, 4, 8]
 * [2, 5, 7, 6, 3, 4, 8, 9, 1] [2, 5, 9, 6, 1, 4, 8, 7, 3] [2, 6, 4, 5, 1, 9, 7,
 * 8, 3] [2, 6, 9, 5, 1, 4, 7, 8, 3] [2, 7, 1, 5, 9, 3, 8, 6, 4] [2, 7, 1, 6, 8,
 * 3, 9, 5, 4] [2, 7, 3, 1, 8, 6, 4, 5, 9] [2, 7, 3, 1, 9, 5, 4, 6, 8] [2, 7, 3,
 * 5, 4, 6, 8, 1, 9] [2, 7, 3, 5, 9, 1, 8, 6, 4] [2, 7, 3, 6, 4, 5, 9, 1, 8] [2,
 * 7, 3, 6, 8, 1, 9, 5, 4] [2, 7, 5, 1, 9, 3, 4, 6, 8] [2, 7, 5, 4, 1, 8, 6, 9,
 * 3] [2, 7, 5, 6, 4, 3, 9, 1, 8] [2, 7, 6, 1, 8, 3, 4, 5, 9] [2, 7, 6, 3, 1, 8,
 * 5, 9, 4] [2, 7, 6, 5, 4, 3, 8, 1, 9] [2, 7, 8, 3, 1, 6, 5, 9, 4] [2, 7, 8, 4,
 * 1, 5, 6, 9, 3] [2, 8, 1, 3, 9, 4, 6, 7, 5] [2, 8, 1, 6, 7, 3, 9, 5, 4] [2, 8,
 * 3, 1, 7, 6, 4, 5, 9] [2, 8, 3, 6, 7, 1, 9, 5, 4] [2, 8, 4, 3, 9, 1, 6, 7, 5]
 * [2, 8, 6, 1, 7, 3, 4, 5, 9] [2, 9, 1, 3, 5, 7, 6, 4, 8] [2, 9, 1, 3, 8, 4, 6,
 * 7, 5] [2, 9, 1, 5, 4, 6, 8, 3, 7] [2, 9, 1, 5, 7, 3, 8, 6, 4] [2, 9, 3, 1, 7,
 * 5, 4, 6, 8] [2, 9, 3, 5, 7, 1, 8, 6, 4] [2, 9, 4, 3, 8, 1, 6, 7, 5] [2, 9, 5,
 * 1, 7, 3, 4, 6, 8] [2, 9, 6, 5, 4, 1, 8, 3, 7] [2, 9, 7, 3, 5, 1, 6, 4, 8] [3,
 * 1, 4, 6, 5, 8, 9, 7, 2] [3, 1, 6, 2, 7, 8, 5, 9, 4] [3, 1, 7, 5, 2, 9, 8, 4,
 * 6] [3, 1, 7, 6, 2, 8, 9, 4, 5] [3, 1, 8, 2, 4, 9, 5, 6, 7] [3, 1, 8, 2, 7, 6,
 * 5, 9, 4] [3, 1, 8, 6, 2, 7, 9, 4, 5] [3, 1, 8, 6, 5, 4, 9, 7, 2] [3, 1, 9, 2,
 * 4, 8, 5, 6, 7] [3, 1, 9, 5, 2, 7, 8, 4, 6] [3, 2, 4, 5, 6, 7, 8, 9, 1] [3, 2,
 * 4, 6, 5, 7, 9, 8, 1] [3, 2, 7, 1, 5, 9, 4, 8, 6] [3, 2, 7, 1, 6, 8, 4, 9, 5]
 * [3, 2, 7, 5, 1, 9, 8, 4, 6] [3, 2, 7, 5, 6, 4, 8, 9, 1] [3, 2, 7, 6, 1, 8, 9,
 * 4, 5] [3, 2, 7, 6, 5, 4, 9, 8, 1] [3, 2, 8, 1, 6, 7, 4, 9, 5] [3, 2, 8, 6, 1,
 * 7, 9, 4, 5] [3, 2, 9, 1, 5, 7, 4, 8, 6] [3, 2, 9, 5, 1, 7, 8, 4, 6] [3, 4, 1,
 * 5, 8, 6, 9, 2, 7] [3, 4, 2, 5, 7, 6, 9, 1, 8] [3, 4, 6, 5, 7, 2, 9, 1, 8] [3,
 * 4, 6, 5, 8, 1, 9, 2, 7] [3, 4, 8, 2, 1, 9, 5, 6, 7] [3, 4, 9, 2, 1, 8, 5, 6,
 * 7] [3, 5, 1, 2, 9, 7, 6, 4, 8] [3, 5, 2, 4, 6, 7, 8, 1, 9] [3, 5, 4, 6, 1, 8,
 * 9, 7, 2] [3, 5, 4, 6, 2, 7, 9, 8, 1] [3, 5, 7, 1, 2, 9, 4, 8, 6] [3, 5, 7, 2,
 * 9, 1, 6, 4, 8] [3, 5, 7, 4, 6, 2, 8, 1, 9] [3, 5, 7, 6, 2, 4, 9, 8, 1] [3, 5,
 * 8, 6, 1, 4, 9, 7, 2] [3, 5, 9, 1, 2, 7, 4, 8, 6] [3, 6, 2, 1, 8, 7, 5, 4, 9]
 * [3, 6, 2, 4, 5, 7, 8, 1, 9] [3, 6, 4, 5, 2, 7, 8, 9, 1] [3, 6, 7, 1, 2, 8, 4,
 * 9, 5] [3, 6, 7, 1, 8, 2, 5, 4, 9] [3, 6, 7, 4, 5, 2, 8, 1, 9] [3, 6, 7, 5, 2,
 * 4, 8, 9, 1] [3, 6, 8, 1, 2, 7, 4, 9, 5] [3, 7, 2, 5, 4, 6, 9, 1, 8] [3, 7, 6,
 * 2, 1, 8, 5, 9, 4] [3, 7, 6, 5, 4, 2, 9, 1, 8] [3, 7, 8, 2, 1, 6, 5, 9, 4] [3,
 * 8, 1, 2, 9, 4, 6, 7, 5] [3, 8, 1, 5, 4, 6, 9, 2, 7] [3, 8, 2, 1, 6, 7, 5, 4,
 * 9] [3, 8, 2, 1, 9, 4, 5, 7, 6] [3, 8, 4, 1, 9, 2, 5, 7, 6] [3, 8, 4, 2, 9, 1,
 * 6, 7, 5] [3, 8, 6, 5, 4, 1, 9, 2, 7] [3, 8, 7, 1, 6, 2, 5, 4, 9] [3, 9, 1, 2,
 * 5, 7, 6, 4, 8] [3, 9, 1, 2, 8, 4, 6, 7, 5] [3, 9, 2, 1, 8, 4, 5, 7, 6] [3, 9,
 * 4, 1, 8, 2, 5, 7, 6] [3, 9, 4, 2, 8, 1, 6, 7, 5] [3, 9, 7, 2, 5, 1, 6, 4, 8]
 * [4, 1, 5, 2, 7, 8, 6, 9, 3] [4, 1, 8, 2, 3, 9, 6, 5, 7] [4, 1, 8, 2, 7, 5, 6,
 * 9, 3] [4, 1, 9, 2, 3, 8, 6, 5, 7] [4, 2, 8, 1, 3, 9, 5, 6, 7] [4, 2, 9, 1, 3,
 * 8, 5, 6, 7] [4, 3, 8, 1, 2, 9, 5, 6, 7] [4, 3, 8, 2, 1, 9, 6, 5, 7] [4, 3, 9,
 * 1, 2, 8, 5, 6, 7] [4, 3, 9, 2, 1, 8, 6, 5, 7] [4, 5, 2, 1, 8, 7, 6, 3, 9] [4,
 * 5, 2, 3, 6, 7, 8, 1, 9] [4, 5, 7, 1, 8, 2, 6, 3, 9] [4, 5, 7, 3, 6, 2, 8, 1,
 * 9] [4, 6, 2, 3, 5, 7, 8, 1, 9] [4, 6, 7, 3, 5, 2, 8, 1, 9] [4, 7, 5, 2, 1, 8,
 * 6, 9, 3] [4, 7, 8, 2, 1, 5, 6, 9, 3] [4, 8, 2, 1, 5, 7, 6, 3, 9] [4, 8, 2, 1,
 * 9, 3, 6, 7, 5] [4, 8, 3, 1, 9, 2, 6, 7, 5] [4, 8, 7, 1, 5, 2, 6, 3, 9] [4, 9,
 * 2, 1, 8, 3, 6, 7, 5] [4, 9, 3, 1, 8, 2, 6, 7, 5] [5, 1, 4, 2, 6, 9, 7, 8, 3]
 * [5, 1, 7, 3, 2, 9, 8, 4, 6] [5, 1, 9, 2, 6, 4, 7, 8, 3] [5, 1, 9, 3, 2, 7, 8,
 * 4, 6] [5, 2, 4, 3, 6, 7, 8, 9, 1] [5, 2, 7, 3, 1, 9, 8, 4, 6] [5, 2, 7, 3, 6,
 * 4, 8, 9, 1] [5, 2, 9, 3, 1, 7, 8, 4, 6] [5, 4, 1, 2, 9, 6, 8, 3, 7] [5, 4, 1,
 * 3, 8, 6, 9, 2, 7] [5, 4, 2, 1, 9, 6, 7, 3, 8] [5, 4, 2, 3, 7, 6, 9, 1, 8] [5,
 * 4, 3, 1, 8, 6, 7, 2, 9] [5, 4, 3, 2, 7, 6, 8, 1, 9] [5, 4, 6, 1, 8, 3, 7, 2,
 * 9] [5, 4, 6, 1, 9, 2, 7, 3, 8] [5, 4, 6, 2, 7, 3, 8, 1, 9] [5, 4, 6, 2, 9, 1,
 * 8, 3, 7] [5, 4, 6, 3, 7, 2, 9, 1, 8] [5, 4, 6, 3, 8, 1, 9, 2, 7] [5, 6, 4, 2,
 * 1, 9, 7, 8, 3] [5, 6, 4, 3, 2, 7, 8, 9, 1] [5, 6, 7, 3, 2, 4, 8, 9, 1] [5, 6,
 * 9, 2, 1, 4, 7, 8, 3] [5, 7, 1, 2, 9, 3, 8, 6, 4] [5, 7, 2, 3, 4, 6, 9, 1, 8]
 * [5, 7, 3, 2, 4, 6, 8, 1, 9] [5, 7, 3, 2, 9, 1, 8, 6, 4] [5, 7, 6, 2, 4, 3, 8,
 * 1, 9] [5, 7, 6, 3, 4, 2, 9, 1, 8] [5, 8, 1, 3, 4, 6, 9, 2, 7] [5, 8, 3, 1, 4,
 * 6, 7, 2, 9] [5, 8, 6, 1, 4, 3, 7, 2, 9] [5, 8, 6, 3, 4, 1, 9, 2, 7] [5, 9, 1,
 * 2, 4, 6, 8, 3, 7] [5, 9, 1, 2, 7, 3, 8, 6, 4] [5, 9, 2, 1, 4, 6, 7, 3, 8] [5,
 * 9, 3, 2, 7, 1, 8, 6, 4] [5, 9, 6, 1, 4, 2, 7, 3, 8] [5, 9, 6, 2, 4, 1, 8, 3,
 * 7] [6, 1, 4, 2, 5, 9, 8, 7, 3] [6, 1, 4, 3, 5, 8, 9, 7, 2] [6, 1, 7, 3, 2, 8,
 * 9, 4, 5] [6, 1, 8, 3, 2, 7, 9, 4, 5] [6, 1, 8, 3, 5, 4, 9, 7, 2] [6, 1, 9, 2,
 * 5, 4, 8, 7, 3] [6, 2, 4, 1, 5, 9, 7, 8, 3] [6, 2, 4, 3, 5, 7, 9, 8, 1] [6, 2,
 * 7, 3, 1, 8, 9, 4, 5] [6, 2, 7, 3, 5, 4, 9, 8, 1] [6, 2, 8, 3, 1, 7, 9, 4, 5]
 * [6, 2, 9, 1, 5, 4, 7, 8, 3] [6, 3, 4, 1, 5, 8, 7, 9, 2] [6, 3, 4, 2, 5, 7, 8,
 * 9, 1] [6, 3, 7, 2, 5, 4, 8, 9, 1] [6, 3, 8, 1, 5, 4, 7, 9, 2] [6, 4, 2, 1, 9,
 * 5, 8, 3, 7] [6, 4, 3, 2, 7, 5, 9, 1, 8] [6, 4, 5, 1, 9, 2, 8, 3, 7] [6, 4, 5,
 * 2, 7, 3, 9, 1, 8] [6, 5, 4, 1, 2, 9, 7, 8, 3] [6, 5, 4, 1, 3, 8, 7, 9, 2] [6,
 * 5, 4, 2, 1, 9, 8, 7, 3] [6, 5, 4, 2, 3, 7, 8, 9, 1] [6, 5, 4, 3, 1, 8, 9, 7,
 * 2] [6, 5, 4, 3, 2, 7, 9, 8, 1] [6, 5, 7, 2, 3, 4, 8, 9, 1] [6, 5, 7, 3, 2, 4,
 * 9, 8, 1] [6, 5, 8, 1, 3, 4, 7, 9, 2] [6, 5, 8, 3, 1, 4, 9, 7, 2] [6, 5, 9, 1,
 * 2, 4, 7, 8, 3] [6, 5, 9, 2, 1, 4, 8, 7, 3] [6, 7, 1, 2, 8, 3, 9, 5, 4] [6, 7,
 * 3, 2, 4, 5, 9, 1, 8] [6, 7, 3, 2, 8, 1, 9, 5, 4] [6, 7, 5, 2, 4, 3, 9, 1, 8]
 * [6, 8, 1, 2, 7, 3, 9, 5, 4] [6, 8, 3, 2, 7, 1, 9, 5, 4] [6, 9, 2, 1, 4, 5, 8,
 * 3, 7] [6, 9, 5, 1, 4, 2, 8, 3, 7] [7, 1, 5, 2, 4, 8, 9, 6, 3] [7, 1, 6, 2, 3,
 * 8, 9, 5, 4] [7, 1, 8, 2, 3, 6, 9, 5, 4] [7, 1, 8, 2, 4, 5, 9, 6, 3] [7, 2, 5,
 * 1, 3, 9, 8, 6, 4] [7, 2, 9, 1, 3, 5, 8, 6, 4] [7, 3, 5, 1, 2, 9, 8, 6, 4] [7,
 * 3, 5, 2, 4, 6, 9, 8, 1] [7, 3, 6, 2, 1, 8, 9, 5, 4] [7, 3, 6, 2, 4, 5, 9, 8,
 * 1] [7, 3, 8, 2, 1, 6, 9, 5, 4] [7, 3, 9, 1, 2, 5, 8, 6, 4] [7, 4, 5, 2, 1, 8,
 * 9, 6, 3] [7, 4, 5, 2, 3, 6, 9, 8, 1] [7, 4, 6, 2, 3, 5, 9, 8, 1] [7, 4, 8, 2,
 * 1, 5, 9, 6, 3] [7, 5, 2, 1, 8, 4, 9, 3, 6] [7, 5, 4, 1, 8, 2, 9, 3, 6] [7, 6,
 * 2, 1, 8, 3, 9, 4, 5] [7, 6, 3, 1, 8, 2, 9, 4, 5] [7, 8, 2, 1, 5, 4, 9, 3, 6]
 * [7, 8, 2, 1, 6, 3, 9, 4, 5] [7, 8, 3, 1, 6, 2, 9, 4, 5] [7, 8, 4, 1, 5, 2, 9,
 * 3, 6]
 */
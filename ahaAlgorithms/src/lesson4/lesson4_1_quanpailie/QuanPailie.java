package lesson4.lesson4_1_quanpailie;

import java.io.IOException;
import java.util.Arrays;

/**
 * 介绍全排列算法 4.1 不撞南墙不回头——深度优先搜索 要求：给定数字m，输出其全排列，比如m=3，则输出结果为（无次序要求）： 123 132 213
 * 231 312 321
 * 
 * @author kwz
 *
 */
public class QuanPailie {

	/**
	 * 主入口
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		byte[] input = new byte[1024];
		int length = System.in.read(input);
		int m = Integer.parseInt(new String(input, 0, length).replace("\n", ""));
		int[] output = new int[m];
		QuanPailie q = new QuanPailie(m);
		q.dfs(0, output);
	}

	/**
	 * 做标记，表示m个数字中哪些数字本轮已经用过了，其值为1代表用过了，0代表没用过。
	 */
	private byte[] bookArray;
	/**
	 * 题目要求中的m
	 */
	private final int m;

	public QuanPailie(int m) {
		this.m = m;
		this.bookArray = new byte[m];
	}

	/**
	 * 深度优先，解决m个数的全排列问题，很巧妙，避免写N层循环来找。
	 * 
	 * @paramoutputCount 目前已经有多少数了
	 * @param output
	 *            初始的输入
	 */
	private void dfs(int outputCount, int[] output) {
		// 出口
		if (outputCount == m) {
			System.out.println(Arrays.toString(output));
			return;
		}
		// 判断哪些数字没用到，随机用一个
		for (int j = 0; j < bookArray.length; j++) {
			if (bookArray[j] == 0) {
				// 该数字可用，给下一个位置赋值为该数字
				output[outputCount] = j + 1;
				bookArray[j] = 1;
				//会一直填满output为止，不用担心目前j是几，反正会填满
				dfs(outputCount + 1, output);
				// 恢复当前数字，让其有可能存放到其他的位置
				bookArray[j] = 0;
			}
		}
	}
}

/**
 * output 4 [1, 2, 3, 4] [1, 2, 4, 3] [1, 3, 2, 4] [1, 3, 4, 2] [1, 4, 2, 3] [1,
 * 4, 3, 2] [2, 1, 3, 4] [2, 1, 4, 3] [2, 3, 1, 4] [2, 3, 4, 1] [2, 4, 1, 3] [2,
 * 4, 3, 1] [3, 1, 2, 4] [3, 1, 4, 2] [3, 2, 1, 4] [3, 2, 4, 1] [3, 4, 1, 2] [3,
 * 4, 2, 1] [4, 1, 2, 3] [4, 1, 3, 2] [4, 2, 1, 3] [4, 2, 3, 1] [4, 3, 1, 2] [4,
 * 3, 2, 1]
 */

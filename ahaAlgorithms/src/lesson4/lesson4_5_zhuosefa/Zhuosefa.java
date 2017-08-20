package lesson4.lesson4_5_zhuosefa;

import java.io.IOException;

/**
 * 第4章 第5节 讲述着色法，还有Floodfill漫水填充法，也叫做种子填充法，比如photoshop的选择某个颜色的魔法棒。
 * 
 * @author kwz
 *
 */
public class Zhuosefa {

	public static void main(String[] args) throws IOException {
		byte[] bytes = new byte[1024 * 1024];
		int length = System.in.read(bytes);
		int M = Integer.valueOf(new String(bytes, 0, length).replace("\n", ""));
		length = System.in.read(bytes);
		int N = Integer.valueOf(new String(bytes, 0, length).replace("\n", ""));
		int[][] tuArray = new int[M + 1][N + 1];
		length = System.in.read(bytes);
		String str = new String(bytes, 0, length).replace("\n", "");
		String[] strs = str.split(",");
		for (int i = 0; i < N; i++) {
			String[] row = strs[i].split(" ");
			for(int j=0; j< M; j++) {
				tuArray[i + 1][j + 1] = Integer.valueOf(row[j]);
			}
		}
		System.out.println("目前的情况：");
		display(M, N, tuArray);

		int color = 0;
		for (int i = 1; i < M; i++) {
			for (int j = 1; j < N; j++) {
				if (tuArray[i][j] > 0) {
					dfs(i, j, M, N, tuArray, --color);
				}
			}
		}

		System.out.println("着色后的情况：");
		display(M, N, tuArray);
		
		System.out.println("共有" + -color + "个小岛");
	}

	/**
	 * 方向
	 */
	private static int[][] directions = { {}, { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	/**
	 * 着色法
	 * 
	 * @param x
	 * @param y
	 * @param m
	 * @param n
	 * @param tuArray
	 * @param color
	 */
	public static void dfs(int x, int y, int m, int n, int[][] tuArray, int color) {
		tuArray[x][y] = color;
		for (int type = 1; type < 5; type++) {
			int[] direction = directions[type];
			int nowX = x + direction[0];
			int nowY = y + direction[1];
			if (nowX > m || nowY > n || nowX < 1 || nowY < 1 || tuArray[nowX][nowY] <= 0) {
				continue;
			}
			dfs(nowX, nowY, m, n, tuArray, color);
		}
	}

	/**
	 * 输出
	 * 
	 * @param m
	 * @param n
	 * @param tuArray
	 */
	public static void display(int m, int n, int[][] tuArray) {
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				System.out.printf("%2d ", tuArray[i][j]);
			}
			System.out.println();
		}
	}
}

/**
 * 
 * input
 * 10
 * 10
 * 1 2 1 0 0 0 0 0 2 3,
 * 3 0 2 0 1 2 1 0 1 2,
 * 4 0 1 0 1 2 3 2 0 1,
 * 3 2 0 0 0 1 2 4 0 0,
 * 0 0 0 0 0 0 1 5 3 0,
 * 0 1 2 1 0 1 5 4 3 0,
 * 0 1 2 3 1 3 6 2 1 0,
 * 0 0 3 4 8 9 7 5 0 0,
 * 0 0 0 3 7 8 6 0 1 2,
 * 0 0 0 0 0 0 0 0 1 0

 * */

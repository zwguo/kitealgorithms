package lesson4.lesson4_2_migong;

/**
 * 4.2 节 解救小哈 要求：有一个迷宫，分成正方形的小块，每个小块中间或者是可以通过的后者是一堵墙无法通过， 而小哈在某一个位置，入口在左上角，请解救她。
 * 
 * @author kwz
 *
 */
public class MiGong {

	public static void main(String[] args) {
		// 定义入口
		byte x = 1, y = 1;
		MiGong mg = new MiGong();
		mg.dfs(x, y);
	}

	/**
	 * 假设迷宫是4*5的矩阵，x轴是4，y轴是5，均从1开始方便理解，0可行，其他是已经走过
	 */
	private final int[][] migongtu = new int[5][6];
	private final int[][] migongtuQiang = new int[5][6];
	private final byte MAX_X = 4;
	private final byte MAX_Y = 5;
	/**
	 * 小哈在3，4的位置
	 */
	private final byte goal_x = 3;
	private final byte goal_y = 4;

	public MiGong() {
		// 放置墙
		migongtuQiang[3][1] = 1;
		migongtuQiang[3][3] = 1;
		migongtuQiang[2][4] = 1;
		migongtuQiang[4][5] = 1;
	}

	/**
	 * 按照顺时针找，右下左上
	 */
	public void dfs(int x, int y) {
		if (x == goal_x && y == goal_y) {
			System.out.println();
			while (!(x == 1 && y == 1)) {
				System.out.print("[" + x + "," + y + "]");
				int value = migongtu[x][y];
				x = value / 10;
				y = value - x * 10;
			}
			System.out.print("[1,1]");
		}

		if (checkOK(x, y, 1)) {
			int nextX = x + 1;
			migongtu[nextX][y] = (x * 10 + y);
			dfs(nextX, y);
			migongtu[nextX][y] = 0;
		}
		if (checkOK(x, y, 2)) {
			int nextY = y + 1;
			migongtu[x][nextY] = (x * 10 + y);
			dfs(x, nextY);
			migongtu[x][nextY] = 0;
		}
		if (checkOK(x, y, 3)) {
			int nextX = x - 1;
			migongtu[nextX][y] = (x * 10 + y);
			dfs(nextX, y);
			migongtu[nextX][y] = 0;
		}
		if (checkOK(x, y, 4)) {
			int nextY = y - 1;
			migongtu[x][nextY] = (x * 10 + y);
			dfs(x, nextY);
			migongtu[x][nextY] = 0;
		}
	}

	/**
	 * 是否某个方向可行
	 * 
	 * @param x
	 * @param y
	 * @param type
	 *            1 2 3 4 表示右下左上
	 * @return
	 */
	private boolean checkOK(int x, int y, int type) {
		switch (type) {
		case 1:
			if (x + 1 > MAX_X) {
				return false;
			}
			return migongtu[x + 1][y] == 0 && migongtuQiang[x + 1][y] == 0;
		case 2:
			if (y + 1 > MAX_Y) {
				return false;
			}
			return migongtu[x][y + 1] == 0 && migongtuQiang[x][y + 1] == 0;
		case 3:
			if (x - 1 < 1) {
				return false;
			}
			return migongtu[x - 1][y] == 0 && migongtuQiang[x - 1][y] == 0;
		case 4:
			if (y - 1 < 1) {
				return false;
			}
			return migongtu[x][y - 1] == 0 && migongtuQiang[x][y - 1] == 0;
		default:
			return false;
		}
	}
}

/**
 * output:
 * 
 * [3,4][4,4][4,3][4,2][3,2][2,2][2,1][1,1]
 * [3,4][3,5][2,5][1,5][1,4][1,3][2,3][2,2][2,1][1,1]
 * [3,4][3,5][2,5][1,5][1,4][1,3][1,2][2,2][2,1][1,1]
 * [3,4][4,4][4,3][4,2][3,2][2,2][1,2][1,1]
 * [3,4][3,5][2,5][1,5][1,4][1,3][2,3][2,2][1,2][1,1]
 * [3,4][4,4][4,3][4,2][3,2][2,2][2,3][1,3][1,2][1,1]
 * [3,4][3,5][2,5][1,5][1,4][1,3][1,2][1,1]
 * [3,4][4,4][4,3][4,2][3,2][2,2][1,2][1,1]
 * [3,4][3,5][2,5][1,5][1,4][1,3][2,3][2,2][1,2][1,1]
 * [3,4][4,4][4,3][4,2][3,2][2,2][2,3][1,3][1,2][1,1]
 * [3,4][3,5][2,5][1,5][1,4][1,3][1,2][1,1]
 * [3,4][4,4][4,3][4,2][3,2][2,2][2,1][1,1]
 * [3,4][3,5][2,5][1,5][1,4][1,3][2,3][2,2][2,1][1,1]
 */

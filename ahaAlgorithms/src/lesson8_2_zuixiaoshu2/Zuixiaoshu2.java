package lesson8_2_zuixiaoshu2;

/**
 * 第8章 第2节 如果可以形成一条通路，那么我随便选取一个顶点，观察它的出边，选择最短的一条，
 * 将该顶点加入树，然后观察其他顶点到树的最短距离（注意是到树的最短距离， 其实也就是到该树的任意顶点的距离中选择最短的一个），找到该顶点，再次加入树，
 * 直到所有的顶点都加入了树。
 * 
 * @author kwz
 *
 */
public class Zuixiaoshu2 {
	// 顶点数量
	private int n = 6;
	// 存储无向图
	private int[][] toArray = new int[n + 1][n + 1];
	// 存储最短距离
	private int[] dis = new int[n + 1];
	// 存储标记数组
	private int[] books = new int[n + 1];

	public static void main(String[] args) {
		Zuixiaoshu2 z = new Zuixiaoshu2();
		init(1, z);
		suanfa(z);
	}

	/**
	 * 初始化
	 * 
	 * @param start
	 * @param z
	 */
	private static void init(int start, Zuixiaoshu2 z) {
		z.toArray[2][4] = 11;
		z.toArray[3][5] = 13;
		z.toArray[4][6] = 3;
		z.toArray[5][6] = 4;
		z.toArray[2][3] = 6;
		z.toArray[4][5] = 7;
		z.toArray[1][2] = 1;
		z.toArray[3][4] = 9;
		z.toArray[1][3] = 2;
		for (int i = 1; i < z.n + 1; i++) {
			for (int j = 1; j < z.n + 1; j++) {
				if (z.toArray[i][j] != 0 && z.toArray[j][i] == 0) {
					z.toArray[j][i] = z.toArray[i][j];
				} else if (z.toArray[j][i] != 0 && z.toArray[i][j] == 0) {
					z.toArray[i][j] = z.toArray[j][i];
				} else {
					z.toArray[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		z.books[start] = 1;
		for (int i = 1; i < z.n + 1; i++) {
			z.dis[i] = z.toArray[start][i];
		}
	}

	/**
	 * 核心算法
	 * @param z
	 */
	private static void suanfa(Zuixiaoshu2 z) {
		int sum = 1;
		System.out.println("加入顶点：1");
		while (sum <= z.n) {
			int min = Integer.MAX_VALUE;
			int nextDingdian = 0;
			for (int i = 1; i < z.dis.length; i++) {
				if (z.books[i] == 1) {
					continue;
				}
				if (z.dis[i] < min) {
					min = z.dis[i];
					nextDingdian = i;
				}
			}
			if (nextDingdian == 0) {
				break;
			}
			z.books[nextDingdian] = 1;
			sum ++;
			System.out.println("加入顶点：" + nextDingdian);
			for (int i = 1; i < z.n + 1; i++) {
				if (z.books[i] == 0 && z.dis[i] > z.toArray[nextDingdian][i]) {
					z.dis[i] = z.toArray[nextDingdian][i];
				}
			}
		}
	}
}
/**
 * output:
 * 加入顶点：1
加入顶点：2
加入顶点：3
加入顶点：4
加入顶点：6
加入顶点：5

 * */

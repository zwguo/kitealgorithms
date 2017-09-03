package lesson8_3_gedian;

import java.util.Arrays;

/**
 * 第8章 第3节 重要城市——图的割点 割点是如果去掉这个点，则这个图就不是连通图了。
 * 该节讲述的算法是求解割点，其思想是：首先进行深度遍历，确定每个顶点到达根顶点（第一个遍历到的顶点）的距离，记为num[]；
 * 然后再求解每个顶点（作为父节点）关联的子节点，在不经过父节点的情况下是否可以访问得到根节点，记录其距离，记为low[]；
 * 这样，如果针对一个顶点，其父亲节点的num值小于等于其子节点的low值，那说明该子节点必须通过其父节点才能访问得到根节点； 则其父节点就是割点。
 * 
 * @author kwz
 *
 */
public class GeDian {
	// 顶点数
	private int n = 6;
	// 边数
	private int m = 7;
	// 图矩阵，1表示通
	private int[][] tuArray = new int[n + 1][n + 1];
	// 根结点
	private int root = 1;
	// 原始结点距离
	private int[] num = new int[n + 1];
	// 不经过父结点的距离
	private int[] low = new int[n + 1];
	// 割点的标记，1为割点
	private int[] flag = new int[n + 1];
	// 代表距离值
	private int index = 0;

	public static void main(String[] args) {
		GeDian g = new GeDian();
		init(g);
		dfs(g, 1, g.root);
		for (int i = 1; i < g.n + 1; i++) {
			if (g.flag[i] == 1) {
				System.out.println("割点：" + i);
			}
		}
	}

	/**
	 * 初始化
	 * 
	 * @param g
	 */
	public static void init(GeDian g) {
		g.tuArray[1][4] = 1;
		g.tuArray[1][3] = 1;
		g.tuArray[4][2] = 1;
		g.tuArray[3][2] = 1;
		g.tuArray[2][5] = 1;
		g.tuArray[2][6] = 1;
		g.tuArray[5][6] = 1;
		for (int i = 1; i < g.n + 1; i++) {
			for (int j = 1; j < g.n + 1; j++) {
				if (g.tuArray[i][j] != 0) {
					g.tuArray[j][i] = g.tuArray[i][j];
				}
			}
		}
	}
	
	/**
	 * 初始化
	 * 
	 * @param g
	 */
	public static void init2(GeDian g) {
		g.tuArray[1][2] = 1;
		g.tuArray[2][3] = 1;
		g.tuArray[3][4] = 1;
		g.tuArray[4][5] = 1;
		g.tuArray[5][6] = 1;
		g.tuArray[6][2] = 1;
		for (int i = 1; i < g.n + 1; i++) {
			for (int j = 1; j < g.n + 1; j++) {
				if (g.tuArray[i][j] != 0) {
					g.tuArray[j][i] = g.tuArray[i][j];
				}
			}
		}
	}

	/**
	 * 深度遍历，计算num和low数组
	 * 
	 * @param g
	 * @param current
	 * @param parent
	 */
	public static void dfs(GeDian g, int current, int parent) {
		int child = 0;
		// 深度遍历层级增加
		g.index++;
		g.num[current] = g.index;
		g.low[current] = g.index;
		System.out.println("---------current:" + current + ",parent:" + parent + "--------------");
		for (int i = 1; i < g.n + 1; i++) {
			// 如果i点和当前current点连通，则可能会更改其low值
			if (g.tuArray[current][i] == 1) {
				// 说明i点还没有被访问过
				if (g.num[i] == 0) {
					child++;
					// 深度遍历
					dfs(g, i, current);
					// i点可以到达的地方，current点都可以到达，故更新low值
					System.out.print("i:" + i + ",current:" + current + ",parent:" + parent + ",g.low[" + i + "]:"
							+ g.low[i] + ",num:" + Arrays.toString(g.num) + ",low:" + Arrays.toString(g.low)
							+ ",changed-low:");
					g.low[current] = Math.min(g.low[current], g.low[i]);
					System.out.println(Arrays.toString(g.low));
					// 如果当前i点能访问得到的最远的地方也没有其父结点访问的远，且该点不是起点，则该点就是割点
					if (g.num[current] <= g.low[i] && current != g.root) {
						g.flag[current] = 1;
					}
					// 如果该点是起点，在经过深度遍历后，还有大于等于两个子节点，那么该点也是割点
					if (current == g.root && child == 2) {
						g.flag[current] = 1;
					}
				} else if (i != parent) {
					// 如果该顶点i曾被访问过，并且这个顶点不是当前current的父亲，那么可能需要更新到根结点的距离
					System.out.print("i:" + i + ",current:" + current + ",parent:" + parent + ",num:"
							+ Arrays.toString(g.num) + ",low:" + Arrays.toString(g.low) + ",changed-num:");
					g.low[current] = Math.min(g.low[current], g.num[i]);
					System.out.println(Arrays.toString(g.low));
				}
			}
		}
	}
}

/**
output:
---------current:1,parent:1--------------
---------current:3,parent:1--------------
---------current:2,parent:3--------------
---------current:4,parent:2--------------
i:1,current:4,parent:2,num:[0, 1, 3, 2, 4, 0, 0],low:[0, 1, 3, 2, 4, 0, 0],changed-num:[0, 1, 3, 2, 1, 0, 0]
i:4,current:2,parent:3,g.low[4]:1,num:[0, 1, 3, 2, 4, 0, 0],low:[0, 1, 3, 2, 1, 0, 0],changed-low:[0, 1, 1, 2, 1, 0, 0]
---------current:5,parent:2--------------
---------current:6,parent:5--------------
i:2,current:6,parent:5,num:[0, 1, 3, 2, 4, 5, 6],low:[0, 1, 1, 2, 1, 5, 6],changed-num:[0, 1, 1, 2, 1, 5, 3]
i:6,current:5,parent:2,g.low[6]:3,num:[0, 1, 3, 2, 4, 5, 6],low:[0, 1, 1, 2, 1, 5, 3],changed-low:[0, 1, 1, 2, 1, 3, 3]
i:5,current:2,parent:3,g.low[5]:3,num:[0, 1, 3, 2, 4, 5, 6],low:[0, 1, 1, 2, 1, 3, 3],changed-low:[0, 1, 1, 2, 1, 3, 3]
i:6,current:2,parent:3,num:[0, 1, 3, 2, 4, 5, 6],low:[0, 1, 1, 2, 1, 3, 3],changed-num:[0, 1, 1, 2, 1, 3, 3]
i:2,current:3,parent:1,g.low[2]:1,num:[0, 1, 3, 2, 4, 5, 6],low:[0, 1, 1, 2, 1, 3, 3],changed-low:[0, 1, 1, 1, 1, 3, 3]
i:3,current:1,parent:1,g.low[3]:1,num:[0, 1, 3, 2, 4, 5, 6],low:[0, 1, 1, 1, 1, 3, 3],changed-low:[0, 1, 1, 1, 1, 3, 3]
i:4,current:1,parent:1,num:[0, 1, 3, 2, 4, 5, 6],low:[0, 1, 1, 1, 1, 3, 3],changed-num:[0, 1, 1, 1, 1, 3, 3]
割点：2
*/
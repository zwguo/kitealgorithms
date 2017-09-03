package lesson8_5_Erfentu;

/**
 * 第8章 第5节 我要当月老——二分图最大匹配
 * 如果有两个结点集合A、B，集合内部结点点没有连线，但是每条连线的一段在集合A，另一端一定在集合B中，这种图就叫做二分图。
比如：
a1-b1
a2-b2
a3-b1
a1-b2
a2-b3
然后我们可以组成不同的匹配，比如：
a1-b1
a2-b2
或者：
a1-b2
a2-b3
a3-b1
显然，第二种分配更好，因为组合数最多，所以问题就是：求解二分图的最大匹配。
该种算法叫做匈牙利算法，由Jack Edmonds提出。思想是利用增广路实现：
增广路的本质是一条路径的起点和终点都是未配对的点。
从任意一个未配对的点u开始，选择其的一条边，比如u->v，如果v未配对，那么则配对成功；
否则，尝试解开v的配对，比如解开v->w，然后继续解开w->y依次类推，如果最后的都能配对，说明走通，否则失败。
要尝试另外一条边。
然后再选择一个未配对节点。
 * @author kwz
 *
 */
public class Erfentu {
	//结点数
	private int n = 6;
	//边数
	private int m = 5;
	//无向图
	private int[][] tuArray = new int[n + 1][n + 1];
	//标记该结点已经配对
	private int[] books = new int[n + 1];
	//存放该结点和谁配对
	private int[] match = new int[n + 1];

	/**
	 * 入口
	 * @param args
	 */
	public static void main(String[] args) {
		Erfentu e = new Erfentu();
		init(e);
		int sum = 0;
		for(int i=1; i<e.n + 1; i++) {
			for(int j=1; j<e.n + 1; j++) {
				e.books[j] = 0;
			}
			if(dfs(e, i)) {
				sum ++;
			}
		}
		System.out.println("配对数：" + sum);
	}
	
	/**
	 * 初始化
	 * @param e
	 */
	public static void init(Erfentu e) {
		e.tuArray[1][4] = 1;
		e.tuArray[1][5] = 1;
		e.tuArray[2][5] = 1;
		e.tuArray[2][6] = 1;
		e.tuArray[3][4] = 1;
		for (int i = 1; i < e.n + 1; i++) {
			for (int j = 1; j < e.n + 1; j++) {
				if (e.tuArray[i][j] != 0) {
					e.tuArray[j][i] = e.tuArray[i][j];
				}
			}
		}
	}

	/**
	 * 配对
	 * @param e
	 * @param u
	 * @return
	 */
	public static boolean dfs(Erfentu e, int u) {
		for(int i=1; i<e.n + 1; i++) {
			if(e.books[i] == 0 && e.tuArray[u][i] == 1) {
				e.books[i] = 1;
				if(e.match[i] == 0 || dfs(e, e.match[i])) {
					e.match[i] = u;
					e.match[u] = i;
					return true;
				}
			}
		}
		return false;
	}
}
/**
 * output：
 * 配对数：3
 * */

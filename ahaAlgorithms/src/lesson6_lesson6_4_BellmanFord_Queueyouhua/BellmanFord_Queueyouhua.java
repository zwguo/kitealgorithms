package lesson6_lesson6_4_BellmanFord_Queueyouhua;

import java.util.Arrays;

import lesson6_lesson6_3_BellmanFord.BellmanFord;

/**
 * 第6章 第4节 Bellman-Ford算法，计算一个顶点到其他顶点的最短距离，根据边来算。 可以检测负权回路，支持路径是负数权重，用队列来优化
 * 
 * @author kwz
 *
 */
public class BellmanFord_Queueyouhua {
	// 下面的定义均从1开始算下标
	private static final int DINGDIANSHU = 5;
	private static final int BIANSHU = 7;
	private static final int MAXVALUE = 999;
	private int[] dis = new int[DINGDIANSHU + 1];
	private int[] u = new int[BIANSHU + 1];
	private int[] v = new int[BIANSHU + 1];
	private int[] w = new int[BIANSHU + 1];
	// 存放站点的队列
	private int[] queue = new int[DINGDIANSHU * DINGDIANSHU]; // 估计值
	private int head = 0;
	private int tail = 0;
	// 用来优化遍历队列的时间的数组
	int[] books = new int[DINGDIANSHU + 1];
	// 创建邻接表来存放边，存放某个顶点出发的第一条边，比如first[1]=3,说明1号顶点出边是3
	int[] first = new int[DINGDIANSHU + 1];
	// 存放边的下一个边，比如next[3]=5,说明下一条边是5。结合first说明1号顶点有两条边，第一条是3，第二条是5
	int[] next = new int[BIANSHU + 1];

	public static void main(String[] args) {
		BellmanFord_Queueyouhua bf = new BellmanFord_Queueyouhua();
		init(bf);
		System.out.println("邻接表first：" + Arrays.toString(bf.first));
		System.out.println("邻接表next：" + Arrays.toString(bf.next));
		suanfa(bf);
		System.out.println("1号顶点到其他顶点的最短距离：");
		System.out.println(Arrays.toString(bf.dis));
	}

	/**
	 * 核心算法
	 * @param bf
	 */
	private static void suanfa(BellmanFord_Queueyouhua bf) {
		bf.queue[bf.head] = 1;
		bf.tail++;
		bf.books[1] = 1;
		while (bf.head < bf.tail) {
			int nowDingdian = bf.queue[bf.head];
			if(bf.first[nowDingdian] != -1) {
				int bianhao = bf.first[nowDingdian];
				setQueue(bf, bianhao);
				while(bf.next[bianhao] != -1) {
					bianhao = bf.next[bianhao];
					setQueue(bf, bianhao);
				}
			}
			bf.head++;
			bf.books[nowDingdian] = 0;
		}
	}
	
	/**
	 * 设置队列
	 * @param bf
	 * @param nowDingdian
	 */
	private static void setQueue(BellmanFord_Queueyouhua bf, int bianhao) {
		int nowDingdian = bf.u[bianhao];
		int endDingdian = bf.v[bianhao];
		int bianchang = bf.w[bianhao];
		if(bf.dis[nowDingdian] != MAXVALUE && bf.dis[endDingdian] > bf.dis[nowDingdian] + bianchang) {
			bf.dis[endDingdian] = bf.dis[nowDingdian] + bianchang;
			if(bf.books[endDingdian] != 1) {
				bf.queue[bf.tail] = endDingdian;
				bf.tail++;
				bf.books[endDingdian] = 1;
			}
		}
	}

	/**
	 * 初始化
	 * 
	 * @param bf
	 */
	private static void init(BellmanFord_Queueyouhua bf) {
		bf.dis[1] = 0;
		for (int i = 2; i < DINGDIANSHU + 1; i++) {
			bf.dis[i] = MAXVALUE;
		}
		for (int i = 1; i < DINGDIANSHU + 1; i++) {
			bf.first[i] = -1;
		}
		for (int i = 1; i < BIANSHU + 1; i++) {
			bf.next[i] = -1;
		}

		int k = 0;
		addBian(bf, ++k, 1, 2, 2);
		linjiebiao(bf, 1, k);
		addBian(bf, ++k, 1, 5, 10);
		linjiebiao(bf, 1, k);
		addBian(bf, ++k, 2, 3, 3);
		linjiebiao(bf, 2, k);
		addBian(bf, ++k, 2, 5, 7);
		linjiebiao(bf, 2, k);
		addBian(bf, ++k, 3, 4, 4);
		linjiebiao(bf, 3, k);
		addBian(bf, ++k, 4, 5, 5);
		linjiebiao(bf, 4, k);
		addBian(bf, ++k, 5, 3, 6);
		linjiebiao(bf, 5, k);
	}

	/**
	 * 设置邻接表，注意first中的值代表边号，next中的值代表下一个边号，所以next大小和边数相同
	 * 
	 * @param bf
	 * @param startDingdian
	 * @param bian
	 */
	private static void linjiebiao(BellmanFord_Queueyouhua bf, int startDingdian, int bian) {
		if (bf.first[startDingdian] != -1) {
			bf.next[bian] = bf.first[startDingdian];
		}
		bf.first[startDingdian] = bian;
	}

	/**
	 * 添加边
	 * 
	 * @param bf
	 * @param bianshu
	 *            当前第几条边
	 * @param u
	 * @param v
	 * @param w
	 */
	private static void addBian(BellmanFord_Queueyouhua bf, int bianshu, int u, int v, int w) {
		bf.u[bianshu] = u;
		bf.v[bianshu] = v;
		bf.w[bianshu] = w;
	}
}

/**
 * output:
 * 邻接表first：[0, 2, 4, 5, 6, 7]
邻接表next：[0, -1, 1, -1, 3, -1, -1, -1]
1号顶点到其他顶点的最短距离：
[0, 0, 2, 5, 9, 9]
*/

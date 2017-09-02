package lesson6_lesson6_3_BellmanFord;

import java.util.Arrays;

/**
* 第6章 第3节 Bellman-Ford算法，计算一个顶点到其他顶点的最短距离，根据边来算。
* 可以检测负权回路，支持路径是负数权重
* @author kwz
*
*/
public class BellmanFord {
	//下面的定义均从1开始算下标
	private static final int DINGDIANSHU = 5;
	private static final int BIANSHU = 5;
	private static final int MAXVALUE = 999;
	private int[] dis = new int[DINGDIANSHU + 1];
	private int[] u = new int[BIANSHU + 1];
	private int[] v = new int[BIANSHU + 1];
	private int[] w = new int[BIANSHU + 1];
	
	public static void main(String[] args) {
		BellmanFord bf = new BellmanFord();
		init(bf);
		bellmanfordsuanfa2(bf);
		System.out.println("1号顶点到其他顶点的最短距离：");
		System.out.println(Arrays.toString(bf.dis));
	}
	
	/**
	 * 核心算法
	 * @param bf
	 */
	public static void bellmanfordsuanfa(BellmanFord bf) {
		for(int k =0; k<DINGDIANSHU; k++) {
			for(int i=1; i<BIANSHU + 1; i++) {
				if(bf.dis[bf.v[i]] > bf.dis[bf.u[i]] + bf.w[i]) {
					bf.dis[bf.v[i]] = bf.dis[bf.u[i]] + bf.w[i];
				}
			}
		}
	}
	
	/**
	 * 核心算法-简单优化，当不再更新时直接终止
	 * @param bf
	 */
	public static void bellmanfordsuanfa2(BellmanFord bf) {
		for(int k =0; k<DINGDIANSHU; k++) {
			boolean isChanged = false;
			for(int i=1; i<BIANSHU + 1; i++) {
				if(bf.dis[bf.u[i]] != MAXVALUE && bf.dis[bf.v[i]] > bf.dis[bf.u[i]] + bf.w[i]) {
					bf.dis[bf.v[i]] = bf.dis[bf.u[i]] + bf.w[i];
					isChanged = true;
				}
			}
			if(!isChanged) {
				System.out.println("优化：提前终止，遍历了："+ (k+1) + "次");
				break;
			}
		}
	}

	/**
	 * 初始化
	 * @param bf
	 */
	private static void init(BellmanFord bf) {
		bf.dis[1]  = 0;
		for(int i=2; i< DINGDIANSHU + 1; i++) {
			bf.dis[i]  = MAXVALUE;
		}
		
		int k = 0;
		addBian(bf, ++k, 2, 3, 2);
		addBian(bf, ++k, 1, 2, -3);
		addBian(bf, ++k, 1, 5, 5);
		addBian(bf, ++k, 4, 5, 2);
		addBian(bf, ++k, 3, 4, 3);
	}
	
	/**
	 * 添加边
	 * @param bf
	 * @param bianshu 当前第几条边
	 * @param u
	 * @param v
	 * @param w
	 */
	private static void addBian(BellmanFord bf, int bianshu, int u, int v, int w) {
		bf.u[bianshu] = u;bf.v[bianshu] = v;bf.w[bianshu] = w;
	}
}
/**
 * output:
 * 优化：提前终止，遍历了：4次
1号顶点到其他顶点的最短距离：
[0, 0, -3, -1, 2, 4]
*/

package lesson8_1_zuixiaoshu;

import java.util.Arrays;

/**
 * 第8章 第1节 图的最小生成树 需求是：一个无向连通图中，每个顶点之间有边，边有长度，现在想生成一颗树，使得所有的顶点都连通，且边长度之和最小。
 * 算法的思想是：按照边长进行排序，然后把边依次加入，如果新加入的边使得原来的树组成了回路，则放弃，否则继续加入，直到加入n-1条边为止，因为组成一个n个结点的树，最少需要n-1条边。
 * 判断回路可以使用并查集算法，只要两个顶点已经连通，就不需要再加入新边了。
 * 
 * @author kwz
 *
 */
public class ZuiXiaoShu {
	// 顶点数
	private int n;
	// 边数
	private int m;
	// 边的起点，u[i] v[i] w[i]组成一条边
	private int[] u;
	// 边的终点
	private int[] v;
	// 边的权重
	private int[] w;
	// 存放并查集
	private int[] bingcha;
	// 存放生成的最小树，下标表示第几个顶点，值代表下一个顶点，上一点
	private int[] zuixiaoshuUp;
	// 存放生成的最小树，下标表示第几个顶点，值代表下一个顶点，下一个点
	private int[] zuixiaoshuDown;

	public static void main(String[] args) {
		ZuiXiaoShu z = new ZuiXiaoShu();
		init(z);
		System.out.println("origin-u:" + Arrays.toString(z.u));
		System.out.println("origin-v:" + Arrays.toString(z.v));
		System.out.println("origin-w:" + Arrays.toString(z.w));
		quickSort(z, 1, z.m);
		System.out.println();
		System.out.println("sorted-u:" + Arrays.toString(z.u));
		System.out.println("sorted-v:" + Arrays.toString(z.v));
		System.out.println("sorted-w:" + Arrays.toString(z.w));
		suanfa(z);
		// 输出通路
		showTonglu(z.zuixiaoshuUp, z.zuixiaoshuDown);
	}

	/**
	 * 输出通路
	 * 
	 * @param up
	 * @param down
	 */
	private static void showTonglu(int[] up, int[] down) {
		// 找到首尾中的任意一个
		int start = 0;
		for (int i = 1; i < up.length; i++) {
			if (up[i] == 0) {
				start = i;
				break;
			}
		}
		for (int i = 1; start == 0 && i < down.length; i++) {
			if (down[i] == 0) {
				start = i;
				break;
			}
		}
		showTongluInner(start, up, down);
	}

	/**
	 * 展示通路内部方法
	 * 
	 * @param start
	 * @param up
	 * @param down
	 */
	private static void showTongluInner(int start, int[] up, int[] down) {
		int[] books = new int[up.length];
		while (start != 0 && (up[start] != 0 || down[start] != 0)) {
			books[start] = 1;
			System.out.print(start + ",");
			int next = start;
			while (true) {
				int tmpIndex = up[next];
				if (tmpIndex == 0 || books[tmpIndex] != 0) {
					tmpIndex = down[next];
				}
				next = tmpIndex;
				if (tmpIndex == 0 || books[tmpIndex] == 0) {
					break;
				}	
			}
			start = next;
		}
		System.out.println();
	}

	/**
	 * 初始化
	 * 
	 * @param z
	 */
	private static void init(ZuiXiaoShu z) {
		z.n = 6;
		z.m = 9;
		z.u = new int[z.m + 1];
		z.v = new int[z.m + 1];
		z.w = new int[z.m + 1];
		z.bingcha = new int[z.n + 1];
		z.zuixiaoshuUp = new int[z.n + 1];
		z.zuixiaoshuDown = new int[z.n + 1];
		int k = 0;
		z.u[++k] = 2;
		z.v[k] = 4;
		z.w[k] = 11;
		z.u[++k] = 3;
		z.v[k] = 5;
		z.w[k] = 13;
		z.u[++k] = 4;
		z.v[k] = 6;
		z.w[k] = 3;
		z.u[++k] = 5;
		z.v[k] = 6;
		z.w[k] = 4;
		z.u[++k] = 2;
		z.v[k] = 3;
		z.w[k] = 6;
		z.u[++k] = 4;
		z.v[k] = 5;
		z.w[k] = 7;
		z.u[++k] = 1;
		z.v[k] = 2;
		z.w[k] = 1;
		z.u[++k] = 3;
		z.v[k] = 4;
		z.w[k] = 9;
		z.u[++k] = 1;
		z.v[k] = 3;
		z.w[k] = 2;
		// 初始化并查集
		for (int i = 1; i < z.n + 1; i++) {
			z.bingcha[i] = i;
		}
	}

	
	/**
	 * 检查两个结点是否连通
	 * 
	 * @param bingcha
	 * @param v1
	 * @param v2
	 * @return
	 */
	private static boolean checkTwoPointConnect(int[] bingcha, int v1, int v2) {
		int p1 = getParent(bingcha, v1);
		int p2 = getParent(bingcha, v2);
		return p1 == p2;
	}

	
	/**
	 * merge两个结点
	 * 
	 * @param bingcha
	 * @param v1
	 * @param v2
	 * @return
	 */
	private static void bingchaMerge(int[] bingcha, int v1, int v2) {
		int p1 = getParent(bingcha, v1);
		int p2 = getParent(bingcha, v2);
		if (p1 != p2) {
			bingcha[p2] = p1;
		}
	}

	
	/**
	 * 得到v点的根结点
	 * 
	 * @param bingcha
	 * @param v
	 * @return
	 */
	private static int getParent(int[] bingcha, int v) {
		if (bingcha[v] == v) {
			return v;
		} else {
			bingcha[v] = getParent(bingcha, bingcha[v]);
			return bingcha[v];
		}
	}

	/**
	 * 算法
	 * 
	 * @param z
	 */
	private static void suanfa(ZuiXiaoShu z) {
		for (int i = 1; i < z.w.length; i++) {
			int start = z.u[i];
			int end = z.v[i];
			System.out.println(
					"目前最小树Up：" + Arrays.toString(z.zuixiaoshuUp) + ",Down:" + Arrays.toString(z.zuixiaoshuDown));
			// 检查是否连通
			if (checkTwoPointConnect(z.bingcha, start, end)) {
				System.out.println("新结点不用加入：" + start + "->" + end);
				continue;
			}
			// 加入并查集
			bingchaMerge(z.bingcha, start, end);
			if (start > end) {
				int tmp = start;
				start = end;
				end = tmp;
			}
			if (z.zuixiaoshuUp[start] == 0) {
				z.zuixiaoshuUp[start] = end;
			} else if (z.zuixiaoshuDown[start] == 0) {
				z.zuixiaoshuDown[start] = end;
			}
			if (z.zuixiaoshuUp[end] == 0) {
				z.zuixiaoshuUp[end] = start;
			} else if (z.zuixiaoshuDown[end] == 0) {
				z.zuixiaoshuDown[end] = start;
			}
		}
	}

	/**
	 * 按照边长排序
	 * 
	 * @param z
	 */
	private static void quickSort(ZuiXiaoShu z, int left, int right) {
		if (left >= right) {
			return;
		}
		int w[] = z.w;
		int leftNextIndex = left;
		int rightNextIndex = right;
		while (rightNextIndex != leftNextIndex) {
			// 先右边查找第一个大于待排数值的
			while (rightNextIndex > leftNextIndex && w[left] <= w[rightNextIndex]) {
				rightNextIndex--;
			}
			// 左边查找第一个小于待排数值的
			while (leftNextIndex < rightNextIndex && w[left] >= w[leftNextIndex]) {
				leftNextIndex++;
			}
			if (rightNextIndex > leftNextIndex) {
				int tmp = z.u[rightNextIndex];
				z.u[rightNextIndex] = z.u[leftNextIndex];
				z.u[leftNextIndex] = tmp;
				tmp = z.v[rightNextIndex];
				z.v[rightNextIndex] = z.v[leftNextIndex];
				z.v[leftNextIndex] = tmp;
				tmp = z.w[rightNextIndex];
				z.w[rightNextIndex] = z.w[leftNextIndex];
				z.w[leftNextIndex] = tmp;
			}
		}
		int tmp = z.u[leftNextIndex];
		z.u[leftNextIndex] = z.u[left];
		z.u[left] = tmp;
		tmp = z.v[leftNextIndex];
		z.v[leftNextIndex] = z.v[left];
		z.v[left] = tmp;
		tmp = z.w[leftNextIndex];
		z.w[leftNextIndex] = z.w[left];
		z.w[left] = tmp;
		quickSort(z, left, leftNextIndex - 1);
		quickSort(z, leftNextIndex + 1, right);
	}
}

/**
 * output:
 * origin-u:[0, 2, 3, 4, 5, 2, 4, 1, 3, 1]
origin-v:[0, 4, 5, 6, 6, 3, 5, 2, 4, 3]
origin-w:[0, 11, 13, 3, 4, 6, 7, 1, 9, 2]

sorted-u:[0, 1, 1, 4, 5, 2, 4, 3, 2, 3]
sorted-v:[0, 2, 3, 6, 6, 3, 5, 4, 4, 5]
sorted-w:[0, 1, 2, 3, 4, 6, 7, 9, 11, 13]
目前最小树Up：[0, 0, 0, 0, 0, 0, 0],Down:[0, 0, 0, 0, 0, 0, 0]
目前最小树Up：[0, 2, 1, 0, 0, 0, 0],Down:[0, 0, 0, 0, 0, 0, 0]
目前最小树Up：[0, 2, 1, 1, 0, 0, 0],Down:[0, 3, 0, 0, 0, 0, 0]
目前最小树Up：[0, 2, 1, 1, 6, 0, 4],Down:[0, 3, 0, 0, 0, 0, 0]
目前最小树Up：[0, 2, 1, 1, 6, 6, 4],Down:[0, 3, 0, 0, 0, 0, 5]
新结点不用加入：2->3
目前最小树Up：[0, 2, 1, 1, 6, 6, 4],Down:[0, 3, 0, 0, 0, 0, 5]
新结点不用加入：4->5
目前最小树Up：[0, 2, 1, 1, 6, 6, 4],Down:[0, 3, 0, 0, 0, 0, 5]
目前最小树Up：[0, 2, 1, 1, 6, 6, 4],Down:[0, 3, 0, 4, 3, 0, 5]
新结点不用加入：2->4
目前最小树Up：[0, 2, 1, 1, 6, 6, 4],Down:[0, 3, 0, 4, 3, 0, 5]
新结点不用加入：3->5
2,1,3,4,6,5,

 * */

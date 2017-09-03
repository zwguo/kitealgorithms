package lesson8_2_zuixiaoshu2;

import java.util.Arrays;

import lesson8_3_gedian.GeDian;

/**
 * 第8章 第2节 如果可以形成一条通路，那么我随便选取一个顶点，观察它的出边，选择最短的一条，
 * 将该顶点加入树，然后观察其他顶点到树的最短距离（注意是到树的最短距离， 其实也就是到该树的任意顶点的距离中选择最短的一个），找到该顶点，再次加入树，
 * 直到所有的顶点都加入了树。 使用堆排序+邻接表优化
 * 
 * @author kwz
 *
 */
public class Zuixiaoshu3 {
	// 顶点数量
	private int n = 6;
	// 边数
	private int m = 9;
	// 边的起点
	private int[] u = new int[2 * m + 1];
	// 边的终点
	private int[] v = new int[2 * m + 1];
	// 边的权重
	private int[] w = new int[2 * m + 1];
	// 存储最短距离
	private int[] dis = new int[n + 1];
	// 存储标记数组
	private int[] books = new int[n + 1];
	// 邻接表，下标是顶点编号，值是边的编号
	private int[] first = new int[n + 1];
	// 邻接表，下标识边的编号，值是相同起始顶点下一条边的编号，这样和first结合起来，就可以找到从一个顶点出发的所有边
	private int[] next = new int[2 * m + 1];
	// 堆排序，存放的值是顶点的编号，最小堆
	private int[] dui = new int[n + 1];
	// 堆排序，下标是顶点编号，值是在堆dui[]中的位置，最小堆
	private int[] duiDingdanPos = new int[n + 1];
	// 堆大小
	private int duiSize = 0;

	public static void main(String[] args) {
		Zuixiaoshu3 z = new Zuixiaoshu3();
		init(z);
		System.out.println("u:" + Arrays.toString(z.u));
		System.out.println("v:" + Arrays.toString(z.v));
		System.out.println("w:" + Arrays.toString(z.w));
		System.out.println("dis:" + Arrays.toString(z.dis));
		System.out.println("books:" + Arrays.toString(z.books));
		System.out.println("first:" + Arrays.toString(z.first));
		System.out.println("next:" + Arrays.toString(z.next));
		System.out.println("dui:" + Arrays.toString(z.dui));
		System.out.println("duiDingdanPos:" + Arrays.toString(z.duiDingdanPos));
		System.out.println("duisize:" + z.duiSize);
		suanfa(z);
	}

	/**
	 * 初始化
	 * 
	 * @param start
	 * @param z
	 */
	private static void init(Zuixiaoshu3 z) {
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
		// 无向图反向也要存储一遍边
		for (int i = z.m + 1; i < z.m * 2 + 1; i++) {
			z.u[i] = z.v[i - z.m];
			z.v[i] = z.u[i - z.m];
			z.w[i] = z.w[i - z.m];
		}
		// 初始化邻接表
		for (int i = 1; i < z.n + 1; i++) {
			z.first[i] = -1;
		}
		// 注意看下这个初始化，是很经典的写法
		for (int i = 1; i < z.m * 2 + 1; i++) {
			int start = z.u[i];
			z.next[i] = z.first[start];
			z.first[start] = i;
		}
		// 初始化dis，选择1号顶点作为树的开始
		for (int i = 1; i < z.dis.length; i++) {
			z.dis[i] = Integer.MAX_VALUE;
		}
		int bianIndex = z.first[1];
		while (bianIndex > 0) {
			int endIndex = z.v[bianIndex];
			z.dis[endIndex] = z.w[bianIndex];
			bianIndex = z.next[bianIndex];
		}
		// 初始化堆
		for (int i = 2; i < z.n + 1; i++) {
			z.duiSize++;
			z.dui[z.duiSize] = i;
			z.duiDingdanPos[i] = z.duiSize;
			siftUp(z.dis, z.dui, z.duiDingdanPos, z.duiSize);
		}
	}

	/**
	 * 堆-向上调整-最小堆
	 * 
	 * @param dui
	 * @param index
	 * @param size
	 */
	private static void siftUp(int[] dis, int[] dui, int[] duiDingdianPos, int index) {
		int parent = index;
		while (parent > 0) {
			parent = index / 2;
			if (dis[dui[parent]] > dis[dui[index]]) {
				duiDingdianPos[dui[parent]] = index;
				duiDingdianPos[dui[index]] = parent;
				int tmp = dui[parent];
				dui[parent] = dui[index];
				dui[index] = tmp;
			} else {
				break;
			}
		}
	}

	/**
	 * 堆-向下调整-最小堆
	 * 
	 * @param dis
	 * @param dui
	 * @param duiDingdianPos
	 * @param index
	 * @param size
	 */
	private static void siftDown(int[] dis, int[] dui, int[] duiDingdianPos, int index, int size) {
		while (true) {
			int leftSon = index * 2;
			int rightSon = leftSon + 1;
			int okIndex = index;
			if (leftSon <= size) {
				if (dis[dui[index]] > dis[dui[leftSon]]) {
					okIndex = leftSon;
				}
			}
			if (rightSon <= size) {
				if (dis[dui[okIndex]] > dis[dui[rightSon]]) {
					okIndex = rightSon;
				}
			}
			if (okIndex != index) {
				duiDingdianPos[dui[okIndex]] = index;
				duiDingdianPos[dui[index]] = okIndex;
				int tmp = dui[okIndex];
				dui[okIndex] = dui[index];
				dui[index] = tmp;
				index = okIndex;
			} else {
				break;
			}
		}
	}

	/**
	 * 堆-出站-最小的值
	 * 
	 * @param dis
	 * @param dui
	 * @param duiDingdianPos
	 * @param size
	 * @return
	 */
	private static int duiPop(int[] dis, int[] dui, int[] duiDingdianPos, int size) {
		if (size > 0) {
			int tmp = dui[1];
			duiDingdianPos[dui[1]] = -1;
			dui[1] = dui[size];
			duiDingdianPos[dui[size]] = 1;
			dui[size] = -1;
			siftDown(dis, dui, duiDingdianPos, 1, size - 1);
			return tmp;
		}
		return -1;
	}

	/**
	 * 核心算法
	 * 
	 * @param z
	 */
	private static void suanfa(Zuixiaoshu3 z) {
		int sum = 1;
		System.out.println("加入顶点：1");
		z.books[1] = 1;
		while (sum <= z.n) {
			int min = Integer.MAX_VALUE;
			int nextDingdian = duiPop(z.dis, z.dui, z.duiDingdanPos, z.duiSize);
			if (nextDingdian < 0) {
				break;
			}
			z.duiSize --;
			z.books[nextDingdian] = 1;
			sum++;
			System.out.println("加入顶点：" + nextDingdian);
			int bianIndex = z.first[nextDingdian];
			while (bianIndex > 0) {
				int endDingdian = z.v[bianIndex];
				if (z.books[endDingdian] == 0 && z.dis[endDingdian] > z.w[bianIndex]) {
					z.dis[endDingdian] = z.w[bianIndex];
					// 因为该顶点的距离变小了，故需要调整堆
					siftUp(z.dis, z.dui, z.duiDingdanPos, z.duiDingdanPos[endDingdian]);
				}
				bianIndex = z.next[bianIndex];
			}
			System.out.println("---------------------------------");
			System.out.println("dis:" + Arrays.toString(z.dis));
			System.out.println("books:" + Arrays.toString(z.books));
			System.out.println("dui:" + Arrays.toString(z.dui));
			System.out.println("duiDingdanPos:" + Arrays.toString(z.duiDingdanPos));
			System.out.println("duisize:" + z.duiSize);
			System.out.println("---------------------------------");
		}
	}
}

/**
 * output:
 * u:[0, 2, 3, 4, 5, 2, 4, 1, 3, 1, 4, 5, 6, 6, 3, 5, 2, 4, 3]
v:[0, 4, 5, 6, 6, 3, 5, 2, 4, 3, 2, 3, 4, 5, 2, 4, 1, 3, 1]
w:[0, 11, 13, 3, 4, 6, 7, 1, 9, 2, 11, 13, 3, 4, 6, 7, 1, 9, 2]
dis:[0, 2147483647, 1, 2, 2147483647, 2147483647, 2147483647]
books:[0, 0, 0, 0, 0, 0, 0]
first:[0, 9, 16, 18, 17, 15, 13]
next:[0, -1, -1, -1, -1, 1, 3, -1, 2, 7, 6, 4, -1, 12, 8, 11, 5, 10, 14]
dui:[0, 2, 3, 4, 5, 6, 0]
duiDingdanPos:[0, 0, 1, 2, 3, 4, 5]
duisize:5
加入顶点：1
加入顶点：2
---------------------------------
dis:[0, 2147483647, 1, 2, 11, 2147483647, 2147483647]
books:[0, 1, 1, 0, 0, 0, 0]
dui:[0, 3, 6, 4, 5, -1, 0]
duiDingdanPos:[0, 0, -1, 1, 3, 4, 2]
duisize:4
---------------------------------
加入顶点：3
---------------------------------
dis:[0, 2147483647, 1, 2, 9, 13, 2147483647]
books:[0, 1, 1, 1, 0, 0, 0]
dui:[0, 4, 6, 5, -1, -1, 0]
duiDingdanPos:[0, 0, -1, -1, 1, 3, 2]
duisize:3
---------------------------------
加入顶点：4
---------------------------------
dis:[0, 2147483647, 1, 2, 9, 7, 3]
books:[0, 1, 1, 1, 1, 0, 0]
dui:[0, 6, 5, -1, -1, -1, 0]
duiDingdanPos:[0, 0, -1, -1, -1, 2, 1]
duisize:2
---------------------------------
加入顶点：6
---------------------------------
dis:[0, 2147483647, 1, 2, 9, 4, 3]
books:[0, 1, 1, 1, 1, 0, 1]
dui:[0, 5, -1, -1, -1, -1, 0]
duiDingdanPos:[0, 0, -1, -1, -1, 1, -1]
duisize:1
---------------------------------
加入顶点：5
---------------------------------
dis:[0, 2147483647, 1, 2, 9, 4, 3]
books:[0, 1, 1, 1, 1, 1, 1]
dui:[0, -1, -1, -1, -1, -1, 0]
duiDingdanPos:[0, 0, -1, -1, -1, 1, -1]
duisize:0
---------------------------------

 * */
package lesson7_1_dui;

import java.util.Arrays;
import java.util.Random;

/**
 * 第7章 第3节 堆——神奇的有限队列
 * 
 * @author kwz
 *
 */
public class Dui {

	public static void main(String[] args) {
		Random r = new Random();
		int[] a = new int[11];
		int[] a100 = new int[101];
		for(int i=1; i<a.length; i++) {
			int tmp = r.nextInt(100);
			if(tmp <= 0 || a100[tmp] == 1) {
				i--;
				continue;
			}
			a[i] = tmp;
			a100[tmp] = 1;
		}
		int n = a.length;
		System.out.println("origin:\t\t" + Arrays.toString(a));
		int[] h = createByUp(a);
		System.out.println("createByUp:\t" + Arrays.toString(h));
		int[] h2 = createByDown(a);
		System.out.println("createByDown:\t" + Arrays.toString(h2));
		int deleteMin = deleteMin(h2);
		System.out.println("删除最小值：" + deleteMin + ",now：" + Arrays.toString(h2));
		n--;
		//检测
		for(int i=1; i<n; i++) {
			int leftIndex = i * 2;
			int rightIndex = i * 2 +1;
			if(leftIndex < n) {
				if(h2[i] > h2[leftIndex]) {
					System.out.println("!!!!!!!!!!!!!!!notright,i:" + i + ">leftIndex:" + leftIndex);
				}
			}
			if(rightIndex < n) {
				if(h2[i] > h2[rightIndex]) {
					System.out.println("!!!!!!!!!!!!!!!notright,i:" + i + ">rightIndex:" + rightIndex);
				}
			}
		}
	}

	/**
	 * 向上调整，创建最小堆
	 * 
	 * @param a
	 * @return
	 */
	public static int[] createByUp(int[] a) {
		int[] h = new int[a.length];
		for (int i = 1; i < h.length; i++) {
			h[i] = a[i];
			siftup(h, i);
			//System.out.println("i:" + i + ",\t:" + Arrays.toString(h));
		}
		return h;
	}

	/**
	 * 向下调整，创建最小堆
	 * 
	 * @param a
	 * @return
	 */
	public static int[] createByDown(int[] a) {
		int[] h = new int[a.length];
		for (int i = 1; i < h.length; i++) {
			h[i] = a[i];
		}
		for (int i = h.length / 2; i > 0; i--) {
			siftdown(h, h.length - 1, i);
		}
		return h;
	}

	/**
	 * 向下调整
	 * 
	 * @param h
	 *            有序的最小堆，下标从1开始
	 * @param n
	 *            当前堆大小
	 * @param i
	 *            从i开始向下调整
	 */
	public static void siftdown(int[] h, int n, int i) {
		int t = 0;
		while (i * 2 <= n) {
			int leftIndex = i * 2;
			int rightIndex = i * 2 + 1;
			t = i;
			if (h[i] > h[leftIndex]) {
				t = leftIndex;
			}
			if (rightIndex <= n) {
				if (h[t] > h[rightIndex]) {
					t = rightIndex;
				}
			}
			if (t != i) {
				int tmp = h[i];
				h[i] = h[t];
				h[t] = tmp;
				i = t;
			} else {
				break;
			}
		}
	}

	/**
	 * 向上调整
	 * 
	 * @param h
	 *            有序的最小堆，下标从1开始
	 * @param i
	 *            从i开始向上调整
	 */
	public static void siftup(int[] h, int i) {
		int parent = i / 2;
		while (parent >= 1) {
			if (h[i] < h[parent]) {
				int tmp = h[i];
				h[i] = h[parent];
				h[parent] = tmp;
				i = parent;
				parent = i / 2;
			} else {
				break;
			}
		}
	}

	/**
	 * 
	 * @param h
	 */
	public static int deleteMin(int[] h) {
		int min = h[1];
		h[1] = h[h.length - 1];
		siftdown(h, h.length - 1, 1);
		return min;
	}
}
/**
 * output:
 * origin:		[0, 10, 7, 4, 24, 55, 2, 38, 85, 43, 52]
createByUp:	[0, 2, 10, 4, 24, 52, 7, 38, 85, 43, 55]
createByDown:	[0, 2, 7, 4, 24, 52, 10, 38, 85, 43, 55]
删除最小值：2,now：[0, 4, 7, 10, 24, 52, 55, 38, 85, 43, 55]

 */
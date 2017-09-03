package lesson8_1_zuixiaoshu;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
	private static int N = 0;

	public static void main(String[] args) {
		Random r = new Random();
		int[] w = new int[11];
		for (int i = 1; i < 11; i++) {
			w[i] = r.nextInt(100);
		}
		//w = new int[]{0, 5, 6, 7, 8};
		System.out.println("origin:" + Arrays.toString(w));
		quickSortDemo(w, 1, w.length - 1);
		System.out.println("sorted:" + Arrays.toString(w));
	}

	/**
	 * 快速排序，忘光了。。。
	 * @param w
	 * @param left
	 * @param right
	 */
	private static void quickSortDemo(int[] w, int left, int right) {
		System.out.println("---------------第" + ++N + "次------------------");
		if(left >= right) {
			System.out.println("left >= right:" + left  + ">=" + right);
			return;
		}
		else {
			System.out.println("left < right:" + left  + "<" + right);
		}
		int leftNextIndex = left;
		int rightNextIndex = right;
		// 用快速排序进行排序
		while (leftNextIndex != rightNextIndex) {
			// 先右边查找第一个大于待排数值的
			while (rightNextIndex > leftNextIndex && w[left] <= w[rightNextIndex]) {
				rightNextIndex--;
			}
			if (rightNextIndex > leftNextIndex) {
				System.out.println("交换：" + w[leftNextIndex] + "->" + w[rightNextIndex]);
				int tmp = w[rightNextIndex];
				w[rightNextIndex] = w[leftNextIndex];
				w[leftNextIndex] = tmp;
				System.out.println("arrsys:" + Arrays.toString(w));
			}
		}
		System.out.println("交换：" + w[left] + "->" + w[leftNextIndex]);
		int tmp = w[left];
		w[left] = w[leftNextIndex];
		w[leftNextIndex] = tmp;
		System.out.println("arrsys:" + Arrays.toString(w));
		quickSortDemo(w, left, leftNextIndex - 1);
		quickSortDemo(w, leftNextIndex + 1, right);
	}
}

/*
 * output:
 * origin:[0, 70, 23, 13, 8, 49, 52, 80, 91, 1, 23]
---------------第1次------------------
left < right:1<10
交换：70->23
arrsys:[0, 23, 23, 13, 8, 49, 52, 80, 91, 1, 70]
交换：23->1
arrsys:[0, 1, 23, 13, 8, 49, 52, 80, 91, 23, 70]
交换：1->1
arrsys:[0, 1, 23, 13, 8, 49, 52, 80, 91, 23, 70]
---------------第2次------------------
left >= right:1>=0
---------------第3次------------------
left < right:2<10
交换：23->8
arrsys:[0, 1, 8, 13, 23, 49, 52, 80, 91, 23, 70]
交换：8->8
arrsys:[0, 1, 8, 13, 23, 49, 52, 80, 91, 23, 70]
---------------第4次------------------
left >= right:2>=1
---------------第5次------------------
left < right:3<10
交换：13->13
arrsys:[0, 1, 8, 13, 23, 49, 52, 80, 91, 23, 70]
---------------第6次------------------
left >= right:3>=2
---------------第7次------------------
left < right:4<10
交换：23->23
arrsys:[0, 1, 8, 13, 23, 49, 52, 80, 91, 23, 70]
---------------第8次------------------
left >= right:4>=3
---------------第9次------------------
left < right:5<10
交换：49->23
arrsys:[0, 1, 8, 13, 23, 23, 52, 80, 91, 49, 70]
交换：23->23
arrsys:[0, 1, 8, 13, 23, 23, 52, 80, 91, 49, 70]
---------------第10次------------------
left >= right:5>=4
---------------第11次------------------
left < right:6<10
交换：52->49
arrsys:[0, 1, 8, 13, 23, 23, 49, 80, 91, 52, 70]
交换：49->49
arrsys:[0, 1, 8, 13, 23, 23, 49, 80, 91, 52, 70]
---------------第12次------------------
left >= right:6>=5
---------------第13次------------------
left < right:7<10
交换：80->70
arrsys:[0, 1, 8, 13, 23, 23, 49, 70, 91, 52, 80]
交换：70->52
arrsys:[0, 1, 8, 13, 23, 23, 49, 52, 91, 70, 80]
交换：52->52
arrsys:[0, 1, 8, 13, 23, 23, 49, 52, 91, 70, 80]
---------------第14次------------------
left >= right:7>=6
---------------第15次------------------
left < right:8<10
交换：91->80
arrsys:[0, 1, 8, 13, 23, 23, 49, 52, 80, 70, 91]
交换：80->70
arrsys:[0, 1, 8, 13, 23, 23, 49, 52, 70, 80, 91]
交换：70->70
arrsys:[0, 1, 8, 13, 23, 23, 49, 52, 70, 80, 91]
---------------第16次------------------
left >= right:8>=7
---------------第17次------------------
left < right:9<10
交换：80->80
arrsys:[0, 1, 8, 13, 23, 23, 49, 52, 70, 80, 91]
---------------第18次------------------
left >= right:9>=8
---------------第19次------------------
left >= right:10>=10
sorted:[0, 1, 8, 13, 23, 23, 49, 52, 70, 80, 91]

 */
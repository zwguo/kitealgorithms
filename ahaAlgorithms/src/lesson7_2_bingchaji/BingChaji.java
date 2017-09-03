package lesson7_2_bingchaji;

/**
 * 第7章 第4节 擒贼先擒王 并查集
 * 从一堆杂乱的数字中找出独立的森林
 * @author kwz
 *
 */
public class BingChaji {
	private static int sum = 0;

	public static void main(String[] args) {
		int n = 11;
		int[] a = new int[n];
		init(a);
		merge(a, 1, 2);
		merge(a, 3, 4);
		merge(a, 5, 2);
		merge(a, 4, 6);
		merge(a, 2, 6);
		merge(a, 8, 7);
		merge(a, 9, 7);
		merge(a, 1, 6);
		merge(a, 2, 4);
		//输出一共有多少森林
		for(int i=1; i<n; i++) {
			if(a[i] == i) {
				System.out.println(i);
			}
		}
		System.out.println("一共循环：" + sum);
	}
	
	/**
	 * 初始化其父节点是自身
	 * @param a
	 */
	public static void init(int[] a) {
		for(int i =1; i<a.length; i++) {
			a[i] = i;
		}
	}
	
	/**
	 * 合并两个属于同一团队的结点，利用靠左原则
	 * @param a
	 * @param v
	 * @param u
	 */
	public static void merge(int[] a, int v, int u) {
		int p1, p2;
		p1 = getParent(a, v);
		p2 = getParent(a, u);
		if(p1 != p2) {
			//靠左原则
			a[p2] = p1;
		}
	}

	/**
	 * 查找某个结点的根结点，中间优化中间结点的根节点，擒贼先擒王，找到最上层的根节点
	 * @param a
	 * @param v
	 * @return
	 */
	public static int getParent(int[] a, int v) {
		sum++;
		if(a[v] == v) {
			return v;
		}
		else {
			a[v] = getParent(a, a[v]);
			return a[v];
		}
	}
}
/**
 * output:
 * 5
9
10
*/

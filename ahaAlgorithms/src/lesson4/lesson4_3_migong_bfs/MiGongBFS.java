package lesson4.lesson4_3_migong_bfs;

/**
 * 4.3 节迷宫算法，广度优先，把下一步可能的范围先收集，然后一步一步寻找，直到穷尽了所有的可能，要求同MiGong.java
 * 
 * @author kwz
 *
 */
public class MiGongBFS {

	public static void main(String[] args) {
		// 构建队列
		int head = 0, tail = 0;
		// 随意赋值一个较大范围
		Node[] queue = new Node[10 * 10];
		int x = 1, y = 1;
		MiGongBFS mg = new MiGongBFS();
		queue[0] = new Node(x, y, null);
		mg.bfs(0, 1, queue);
	}

	/**
	 * 假设迷宫是4*5的矩阵，x轴是4，y轴是5，均从1开始方便理解，0可行，其他是已经走过
	 */
	private final int[][] migongtu = new int[5][6];
	private final int[][] migongtuQiang = new int[5][6];
	private final byte MAX_X = 4;
	private final byte MAX_Y = 5;
	/**
	 * 小哈在3，4的位置
	 */
	private final int goal_x = 3;
	private final int goal_y = 4;

	public MiGongBFS() {
		// 放置墙
		migongtuQiang[3][1] = 1;
		migongtuQiang[3][3] = 1;
		migongtuQiang[2][4] = 1;
		migongtuQiang[4][5] = 1;
	}

	/**
	 * 查找小哈
	 */
	public void bfs(int head, int tail, Node[] queue) {
		if (head == tail) {
			return;
		}
		Node node = queue[head];
		if (node.getX() == goal_x && node.getY() == goal_y) {
			System.out.println();
			while (node != null) {
				System.out.print("[" + node.getX() + "," + node.getY() + "]");
				node = node.getPreNode();
			}
			// 一找到就终止，因为这是最近的路之一
			return;
		}

		int x = node.getX();
		int y = node.getY();

		if (checkOK(x, y, 1)) {
			int nextX = x + 1;
			migongtu[nextX][y] = 1;
			queue[tail++] = new Node(nextX, y, node);
		}
		if (checkOK(x, y, 2)) {
			int nextY = y + 1;
			migongtu[x][nextY] = 1;
			queue[tail++] = new Node(x, nextY, node);
		}
		if (checkOK(x, y, 3)) {
			int nextX = x - 1;
			migongtu[nextX][y] = 1;
			queue[tail++] = new Node(nextX, y, node);
		}
		if (checkOK(x, y, 4)) {
			int nextY = y - 1;
			migongtu[x][nextY] = 1;
			queue[tail++] = new Node(x, nextY, node);
		}

		head++;
		bfs(head, tail, queue);
	}

	/**
	 * 是否某个方向可行
	 * 
	 * @param x
	 * @param y
	 * @param type
	 *            1 2 3 4 表示右下左上
	 * @return
	 */
	private boolean checkOK(int x, int y, int type) {
		switch (type) {
		case 1:
			if (x + 1 > MAX_X) {
				return false;
			}
			return migongtu[x + 1][y] == 0 && migongtuQiang[x + 1][y] == 0;
		case 2:
			if (y + 1 > MAX_Y) {
				return false;
			}
			return migongtu[x][y + 1] == 0 && migongtuQiang[x][y + 1] == 0;
		case 3:
			if (x - 1 < 1) {
				return false;
			}
			return migongtu[x - 1][y] == 0 && migongtuQiang[x - 1][y] == 0;
		case 4:
			if (y - 1 < 1) {
				return false;
			}
			return migongtu[x][y - 1] == 0 && migongtuQiang[x][y - 1] == 0;
		default:
			return false;
		}
	}
}

/**
 * 队列中的单个节点
 * 
 * @author kwz
 *
 */
class Node {
	private int x, y;
	private Node preNode;

	public Node(int x, int y, Node preNode) {
		this.x = x;
		this.y = y;
		this.preNode = preNode;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Node getPreNode() {
		return preNode;
	}

}

/**
 * [3,4][4,4][4,3][4,2][3,2][2,2][2,1][1,1]
 */

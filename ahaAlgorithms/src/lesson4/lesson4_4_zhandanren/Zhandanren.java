package lesson4.lesson4_4_zhandanren;

/**
 * 第4章 第4节 有一个M*N的图，上面有墙，有一些敌人，有一个小人可以走动放置炸弹，现在要解决这个炸弹放置到哪里，可以炸掉最多的敌人。 y x ****
 * **** **** ****
 * 
 * @author kwz
 *
 */
public class Zhandanren {

	public static void main(String[] args) {
		final int M = 11;
		final int N = 11;
		// 从1开始，值是0表示可通行，其他不可通行
		final int[][] tuArray = new int[M + 1][N + 1];
		// 从1开始，敌人所在位置，值1表示有敌人
		final int[][] armyArray = new int[M + 1][N + 1];
		// 从1开始，墙所在位置，值1表示有墙
		final int[][] qiangArray = new int[M + 1][N + 1];
		setArmArray(armyArray);
		setQiangArray(qiangArray);
		// 起始位置
		Node startNode = new Node(3, 3, null);
		Node[] queue = new Node[M * N];
		int head = 0, tail = 1;
		queue[head] = startNode;
		Zhandanren zdr = new Zhandanren();
		zdr.findBastPosition(queue, head, tail, M, N, tuArray, armyArray, qiangArray, 0, null);
	}

	/**
	 * 方向对应的xy变化，右下左上，从1开始
	 */
	private static final int[][] nextDirection = { {}, { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	/**
	 * 找到可以杀敌最多的位置
	 * 
	 * @param startX
	 * @param startY
	 * @param m
	 * @param n
	 * @param tuArray
	 * @param armyArray
	 * @param qiangArray
	 */
	public void findBastPosition(Node[] queue, int head, int tail, int m, int n, int[][] tuArray, int[][] armyArray,
			int[][] qiangArray, int sumKilled, Node nowNode) {
		if (head == tail) {
			// 出口
			System.out.println("共可以杀死" + sumKilled + "个敌人，只要走到[" + nowNode.getX() + "," + nowNode.getY() + "]");
			while (nowNode != null) {
				System.out.print("[" + nowNode.getX() + "," + nowNode.getY() + "]");
				nowNode = nowNode.getPreNode();
			}
			return;
		}
		Node node = queue[head];
		tuArray[node.getX()][node.getY()] = 1;
		int sum = killArmyCount(node.getX(), node.getY(), m, n, armyArray, qiangArray);
		if (sumKilled < sum) {
			sumKilled = sum;
			nowNode = node;
		}
		for (int type = 1; type < 5; type++) {
			boolean isOk = checkOk(type, node.getX(), node.getY(), m, n, tuArray, armyArray, qiangArray);
			if (!isOk) {
				continue;
			}
			int[] direction = nextDirection[type];
			int nowX = direction[0] + node.getX();
			int nowY = direction[1] + node.getY();
			queue[tail++] = new Node(nowX, nowY, node);
		}
		head++;
		findBastPosition(queue, head, tail, m, n, tuArray, armyArray, qiangArray, sumKilled, nowNode);
	}

	/**
	 * 该位置可杀敌数量
	 * 
	 * @param x
	 * @param y
	 * @param m
	 * @param n
	 * @param armyArray
	 * @param qiangArray
	 * @return
	 */
	private int killArmyCount(int x, int y, int m, int n, int[][] armyArray, int[][] qiangArray) {
		int sum = 0;
		for (int type = 1; type < 5; type++) {
			int[] direction = nextDirection[type];
			int nowX = direction[0] + x;
			int nowY = direction[1] + y;
			// 直到碰到墙
			while (!(nowX > m || nowY > n || nowX < 1 || nowY < 1 || qiangArray[nowX][nowY] == 1)) {
				if (armyArray[nowX][nowY] == 1) {
					sum++;
				}
				nowX = direction[0] + nowX;
				nowY = direction[1] + nowY;
			}
		}
		return sum;
	}

	/**
	 * 判断是否走得通
	 * 
	 * @param type
	 *            右下左上 1234
	 * @param x
	 * @param y
	 * @param m
	 * @param n
	 * @param tuArray
	 * @param armyArray
	 * @param qiangArray
	 * @return
	 */
	private boolean checkOk(int type, int x, int y, int m, int n, int[][] tuArray, int[][] armyArray,
			int[][] qiangArray) {
		int[] direction = nextDirection[type];
		int nowX = direction[0] + x;
		int nowY = direction[1] + y;
		if (nowX > m || nowY > n || nowX < 1 || nowY < 1) {
			return false;
		}
		if (tuArray[nowX][nowY] != 0 || armyArray[nowX][nowY] == 1 || qiangArray[nowX][nowY] == 1) {
			return false;
		}
		return true;
	}

	/**
	 * 设置敌人位置
	 * 
	 * @param armyArray
	 */
	private static void setArmArray(final int[][] armyArray) {
		armyArray[1][1] = 1;
		armyArray[2][1] = 1;
		armyArray[4][1] = 1;
		armyArray[5][1] = 1;
		armyArray[6][1] = 1;
		armyArray[8][1] = 1;
		armyArray[9][1] = 1;
		armyArray[10][1] = 1;
		armyArray[5][2] = 1;
		armyArray[7][2] = 1;
		armyArray[9][2] = 1;
		armyArray[11][2] = 1;
		armyArray[11][3] = 1;
		armyArray[1][4] = 1;
		armyArray[9][4] = 1;
		armyArray[11][4] = 1;
		armyArray[1][5] = 1;
		armyArray[2][5] = 1;
		armyArray[4][5] = 1;
		armyArray[5][5] = 1;
		armyArray[6][5] = 1;
		armyArray[10][5] = 1;
		armyArray[11][5] = 1;
		armyArray[1][6] = 1;
		armyArray[5][6] = 1;
		armyArray[2][7] = 1;
		armyArray[6][7] = 1;
		armyArray[1][8] = 1;
		armyArray[5][8] = 1;
		armyArray[11][8] = 1;
		armyArray[4][9] = 1;
		armyArray[6][9] = 1;
		armyArray[7][9] = 1;
		armyArray[8][9] = 1;
		armyArray[10][9] = 1;
		armyArray[11][9] = 1;
		armyArray[1][10] = 1;
		armyArray[5][10] = 1;
		armyArray[7][10] = 1;
		armyArray[11][10] = 1;
		armyArray[1][11] = 1;
		armyArray[2][11] = 1;
		armyArray[4][11] = 1;
		armyArray[5][11] = 1;
		armyArray[6][11] = 1;
		armyArray[8][11] = 1;
		armyArray[10][11] = 1;
		armyArray[11][11] = 1;
	}

	/**
	 * 设置位置
	 * 
	 * @param armyArray
	 */
	private static void setQiangArray(final int[][] qiangArray) {
		qiangArray[7][1] = 1;
		qiangArray[1][2] = 1;
		qiangArray[2][2] = 1;
		qiangArray[4][2] = 1;
		qiangArray[6][2] = 1;
		qiangArray[8][2] = 1;
		qiangArray[10][2] = 1;
		qiangArray[8][3] = 1;
		qiangArray[2][4] = 1;
		qiangArray[4][4] = 1;
		qiangArray[5][4] = 1;
		qiangArray[6][4] = 1;
		qiangArray[8][4] = 1;
		qiangArray[10][4] = 1;
		qiangArray[8][5] = 1;
		qiangArray[2][6] = 1;
		qiangArray[4][6] = 1;
		qiangArray[6][6] = 1;
		qiangArray[8][6] = 1;
		qiangArray[10][6] = 1;
		qiangArray[1][7] = 1;
		qiangArray[2][8] = 1;
		qiangArray[4][8] = 1;
		qiangArray[6][8] = 1;
		qiangArray[7][8] = 1;
		qiangArray[8][8] = 1;
		qiangArray[10][8] = 1;
		qiangArray[5][9] = 1;
		qiangArray[2][10] = 1;
		qiangArray[4][10] = 1;
		qiangArray[6][10] = 1;
		qiangArray[8][10] = 1;
		qiangArray[10][10] = 1;
		qiangArray[7][11] = 1;
	}
}

/**
 * 节点
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
 * output 共可以杀死10个敌人，只要走到[11,7]
 * [11,7][10,7][9,7][8,7][7,7][7,6][7,5][7,4][7,3][6,3][5,3][4,3][3,3]
 */
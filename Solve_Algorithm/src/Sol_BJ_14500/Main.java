package Sol_BJ_14500;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static int N, M;
	public static int map[][];

	// ��, ��
	public static int[][] t1 = { { 6, 6, 6 }, { 2, 2, 2 } };
	// ��
	public static int[][] t2 = { { 6, 2, 4 }, { 2, 6, 8 } };
	// L ( 2����)
	public static int[][] t3 = { { 2, 2, 6 }, { 6, 6, 8 }, { 8, 8, 4 }, { 4, 4, 2 }, { 6, 8, 8 }, { 8, 4, 4 },
			{ 4, 2, 2 }, { 2, 6, 6 } };
	// Z ( 2���� )
	public static int[][] t4 = { { 2, 6, 2 }, { 6, 8, 6 }, { 6, 2, 6 }, { 8, 6, 8 } };
	// ��
	public static int[][] t5 = { { 4, 6, 2 }, { 8, 6, 2 }, { 8, 4, 6 }, { 8, 4, 2 } };

	public static ArrayList<int[][]> list;
	// �ִ밪
	public static int MAX;
	// ������
	public static int MX, MY, MTYPE1, MTYPE2;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String[] s1 = br.readLine().split(" ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(s1[j - 1]);
			}
		}
		MAX = Integer.MIN_VALUE;
		solve(0);
	}

	public static void solve(int a) {
		list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		for (int i = 0; i < list.size(); i++) {

			if (i < 4) {
				findonetofour(i);
			} else {
				findfive(i);
			}

		}
		System.out.println(MAX);
	}

	/**
	 * ���������� ���� DFS�� ã�ư���
	 * 
	 * @param idx ArrayList�� ���� 2���� �迭 ������ ���� �ε���
	 */
	public static void findonetofour(int idx) {
		int[][] t = list.get(idx);
		int tmax = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				for (int a = 0; a < t.length; a++) {
					int tx = j;
					int ty = i;

					int cnt = 0;
					int val = 0;

					int nowtype = -1;
					cnt = 0;
					val = 0;
					val += map[i][j];
					nowtype = -1;

					for (int b = 0; b < t[0].length; b++) {

						int[] ttd = move(tx, ty, t[a][b]);
						tx = ttd[0];
						ty = ttd[1];

						if (isVaild(tx, ty) == true) {
							cnt++;
							val += map[ty][tx];
							nowtype = a;
						} else {
							nowtype = -1;
							break;
						}

					}
					if (cnt == t[0].length) {
						if (MAX < val) {
							MAX = val;
							MX = j;
							MY = i;
							MTYPE1 = idx;
							MTYPE2 = nowtype;
						}
					}
				}
			}
		}
	}

	/**
	 * ���������κ��� BFS�� ã�ư���
	 * 
	 * @param idx ArrayList�� ���� 2���� �迭 ������ ���� �ε���
	 */
	public static void findfive(int idx) {
		int[][] t = list.get(idx);
		int tmax = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {

				for (int a = 0; a < t.length; a++) {
					int tx = j;
					int ty = i;

					int initX = j;
					int initY = i;

					int cnt = 0;
					int val = 0;
					int nowtype = -1;
					cnt = 0;
					nowtype = -1;
					val = 0;
					val += map[i][j];
					for (int b = 0; b < t[0].length; b++) {
						int[] ttd = move(tx, ty, t[a][b]);

						tx = ttd[0];
						ty = ttd[1];
						if (isVaild(tx, ty) == true) {
							cnt++;
							val += map[ty][tx];
							tx = initX;
							ty = initY;
							nowtype = a;
						} else {
							nowtype = -1;
							tx = initX;
							ty = initY;
							break;
						}

					}
					if (cnt == t[0].length) {

						if (MAX < val) {
							MAX = val;
							MX = j;
							MY = i;
							MTYPE1 = idx;
							MTYPE2 = nowtype;
						}
					}

				}

			}
		}
	}

	/**
	 * ���� ��ȯ
	 * 
	 * @param x   x��
	 * @param y   y��
	 * @param dir ���� �� Ű���� �����е� 8, 4, 6 ,2 ��, ��, ��, ��
	 * @return �迭�� �ε��� 0 = x , �ε��� 1 = y
	 */
	public static int[] move(int x, int y, int dir) {
		int[] t = new int[2];
		switch (dir) {
		case 8:
			y--;
			break;
		case 2:
			y++;
			break;
		case 4:
			x--;
			break;
		case 6:
			x++;
			break;

		}
		t[0] = x;
		t[1] = y;
		return t;

	}

	/**
	 * ��ǥ�� ����� Ŭ����
	 * 
	 * @author Choi
	 *
	 */
	static class point {
		int x, y;

		public point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * �� ���� �ȿ� �ִ� �� Ȯ�� �޼ҵ�
	 * 
	 * @param x x��
	 * @param y y��
	 * @return ���� �ȿ� ������ true, �ƴϸ� false
	 */
	public static boolean isVaild(int x, int y) {
		if (x < 1 || x > M || y < 1 || y > N)
			return false;
		else
			return true;
	}

}

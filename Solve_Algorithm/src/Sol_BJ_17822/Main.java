package Sol_BJ_17822;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	/**
	 * N ������ // M ����
	 * N ==> y // M == x
	 * T ��� ����
	 */
	public static int N, M, T;
	/**
	 * round : ���� �迭
	 * round[1][1 ~ 4] : �� ���� 1 ~ 4 �� �� �� �� �� 
	 * round[2][x] : �� ���� ū ���� ��
	 * ...
	 * 
	 * check : ������ �� ã�� �� ��ġ üũ �� �迭
	 */
	public static int round[][];
	public static int check[][];
	public static int[] x, d, k;

	
	/** 
	 * ���� ��ġ ã������ ���� �迭
	 */
	public static int dx[] = { -1, 1, 0, 0 };
	public static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s1 = br.readLine().split(" ");

		N = Integer.parseInt(s1[0]);
		M = Integer.parseInt(s1[1]);
		T = Integer.parseInt(s1[2]);

		round = new int[N + 1][M + 1];
		check = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 1; j <= M; j++) {
				round[i][j] = Integer.parseInt(s[j - 1]);

			}
		}
		x = new int[T + 1];
		d = new int[T + 1];
		k = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			String s2[] = br.readLine().split(" ");
			x[i] = Integer.parseInt(s2[0]);
			d[i] = Integer.parseInt(s2[1]);
			k[i] = Integer.parseInt(s2[2]);
		}

		solve();
	}

	public static void solve() {

		for (int i = 1; i <= T; i++) {
			int xVal = x[i];
			int kVal = k[i];
			int dVal = d[i];
			calcMultifle(xVal, dVal, kVal);
			int SameCnt = FindNearbySameValues();
			if (SameCnt > 0) {
				DeleteSameValues();
			} else {
				double avg = calcAverage();
				histogramRound(avg);
			}
			clear();
		}
		int result = calcResult();
		System.out.println(result);
	}

	/**
	 * ��� ��� -> ����� ���߾� ȸ��
	 * ȸ���� �� �ݺ� ȸ��
	 */
	public static void calcMultifle(int x, int d, int k) {

		int cnt = 1;
		while (true) {
			int val = x * cnt;
			if (val > N)
				break;

			for (int i = 0; i < k; i++) {
				rotate(val, d);
			}
			cnt++;
		}
	}

	/**
	 * ���� ȸ��
	 * @param x ���� ��ġ
	 * @param r �ð�/�ݽð� ( r == 1 �ݽð� r == 0  �ð�)
	 */
	public static void rotate(int x, int r) {
		if (r == 1) { // �ݽð�
			// 4 ->3 , 3 -> 2, 2->1, 1->4
			int temp = round[x][1];
			for (int i = 1; i < round[x].length - 1; i++) {
				round[x][i] = round[x][i + 1];
			}
			round[x][round[x].length - 1] = temp;
		} else { // �ð�
			// 1 -> 2 , 2 -> 3, 3 -> 4, 4 -> 1
			int temp = round[x][round[x].length - 1];
			for (int i = round[x].length - 1; i >= 1; i--) {
				round[x][i] = round[x][i - 1];
			}
			round[x][1] = temp;
		}
	}

	/**
	 * ������ ���� ã��
	 * ã���ڿ� �ٷ� ���ǿ� ����ϴ°��� �ƴ϶� ��ġ�� üũ�صΰ� �ѹ��� ����
	 * ������ ���� ������ �����ؼ� �б������� Ȱ��(��մ��ϳ�, ������ ���� �����ϳ�)
	 * @return ������ ���� ����
	 */
	public static int FindNearbySameValues() {
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				int nowVal = round[i][j];
				for (int k = 0; k < dx.length; k++) {
					int mx = j + dx[k];
					int my = i + dy[k];
					
					// �����̿��� Ȯ�ν� �Ѿ���� �ݴ������� ����
					if (mx > M)
						mx = 1;
					if (mx < 1)
						mx = M;
					if (isVaild(mx, my) && nowVal != 0) {
						int checkVal = round[my][mx];
						if (nowVal == checkVal) {
							check[i][j] = 1;
							check[my][mx] = 1;
							cnt++;
						}
					}

				}
			}
		}
		return cnt;
	}
	/**
	 * ������ ���� ����
	 */
	public static void DeleteSameValues() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (check[i][j] == 1)
					round[i][j] = 0;
			}
		}
	}

	/**
	 * ���� ���� ��հ� ����
	 * @return ���� ��հ�
	 */
	public static double calcAverage() {
		double res = 0;
		double cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (round[i][j] != 0) {
					res += round[i][j];
					cnt++;
				}
			}
		}
		return res / cnt;
	}

	/**
	 * ��պ��� ������ -1 , ��պ��� ������ +1
	 * @param avg ���� ������ ��հ�
	 */
	public static void histogramRound(double avg) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (round[i][j] != 0) {
					if (round[i][j] < avg)
						round[i][j]++;
					else if (round[i][j] > avg)
						round[i][j]--;
					else {
					}
				}
			}
		}
	}

	/**
	 * ���� ���� ã�� ���� �ʱ�ȭ
	 */
	public static void clear() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				check[i][j] = 0;
			}
		}
	}

	/**
	 * ������ ���� ��ü �� ����
	 * @return ���� ��ü ���� ��
	 */
	public static int calcResult() {
		int res = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				res += round[i][j];
			}
		}

		return res;
	}

	/**
	 * �迭 ��� �Ѿ�°� üũ
	 */
	public static boolean isVaild(int x, int y) {

		if (x < 1 || x > M || y < 1 || y > N)
			return false;
		else
			return true;
	}

	/**
	 * round ���� ���� Ȯ�ο�
	 */
	public static void debugMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(round[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---roundcheck---");
	}

	/**
	 * check ���� ���� Ȯ�ο�
	 */
	public static void debugcheck() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(check[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---debugcheck---");
	}
}

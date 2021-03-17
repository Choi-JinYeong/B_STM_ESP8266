package Git_BJ_14891;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/14891

// 1�ð� 30�� �ҿ�

public class Main {

	public static int[][] r;
	public static int K;
	public static int cmd[][];
	public static int currentRotate[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		r = new int[5][8];

		for (int i = 1; i <= 4; i++) {
			String s = br.readLine();
			for (int k = 0; k < 8; k++) {
				r[i][k] = Integer.parseInt(s.charAt(k) + "");
			}
		}
		K = Integer.parseInt(br.readLine());
		cmd = new int[K][2];
		currentRotate = new int[5];
		// 12�� ���� ���� ������� �ð����
		for (int i = 0; i < K; i++) {
			String[] s = br.readLine().split(" ");
			cmd[i][0] = Integer.parseInt(s[0]); // ��ȣ
			cmd[i][1] = Integer.parseInt(s[1]); // ����
		}
		solve();
		calc();

	}

	public static void calc() {
		int cnt = 0;
		for (int i = 1; i <= 4; i++) {
			// System.out.println(r[i][0]);
			if (r[i][0] == 1) {
				switch (i) {
				case 1:
					cnt += 1;
					break;
				case 2:
					cnt += 2;
					break;
				case 3:
					cnt += 4;
					break;
				case 4:
					cnt += 8;
					break;
				}
			}
		}
		System.out.println(cnt);
	}

	// N ���� 0, S���� 1
	// �ð���� 1, �ݽð� -1

	public static void solve() {
		printR();
		// System.out.println("���� ��====================");
		for (int i = 0; i < cmd.length; i++) {
			int num = cmd[i][0];
			int rotate = cmd[i][1];
			checkRotate(num, rotate);
			clear();
			// System.out.println((i + 1) + "==================");
			printR();
		}

	}

	public static void clear() {
		for (int i = 1; i <= 4; i++)
			currentRotate[i] = 0;
	}

	public static void checkRotate(int num, int rotate) {

		int minusNum = num;
		int plusNum = num;
		int lnum = num;
		int rnum = num;
		// ���� �ֺ��� üũ
		int idx = 0;
		currentRotate[num] = rotate;

		while (true) {
			if (minusNum < 1 && plusNum > 4)
				break;
			lnum = minusNum;
			rnum = plusNum;
			minusNum -= 1;
			plusNum += 1;

			if (minusNum > 0) {
				int a = checkNS(lnum, minusNum, -1);
				if (a == -1) {
					// System.out.println(" a == -1");
					break;
				}
				if (a == 1 || currentRotate[minusNum + 1] == 0) { // ������ �Ǵ� �ȵ��¾ָ� �ȵ���
					currentRotate[minusNum] = 0;
				} else { // �ٸ��� ȸ��
					if (currentRotate[minusNum + 1] == 1) {
						currentRotate[minusNum] = -1; // ���� �־������

					} else if (currentRotate[minusNum + 1] == -1) {
						currentRotate[minusNum] = 1;

					} else {
						// ������ �־�
					}
				}

			}
			if (plusNum < 5) {
				int b = checkNS(rnum, -1, plusNum);
				if (b == -1) {
					// System.out.println(" b == -1");
					break;
				}
				if (b == 1 || currentRotate[plusNum - 1] == 0) { // ���� �� �Ǵ� �ȵ��¾ָ� ���� �ȵ��ƾ���
					currentRotate[plusNum] = 0;
				} else { // �ٸ��� ȸ��
					// �������� �ݽð�� , ȸ���ϴ¾ִ� �ð�
					// �������� �ð��, ȸ���ϴ¾��� �ݽð�
					if (currentRotate[plusNum - 1] == 1) {
						currentRotate[plusNum] = -1;
						// rotating(num + idx, -1);
					} else if (currentRotate[plusNum - 1] == -1) {
						currentRotate[plusNum] = 1;
						// rotating(num + idx, 1);
					} else {
						// ������ �־�
					}
				}
			}
		}
		for (int i = 1; i <= 4; i++) {
			rotating(i, currentRotate[i]);
			// System.out.println("rotate " + i + " : " + currentRotate[i]);
		}
	}

	public static int checkNS(int num, int leftnum, int rightnum) {
		if (rightnum == -1) { // ������ ���� ���ʹ���

			if (r[leftnum][2] != r[num][6]) { // ���� �ʾ�
				return 0; // �ٸ���
			} else { // ����
				return 1;
			}

		}
		if (leftnum == -1) { // ������ ���� ������ ���� ��
			if (r[num][2] != r[rightnum][6]) { // ���� �ʾ�
				return 0; // �ٸ���
			} else { // ����
				return 1;
			}
		}

		return -1;

	}

	public static void rotating(int num, int rotate) {
		if (rotate == 1) { // �ð�
			int temp7 = r[num][7];
			for (int i = 7; i > 0; i--) {
				r[num][i] = r[num][i - 1];
			}
			r[num][0] = temp7;

		} else if (rotate == -1) { // �ݽð�
			int temp0 = r[num][0];
			for (int i = 0; i < 7; i++) {
				r[num][i] = r[num][i + 1];
			}
			r[num][7] = temp0;
		}
	}

	public static void printR() {
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < 8; j++) {
				// System.out.print(r[i][j] + " ");
				// System.out.println();
			}
		}
	}
}

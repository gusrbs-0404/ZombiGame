package ZombieGame;

import java.util.Random;
import java.util.Scanner;

public class ZombieGameSystem {
	private final int MOVING = 1;
	private final int EXIT = 2;

	private static Scanner scan = new Scanner(System.in);
	private static Random ran = new Random();

	private static Human human = new Human();
	private static Normal normal = new Normal();
	private static Boss boss = new Boss();

	private boolean isrun = true;
	private int potionCount = 3;

	private void ZombieGameSystem() {

	}

	private static ZombieGameSystem instance = new ZombieGameSystem();

	public static ZombieGameSystem getInstance() {
		return instance;
	}

	public static int ranHp() {
		int ranHp = ran.nextInt(human.MAX_HP / 3);

		return ranHp;
	}

	public static int ranAttack() {
		int ranAttack = ran.nextInt(20) + 1;

		return ranAttack;
	}

	public static int ranMoving() {
		int ranMoving = ran.nextInt(3) + 1;

		return ranMoving;
	}

	public void run() {
		while (isrun) {
			menu();

		}
	}

	private void menu() {
		System.out.println("1.이동하기");
		System.out.println("2.종료하기");
		int select = inputNumber("메뉴 선택");

		if (select < 1 || select > 2) {
			System.out.println("메뉴 잘못입력했습니다!");
			return;
		}

		if (select == MOVING) {
			human.moving(human);
		} else if (select == EXIT) {
			System.out.println("좀비게임 종료 합니다!");
			isrun = false;
		}
	}

	private int randomNumber() {
		int ranNum = ran.nextInt(5) + 1; // 1 2 3 4 5
											// 1~3 이동 | 4 좀비 | 5 포션

		return ranNum;
	}

	private int inputNumber(String msg) {
		System.out.print(msg + " : ");
		String input = "";

		input = scan.nextLine();
		int number = -1;
		try {
			number = Integer.parseInt(input);
			return number;
		} catch (Exception e) {
			System.err.println("숫자로 입력하세요.");
		}

		return number;
	}
}

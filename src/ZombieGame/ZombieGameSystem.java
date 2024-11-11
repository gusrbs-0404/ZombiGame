package ZombieGame;

import java.util.Random;
import java.util.Scanner;

public class ZombieGameSystem {
	private static Scanner scan = new Scanner(System.in);
	private static Random ran = new Random();

	private static Human human = new Human();
	private static Normal normal = new Normal();
	private static Boss boss = new Boss();

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
		int num = inputNumber("숫자입력");
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

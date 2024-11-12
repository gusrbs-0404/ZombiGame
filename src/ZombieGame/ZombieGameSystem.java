package ZombieGame;

import java.util.Random;
import java.util.Scanner;

public class ZombieGameSystem {
	private final int MOVING = 1;
	private final int POTION = 2;
	private final int EXIT = 3;

	private final int ATTACK = 1;
	private final int RUNAWAY = 3;

	private static Scanner scan = new Scanner(System.in);
	private static Random ran = new Random();

	private static Human human = new Human();
	private static Normal normal = new Normal();
	private static Boss boss = new Boss();

	private boolean isrun = true;

	private int potionCount = 3;
	private int zombieCount = 0;

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
		System.out.println("2.포션먹기");
		System.out.println("3.종료하기");
		int select = inputNumber("메뉴 선택");

		if (select < 1 || select > 2) {
			System.out.println("메뉴 잘못입력했습니다!");
			return;
		}

		if (select == MOVING) {
			human.moving(human);
			actionMenu();
		} else if (select == POTION) {
			human.potion(human);
		} else if (select == EXIT) {
			System.out.println("좀비게임 종료 합니다!");
			isrun = false;
		}
	}

	private void actionMenu() {
		int actionNumber = actionRandomNumber();
		if (actionNumber >= 1 && actionNumber <= 3) {
			System.out.println("아무일도 없었다.");
		} else if (actionNumber == 4) {
			fighting();
		} else if (actionNumber == 5) {
			plusPotion();
		} else if (actionNumber == 6) {
			trap();
		}
	}

	private void plusPotion() {
		potionCount++;
		System.out.printf("포션을 얻었다!!!\n총 포션 개수 : %d\n", potionCount);
	}

	private void trap() {
		System.out.println("함정에 걸렸다!");
		int hp = ranAttack();
		human.hp -= hp;
		System.out.printf("%s 체력이 %d만큼 까였다...\n", human.name, hp);
		System.out.println(human);
	}

	private void fighting() {
		System.out.println("좀비를 만났다!!");
		boolean isFighting = true;
		while (isFighting) {
			System.out.println("1.공격한다.");
			System.out.println("2.포션먹는다.");
			System.out.println("3.도망간다(hp-30)");

			int select = inputNumber("메뉴 선택");

			if (select == ATTACK) {
				isFighting = attack();
			} else if (select == POTION) {
				potion();
			} else if (select == RUNAWAY) {
				isFighting = runaway();
			}

		}
		// zombieCount++;
	}

	private boolean attack() {
		human.attack(normal);

		if (normal.hp < 0) {
			normal.hp = 0;
		}

		System.out.println(normal);

		normal.attack(human);

		if (human.hp < 0) {
			human.hp = 0;
		}

		System.out.println(human);

		if (human.hp == 0) {
			System.out.println("길동이가 죽었다!\n게임 종료!");

			isrun = false;

			return false;
		}
		if (normal.hp == 0) {
			System.out.println("길동이가 좀비를 죽였다!");

			potionCount++;
			System.out.printf("포션을 1개를 얻었다!!!\n총 포션 개수 : %d\n", potionCount);

			return false;
		}

		return true;
	}

	private void potion() {
		human.potion(human);
	}

	private boolean runaway() {
		if (human.hp > 30) {
			human.hp -= 30;

			System.out.println("무사히 도망쳤다!!");
			System.out.println(human);

			return false;
		} else {
			System.out.println("hp가 부족해서 도망칠 수 없다!");
			return true;
		}

	}

	private int actionRandomNumber() {
		int ranNum = ran.nextInt(6) + 1; // 1 2 3 4 5 6
											// 1~3 이동 | 4 좀비 | 5 포션 | 함정

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

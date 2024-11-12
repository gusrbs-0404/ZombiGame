package ZombieGame;

import java.util.Random;
import java.util.Scanner;

public class ZombieGameSystem {
	private final int MOVING = 1;
	private final int POTION = 2;
	private final int EXIT = 3;

	private final int ATTACK = 1;
	private final int RUNAWAY = 3;

	private final int JOINGMOVING = 3;
	private final int FIGHTING = 4;
	private final int GETPOTION = 5;
	private final int GETTRAP = 6;

	private static Scanner scan = new Scanner(System.in);
	private static Random ran = new Random();

	private static Human human = new Human();
	private static Normal normal;
	private static Boss boss;

	private boolean isrun = true;

	private int potionCount = 3;

	public static int zombieCount = 0;

	public static int normalUpgradeHp = 0;
	public static int normalUpgradeAttack = 0;

	public static int bossUpgradeHp = 0;
	public static int bossUpgradeAttack = 0;

	private void ZombieGameSystem() {

	}

	private static ZombieGameSystem instance = new ZombieGameSystem();

	public static ZombieGameSystem getInstance() {
		return instance;
	}

	public static int ranHp() {
		int ranHp = ran.nextInt(human.MAX_HP / 3) + 10;

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

	public static int probability() {
		int ranprobability = ran.nextInt(15);
		return ranprobability;
	}

	public void run() {
		while (isrun) {
			humanUpgrade();
			menu();

		}
	}

	private void menu() {
		System.out.println("1.이동하기");
		System.out.println("2.포션먹기");
		System.out.println("3.종료하기");
		int select = inputNumber("메뉴 선택");

		if (select < MOVING || select > EXIT) {
			System.out.println("메뉴 잘못입력했습니다!");
			return;
		}

		if (select == MOVING) {
			human.moving(human);
			actionMenu();
		} else if (select == POTION) {
			if (human.hp == human.MAX_HP) {
				System.out.println("풀피라 포션을 먹을수 없다!");
				return;
			}
			human.potion(human);
		} else if (select == EXIT) {
			System.out.println("좀비게임 종료 합니다!");
			isrun = false;
		}
	}

	private void actionMenu() {
		int actionNumber = actionRandomNumber();

		if (actionNumber >= MOVING && actionNumber <= JOINGMOVING) {
			System.out.println("아무일도 없었다.");
		} else if (actionNumber == FIGHTING) {
			if (zombieCount % 3 == 0 && zombieCount != 0) {
				boss = new Boss();
				fighting(boss);
				zombieCount = 0;
			} else {
				normal = new Normal();
				fighting(normal);
			}
		} else if (actionNumber == GETPOTION) {
			plusPotion();
		} else if (actionNumber == GETTRAP) {
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

	private void fighting(Unit unit) {
		System.out.printf("%s를 만났다!!\n", unit.name);

		boolean isFighting = true;
		while (isFighting) {
			System.out.println("1.공격한다.");
			System.out.println("2.포션먹는다.");
			System.out.println("3.도망간다(hp-30)");

			int select = inputNumber("메뉴 선택");

			if (select == ATTACK) {
				isFighting = attack(unit);
			} else if (select == POTION) {
				potion();
			} else if (select == RUNAWAY) {
				isFighting = runaway();
			}

		}
	}

	private boolean attack(Unit unit) {
		boolean isAttack = true;

		human.attack(unit);

		if (unit.hp < 0) {
			unit.hp = 0;
		}

		System.out.println(unit);

		unit.attack(human);

		if (human.hp < 0) {
			human.hp = 0;
		}

		System.out.println(human);

		isAttack = humanDie();

		isAttack = zombieDie(unit);

		return true;
	}

	private boolean humanDie() {
		if (human.hp == 0) {
			System.out.println("길동이가 죽었다!\n게임 종료!");

			isrun = false;

			return false;
		}
		return true;
	}

	private boolean zombieDie(Unit unit) {
		if (unit.hp == 0) {
			zombieCount++;

			System.out.printf("길동이가 %s를 죽였다!\n", unit.name);
			System.out.println("다음에 만날 좀비가 강력해집니다!");

			if (unit instanceof Normal) {
				Normal target = (Normal) unit;
				target.upgrade(unit);
			} else if (unit instanceof Boss) {
				Boss target = (Boss) unit;
				target.upgrade(unit);
			}

			potionCount++;
			System.out.printf("포션을 1개를 얻었다!!!\n총 포션 개수 : %d\n", potionCount);

			return false;
		}
		return true;
	}

	private void potion() {
		if (human.hp == human.MAX_HP) {
			System.out.println("풀피라 포션을 먹을수 없다!");
			return;
		}

		if (potionCount == 0) {
			System.out.println("가지고 있는 포션이 없다...");
			return;
		}

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

	private void humanUpgrade() {
		human.upgrade(human);
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

package ZombieGame;

public class Human extends Unit implements Potion, Moving {

	private int totalMoving = 0;
	private int upgradeCount = 1;
	private int attackUpgrade = 0;

	Human() {
		super("길동이", 200);
	}

	@Override
	public void attack(Unit unit) {
		int attack = ZombieGameSystem.ranAttack() + attackUpgrade;
		String masg = String.format("%s가 %s에게 %d만큼 공격했다!!!!", name, unit.name, attack);
		System.out.println(masg);

		unit.hp -= attack;

		System.out.println("깡!!!!");
	}

	@Override
	public void potion(Human human) {

		int ranHp = ZombieGameSystem.ranHp();

		System.out.printf("포션을 먹었다! %d만큼 획복한다!!\n", ranHp);
		while (ranHp >= 0 && human.hp <= human.MAX_HP) {
			System.out.println(human + "|| [HP] 회복중");
			human.hp++;
			ranHp--;
			try {
				Thread.sleep(300);
			} catch (Exception e) {
			}
		}

		System.out.println("<<< 회복 완료 >>> ");
	}

	@Override
	public void moving(Human human) {
		int moving = ZombieGameSystem.ranMoving();
		totalMoving += moving;

		String masg = String.format("%s가 %d만큼 이동했다.\n총 이동거리 : %d", name, moving, totalMoving);
		System.out.println(masg);
	}

	public void upgrade(Human human) {
		if (totalMoving / 10 == upgradeCount) {
			System.out.println(totalMoving / 10);
			String masg = String.format("%s의 [체력 : 10 | 공격력 : 5] 증가 합니다!", name);
			System.out.println(masg);

			human.MAX_HP += 10;
			upgradeCount++;
			attackUpgrade += 5;
			System.out.println(human);
		}
	}

}

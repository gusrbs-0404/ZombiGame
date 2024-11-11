package ZombieGame;

public class Human extends Unit implements Attack, Potion, Moving {

	private int totalMoving = 0;

	Human() {
		super("길동이", 150);
	}

	@Override
	public void attack(Unit unit) {
		int attack = ZombieGameSystem.ranAttack();
		String masg = String.format("%s가 %s에게 %d만큼 공격했다!!!!", name, unit.name, attack);
		System.out.println(masg);

		unit.hp -= attack;

		System.out.println("깡!!!!");
	}

	@Override
	public void potion(Human human) {
		if (human instanceof Human) {
			Human target = (Human) human;

			int ranHp = ZombieGameSystem.ranHp();

			System.out.printf("포션을 먹었다! %d만큼 획복한다!!\n", ranHp);
			while (ranHp > 0 && target.hp < target.MAX_HP) {
				System.out.println(target + "|| [HP] 회복중");
				target.hp++;
				ranHp--;
				try {
					Thread.sleep(300);
				} catch (Exception e) {
				}
			}

			System.out.println("<<< 회복 완료 >>> ");
		}
	}

	@Override
	public void moving(Human human) {
		int moving = ZombieGameSystem.ranMoving();
		totalMoving += moving;

		String masg = String.format("%s가 %d만큼 이동했다.\n총 이동거리 : %d", name, moving, totalMoving);
		System.out.println(masg);
	}

}

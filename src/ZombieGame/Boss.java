package ZombieGame;

public class Boss extends Unit implements Upgrade, Zombie {

	Boss() {
		super("보스 좀비", (300 + ZombieGameSystem.bossUpgradeHp));
	}

	@Override
	public void attack(Unit unit) {
		int attack = ZombieGameSystem.ranAttack() + ZombieGameSystem.bossUpgradeAttack;
		String masg = String.format("%s가 %s에게 %d만큼 공격했다!!!!", name, unit.name, attack);
		System.out.println(masg);

		unit.hp -= attack;
		System.out.println("쿼어어ㅓ어어!!!!!!");
	}

	@Override
	public int critical() {
		int probability = ZombieGameSystem.probability();

		return probability;
	}

	@Override
	public void upgrade(Unit unit) {
		if (unit instanceof Boss) {
			Boss target = (Boss) unit;

			String masg = String.format("%s의 [최대 체력 : 100 | 공격력 : 20] 증가 합니다!", name);
			System.out.println(masg);
			ZombieGameSystem.bossUpgradeHp += 300;
			ZombieGameSystem.bossUpgradeAttack += 50;
		}

	}

}

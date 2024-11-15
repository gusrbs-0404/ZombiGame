package ZombieGame;

public class Normal extends Unit implements Upgrade {

	Normal() {
		super("일반좀비", (100 + ZombieGameSystem.normalUpgradeHp));
	}

	@Override
	public void attack(Unit unit) {
		int attack = ZombieGameSystem.ranAttack() + ZombieGameSystem.normalUpgradeAttack;

		if (critical() == critical) {
			System.out.println("크리티컬!!!");

			attack += 20;
		}

		String masg = String.format("%s가 %s에게 %d만큼 공격했다!!!!", name, unit.name, attack);
		System.out.println(masg);

		unit.hp -= attack;

		System.out.println("와그작!!");
	}

	@Override
	public int critical() {
		int probability = ZombieGameSystem.probability();

		return probability;
	}

	@Override
	public void upgrade(Unit unit) {
		if (unit instanceof Normal) {

			Normal target = (Normal) unit;

			int zombiecount = ZombieGameSystem.zombieCount;

			if (zombiecount >= 1) {
				String masg = String.format("%s의 [최대 체력 : 50 | 공격력 : 5] 증가 합니다!", name);
				System.out.println(masg);
				ZombieGameSystem.normalUpgradeHp += 50;
				ZombieGameSystem.normalUpgradeAttack += 5;
			}
		}
	}
}

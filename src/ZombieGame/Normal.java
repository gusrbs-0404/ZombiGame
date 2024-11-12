package ZombieGame;

public class Normal extends Unit {

	Normal() {
		super("일반좀비", 100);
	}

	@Override
	public void attack(Unit unit) {
		int attack = ZombieGameSystem.ranAttack();
		String masg = String.format("%s가 %s에게 %d만큼 공격했다!!!!", name, unit.name, attack);
		System.out.println(masg);

		unit.hp -= attack;

		System.out.println("와그작!!");
	}

}

package ZombieGame;

public class Human extends Unit  implements Attack{

	Human() {
		super("길동", 150);
	}

	@Override
	public void attack(Unit unit) {
		System.out.println("깡!!!!");
	}

}

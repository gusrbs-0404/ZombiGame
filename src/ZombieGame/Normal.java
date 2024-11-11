package ZombieGame;

public class Normal extends Unit implements Attack{

	Normal() {
		super("일반좀비", 100);
	}

	@Override
	public void attack(Unit unit) {
		System.out.println("와그작!!");
	}

}

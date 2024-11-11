package ZombieGame;

public class Boss extends Unit implements Attack{

	Boss() {
		super("보스 좀비", 300);
	}

	@Override
	public void attack(Unit unit) {
		System.out.println("쿼어어ㅓ어어!!!!!!");
	}

}

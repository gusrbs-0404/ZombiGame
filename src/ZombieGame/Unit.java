package ZombieGame;

abstract class Unit implements Attack {
	protected final int MAX_HP;
	protected int hp;
	protected String name;

	Unit(String name, int hp) {
		MAX_HP = hp;
		this.hp = hp;
		this.name = name;
	}

	@Override
	public String toString() {
		String massgae = String.format("[%s] | [HP : %d/%d]", name, hp, MAX_HP);
		return massgae;
	}
}

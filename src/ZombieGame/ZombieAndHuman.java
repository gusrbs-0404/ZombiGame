package ZombieGame;

abstract class ZombieAndHuman {
	protected final int MAX_HP;
	protected int hp;
	protected String name;
	
	ZombieAndHuman(String name, int hp){
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

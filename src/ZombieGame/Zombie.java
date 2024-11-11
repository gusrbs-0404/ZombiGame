package ZombieGame;

abstract class Zombie {
	final int MAX_HP;
	int hp;
	String name;
	
	Zombie(String name, int hp){
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

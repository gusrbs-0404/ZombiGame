package ZombieGame;

interface Attack {
	public static final int critical = 1;

	public void attack(Unit unit);

	public int critical();
}

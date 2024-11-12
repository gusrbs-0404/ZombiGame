package ZombieGame;

public class Boss extends Unit implements Zombie{

	Boss() {
		super("보스 좀비", 300);
	}

	@Override
	public void attack(Unit unit) {
		int attack = ZombieGameSystem.ranAttack();
		String masg = String.format("%s가 %s에게 %d만큼 공격했다!!!!", name, unit.name, attack);
		System.out.println(masg);

		unit.hp -= attack;
		System.out.println("쿼어어ㅓ어어!!!!!!");
	}

	@Override
	public void upgrade(Unit unit) {
		// TODO Auto-generated method stub
		
	}
	
	

}

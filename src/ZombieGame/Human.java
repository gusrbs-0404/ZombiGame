package ZombieGame;

public class Human extends Unit  implements Attack, Potion{

	Human() {
		super("길동이", 150);
	}

	@Override
	public void attack(Unit unit) {
		int attack = ZombieGameSystem.ranAttack();
		String masg = String.format("%s가 %s에게 %d만큼 공격했다!!!!", name, unit.name,attack);
		System.out.println(masg);
		
		unit.hp -= attack;
		
		System.out.println("깡!!!!");
	}

	@Override
	public void potion(Human human) {
		if (human instanceof Human) {
			Human target = (Human) human;
			
			int ranHp = ZombieGameSystem.ranHp();
			
			while (target.hp < target.MAX_HP) {
				System.out.println(target + "|| [HP] 회복중");
				target.hp++;
				ranHp --;
				try {
					Thread.sleep(300);
				} catch (Exception e) {
				}
			}


			System.out.println("<<< 회복 완료 >>> ");
		}
	}

}

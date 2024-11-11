package ZombieGame;

public class Human extends Unit  implements Attack, Potion{

	Human() {
		super("길동", 150);
	}

	@Override
	public void attack(Unit unit) {
		System.out.println("깡!!!!");
	}

	@Override
	public void potion(Human human) {
		if (human instanceof Human) {
			Human target = (Human) human;
			
			int ranNum = ZombieGameSystem.ranHp();
			
			while (target.hp < target.MAX_HP) {
				System.out.println(target + "|| [HP] 회복중");
				target.hp++;
				ranNum --;
				try {
					Thread.sleep(300);
				} catch (Exception e) {
				}
			}


			System.out.println("<<< 회복 완료 >>> ");
		}
	}

}

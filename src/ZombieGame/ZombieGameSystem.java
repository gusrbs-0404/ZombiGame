package ZombieGame;

import java.util.Random;
import java.util.Scanner;

public class ZombieGameSystem {
	private static Scanner scan = new Scanner(System.in);
	private static Random ran = new Random();
	private static Human human = new Human();
	private static Normal normal = new Normal();
	private static Boss boss = new Boss();
	
	private void ZombieGameSystem()	{

	}
	
	private static ZombieGameSystem instance = new ZombieGameSystem();
	
	public static ZombieGameSystem getInstance() {
		return instance;
	}
	
	public static int ranHp() {
		int ranNum = ran.nextInt(human.MAX_HP)/3;
		
		return ranNum;
	}
	
	public void run() {
		// 
	}
}

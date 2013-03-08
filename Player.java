package rogue;
import java.util.ArrayList;

public class Player {
	private ArrayList <String> inventory = new ArrayList <String>();
	private int gold;
	private int arm;
	private int hp;
	private int str;
	private int exp;
	private static int  sightRadius = 4, totalxp, level;
	public Player()
	{
		//inventory.add(new Item(0));
		// Level = 1, gold = 0, Hp = 20, Str = 1, Arm = 1, Exp = 0
		level = 1; gold = 0; hp = 20; str = 1; arm = 1; exp = 0;
		
	}
	
	
	public void giveXP(int xp) // we will get this XP from the Monster's class
	{
		totalxp += xp;
		if(totalxp > 1000*level)
		{
			totalxp-=1000*level;
			level ++;
		}
	}
	
	
	public static int getSight()
	{
		return sightRadius;
	}
	
	// set() methods for the bottom line information on the screen
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void setStr(int str) {
		this.str = str;
	}
	
	public void setArm(int arm) {
		this.arm = arm;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	// get() methods for the bottom line information on the screen
	
	public int getLevel() {
	return level;	
	}
	
	public int getGold() {
		return gold;	
		}
	
	public int getHp() {
		return hp;	
		}
	
	public int getStr() {
		return str;	
		}
	
	public int getArm() {
		return arm;	
		}
	
	public int getExp() {
		return exp;	
		}
	//static Player player = new Player(1, 1); // temporary}
	public static void main(String[] args) {
		Player a = new Player ();
		a.setArm(2);
		System.out.println(a.getArm());

	}

}

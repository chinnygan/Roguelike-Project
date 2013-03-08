package rogue;

public class Combat {
	  

	private static double hp; //player's health
	private static double lvl;   //which level the player's on

	private static double damage;
	//the amount of damage the player/hostile can cause

	private int itemValue; 
	//can be a multiplier for damage, imported from Item text file

	public static double damage(){
		double multiplier = (double)(Math.random() * (lvl/10));
		damage = multiplier * 100;
		return damage;
	}

	public static double returnHp(){
	//calculates new hp of player, given current health (double hp) and current level (int lvl) in the game	

		hp = hp  - damage(); //sets player's health to new value after fight
		return hp;		   //also returns player's health (double hp)

	}

	//test
	public static void main(String[] args) {
		hp = 100.0;
		lvl = 7.0;
		System.out.println((Math.round(returnHp())*100)/100);
	}



}
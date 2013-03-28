public class Combat {
	  

	private static double hp; //player's health
	private static double lvl;   //which level the player's on

	private static double damage;
	//the amount of damage the player/hostile can cause

	private int itemValue; 
	//can be a multiplier for damage, imported from Item text file

	public static void calculateXP(Player p, int mLevel){
		int base = 300;
		int pLevel = p.getLevel();
		int newXP = 0;

		if ( pLevel<=3 && mLevel<=3 && pLevel != mLevel){
			newXP = pLevel*base;

		}else if ( pLevel == mLevel) {
			newXP = pLevel*base;

		}else {
			newXP = (mLevel-pLevel)*base/2 + pLevel*base; 
		}

		giveXP(p, newXP);

	}

	public static void giveXP(Player p, int xp){

		p.setXP(p.getXP() + xp);

	}
	
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
		Player p = new Player(0,0);
		int monster = 3;
		calculateXP(p,5);
	}

	public static void heal(int pow, Player p) {
		p.setHp(p.getHp() + pow);
		
	}



}





	


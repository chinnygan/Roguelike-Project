
public class Combat {
	
	
	
	
	public int calculateXP(Player p, int mLevel){
		int base = 300;
		int pLevel = Player.getLevel(p);
		int newXP = 0;
		
		if ( pLevel<=3 && mLevel<=3 && pLevel != mLevel){
			newXP = pLevel*base;
			
		}else if ( pLevel == mLevel) {
			newXP = pLevel*base;
			
		}else {
			newXP = (mLevel-pLevel)*base/2 + pLevel*base; 
		}
		
		return newXP;
		
	}
	
	public int giveXP(Player p, Monster m){
		
		int mLevel = Monster.getLevel(m);
		return Player.getXP(p) + calculateXP(p, mLevel);
		
	}
	
	

import java.util.ArrayList;

public class Player extends Agent{
	private ArrayList <Item> inventory = new ArrayList <Item>();
	private int gold, arm, hp, str, exp, level, sightRadius = 4, totalxp, x, y, weapon, atk;
	private Item eqweapon, eqarmor;
	
	public Player(int x, int y) {
		super(x,y);
		inventory.add(new Item(Client.getItemResource(),0));
		inventory.add(new Item(Client.getItemResource(),1));
		level = 1; gold = 0; hp = 20; str = 1; arm = 1; exp = 0;
		
	}
	
	public void equipWeapon(Item r)	{
		if(inventory.contains("Weapon") && r.gettype() == "Weapon")
		{inventory.remove("Weapon");
		eqweapon=r;
		}
	}
	
	public void equipArmor(Item r)	{
		if(inventory.contains("Armor") && r.gettype() == "Armor")
		{inventory.remove("Armor");
		eqarmor=r;
		}
		
	}
	
	
	public void combatDmg(){
		
	}
	
	public void setXP(int xp) // we will get this XP from the Monster's class
	{
		totalxp += xp;
		if(totalxp > 1000*level)
		{
			totalxp-=1000*level;
			level ++;
		}
	}
	
	
	public int getSight()
	{
		return sightRadius;
	}
	
	// set() methods for the bottom line information on the screen
	
	public void setX(int x){
		this.x = x;	
	}
	
	public void setY(int y){
		this.y = y;	
	}
	
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
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
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
	public int getXP() {
		return totalxp;	
		}
	
	public static void main(String[] args) {
		Player a = new Player (0,0);
		a.setArm(2);
		System.out.println(a.getArm());

	}


	public void useFirstConsumable() {
		//uses first item in inventory which is of the 'consumable' type
		
	}

	public void removeWeapon() {
		// removes item from equipped slot and places it in the player's inventory
	}
	
	public void removeArmour() {
		// removes item from equipped slot and places it in the player's inventory
	}
	
	public void moveUp() {
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,0);
		super.moveUp();	
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,1);
	}

	public void moveUpRight() {
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,0);
		super.moveUpRight();
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,1);
	}


	public void moveUpLeft() {
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,0);
		super.moveUpLeft();
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,1);
	}
	
	public void moveLeft() {
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,0);
		super.moveLeft();
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,1);
	}
	public void moveRight() {
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,0);
		super.moveDownRight();
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,1);

	}
	public void moveDown() {
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,0);
		super.moveDown();
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,1);

	}
	
	public void moveDownLeft() {
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,0);
		super.moveDownLeft();
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,1);

	}
	
	public void moveDownRight() {
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,0);
		super.moveDownRight();
		Client.getBoardList().get(Client.getCurrentFloor()).setTileCreatureid(x,y,1);

	}
}

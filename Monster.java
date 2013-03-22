import java.util.ArrayList;

public class Monster extends Agent {

	private int id, lvl, boardNum, def, hp;
	private String name, description, dmg, code;

	// Constructor for the monster object
	// TODO cause id to be read in from the text file
	public Monster(int x, int y, int id, int boardNumber) {
		super(x, y);
		this.id = id;
		this.boardNum = boardNumber;

		int i = 0;
		ArrayList<String> raw = new ArrayList<String>();
		In in = new In("C:/Users/Scott/Desktop/OOP/Roguelike/monsters.txt");
		while (in.hasNextLine()) {
			raw.add(i, in.readLine());
			i++;
		}
		decrypt(raw.get(id));
	}

	public void decrypt(String s) {
		name = s.substring(0, s.indexOf('('));
		s = s.substring(s.indexOf('(') + 1, s.indexOf(')'));
		dmg = (s.substring(0, s.indexOf(',')));
		s = ItemResource.chop(s);
		def = Integer.parseInt(s.substring(0, s.indexOf(',')));
		s = ItemResource.chop(s);
		hp = Integer.parseInt(s.substring(0, s.indexOf(',')));
		s = ItemResource.chop(s);
		setCode(s.substring(0, s.indexOf(',')));
		s = ItemResource.chop(s);
		lvl = Integer.parseInt((s.substring(0, s.indexOf(','))));
		s = ItemResource.chop(s);

		description = (s.substring(0, s.length()));
	}

	// Finds the distance between the Monster and the player, and determines
	// whether Monster attacks or moves towards the player depending on this
	// distance
	public void behaviour(Player p) {
		int xDif = this.getX() - p.getX();
		int yDif = this.getY() - p.getY();

		// Checks that the monster is in attack distance AND that it is not on a
		// diagonal to the player
		if (Math.abs(xDif) <= 1 && Math.abs(yDif) <= 1
				&& Math.abs(xDif) != Math.abs(yDif)) {
			attack();
		} else {
			moveTowardsPlayer(xDif, yDif);
		}
	}

	// Placeholder for calling combat functions
	public void attack() {
		System.out.println("Monster Attacks!");
	}

	// TODO Accommodate for surfaces such as walls that cannot be moved through

	// Measures the greatest distance between Player and Monster and moves
	// towards the player one block to reduce it (and so moves towards the
	// player
	public void moveTowardsPlayer(int xDif, int yDif) {
		int xAbs = Math.abs(xDif);
		int yAbs = Math.abs(yDif);

		// Possibly going to add a case of when xAbs == yAbs (the monster is on
		// a diagonal to the player), but for now it seems
		// unnecessary and defaults to a movement on the y axis
		if (xAbs > yAbs) {
			if (xDif < 0) {
				this.moveRight();
			} else {
				this.moveLeft();
			}
		} else {
			if (yDif < 0) {
				this.moveDown();
			} else {
				this.moveUp();
			}
		}
	}

	// TODO create a function that boosts the damage negatively or positively
	// Buffs the monsters statistics by the "boost", then changes the name of
	// the monster to reflect this boost
	public void buff(int boost) {

		this.def += boost;
		this.hp += boost;
		this.lvl += boost;

		this.name = this.name + " (buffed " + boost + ")";
	}

	// Creates a random monster
	public static Monster randomMonster(int xVal, int yVal, Player p,
			int boardNumber) {

		// TODO find total lines of monsters.text
		// The value this is multiplied should be changed by the number of lines
		// in monsters.txt
		int randomID = (int) (Math.random() * 3);

		Monster ranMon = new Monster(xVal, yVal, randomID, boardNumber);

		int levelDif = p.getLevel() - ranMon.getLevel();

		// TODO Create damage boost function
		if (levelDif >= 5 || levelDif <= -5) {
			ranMon.buff(levelDif);
		}

		return ranMon;
	}

	// Testing functions for the Monster
	public static void main(String[] args) {

		Player player = new Player();

		Monster monster1 = Monster.randomMonster(5, 5, player, 1);

		System.out.println("Name: " + monster1.getName());
		System.out.println("Damage: " + monster1.getDmg());
		System.out.println("Defence: " + monster1.getDef());
		System.out.println("Level: " + monster1.getLevel());
		System.out.println("Hitpoints: " + monster1.getHp());
		System.out.println("Description: " + monster1.getDescription());

		System.out.println();

		// Set the Player's x & y values here
		player.setX(0);
		player.setY(0);

		System.out.println("Player starts (" + player.getX() + " , "
				+ player.getY() + ")");
		System.out.println("Monster starts (" + monster1.getX() + " , "
				+ monster1.getY() + ")");

		System.out.println();

		for (int i = 0; i < 10; i++) {
			monster1.behaviour(player);

			System.out.println("Monster at (" + monster1.getX() + " , "
					+ monster1.getY() + ")");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return lvl;
	}

	public void setLevel(int lvl) {
		this.lvl = lvl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public void setDmg(String dmg) {
		this.dmg = dmg;
	}

	public int getDef() {
		return def;
	}

	public String getDmg() {
		return dmg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
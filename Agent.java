//A base class for the agents(Player and Monsters)
//Will most likely be changed a lot
//It's not as lovely as it needs to be

public abstract class Agent {
	private int x;
	private int y;
	
	public Agent(int x, int y) {
		this.x = y;
		this.y = y;
	}

	//Set values for x & y
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	//Retrieve values for x & y
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	//Movement of the agent
	public void moveUp() {
		if (canmove(Room.inRange(this.x),Room.inRange(this.y - 1))) {
			y--;
		}
	}

	private boolean canmove(int x, int y) {
			if (Client.getBoardList().get(Client.getCurrentFloor()).getTileType(x, y) == '~' && Client.getBoardList().get(Client.getCurrentFloor()).getTileCreatureId(x,y) == 0) {
				return true;
			}
			return false;
	}

	public void moveDown() {
		if (canmove(Room.inRange(this.x),Room.inRange(this.y + 1))) {
		y++;
		}
	}

	public void moveRight() {
		if (canmove(Room.inRange(this.x + 1),Room.inRange(this.y))) {
		x++;
		}
	}

	public void moveLeft() {
		if (canmove(Room.inRange(this.x - 1),Room.inRange(this.y))) {
		x--;
		}
	}

	public void moveUpRight() {
		if (canmove(Room.inRange(this.x + 1),Room.inRange(this.y - 1))) {
		y--;
		x++;
		}
		
	}

	public void moveUpLeft() {
		if (canmove(Room.inRange(this.x - 1),Room.inRange(this.y - 1))) {
		y--;
		x--;
		}
	}

	public void moveDownRight() {
		if (canmove(Room.inRange(this.x + 1),Room.inRange(this.y + 1))) {
		y++;
		x++;
		}
	}

	public void moveDownLeft() {
		if (canmove(Room.inRange(this.x - 1),Room.inRange(this.y + 1))) {
		y++;
		x--;
		}
	}

}

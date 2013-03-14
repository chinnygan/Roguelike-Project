//A base class for the agents(Player and Monsters)
//Will most likely be changed a lot
//It's not as lovely as it needs to be

public class Agent {
	
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
	public int moveUp() {
		return y--;
	}

	public int moveDown() {
		return y++;
	}

	public int moveRight() {
		return x++;
	}

	public int moveLeft() {
		return x--;
	}

}

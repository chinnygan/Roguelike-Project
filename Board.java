package rogue;

//Other classes can access the information by using the various getXXXX functions I've written. Please check which ones are
//already written to avoid excess code in other classes. e.g. using Board.xyDist(x1,x2,y1,y2) instead of writing a method
//to the same effect in another class.

//Known errors:
//	.determineVisible does not act as intended. Compiles just fine, however.	

//Update 1: Fixed mistakes in .get and .set methods. Fixed compilation problem
//Update 2: Added a .toString() method to simplify creation of JFrame over using .printboard()
//Update 3: Added a prototype function for shaping the map. Needs work. Also added function for printing
//       the true contents of a board.
//Update 4: New approach for map. Now starts with a blank grid then places room and corridors. WIP.
//	    Added Room Class to make this task easier, as well as a merging method to Board.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
	private int[][] tileCreatureid = new int[50][50];
	private int xlimit = 10, ylimit = 10, i, j = 0;
	private char[][] tileType = new char[ylimit][xlimit], visTile = new char[ylimit][xlimit];
	private Item[][] tileItem = new Item[ylimit][xlimit];
	private boolean[][] shapeable = new boolean[ylimit][xlimit];

	//creates empty board
	public Board() {
		for (i = 0; i < ylimit ;i++) {
			for (j = 0; j < xlimit ; j++) {
				tileItem[i][j] = null;
				tileCreatureid[i][j] = 0; // represents empty square
				tileType[i][j] = ' ';
				visTile[i][j] = ' ';
				shapeable[i][j] = true;
			}
		}
	}

	// creates board given the values of another board. Effectively clones a board.
	public Board(int xlimit, int ylimit, char[][] tileType, Item[][] tileItem, int[][] tileCreatureid, boolean[][] shapable, char visTile[][]) {
		this.xlimit = xlimit;
		this.ylimit = ylimit;
		for (i=0;i<ylimit;i++) {
			for (j=0;j<xlimit;j++) {
				this.tileCreatureid[i][j] = tileCreatureid[i][j];
				this.tileType[i][j] = tileType[i][j];
				this.tileItem[i][j] = tileItem[i][j];
				this.shapeable[i][j] = shapable[i][j];
				this.visTile[i][j] = visTile[i][j];
			}
		}
	}


	//sets the character shown in a tile to the character representation of the Agent.

		public void setAgent(int id, int x, int y) {
		  if (tileType[y][x] != ' ') {
		   tileCreatureid[y][x] = id;
		  }
		 }


	//determines linear distance between two tiles
	public static double xyDist (int x1, int x2, int y1, int y2) {
		return Math.sqrt(((x2 - x1) * (x2 - x1)) - ((y2 - y1) * (y2 - y1))); 
	}

	// uses the player's current position and sight radius to determine which tiles can currently be seen
	public void determineVisible(int sightradius, int playerX, int playerY) {;
			char creatureChar;
			for (i = 0; i < ylimit;i++) {
				for (j = 0; j < xlimit; j++) {
					if (xyDist(playerX, playerY, j, i) <= sightradius) {
						if (tileCreatureid[i][j] == 0 && tileItem[i][j] == null) {
							visTile[i][j] = tileType[i][j];
						} else {
							if (tileCreatureid[i][j] == 0 &&tileItem[i][j] != null) {
								visTile[i][j] = tileItem[i][j].getrep();
							}
							else {
								if (tileCreatureid[i][j] != 0) {
									switch (tileCreatureid[i][j]) {
									case 1:
										 creatureChar = '@';
										break;
									default: creatureChar = ' ';
											}
								visTile[i][j] = creatureChar;
								}
							}
						}
						}
					}

				}
					System.out.println();
			} 



	//prints the board as the player sees it.
	public void printBoard() {
		this.determineVisible(Player.getSight(),Agent.getX(), Agent.getY()); // will use .get methods in final version
		for (i = 0; i < ylimit;i++) {
			for (j = 0; j < xlimit; j++) {
				System.out.print(visTile[i][j]);
			}
			System.out.println();
		}
	}

	//prints the board as though the player had a sight radius of 1000.
	public void printTrueBoard() {
		char creatureChar;
		for (i = 0; i < ylimit;i++) {
			for (j = 0; j < xlimit; j++) {
					if (tileCreatureid[i][j] == 0 && tileItem[i][j] == null) {
						visTile[i][j] = tileType[i][j];
						System.out.print('@');//just check it should be removed :P
					} else {
						if (tileCreatureid[i][j] == 0 &&tileItem[i][j] != null) {
							visTile[i][j] = tileItem[i][j].getrep();
						}
						else {
							if (tileCreatureid[i][j] != 0) {
								switch (tileCreatureid[i][j]) {
								case 1:
									 creatureChar = '@';
									break;
								default: creatureChar = ' ';
										}
							visTile[i][j] = creatureChar;
							}
						}

				}
					System.out.print(visTile[i][j]);
			}
				System.out.println();
		}
	}

	public String toString() {
		this.determineVisible(1, 1, 1); // will use .get methods in final version
		String out = "";
		for (i = 0; i < ylimit;i++) {
			for (j = 0; j < xlimit; j++) {
				out = out + visTile[i][j];
			}
			out = out + "\r";
		}
		return out;
	}

	public static void main(String[] args){
		Board a = new Board();
		a.printTrueBoard();
	}

	//fills the map with certain rooms depending on the type of floor generated within this method
	// 1: normal
	// 2: open area with enclosed rooms
	// 3: labyrinth
	// 4: arena
	// 5: haven
	public void shape() {
		int floortype = (int) Math.ceil(Math.random() * 5);

		switch (floortype) {
		case 1: shapenormal();
				addmonsters();
				additems();
			break;
		case 2: shapenormal();
				openup();
				addmonsters();
				additems();
			break;
		case 3: shapelabyrinth();
				addmonsters();
				additems();
			break;
		case 4: shapearena();
				addmonsters();
				additems();
			break;
		case 5: shapenormal();
				additems();
			break;
		}
	}

	//sets all shapeable tiles to ~
	private void openup() {
		// TODO Auto-generated method stub

	}

	//generates a bunch of items and places them on the board
	private void additems() {
		// TODO Auto-generated method stub

	}

	//generates a bunch of monsters and places them on the board
	private void addmonsters() {
		// TODO Auto-generated method stub

	}

	//shapes as one large circular room, adds more monsters than normal
	private void shapearena() {
		// TODO Auto-generated method stub

	}

	//shapes as a series of weaving corridors
	private void shapelabyrinth() {
		// TODO Auto-generated method stub

	}

	//places a random number of rooms and connects them
	private void shapenormal() {

		int n, number = (int) (Math.ceil(Math.random() * 10)) + 5;
		ArrayList<Room> r = new ArrayList<Room>();
		int centreX = (int) Math.floor(Math.random() * (xlimit - 5) ) + 2;
		int centreY = (int) Math.floor(Math.random() * (ylimit - 5) ) + 2;

		for (n=0;n<=number;n++) {
			Room temp = new Room(centreY, centreX);
			if (Math.abs(temp.getCentreX() - centreX) >= 5 && Math.abs(((Room) temp).getCentreY() - centreY) >= 5) {
				r.add(n,temp);
				mergeRoom(r.get(n));
			}
		}

	}

	//places a room a room on the board
	private void mergeRoom(Room room) {
		for (i=0;i<ylimit;i++) {
			for (j=0;j<xlimit;j++) {
				// lotsa code will go here
			}
		}

	}

	//obsolete, being kept for reference
	public void placeSquare(int centreX, int centreY, int radius) {


		for (i = centreY - radius; i <= centreY + radius; i++) {
			for (j = centreX - radius; j <= centreX + radius; j++) {
				if ((centreX - j >= 0) && (centreX + j <= xlimit - 1) && (centreY - i >= 0) && (centreY + i <= ylimit - 1)) {
					tileType[i][j] = '~';
				}
			}
		}

		for (i = centreY - radius - 1; i <= centreY + radius + 1; i++)
			for (j = centreX - radius - 1; j <= centreX + radius + 1; j++) {
				if ((centreX - j >= 0) && (centreX + j <= xlimit - 1) && (centreY - i >= 0) && (centreY + i <= ylimit - 1)) {
					shapeable[i][j] = false;
				}

			}
	}

	// various .get / .set methods
	public int getWidth() {
		return xlimit;
	}

	public int getHeight() {
		return ylimit;
	}

	public int getAgent(int x, int y) {
		return tileCreatureid[y][x];
	}

	public void setTileType(int x, int y, char type) {
		tileType[y][x] = type;
	}	

	public int getTileType(int x, int y) {
		return tileType[y][x];
	}	

	public Item getItem(int x, int y) {
		return tileItem[y][x];
	}	

	public void setItem(int x, int y, Item item) {
		tileItem[y][x] =  item;
	}
}
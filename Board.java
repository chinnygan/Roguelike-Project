import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class Board extends JPanel{
	private int[][] tileCreatureid = new int[30][30];
	private int xlimit = 30, ylimit = 30, i, j = 0;
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
		
		Room[] rooms;
		int[] centreXs;
		int[] centreYs;
		int floorType = (int) Math.floor((Math.random() * 4));
		
		switch (floorType) {
			case 0: shapenormal();
				break;
			case 1: shapeOpen();
				break;
			case 2: shapearena();
				break;
			case 3: shapelabyrinth();
				break;
			default: shapenormal();
				break;
		}
		int currentfloor = Client.getCurrentFloor();
		if (currentfloor == 0) {
			placeUpStairs();
		} else {
			placeUpStairs();
			placeDownStairs();
		}
		additems();
		addmonsters();
		
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
				this.shapeable[i][j] = shapeable[i][j];
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
		return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))); 
	}
	
	// uses the player's current position and sight radius to determine which tiles can currently be seen
	public void determineVisible() {
			int playerX = Client.getPlayer().getX();
			int playerY = Client.getPlayer().getY();
			int sightradius = Client.getPlayer().getSight();
			char creatureChar;
			for (i = 0; i < ylimit;i++) {
				for (j = 0; j < xlimit; j++) {
					//if (xyDist(playerX, j, playerY, i) <= sightradius) {
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
				//	}
					
				}
					System.out.println();
			} 

	
	
	//prints the board as the player sees it.
	public void printBoard(Player player) {
		this.determineVisible(); // will use .get methods in final version
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
					System.out.print(visTile[i][j] + " ");
			}
				System.out.println();
		}
	}

	public String toString() {
		this.determineVisible(); // will use .get methods in final version
		String out = "<HTML>";
		for (i = 0; i < ylimit;i++) {
			for (j = 0; j < xlimit; j++) {
				if (tileType[i][j] == ' ') {
					out = out + "<SPACE_SEPARATOR>";
				} else {
				out = out + visTile[i][j] + " ";
				}
				out = out + "<SPACE_SEPARATOR>";
			}
			out = out + "<BR>";
		}
		out = out + "<HTML>";
		return out;
	}
			
	public static void main(String[] args){

	}
	
	//sets all shapeable tiles to ~
	private void shapeOpen() {
		shapenormal();
		shapenormal();
		for (i=0;i<ylimit;i++) {
			for (j=0;j<xlimit;j++) {
				if (shapeable[i][j] == true) {
					tileType[i][j] = '~';
				}
			}
		}
	}

	//generates a bunch of items and places them on the board
	private void additems() {
		double chance;
		for (i=0;i<ylimit;i++) {
			for (j=0;j<xlimit;j++) {
				chance = Math.random();
				if (chance <= 0.05) {
					tileItem[i][j] = Item.randomItem();
				}
			}
		}
	}

	//generates a bunch of monsters and places them on the board
	private void addmonsters() {
		double chance;
		for (i=0;i<ylimit;i++) {
			for (j=0;j<xlimit;j++) {
				chance = Math.random();
				if (chance <= 0.05) {
					int listposition = Client.getMonsterListSize();
					Client.getMonsterList().add(listposition, Monster.randomMonster(j, i, Client.getPlayer()));
				}
			}
		}
	}
		
	

	//shapes as one large circular room, adds more monsters than normal
	private void shapearena() {
		Room r = new Room(12, 12, (int) (4 + Math.floor(Math.random() * 5)));
		mergeRoom(r);
	}

	//shapes as a series of weaving corridors
	private void shapelabyrinth() {

		int i,j,n, pointCount = (int)(Math.random() * 10) + 5;
		int[] pointXs = new int[pointCount], pointYs = new int[pointCount];
		
		for (n=0; n < pointCount; n++) {
			pointXs[n] = (int) Math.floor(Math.random() * (xlimit - 1));
			pointYs[n] = (int) Math.floor(Math.random() * (ylimit - 1));
		}
		for (n=0; n < pointCount - 1; n++) {
			connectPoints(pointXs[n],pointXs[n+1],pointYs[n],pointYs[n+1]);
		}
	}

	private void connectRooms(Room r1, Room r2) {
		boolean xFirst = false, yFirst = false;
		int roomCount = (int) (Math.random() * 10) + 5;
		int[] x = new int[roomCount], y = new int[roomCount];
		int i,j, x1 = r1.getCentreX(), y1 = r1.getCentreY(), x2 = r2.getCentreX(), y2 = r2.getCentreY();
		
		for (i=0;i<roomCount;i++) {
			x[i] = (int) Math.random() * xlimit;
			y[i] = (int) Math.random() * ylimit;
		}
		
		int rand = (int) Math.random();
		
		switch (rand) {
			case 0: xFirst = true;
				break;
			case 1: yFirst = true;
				break;
			default: xFirst = true;
				break;
		}
		
		if (xFirst == true) {
			i = y1;
			if (x2 >= x1) {
				for (j=x1;j<=x2;j++) {
					tileType[i][j] = '~';
				}
				j = x2;
				if (y2>=y1) {
					for (i=y1;i<=y2;i++) {
						tileType[i][j] = '~';
					}
				}
				if (y1>=y2) {
					for (i=y2;i<=y1;i++) {
						tileType[i][j] = '~';
					}
				}
			}
			
			if (x2 <= x1) {
				for (j=x2;j<=x1;j++) {
					tileType[i][j] = '~';
				}
				j = x1;
				if (y2>=y1) {
					for (i=y1;i<=y2;i++) {
						tileType[i][j] = '~';
					}
				}
				if (y1>=y2) {
					for (i=y2;i<=y1;i++) {
						tileType[i][j] = '~';
					}
				}
			}
		}
		
		if (yFirst == true) {
			j = x1;
			if (y2 >= y1) {
				for (i=y1;i<=y2;i++) {
					tileType[i][j] = '~';
				}
				i = y2;
				if (x2>=x1) {
					for (j=x1;j<=x2;j++) {
						tileType[i][j] = '~';
					}
				}
				if (x1>=x2) {
					for (j=x2;j<=x1;j++) {
						tileType[i][j] = '~';
					}
				}
			}
			
			if (y2 <= y1) {
				for (i=y2;i<=y1;i++) {
					tileType[i][j] = '~';
				}
				j = x1;
				if (y2>=y1) {
					for (j=x1;j<=x2;j++) {
						tileType[i][j] = '~';
					}
				}
				if (y1>=y2) {
					for (j=x2;i<=x1;j++) {
						tileType[i][j] = '~';
					}
				}
			}
		}
	}
	
	private void connectPoints(int x1, int x2, int y1, int y2) {
		boolean xFirst = false, yFirst = false;
		int roomCount = (int) (Math.random() * 10) + 5;
		int[] x = new int[roomCount], y = new int[roomCount];
		int i,j;
		
		for (i=0;i<roomCount;i++) {
			x[i] = (int) Math.random() * xlimit;
			y[i] = (int) Math.random() * ylimit;
		}
		
		int rand = (int) Math.random();
		
		switch (rand) {
			case 0: xFirst = true;
				break;
			case 1: yFirst = true;
				break;
			default: xFirst = true;
				break;
		}
		
		if (xFirst == true) {
			i = y1;
			if (x2 >= x1) {
				for (j=x1;j<=x2;j++) {
					tileType[i][j] = '~';
				}
				j = x2;
				if (y2>=y1) {
					for (i=y1;i<=y2;i++) {
						tileType[i][j] = '~';
					}
				}
				if (y1>=y2) {
					for (i=y2;i<=y1;i++) {
						tileType[i][j] = '~';
					}
				}
			}
			
			if (x2 <= x1) {
				for (j=x2;j<=x1;j++) {
					tileType[i][j] = '~';
				}
				j = x1;
				if (y2>=y1) {
					for (i=y1;i<=y2;i++) {
						tileType[i][j] = '~';
					}
				}
				if (y1>=y2) {
					for (i=y2;i<=y1;i++) {
						tileType[i][j] = '~';
					}
				}
			}
		}
		
		if (yFirst == true) {
			j = x1;
			if (y2 >= y1) {
				for (i=y1;i<=y2;i++) {
					tileType[i][j] = '~';
				}
				i = y2;
				if (x2>=x1) {
					for (j=x1;j<=x2;j++) {
						tileType[i][j] = '~';
					}
				}
				if (x1>=x2) {
					for (j=x2;j<=x1;j++) {
						tileType[i][j] = '~';
					}
				}
			}
			
			if (y2 <= y1) {
				for (i=y2;i<=y1;i++) {
					tileType[i][j] = '~';
				}
				j = x1;
				if (y2>=y1) {
					for (j=x1;j<=x2;j++) {
						tileType[i][j] = '~';
					}
				}
				if (y1>=y2) {
					for (j=x2;i<=x1;j++) {
						tileType[i][j] = '~';
					}
				}
			}
		}
	}
	
		// copy the same snippet and make appropriate changes for Y-first.
			
		
		// connects tiles e.g.
		//
		//				 ~~~P~
		//			   ~~~
		//		     ~~~
		//		  ~~~~		 
		//		P~~
		//
		// where P represent
		

	//places a random number of rooms and connects them
	private void shapenormal() {

		int n, number = (int) (Math.floor(Math.random() * 7)) + 5;
		ArrayList<Room> rooms = new ArrayList<Room>();
		int rand;
		for (n=0;n<number;n++) {
			rand = (int)Math.floor(Math.random() * 2);
			switch (rand) {
			case 1: rooms.add(n,Room.randomCircularRoom());
				break;
			case 2: rooms.add(n,Room.randomSquareRoom());
				break;
			default:
				rooms.add(n,Room.randomSquareRoom());
				break;
			}
			mergeRoom(rooms.get(n));
		}
		for (Room r: rooms) {
			connectRooms(r, nextRoomInArrayList(rooms, rooms.indexOf(r)));
			connectRooms(r, priorRoomInArrayList(rooms, rooms.indexOf(r)));
			
		}
		
	}

	//places a room a room on the board
	public void mergeRoom(Room room) {
		int k,l;
		
		for (i=0;i<ylimit;i++) {
			for (j=0;j<xlimit;j++) {
				if (shapeable[i][j] == true) {
					tileType[i][j] = room.getTile(i, j);
					}
				}
			}
		for (i=0;i<ylimit;i++) {
			for (j=0;j<xlimit;j++) {
				if (tileType[i][j] == '~') {
					for (k=inRange(i-1);k<inRange(i+1);k++) {
						for (l=inRange(j-1);l<inRange(j+1);l++) {
							shapeable[inRange(i)][inRange(l)] = false;
						}
					}
				}
			}
		}
	}

	public Room priorRoomInArrayList(ArrayList<Room> r, int pos) {
		if (pos == 0) {
			return r.get(r.size() - 1);
		}
		return r.get(pos-1);
	}
	
	public Room nextRoomInArrayList(ArrayList<Room> r, int pos) {
		if (pos == r.size() - 1) {
			return r.get(0);
		}
		return r.get(pos+1);
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
	
	public int inRange(int n) {
		if (n < 0) {
			return 0;
		}
		if (n > xlimit) {
			return xlimit - 1;
		}
		return n;
	}
	
	public void placeUpStairs() {
		boolean placed = false;
		int randomX, randomY;
		while (placed == false) {
			randomX = (int) Math.floor(Math.random() * xlimit);
			randomY = (int) Math.floor(Math.random() * ylimit);
			if (tileType[randomY][randomX] == '~') {
				tileType[randomY][randomX] = '>';
				placed = true;
			}
		}
	}
	
	public void placeDownStairs() {
		boolean placed = false;
		int randomX, randomY;
		while (placed == false) {
			randomX = (int) Math.floor(Math.random() * xlimit);
			randomY = (int) Math.floor(Math.random() * ylimit);
			if (tileType[randomY][randomX] == '~') {
				tileType[randomY][randomX] = '<';
				placed = true;
			}
		}
	}



	public int getTileCreatureId(int x, int y) {
		return tileCreatureid[y][x];
	}



	public void setTileCreatureid(int x, int y, int id) {
		tileCreatureid[y][x] = id;
		
	}




}




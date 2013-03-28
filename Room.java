public class Room {
	public static Room randomCircularRoom() {
		return (new Room((int)Math.floor(Math.random() * 30),(int)Math.floor(Math.random() * 30),(int)Math.ceil(Math.random() * 6 + 2)));
	}
	public static Room randomSquareRoom() {
		return (new Room((int)Math.floor(Math.random() * 30),(int)Math.floor(Math.random() * 30)));
	}
		
	private int i, j, width, height, centreX, centreY, radius;

	private char[][] tileType;
	
	//creates rectangular room
	public Room (int centreX, int centreY) {
		width = (int) Math.ceil(Math.random() * 5) + 4;
		height = (int) Math.ceil(Math.random() * 5) + 4;
		this.centreX = centreX;
		this.centreY = centreY;
		tileType = new char[30][30];
		for (i = 0; i < 30; i++) {
			for (j=0; j< 30; j++) {
				if ((inRange(centreY - ((width - 1) / 2)) <= i) & (inRange(centreY + ((width-1)/2)) >= i) & (inRange(centreX - ((width - 1) / 2)) <= j) & (inRange(centreX + ((width-1)/2)) >= j)) {
					tileType[i][j] = '~';
				}
			}
		}
	}
	
	//creates circular room
	public Room (int centreX, int centreY, int radius) {
		this.centreX = centreX;
		this.centreY = centreY;
		this.radius = radius;
		tileType = new char[30][30];
		width = 2 *radius + 1;
		height = 2 * radius + 1;
		for (i = 0; i < 30; i++) {
			for (j = 0; j < 30; j++) {
				if (Math.floor(Board.xyDist(centreX, j, centreY, i)) <= radius) {
					tileType[i][j] = '~';
				} else {
					tileType[i][j] = ' ';
				}
			}
		}
	}
	
	public int getCentreX() {
		return centreX;
	}
	
	public int getCentreY() {
		return centreY;
	}
	
	public char getTile(int y, int x) {
		return tileType[y][x];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String toString() {
		String out = "";
		for (i = 0; i < 30;i++) {
			for (j = 0; j < 30; j++) {
				out = out + tileType[i][j];
			}
			out = out + "\r";
		}
		return out;
	}
	

	
	public static int inRange(int n) {
		if (n < 0) {
			return 0;
		}
		if (n > 29) {
			return 29;
		}
		return n;
	}
}

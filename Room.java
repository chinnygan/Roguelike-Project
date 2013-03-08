package rogue;

//Very much WIP

public class Room {
private char[][] tileType;
	private int i, j, width, height, centreX, centreY;

	//creates rectangular room
	public Room (int centreX, int centreY) {
		int width = (int) Math.ceil(Math.random() * 5) + 2, height = (int) Math.ceil(Math.random() * 5) + 2;
		this.centreX = centreX;
		this.centreY = centreY;
		tileType = new char[(2 * height) + 1][(2 * width) + 1];
		for (i = centreY - height; i <= centreY + height; i++) {
			for (j = centreX - width; j <= centreX + width; j++) {
				if ((centreX - j >= 0) && (centreX + j <= width - 1) && (centreY - i >= 0) && (centreY + i <= height - 1)) {
					tileType[i][j] = '~';
				}
			}
		}
	}

	//creates circular room
	public Room (int centreX, int centreY, int radius) {
		tileType = new char[(2 * radius) + 1][(2 * radius) + 1];
		for (i = centreY - height; i <= centreY + height; i++) {
			for (j = centreX - width; j <= centreX + width; j++) {
				if (Board.xyDist(centreY, i, centreX, j) <= radius) {
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
		return tileType[i][j];
	}
}

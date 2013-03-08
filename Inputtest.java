package rogue;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

// due to the way eclipse is designed there's no way to code clearing the console into the program, 
// but this could be used as a starting point for a movement/grid generation system

public class Inputtest {

// Functions

// Determines if a tile is within the player's sight radius. Does NOT account for line of sight.

	public static boolean insightradius(int tileX, int tileY, int PlayerX, int PlayerY, int Playersight) {
		if ((Math.abs(PlayerX-tileX) <= Playersight) && Math.abs(PlayerY - tileY) <= Playersight) {
			return true;
		} else {
			return false;
		}
	}

// Determines if a player can move to a tile on the x or y dimension using the player position and limits

	public static boolean validmovement(int Player, int limit) {
		if ((Player > 0) && (Player < (limit - 1))) {
			return true;
		} else {
			return false;
		} 
	}

// Main program, segregated to help understand what each part of the code does.

	public static void main(String[] args) {

/////////////////////////////////////////////////////////////////////////////////////////////////

// Instantiates variables as needed, could be cleaned up to one line per variable type for final version.

		int Xlength = 50, Ylength = 50;
		Character[][] Pixel = new Character[Xlength][Ylength];
		Character[][] VPixel = new Character[Xlength][Ylength];
		int PlayerX = (int)Math.ceil((Math.random() * 50) - 1);
		int PlayerY = (int)Math.ceil((Math.random() * 50) - 1);
		int x, y, Playersight = 5;

		Scanner input = new Scanner(System.in);

/////////////////////////////////////////////////////////////////////////////////////////////////

// Assigns every tile the value '~' and determines that the player does not know what is in any of the squares.
// Then determines which squares the player can see and assigns the appropriate values to the Visible tiles.

		for ( y = 0; y < Ylength; y++) {
			for (x = 0; x < Xlength; x++) {
				Pixel[y][x] = '~';
				if (insightradius(x,y,PlayerX,PlayerY,Playersight)) {
					VPixel[y][x] = Pixel[y][x];
					VPixel[PlayerY][PlayerX] = '@';
				}
				if (VPixel[y][x] == null) {
					VPixel[y][x] = ' ';
				}
			}
		}

// Prints player position. Probably a more efficient way to do this, but there's no need to fix what ain't broken.
// Then reprints the grid with the player position in mind. Actually just thought of a way to make this more efficient
//but I don't have access to coding facilities atm.

		VPixel[PlayerY][PlayerX] = '@';

		for ( y = 0; y < Ylength; y++) {
			for (x = 0; x < Xlength; x++) {
				System.out.print(VPixel[y][x]);
			}
			System.out.println();
		}

// Gets the next player movement from the console

		String Movement = input.next();

// Loop which determines if a player can move as per the console command and then reprints the grid according
// to what the player last saw in each square. Repeats until the console command 'End' is entered.

		do {


			switch (Movement) {
				case "Up":
					if (validmovement(PlayerY,Ylength)) {
						PlayerY--;
					}
					break;
				case "Left":
					if (validmovement(PlayerX,Xlength)) {
						PlayerX--;
					}
					break;
				case "Right":
					if (validmovement(PlayerX,Xlength)) {
						PlayerX++;
					}
					break;
				case "Down":
					if (validmovement(PlayerY,Ylength)) {
						PlayerY++;
					}
					break;
				default:
					break;
			}

			for ( y = 0; y < Ylength; y++) {
				for (x = 0; x < Xlength; x++) {
					if (insightradius(x,y,PlayerX,PlayerY,Playersight)) {
						VPixel[y][x] = Pixel[y][x];
						VPixel[PlayerY][PlayerX] = '@';
					}
					if (VPixel[y][x] == null) {
						VPixel[y][x] = ' ';
					}

					System.out.print(VPixel[y][x]);
				}

				System.out.println();
			}

			Movement = input.next();
		} while (Movement != "End"); 



	}
}
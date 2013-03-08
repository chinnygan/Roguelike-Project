package rogue;

public class Monster extends Agent {

	  // Agent p is to be replaced by the player

		// Finds the distance between the Monster and the player, and determines
		// whether Monster attacks or moves towards the player depending on this
		// distance
		public static void action(Monster m, Agent p) {
			int xDif = m.getX() - p.getX();
			int yDif = m.getY() - p.getY();

			// Checks that the monster is in attack distance AND that it is not on a
			// diagonal to the player
			if (Math.abs(xDif) <= 1 && Math.abs(yDif) <= 1
					&& Math.abs(xDif) != Math.abs(yDif)) {
				m.attack();
			} else {
				Monster.moveTowardsPlayer(m, xDif, yDif);
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
		public static void moveTowardsPlayer(Monster m, int xDif, int yDif) {
			int xAbs = Math.abs(xDif);
			int yAbs = Math.abs(yDif);

			// Possibly going to add a case of when xAbs == yAbs (the monster is on
			// a diagonal to the player), but for now it seems
			// unnecessary and defaults to a movement on the y axis
			if (xAbs > yAbs) {
				if (xDif < 0) {
					m.moveRight();
				} else {
					m.moveLeft();
				}
			} else {
				if (yDif < 0) {
					m.moveDown();
				} else {
					m.moveUp();
				}
			}
		}

		// Testing functions for the Monster
		public static void main(String[] args) {
			Monster monster1 = new Monster();
			Agent player = new Agent();

			// Set the Monster's x & y values here
			monster1.setX(-5);
			monster1.setY(-5);

			// Set the Player's x & y values here
			player.setX(0);
			player.setY(0);

			System.out.println("Player starts (" + player.getX() + " , "
					+ player.getY() + ")");
			System.out.println("Monster starts (" + monster1.getX() + " , "
					+ monster1.getY() + ")");

			System.out.println();

			for (int i = 0; i < 10; i++) {
				Monster.action(monster1, player);

				System.out.println("Monster at (" + monster1.getX() + " , "
						+ monster1.getY() + ")");
			}
		}

	}
package rogue;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

//Update 1: Added a few classes for getting damage values and for using items
public class Item {
	
	private int id, def;
	private String name, type, dmg, description, effect;
	private char rep;
	public Item(int id) {
		this.id = id;
		int i = 0;
		List<String> raw = new ArrayList<String>();
		In in= new In("C:/Users/Ross/Rogue/RogueProject/data/items.txt"); 
		while (in.hasNextLine()) {
			raw.add(i, in.readLine());
			i++;
		}
		decrypt(raw.get(id));
		switch (type) {
		case "Weapon":
			rep = ')';
			break;
		case "Armor":
			rep = ']';
			break;
		case "Consumable":
			rep = '!';
			break;
		}
	}

	// Reads in a set of strings from the items.txt file in the format: name(dmg,def,type,rep,effect,description)
	// Assigns values and sets item variables to appropriate values

		public static String chop(String s) {
			if (s.indexOf(',') != -1) {
				return s.substring(s.indexOf(',')+1, s.length());
			} else {
				return s.substring(0, s.indexOf(','));
			}
		}

		public String toString() {
			String out = "";
			switch (type) {
			case "Weapon":
				out = name + ": " + dmg + '\r' + description;
				break;
			case "Armor":
				out = name + ": DR " + def + '\r' + description;
				break;
			case "Consumable":
				out = name + ": " + '\r' + description;
				break;
			case "Misc":
				out = name + ": " + '\r' + description;
			}
			return out;
		}

		public void decrypt(String s) {
			name = s.substring(0,s.indexOf('('));
			s = s.substring(s.indexOf('(')+1, s.indexOf(')'));
			dmg = s.substring(0, s.indexOf(','));
			s = Item.chop(s);
			def = Integer.parseInt(s.substring(0, s.indexOf(',')));
			s = Item.chop(s);
			type = (s.substring(0, s.indexOf(',')));
			s = Item.chop(s);
			effect = s.substring(0, s.length());
			s = Item.chop(s);
			description = (s.substring(0, s.length()));
		}

		public static Item randomItem() {
			final int total = 13; // must be set to the number of items in the items.text file + 1
			return new Item((int)Math.floor(Math.random() * total));
		}

		public int getid() {
			return id;
		}

		public char getrep() {
			return rep;
		}

		public String getname() {
			return name;
		}

		public int getdef() {
			return def;
		}

		public String getdescription() {
			return description;
		}

		public String gettype() {
			return type;
		}

		public String getdmgString() {
			return dmg;
		}

		public int getdmgVal() {
			int total = 0;
			if (type == "Weapon") {
				int i;
				if (dmg.equals("0")) {
					return 0;
				} else {
					for (i = 0; i < Integer.parseInt(dmg.substring(0, dmg.indexOf('d'))); i++) {
						total = total + (int) Math.ceil(Math.random() * Integer.parseInt(dmg.substring(dmg.indexOf('d')+1, dmg.length())));
					}
				}
			}
			return total;
		}

		public void use() {
			// will go in Player class so the item can be deleted from inventory
		}

}


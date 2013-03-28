import java.util.ArrayList;
import java.util.List;

public class Item {
	
	public static Item randomItem() {
		final int total = 13; // must be set to the number of items in the items.text file + 1
		return new Item(Client.getItemResource(), (int)Math.floor(Math.random() * total));
	}
	private int id, def;
	private String name, type, dmg, effect, desc;
		
	// Reads in a set of strings from the items.txt file in the format: name(dmg,def,type,rep,effect,description)
	// Assigns values and sets item variables to appropriate values
		
		private char rep;
		
		public Item(ItemResource items, int id) {
			this.id = id;
			this.name = (String) items.getNames().get(id);
			this.dmg = (String) items.getdmg().get(id);
			this.def = (int) items.getdef().get(id);
			this.effect = (String) items.geteffect().get(id);
			this.type = (String) items.gettype().get(id);
			this.desc = (String) items.getdesc().get(id);
		}
		

		
		public int evaluate(String dicerep) {
			int i, total = 0;
			for (i = 0; i < Integer.parseInt(dicerep.substring(0, dicerep.indexOf('d'))); i++) {
				total = total + (int) Math.ceil(Math.random() * Integer.parseInt(dicerep.substring(dicerep.indexOf('d')+1, dicerep.length())));
			}
			return total;
		}
		
		public int getdef() {
			return def;
		}
		
		
		public String getdmgString() {
			return dmg;
		}
		
		public int getdmgVal() {
			int total = 0;
			if (type == "Weapon") {
				if (dmg.contains("d")) {
					total = evaluate(dmg);
				} else {
					return 0;
				}
			}
			return total;
		}
		
		public int getid() {
			return id;
		}
		
		public String getname() {
			return name;
		}
		
		public char getrep() {
			return rep;
		}
		
		public String gettype() {
			return type;
		}
		
		public void heal(int n) {
			Combat.heal(evaluate("" + n +"d6"), Client.getPlayer());
		}
		
		public void increaseHP(int n) {
			Client.getPlayer().setHp(Client.getPlayer().getHp() + n);
		}
		
		public void increaseStr(int n) {
			Client.getPlayer().setStr(Client.getPlayer().getStr() + n);
		}
		
		public void increaseArm(int n) {
			Client.getPlayer().setArm(Client.getPlayer().getArm() + n);
		}
		
		public void increaseXP(int n) {
			Client.getPlayer().setXP(Client.getPlayer().getXP() + n);
		}
		
		public void increaseAllStats(int n) {
			Client.getPlayer().setStr(Client.getPlayer().getStr() + n);
			Client.getPlayer().setArm(Client.getPlayer().getArm() + n);
			Client.getPlayer().setHp(Client.getPlayer().getHp() + (n * 5));
		}
		
		public void increasePlayerDef(Player p) {
			//p.setDmg(p.getDef()+1);
		}
		
		public void increasePlayerDmg(Player p) {
			//p.setDmg(p.getDmg()+1);
		}
		
		public void increasePlayerMaxHP(Player p) {
			//p.setHP(p.getHP()+5);
		}
			
		public void increasePlayerXP(Player p) {
			//p.setDmg(p.getDmg()+500);
		}
		
		@Override
		public String toString() {
			String out = "";
			switch (type) {
			case "Weapon":
				out = name + ": " + dmg + "\r" + desc;
				break;
			case "Armor":
				out = name + ": DR " + def + "\r" + desc;
				break;
			case "Consumable":
				out = name + ": " + "\r" + desc;
				break;
			case "Misc":
				out = name + ": " + "\r" + desc;
			}
			return out;
		}
		
		public void use() {
			// will go in Player class so the item can be deleted from inventory
		}
		
		public static void printItems() {
			int i;
			System.out.println("Number of items: " + Client.getItemResource().getSize());
			for (i=0;i<Client.getItemResource().getSize();i++) {
				System.out.println(new Item(Client.getItemResource(), i));
				System.out.println();
			}
		}
}
		




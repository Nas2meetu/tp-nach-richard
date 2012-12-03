package tp.pr2;

/**
 *
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 1
 *
 */

import static tp.pr2.Constants.*;

public class ItemContainer {

	// private ArrayList<Item> container;
	// private Vector<Item> container;
	private int cont;
	private Item[] container;

	public ItemContainer() {
		container = new Item[100];
		cont = 0;
	}

	public Item[] getContainer() {
		return container;
	}

	public int numberOfItems() {
		return container.length;
		// return container.size();
	}

	public Item pickItem(String id) {

		int pos = posItem(id);
		if (pos == -1)
			return null;
		Item item = container[pos];
		this.moveItemLeft(pos);
		cont--;
		return item;
	}

	private int whereInsert(String id){
		int i = 0;
		while (i < cont) {
			if (container[i].getId().compareTo(id)<0) {
				return i;
			}
			i++;
		}
		return cont;
	}
	private int posItem(String id) {
		int i = 0;
		while (i < cont) {
			if (container[i].getId().equals(id)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private void moveItemLeft(int i) {
		for (int j = i; j < cont; j++) {
			container[j] = container[j + 1];
		}
		cont--;
	}

	private void moveItemRight(int i) {

		for (int j = cont; j < i; j--) {
			container[j] = container[j + 1];
		}
		cont++;
	}

	public boolean addItem(Item item) {
		int pos = posItem(item.getId());
		if (pos != -1)
			return false;
		pos = whereInsert(item.getId());
		this.moveItemRight(pos);
		container[pos]=item;
		cont++;
		return true;
	
	}

	/*
	 * public Item pickItem(String id) {
	 * 
	 * 
	 * //container.remove(id); return item; }
	 */

	/*
	 * public boolean addItem(Item item) {
	 * 
	 * int i = 0; while (i < this.container.size() &&
	 * !(this.container.elementAt(i).getId().equals(item.getId()))) i++; if (i
	 * == container.size()) { container.add(item); return true; } else return
	 * false; }
	 */

	/*
	 * private String showContainer(){ String list = CONTAINER; Iterator <Item>
	 * walleitem = container.iterator();
	 * 
	 * while (walleitem.hasNext()){ list = list + (walleitem.next().toString());
	 * } if (container.size() == 0){ // no items en el container list =
	 * (CONTAINER_EMPTY); } return list; }
	 * 
	 * 
	 * public ArrayList<Item> getContainer() { return container; }
	 * 
	 * public String toString(){ return showContainer(); }
	 */

}

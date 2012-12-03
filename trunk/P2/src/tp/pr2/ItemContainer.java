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
	
	//private ArrayList<Item> container;
	private Item[] container;
	//private Vector<Item> container; 
	
	public ItemContainer(){
	//	this.container = new Vector<Item>();
	}

	public Item[] getContainer() {
		return container;
	}

	public int numberOfItems() {
		return container.length;
		//return container.size();
	}
	public boolean addItem(Item item) {
	int i=0;
	while (i<container.length) {
		if (container[i].getId().equals(item.getId())){
			return false;
		}
		i++;
	}
	container[container.length+1]=item;
	return true;
	}

	/*public Item pickItem(String id) {
	 
	
		//container.remove(id);
		return item;
	}*/


	/*public boolean addItem(Item item) {
		
		int i = 0;
		while (i < this.container.size()
		&& !(this.container.elementAt(i).getId().equals(item.getId())))
			i++;
			if (i == container.size()) {
				container.add(item);
				return true;
			} else
			return false;
		}*/ 
		


	public Item getItem(String id) {
		return getItem(null);//hacer getItem, veremos como hacerlo
	}
	/*private String showContainer(){
		String list = CONTAINER;
		Iterator <Item> walleitem = container.iterator();
		
		while (walleitem.hasNext()){
			list = list + (walleitem.next().toString());
		} 	
		if (container.size() == 0){  // no items en el container
			list = (CONTAINER_EMPTY);
		}
		return list; 
	}*/
	
	
	/*public ArrayList<Item> getContainer() {
		return container;
	}
	
	public String toString(){
		return showContainer();
	}*/

}

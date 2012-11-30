package tp.pr2;

//import static tp.pr2.Constants.*;

public class ItemContainer {
	
	//private ArrayList<Item> container;
	private Item[] container;
	
	public ItemContainer(){
		container=null;
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
	}

	

	public Item getItem(String id) {
		return item;
	}
	private String showContainer(){
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

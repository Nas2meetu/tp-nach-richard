package tp.pr2;

import static tp.pr2.Constants.*;

/**
 *
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 1
 *
 */

public class ItemContainer {

	
	private int numberOfItems;
	private Item[] container;
	
	public ItemContainer() {
		container = new Item[100];
		numberOfItems = 0;
	}

	public Item[] getContainer() {
		return container;
	}

	public int numberOfItems() {
		return numberOfItems;
	}
	
	public Item getItem(String id){
		
		int i = 0;
		while (i < numberOfItems) {
			if (container[i].getId().equalsIgnoreCase(id)) {
				return container[i];
			}
			i++;
		}return null;
	}
	/**
	 * Usamos el posItem para sacar la posicion del id del Item
	 * Si la posicion es -1, devuelve item vacio
	 * Creamos una instancia Item en la que metemos el item del container en la posicion (pos)
	 * usamos despues moveItemLeft para desplazar desde la posicion (pos) a la izquierda y
	 * ponemos el cont uno menos, al finalizar, devolvemos el item 
	 * 
	 * Use posItem to know the position of item's id. 
	 * If position is -1, return an empty item.
	 * Make an instance Item and put into container's item in position (pos)
	 * Use after moveItemLeft to shift move position (pos) to left and
	 * decrease cont one unit, finally return item.
	 * 
	 * @param id is a reference to identify an item.
	 * 
	 * @return picked item has been pick or not.
	 */
	
	public Item pickItem(String id) { 
		Item picked;
		int pos = posItem(id);
		if (pos == -1)
			picked = null;
		else{
			Item item = container[pos];
			getItem(item.getId());
			this.moveItemLeft(pos);
			picked = item;
		}
		return picked;
	}
	
	/**
	 * En whereInsert recibimos un id de un item y sabremos la posicion donde hay que insertarlo
	 * Para ello, usamos una variable "i" para recorrer el array container hasta el final del 
	 * container, donde iremos comparando todos los items del container con el "id" del item que
	 * recibe el metodo, si la comparacion es menor que 0, querrá decir que es menor y devolverá
	 * "i", que será la posición. Si llega hasta el final del array y no hay ninguno menor,
	 * entonces la posicion donde insertar el item será "cont" o "cont + 1"  
	 * 
	 * Recibe and item's id, and find position to insert this item.
	 * Use i parameter to move around container's array, from i position to end of container's array.
	 * Compare all item container with id was received from method. 
	 * If comparation is small than 0 return i (position to insert item)
	 * else increase i counter.
	 * If counter i find the end of array position of insert item is i.
	 * 
	 * @param id is a reference to identify an item.
	 * 
	 * @return i is a position to insert item.
	 */

	private int whereInsert(String id){
		int i = 0;
		while (i < numberOfItems) {
			if (container[i].getId().compareToIgnoreCase(id)>0) {
				return i;
			}else
				i++;
		}
		return i;
	}
	
	/**
	 * PosItem recibe un "id" de un item, recorre el array container y mira si el "id" del item
	 * y el "id" del container son iguales, si es asi devuelve la posicion "i" del array. Sino
	 * devuelve -1
	 * 
	 * PosItem recive an item's id, move around item's container and 
	 * compare item's id with container's id, if equal return i. 
	 * Else return -1
	 * 
	 * @param id is a reference to identify an item.
	 * 
	 * @return -1
	 */
	
	private int posItem(String id) {
		int i = 0;
		while (i < numberOfItems) {
			if (container[i].getId().equalsIgnoreCase(id)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	/**
	 * moveItemLeft moves Item's position one to left from initial position (pos).
	 * 
	 * deplaza la posicion del container una posicion a la izquierda a partir de un
	 * "pos" dado.
	 * 
	 * @param pos initial position.
	 */

	private void moveItemLeft(int pos) {
		for (int j = pos; j < numberOfItems; j++) {
			container[j] = container[j + 1];
		}
		numberOfItems--;
	}
	
	/**
	 * moveItemRight deplaza la posicion del container una posicion a la derecha a partir de un
	 * "i" dado.
	 * 
	 * moveItemLeft moves Item's position one to right from initial position (i).
	 * 
	 * @param i initial position.
	 */

	private void moveItemRight(int pos) {

		for (int j = numberOfItems; j >= pos; j--) {
			container[j+1] = container[j];
		}
		numberOfItems++;
	}
	
	/**
	 * addItem se encarga de añadir un item al container de itemContainer. Para ello, crea una 
	 * instancia "pos" con la posicion generada por el metodo posItem con el "id" del item, si la
	 * posicion es -1 entonces devuelve false y no añade el item. Por el contrario, sino es asi
	 * modifica la posicion "pos" con la posicion donde tendremos que insertar el item, desplazamos
	 * el array container una posicion a la derecha e insertamos el item. Incrementamos el "cont"
	 * en 1 y devolvemos true. 
	 * 
	 * addItem insert an item in itemContainer's container.
	 * 
	 * @param item
	 * 
	 * @return added
	 */

	public boolean addItem(Item item) {
		boolean added;
		int pos = posItem(item.getId());
		if (pos != -1)
			added = false;
		else{
			pos = whereInsert(item.getId());
			this.moveItemRight(pos);
			container[pos]=item;
			added = true;
		}
			return added;
	
	}
	
	/**
	 * 
	 * Show content of ItemContainer
	 * 
	 */
	
	public String toString(){
	
		String showItems = "";
		if (numberOfItems() == 0) { 
			return CONTAINER_EMPTY;
		} else {
			for (int i = 0; i < numberOfItems(); i++) {
				showItems += "   " + container[i].getId() + LINE_SEPARATOR;
			}
			return showItems;
		}
	}
	
	/**
	 * 
	 * Show items of container.
	 * 
	 * @return showItems items of container.
	 */
	
	public String showItems(){
		
		String showItems = "";
		if (numberOfItems() == 0) { 
			return CONTAINER_EMPTY;
		} else {
			for (int i = 0; i < numberOfItems(); i++) {
				showItems += "   " + container[i].getId() + LINE_SEPARATOR;
			}
			System.out.println(CONTAINER);
			return showItems;
		}
	}
	 
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
	 * */
	  

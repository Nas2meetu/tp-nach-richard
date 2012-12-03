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
	
	/**
	 * Usamos el posItem para sacar la posicion del id del Item
	 * Si la posicion es -1, devuelve item vacio
	 * Creamos una instancia Item en la que metemos el item del container en la posicion (pos)
	 * usamos despues moveItemLeft para desplazar desde la posicion (pos) a la izquierda y
	 * ponemos el cont uno menos, al finalizar, devolvemos el item 
	 * 
	 * @param id
	 * @return
	 */
	public Item pickItem(String id) { 

		int pos = posItem(id);
		if (pos == -1)
			return null;
		Item item = container[pos];
		this.moveItemLeft(pos);
		cont--;
		return item;
	}
	
	/**
	 * En whereInsert recibimos un id de un item y sabremos la posicion donde hay que insertarlo
	 * Para ello, usamos una variable "i" para recorrer el array container hasta el final del 
	 * container, donde iremos comparando todos los items del container con el "id" del item que
	 * recibe el metodo, si la comparacion es menor que 0, querrá decir que es menor y devolverá
	 * "i", que será la posición. Si llega hasta el final del array y no hay ninguno menor,
	 * entonces la posicion donde insertar el item será "cont" o "cont + 1"  
	 * 
	 * @param id
	 * @return
	 */

	private int whereInsert(String id){
		int i = 0;
		while (i < cont) {
			if (container[i].getId().compareTo(id)<0) {
				return i;
			}else
				i++;
		}
		return cont+1;
	}
	
	/**
	 * PosItem recibe un "id" de un item, recorre el array container y mira si el "id" del item
	 * y el "id" del container son iguales, si es asi devuelve la posicion "i" del array. Sino
	 * devuelve -1
	 * 
	 * @param id
	 * @return
	 */
	
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
	
	/**
	 * moveItemLeft deplaza la posicion del container una posicion a la izquierda a partir de un
	 * "i" dado.
	 * 
	 * @param i
	 */

	private void moveItemLeft(int i) {
		for (int j = i; j < cont; j++) {
			container[j] = container[j + 1];
		}
		cont--;
	}
	
	/**
	 * moveItemRight deplaza la posicion del container una posicion a la derecha a partir de un
	 * "i" dado.
	 * 
	 * @param i
	 */

	private void moveItemRight(int i) {

		for (int j = cont; j < i; j--) {
			container[j] = container[j + 1];
		}
		cont++;
	}
	
	/**
	 * addItem se encarga de añadir un item al container de itemContainer. Para ello, crea una 
	 * instancia "pos" con la posicion generada por el metodo posItem con el "id" del item, si la
	 * posicion es -1 entonces devuelve false y no añade el item. Por el contrario, sino es asi
	 * modifica la posicion "pos" con la posicion donde tendremos que insertar el item, desplazamos
	 * el array container una posicion a la derecha e insertamos el item. Incrementamos el "cont"
	 * en 1 y devolvemos true. 
	 * 
	 * @param item
	 * @return
	 */

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

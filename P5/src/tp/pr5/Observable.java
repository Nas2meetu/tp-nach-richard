package tp.pr5;

import java.util.List;
import java.util.Vector;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 */

public class Observable<T> {

	protected List<T> observers;
	
	public Observable()
	{
		this.observers = new Vector<T>();
	}

	public void addObserver(T observer) {
		if (!observers.contains(observer))
			observers.add(observer);
	}

	public void removeObserver(T observer) {
		if (observers.contains(observer))
			observers.remove(observer);
	}

}

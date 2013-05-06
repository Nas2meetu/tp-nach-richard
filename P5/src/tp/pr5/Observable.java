package tp.pr5;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {

	private List<T> observers = new ArrayList<T>();

	public List<T> getObservers() {
		return observers;
	}

	public void setObservers(List<T> observers) {
		this.observers = observers;
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

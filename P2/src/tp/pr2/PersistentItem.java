package tp.pr2;

public abstract class PersistentItem extends Item {
		
		public PersistentItem(String id, String description) {
			
			super(id, description);
			
		}
		
		public String toString() {
			return super.toString();
		}
		
		public boolean canBeUsed() {
			return true;
		}

}

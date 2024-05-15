package Gestionnaire;

public abstract class GameManager {
	Gestionnaire parent;

	public GameManager(Gestionnaire parent) {
		this.parent = parent;
	}

	public SaleDiscriber getFirstSalle() {
		return null;
	}

	public SaleDiscriber getSalle(int num) {
		return null;
	}

	/**
	 * GameManager
	 */
	public GameManager() {
	}
}

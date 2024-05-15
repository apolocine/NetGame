package Gestionnaire;

public interface SaleDiscriber {
	public TableDiscriber getFirstTable();

	public int getNbJoueurDansLaSalle();

	public int getNbMaxTable();

	public TableDiscriber getTable(int num);

	public boolean isFull();

	public void setNbMaxTable(int MaxTable);
}

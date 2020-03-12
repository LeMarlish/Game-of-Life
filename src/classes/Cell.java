package classes;

public class Cell {
	
	private int status = 0;
	// 1 : Alive cell
	// 0 : Dead cell

	public Cell(int alive) {
		this.status = alive;
	}

	public int getCellStatus() {
		return status;
	}

	public void setCellStatus(int alive) {
		this.status = alive;
	}

}

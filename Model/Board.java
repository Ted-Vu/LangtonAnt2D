package Model;

public class Board {

	public enum Color {
		BLACK, WHITE;

		public Color inverseColour() {
			return WHITE.equals(this) ? BLACK : WHITE;
		}
	}

	private Color[][] cells;
	private int universeSize;

	public Board(int size) {
		this.universeSize = size;
		cells = new Color[universeSize][universeSize];

		// initially all cells are white
		for (int row = 0; row < universeSize; ++row) {
			for (int col = 0; col < universeSize; ++col) {
				cells[row][col] = Color.WHITE;
			}
		}
	}

	public Color getCellColor(int x, int y) {
		return cells[x][y];
	}

	public void setCellColor(int x, int y, Color color) {
		cells[x][y] = color;
	}

	public int getSize() {
		return universeSize;
	}
}

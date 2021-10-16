package Model;

public class Ant {

	public int x, y, steps;
	public Direction direction;

	public Ant(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public boolean isInWorld(Board w) {
		return 0 <= x && x < w.getSize() && 0 <= y && y < w.getSize();
	}

	public void step(Board w) {
		Board.Color c = w.getCellColor(x, y);

		direction = (Board.Color.WHITE == c) ? direction.turnRight() : direction.turnLeft();
		w.setCellColor(x, y, c.inverseColour());
		x += direction.deltaX;
		y += direction.deltaY;

		steps++;
	}

}
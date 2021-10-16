package Model;

public enum Direction {

	// use of Enum
	NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

	public int deltaX, deltaY;

	private Direction(int dX, int dY) {
		deltaX = dX;
		deltaY = dY;
	}

	public Direction turnLeft() {
		return Direction.values()[(this.ordinal() + 3) % 4];
	}

	public Direction turnRight() {
		return Direction.values()[(this.ordinal() + 1) % 4];
	}

}
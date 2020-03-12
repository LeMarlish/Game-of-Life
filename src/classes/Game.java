package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import others.Status;

@SuppressWarnings("serial")
public class Game extends JPanel implements MouseListener, MouseMotionListener {

	private Status status;
	private static final int SIZE_X = 100;
	private static final int SIZE_Y = 60;
	private static final int SIZE = 10;
	private Cell[][] cell = new Cell[SIZE_X][SIZE_Y];
	private Cell[][] cellCache = new Cell[SIZE_X][SIZE_Y];

	private int gameSpeed = 200;
	private int currentSpeed = 2;

	// 0 - Slowest - 500
	// 1 - Slow - 300
	// 2 - Normal - 200
	// 3 - Fast - 125
	// 4 - Fastest - 75

	public Game() {
		// Initialise all cells as dead
		for (int i = 0; i < SIZE_X; i++) {
			for (int j = 0; j < SIZE_Y; j++) {
				cell[i][j] = new Cell(0);
				cellCache[i][j] = new Cell(0);
			}
		}

		addMouseListener(this);
		addMouseMotionListener(this);
		setStatus(Status.PAUSED);
	}

	public void update() {

		if (status == Status.RUNNING) {
			updateCells();
		}
		this.repaint();

		// Game speed
		try {
			Thread.sleep(gameSpeed);
		} catch (InterruptedException e) {
			// Do nothing.
		}

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setGameSpeed(int speed) {
		currentSpeed += speed;

		switch (currentSpeed) {
		case 0:
			gameSpeed = 500;
			break;
		case 1:
			gameSpeed = 300;
			break;
		case 2:
			gameSpeed = 250;
			break;
		case 3:
			gameSpeed = 100;
			break;
		case 4:
			gameSpeed = 50;
			break;
		default:
			// Catch invalid values.
			if (currentSpeed == -1) {
				currentSpeed = 0;
			} else if (currentSpeed == 5) {
				currentSpeed = 4;
			}
		}

	}

	public void clearMap() {
		for (int i = 0; i < SIZE_X; i++) {
			for (int j = 0; j < SIZE_Y; j++) {
				cell[i][j].setCellStatus(0);
			}
		}
		update();
	}

	public void clickedCell(MouseEvent e) {
		int x;
		int y;
		x = e.getPoint().x / 10;
		y = e.getPoint().y / 10;
		// Catch invalid values
		if (x >= 0 && y >= 0 && x < SIZE_X && y < SIZE_Y) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				cell[x][y].setCellStatus(1);
			} else if (SwingUtilities.isRightMouseButton(e)) {
				cell[x][y].setCellStatus(0);
			}
		}

		repaint();

	}

	public void updateCells() {

		int neighbours;

		// Look for neighbour cells
		// Get the position of a cell
		for (int x = 0; x < SIZE_X; x++) {
			for (int y = 0; y < SIZE_Y; y++) {
				neighbours = 0;
				// Look for neighbour cells
				for (int i = x - 1; i <= x + 1; i++) {
					for (int j = y - 1; j <= y + 1; j++) {
						// Catch out of bounds indexes in the array
						if ((i >= 0 && i < SIZE_X) && (j >= 0 && j < SIZE_Y)) {
							// Makes sure it's not checking the position of the cell itself
							if((((i != x && j == y) || (i == x && j != y)) || (i != x && j != y))) {
								neighbours += cell[i][j].getCellStatus();
							}
						}
					}
				}
				// Apply the game's rules
				if (neighbours == 3) {
					cellCache[x][y].setCellStatus(1); // Cell lives
				} else if (neighbours == 2 && cell[x][y].getCellStatus() == 1) {
					cellCache[x][y].setCellStatus(1); // Cell lives
				} else {
					cellCache[x][y].setCellStatus(0); // Cell dies
				}
			}
		}
		// Update cell array with cellCache array's values.
		for (int i = 0; i < SIZE_X; i++) {
			for (int j = 0; j < SIZE_Y; j++) {
				cell[i][j].setCellStatus(cellCache[i][j].getCellStatus());
				//cellCache[i][j].setCellStatus(0);
			}
		}

	}

	///////////////////////////////
	// PAINT WORLD
	@Override
	public void paint(Graphics g) {
		Color color = new Color(238, 238, 238);
		g.setColor(color);
		for (int i = 0; i < SIZE_X; i++) {
			for (int j = 0; j < SIZE_Y; j++) {
				g.fillRect(i * SIZE, j * SIZE, SIZE, SIZE);
			}
		}

		g.setColor(Color.BLACK);
		for (int i = 0; i < SIZE_X; i++) {
			for (int j = 0; j < SIZE_Y; j++) {
				if (cell[i][j].getCellStatus() == 1) {
					g.fillRect(i * SIZE, j * SIZE, SIZE, SIZE);
				}
			}
		}

		g.setColor(Color.GRAY);
		for (int i = 0; i < SIZE_X; i++) {
			for (int j = 0; j < SIZE_Y; j++) {
				g.drawRect(i * SIZE, j * SIZE, SIZE, SIZE);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clickedCell(e);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		clickedCell(e);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// clickedCell(e);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Do nothing

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// clickedCell(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

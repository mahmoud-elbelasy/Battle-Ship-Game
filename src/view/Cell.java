package view;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.Board;
import javaapplication1.Ship;

public class Cell extends Rectangle {

	// The Ship that occupies this Cell (if any)
	public Ship ship = null;
	
	// Whether this Cell has been marked as shot or not
	public boolean shot = false;
	
	// The (x, y) coordinate of this Cell on the Board
	public int x = 0;
	public int y = 0;
	
	// The Board that this Cell belongs to
	private Board board;

	// Constructor for the Cell
	public Cell(Board b, int x, int y) {
		super(30, 30);           
		this.board = b;        
		this.x = x;
		this.y = y;
		setFill(Color.GRAY);  
		setStroke(Color.DARKGRAY);
	}

	// Mark this Cell as shot and change its appearance accordingly
	public boolean takeShot() {
		shot = true;
		setFill(Color.ANTIQUEWHITE);
		setStroke(Color.BISQUE);

		// If this Cell contains a Ship, mark the Ship as hit and change the Cell's appearance accordingly
		if (ship != null) {
			ship.hit();
			setFill(Color.DARKRED);
			setStroke(Color.ORANGERED);
			
			// If the Shipis sunk, decrement the number of ships on the Board
			if (!ship.alive()) {          
				board.setNumShips(board.getNumShips() - 1);
			}
			return true;
		}
		return false;
	}

	// Check if the specified (x, y) coordinate is a valid point on the Board
	public boolean isValid(int x, int y) {
		return board.isValid(x, y);
	}

	// Get the Ship that occupies this Cell (if any)
	public Ship getShip() {
		return this.ship;
	}
}
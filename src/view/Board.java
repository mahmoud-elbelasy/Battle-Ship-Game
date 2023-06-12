
package view;


import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import java.util.*;
import javaapplication1.Ship;

public class Board  extends Parent{
	// The VBox that holds all the HBox rows of Cells
	VBox rows = new VBox(); 
        // The number of ships on the board
	private int numShips = 6;
        
        // Whether this Board is for the enemy player (true) or the player (false)
        boolean enemy = false; 

        // Constructor for the Board
	public Board(EventHandler<? super MouseEvent> handler, boolean isEnemy) {
            
                // Set the enemy flag based on the input parameter
		this.enemy = isEnemy;
                
                // Create 10 rows of Cells and add them to the VBox
		for( int i = 0; i < 10; i++) {
			HBox row = new HBox();
			for(int j = 0; j < 10; j++) {
				Cell cell = new Cell(this,j, i);
				cell.setOnMouseClicked(handler);
				row.getChildren().add(cell);
			}
			rows.getChildren().add(row);
		}
                // Add the VBox of rows to the Board
		getChildren().add(rows);
	}

	// Get the Cell at the specified (x, y) coordinate
	public Cell getCell(int x, int y) {
		return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
	}
        
        // Check whether a Ship can be placed at the specified (x, y) coordinate
	public boolean allowPlaceShip(Ship ship, int x, int y) {
            
                // Get the length of the Ship
		int len = ship.type;
                
                // Check if the Ship can be placed vertically
		if(ship.isVertical) {
			for(int a = y; a < y+len; a++) {
                            
                                // Check if the (x, a) coordinate is a valid point on the Board
				if(!ValidPoint(x, a))  
					return false;
				// Check if the Cell at the (x, a) coordinate already has a Ship
				Cell cell = getCell(x, a); 
				if(cell.ship != null)
					return false;
                                
                                // Check if any of the neighboring Cells have a Ship
				
			}
                
                // Check if the Ship can be placed horizontally
		} else {
			for( int a = x; a < x + len; a++ ) {
                                // Check if the (a, y) coordinate is a valid point on the Board
				if(!ValidPoint(a, y))
					return false;
				
                                // Check if the Cell at the (a, y) coordinate already has a Ship
				Cell cell = getCell(a, y); 
				if(cell.ship != null)
					return false;
                                
                                
				}
			}
                // If the Ship can be placed without overlapping any other Ships, return true
		return true;
		}
        
       

        // Place the specified Ship at the (x, y) coordinate if it is allowed
	public boolean placeShip(int x, int y, Ship ship) {
		if(allowPlaceShip(ship,x,y)) {
			int len = ship.type;
			if(ship.isVertical) {
				for(int a = y; a < y+len; a++) {
					Cell cell = getCell(x, a);
					cell.ship = ship;
					if(!enemy) {
						cell.setFill(Color.DARKCYAN);
						cell.setStroke(Color.DARKSLATEGRAY);
					}
				}
			}else {
				for(int a = x; a < x+len;a++) {
					Cell cell = getCell(a, y);
                    cell.ship = ship;
                    if (!enemy) {
                        cell.setFill(Color.DARKCYAN); 
                        cell.setStroke(Color.DARKSLATEGRAY);  
				}
			}
		}
			return true;
	}
		return false;
}
        
        // Check if the specified (x, y) coordinate is a valid point on the Board
	private boolean ValidPoint(double x, double y) {
		if(x >= 0 && x < 10 && y >= 0 && y < 10) {
			return true;
		}else {
			return false;
		}
	}
		
        // Check if the specified (x, y) coordinate is a valid point on the Board
	public boolean isValid(int x, int y){
		if(x >= 0 && x < 10 && y >= 0 && y < 10) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
        // Get the number of ships on the Board
	public int getNumShips() {
		return numShips;
	}
	
        // Set the number of ships on the Board
	public void setNumShips(int numShips) {
		this.numShips = numShips;
	}
}

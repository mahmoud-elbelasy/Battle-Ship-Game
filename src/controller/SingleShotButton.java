/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Mcs
 */
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import view.Board;
import view.Cell;
/**
 * Represents a slash button.
 * @author 
 */
public class SingleShotButton{
	public static int singleShot(Cell c) {
		int sunkShip = 0;
		if (c.takeShot()) {
			if (!(c.getShip().alive())) {
				sunkShip=2; // sunk ship
			}
			else {
				sunkShip=1;
			}
		}
		return sunkShip;
	}
	
}
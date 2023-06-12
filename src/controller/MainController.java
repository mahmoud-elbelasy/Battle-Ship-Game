
package controller;

/**
 *
 * @author Mcs
 */   


import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.*;
import java.util.Random;
import view.Cell;
import view.Board;
import javaapplication1.Ship;
import javafx.application.Platform;
import javafx.scene.image.Image;



public class MainController extends Application {
    

    public void setAllowedShips(int allowedShips) {
        this.allowedShips = allowedShips;
    }
   

	/**
         * 
         * 
	 * A boolean value specifying if this turn is the enemy's turn.
	 */
	private boolean isEnemyTurn = false;

	/**
	 * If true, the game is currently running. If false, both players are currently
	 * placing ships.
	 */
	private boolean run = false;

	/**
	 * A reference to the board that the player is using.
	 */
	private Board player;

	/**
	 * A reference to the board that the enemy is using.
	 */
	private Board enemy;

	/**
	 * An integer value specifying the amount of allowed ships.
	 */
	private int allowedShips = 5;

	/**
	 * A reference to a pseudo-random number generator.
	 */
	private Random rand = new Random();

	/**
	 * A boolean value specifying if it is the enemy's turn.
	 */
	private boolean enemysTurn = false;

	/**
	 * A boolean value specifying if either player has won this game.
	 */
	private boolean victory = false;

	/**
	 * A reference to the text area that displays the desired information.
	 */
	private TextArea info = new TextArea();// added text area

	/**
	 * An integer value specifying the enemy's turn.
	 */
	private int enemyTurnNumber = 0;
        
        

	/**
	 * An integer value specifying the players turn.
	 */
	private int playerTurnNumber = 0;

	/**
	 * A label for enemy board.
	 */
	private Label enemyLabel = new Label("Enemy - Turn " + enemyTurnNumber);
        

	/**
	 * A label for the player board.
	 */
//	
        private Label playerLabel = new Label("Player - Turn " + playerTurnNumber);
        
	private boolean oneShot =  true;
        private String getDefaultMessage(){//added default message in text area
		return 
				"\t    ⚓ 𝒮𝓉𝒶𝓇𝓉𝒾𝓃𝑔 𝒯𝒽𝑒 𝒢𝒶𝓂𝑒 ⚓\n\t\t⚓ 𝒫𝓁𝒶𝒸𝑒 𝓉𝒽𝑒 𝓈𝒽𝒾𝓅𝓈 ⚓\n";
	}
        private String numEnemyShips() {
            return "the number of remaning ships of enemy is:" +  enemy.getNumShips();
        }
        
        private String numPlayerShips() {
            return "the number of remaning ships of player is:" +  player.getNumShips();
        }

	/**
	 * Starts this game.
	 */
        GameSound s1 = new GameSound();
	public void gameStart() {
		int num = 5;

		while (num > 0) {
			int y = rand.nextInt(10);
			int x = rand.nextInt(10);
                        int z = rand.nextInt(6);
			if (enemy.placeShip(x, y, new Ship(num, Math.random() < 0.5))) {
                                num--;
			}
		}
		run = true;
	}
                public void placeShips(MouseEvent event) {
		if (run)
			return;
		Cell c = (Cell) event.getSource();
		if (player.placeShip(c.x, c.y, new Ship(allowedShips, event.getButton() == MouseButton.PRIMARY)))
			if (--allowedShips == 0) {
				gameStart();
			}
                
	}
        private void enemysMove(){
		if (!victory) {
			while (enemysTurn) {
				int y = rand.nextInt(10);
				int x = rand.nextInt(10);

				
				Cell c = player.getCell(x, y);
                                if(oneShot) {
					if (c.shot) {
						continue;
					}
					System.out.println("single" + enemyTurnNumber);
					enemyDisplay(SingleShotButton.singleShot(c));
				}
                        }
                }
        }
        private void enemyDisplay(int sunkShip) {
		enemysTurn = true;
		if(sunkShip == 2) {
			info.appendText("\nOne of your SHIPS have been SUNK! \n\nYou have " + player.getNumShips() + " ship(s) REMAINING!\n");
		}else if (sunkShip == 1) {
			info.appendText("\nOne of your SHIPS have been HIT!");
		}
		else {
			enemysTurn = false;
		}
	}
        
        
         
	public void setButtons(BorderPane root, Stage primaryStage) {
         
		VBox box = new VBox();
		HBox row1 = new HBox(10);

		Label label2 = new Label("Display:");
		label2.setTextFill(Color.WHITE);
		label2.setStyle("-fx-font: 18 arial;"
                + "-fx-font-weight : bold;"
                );
		
		Label label3 = new Label("Menu:");
		label3.setTextFill(Color.WHITE);
		label3.setStyle("-fx-font: 18 arial;"
                + "-fx-font-weight : bold;"
                );
                
                
                Button exitButton = new Button("Exit");
                exitButton.setStyle("-fx-font: 18 arial;"
                + "-fx-base: #ed0c1c;-fx-font-weight : bold;"
                );
                Button RestartButton = new Button("Restart");
                RestartButton.setStyle("-fx-font: 18 arial;"
                + "-fx-base: #3fc0d2;-fx-font-weight : bold;"
                );
		exitButton.setOnAction(e -> {
			this.exit(primaryStage);
		});
                RestartButton.setOnAction(e -> {
                    this.restartPrimaryStage(primaryStage); 
                });

                row1.getChildren().addAll(exitButton,RestartButton);
                info = new TextArea();
		info.setEditable(false);
		info.setPrefSize(300, 300);
		info.setWrapText(true);
                info.setStyle("-fx-control-inner-background: #a2d4e4; -fx-font-size: 12pt;");
		info.setText(getDefaultMessage());
		box.setSpacing(10);
		box.setPadding(new Insets(10, 10, 10, 10));
		box.getChildren().addAll( label2, info, label3, row1);
		root.setLeft(box);
        }
        
        
        private void exit(Stage primaryStage) {
		primaryStage.close();
	
	}
        private void display(int sunkShip) {
		enemysTurn = false;
		if(sunkShip == 2) {
			info.appendText("\nCRITICAL HIT!\n You SUNK one of the enemy's SHIPS! \n\nThe enemy has "
					+ enemy.getNumShips() + " ship(s) REMAINING!\n");
		}else if (sunkShip == 1) {
			info.appendText("\nYou HIT one of the enemy's SHIPS!");
		}
		else {
			enemysTurn = true;
		}
	}

        
        public void shoot(MouseEvent event) {
		if (!victory) {
			if (!run)
				return;
			Cell c = (Cell) event.getSource();

                        if(oneShot) {
				if (c.shot) {
					return;
				}
				display(SingleShotButton.singleShot(c));
			}
                        if (enemy.getNumShips() == 0) {
				
				info.appendText("\n\n\n\t ****** You Win! *******");
                        
				this.victory = true;
			}
			if (enemysTurn)
				playerTurnNumber++;
				playerLabel.setText("Player - Turn " + playerTurnNumber);
				enemyLabel.setText("Enemy - Turn " + playerTurnNumber );
				enemysMove();
                        }
                        if (player.getNumShips() == 0) {
				// Win message or picture(s)
				info.appendText("\n\n\n\t***** Enemy Win! *****");
				this.victory = true;
			}
	}

	public Parent create(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setPrefSize(800, 800);
		setButtons(root, primaryStage);
		playerLabel.setTextFill(Color.ALICEBLUE);
		playerLabel.setStyle("-fx-font: 18 arial;"
                + "-fx-base: #ed0c1c;-fx-font-weight : bold;"
                );
		enemyLabel.setTextFill(Color.ALICEBLUE);
		enemyLabel.setStyle("-fx-font: 18 arial;"
                + "-fx-base: #ed0c1c;-fx-font-weight : bold;"
                );

		enemy = new Board(event -> {shoot(event);}, true);
		player = new Board(event -> {placeShips(event);}, false);
		root.setId("root");
		VBox vbox = new VBox(10, enemyLabel, enemy, playerLabel, player);
		vbox.setAlignment(Pos.CENTER);
		root.setCenter(vbox);

		return root;
                
	}
	
	/**
	 * 
	 * Creates the start screen.
	 * 
	 * @return Returns the pane that this start screen is placed within
	 * 
	 */
	public Parent startScreen(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setPrefSize(800, 800);
		pane.setId("pane");
		Button button = new Button("START");
		button.setStyle("-fx-font: 18 arial;"
                + "-fx-base: #50586f;-fx-font-weight : bold;");

		button.setOnAction(e-> {
			Scene scene = new Scene(create(primaryStage));
			scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
		});
		pane.setCenter(button);
		return pane;
	}

	@Override

	public void start(Stage primaryStage) {
            
            s1.mainSound.play();

			primaryStage.setTitle("BattleShip Game");
			primaryStage.setResizable(false);
			Scene scene = new Scene(startScreen(primaryStage));
			scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
                        primaryStage.getIcons().add(new Image("Images/game_icon.png"));

	}


        public void restartPrimaryStage(Stage primaryStage) {
            primaryStage.close();
            primaryStage = new Stage();
            Platform.runLater(() -> {

                new MainController().start(new Stage());

            });

        }
        
        
	public static void main(String[] args) {
		launch(args);
	}
        
        

		
	}
        
        

                
     
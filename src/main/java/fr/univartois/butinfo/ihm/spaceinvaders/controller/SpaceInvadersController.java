package fr.univartois.butinfo.ihm.spaceinvaders.controller;

import java.net.URL;

import fr.univartois.butinfo.ihm.spaceinvaders.model.AbstractMovable;
import fr.univartois.butinfo.ihm.spaceinvaders.model.GameGrid;
import fr.univartois.butinfo.ihm.spaceinvaders.model.ISpaceInvadersController;
import fr.univartois.butinfo.ihm.spaceinvaders.model.SpaceInvadersGame;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SpaceInvadersController implements ISpaceInvadersController{
	
	private SpaceInvadersGame game;
	
	private Background createBackground(String name) {
	    BackgroundImage backgroundImage = new BackgroundImage(
	            game.getSpriteStore().createSprite(name),
	            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	    return new Background(backgroundImage);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	private Label[][] jeuGridLabel;
	
	@FXML
	private Stage stage;
	
    @FXML
    private GridPane jeuGrid;

    @FXML
    private Label scoreText;

    @FXML
    private Label vieText;
    
    @FXML
    private Label winnerText;

	@Override
	public void setSpaceInvadersGame(SpaceInvadersGame game) {
		this.game = game;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void prepDisp(GameGrid grid) {
		// TODO Auto-generated method stub
    	int row = jeuGrid.getRowCount();
    	int column = jeuGrid.getColumnCount();
    	jeuGridLabel = new Label[row][column];
    	for(int i=0;i<row;i++) {
    		for(int j=0;j<column;j++) {
    			jeuGridLabel[i][j] = new Label();
    			jeuGridLabel[i][j].setBackground(createBackground("back"));
    			if (i == row-1) {
    				jeuGridLabel[i][j].setGraphic(new ImageView(game.getSpriteStore().createSprite("back")));
    			}
    			jeuGridLabel[i][j].setPrefSize(100, 100);
    			jeuGrid.add(jeuGridLabel[i][j], j, i);
    			backgroundLabel(grid, i, j);
    		}
    	}
		
		stage.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
    		e.consume();
    		switch(e.getCode()) {
		
			case RIGHT->
				game.moveRight();
				
			case LEFT->
				game.moveLeft();
			
    		}
		});
		stage.addEventFilter(KeyEvent.KEY_TYPED, e -> {
    		e.consume();
    		switch(e.getCharacter()) {
				
			case " ":
				game.fireShot();
				break;
				
			default:
				break;
    		}
		});
		
	}

	public void backgroundLabel(GameGrid grid, int r, int c) {
		grid.get(r, c).getMovableProperty().addListener(
				(p,o,n)->{
					if(n != null) {
						jeuGridLabel[r][c].setGraphic(createGraphicFor(n));
					}
					else {
						jeuGridLabel[r][c].setGraphic(null);
					}
				});
	}
	private Node createGraphicFor(AbstractMovable movable) {
		  HBox box = new HBox();
		  box.setPrefHeight(50);
		  box.setPrefWidth(50);
		  box.setAlignment(Pos.CENTER);

		  ImageView view = new ImageView(movable.getSprite());
		  box.getChildren().add(view);

		  return box;
		}

	@Override
	public void addMovable(AbstractMovable movable) {
		int c = movable.getColumn();
		int r = movable.getRow();
		
	}

	@Override
	public void setWinner(String message) {
		// TODO Auto-generated method stub
		winnerText.setText(message);
	}

	@Override
	public void setHealth(SimpleIntegerProperty health) {
		// TODO Auto-generated method stub
		vieText.textProperty().bind(health.asString());
	}

	@Override
	public void setScore(SimpleIntegerProperty score) {
		// TODO Auto-generated method stub
		scoreText.textProperty().bind(score.asString());
		
	}

}

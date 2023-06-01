package fr.univartois.butinfo.ihm.spaceinvaders.model;

import javafx.beans.property.SimpleIntegerProperty;

public interface ISpaceInvadersController {

	void setSpaceInvadersGame(SpaceInvadersGame game);
	
	void prepDisp(GameGrid grid);
	
	void addMovable(AbstractMovable movable);
	
	void setWinner(String message);
	
	void setHealth(SimpleIntegerProperty health);

	void setScore(SimpleIntegerProperty score);
	
}
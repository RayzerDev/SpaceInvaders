package fr.univartois.butinfo.ihm.spaceinvaders.controller;

import java.io.IOException;

import fr.univartois.butinfo.ihm.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.ihm.spaceinvaders.model.SpriteStore;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SelectThemeController {
	
	

    @FXML
    private ImageView alienImage;

    @FXML
    private ImageView backImage;

    @FXML
    private Label heart;

    @FXML
    private ImageView heartImage;

    @FXML
    private ImageView landImage;

    @FXML
    private ListView<String> listThemes;

    @FXML
    private ImageView shipImage;

    @FXML
    private ImageView shotImage;

    private SpriteStore spriteStore;

	private Stage currentStage;
    
    @FXML
    private void initialize() {
    	spriteStore = new SpriteStore("base");
    	listThemes.setItems(FXCollections.observableArrayList("asterix","base","starwars"));
    	listThemes.getSelectionModel().selectedItemProperty().addListener(
    			(p,o,n)->{
    				spriteStore = new SpriteStore(n);
    				alienImage.setImage(spriteStore.createSprite("alien"));
    				shipImage.setImage(spriteStore.createSprite("ship"));
    				backImage.setImage(spriteStore.createSprite("back"));
    				shotImage.setImage(spriteStore.createSprite("shot"));
    				landImage.setImage(spriteStore.createSprite("land"));
    				heartImage.setImage(spriteStore.createSprite("mine"));
    			});
    }
    @FXML
    void validTheme(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/spaceinviders-view.fxml"));
        Parent viewContent = fxmlLoader.load();
        
        SpaceInvadersController controller = fxmlLoader.getController();
        SpaceInvadersGame game = new SpaceInvadersGame(11,11,listThemes.getSelectionModel().selectedItemProperty().get());
        controller.setStage(currentStage);
        game.setController(controller);
        controller.setSpaceInvadersGame(game);
        game.prepare();
        game.start();
        // Ensuite, on la place dans une Scene...
		Scene scene = new Scene(viewContent);
        // que l'on place elle-même dans la fenêtre.
        currentStage.setScene(scene);
    }
    public void setStage(Stage s) {
    	currentStage = s;
    }
}

package com.example.crapgame;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image; // Esta es la que necesitas
import java.awt.*;

public class GameController {
    LevelManager levelManager = new LevelManager();
    LifesManager lifesManager = new LifesManager();
    TimeManager timeManager = new TimeManager();
    WordManager wordManager = new WordManager();


    @FXML
    private Button sendWordButton;
    @FXML
    private TextField wordField;
    @FXML
    private Label wordLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private Label correctWrongLabel;
    @FXML
    private ProgressBar timeProgressBar;
    @FXML
    private ImageView lifesImageView;

    private void handleSendWordButtonAction() {
        if (wordManager.checkWord(wordField.getText())) {
            correctWrongLabel.setText("Correcto");
        } else {
            correctWrongLabel.setText("Incorrecto");
        }
    };
    private void handleLifesImageViewAction() {
        lifesManager.decreaseLifes();
        lifesManager.lifesOver();
    };
    private void handleTimeProgressBarAction() {
        timeManager.countdown();
    }
    private void handleLifesImageChanger() {
        switch (lifesManager.getLifes()) {
            case 0:
                Image imageZeroLifes = new Image(getClass().getResourceAsStream("/com/example/crapgame/lifes/0_lifes.png"));
                lifesImageView.setImage(imageZeroLifes);
                break;
            case 1:
                Image imageOneLifes = new Image(getClass().getResourceAsStream("/com/example/crapgame/lifes/1_lifes.png"));
                lifesImageView.setImage(imageOneLifes);
                break;
            case 2:
                Image imageTwoLifes = new Image(getClass().getResourceAsStream("/com/example/crapgame/lifes/2_lifes.png"));
                lifesImageView.setImage(imageTwoLifes);
                break;
            case 3:
                Image imageThreeLifes = new Image(getClass().getResourceAsStream("/com/example/crapgame/lifes/3_lifes.png"));
                lifesImageView.setImage(imageThreeLifes);
                break;
            case 4:
                Image imageFourLifes = new Image(getClass().getResourceAsStream("/com/example/crapgame/lifes/4_lifes.png"));
                lifesImageView.setImage(imageFourLifes);
                break;
            default:
                break;
        }

    }




    @FXML
    private void initialize() {}
}
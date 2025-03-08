package com.example.crapgame;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

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



    @FXML
    private void initialize() {}
}
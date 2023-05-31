package GUI;

import Engine.Game;
import Engine.GameSettings;
import Engine.InventoryManager;
import Engine.Level.LevelManager;
import Logs.LogTo;
import Logs.Logger;
import Utility.Window;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import network.udp.ConnectingClient;
import network.udp.HostingClient;
import network.udp.IPManager;
import network.udp.client.ClientController;
import network.udp.client.ClientControllerSingleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

public class GUIManager {
    private Window window;
    private Stage stage;
    private LevelManager levelManager;
    private GameSettings settings;

    public GUIManager(Window window, Stage stage, LevelManager levelManager) {
        this.window = window;
        this.stage = stage;
        this.levelManager = levelManager;
        this.settings = new GameSettings();
    }

    public void renderWin() {
        VBox pane = new VBox();
        pane.setSpacing(10);
        pane.setAlignment(Pos.CENTER);

        Label text = new Label("You won! All enemies are dead.");
        Button goToLevels = new Button("Go to Levels");

        goToLevels.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//            renderLevels();
        });

        pane.getChildren().addAll(text, goToLevels);
        stage.setScene(new Scene(pane));
    }

    public void renderLose() {
        VBox pane = new VBox();
        pane.setSpacing(10);
        pane.setAlignment(Pos.CENTER);

        Label text = new Label("You lose! Try again next time.");
        Button goToLevels = new Button("Go to Levels");

        goToLevels.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//            renderLevels();
        });

        pane.getChildren().addAll(text, goToLevels);
        stage.setScene(new Scene(pane));
    }

    private void displayErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

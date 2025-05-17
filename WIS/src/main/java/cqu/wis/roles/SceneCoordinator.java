/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Handles switching between multiple scenes in the application
 * Uses a hashmap to store scenes with enum keys.
 * 
 * @author Ujjwal Dhakal 12222900
 */
public class SceneCoordinator {

    /** Enum representing keys for each scene. */
    public static enum SceneKey {
        QUERY // More scenes like LOGIN, PASSWORD will be added in future
    }

    private Stage stage; // The main stage used by the application
    private HashMap<SceneKey, Scene> scenes = new HashMap<>();

    /**
     * Constructor accepts the main stage.
     * @param stage the JavaFX stage
     */
    public SceneCoordinator(Stage stage) {
        this.stage = stage;
    }

    /**
     * Adds a new scene to the coordinator.
     * @param key the identifier for the scene
     * @param value the scene object
     */
    public void addScene(SceneKey key, Scene value) {
        scenes.put(key, value);
    }

    /**
     * Starts the scene display with the QUERY scene.
     */
    public void start() {
        setScene(SceneKey.QUERY);
    }

    /**
     * Switches to a scene using the given key.
     * @param key the scene to switch to
     */
    public void setScene(SceneKey key) {
        Scene s = scenes.get(key);
        stage.setScene(s);
        stage.setTitle("Whiskey Information System");
        stage.show();
    }
}


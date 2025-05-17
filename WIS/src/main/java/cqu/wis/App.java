package cqu.wis;

import cqu.wis.data.WhiskeyData;
import cqu.wis.roles.SceneCoordinator;
import cqu.wis.roles.WhiskeyDataManager;
import cqu.wis.roles.WhiskeyDataValidator;
import cqu.wis.view.QueryController;
import cqu.wis.roles.SceneCoordinator.SceneKey;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 * 
 * @author Ujjwal Dhakal 12222900
 */

public class App extends Application {

    /**
     * JavaFX application starts
     */
    @Override
    public void start(Stage stage) {
        SceneCoordinator sc = new SceneCoordinator(stage);

        try {
            // Create backend components
            WhiskeyData wd = new WhiskeyData();
            WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
            WhiskeyDataValidator wdv = new WhiskeyDataValidator();

            // Connect to database
            wd.connect();

            // create scene
            Scene qs = makeScene(SceneKey.QUERY);

            // Injection
            QueryController qc = (QueryController) qs.getUserData();
            qc.inject(sc, wdm, wdv);

            // Register scene
            sc.addScene(SceneKey.QUERY, qs);
        } catch (Exception e) {
            System.err.println("Error during startup: " + e.getMessage());
            System.exit(0);
        }

        // Step 6: Show scene
        sc.start();
    }

    /**
     * Dynamically loads scene based on SceneKey enum
     * @param key scene key
     * @return JavaFX scene
     * @throws Exception if loading fails
     */
    private static Scene makeScene(SceneKey key) throws Exception {
        String fxml = "/cqu/wis/view/" + key.name().toLowerCase() + ".fxml";
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
        Scene scene = new Scene(loader.load());
        scene.setUserData(loader.getController());
        return scene;
    }

    /**
     *  entry point
     */
    public static void main(String[] args) {
        launch();
    }
}  
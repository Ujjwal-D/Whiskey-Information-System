package cqu.wis;

import cqu.wis.data.UserData;
import cqu.wis.data.WhiskeyData;
import cqu.wis.roles.SceneCoordinator;
import cqu.wis.roles.WhiskeyDataManager;
import cqu.wis.roles.WhiskeyDataValidator;
import cqu.wis.view.QueryController;
import cqu.wis.roles.SceneCoordinator.SceneKey;
import cqu.wis.roles.UserDataManager;
import cqu.wis.roles.UserDataValidator;
import cqu.wis.view.LoginController;
import cqu.wis.view.PasswordController;
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
     * This method fulfills all requirements to start the app
     * @param stage main window
     */
    @Override
    public void start(Stage stage) {
        SceneCoordinator sc = new SceneCoordinator(stage);

        try {
            // Backend instances
            WhiskeyData wd = new WhiskeyData();
            WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
            WhiskeyDataValidator wdv = new WhiskeyDataValidator();

            UserData ud = new UserData();
            UserDataManager udm = new UserDataManager(ud);
            UserDataValidator udv = new UserDataValidator(udm);

             wd.connect();
             ud.connect();

             // Login Scene
             Scene loginScene = makeScene(SceneKey.LOGIN);
             LoginController lc = (LoginController) loginScene.getUserData();
             lc.inject(sc, udm, udv);
             sc.addScene(SceneKey.LOGIN, loginScene);

             // Password Scene
             Scene passwordScene = makeScene(SceneKey.PASSWORD);

             PasswordController pc = (PasswordController) passwordScene.getUserData();
             pc.inject(sc, udm, udv);
             sc.addScene(SceneKey.PASSWORD, passwordScene);

             // Query Scene
             Scene queryScene = makeScene(SceneKey.QUERY);
             QueryController qc = (QueryController) queryScene.getUserData();
             qc.inject(sc, wdm, wdv);
             sc.addScene(SceneKey.QUERY, queryScene);

        } catch (Exception e) {
            System.err.println("Startup Error: " + e.getMessage());
            System.exit(0);
        }

        // Start at login scene as per Phase 4
        sc.setScene(SceneKey.LOGIN);
    }

    /**
     * Creates JavaFX scene based on SceneKey
     * 
     * @param key identifier for scenes to display
     * @return JavaFX Scene 
     * @throws Exception if scene cannot be created by any error
     */
    private static Scene makeScene(SceneKey key) throws Exception {
        String fxml = "/cqu/wis/view/" + key.name().toLowerCase() + ".fxml";    // string for location
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));// loading the FXMLLoader
        Scene scene = new Scene(loader.load()); // assigning scene
        scene.setUserData(loader.getController());  // assigning controller
        return scene;
    }
    
    /**
     * Main entry point of WIS
     * 
     * @param args command-line arguments passed to app
     */
    public static void main(String[] args) {
        launch();   // launched application
    }
}
  
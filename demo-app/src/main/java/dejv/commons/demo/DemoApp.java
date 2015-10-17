package dejv.commons.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZoomFX demo application
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.2.0
 */
public class DemoApp
        extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApp.class);


    public static void main(final String[] args) {

        try {
            Application.launch(DemoApp.class, (String[]) null);

        } catch (Exception e) {
            LOGGER.error("Unhandled exception", e);
        }
    }


    @Override
    public void start(final Stage primaryStage) throws Exception {

        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(DemoApp.class.getResource("/fxml/demo.fxml"));

        final Pane page = fxmlLoader.load();
        final Scene scene = new Scene(page);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Commons [1.2.0] Showcase");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}

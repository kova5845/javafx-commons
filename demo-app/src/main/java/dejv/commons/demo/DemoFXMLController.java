package dejv.commons.demo;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * FXML controller for fxml/demo.fxml
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.2.0
 */
public class DemoFXMLController {

    static final long serialVersionUID = 2L;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoFXMLController.class);

    @FXML
    private Pane pane;

    @FXML
    public void initialize() {
    }


}

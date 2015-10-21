package dejv.commons.demo;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dejv.jfx.controls.radialmenu.RadialMenuButton;


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
    private AnchorPane pane;

    @FXML
    public RadialMenuButton rmb;


    @FXML
    public void initialize() {
//
//        mMainMenu = new Menu("Main Menu", GlyphsDude.createIcon(FontAwesomeIcon.BARS));
//
//        final MenuItem mNew = new MenuItem("New file", GlyphsDude.createIcon(FontAwesomeIcon.FILE_ALT));
//        final MenuItem mOpen = new MenuItem("Open file", GlyphsDude.createIcon(FontAwesomeIcon.FOLDER_OPEN));
//        final Menu mSave = new Menu("Save file", GlyphsDude.createIcon(FontAwesomeIcon.FLOPPY_ALT));
//        final MenuItem mSaveAs = new MenuItem("Save file as...", GlyphsDude.createIcon(FontAwesomeIcon.FLOPPY_ALT));
//        final MenuItem mPrint = new MenuItem("Print", GlyphsDude.createIcon(FontAwesomeIcon.PRINT));
//        final MenuItem mSettings = new MenuItem("Settings", GlyphsDude.createIcon(FontAwesomeIcon.COGS));
//
//        mSave.getItems().add(mSaveAs);
//        mMainMenu.getItems().addAll(mNew, mOpen, mSave, mPrint, mSettings);
//
//        mainMenu.setMenu(mMainMenu);
//        mainMenu.getRadialMenuParams().setAngles(180, 90);
    }


    @FXML
    public void onDebug(ActionEvent actionEvent) {
        final ObservableMap<String, String> rmbStyleMap = FXCollections.observableHashMap();
        rmbStyleMap.addListener((InvalidationListener)(observable) ->
                rmb.setStyle(rmbStyleMap.entrySet().stream()
                        .map((entry) -> entry.getKey() + ": " + entry.getValue() + ";")
                        .reduce((s1, s2) -> s1 + s2).orElse("")));

        final Button bSize = new Button("Size");
        bSize.setOnAction((event) -> rmbStyleMap.put("-fx-size", "35"));

        final Button bGraphic = new Button("Graphic");
        bGraphic.setOnAction((event) -> rmbStyleMap.put("-fx-graphic","url(\"http://icons.iconarchive.com/icons/hopstarter/button/16/Button-Add-icon.png\")"));

        final HBox menuButtonRow = new HBox();
        menuButtonRow.setAlignment(Pos.CENTER_LEFT);
        menuButtonRow.getChildren().addAll(new Label("RadialMenuButton:"), bSize, bGraphic);

        final VBox vbox = new VBox();
        vbox.getChildren().addAll(menuButtonRow);

        final Dialog dialog = new Dialog();
        dialog.initModality(Modality.NONE);
        dialog.initOwner(pane.getScene().getWindow());
        dialog.setTitle("Debugging actions");
        dialog.setHeaderText("Select an action to perform below:");
        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.show();
    }
}

package dejv.commons.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import dejv.jfx.controls.radialmenu.RadialMenu;


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
    private RadialMenu mainMenu;

    private Menu mMainMenu;

    @FXML
    public void initialize() {

        mMainMenu = new Menu("Main Menu", GlyphsDude.createIcon(FontAwesomeIcon.BARS));

        final MenuItem mNew = new MenuItem("New file", GlyphsDude.createIcon(FontAwesomeIcon.FILE_ALT));
        final MenuItem mOpen = new MenuItem("Open file", GlyphsDude.createIcon(FontAwesomeIcon.FOLDER_OPEN));
        final Menu mSave = new Menu("Save file", GlyphsDude.createIcon(FontAwesomeIcon.FLOPPY_ALT));
        final MenuItem mSaveAs = new MenuItem("Save file as...", GlyphsDude.createIcon(FontAwesomeIcon.FLOPPY_ALT));
        final MenuItem mPrint = new MenuItem("Print", GlyphsDude.createIcon(FontAwesomeIcon.PRINT));
        final MenuItem mSettings = new MenuItem("Settings", GlyphsDude.createIcon(FontAwesomeIcon.COGS));

        mSave.getItems().add(mSaveAs);
        mMainMenu.getItems().addAll(mNew, mOpen, mSave, mPrint, mSettings);

        mainMenu.setMenu(mMainMenu);
        mainMenu.getRadialMenuParams().setAngles(180, 90);
    }


}

package dejv.jfx.controls.radialmenu;

import java.util.List;

import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;

import dejv.jfx.controls.radialmenu.skin.RadialMenuPopupSkin;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class RadialMenuPopup
        extends PopupControl {



    @Override
    protected Skin<?> createDefaultSkin() {
        return new RadialMenuPopupSkin(this);
    }


    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return super.getCssMetaData();
    }
}

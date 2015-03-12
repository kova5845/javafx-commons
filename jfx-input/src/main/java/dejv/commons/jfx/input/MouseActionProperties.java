package dejv.commons.jfx.input;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class MouseActionProperties {
    private final Buttons buttons;
    private final Modifiers modifiers;


    public MouseActionProperties(Buttons buttons, Modifiers modifiers) {
        this.buttons = buttons;
        this.modifiers = modifiers;
    }


    public Buttons getButtons() {
        return buttons;
    }


    public Modifiers getModifiers() {
        return modifiers;
    }
}

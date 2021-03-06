package dejv.commons.jfx.input;

/**
 * Holder class for mouse-buttons combination of the mouse events.
 * <dl><dt>Example:</dt></dl>
 * To instantiate the MouseButtons class for event, that should trigger on simultaneous press of Primary and Middle mouse buttons, use:
 * <pre>
 *  <code>
 * new MouseButtons().withPrimary().withMiddle();
 *  </code>
 * </pre>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class MouseButtons {
    private boolean primary;
    private boolean middle;
    private boolean secondary;


    /**
     * Creates the new instance of Buttons class with no buttons included.
     *
     * @return Empty MouseButtons
     */
    public static MouseButtons none() {
        return new MouseButtons();
    }


    /**
     * Creates the new instance of Buttons class with "primary" button included.
     *
     * @return MouseButtons with "primary" button included
     */
    public static MouseButtons primary() {
        return new MouseButtons().withPrimary();
    }


    /**
     * Creates the new instance of Buttons class with "middle" button included.
     *
     * @return MouseButtons with "middle" button included
     */
    public static MouseButtons middle() {
        return new MouseButtons().withMiddle();
    }


    /**
     * Creates the new instance of Buttons class with "secondary" button included.
     *
     * @return MouseButtons with "secondary" button included
     */
    public static MouseButtons secondary() {
        return new MouseButtons().withSecondary();
    }


    /**
     * Provides the information, if the Primary mouse button should be down for the related event to trigger.
     *
     * @return True if the Primary mouse button is included, false otherwise.
     */
    public boolean isPrimary() {
        return primary;
    }


    /**
     * Sets the state of the Primary button.
     *
     * @param primary Set true to include the button, false to exclude it.
     */
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }


    /**
     * Includes the Primary button setting.
     *
     * @return This instance with Primary mouse button included.
     */
    public MouseButtons withPrimary() {
        setPrimary(true);
        return this;
    }


    /**
     * Provides the information, if the Middle mouse button should be down for the related event to trigger.
     *
     * @return True if the Middle mouse button is included, false otherwise.
     */
    public boolean isMiddle() {
        return middle;
    }


    /**
     * Sets the state of the Middle button.
     *
     * @param middle Set true to include the button, false to exclude it.
     */
    public void setMiddle(boolean middle) {
        this.middle = middle;
    }


    /**
     * Includes the Middle button setting.
     *
     * @return This instance with Middle mouse button included.
     */
    public MouseButtons withMiddle() {
        setMiddle(true);
        return this;
    }


    /**
     * Provides the information, if the Secondary mouse button should be down for the related event to trigger.
     *
     * @return True if the Secondary mouse button is included, false otherwise.
     */
    public boolean isSecondary() {
        return secondary;
    }


    /**
     * Sets the state of the Secondary button.
     *
     * @param secondary Set true to include the button, false to exclude it.
     */
    public void setSecondary(boolean secondary) {
        this.secondary = secondary;
    }


    /**
     * Includes the Secondary button setting.
     *
     * @return This instance with Secondary mouse button included.
     */
    public MouseButtons withSecondary() {
        setSecondary(true);
        return this;
    }

}

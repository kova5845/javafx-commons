package dejv.commons.jfx.input;

/**
 * Holder class for modifier-keys combination of the gesture and mouse events.
 * <p>
 * <dl><dt>Example:</dt></dl>
 * To instantiate the Modifier class for event, that should trigger with simultaneous press of Alt and Ctrl keys, use:
 * <pre>
 *  <code>
 * new Modifier().withAlt().withControl();
 *  </code>
 * </pre>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class Modifiers {

    private boolean shift = false;
    private boolean control = false;
    private boolean alt = false;
    private boolean meta = false;
    private boolean shortcut = false;


    /**
     * Creates the new instance of Modifiers class with no modifiers included.
     */
    public static Modifiers empty() {
        return new Modifiers();
    }


    /**
     * Provides the information, if the Shift modifier should be active for the related event to trigger.
     *
     * @return True if the Shift modifier is included, false otherwise.
     */
    public boolean isShift() {
        return shift;
    }


    /**
     * Sets the state of the Shift modifier.
     *
     * @param shift Set true to include the modifier, false to exclude it.
     */
    public void setShift(boolean shift) {
        this.shift = shift;
    }


    /**
     * Includes the Shift modifier setting.
     *
     * @return This instance with Shift modifier included.
     */
    public Modifiers withShift() {
        setShift(true);
        return this;
    }


    /**
     * Provides the information, if the Control modifier should be active for the related event to trigger.
     *
     * @return True if the Control modifier is included, false otherwise.
     */
    public boolean isControl() {
        return control;
    }


    /**
     * Sets the state of the Control modifier.
     *
     * @param control Set true to include the modifier, false to exclude it.
     */
    public void setControl(boolean control) {
        this.control = control;
    }


    /**
     * Includes the Control modifier setting.
     *
     * @return This instance with Control modifier included.
     */
    public Modifiers withControl() {
        setControl(true);
        return this;
    }


    /**
     * Provides the information, if the Alt modifier should be active for the related event to trigger.
     *
     * @return True if the Alt modifier is included, false otherwise.
     */
    public boolean isAlt() {
        return alt;
    }


    /**
     * Sets the state of the Alt modifier.
     *
     * @param alt Set true to include the modifier, false to exclude it.
     */
    public void setAlt(boolean alt) {
        this.alt = alt;
    }


    /**
     * Includes the Alt modifier setting.
     *
     * @return This instance with Alt modifier included.
     */
    public Modifiers withAlt() {
        setAlt(true);
        return this;
    }


    /**
     * Provides the information, if the Meta modifier should be active for the related event to trigger.
     *
     * @return True if the Meta modifier is included, false otherwise.
     */
    public boolean isMeta() {
        return meta;
    }


    /**
     * Sets the state of the Meta modifier.
     *
     * @param meta Set true to include the modifier, false to exclude it.
     */
    public void setMeta(boolean meta) {
        this.meta = meta;
    }


    /**
     * Includes the Meta modifier setting.
     *
     * @return This instance with Meta modifier included.
     */
    public Modifiers withMeta() {
        setMeta(true);
        return this;
    }


    /**
     * Provides the information, if the Shortcut modifier should be active for the related event to trigger.
     *
     * @return True if the Shortcut modifier is included, false otherwise.
     */
    public boolean isShortcut() {
        return shortcut;
    }


    /**
     * Sets the state of the shortcut modifier.
     *
     * @param shortcut Set true to include the modifier, false to exclude it.
     */
    public void setShortcut(boolean shortcut) {
        this.shortcut = shortcut;
    }


    /**
     * Includes the Shortcut modifier setting.
     *
     * @return This instance with Shortcut modifier included.
     */
    public Modifiers withShortcut() {
        setShortcut(true);
        return this;
    }
}

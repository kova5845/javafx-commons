package dejv.commons.jfx.input;

/**
 * Holder class for modifier-keys combination of the mouse events.
 * <dl><dt>Example:</dt></dl>
 * To instantiate the MouseModifiers class for event, that should trigger with simultaneous press of Ctrl and Alt keys, use:
 * <pre>
 *  <code>
 * new MouseModifiers().control().withAlt();
 *  </code>
 * </pre>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.1.0
 */
public class MouseModifiers {

    private boolean shift = false;
    private boolean control = false;
    private boolean alt = false;
    private boolean meta = false;


    /**
     * Creates the new instance of MouseModifiers class with no modifiers included.
     *
     * @return Empty MouseModifiers
     */
    public static MouseModifiers none() {
        return new MouseModifiers();
    }


    /**
     * Creates the new instance of MouseModifiers class with "shift" modifier included.
     *
     * @return MouseModifiers with "shift" modifier included
     */
    public static MouseModifiers shift() {
        return new MouseModifiers().withShift();
    }


    /**
     * Creates the new instance of MouseModifiers class with "control" modifier included.
     *
     * @return MouseModifiers with "control" modifier included
     */
    public static MouseModifiers control() {
        return new MouseModifiers().withControl();
    }


    /**
     * Creates the new instance of MouseModifiers class with "alt" modifier included.
     *
     * @return MouseModifiers with "alt" modifier included
     */
    public static MouseModifiers alt() {
        return new MouseModifiers().withAlt();
    }


    /**
     * Creates the new instance of MouseModifiers class with "meta" modifier included.
     *
     * @return MouseModifiers with "meta" modifier included
     */
    public static MouseModifiers meta() {
        return new MouseModifiers().withMeta();
    }


    /**
     * Informs in shortcut, whether there are no modifiers set.
     *
     * @return True if there are no modifiers set, false otherwise.
     */
    public boolean isNone() {
        return !(isShift() || isAlt() || isControl() || isMeta());
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
    public MouseModifiers withShift() {
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
    public MouseModifiers withControl() {
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
    public MouseModifiers withAlt() {
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
    public MouseModifiers withMeta() {
        setMeta(true);
        return this;
    }
}

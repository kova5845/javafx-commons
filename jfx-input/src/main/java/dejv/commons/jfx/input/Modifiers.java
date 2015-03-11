package dejv.commons.jfx.input;

/**
 * <p>
 * @since 1.1.0
 * @author dejv78 (dejv78.github.io)
 */
public class Modifiers {

    private boolean shift = false;
    private boolean control = false;
    private boolean alt = false;
    private boolean meta = false;
    private boolean shortcut = false;


    public Modifiers() {
    }


    public boolean isShift() {
        return shift;
    }


    public void setShift(boolean shift) {
        this.shift = shift;
    }


    public Modifiers withShift() {
        setShift(true);
        return this;
    }


    public boolean isControl() {
        return control;
    }


    public void setControl(boolean control) {
        this.control = control;
    }


    public Modifiers withControl() {
        setControl(true);
        return this;
    }


    public boolean isAlt() {
        return alt;
    }


    public void setAlt(boolean alt) {
        this.alt = alt;
    }


    public Modifiers withAlt() {
        setAlt(true);
        return this;
    }


    public boolean isMeta() {
        return meta;
    }


    public void setMeta(boolean meta) {
        this.meta = meta;
    }


    public Modifiers withMeta() {
        setMeta(true);
        return this;
    }


    public boolean isShortcut() {
        return shortcut;
    }


    public void setShortcut(boolean shortcut) {
        this.shortcut = shortcut;
    }


    public Modifiers withShortcut() {
        setShortcut(true);
        return this;
    }
}

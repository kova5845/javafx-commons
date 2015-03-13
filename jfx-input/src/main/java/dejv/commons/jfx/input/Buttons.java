package dejv.commons.jfx.input;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class Buttons {
    private boolean primary;
    private boolean middle;
    private boolean secondary;


    public static Buttons empty() {
        return new Buttons();
    }


    public boolean isPrimary() {
        return primary;
    }


    public void setPrimary(boolean primary) {
        this.primary = primary;
    }


    public Buttons withPrimary() {
        setPrimary(true);
        return this;
    }


    public boolean isMiddle() {
        return middle;
    }


    public void setMiddle(boolean middle) {
        this.middle = middle;
    }


    public Buttons withMiddle() {
        setMiddle(true);
        return this;
    }


    public boolean isSecondary() {
        return secondary;
    }


    public void setSecondary(boolean secondary) {
        this.secondary = secondary;
    }


    public Buttons withSecondary() {
        setSecondary(true);
        return this;
    }

}

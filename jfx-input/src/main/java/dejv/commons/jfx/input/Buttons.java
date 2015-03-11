package dejv.commons.jfx.input;

/**
 * <p>
 *
 * @author dejv78 (dejv78.github.io)
 * @since 1.0.0
 */
public class Buttons {
    private boolean left;
    private boolean middle;
    private boolean right;


    public Buttons() {
    }


    public boolean isLeft() {
        return left;
    }


    public void setLeft(boolean left) {
        this.left = left;
    }


    public Buttons withLeft() {
        setLeft(true);
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


    public boolean isRight() {
        return right;
    }


    public void setRight(boolean right) {
        this.right = right;
    }


    public Buttons withRight() {
        setRight(true);
        return this;
    }

}

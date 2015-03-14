package dejv.commons.jfx.binding;

import static junit.framework.TestCase.assertEquals;

import java.util.function.BinaryOperator;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.Test;

import dejv.commons.binding.ReductionDoubleBinding;

/**
 * Test class for ReductionDoubleBinding
 * <br/>
 *
 * @author dejv78 (www.dejv.info)
 */
public class ReductionDoubleBindingTest {

    private static final BinaryOperator<Double> REDUCTION = ReductionDoubleBindingTest::sum;
    private static final DoubleProperty DE1 = new SimpleDoubleProperty();
    private static final DoubleProperty DE2 = new SimpleDoubleProperty();


    @Before
    public void init() {
        DE1.set(10);
        DE2.set(20);
    }


    @Test(expected = NullPointerException.class)
    public void createWithNullDependency() {
        new ReductionDoubleBinding(null, REDUCTION);
    }


    @Test(expected = NullPointerException.class)
    public void createWithNullReduction1() {
        new ReductionDoubleBinding(DE1, null);
    }


    @Test(expected = NullPointerException.class)
    public void createWithNullDependency1() {
        new ReductionDoubleBinding(null, DE2, REDUCTION);
    }


    @Test(expected = NullPointerException.class)
    public void createWithNullDependency2() {
        new ReductionDoubleBinding(DE1, null, REDUCTION);
    }


    @Test(expected = NullPointerException.class)
    public void createWithNullReduction2() {
        new ReductionDoubleBinding(DE1, DE2, null);
    }


    @Test
    public void createWithValidParams() {
        final DoubleBinding cut = new ReductionDoubleBinding(DE1, DE2, REDUCTION);

        assertEquals("Binding value 1", REDUCTION.apply(DE1.get(), DE2.get()), cut.get(), Precision.EPSILON);

        DE1.set(30);
        assertEquals("Binding value 2", REDUCTION.apply(DE1.get(), DE2.get()), cut.get(), Precision.EPSILON);

        DE2.set(50);
        assertEquals("Binding value 3", REDUCTION.apply(DE1.get(), DE2.get()), cut.get(), Precision.EPSILON);
    }


    @Test(expected = NullPointerException.class)
    public void callAddWithNullDependency() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(DE1, DE2, REDUCTION);

        cut.add(null);
    }


    @Test
    public void callAddWithValidDependency() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(DE1, DE2, REDUCTION);

        final DoubleProperty de3 = new SimpleDoubleProperty(30);
        cut.add(de3);
        assertEquals("Binding value 1", REDUCTION.apply(REDUCTION.apply(DE1.get(), DE2.get()), de3.get()), cut.get(), Precision.EPSILON);

        cut.add(de3);
        assertEquals("Binding value 2", REDUCTION.apply(REDUCTION.apply(DE1.get(), DE2.get()), de3.get()), cut.get(), Precision.EPSILON);
    }


    @Test(expected = NullPointerException.class)
    public void callRemoveWithNullDependency() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(DE1, DE2, REDUCTION);

        cut.remove(null);
    }


    @Test
    public void callRemoveWithValidDependency() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(DE1, DE2, REDUCTION);

        final DoubleProperty de3 = new SimpleDoubleProperty(-10);
        cut.add(de3);

        cut.remove(DE1);
        assertEquals("Binding value 1", REDUCTION.apply(DE2.get(), de3.get()), cut.get(), Precision.EPSILON);

        cut.remove(DE1);
        assertEquals("Binding value 2", REDUCTION.apply(DE2.get(), de3.get()), cut.get(), Precision.EPSILON);

        cut.remove(DE2);
        assertEquals("Binding value 3", de3.get(), cut.get(), Precision.EPSILON);

        cut.remove(de3);
        assertEquals("Binding value 4", Double.NaN, cut.get(), Precision.EPSILON);
    }


    @Test
    public void callClear() {
        final ReductionDoubleBinding cut = new ReductionDoubleBinding(DE1, DE2, REDUCTION);
        final DoubleProperty de3 = new SimpleDoubleProperty(-10);
        cut.add(de3);

        cut.clear();
        assertEquals("Binding value 1", Double.NaN, cut.get(), Precision.EPSILON);
    }


    private static double sum(double d1, double d2) {
        return d1 + d2;
    }
}
